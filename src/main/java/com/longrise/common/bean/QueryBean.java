package com.longrise.common.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryBean {
	final static Logger Log = LoggerFactory.getLogger(QueryBean.class);
	private Map<String, Object> queryParam = null;
	private Map<String, Object> queryField = null;

	public QueryBean() {
		queryParam = new HashMap<String, Object>();
		queryField = new HashMap<String, Object>();
	}

	public void addField(String name) {
		queryField.put(name, null);
	}

	public void removeField(String name) {
		queryField.remove(name);
	}

	public void addParam(String name, Object value) {
		queryParam.put(name, value);
	}

	public void removeParam(String name) {
		queryParam.remove(name);
	}

	public ResultBean selectFristRow(String sql, DataSource ds, Object[] objects) {
		QueryRunner qr = new QueryRunner(ds);
		Map<String, Object> result = null;
		ResultBean rb = null;
		try {
			result = qr.query(sql, new MapHandler(), objects);
			rb = new ResultBean(result);
		} catch (SQLException e) {
			Log.error("",e);
		}
		return rb;
	}

	/**
	 * 该方法查询之后返回的ResultBeanSet将给出allSize,size，需要自行传入setPageSize后自动计算pageCount,
	 * 分页查询语句请自行实现
	 * 
	 * @param sql
	 * @param ds
	 * @param objects
	 * @return
	 */
	public ResultBeanSet select(String sql, DataSource ds, Object... objects) {
		QueryRunner qr1 = new QueryRunner(ds);
		List<Map<String, Object>> result = null;
		ResultBeanSet rbSet = null;
		try {
			String cSql = "";
			String a = sql.toLowerCase();
			int l = a.lastIndexOf(" limit ");
			if (l > 0) {
				cSql = sql.substring(0, l);
			}
			else
			{
			    cSql = sql;
			}
			cSql = cSql.substring(0, 6) + " count(*) " + cSql.substring(a.indexOf("from"), cSql.length());
			ResultBean countBean = selectFristRow(cSql, ds, objects);
			if (countBean.getLong("count") > 0) {
				result = qr1.query(sql, new MapListHandler(), objects);
				rbSet = new ResultBeanSet(result);
				rbSet.setAllSize(countBean.getLong("count"));
				rbSet.setSize(result.size());
			}
		} catch (SQLException e) {
			Log.error("",e);
		}
		return rbSet;
	}

	/**
	 * 该方法查询之后返回的ResultBeanSet将给出allSize,size，传入setPageSize后自动计算pageCount,
	 * 分页查询语句请自行实现
	 * 
	 * @param sql
	 * @param ds
	 * @param objects
	 * @return
	 */
	public ResultBeanSet select(String sql, DataSource ds, Object[] objects, int pageSize) {
		QueryRunner qr1 = new QueryRunner(ds);
		List<Map<String, Object>> result = null;
		ResultBeanSet rbSet = null;
		try {
			String cSql = "";
			String a = sql.toLowerCase();
			int l = a.lastIndexOf(" limit ");
			if (l > 0) {
				cSql = sql.substring(0, l);
			}
			cSql = cSql.substring(0, 6) + " count(*) " + cSql.substring(a.indexOf("from"), cSql.length());
			ResultBean countBean = selectFristRow(cSql, ds, objects);
			if (countBean.getInteger("count") > 0) {
				result = qr1.query(sql, new MapListHandler(), objects);
				rbSet = new ResultBeanSet(result);
				rbSet.setAllSize(countBean.getInteger("count"));
				rbSet.setSize(result.size());
			}
		} catch (SQLException e) {
			Log.error("",e);
		}
		return rbSet;
	}

	/**
	 * 该方法用于PostgreSQL数据库的分页查询
	 * 
	 * @param sql
	 * @param ds
	 * @param objects
	 * @return
	 */
	public ResultBeanSet pgPagingSelect(String sql, DataSource ds, Object[] objects, int pageSize, int pageNum) {
		QueryRunner qr1 = new QueryRunner(ds);
		List<Map<String, Object>> result = null;
		ResultBeanSet rbSet = null;
		try {
			String cSql = sql;
			String a = sql.toLowerCase();
			cSql = cSql.substring(0, 6) + " count(*) " + cSql.substring(a.indexOf("from"), cSql.length());
			ResultBean countBean = selectFristRow(cSql, ds, objects);
			if (countBean.getInteger("count") > 0) {
				int limit = pageSize;
				int offset = (pageSize - 1) * pageNum;
				sql += " limit " + limit + " offset " + offset;
				result = qr1.query(sql, new MapListHandler(), objects);
				rbSet = new ResultBeanSet(result);
				rbSet.setAllSize(countBean.getInteger("count"));
				rbSet.setSize(result.size());
				rbSet.setPageNum(pageNum);
				rbSet.setPageSize(pageSize);
			}
		} catch (SQLException e) {
			Log.error("",e);
		}
		return rbSet;
	}

	public ResultBean insert(String sql, DataSource ds, Object[] params) {
		QueryRunner qr = new QueryRunner(ds);
		Map<String, Object> result = null;
		ResultBean rb = null;
		try {
			result = qr.insert(sql, new MapHandler(), params);
			rb = new ResultBean(result);
		} catch (SQLException e) {
			Log.error("",e);
		}
		return rb;
	}

	public int update(String sql, DataSource ds, Object[] params) {
		QueryRunner qr = new QueryRunner(ds);
		int result = -1;
		try {
			result = qr.update(sql, new MapHandler(), params);
		} catch (SQLException e) {
			Log.error("",e);
		}
		return result;
	}

	public int delete(String sql, DataSource ds, Object[] params) {
		QueryRunner qr = new QueryRunner(ds);
		int result = -1;
		try {
			result = qr.update(sql, new MapHandler(), params);
		} catch (SQLException e) {
			Log.error("",e);
		}
		return result;
	}

	/**
	 * 批处理增删改操作，事务处理
	 * 
	 * @param sql
	 * @param ds
	 * @param params
	 * @return
	 */
	public ResultBeanSet batch(String sql, DataSource ds, Object[][] params) {
		QueryRunner qr = new QueryRunner(ds);
		List<Map<String, Object>> result = null;
		ResultBeanSet rbSet = null;
		Connection conn = null;
		try {
			conn = qr.getDataSource().getConnection();
			qr.batch(conn, sql, params);
			DbUtils.commitAndClose(conn);
			rbSet = new ResultBeanSet(result);
		} catch (SQLException e) {
			Log.error("",e);
			try {
				DbUtils.rollbackAndClose(conn);
			} catch (SQLException e1) {
				Log.error("",e1);
			}
		}
		return rbSet;
	}
}
