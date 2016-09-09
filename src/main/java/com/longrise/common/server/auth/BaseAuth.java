package com.longrise.common.server.auth;


/**
 * 鉴权抽象类，主要定义了以下方法 授权；鉴权；
 * 
 * @author tangsw
 *
 */
public abstract class BaseAuth {
	public static final String AUTH_KEY = "___AUTHKEY___"; 
	/**
	 * 鉴权
	 * @param accessKey
	 * @return
	 */
	public abstract boolean auth(Object[] objects);
	/**
	 * 授权
	 * @param objects
	 * @return
	 */
	public abstract boolean grant(Object[] objects);
	/**
	 * 是否允许授权
	 * @param objects
	 * @return                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
	 */
	public abstract boolean isGrant(Object result );
}