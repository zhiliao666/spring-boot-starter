package com.zhiliao.tomcat;

public class TomcatInfo {
	/**
	 * 最大线程数
	 */
	private int maxThreads;
	/**
	 * 当前创建线程数
	 */
	private int currentThreads;
	/**
	 * 当前工作线程数
	 */
	private int busyThreads;
	/**
	 * @return the maxThreads
	 */
	public int getMaxThreads() {
		return maxThreads;
	}
	/**
	 * @param maxThreads the maxThreads to set
	 */
	public void setMaxThreads(int maxThreads) {
		this.maxThreads = maxThreads;
	}
	/**
	 * @return the currentThreads
	 */
	public int getCurrentThreads() {
		return currentThreads;
	}
	/**
	 * @param currentThreads the currentThreads to set
	 */
	public void setCurrentThreads(int currentThreads) {
		this.currentThreads = currentThreads;
	}
	/**
	 * @return the busyThreads
	 */
	public int getBusyThreads() {
		return busyThreads;
	}
	/**
	 * @param busyThreads the busyThreads to set
	 */
	public void setBusyThreads(int busyThreads) {
		this.busyThreads = busyThreads;
	}

	
	
}
