package com.longrise.common.db.druid;

import java.io.File;
import java.io.IOException;

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

import com.alibaba.druid.pool.DruidDataSource;
import com.longrise.common.utils.Global;


public class DruidInit implements Servlet {
	final static Logger Log = LoggerFactory.getLogger(DruidInit.class);

	public void destroy() {

	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public String getServletInfo() {
		return null;
	}

	public void init(ServletConfig arg0) throws ServletException {
		String dbxml = arg0.getInitParameter("DBConfig");
		Element element = null;
		// 可以使用绝对路径
		Log.info(Global.getWebRootRealPath() + dbxml);
		File f = new File(Global.getWebRootRealPath() + dbxml);

		// documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = null;
		try {
			// 返回documentBuilderFactory对象
			dbf = DocumentBuilderFactory.newInstance();
			// 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
			db = dbf.newDocumentBuilder();
			// 得到一个DOM并返回给document对象
			Document dt = db.parse(f);
			element = dt.getDocumentElement();
			NodeList childNodes = element.getChildNodes();
			// 遍历这些子节点
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node node1 = childNodes.item(i);
				if ("datasource".equals(node1.getNodeName())) {
					Element datasource = (Element) node1;
					String dsId = datasource.getAttribute("id");
					Log.info("检测到数据源配置--数据源名称：" + dsId);
					NodeList childNodes2 = datasource.getChildNodes();
					DruidConfigBean dcb = new DruidConfigBean();
					for (int j = 0; j < childNodes2.getLength(); j++) {
						Node node2 = childNodes2.item(j);
						if ("property".equals(node2.getNodeName())) {
							Element property = (Element) node2;
							String name = property.getAttribute("name");
							String value = property.getAttribute("value");
							Log.info("检测到数据源配置--数据源名称：" + dsId+"--"+name+":"+value);
							dcb.setProperty(name, value);
						}
					}
					//设置配置
					DruidDataSource dds = new Druid().setDataSourcePool(dcb);
					if(dds!=null)
					{
						DruidDataSources.getInstance().setDruidDataSource(dsId, dds);
					}
				}

			}
		} catch (Exception e) {
			Log.error("数据源配置解析错误，请检查原因", e);
		}

	}

	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {

	}

}
