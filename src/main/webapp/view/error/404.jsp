<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<DIV style="text-align:center">
		<img src="<%=request.getContextPath()%>/static/images/notfound.gif">
		<P style="FONT-SIZE: 24px; LINE-HEIGHT: 70px">Opps! Page Not Found.</P>
		<a href="<%=request.getContextPath()%>">返回首页</a>
	</DIV>
</body>
</html>