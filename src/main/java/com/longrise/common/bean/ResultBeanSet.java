package com.longrise.common.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultBeanSet {
	private long pageNum = 0L;
	private long pageSize = -1L;
	private long pageCount = 0;
	private long size = 0L;
	private long allSize = 0L;

	public long getAllSize() {
		return allSize;
	}

	public void setAllSize(long allSize) {
		this.allSize = allSize;
		if (pageCount == 0 && allSize > 0 && pageSize > 0) {
			if (allSize <= pageSize) {
				pageCount = 1;
			} else {
				if (allSize / pageSize > 0) {
					pageCount = allSize / pageSize + 1;
				} else {
					pageCount = allSize / pageSize;
				}
			}
		}
	}

	private List<Map<String, Object>> resultSet = null;

	public long getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		if (pageCount == 0 && allSize > 0 && pageSize > 0) {
			if (allSize <= pageSize) {
				pageCount = 1;
			} else {
				if (allSize / pageSize > 0) {
					pageCount = allSize / pageSize + 1;
				} else {
					pageCount = allSize / pageSize;
				}
			}
		}
	}

	public long getPageCount() {
		if (pageCount == 0 && allSize > 0 && pageSize > 0) {
			if (allSize <= pageSize) {
				pageCount = 1;
			} else {
				if (allSize / pageSize > 0) {
					pageCount = allSize / pageSize + 1;
				} else {
					pageCount = allSize / pageSize;
				}
			}
		}
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public long getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public ResultBeanSet() {
		resultSet = new ArrayList<Map<String, Object>>();
	};

	public ResultBeanSet(List<Map<String, Object>> resultSet) {
		this.resultSet = resultSet;
	};

	public List<Map<String, Object>> getResultBean() {
		return resultSet;
	}

	public void setResultBeanSet(List<Map<String, Object>> resultSet) {
		this.resultSet = resultSet;
	}

	public ResultBean getResultBean(int index) {
		return new ResultBean(resultSet.get(index));
	}
}
