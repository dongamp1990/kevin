package org.alipp.api.base;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class Pager extends SimpleTagSupport {
	
	//当前页吗
	private int currentPage;
	//每页显示数量
	private int pageSize;
	//总记录条数
	private int totalRecord;
	//请求url
	private String requestUrl;
	//默认样式
	private String style = "digg";
	//总页数
	private int totalPage = 0;
	
	@Override
	public void doTag() throws JspException, IOException {
		
		StringBuilder res = new StringBuilder(); //页码
//		StringBuilder str = new StringBuilder(); //拼装内容
		
		if(this.totalRecord > 0){ //当总记录数大于0 
			//计算出总页数
			totalPage = this.totalRecord % this.pageSize == 0 
					? this.totalRecord / this.pageSize 
					: (this.totalRecord / this.pageSize)+1; 
			
			res.append("<div class='row'>");
			res.append("<div class='col-md-5 col-sm-5'>");
			res.append("<div class='dataTables_info' id='sample_1_info' role='status' aria-live='polite'>");
			res.append("共").append(totalRecord).append("条记录,").append(totalPage).append("页");
			res.append("</div>");
			res.append("</div>");
			res.append("<div class='col-md-7 col-sm-7'>");
			res.append("<div class='dataTables_paginate paging_bootstrap_full_number' id='sample_1_paginate'>");
			res.append("<ul class='pagination' style='visibility: visible;'>");
			
			if (this.currentPage != 1) {
				res.append("<li class='prev'><a href='javascript:void(0)' onclick='pageChange(1)' title='首页'><i class='fa fa-angle-double-left'></i></a></li>");
				res.append("<li class='prev'><a href='javascript:void(0)' onclick='pageChange(").append(currentPage-1).append(")'")
				.append("title='上一页'><i class='fa fa-angle-left'></i></a></li>");
			}else {
				res.append("<li class='prev disabled'><a href='#' title='首页'><i class='fa fa-angle-double-left'></i></a></li>");
				res.append("<li class='prev disabled'><a href='#' title='上一页'><i class='fa fa-angle-left'></i></a></li>");
			}
			
			int displayStart = 0;
			int displayTo = 0;

			int size = 7;
			if (totalPage <= 7) {
				displayStart = 1;
				displayTo = totalPage;
			} else {
				if (currentPage <= 4) {
					displayStart = 1;
					displayTo = size;
				} else if (currentPage + 3 > totalPage) {
					displayStart = totalPage - size + 1;
					displayTo = totalPage;
				} else {
					displayStart = currentPage - 3;
					displayTo = currentPage + 3;
				}
			}
			
			for (int i = displayStart; i <= displayTo; i++) {
				if (i == currentPage) {
					res.append("<li class='active'><a href='#'>").append(i).append("</a></li>");
				}else {
					res.append("<li><a href='javascript:void(0)' onclick='pageChange(").append(i).append(")'>").append(i).append("</a></li>");
				}
			}
			
			if (currentPage < totalPage) {
				res.append("<li class='next'><a href='javascript:void(0)' onclick='pageChange(").append(currentPage+1).append(")' ")
					.append("title='下一页'><i class='fa fa-angle-right'></i></a></li>");
			}
			if (currentPage != totalPage && totalPage != 0) {
				res.append("<li class='next'><a href='javascript:void(0)' onclick='pageChange(").append(totalPage).append(")' ")
				   .append("title='最后一页'><i class='fa fa-angle-double-right'></i></a></li>");
			}
			if (currentPage == totalPage) {
				res.append("<li class='next disabled'><a href='#'")
				.append("title='Next'><i class='fa fa-angle-right'></i></a></li>");
				res.append("<li class='next disabled'><a href='#'")
				   .append("title='Last'><i class='fa fa-angle-double-right'></i></a></li>");
			}
			res.append("</ul>");
			res.append("</div>");
			res.append("</div>");
			res.append("</div>");
			
			//拼接分页js代码
			res.append("<script type='text/javascript'>")
			   .append(" function pageChange(currentPage){var url = '")
			   .append(requestUrl).append("'; ")
			   .append(" url = url.replace('currentPage=page', 'currentPage=' + currentPage); ")
			   .append(" window.location.href = url;}")
			   .append("</script>");
			
		}else{
			res.append("<div class='row'>");
			res.append("<div class='col-md-5 col-sm-5'>");
			res.append("<div class='dataTables_info' id='sample_1_info' role='status' aria-live='polite'>");
			res.append("共0条记录,").append("0").append("页");
			res.append("</div>");
			res.append("</div>");
		}
		
		getJspContext().getOut().print(res.toString());
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
