package com.longrise.common.db.druid;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DruidConfigBean {
	final Logger Log = LoggerFactory.getLogger(DruidConfigBean.class);
	String id;
	String url;
	String username;
	String password;
	int initialSize;
	int minIdle;
	int maxActive;
	long maxWait;
	long timeBetweenEvictionRunsMillis;
	long minEvictableIdleTimeMillis;
	String validationQuery;
	boolean testWhileIdle;
	boolean testOnBorrow;
	boolean testOnReturn;
	boolean poolPreparedStatements;
	int maxPoolPreparedStatementPerConnectionSize;
	String filters;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}
	public void setInitialSize(String initialSize) {
		this.initialSize = Integer.valueOf(initialSize);
	}
	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	public void setMinIdle(String minIdle) {
		this.minIdle = Integer.valueOf(minIdle);
	}
	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	public void setMaxActive(String maxActive) {
		this.maxActive = Integer.valueOf(maxActive);
	}
	public long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}
	public void setMaxWait(String maxWait) {
		this.maxWait = Long.valueOf(maxWait);
	}
	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}
	public void setTimeBetweenEvictionRunsMillis(String timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = Long.valueOf(timeBetweenEvictionRunsMillis);
	}
	public long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}
	public void setMinEvictableIdleTimeMillis(String minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = Long.valueOf(minEvictableIdleTimeMillis);
	}
	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}
	public void setTestWhileIdle(String testWhileIdle) {
		this.testWhileIdle = Boolean.valueOf(testWhileIdle);
	}
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	public void setTestOnBorrow(String testOnBorrow) {
		this.testOnBorrow = Boolean.valueOf(testOnBorrow);
	}
	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}
	public void setTestOnReturn(String testOnReturn) {
		this.testOnReturn = Boolean.valueOf(testOnReturn);
	}
	public boolean getPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	public void setPoolPreparedStatements(boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}
	public void setPoolPreparedStatements(String poolPreparedStatements) {
		this.poolPreparedStatements = Boolean.valueOf(poolPreparedStatements);
	}
	public int getMaxPoolPreparedStatementPerConnectionSize() {
		return maxPoolPreparedStatementPerConnectionSize;
	}

	public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
		this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
	}
	public void setMaxPoolPreparedStatementPerConnectionSize(String maxPoolPreparedStatementPerConnectionSize) {
		this.maxPoolPreparedStatementPerConnectionSize = Integer.valueOf(maxPoolPreparedStatementPerConnectionSize);
	}
	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public void setProperty(String name, String value) {
		
		try {
			Field field = this.getClass().getDeclaredField(name);
			boolean access = field.isAccessible();
			if (!access)
				field.setAccessible(true);
			if("int".equals(field.getType().toString()))
			{
				field.set(this, Integer.valueOf(value));
			}
			else if("boolean".equals(field.getType().toString()))
			{
				field.set(this, Boolean.valueOf(value));
			}
			else if("long".equals(field.getType().toString()))
			{
				field.set(this, Long.valueOf(value));
			}
			else
			{
				field.set(this, value);
			}
			if (access)
				field.setAccessible(false);
		} catch (Exception e) {
			Log.error("druid参数有误，请检查配置",e);
		}
	}
}
