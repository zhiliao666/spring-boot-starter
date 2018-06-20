package com.zhiliao.until;

import java.io.Serializable;

/**
 * api 返回结果包装类
 *
 * @author zhangqh
 * @date 2018年6月20日
 */
public class ApiResult<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int successCode = 200;
	//系统异常
	public static final int errorCode500 = 500;
	//token失效
	public static final int errorCode301 = 301;
	//参数错误
	public static final int errorCode302 = 302;
	
	/**
	 * 返回状态码 200 成功 301 token失效 500 系统异常
	 */
    private Integer c = successCode;
    /**
     * 对应返回码说明
     */
    private String m;
    /**
     * 具体返回数据
     */
    private T d;
    
    /**
	 * 自定义错误码
	 * @param errorCode
	 * @param errorMsg
	 * @return
	 */
	public ApiResult<T> setError(int errorCode ,String errorMsg) {
		this.c = errorCode ;
		this.m = errorMsg ;
		return this ;
	}

	public ApiResult<T> setResult(T result) {
		this.m = "成功" ;
		this.d = result;
		return this ;
	}
	/**
	 * @return the c
	 */
	public Integer getC() {
		return c;
	}
	/**
	 * @param c the c to set
	 */
	public void setC(Integer c) {
		this.c = c;
	}
	/**
	 * @return the m
	 */
	public String getM() {
		return m;
	}
	/**
	 * @param m the m to set
	 */
	public void setM(String m) {
		this.m = m;
	}
	/**
	 * @return the d
	 */
	public T getD() {
		return d;
	}
	/**
	 * @param d the d to set
	 */
	public void setD(T d) {
		this.d = d;
	}

    
    
}
