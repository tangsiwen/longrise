package com.longrise.common.server;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ServiceInfo {
	final static Logger Log = LoggerFactory.getLogger(ServiceInfo.class);
	private static Map<String,ServiceConfig> serviceInfo = new HashMap<String,ServiceConfig>();
	public static void setServiceInfo(Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		Annotation[] classAnn = clazz.getAnnotations();
		String serviceRootPath = ""; 
		for(Annotation a:classAnn)
		{
			if(a.annotationType().equals(Path.class))
			{
				Path p = (Path) a;
				serviceRootPath = p.value();
				if("/".equals(serviceRootPath))
				{
						serviceRootPath = "";
				}
			}
		}
		for (Method method : methods) {
			String path = "";
			String name = "";
			String version = "";
			String authMethod = "";
			String stateless = "";
			String permitMethod = "";
			String monitored = "";
			String httpType = "";
			String authorizer = "";
			if (method.isAnnotationPresent(ServiceConfig.class)) {
				ServiceConfig bs = (ServiceConfig) method.getAnnotation(ServiceConfig.class);
				name = bs.logic();
				serviceInfo.put(name, bs);
				version = bs.version();
				if (bs.stateless()) {
					stateless = "无需鉴权";
				} else {
				    
					stateless = "需鉴权";
//					authMethod = "鉴权规则={";
//					for (Class<? extends BaseAuth> ba : bs.authmethods()) {
//						authMethod += ba.getName() + ",";
//					}
//					authMethod = authMethod.substring(0, authMethod.length() - 1);
//
//					authMethod = authMethod + "}";
					authMethod = "鉴权规则：" + bs.authmethod().getName();
					permitMethod = "授权规则：" + bs.permitmethod().getName();

				}
				monitored = bs.Monitored() ? "已监管" : "无监管";
				authorizer = bs.authorizer() ? "授权接口" : "不许授权";
			}
			if (method.isAnnotationPresent(POST.class)) {
				httpType = "POST";
			}
			if (method.isAnnotationPresent(GET.class)) {
				httpType = "GET";
			}
			if (method.isAnnotationPresent(Path.class)) {
				Path p = (Path) method.getAnnotation(Path.class);
				path = p.value();
			}
			path = serviceRootPath+path;
			String desc = String.format("检查到服务--服务路径：%s,服务方法：%s,版本：%s,服务类型：%s,%s,%s,%s,%s", path,name, version, httpType, stateless,
			        authMethod,authorizer,permitMethod, monitored);
			Log.info(desc);
		}
	}
	public static ServiceConfig getServiceInfo(String logic){
		return serviceInfo.get(logic);
	}
}
