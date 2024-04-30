<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>프로젝트 수정 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>

    <script type="text/javascript" src="/js/project/projectmodify.js"></script>
    <link rel="stylesheet" href="/css/project/projectmodify.css"/>
</head>
<body>
<div class="input-form overflow-scroll">
    <h4>프로젝트 수정</h4>
    <form>
        <div class="grid" data-grid-columns="auto"
             data-grid-rows="auto"
             data-column-gap="1rem"
             data-row-gap="1rem">
            <%-- 프로젝트 명, 고객사명 --%>
            <div class="form-row">
                <label for="project-name">프로젝트명</label>
                <input type="text" id="project-name" name="prjName" value="${project.prjName}" autocomplete="off"/>
            </div>

            <div class="form-row">
                <label for="client-info">고객사명</label>
                <input type="text" id="client-info" name="clntInfo" value="${project.clntInfo}" autocomplete="off"/>
            </div>

            <%-- 게시판 생성 여부 --%>
            <div class="form-row checkbox">
                <div>
                    <input type="checkbox" id="requirement-check" name="reqYn" value="Y"
                           <c:if test="${project.reqYn == 'Y'}">checked</c:if> />
                    <label for="requirement-check"></label>
                    <label for="requirement-check">요구사항 관리 필요여부</label>
                    <input type="checkbox" id="output-check" name="outYn" value="Y"
                           <c:if test="${project.outYn == 'Y'}">checked</c:if> />
                    <label for="output-check"></label>
                    <label for="output-check">산출물 관리 필요여부</label>
                </div>
                <div>
                    <input type="checkbox" id="issue-check" name="isYn" value="Y" disabled
                           <c:if test="${project.isYn == 'Y'}">checked</c:if> />
                    <label for="issue-check"></label>
                    <label for="issue-check">이슈관리</label>
                    <input type="checkbox" id="knowledge-check" name="knlYn" value="Y" disabled
                           <c:if test="${project.knlYn == 'Y'}">checked</c:if> />
                    <label for="knowledge-check"></label>
                    <label for="knowledge-check">지식관리</label>
                    <input type="checkbox" id="qna-check" name="qaYn" value="Y" disabled
                           <c:if test="${project.qaYn == 'Y'}">checked</c:if> />
                    <label for="qna-check"></label>
                    <label for="qna-check">묻고 답하기</label>
                </div>
            </div>

            <%-- 상태코드 변경 --%>
            <div class="form-row">
                <label for="status">프로젝트 상태</label>
                <select id="status" name="prjSts">
                    <c:forEach items="${commonCodeList}" var="code">
                        <option value="${code.cmcdId}" ${project.prjSts == code.cmcdId ? 'selected' : ''}>${code.cmcdName}</option>
                    </c:forEach>
                </select>
            </div>

            <%-- 담당 부서 선택 --%>
            <div class="form-row">
                <label for="dept-list">담당부서 </label>
                <select id="dept-list">
                    <option value="" selected disabled hidden>부서 선택</option>
                    <c:forEach items="${department}" var="department">
                        <option value="${department.deptId}" ${project.deptId == department.deptId ? 'selected' : ''}>${department.deptName}</option>
                    </c:forEach>
                </select>
                <input id="hidden-dept-id" type="hidden" name="deptId" value="${project.deptId}"/>
            </div>

            <%-- 담당자 (PM) 선택 --%>
            <div class="form-row">
                <label for="hidden-pm-id">담당자 </label>
                <input id="pm-search" autocomplete="off" placeholder="담당자 검색" class="input-custom"
                       value="${pm.employeeVO.empName}-${project.deptVO.deptName}">
                <input id="hidden-pm-id" type="hidden" name="pmId" value="${pm.tmId}"/>
                <div id="employee-list" class="datalist-custom">
                    <c:forEach items="${employee}" var="employee">
                        <div class="option-custom"
                             data-emp-id="${employee.empId}">${employee.empName}-${employee.departmentVO.deptName}</div>
                    </c:forEach>
                </div>
            </div>

            <%--    html date type 사용으로 해결, datepicker는 미사용    --%>
            <div class="form-row">
                <div>
                    <label for="start-date">시작일</label>
                    <input type="date" id="start-date" name="strtDt" value="${project.strtDt}"/>

                    <label for="end-date">종료일</label>
                    <input type="date" id="end-date" name="endDt" value="${project.endDt}"/>
                </div>
            </div>

            <div class="form-row">
                    <button id="btn-modify" type="button" value="${project.prjId}">수정</button>
            </div>
        </div>
    </form>
</div>
</body>

</html>