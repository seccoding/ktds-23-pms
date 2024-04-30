<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>프로젝트 상세 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/project/projectview.js"></script>
    <script type="text/javascript" src="/js/lib/chart.js"></script>
    <link rel="stylesheet" href="/css/project/modal.css"/>
    <link rel="stylesheet" href="/css/project/projecttab.css"/>
    <link rel="stylesheet" href="/css/project/projectview.css"/>
</head>
<body>

<jsp:include page="modal.jsp"/>

<div>

    <jsp:include page="projecttaps.jsp"/>

    <div class="project-boards">
        <%--    요구사항, 이슈, 지식관리, 묻고 답하기 관리 권한에 대한 요소를 보여준다.   --%>
        <ul>
            <c:if test='${project.reqYn eq "Y"}'>
                <li>
                    <span class="badge bg-success">요구사항관리</span>
                </li>
            </c:if>
            <c:if test='${project.isYn eq "Y"}'>
                <li>
                    <span class="badge bg-label-danger">이슈관리</span>
                </li>
            </c:if>
            <c:if test='${project.knlYn eq "Y"}'>
                <li>
                    <span class="badge bg-label-warning">지식관리</span>
                </li>
            </c:if>
            <c:if test='${project.qaYn eq "Y"}'>
                <li>
                    <span class="badge bg-label-info">묻고답하기</span>
                </li>
            </c:if>
            <c:if test='${project.outYn eq "Y" and (sessionScope._LOGIN_USER_.admnCode eq "301" or sessionScope._LOGIN_USER_.empId eq pm.tmId)}'>
                <li>
                    <span class="badge bg-success">산출물관리</span>
                </li>
            </c:if>
        </ul>
    </div>

    <div
            class="grid bs"
            data-gap="0.5rem"
            data-grid-columns="1fr 1fr"
            data-grid-rows="auto"
    >
        <div class="overflow-scroll">
            <div>
                <table class="fit-parent">
                    <tr>
                        <th>프로젝트</th>
                        <td>${project.prjName}</td>
                        <th>PM</th>
                        <td>${pm.employeeVO.empName}</td>
                        <th style="text-align: center;">진행상태</th>
                    </tr>
                    <tr>
                        <th>기간</th>
                        <td>${project.strtDt} ~ ${project.endDt}</td>
                        <th>담당부서</th>
                        <td>${project.deptVO.deptName}</td>
                        <td rowspan="2" style="text-align: center;">${project.prjStsCode.cmcdName}</td>
                    </tr>
                    <tr>
                        <th>인원</th>
                        <td>${teammateCount}</td>
                        <th>고객사</th>
                        <td>${project.clntInfo}</td>
                    </tr>
                </table>
            </div>

            <div id="chart_package">
                <table class="fit-parent not-bottom-line chart-table">
                    <tr>
                        <c:if test='${project.reqYn eq "Y"}'>
                            <td>
                                <div class="chart-container">
                                    <canvas id="requirement-chart"></canvas>
                                </div>
                            </td>
                        </c:if>
                        <c:if test='${project.isYn eq "Y"}'>
                            <td>
                                <div class="chart-container">
                                    <canvas id="issue-chart"></canvas>
                                </div>
                            </td>
                        </c:if>
                    </tr>

                    <tr>
                        <c:if test='${project.reqYn eq "Y"}'>
                            <td>
                                <div class="center-info-item">

                                    <table class="fit-parent">
                                        <tbody class="center-info-item">
                                        <tr id="requirement-info-name">

                                        </tr>
                                        <tr id="requirement-info-value">

                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </td>
                        </c:if>

                        <c:if test='${project.isYn eq "Y"}'>
                            <td>
                                <div class="center-info-item">
                                    <table class="fit-parent">
                                        <tr id="issue-info-table-name">

                                        </tr>
                                        <tr id="issue-info-table-value">

                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </c:if>
                    </tr>
                </table>
            </div>
        </div>

        <c:choose>
            <c:when test='${project.reqYn eq "Y"}'>
                <div class="overflow-scroll">
                    <table class="table text-center">
                        <thead>
                        <tr>
                            <th>요구사항</th>
                            <th>시작일</th>
                            <th>종료일</th>
                            <th>일정상태</th>
                            <th>요구사항 진행상태</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requirement}" var="requirement">
                            <tr class="project-row" data-project-id="${requirement.rqmId}">
                                <td>${requirement.rqmTtl}</td>
                                <td>${requirement.strtDt}</td>
                                <td>${requirement.endDt}</td>
                                <td>${requirement.scdStsVO.cmcdName}</td>
                                <td>${requirement.rqmStsVO.cmcdName}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                    요구사항 관리 권한이 없는 프로젝트 입니다.
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <c:if test="${sessionScope._LOGIN_USER_.admnCode eq '301'}">
        <div class="btn-group">
            <div>
                <button id="btn-modify" type="button" onclick="location.href='/project/modify/${project.prjId}'">수정
                </button>
                <button id="btn-delete" type="button" value="${project.prjId}">삭제</button>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
