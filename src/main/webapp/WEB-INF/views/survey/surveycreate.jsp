<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 생성</title>
<jsp:include page="../commonheader.jsp"></jsp:include>
<jsp:include page="../commonmodal.jsp"></jsp:include>
<script type="text/javascript" src="/js/survey/surveycreate.js"></script>
<style type="text/css">
    div {
        border: 1px solid #000;
        margin: 0;
        padding: 10px;       
    }
    .modal-confirm-window div {
        border: 0px solid #000;
        margin: 0;
        padding: 0;       

    }
    .survey-body {
        border: 1px solid #ccc; /* 연한 회색 테두리 */
        border-radius: 8px; /* 둥근 꼭지점 */
        padding: 10px; /* 내부 여백 */
        margin-bottom: 20px; /* 하단 여백 */
        width: 100%;
    }
    .survey-question {
        display: flex;
        flex-direction: column;
        width: 100%;
    }
    .survey-question-top > div {
        display: inline-block;
    }
    .survey-question-bottom {
    	width: 100%;
    }
    .survey-question-bottom > ul > li > div {
        display: flex;
	    align-items: center;
	    justify-content: center;
	    height: 30px;
	    width: 30px;
	    border-radius: 50%;
	    background-color: #E9ECEF;
	    color: #333;
	    font-weight: bold;
	}
    button {
        margin-right: 5px; 
    }
    .delete-survey-question, #btn-compl-srv {
        float: right;
        margin-right: 0;
    }
    form:after {
        content: "";
        display: table;
        clear: both;
    }
    .survey-question-middle {
        display: flex;
        align-items: center;
        margin-bottom: 10px;
    }
    .survey-question-middle div, .survey-question-bottom li div {
	    width: 30px;
	    height: 30px;
	    line-height: 30px; /* 세로 중앙 정렬을 위해 높이와 동일하게 설정 */
	    text-align: center;
	    margin-right: 10px;
	    background-color: #E9ECEF; /* 연한 회색 배경 */
	    color: #333; /* 어두운 글자 색상 */
	    border-radius: 50%; /* 원형 모양 */
	    font-weight: bold; /* 글자 굵게 */
	    display: flex;
	    align-items: center;
	    justify-content: center;
	}
    .survey-question-middle input[type='text'] {
        flex-grow: 1;
    }
    .survey-body div {
        border: none;
    }
    .survey-question:not(:last-child) {
        border-bottom: 1px solid #ddd;
    }
    .survey-question-bottom li {
        display: flex; 
        align-items: center;
        margin-bottom: 5px;
        position: relative;
        padding-right: 90px;
	    width: 100%; /* 상위 요소의 너비를 100%로 설정 */
	}
	.survey-question-bottom li input[type='text'].answer-name {
	    flex-grow: 1; /* 나머지 공간 모두 차지 */
	    margin-right: 10px; /* 오른쪽 여백 */
	}	
	.survey-question-bottom li input[type='text'].link-input {
	    width: 100px; /* 고정된 너비 */
	    margin-right: 10px;
	}
	.survey-question-bottom li button.delete-survey-answer {
	    position: absolute; /* 절대 위치 설정 */
	    right: 0; /* 오른쪽 끝에 배치 */
	    width: 80px;
	}
	#btn-compl-srv {
	    margin-right: 20px;
	}
    .modal {
        border: none;  /* 모달 창에는 테두리 없음 */
        padding: 0;    /* 모달 창 패딩 없음 */
        margin: 0;     /* 모달 창 마진 없음 */
    }
</style>
</head>
<body>
    <h1>설문 생성</h1>
    <form class="survey-form">
        <div class="survey-body" data-prj-id="${projectVO.prjId}" data-srv-sts="${surveyQuestionVO.srvSts}"></div>
    </form>
    <button id="btn-add-srv-qst">문항 추가</button>
    <button id="btn-compl-srv">설문 완성</button>
</body>
</html>