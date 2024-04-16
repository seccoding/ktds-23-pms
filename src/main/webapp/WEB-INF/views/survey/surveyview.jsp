<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <h1>상세 정보</h1>
    <div class="grid" data-id="${surveyQuestionVO.prjId}">
        <div>프로젝트 명</div>
        <div>${surveyQuestionVO.projectVO.prjName}</div>
        <div>총책임자 명</div>
        <div>${surveyQuestionVO.employeeVO.empName}</div>
        <div>수행 부서</div>
        <div>${surveyQuestionVO.departmentVO.deptName}</div>
        <div>수행 기간</div>
        <div></div>
    </div>
    <div>
        <a href="/survey/write?prjId=${surveyQuestionVO.prjId}">설문 생성</a>
    </div>
</body>
</html>