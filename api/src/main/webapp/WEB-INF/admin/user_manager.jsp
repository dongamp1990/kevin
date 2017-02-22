<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="page" uri="/pager-tags"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>QT网站后台管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<jsp:include page="head_import.jsp" />
</head>
<body
	class="page-boxed page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-sidebar-closed-hide-logo">
	<!-- BEGIN HEADER -->
	<div class="page-header navbar navbar-fixed-top">
		<!-- BEGIN HEADER INNER -->
		<div class="page-header-inner container">
			<!-- BEGIN LOGO -->
			<div class="page-logo">
				<a href="index"> <img
					src="http://7xp9ph.com1.z0.glb.clouddn.com/img/logo-default.png"
					alt="logo" class="logo-default" />
				</a>
				<div class="menu-toggler sidebar-toggler">
					<!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
				</div>
			</div>
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<a href="javascript:;" class="menu-toggler responsive-toggler"
				data-toggle="collapse" data-target=".navbar-collapse"> </a>
			<!-- END RESPONSIVE MENU TOGGLER -->
			<!-- BEGIN PAGE TOP -->
			<div class="page-top">
				<!-- BEGIN HEADER SEARCH BOX -->
				<!-- DOC: Apply "search-form-expanded" right after the "search-form" class to have half expanded search box -->
				<form class="search-form search-form-expanded"
					action="extra_search.html" method="GET">
					<!-- 				<div class="input-group"> -->
					<!-- 					<input type="text" class="form-control" placeholder="Search..." name="query"> -->
					<!-- 					<span class="input-group-btn"> -->
					<!-- 					<a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a> -->
					<!-- 					</span> -->
					<!-- 				</div> -->
				</form>
				<!-- END HEADER SEARCH BOX -->
				<!-- BEGIN TOP NAVIGATION MENU -->
				<jsp:include page="top_menu.jsp"></jsp:include>
				<!-- END TOP NAVIGATION MENU -->
			</div>
			<!-- END PAGE TOP -->
		</div>
		<!-- END HEADER INNER -->
	</div>
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="container">
		<div class="page-container">
			<!-- BEGIN SIDEBAR -->
			<div class="page-sidebar-wrapper">
				<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
				<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
				<!-- 左边菜单开始 -->
				<jsp:include page="left_menu.jsp" />
				<!-- 菜单结束 -->
			</div>
			<!-- END SIDEBAR -->
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="page-content">
					<!-- END PAGE HEADER-->
					<!-- BEGIN PAGE HEADER-->
					<h3 class="page-title">用户管理</h3>
					<div class="page-bar">
						<ul class="page-breadcrumb">
							<li><i class="fa fa-home"></i> <a href="javascript:void(0)">用户管理</a></li>
						</ul>
						<div class="page-toolbar"></div>
					</div>
					<!-- END PAGE HEADER-->
					<div class="row">
						<div class="col-md-12">
							<!-- BEGIN EXAMPLE TABLE PORTLET-->
							<div class="portlet box blue-hoki">
								<div class="portlet-title">
									<div class="caption">
										<i class="fa fa-globe"></i>数据列表
									</div>
								</div>
								<div class="portlet-body">
									<div class="table-toolbar">
										<div class="row">
											<div class="col-md-6">
												<div class="btn-group">
													<button id="sample_editable_1_new" class="btn green" onclick="dataInput();">
														增加用户 <i class="fa fa-plus"></i>
													</button>
												</div>
											</div>
										</div>
									</div>
									<div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
										<div class="row">
											<div class="col-md-6 col-sm-6">
												<div class="dataTables_length" id="sample_1_length">
													<label>显示
														<select name="sample_1_length" aria-controls="sample_1" 
															class="form-control input-xsmall input-inline" id="limit" onchange="pageLimitChange()">
															<option value="1" <c:if test="${pageSize==1 }">selected='selected'</c:if>>1</option>
															<option value="10" <c:if test="${pageSize==10 }">selected='selected'</c:if>>10</option>
															<option value="20" <c:if test="${pageSize==20 }">selected='selected'</c:if>>20</option>
															<option value="30" <c:if test="${pageSize==30 }">selected='selected'</c:if>>30</option>
															<option value="50" <c:if test="${pageSize==50 }">selected='selected'</c:if>>50</option>
														</select>条记录
													</label>
												</div>
											</div>
											<div class="col-md-6 col-sm-6">
												<div id="sample_1_filter" class="dataTables_filter">
													<label>搜索：<input type="search"
														class="form-control input input-inline"
														placeholder="用户名 按回车搜索" aria-controls="sample_1" id="userName" value="${userName }"/></label>
												</div>
											</div>
										</div>
										<div class="table-scrollable">
											<table
												class="table table-striped table-bordered table-hover dataTable no-footer"
												id="sample_1" role="grid" aria-describedby="sample_1_info">
												<thead>
													<tr>
														<th class="numeric" width="5%">
															 ID
														</th>
														<th>
															登录名
														</th>
														<th class="numeric">
															 用户名
														</th>
														<th class="numeric">
															 创建时间
														</th>
														<th class="numeric" width="10%">
															操作
														</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty userPage}">
														<c:forEach var="user" items="${userPage.list }">
															<tr>
																<td>
																	 ${user.user_id }
																</td>
																<td>
																	 ${user.login_name }
																</td>
																<td class="numeric">
																	${user.user_name }
																</td>
																<td class="numeric">
																	<f:formatDate value="${user.create_date }" pattern="yyyy-MM-dd HH:mm:ss"/>
																</td>
																<td class="numeric">
																	<a onclick="deleteData(${user.user_id});"><span aria-hidden="true" class="icon-trash" title="删除"></span></a>
																	<a onclick="updateData(${user.user_id});"><span aria-hidden="true" class="icon-wrench" title="修改"></span></a>
																</td>
															</tr>
														</c:forEach>
													</c:if>
												</tbody>
											</table>
										</div>
