package top.coderak.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class Page {

	private Integer pageSize;
	private Integer rows;
	private Integer pages;
	private Integer startRow;
	private Integer currentPage;
	private Integer categoryId;
	private List list;

	@Override
	public String toString() {
		return "Page{" +
				"pageSize=" + pageSize +
				", rows=" + rows +
				", pages=" + pages +
				", startRow=" + startRow +
				", currentPage=" + currentPage +
				", categoryId=" + categoryId +
				", list=" + list +
				'}';
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}


	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
