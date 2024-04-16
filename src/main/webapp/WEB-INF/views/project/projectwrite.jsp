<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>프로젝트 생성 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>

    <script type="text/javascript" src="/js/project/projectwrite.js"></script>
</head>
<body>
<h4>프로젝트 생성</h4>
<form>
    <div class="grid" data-grid-columns="auto"
         data-grid-rows="auto"
         data-column-gap="1rem"
         data-row-gap="1rem">
        <%-- 프로젝트 명, 고객사명 --%>
        <div>
            <label for="project-name">프로젝트명</label>
            <input type="text" id="project-name" name="prjName" value="${project.prjName}" autocomplete="off"/>

            <label for="client-info">고객사명</label>
            <input type="text" id="client-info" name="clntInfo" value="${project.clntInfo}" autocomplete="off"/>
        </div>

        <%-- 게시판 생성 여부 --%>
        <div>
            <div>
                <input type="checkbox" id="requirement-check" name="reqYn" value="Y"/>
                <label for="requirement-check"></label>
                <label for="requirement-check">요구사항 관리 필요여부</label>
                <input type="checkbox" id="output-check" name="outYn" value="Y"/>
                <label for="output-check"></label>
                <label for="output-check">산출물 관리 필요여부</label>
            </div>
            <div>
                <input type="checkbox" id="issue-check" name="isYn" value="Y" disabled/>
                <label for="issue-check"></label>
                <label for="issue-check">이슈관리</label>
                <input type="checkbox" id="knowledge-check" name="knlYn" value="Y" disabled/>
                <label for="knowledge-check"></label>
                <label for="knowledge-check">지식관리</label>
                <input type="checkbox" id="qna-check" name="qaYn" value="Y" disabled/>
                <label for="qna-check"></label>
                <label for="qna-check">묻고 답하기</label>
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
            <input list="employee-list" name="pmId" id="pm-selector" autocomplete="off">
            <datalist id="employee-list">
                <option value="test">테스트</option>
                <c:forEach items="${employee}" var="employee">
                    <option value="${employee.empId}">${employee.empName}</option>
                </c:forEach>
            </datalist>
        </div>

        <%--    html date type 사용으로 해결, datepicker는 미사용    --%>
        <div>
            <label for="start-date">시작일</label>
            <input type="date" id="start-date" name="strtDt" value="${project.strtDt}"/>
            <label for="end-date">종료일</label>
            <input type="date" id="end-date" name="endDt" value="${project.endDt}"/>
        </div>

        <div>
            <div>
                <button id="btn-create" type="button">생성</button>
            </div>
        </div>
    </div>
</form>

</body>

</html>