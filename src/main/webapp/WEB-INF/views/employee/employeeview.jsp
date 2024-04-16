<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사원 상세정보 </title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="../layout/layout.jsp" />
    <img src="profile.jpg" alt="profile picture">
    ${employee.empId}
    <div class="grid">
        <table class="table" href="/employee/view?id=${employee.empId}">
            <thead>
                <tr>
                    <th>사원 번호</th>
                    <th>사원 이름</th>
                    <th>재직 상태</th>
                    <th>연차</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty employeeView.employeeView }">
                        <c:forEach items="${employeeView.employeeView}" var="employee">
                <tr>
                    <td>${employeeVO.empId}</td>
                    <td>${employeeVO.empName}</td>
                    <td>${employeeVO.workSts}</td>
                    <td>${employeeVO.hireYear}</td>
                </tr>
            </c:forEach>
        </c:when>
    </c:choose>
            </tbody>
            <thead>
                <tr>
                    <th>입사일</th>
                    <th>직급</th>
                    <th>직무</th>
                    <th>부서</th>
                    <th>팀</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>${employeeVO.hireDt}</th>
                    <th>${employeeVO.pstnId}</th>
                    <th>${employeeVO.jobId}</th>
                    <th>${employeeVO.deptId}</th>
                    <th>${teamVO.tmId}</th>
                </tr>
            </tbody>

        </table>
    </div>

</body>
</html>