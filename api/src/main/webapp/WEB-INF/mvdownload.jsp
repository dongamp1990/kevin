<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>解析飘V下载地址</title>
<style type="text/css">
	.form {
		position:absolute;
		right:700px;
		padding-top:60px;
	}
	.input {
		height: 25px
	}
	button {
		height: 31.5px;
		width:80px;
	}
	.label {
		width:80px;
	}
	.h3 {
		
	}
	.span{
		position:absolute;
		left:675px;
		padding-top:170px;
		color: red;
	}
</style>
</head>
<body>
	<form method="POST" class="form" id="form" >
		<label clas="label">输入URL：</label><input type="text" size="50" name="url" class="input" id="url"></input>
		<button onclick="check();">解析</button>
		<span id="msg" style="color: red; padding-top: 15px"></span>
	</form>
</body>
<script src="http://7xp9ph.com1.z0.glb.clouddn.com/js/jquery.1.1.3.min.js" type="text/javascript"></script>
<script type="text/javascript">

	$(function(){
		$("#form").on("submit",function(event){
			var url = $("#url").val();
            if(url == null || url == ""){
	   			 alert("请输入URL");
// 	   			 $("#msg").text("请输入URL");
// 	   			 $("#msg").attr("style","display:block;");
				 $("#url").focus();
	   			 event.preventDefault();
	   		}else {
	   			$("#form").attr("action","${pageContext.request.contextPath}/mv/parseDownloadUrl");
	   			return true;
	   		}
        })
        
	})
</script>
</html>