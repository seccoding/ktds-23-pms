<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>팀원 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <style>

    </style>
</head>
<body>

<div>
    <div>
        <h4>
            프로젝트 : ${projectName}
        </h4>
    </div>

    <div>
        ${teammateCount}명
    </div>

    <table class="table">
        <thead>
        <tr>
            <th>부서</th>
            <th>역할</th>
            <th>이름</th>
            <th>이메일</th>
        </tr>
        </thead>
        <tbody>
        <!-- jstl > choose when otherwise / if ~ elif ~ else-->
        <c:choose>
            <%-- projectList 내용이 존재한다면 --%>
            <c:when test="${not empty teammate}">
                <%-- 내용을 반복해서 보여줌 --%>
                <c:forEach items="${teammate}" var="teammate">
                    <tr class="teammate-row" data-teammate-id="${teammate.tmId}">
                        <td>${teammate.employeeVO.departmentVO.deptName}</td>
                        <td>${teammate.role}</td>
                        <td>${teammate.employeeVO.empName}</td>
                        <td>${teammate.employeeVO.email}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <%-- projectList의 내용이 존재하지 않는다면 --%>
            <c:otherwise>
                <tr>
                    <td colspan="4">
                        <a href="/project/write">
                            등록된 팀원이 없습니다.
                        </a>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
</body>
</html>
