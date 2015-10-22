package com.gusi.demo.utils;

public class PageInfo {
	private int pageNo = 1;// 当前页编号
	private int dbIndex = 0;// 起始行，通常该属性通过pageNo和pageSize计算得到
	private int pageSize = 5;// 每页显示条数
	private int totalReacordNumber;// 总共的记录条数
	private int totalPageNumber;// 总共的页数，通过总共的记录条数以及每页大小计算而得

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int currentPageNo) {

		int temp = (currentPageNo - 1) < 0 ? 0 : (currentPageNo - 1);
		this.dbIndex = temp * pageSize;
		this.pageNo = currentPageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}

	public int getTotalReacordNumber() {
		return totalReacordNumber;
	}

	public void setTotalReacordNumber(int totalReacordNumber) {
		if (totalReacordNumber % pageSize == 0) {
			this.totalPageNumber = totalReacordNumber / pageSize;
		} else {
			this.totalPageNumber = totalReacordNumber / pageSize + 1;
		}
		this.totalReacordNumber = totalReacordNumber;
	}

	public int getTotalPageNumber() {
		return totalPageNumber;
	}

	public void setTotalPageNumber(int totalPageNumber) {
		this.totalPageNumber = totalPageNumber;
	}

	@Override
	public String toString() {
		return "PageInfo [pageNo=" + pageNo + ", dbIndex=" + dbIndex + ", pageSize=" + pageSize + ", totalReacordNumber="
				+ totalReacordNumber + ", totalPageNumber=" + totalPageNumber + "]";
	}

}
