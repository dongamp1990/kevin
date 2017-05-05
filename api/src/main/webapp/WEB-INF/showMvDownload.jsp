<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${title}下载</title>
</head>
<body>
	<h3>720P下载地址</h3>
	<c:if test="${not empty _720p}">
		<textarea rows="30" cols="100" >${_720p}</textarea>
	</c:if>
	<br>
	<h3>高清TV下载地址</h3>
	<textarea rows="30" cols="100">${tv}</textarea>
</body>
</html>