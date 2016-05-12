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
					<h3 class="page-title">资料管理</h3>
					<div class="page-bar">
						<ul class="page-breadcrumb">
							<li><i class="fa fa-home"></i> <a href="data_manager">资料管理</a>
								<i class="fa fa-angle-right"></i></li>
							<li><a href="javascript:void(0)">修改数据</a></i></li>
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
												<i class="fa fa-gift"></i>修改数据
											</div>
										</div>
										<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<form action="update_data_manager_input_submit" class="form-horizontal" method="post" id="userForm">
												<div class="form-body">
													<div class="form-group">
														<label class="col-md-3 control-label">QT名称</label>
														<div class="col-md-4">
															<input type="text" class="form-control"
																placeholder="QT名称" name="qtName" value="${customerInfo.qt_name }"/>
															<input type="hidden" name="id" value="${customerInfo.id }"/>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">QQ号码</label>
														<div class="col-md-4">
															<input type="text" class="form-control"
																placeholder="QQ号码" name="qqNumber" id="qqNumber" value="${customerInfo.qq_number }"/>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">手机号码</label>
														<div class="col-md-4">
															<input type="text" class="form-control"
																placeholder="手机号码" name="phoneNumber" value="${customerInfo.phone_number }"/>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">地区</label>
														<div class="col-md-4">
															<input type="text" class="form-control" placeholder="地区"
																name="area" value="${customerInfo.area }"/>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">级别</label>
														<div class="col-md-8">
															<div class="col-md-3">
																<input type="hidden" class="form-control" placeholder="级别" name="level" id="level" value="${customerInfo.level }"/>
																<div id="function-demo1" class="target-demo"></div>	
																<div id="function-hint1" class="hint" style="display: none;"></div>
															</div>
															<div class="col-md-3">
																<select id="code" class="form-control input input-inline" name="code">
																	<c:if test="${not empty codes }">
																		<c:forEach items="${codes }" var="code">
																			<option value="${code.code_id }" <c:if test="${code.code_id == customerInfo.level_code }">selected="selected"</c:if>  >${code.code_name }</option>
																		</c:forEach>
																	</c:if>
																</select>
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">违规记录</label>
														<div class="col-md-4">
															<textarea class="form-control" placeholder="违规记录"
																name="illegalityRecord" rows="5">${customerInfo.illegality_record }</textarea>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">违规处罚</label>
														<div class="col-md-4">
															<textarea class="form-control" placeholder="违规记录"
																name="illegalityPunish" rows="5">${customerInfo.illegality_punish }</textarea>
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
	<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/jquery-raty/jquery.raty.min.js" type="text/javascript"></script>
	<script>
		jQuery(document).ready(function() {
			Metronic.init(); // init metronic core componets
			Layout.init(); // init layout
			$('#data_manager_menu').addClass('active');
			$("#userForm").validate({
			 	submitHandler:function(form){
			        form.submit();
			 	},
			 	rules: {
			 		qtName: "required",
			 		qqNumber: {
			 			"required" : true,
					 	 remote: {
					 	    url: "${pageContext.request.contextPath}/admin/check_qq_number",     //后台处理程序
					 	    type: "post",               //数据发送方式
					 	    //dataType: "json",           //接受数据格式   
					 	    data: {                     //要传递的数据
					 	    	qqNumber: function() {
					 	            return $("#qqNumber").val();
					 	        },
					 	        oldQQNumber : "${customerInfo.qq_number}"
					 	    }
					 	 }
			 		}
			 	},
		 		messages: {
		 			qqNumber: {
						"required":"请输入QQ号码",
						"remote":"QQ号码已存在"
		 	    	},
		 	    	qtName: "请输入QT名称"
			 	}
		    });
			
			var path = "http://7xp9ph.com1.z0.glb.clouddn.com/js/jquery-raty";
			$('#function-demo1').raty({
			 	number: 5,//多少个星星设置
				score: function() {
					return $("#level").val();
				},//初始值是设置
				targetType: 'number',//类型选择，number是数字值，hint，是设置的数组值
				path      : path+"/img",
				cancelOff : 'cancel-off-big.png',
				cancelOn  : 'cancel-on-big.png',
				size      : 24,
				starHalf  : 'star-half-big.png',
				starOff   : 'star-off-big.png',
				starOn    : 'star-on-big.png',
				target    : '#function-hint1',
				cancel    : false,
				targetKeep: true,
				precision : false,//是否包含小数
				click: function(score, evt) {
// 				  alert('ID: ' + $(this).attr('id') + "\nscore: " + score + "\nevent: " + evt.type);
				  if(evt.type == 'click') {
					  $("#level").val(score);
				  }
				}
			});
		});

		function pageLimitChange() {
			var limit = $("#limit").val();
			var url = "${pageContext.request.contextPath}/admin/data_manager?&currentPage=1&pageSize="
					+ limit + "&qtName=" + $("#qtName").val();
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