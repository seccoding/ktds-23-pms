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
    body {
        font-family: Arial, sans-serif; /* 일관된 폰트 스타일 */
        background-color: #f4f4f4; /* 배경색 */
        margin: 0;
        padding: 20px;
    }

    .survey-question {
        font-size: 16px;
        color: #333;
        padding: 10px;
        background-color: #fff;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        margin-bottom: 20px; /* 문항 간 기본 여백 설정 */
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
        font-weight: bold;
    }

    button {
        cursor: pointer; /* 마우스 오버 시 커서 변경 */
    }

    button:hover {
        background-color: #d45642; /* 마우스 오버 시 버튼 색상 변경 */
    }

    textarea {
        width: 100%;
        min-Height: 100px;
        margin-bottom: 20px; /* 텍스트 영역 아래 여백 */
    }

    .survey-body {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        text-align: left;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        margin-bottom: 30px; /* 설문 영역 간 여백 */
    }

    .survey-body .button-container {
        text-align: right;
    }

    .survey-body .button-container button {
        display: inline-block;
    }
    li.selected {
        border: 2px solid #007BFF; /* 선택된 항목 테두리 추가 */
    }
    label {
        display: block; /* 라벨이 li의 전체 너비를 차지하도록 함 */
        width: 100%;
        padding: 8px 12px; /* 패딩 추가 */
        cursor: pointer;
    }

    input[type="radio"] {
        margin-right: 10px; /* 라디오 버튼과 텍스트 사이의 간격 */
        cursor: pointer;
    }
    .questionnaire-question {
        font-size: 16px;
        color: #333;
        padding: 10px;
        background-color: #f9f9f9;
        border: 1px solid #ddd;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        margin-bottom: 20px;
    }
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