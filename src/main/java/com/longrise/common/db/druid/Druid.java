package com.longrise.common.db.druid;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;

public class Druid {
	final Logger Log = LoggerFactory.getLogger(Druid.class);

	@SuppressWarnings("deprecation")
	public DruidDataSource setDataSourcePool(DruidConfigBean dcb) {
		DruidDataSource druiddataSource = new DruidDataSource();
		druiddataSource.setUsername(dcb.getUsername());
		druiddataSource.setPassword(dcb.getPassword());
		druiddataSource.setUrl(dcb.getUrl());
		druiddataSource.setInitialSize(dcb.getInitialSize());
		druiddataSource.setMinIdle(dcb.getMinIdle());
		druiddataSource.setMaxActive(dcb.getMaxActive());
		druiddataSource.setMaxWait(dcb.getMaxWait());
		// druiddataSource.setMaxIdle(dcb.getMinIdle());
		druiddataSource.setTimeBetweenEvictionRunsMillis(dcb.getTimeBetweenEvictionRunsMillis());
		druiddataSource.setMinEvictableIdleTimeMillis(dcb.getMinEvictableIdleTimeMillis());
		druiddataSource.setValidationQuery(dcb.getValidationQuery());
		druiddataSource.setTestWhileIdle(dcb.isTestWhileIdle());
		druiddataSource.setTestOnBorrow(dcb.isTestOnBorrow());
		druiddataSource.setTestOnReturn(dcb.isTestOnReturn());
		// 启用监控统计功能
		try {
			druiddataSource.setFilters(dcb.getFilters());
		} catch (Exception e) {
			Log.error("Druid Filters配置异常", e);
		}
		druiddataSource.setPoolPreparedStatements(dcb.getPoolPreparedStatements());
		druiddataSource
				.setMaxPoolPreparedStatementPerConnectionSize(dcb.getMaxPoolPreparedStatementPerConnectionSize());
		if (!druiddataSource.isInited()) {
			try {
				druiddataSource.init();
			} catch (Exception e) {
				druiddataSource.close();
				druiddataSource = null;
				Log.error("DruidDataSource初始化失败", e);
			}
		}
		return druiddataSource;
	}
}
