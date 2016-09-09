package com.longrise.common.db.druid;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * druid多数据源
 * 
 * @author Administrator
 *
 */
public class DruidDataSources {
	final static Logger Log = LoggerFactory.getLogger(DruidInit.class);
	private Map<String, DruidDataSource> dbs;
	private static DruidDataSources ddss;

	private DruidDataSources() {

	}

	public static DruidDataSources getInstance() {
		if (ddss == null) {
			ddss = new DruidDataSources();
		}
		return ddss;
	}

	public DruidDataSource getDruidDataSource(String id) {
		return dbs.get(id);
	}

	public void setDruidDataSource(String id, DruidDataSource dds) {
		if (dbs == null) {
			dbs = new HashMap<String, DruidDataSource>();
		}
		dbs.put(id, dds);
		Log.info("成功添加数据源："+id);
	}
}
