<%@ page language="java"  contentType="text/html; charset=UTF-8" %>

<html>
<body>
<h2>店铺测试</h2>



springmvc上传文件
<form name="form1" action="/file/uploadFile.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="springmvc上传文件" />
</form>


富文本图片上传文件
<form name="form4" action="/file/uploadFile.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="富文本图片上传文件" />
</form>

CDN上传文件  CDN
<form name="form3" action="/file/uploadFileCDN.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="springmvc上传文件" />
</form>


</body>
</html>
