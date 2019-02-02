package com.zhiliao.tomcat.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class TomcatNamedThreadFactory implements ThreadFactory
{
	private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

	private final AtomicInteger mThreadNum = new AtomicInteger(1);

	private final String name;

	private final boolean mDaemo;

	private final ThreadGroup mGroup;

	public TomcatNamedThreadFactory()
	{
		this("pool-" + POOL_SEQ.getAndIncrement(),false);
	}

	public TomcatNamedThreadFactory(String name)
	{
		this(name,false);
	}

	public TomcatNamedThreadFactory(String name,boolean daemo)
	{
		this.name = name;
		mDaemo = daemo;
        SecurityManager s = System.getSecurityManager();
        mGroup = ( s == null ) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
	}

	public Thread newThread(Runnable runnable)
	{
        Thread ret = new Thread(mGroup,runnable,name+"-"+mThreadNum.getAndIncrement(),0);
        ret.setDaemon(mDaemo);
        return ret;
	}

	public ThreadGroup getThreadGroup()
	{
		return mGroup;
	}

}
