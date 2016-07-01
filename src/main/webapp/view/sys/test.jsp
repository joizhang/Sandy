<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	
	<title>My JSP 'test.jsp' starting page</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
		-->
</head>

<body>
	This is my JSP page.
	<br>
	<script src="static/jquery/jquery.js"></script>
	<script>
	$(document).ready(function(){  
        var saveDataAry=[];  
        var users={"username":"test","password":"gz"};
        var data2={"username":"ququ","password":"gr"};
        console.log(users);
        console.log(JSON.stringify(users));
        saveDataAry.push(users);  
        saveDataAry.push(data2);  
        console.log(saveDataAry);
        console.log(JSON.stringify(saveDataAry));       
        $.ajax({ 
            type:"POST", 
            url:"<%=request.getContextPath()%>/user/saveUser", 
            dataType:"json",      
            contentType:"application/json",               
            data: JSON.stringify(users), 
         }).done(function(data, status){
         	console.log('data : ' + data + ' status : ' + status);
         }).error(function(data, status){
         	console.log('data : ' + data + ' status : ' + status);
         }); 
    }); 
	</script>
</body>
</html>