<!-- 										<div class="row"> -->
<!-- 											<div class="col-md-5 col-sm-5"> -->
<!-- 												<div class="dataTables_info" id="sample_1_info" -->
<!-- 													role="status" aria-live="polite">Showing 1 to 5 of 25 -->
<!-- 													records</div> -->
<!-- 											</div> -->
<!-- 											<div class="col-md-7 col-sm-7"> -->
<!-- 												<div -->
<!-- 													class="dataTables_paginate paging_bootstrap_full_number" -->
<!-- 													id="sample_1_paginate"> -->
<!-- 													<ul class="pagination" style="visibility: visible;"> -->
<!-- 														<li class="prev disabled"><a href="#" title="First"><i -->
<!-- 																class="fa fa-angle-double-left"></i></a></li> -->
<!-- 														<li class="prev disabled"><a href="#" title="Prev"><i -->
<!-- 																class="fa fa-angle-left"></i></a></li> -->
<!-- 														<li class="active"><a href="#">1</a></li> -->
<!-- 														<li><a href="#">2</a></li> -->
<!-- 														<li><a href="#">3</a></li> -->
<!-- 														<li><a href="#">4</a></li> -->
<!-- 														<li><a href="#">5</a></li> -->
<!-- 														<li class="next"><a href="#" title="Next"><i -->
<!-- 																class="fa fa-angle-right"></i></a></li> -->
<!-- 														<li class="next"><a href="#" title="Last"><i -->
<!-- 																class="fa fa-angle-double-right"></i></a></li> -->
<!-- 													</ul> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
										<page:pager pageSize="${userPage.pageSize }" requestUrl="${pageContext.request.contextPath}/admin/user_manager?currentPage=page&pageSize=${userPage.pageSize }" 
	                            		totalRecord="${userPage.totalRow }" currentPage="${userPage.pageNumber}"/>		
									</div>
								</div>
							</div>
							<!-- END EXAMPLE TABLE PORTLET-->
						</div>
					</div>
				</div>
			</div>
			<!-- END CONTENT -->
			<!-- BEGIN QUICK SIDEBAR -->
			<!--Cooming Soon...-->
			<!-- END QUICK SIDEBAR -->
		</div>
		<!-- END CONTAINER -->
		<!-- BEGIN FOOTER -->
		<jsp:include page="footer.jsp"/>
		<!-- END FOOTER -->
	</div>
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
<script src="../../assets/global/plugins/respond.min.js"></script>
<script src="../../assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
	<jsp:include page="foot_import.jsp" />
	<script>
		jQuery(document).ready(function() {
			Metronic.init(); // init metronic core componets
			Layout.init(); // init layout
			$('#user_manager_menu').addClass('active');
			
			document.onkeydown=function(event){
	          var e = event || window.event || arguments.callee.caller.arguments[0];
	              if(e && e.keyCode==13){ // enter 键
	                 if($("#userName").val() != null && $("#qtName").val() != "") {
	         			var url = "${pageContext.request.contextPath}/admin/user_manager?&currentPage=1&pageSize="+${pageSize}+"&userName=" + $("#userName").val();
	         			window.location.href = url;
	                 }
	              }
	        };
		});
		
		function pageLimitChange() {
			var limit = $("#limit").val();
			var url = "${pageContext.request.contextPath}/admin/user_manager?&currentPage=1&pageSize="+limit+"&userName=" + $("#userName").val();
			window.location.href = url;
		}
		
		function dataInput() {
			var url = "${pageContext.request.contextPath}/admin/user_manager_input";
			window.location.href = url;
		}
		function updateData(id) {
			var url = "${pageContext.request.contextPath}/admin/update_user_manager_input?id="+id;
			window.location.href = url;
		}
		function deleteData(id) {
			if(confirm("操作不可恢复，您确定操作吗?")) {
				var url = "${pageContext.request.contextPath}/admin/delete_user?id="+id;
				$.get(url, function(data) {
					if(data.status == "ok") {
						var url = "${pageContext.request.contextPath}/admin/user_manager?currentPage=${currentPage}&pageSize=${pageSize}&qtName=" + $("#userName").val();
						window.location.href = url;
					}
				});
			}
		}
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>