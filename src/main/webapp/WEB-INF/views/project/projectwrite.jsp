<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>프로젝트 생성 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>

    <script type="text/javascript" src="/js/project/projectwrite.js"></script>
    <link rel="stylesheet" href="/css/project/projectwrite.css"/>
</head>
<body>
<div class="input-form overflow-scroll">
    <h4>프로젝트 생성</h4>
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
            <div class="form-row">
                <label for="dept-list">담당부서 </label>
                <select id="dept-list">
                    <option value="" selected disabled hidden>부서 선택</option>
                    <c:forEach items="${department}" var="department">
                        <option value="${department.deptId}">${department.deptName}</option>
                    </c:forEach>
                </select>
                <input id="hidden-dept-id" type="hidden" name="deptId"/>
            </div>

            <%-- 담당자 (PM) 선택 --%>
            <div class="form-row">
                <label for="hidden-pm-id">담당자 </label>
                <input id="pm-search" autocomplete="off" placeholder="담당자 검색" class="input-custom">
                <input id="hidden-pm-id" type="hidden" name="pmId"/>
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
                <button id="btn-create" type="button">생성</button>
            </div>
        </div>
    </form>
</div>
</body>

</html>