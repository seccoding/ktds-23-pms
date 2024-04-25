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
            <c:forEach items="${replyList.replyList}" var="reply">
                <p>${reply.srvQst}</p>
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