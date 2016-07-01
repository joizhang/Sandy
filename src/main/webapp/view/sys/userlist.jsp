<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
    <title>user list</title>
    <link rel="stylesheet" href="static/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/sweetalert.css" />
</head>
  
<body id="main">
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<a href="javascript:;" class="btn btn-primary btn-sm" id="exportPDF">导出PDF</a>
			</div>
		</div>
		<table class="table table-bordered">
			<tr>
				<th>ID</th>
				<th>用户名</th>
				<th>密码</th>
				<th>日期</th>
				<th>编辑</th>
			</tr>
			<c:if test="${!empty userlist}">
			<c:forEach items="${userlist}" var="item">
				<tr>
					<td>${item["userid"]}</td>
					<td>${item["username"]}</td>
					<td>${item["password"]}</td>
					<td>${item["createdtime"]}</td>
					<td><a href="javascript:;" class="edit">修改</a>&nbsp;|&nbsp;<a href="javascript:;" class="del">删除</a></td>
					<td style="display:none"><input type="hidden" value="${item['userid']}" id="userid"></td>
				</tr>
			</c:forEach>
			</c:if>
		</table>
		<div>
			<a href="<%=request.getContextPath()%>/adduser">录入新用户</a>
			<a href="<%=request.getContextPath()%>/index">返回首页</a>
		</div>
	</div>
	<div id="download" style="display:none"></div>
	<script src="static/jquery/jquery-1.11.3.min.js"></script>
	<script src="static/js/sweetalert.min.js"></script>
	<script>
	$(".del").each(function(){
	    $(this).click(function(){
	        var userid = $(this).parent().parent().find("#userid").val();
	        $tr = $(this).parent().parent();
	        console.log(userid);
	        swal({
				title: "您确定要删除吗？", 
				text: "您确定要删除这条数据？", 
				type: "warning",
				showCancelButton: true,
				closeOnConfirm: false,
				confirmButtonText: "是的，我要删除",
				confirmButtonColor: "#ec6c62"
			}, function() {
				$.ajax({
					url: "<%=request.getContextPath()%>/deleteuser",
					type: "post",
					data: {
						"userid":userid
					},
             		dataType: "json"
				}).done(function(data) {
					console.log(data);
					$tr.fadeOut("slow");
					swal("操作成功!", "已成功删除数据！", "success");
				}).error(function(data) {
					swal("OMG", "删除操作失败了!", "error");
				});
			});
	    });
	});
	
	$('.edit').each(function(){
		$(this).click(function(){
			var userid = $(this).parent().parent().find("#userid").val();
			$.ajax({
				type: 'post',
				url: '<%=request.getContextPath()%>/getUserDetail',
				data: {
					"userid":userid
				},
				dataType: 'json'
			}).done(function(data){
				console.log(data); 
				swal({  
					title: data.userid,   
					text: '<input type="text" value="'+data.username+'">',   
  					html: true,	
					showCancelButton: true,   
					closeOnConfirm: false,
				}, function(inputValue){   
					if (inputValue === false) return false;      
					if (inputValue === "") {     
						swal.showInputError("请输入!");     
						return false   
					}      
					swal("棒极了!", "您填写的是: " + inputValue, "success"); 
				});
			})
		})
	});
	
	$('#exportPDF').click(function(){
		 $('#download').append($('<iframe/>').attr('src','<%=request.getContextPath()%>/exportPDF'));
	});
	</script>
</body>
</html>
