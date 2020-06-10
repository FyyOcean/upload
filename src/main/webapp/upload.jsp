<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
   <H1>用来测试文件上传</H1>
<form action="${pageContext.request.contextPath}/file/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="aaa"> <br>
    <input type="submit" value="上传文件">
</form>
<h2>测试文件下载的处理</h2>
   <a href="${pageContext.request.contextPath}/file/down?fileName=index.html">index.html</a>
   <a href="${pageContext.request.contextPath}/file/down?fileName=推理数学.txt">推理数学.txt</a>
   <a href="${pageContext.request.contextPath}/file/down?fileName=模板网址.txt">模板网址.txt</a>
</body>
</html>
