package com.longrise.common.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultBeanSet {
	private int pageNum = 0;
	private int pageSize = -1;
	private int pageCount = 0;
	private int size = 0;
	private int allSize = 0;

	public int getAllSize() {
		return allSize;
	}

	public void setAllSize(int allSize) {
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

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
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

	public int getPageCount() {
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

	public int getSize() {
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
