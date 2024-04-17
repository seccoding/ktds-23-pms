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
    .survey-question {
        display: flex;
        flex-direction: column;
    }
    .survey-question-top > div {
        display: inline-block;
    }
    .survey-question-bottom > ul > li > div {
        display: inline-block;
    }
</style>
</head>
<body>
    <h1>설문 작성</h1>
    <form>
        <div class="survey-body" data-id="${surveyQuestionVO.prjId}">
            <!-- <div class="survey-question">
                <div class="survey-question-top">
                    <div>질문 1</div>
                    <input type="text" placeholder="질문 입력" value="${surveyQuestionVO.srvQst}" />
                </div>
                <div class="survey-question-middle">
                    <span>질문 형태</span>
                    <button id="btn-selective-type">선택형</button>
                    <button id="btn-descriptive-type">서술형</button>
                </div>
                <div class="survey-question-bottom">
                    <ul>
                        <li>
                            <div>답변 1</div>
                            <input type="text" placeholder="답변명"/>
                            <input type="text" placeholder="연결"/>
                        </li>
                        <li>
                            <div>답변 2</div>
                            <input type="text" placeholder="답변명"/>
                            <input type="text" placeholder="연결"/>
                        </li>
                        <li>
                            <div>답변 3</div>
                            <input type="text" placeholder="답변명"/>
                            <input type="text" placeholder="연결"/>
                        </li>
                        <li>
                            <div>답변 4</div>
                            <input type="text" placeholder="답변명"/>
                            <input type="text" placeholder="연결"/>
                            <button>제거</button>
                        </li>
                    </ul>
                    <button> 답변 항목 추가 </button>
                    <button id="btn-insert-srv-qst">문항 완성</button>
                    <button id="btn-delete-srv-qst">문항 삭제</button>
                </div>
                <div>   
                </div>
            </div> -->
        </div>
    </form>
    <button id="btn-add-srv-qst">문항 추가</button>
    <button id="btn-compl-srv">설문 완성</button>
</body>
</html>