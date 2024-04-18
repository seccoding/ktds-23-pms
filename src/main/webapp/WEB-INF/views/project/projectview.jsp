<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>프로젝트 상세 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/project/projectview.js"></script>
    <script type="text/javascript" src="/js/lib/chart.js"></script>
    <style>
        .chart-table {
            width: 100%;
            table-layout: fixed; /* 테이블 내의 셀 너비를 고정합니다 */
        }

        .chart-table td {
            text-align: center; /* 내용을 가운데로 정렬합니다 */
        }

        .chart-container {
            max-width: 400px;
            max-height: 400px;
            margin: auto;
        }

        .chart-container canvas {
            width: 100% !important;
            height: auto !important;
        }

        .center-info-item {
            display: inline-block;
            justify-content: center;
        }

        .btn-group {
            margin: 15px;
            float: right;
        }
    </style>
</head>
<body>
<div>

    <%--  상단 탭  --%>
    <%-- 기본정보, 구성원, 산출물, 요구사항 id를 js에서 QueryParam을 뽑아서 Control 해야할 듯 --%>
    <div class="tabs">
        <ul>
            <li>
                <a id="tab-show" href="/project/view?prjId=${project.prjId}">기본정보</a>
            </li>
            <li>
                <a id="tab-member" href="/project/team?prjId=${project.prjId}">구성원</a>
            </li>
            <li>
                <a id="tab-output" href="/output?prjId=${project.prjId}">산출물</a>
            </li>
            <li>
                <a id="tab-requirement" href="/requirement/search?prjId=${project.prjId}">요구사항</a>
            </li>
        </ul>
    </div>

    <div>
        <table class="table">
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
    </div>

    <div id="chart_package">
        <table class="chart-table">
            <tr>
                <td>
                    <div class="chart-container">
                        <canvas id="requirement-chart"></canvas>
                    </div>
                </td>
                <td>
                    <div class="chart-container">
                        <canvas id="issue-chart"></canvas>
                    </div>
                </td>
            </tr>

            <tr>
                <td>
                    <div class="center-info-item">

                        <table>
                            <tbody class="center-info-item">
                            <tr id="requirement-info-name">

                            </tr>
                            <tr id="requirement-info-value">

                            </tr>
                            </tbody>
                        </table>
                    </div>
                </td>
                <td>
                    <div class="center-info-item">
                        <table>
                            <tr id="issue-info-table-name">

                            </tr>
                            <tr id="issue-info-table-value">

                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
        </table>
    </div>

    <div class="btn-group">
        <div>
            <button id="btn-modify" type="button" onclick="location.href='/project/modify/${project.prjId}'">수정</button>
            <button id="btn-delete" type="button" onclick="location.href='/project/delete/${project.prjId}'">삭제</button>
        </div>
    </div>
</div>
</body>
</html>
