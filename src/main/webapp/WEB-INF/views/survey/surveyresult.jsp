<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 결과</title>
<jsp:include page="../commonheader.jsp"></jsp:include>
<style type="text/css">
    body {
        font-family: Arial, sans-serif; /* 일관된 폰트 스타일 */
        background-color: #f4f4f4; /* 배경색 */
        margin: 0;
        padding: 20px;
    }

    .question {
        background-color: #fff; /* 문항 배경색 */
        padding: 15px; /* 문항 내부 여백 */
        border-radius: 8px; /* 둥근 꼭지점 */
        box-shadow: 0 4px 6px rgba(0,0,0,0.15); /* 그림자 효과 */
        margin-bottom: 30px; /* 문항 간 여백 */
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

    div:not(.completed-survey-count) {
        background-color: #fff;
        padding: 10px;
        border-radius: 8px;
        text-align: center;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    .completed-survey-count {
        padding: 10px 20px; /* 상하 10px, 좌우 20px 패딩 */
        margin: 10px 0; /* 위아래로 10px 여백 */
        border-radius: 5px; /* 둥근 모서리 */
        font-weight: bold; /* 글자 굵게 */
        font-size: 16px; /* 글자 크기 */
        box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* 약간의 그림자 효과 */
    }
</style>
</head>
<body>
    <div class="completed-survey-count">작성자 수 : ${srvYn} / ${teammateCount} 명</div>
    <c:choose>
        <c:when test="${not empty questionList.questionList}">
            <c:forEach items="${questionList.questionList}" var="question">
                <div class="question">
                    <p>${question.srvQst}</p>
                    <ul>
                        <c:choose>
                            <c:when test="${question.typeYn == 'N'}">
                                <c:forEach items="${responseCounts[question.srvId]}" var="entry">
                                    <li>${entry.key} : ${entry.value} 명</li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${replyList}" var="reply">
                                    <c:if test="${reply.srvId == question.srvId}">
                                        <li>${reply.srvRplCntnt}</li>
                                    </c:if>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </ul>
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