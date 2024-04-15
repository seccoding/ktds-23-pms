<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Ckeditor List Exam</title>
</head>
<body>
<h1>ckeditor article list</h1>
<c:forEach items="${list}" var="data">
    <a href='/ckeditor/${data.id}'>${data.title}</a><br>
</c:forEach>
</body>
</html>
