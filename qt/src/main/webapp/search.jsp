<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>qt1387会员查询</title>
<!-- Bootstrap core CSS -->
<link href="http://7xp9ph.com1.z0.glb.clouddn.com/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="http://7xp9ph.com1.z0.glb.clouddn.com/css/fount-awesome/css/font-awesome.min.css" type="text/css" />
<link href="http://7xp9ph.com1.z0.glb.clouddn.com/css/themes/blue.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="http://7xp9ph.com1.z0.glb.clouddn.com/css/custom.css" rel="stylesheet" type="text/css"/>
<link href="css/site.min.css" type="text/css" />
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12"> 
			</div>
		</div>
		<div class="row" style="min-height: 200px">
		</div>
		<div class="row" style="min-height: 450px">
			<div class="col-xs-12">
				<div>
					<c:if test="${not empty info }">
						<div class="table-scrollable">
							<table class="table table-bordered table-hover text-center h1">
								<tbody>
									<tr class="active">
										<td colspan="3">
											查询结果
										</td>
									</tr>
									<tr class="success">
										<td>
											 级别
										</td>
										<td colspan="2">
												<div class="form-group">
													<input type="hidden" class="form-control" placeholder="级别" name="level" id="level" value="${info.level }"/> 
													<div class="col-md-8">
														<div class="col-md-6">
															<div id="function-demo1" class="target-demo" style="width: 240px"></div> 
												 			<div id="function-hint1" class="hint" style="display: none;"></div>
														</div>
														<p>${info.code_name }</p>
													</div>
												 	
												</div>
											 	
										</td>
									</tr>
									<tr class="info">
										<td>
											 QT名称
										</td>
										<td colspan="2">
											 ${info.qt_name }
										</td>
									</tr>
									<tr class="warning">
										<td>
											 地区
										</td>
										<td colspan="2">
											 ${info.area }
										</td >
									</tr>
									<tr class="danger">
										<td>
											 QQ号码
										</td>
										<td colspan="2">
											 ${info.qq_number }
										</td>
									</tr>
									<tr class="active">
										<td>
											 手机号码
										</td>
										<td colspan="2">
											 ${info.phone_number }
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div style="height: 80px">
							<p class=" text-center" style="color: white;">
								<a href="${pageContext.request.contextPath}/index" class="h1">返回查询页</a>
							</p>
						</div>
					</c:if>
					<c:if test="${empty info }">
						<p class="h1 text-center" style="color: white;">
							没有您查询的会员，请重新填写&nbsp;<a href="${pageContext.request.contextPath}/index">返回查询页</a>
						</p>
					</c:if>
				</div>
			</div>
		</div>
		<div class="copyright text-center">2016 QT&copy;
		</div>
	</div>
	<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/jquery.1.1.3.min.js" type="text/javascript"></script>
	<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/bootstrap.3.3.5.min.js" type="text/javascript"></script>
	<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/jquery-raty/jquery.raty.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
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
				readOnly  : true,
				targetKeep: true,
				precision : false,//是否包含小数
				click: function(score, evt) {
// 				  alert('ID: ' + $(this).attr('id') + "\nscore: " + score + "\nevent: " + evt.type);
				  if(evt.type == 'click') {
					  $("#level").val(score);
				  }
				}
			});
			$("#function-demo1").css("width", "200px");
		});
	</script>
</body>
</html>
