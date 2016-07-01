<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
    <title>My JSP 'input.jsp' starting page</title>
    
	<link rel="stylesheet" href="static/css/sweetalert.css" />
</head>
  
<body id="main">
    <div style="padding: 5px;overflow: hidden;">
	<form id="form1" onsubmit="return addUser();">
		<table class="tableForm">
			<tr>
				<th style="width: 80px;">Username</th>
				<td>
					<input name="username" type="text" required="required" id="username" />
				</td>
			</tr>
			<tr>
				<th>Password</th>
				<td>
					<input name="password" type="text" required="required" id="password" />
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="增加"></td>
			</tr>
		</table>
	</form>
	</div>
	
	<div>
		<a href="<%=request.getContextPath()%>/userlist">查看所有用户</a>
		<a href="<%=request.getContextPath()%>/index">返回首页</a>
	</div>
	
	<script src="static/jquery/jquery.js"></script>
	<script src="static/js/sweetalert.min.js"></script>
	<script>
	function addUser(){
		var user = [
			{name:'username',value:'123'},
			{name:'password',value:'123'},
		];
		console.log(user);
		var $form = $("#form1");
		console.log($form.serializeArray());
		swal({
			title: "您确定要保存吗？", 
			text: "您确定要录入这条数据？", 
			type: "warning",
			showCancelButton: true,
			closeOnConfirm: false,
			confirmButtonText: "是的，我要保存",
			confirmButtonColor: "#ec6c62"
		},
		function(){
			$.ajax({
				url: "<%=request.getContextPath()%>/add",
				type: "post",
				data: $form.serializeArray(),
	            dataType:"json",
			}).done(function(data){
				swal("录入成功!", "用户已保存！", "success");
			}).error(function(data) {
				swal("OMG", "保存失败了,用户名已注册!", "error");
			});
		});
		return false;
	}
	
	function addUser1() {
		var form = document.forms[0];
		form.action = "${pageContext.request.contextPath}/add";
		form.method = "post";
		form.submit();
    	}
	</script>
</body>
</html>
