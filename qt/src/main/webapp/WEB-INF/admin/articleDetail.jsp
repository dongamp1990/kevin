<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--  <%@ page isELIgnored="false"%>  --%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>网站后台管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<jsp:include page="head_import.jsp"/>
</head>
<body class="page-boxed page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-sidebar-closed-hide-logo">
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<div class="page-header-inner container">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<a href="index">
			<img src="http://7xp9ph.com1.z0.glb.clouddn.com/img/logo-default.png" alt="logo" class="logo-default"/>
			</a>
			<div class="menu-toggler sidebar-toggler">
				<!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
			</div>
		</div>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN PAGE TOP -->
		<div class="page-top">
			<!-- BEGIN HEADER SEARCH BOX -->
			<!-- DOC: Apply "search-form-expanded" right after the "search-form" class to have half expanded search box -->
<!-- 			<form class="search-form search-form-expanded" action="extra_search.html" method="GET"> -->
<!-- 				<div class="input-group"> -->
<!-- 					<input type="text" class="form-control" placeholder="Search..." name="query"> -->
<!-- 					<span class="input-group-btn"> -->
<!-- 					<a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a> -->
<!-- 					</span> -->
<!-- 				</div> -->
<!-- 			</form> -->
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
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="container">
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar-wrapper">
			<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
			<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
			<!-- 左边菜单开始 -->
			<jsp:include page="left_menu.jsp"/>	
			<!-- 菜单结束 -->
		</div>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
<!-- 				<h3 class="page-title">文章列表</h3> -->
<!-- 				<div class="page-bar"> -->
<!-- 					<ul class="page-breadcrumb"> -->
<!-- 						<li> -->
<!-- 							<i class="fa fa-home"></i> -->
<!-- 							<a href="index">首页</a> -->
<!-- 							<i class="fa fa-angle-right"></i> -->
<!-- 						</li> -->
<!-- 						<li> -->
<!-- 							<a href="#">文章列表</a> -->
<!-- 						</li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
				<!-- END PAGE HEADER-->
				<!-- BEGIN DASHBOARD STATS -->
				<div class="row">
					<div class="col-md-12 ">
						<div class="portlet box blue-hoki">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-gift"></i>文章编辑
								</div>
							</div>
							<div class="portlet-body form">
								<form class="form-horizontal form-bordered">
									<div class="form-body">
										<div class="form-group">
											<label class="control-label col-md-1"> </label>
											<div class="col-md-11">
												<div name="summernote" id="summernote_1">
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
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
	<div class="page-footer">
		<div class="page-footer-inner">
			 2015 &copy; <a href="http://kevin9180.com">www.kevin9180.com</a>
		</div>
		<div class="scroll-to-top">
			<i class="icon-arrow-up"></i>
		</div>
	</div>
	<!-- END FOOTER -->
</div>
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="../../assets/global/plugins/respond.min.js"></script>
<script src="../../assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<jsp:include page="foot_import.jsp"/>
<script>
jQuery(document).ready(function() {    
   Metronic.init(); // init metronic core componets
   Layout.init(); // init layout
   ComponentsEditors.init();
   $('#articles_menu').addClass('active');
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>