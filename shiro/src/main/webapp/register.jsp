<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isThreadSafe="false" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>index</title>
</head>
<body>
    <h1>用户注册</h1>

<form action="${pageContext.request.contextPath}/user/register" method="post">
    用户名：<input type="text" name="username"> <br/>
    密码：<input type="text" name="password"> <br/>
    <input type="submit" value="注册">
</form>
</body>
</html>