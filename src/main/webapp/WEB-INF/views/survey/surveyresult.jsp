<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 결과</title>
<jsp:include page="../commonheader.jsp"></jsp:include>
</head>
<body>
    <c:choose>
        <c:when test="${not empty replyList.replyList}">
            <c:forEach items="${replyList.questionList}" var="question">
                <p>${question.srvQst}</p>
                    <ul>
                        <c:forEach items="${replyList.replyList}" var="reply">
                            <li>${reply.srvRplCntnt}</li>
                        </c:forEach>
                    </ul>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div>
                <a href="/survey/list">
                    작성한 설문지가 없습니다.
                </a>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>