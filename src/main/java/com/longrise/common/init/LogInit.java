package com.longrise.common.init;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.longrise.common.utils.Global;

public class LogInit implements Servlet {
	//final static Logger Log = LoggerFactory.getLogger(LogInit.class);
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public String getServletInfo() {
		return null;
	}

	public void init(ServletConfig arg0) throws ServletException {
		/** 系统日志初始化配置 **/
		Global.setWebRootRealPath(arg0.getServletContext().getRealPath("/"));
		//System.out.println(Global.getWebRootRealPath() + arg0.getInitParameter("LogConfig"));
		//System.setProperty("log4j.configuration", Global.getWebRootRealPath() + arg0.getInitParameter("LogConfig"));
		//Log.info("系统日志初始化配置成功");
		PropertyConfigurator.configure(Global.getWebRootRealPath() + arg0.getInitParameter("LogConfig"));
		//log4jConfig();
		/** 系统日志初始化配置 **/
	}

	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
	
	}

//	private void log4jOutLogFileConfig() {
//		Logger rootLogger = Logger.getRootLoger();
//		DailyRollingFileAppender drfa = (DailyRollingFileAppender) rootLogger.getAppender("file");
//		drfa.setFile(Global.getWebRootRealPath() + "WEB-INF" + drfa.getFile());
//		rootLogger.removeAppender("file");
//		rootLogger.addAppender(drfa);
//	}

}
