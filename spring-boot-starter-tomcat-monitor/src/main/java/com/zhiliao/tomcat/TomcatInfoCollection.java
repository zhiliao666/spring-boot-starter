package com.zhiliao.tomcat;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import org.apache.tomcat.util.modeler.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zhiliao.tomcat.core.LogStore;
import com.zhiliao.tomcat.core.TomcatNamedThreadFactory;
import com.zhiliao.tomcat.core.Store;

/**
 * tomcat指标收集入口类
 *
 * @author zhangqh
 * @date 2019年2月2日
 */
public class TomcatInfoCollection {
	
	private static final Logger logger = LoggerFactory.getLogger(TomcatInfoCollection.class);
	
	/**
	 * 控制是否初始化完成状态
	 */
	private volatile boolean intialized = false;

	private ScheduledExecutorService serivce = null;

	private Store store = null;

	/**
	 * MBean server.
	 */
	protected MBeanServer mBeanServer = null;

	/**
	 * Vector of thread pools object names.
	 */
	protected Vector<ObjectName> threadPools = new Vector<ObjectName>();


	public void init() {

		logger.info("TomcatInfoCollection init start.");

		// Retrieve the MBean server
		mBeanServer = Registry.getRegistry(null, null).getMBeanServer();

		try {
			
			threadPools.clear();
			/**
			 * 注意：:type=ThreadPool 
			 * 这个是tomcat中 JMX中收集tomcat线程池中的一个mbean的名称一部分，这边加上**是模糊查找bean名称  
			 * 全名应该是：Tomcat:type=ThreadPool,name="http-nio-8083" 其中8083是应用的端口号 
			 * tomcat中不仅仅只有这一个mbean，这边只收集tomcat线程池相关数据，
			 * 需要收集其他的指标可以参考https://ci.apache.org/projects/tomcat/tomcat85/docs/manager-howto.html#JMX_Get_command文档中
			 * JMX Proxy Servlet
			 */
			String onStr = "*:type=ThreadPool,*";
			ObjectName objectName = new ObjectName(onStr);
			Set<ObjectInstance> set = mBeanServer.queryMBeans(objectName, null);
			Iterator<ObjectInstance> iterator = set.iterator();
			while (iterator.hasNext()) {
				ObjectInstance oi = iterator.next();
				threadPools.addElement(oi.getObjectName());
			}

		} catch (Exception e) {
			logger.error("TomcatInfoCollection init failed.", e);
		}

		serivce = Executors
				.newSingleThreadScheduledExecutor(new TomcatNamedThreadFactory(
						"zhiliao-tomcat-collect"));

		store = new LogStore();

		this.intialized = true;

		logger.info("TomcatInfoCollection init success.");
	}

	public void startMonitor() {

		if (isIntialized()) {
			return;
		}

		init();

		serivce.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				collect();
			}
		}, 30000, 1000, TimeUnit.MILLISECONDS);

	}

	public void collect() {
		if (!isIntialized()) {
			init();
		}
		if (!isIntialized()) {
			logger.error("TomcatInfoCollection init error.");
			return;
		}
		try {
			Enumeration<ObjectName> enumeration = threadPools.elements();
			while (enumeration.hasMoreElements()) {
				ObjectName objectName = enumeration.nextElement();

				TomcatInfo serverInfo = new TomcatInfo();
				serverInfo.setMaxThreads((Integer) mBeanServer.getAttribute(
						objectName, "maxThreads"));
				serverInfo.setCurrentThreads((Integer) mBeanServer
						.getAttribute(objectName, "currentThreadCount"));
				serverInfo.setBusyThreads((Integer) mBeanServer
						.getAttribute(objectName, "currentThreadsBusy"));

				store.storeInfo(serverInfo);

			}
		} catch (Exception e) {
			logger.error("TomcatInfoCollection info failed.", e);
		}
	}

	public void stopMonitor() {
		this.intialized = false;
		if (serivce != null && !serivce.isShutdown())
			serivce.shutdown();
	}

	public boolean isIntialized() {
		return intialized;
	}
}
