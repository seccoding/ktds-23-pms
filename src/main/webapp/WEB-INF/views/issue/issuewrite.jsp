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
    <c:if test="${not empty errorMessage}">
        <dialog class="alert-dialog">
          <h1>${errorMessage}</h1>
        </dialog>
    </c:if>
    <form action="/issue/write" method="post" enctype="multipart/form-data">
        <div class="grid">
            <label for="requirement-title">요구사항명</label>
            <input type="text" id="requirement-title" name="rqmTtl" value="${issue.rqmTtl}"/>

            <label for="issue-title">이슈명</label>
            <input type="text" id="issue-title" name="isTtl" value="${issue.isTtl}"/>

            <label for="content">내용</label>
            <textarea type="text" id="content" name="isTtl" value="${issue.isTtl}">${issueVO.isCntnt}</textarea>
        </div>

    </form>
    <div>
        <div>
            <button id="btn-create" type="button">생성</button>
        </div>
    </div>
</body>
</html>