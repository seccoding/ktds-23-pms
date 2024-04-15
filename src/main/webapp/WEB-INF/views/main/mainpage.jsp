<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>메인페이지입니다.</h1>
<div>${sessionScope._LOGIN_USER_.empName}님 환영합니다</div>
<div>${sessionScope._LOGIN_USER_.empId}님 환영합니다</div>
<div><a href="/employee/logout">로그아웃</a></div>

</body>
</html>
