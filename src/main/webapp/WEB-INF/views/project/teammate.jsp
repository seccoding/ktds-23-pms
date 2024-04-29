<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>팀원 페이지</title>
    <jsp:include page="../commonheader.jsp"/>
    <script type="text/javascript" src="/js/project/teammate.js"></script>
    <link rel="stylesheet" href="/css/project/modal.css"/>
    <link rel="stylesheet" href="/css/project/projecttab.css"/>
    <link rel="stylesheet" href="/css/project/teammate.css"/>
</head>
<body>

<%-- 팀원 삭제, 추가 모달 영역 --%>
<jsp:include page="modal.jsp"/>

<%-- alert 모달 영역 --%>


<%--메인 컨텐츠--%>
<div>

    <jsp:include page="projecttaps.jsp"/>

    <div class="project-name" id="project" data-project-id="${project.prjId}">
        <span>${project.prjName} - ${teammateCount}명</span>
    </div>

    <div class="bs">
        <table class="mb-0 table">
            <thead>
            <tr>
                <%--      삭제기능, if 필요      --%>
                <c:if test='${sessionScope._LOGIN_USER_.admnCode eq "301" or sessionScope._LOGIN_USER_.empId eq pm.tmId}'>

                    <th>
                        <input type="checkbox"
                               id="checked-all"
                               data-target-class="target-teammate-id"/>
                        <label for="checked-all"></label>
                    </th>

                </c:if>
                <th>부서</th>
                <th>역할</th>
                <th>이름</th>
                <th>이메일</th>

                <%--      삭제기능, if 필요      --%>
                <c:if test='${sessionScope._LOGIN_USER_.admnCode eq "301" or sessionScope._LOGIN_USER_.empId eq pm.tmId}'>
                    <th></th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <%-- projectList 내용이 존재한다면 --%>
                <c:when test="${not empty teammate}">
                    <%-- 내용을 반복해서 보여줌 --%>
                    <c:forEach items="${teammate}" var="teammate">
                        <tr class="teammate-row" data-teammate-id="${teammate.tmId}" id="${teammate.tmId}">
                                <%--      삭제기능, if 필요      --%>
                            <c:if test='${sessionScope._LOGIN_USER_.admnCode eq "301" or sessionScope._LOGIN_USER_.empId eq pm.tmId}'>
                                <td>
                                    <c:choose>
                                        <c:when test="${teammate.role eq 'PM'}">
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox"
                                                   class="target-teammate-id"
                                                   name="targetTeammateId"
                                                   value="${teammate.prjTmId}"
                                                   id="${teammate.prjTmId}">
                                            <label for="${teammate.prjTmId}"></label>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </c:if>
                            <td>${teammate.employeeVO.departmentVO.deptName}</td>
                            <td>${teammate.role}</td>
                            <td>${teammate.employeeVO.empName}</td>
                            <td>${teammate.employeeVO.email}</td>
                                <%--      삭제기능, if 필요      --%>

                            <c:if test='${sessionScope._LOGIN_USER_.admnCode eq "301" or sessionScope._LOGIN_USER_.empId eq pm.tmId}'>

                                <c:choose>
                                    <c:when test="${teammate.role eq 'PM'}">
                                        <td class="h-59">
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            <button value="${teammate.prjTmId}"
                                                    name="deleteTeammate"
                                                    onclick="javascript:void(0);">삭제
                                            </button>

                                        </td>
                                    </c:otherwise>
                                </c:choose>

                            </c:if>
                        </tr>
                    </c:forEach>
                </c:when>
                <%-- projectList의 내용이 존재하지 않는다면 --%>
                <c:otherwise>
                    <tr>
                        <td colspan="6">
                            <p>
                                등록된 팀원이 없습니다.
                            </p>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>

    <c:if test='${sessionScope._LOGIN_USER_.admnCode eq "301" or sessionScope._LOGIN_USER_.empId eq pm.tmId}'>

        <div class="btn-group">
            <div>
                    <%--해당 버튼을 누르면 팀원 등록 모달이 생김--%>
                <button id="new-teammate" onclick="javascript:void(0);" data-dept-id="${deptId}">팀원 등록</button>
                <button id="delete-massive-teammate" onclick="javascript:void(0);">일괄 삭제</button>
            </div>
        </div>

    </c:if>
</div>
</body>
</html>
