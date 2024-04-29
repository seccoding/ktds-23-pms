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
    body {
        font-family: Arial, sans-serif; /* 일관된 폰트 스타일 */
        background-color: #f4f4f4; /* 배경색 */
        margin: 0;
        padding: 20px;
    }

    p {
        font-size: 16px;
        color: #333;
        padding: 10px;
        background-color: #f9f9f9;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        margin-bottom: 20px; /* 기본 여백 설정 */
    }

    p.no-options {
        margin-bottom: 0; /* 선택지가 없는 경우 여백 제거 */
    }

    ul {
        list-style-type: none; /* 목록 기호 제거 */
        padding: 0;
    }

    li {
        background-color: #E9ECEF; /* 선택지 배경색 */
        padding: 8px 12px;
        margin-bottom: 5px; /* 선택지 간 여백 */
        border-radius: 5px; /* 둥근 꼭지점 */
        font-weight: bold; /* 글자 굵게 */
    }

    a {
        color: #007BFF; /* 링크 색상 */
        text-decoration: none; /* 밑줄 제거 */
    }

    a:hover {
        text-decoration: underline; /* 마우스 오버 시 밑줄 */
    }
    div {
        background-color: #fff;
        padding: 10px;
        border-radius: 8px;
        text-align: center;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
    .question {
        background-color: #fff; /* 문항 배경색 */
        padding: 15px; /* 문항 내부 여백 */
        border-radius: 8px; /* 둥근 꼭지점 */
        box-shadow: 0 4px 6px rgba(0,0,0,0.15); /* 그림자 효과 */
        margin-bottom: 30px; /* 문항 간 여백 */
    }
</style>
</head>
<body>
    <c:choose>
        <c:when test="${not empty questionList.questionList}">
            <c:forEach items="${questionList.questionList}" var="question">
                <div class="question">
                    <c:choose>
                        <c:when test="${question.typeYn eq 'Y'}">
                            <p class="no-options">${question.srvQst}</p>
                        </c:when>
                        <c:otherwise>
                            <p>${question.srvQst}</p>
                            <ul>
                                <c:forEach items="${pickList}" var="pick">
                                    <c:if test="${pick.srvId == question.srvId}">
                                        <li>${pick.sqpCntnt}</li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
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