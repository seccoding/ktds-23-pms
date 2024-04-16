<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>부서/팀 조회</title>
    <jsp:include page="../commonheader.jsp" />
  </head>
  <body>
    <h1>부서/팀 조회</h1>
    <div>
      <table class="table">
        <thead>
          <tr>
            <th>부서명</th>
            <th>팀명</th>
            <th>사원명</th>
            <th>직무명</th>
            <th>직급</th>
          </tr>
        </thead>

        <tbody>
          <c:choose>
            <c:when test="${not empty departmentList.departmentList}">
              <c:forEach
                items="${departmentList.departmentList}"
                var="department"
              >
                <tr>
                  <td>${department.deptName}</td>
                  <td>${department.teamVO.tmName}</td>
                  <td>${department.employeeVO.empName}</td>
                  <td>${department.jobVO.jobName}</td>
                  <td>${department.commonCodeVO.cmcdName}</td>
                </tr>
              </c:forEach>
            </c:when>
          </c:choose>
        </tbody>
      </table>
    </div>
  </body>
</html>
