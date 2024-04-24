<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 작성</title>
<jsp:include page="../commonheader.jsp"></jsp:include>
<script type="text/javascript" src="/js/survey/surveywrite.js"></script>
<style type="text/css">
    div {
        border: 1px solid #000;
        margin: 0;
        padding: 10px;
        
    }
    /* .survey-question {
        display: flex;
        flex-direction: column;
    }
    .survey-question-top > div {
        display: inline-block;
    }
    .survey-question-bottom > ul > li > div {
        display: inline-block;
    } */
</style>
</head>
<body>
    <h1>설문 작성</h1>
    <div class="survey-body" data-prj-id="${surveyQuestionVO.prjId}">
        <div class="survey-question"></div>
        <button id="next-srv-btn">다음</button>
    </div>
</body>
</html>