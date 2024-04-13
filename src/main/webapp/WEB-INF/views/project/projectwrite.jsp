<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>프로젝트 생성 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>

    <link rel="stylesheet" href="/css/lib/jquery-ui.css"/>
    <script type="text/javascript" src="/js/lib/jquery-ui.js"></script>
    <script type="text/javascript" src="/js/lib/datepicker.js"></script>

    <script type="text/javascript" src="/js/project/projectwrite.js"></script>
</head>
<body>
<jsp:include page="../layout/layout.jsp"></jsp:include>
<div class="main">
    <h4>프로젝트 생성</h4>
    <form action="/project/write" method="post">

        <c:if test="${not empty errorMessage}">
            <dialog class="alert-dialog">
                <h1>${errorMessage}</h1>
            </dialog>
        </c:if>

        <%-- 프로젝트 명, 고객사명 --%>
        <div>
            <label for="project-name">프로젝트명</label>
            <input type="text" id="project-name" name="prjName" value="${project.prjName}"/>

            <label for="client-info">고객사명</label>
            <input type="text" id="client-info" name="clntInfo" value="${project.clntInfo}"/>
        </div>

        <%-- 게시판 생성 여부 --%>
        <div>
            <label><input type="checkbox" id="requirement-check" name="reqYn" value="Y"> 요구사항 관리 필요여부</label>
            <label><input type="checkbox" id="output-check" name="outYn" value="Y"> 산출물 관리 필요여부</label>
            <div>
                <label><input type="checkbox" id="issue-check" name="isYn" value="Y" disabled> 이슈관리</label>
                <label><input type="checkbox" id="knowledge-check" name="knlYn" value="Y" disabled> 지식관리</label>
                <label><input type="checkbox" id="qna-check" name="qaYn" value="Y" disabled> 묻고 답하기</label>
            </div>
        </div>

        <%-- 담당 부서 선택 --%>
        <div>
            <label for="dept-list">담당부서 </label>
            <select id="dept-list" name="deptId">
                <option value="volvo">테스트</option>
                <c:forEach items="${department}" var="department">
                    <option value="${department.deptId}">${department.deptName}</option>
                </c:forEach>
            </select>
        </div>

        <%-- 담당자 (PM) 선택 --%>
        <div>
            <label for="pm-selector">담당자 </label>
            <input list="employee-list" name="pmId" id="pm-selector">
            <datalist id="employee-list">
                <option value="Safari">테스트</option>
                <c:forEach items="${employee}" var="employee">
                    <option value="${employee.empId}">${employee.empName}</option>
                </c:forEach>
            </datalist>
        </div>

        <%-- datepicker.js 사용, 시작일과 종료일 선택 --%>
        <div>
            <label for="start-datepicker">시작일</label>
            <input type="text" id="start-datepicker" name="strtDt" value="${project.strtDt}"/>
            <label for="start-datepicker">종료일</label>
            <input type="text" id="end-datepicker" name="endDt" value="${project.endDt}"/>
        </div>

        <div>
            <div>
                <input type="submit" value="저장"/>
            </div>
        </div>
    </form>

</div>
<jsp:include page="../layout/layout_close.jsp"></jsp:include>
</body>

</html>