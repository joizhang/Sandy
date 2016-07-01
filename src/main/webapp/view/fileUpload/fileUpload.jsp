<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
    <title>My JSP 'fileUpload.jsp' starting page</title>
    
    <link rel="stylesheet" href="static/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/bootstrap-fileinput-master/css/fileinput.css">
    <link rel="stylesheet" href="static/bootstrap3-dialog-master/dist/css/bootstrap-dialog.css">
</head>
  
<body>
	<div class="container">
		<div class="page-header">
            <h1>Bootstrap 文件上传</h1>
        </div>
        
        <%-- 
        <div class="panel panel-default">
			<div class="panel-heading">单文件上传</div>
			<div class="panel-body">
				<form enctype="multipart/form-data">
					<div class="form-group">
                    	<input id="file-single" type="file" class="file" data-upload-url="#">
               	 	</div>
	            </form>
            </div>
		 </div>
        --%>
        <div class="panel panel-default">
			<div class="panel-heading">多文件上传</div>
			<div class="panel-body">
				<form enctype="multipart/form-data">
					<div class="form-group">
	            		<input id="file-mutiple" name="myFiles" type="file" multiple class="file" data-overwrite-initial="false" data-min-file-count="2">
	            	</div>
	            </form>
            </div>
		 </div>
		 
		 
	</div>
	
    <script src="static/jquery/jquery-1.11.3.min.js"></script>
    <script src="static/bootstrap/dist/js/bootstrap.min.js"></script>
   	<script src="static/bootstrap-fileinput-master/js/fileinput.js"></script>
   	<script src="static/bootstrap-fileinput-master/js/fileinput_locale_zh.js"></script>
   	<script src="static/bootstrap3-dialog-master/dist/js/bootstrap-dialog.js"></script>
    <script>
    $("#file-mutiple").fileinput({
    	language: 'zh',													//设置语言为中文
    	//showCaption: false,											//是否展示输入框，默认为true
    	//showPreview:false,											//是否展示预览，默认为true
    	//showRemove:false,													
    	//showUpload: false,											//不显示上传按钮
    	//allowedFileTypes: ['image', 'html', 'text', 'video', 'audio', 'flash', 'object'],		//允许的文件类型
    	allowedFileExtensions : ['jpg', 'png', 'gif'],					//允许的文件扩展名
    	/* previewSettings: {											//预览文件宽高设置
    		image: {width: "auto", height: "160px"},
    		html: {width: "213px", height: "160px"},
		    text: {width: "160px", height: "136px"},
		    video: {width: "213px", height: "160px"},
		    audio: {width: "213px", height: "80px"},
		    flash: {width: "213px", height: "160px"},
		    object: {width: "213px", height: "160px"},
		    other: {width: "160px", height: "160px"}
    	} */
    	uploadAsync: true,												//异步上传
        uploadUrl: '<%=request.getContextPath()%>/upload',				//请求
        overwriteInitial: false,
        maxFileSize: 2000,												//文件大小单位为KB
        maxFileCount: 10,												//文件数量，如果设置为0，则上传文件数量无限制
        slugCallback: function(filename) {								//回调函数来去除特殊字符
        	//console.log(filename);
            return filename.replace('(', '_').replace(']', '_');
        }
	}).on('filebatchuploadcomplete', function(event, files, extra) {
    	console.log('File batch upload complete');
	});
    </script>
</body>
</html>
