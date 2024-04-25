<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 상세 정보</title>
<jsp:include page="../commonheader.jsp"></jsp:include>
<style type="text/css">
    div {
        border: 1px solid #000;
    }
    div.grid {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr 1fr;
        grid-template-rows: 100px 100px;
        margin-top: 1rem;
    }
</style>
</head>
<body>
    <c:choose>
        <c:when test="${not empty questionList.questionList}">
            <c:forEach items="${questionList.questionList}" var="question">
                <p>${question.srvQst}</p>
                <c:if test="${question.typeYn eq 'N'}">
                    <ul>
                        <c:forEach items="${pickList.pickList}" var="pick">
                            <li>${pick.sqpCntnt}</li>
                        </c:forEach>
                    </ul>
                </c:if>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div>
                <a href="/survey/list">
                    등록된 설문지가 없습니다.
                </a>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>