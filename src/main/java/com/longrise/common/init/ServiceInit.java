package com.longrise.common.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.longrise.common.server.ServiceConfig;
import com.longrise.common.server.ServiceInfo;
import com.longrise.common.utils.Global;


public class ServiceInit implements Servlet {
	protected static List<ServiceConfig> services = new ArrayList<ServiceConfig>();
	final Logger logger = LoggerFactory.getLogger(ServiceInit.class);
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void init(ServletConfig arg0) throws ServletException {
		getServiceConfig(arg0);
	}

	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
	}



	private void getServiceConfig(ServletConfig arg0) {
		String servicexml = arg0.getInitParameter("ServiceClass");
		Element element = null;
		// 可以使用绝对路径
		File f = new File(Global.getWebRootRealPath() + servicexml);
		// documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = null;
		try {
			// 返回documentBuilderFactory对象
			dbf = DocumentBuilderFactory.newInstance();
			// 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
			db = dbf.newDocumentBuilder();
			// 得到一个DOM并返回给document对象
			InputStream is = new FileInputStream(f); 
			Document dt = db.parse(is);
			element = dt.getDocumentElement();
			NodeList childNodes = element.getChildNodes();
			// 遍历这些子节点
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node node1 = childNodes.item(i);
				if ("class".equals(node1.getNodeName())) {
					ServiceInfo.setServiceInfo(Class.forName(node1.getTextContent()));
				}

			}
		} catch (Exception e) {
			logger.error("服务配置解析错误，请检查原因", e);
		}
	}
}
