<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>프로젝트 상세 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/project/projectview.js"></script>
    <script type="text/javascript" src="/js/lib/chart.js"></script>
</head>
<body>
<jsp:include page="../layout/layout.jsp"></jsp:include>

<div class="main">

    <%--  상단 탭  --%>
    <%--  기본정보, 구성원, 산출물  --%>
    <div class="tabs">
        <ul>
            <li>
                <a id="tab-show" href="/project/view?prjId=${project.prjId}">기본정보</a>
            </li>
            <li>
                <a id="tab-member" href="#">구성원</a>
            </li>
            <li>
                <a id="tab-output" href="#">산출물</a>
            </li>
        </ul>
    </div>

    <table>
        <tr>
            <th>프로젝트명</th>
            <td>${project.prjName}</td>
            <th>PM</th>
            <td>${pm.employeeVO.empName}</td>
        </tr>
        <tr>
            <th>기간</th>
            <td>${project.strtDt} ~ ${project.endDt}</td>
            <th>담당부서</th>
            <td>${project.deptVO.deptName}</td>
        </tr>
        <tr>
            <th>참여인원수</th>
            <td>${teammateCount}</td>
            <th>프로젝트 상태</th>
            <td>${project.prjStsCode.cmcdName}</td>
        </tr>
    </table>




    <div id="chart-package">


        <div>
            <canvas id="requirement-chart"></canvas>
        </div>

        <div>
            <canvas id="issue-chart"></canvas>
        </div>



        <div >
            <table>
                <tr>

                </tr>
                <tr>

                </tr>
            </table>
        </div>
        <div >
            <tr>

            </tr>
            <tr>

            </tr>
        </div>
    </div>
</div>

<jsp:include page="../layout/layout_close.jsp"></jsp:include>
</body>
</html>
