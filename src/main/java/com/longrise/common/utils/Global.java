package com.longrise.common.utils;

import java.util.HashMap;

import org.springframework.context.ApplicationContext;

public class Global {
	public static HashMap<String, String> forgetVCode;
	private static String webRootRealPath;
	private static Integer RedisDefaultTable;
	private static ApplicationContext applicationContext;

	static {
		forgetVCode = new HashMap<String, String>();
	}

	public static String formatResultString(String ret, String ms) {
		return String.format("{\"result\":\"%s\",\"ms\":\"%s\"}", ret, ms);
	}

	public static void setWebRootRealPath(String path) {
		System.out.println(path);
		webRootRealPath = path;
	}

	public static String getWebRootRealPath() {
		return webRootRealPath;
	}

	public static void setRedisDefaultTable(Integer rdt) {
		RedisDefaultTable = rdt;
	}

	public static Integer getRedisDefaultTable() {
		return RedisDefaultTable;
	}

    public static ApplicationContext getApplicationContext ()
    {
        return applicationContext;
    }

    public static void setApplicationContext ( ApplicationContext applicationContext )
    {
        Global.applicationContext = applicationContext;
    }
	
}
