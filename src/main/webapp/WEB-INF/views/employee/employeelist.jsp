<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사원 조회</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
</head>
<body>
    <h2>사원 조회</h2>
    <div class="flex">
        <div>총 ${employeeList.employeeCnt}명이 검색되었습니다. </div>
    </div>
    <div class="grid">
        <table class="table">
            <thead>
                <tr>
                    <th>사원ID</th>
                    <th>사원명</th>
                    <th>부서ID</th>
                    <th>직무ID</th>
                    <th>생년월일</th>
                </tr>
            </thead>
        
        <tbody>
            <c:choose>
                <c:when test="${not empty employeeList.employeeList}">
                    <c:forEach items="${employeeList.employeeList}" var="employee">
                        <tr>
                            <td>${employee.empId}</td>
                            <td>${employee.empName}</td>
                            <td>${employee.deptId}</td>
                            <td>${employee.jobId}</td>
                            <td>${employee.brth}</td>
                        </tr>
                    </c:forEach>
                </c:when>
            </c:choose>
        </tbody>
    </table>
    </div>

</body>    
</html>
