<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>QT网站后台登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
<link href="http://7xp9ph.com1.z0.glb.clouddn.com/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="http://7xp9ph.com1.z0.glb.clouddn.com/css/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="http://7xp9ph.com1.z0.glb.clouddn.com/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="http://7xp9ph.com1.z0.glb.clouddn.com/css/uniform.default.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="http://7xp9ph.com1.z0.glb.clouddn.com/css/login.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link href="http://7xp9ph.com1.z0.glb.clouddn.com/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="http://7xp9ph.com1.z0.glb.clouddn.com/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="http://7xp9ph.com1.z0.glb.clouddn.com/css/layout.css" rel="stylesheet" type="text/css"/>>
<link href="http://7xp9ph.com1.z0.glb.clouddn.com/css/themes/grey.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="http://7xp9ph.com1.z0.glb.clouddn.com/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
<div class="menu-toggler sidebar-toggler">
</div>
<!-- END SIDEBAR TOGGLER BUTTON -->
<!-- BEGIN LOGO -->
<div class="logo">
	<img src="http://7xp9ph.com1.z0.glb.clouddn.com/img%2Flogo-big.png" alt=""/>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
	<!-- BEGIN LOGIN FORM -->
	<form class="login-form" action="loginSubmit" method="post">
		<h3 class="form-title">账号登录</h3>
		<c:if test="${not empty errMsg}">
			<div class="alert alert-danger ">
				<button class="close" data-close="alert"></button>
				<span>${errMsg} </span>
			</div>
		</c:if>
		<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">用户名</label>
			<input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="Username" name="username"/>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">密码</label>
			<input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="Password" name="password"/>
		</div>
		<div class="form-actions">
			<button type="submit" class="btn btn-success uppercase pull-right">登录</button>
<!-- 			<label class="rememberme check"> -->
<!-- 			<input type="checkbox" name="remember" value="1"/>记住密码</label> -->
<!-- 			<a href="javascript:;" id="forget-password" class="forget-password">Forgot Password?</a> -->
		</div>
	</form>
</div>
<div class="copyright">
	 2016 QT©
</div>
<!-- END LOGIN -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="../../assets/global/plugins/respond.min.js"></script>
<script src="../../assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/jquery.1.1.3.min.js" type="text/javascript"></script>
<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/jquery-migrate.min.js" type="text/javascript"></script>
<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/bootstrap.3.3.5.min.js" type="text/javascript"></script>
<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/jquery.blockui.min.js" type="text/javascript"></script>
<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/jquery.uniform.min.js" type="text/javascript"></script>
<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/jquery.cokie.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/jquery.validate.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/metronic.js" type="text/javascript"></script>
<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/layout.js" type="text/javascript"></script>
<!-- <script src="../../assets/admin/layout/scripts/demo.js" type="text/javascript"></script> -->
<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/login.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {     
Metronic.init(); // init metronic core components
Layout.init(); // init current layout
Login.init();
// Demo.init();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>