<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이슈 작성 페이지</title>
</head>
<body>
    <form>
        <div>
            <label for="requirement-title">요구사항명</label>
            <input type="text" id="requirement-title" name="rqmTtl" value="${issue.rqmTtl}"/>

            <label for="issue-title">이슈명</label>
            <input type="text" id="issue-title" name="isTtl" value="${issue.isTtl}"/>
        </div>

    </form>
    <div>
        <div>
            <button id="btn-create" type="button">생성</button>
        </div>
    </div>
</body>
</html>