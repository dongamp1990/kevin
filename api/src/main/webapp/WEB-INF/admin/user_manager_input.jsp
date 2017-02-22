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
<body class="page-boxed page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-sidebar-closed-hide-logo">
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
							<li><i class="fa fa-home"></i> <a href="user_manager">用户管理</a>
								<i class="fa fa-angle-right"></i></li>
							<li><a href="javascript:void(0)">添加用户</a></i></li>
						</ul>
						<div class="page-toolbar"></div>
					</div>
					<!-- END PAGE HEADER-->
					<div class="row">
						<div class="col-md-12">
							<div
								class="tabbable tabbable-custom tabbable-noborder tabbable-reversed">
								<div class="tab-content">
									<div class="portlet box blue-hoki">
										<div class="portlet-title">
											<div class="caption">
												<i class="fa fa-gift"></i>添加用户
											</div>
										</div>
										<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<form action="user_manager_input_submit" class="form-horizontal" method="post" id="userForm">
												<div class="form-body">
													<div class="form-group">
														<label class="col-md-3 control-label">登录名</label>
														<div class="col-md-4">
															<input type="text" class="form-control" required
																placeholder="登录名" name="loginName" id="loginName">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">用户名</label>
														<div class="col-md-4">
															<input type="text" class="form-control"
																placeholder="用户名" name="userName">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">密码</label>
														<div class="col-md-4">
															<input type="text" class="form-control" placeholder="密码"
																name="password">
														</div>
													</div>
												</div>
												<div class="form-actions fluid">
													<div class="row">
														<div class="col-md-offset-3 col-md-9">
															<button type="submit" class="btn green">提交</button>
															<button type="button" class="btn default" onclick="cancel();">返回</button>
														</div>
													</div>
												</div>
											</form>
											<!-- END FORM-->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END FOOTER -->
		</div>
		<jsp:include page="footer.jsp"/>
	</div>
	
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
			
			 $("#userForm").validate({
			 	submitHandler:function(form){
			        form.submit();
			 	},
			 	rules: {
			 		loginName: {
			 			"required" : true,
					 	 remote: {
					 	    url: "${pageContext.request.contextPath}/admin/check_login_name",     //后台处理程序
					 	    type: "post",               //数据发送方式
					 	    //dataType: "json",           //接受数据格式   
					 	    data: {                     //要传递的数据
					 	    	loginName: function() {
					 	            return $("#loginName").val();
					 	        }
					 	    }
					 	 }
			 		},
			 		userName: "required",
			 		password: {
			 	        required: true,
			 	        minlength: 6
			 	      }
			 	},
		 	    messages: {
		 	    	loginName: {
						"required":"请输入登录名",
						"remote":"登录名已存在"
		 	    	},
		 	    	userName: "请输入用户名",
			 	    password: {
			 	        required: "请输入密码",
			 	        minlength: "密码长度不能小于 6 个字母"
			 	      }
			 	}
		    });
		});

		function pageLimitChange() {
			var limit = $("#limit").val();
			var url = "${pageContext.request.contextPath}/admin/user_manager?&currentPage=1&pageSize="
					+ limit + "&userName=" + $("#userName").val();
			window.location.href = url;
		}
		
		function cancel() {
			window.history.go(-1);
		}
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>