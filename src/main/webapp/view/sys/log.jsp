<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
    <title>My JSP 'ajaxuserlist.jsp' starting page</title>
    <link rel="stylesheet" href="static/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/animate.min.css">
</head>
  
<body>
	<table class="table table-bordered">
		<tr>
			<th>ID</th>
			<th>操作者</th>
			<th>操作表</th>
			<th>操作时间</th>
			<th>详情</th>
			<th>IP地址</th>
		</tr>
		<c:if test="${!empty pager.dataList}">
		<c:forEach items="${pager.dataList}" var="item">
			<tr>
				<td>${item["operationId"]}</td>
				<td>${item["operationMan"]}</td>
				<td>${item["operationTable"]}</td>
				<td>${item["operationDate"]}</td>
				<td><a href="javascript:void(0);" class="detail">查看详情</a></td>
				<td></td>
				<td style="display:none"><input type="hidden" value="${item['operationId']}" id="operationId"></td>
			</tr>
		</c:forEach>
		</c:if>
	</table>
    <ul id="pagination-demo" class="pagination-sm"></ul>
    <div>
    	<a href="<%=request.getContextPath()%>/index">返回首页</a>
    </div>
    
    <script src="static/jquery/jquery-1.11.3.min.js"></script>
	<script src="static/jQueryPaginationPlugin/jquery.twbsPagination.js"></script>
	<script src="static/js/jquery.hDialog.min.js"></script>
	<script>
	$('#pagination-demo').twbsPagination({
        totalPages: ${pager.totalPage},
        visiblePages: 7,
        onPageClick: function (event, page) {
            $('#page-content').text('Page ' + page);
        },
        href: '?page={{number}}',
        first: '首页',
        prev: '上一页',
        next: '下一页',
        last: '尾页'
    });
    
    $('.detail').each(function(){
    	$(this).click(function(){
    		var operationId = $(this).parent().parent().find('#operationId').val();
    		console.log(operationId);
    		$.ajax({
    			type: 'post',
				url: '<%=request.getContextPath()%>/getLogOperationContent',
				data: {
					"operationId":operationId
				},
				dataType: 'json'
    		}).done(function(data){
    			console.log(data);
    			$.dialog('confirm','提示',data.operationContent);
    		}).error(function(data){
    		
    		});
    	});
    });
	</script>
</body>
</html>
