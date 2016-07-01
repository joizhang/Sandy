<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />    
    <title>My JSP 'index.jsp' starting page</title>

</head>
  
<body>
    welcome <shiro:principal/> <a href="<%=request.getContextPath()%>/logout">退出</a>
    <ul>
    	<li><a href="<%=request.getContextPath()%>/adduser">录入新用户</a></li>
    	<li><a href="<%=request.getContextPath()%>/userlist">查看所有用户</a></li>
    	<li><a href="<%=request.getContextPath()%>/fileupload">上传文件</a></li>
    	<li><a href="<%=request.getContextPath()%>/gallery">画廊</a></li>
    	<li><a href="<%=request.getContextPath()%>/log">日志（使用jQuery Pagination分页）</a></li>
    </ul>
</body>
</html>
