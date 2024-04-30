<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사원 조회</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/employee/employeelist.js"></script>
    <style>
        div.grid div.right-align {
            text-align : right;
        }
        
    </style>
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
                    <th>부서명</th>
                    <th>직무명</th>
                    <th>생년월일</th>
                </tr>
            </thead>
        
        <tbody>
            <c:choose>
                <c:when test="${not empty employeeList.employeeList}">
                    <c:forEach items="${employeeList.employeeList}" var="employee">
                        <tr>
                            <td>${employee.empId}</td>
                            <td>
                                <a href="/employee/view?empId=${employee.empId}">${employee.empName}</a>
                            </td>
                            <td>${employee.departmentVO.deptName}</td>
                            <td>${employee.jobVO.jobName}</td>
                            <td>${employee.brth}</td>
                        </tr>
                    </c:forEach>
                </c:when>
            </c:choose>
        </tbody>
    </table>
    <div>
        <nav aria-label="Page navigation">
        <form id="search-form">
          <input type="hidden" id="page-no" name="pageNo" value="0" />
          <select id="list-size" name="listSize">
            <option value="10" ${searchEmployeeVO.listSize eq 10 ? 'selected' : ''}>10개</option>
            <option value="20" ${searchEmployeeVO.listSize eq 20 ? 'selected' : ''}>20개</option>
            <option value="30" ${searchEmployeeVO.listSize eq 30 ? 'selected' : ''}>30개</option>
            <option value="50" ${searchEmployeeVO.listSize eq 50 ? 'selected' : ''}>50개</option>
          </select>
    
          <select id="search-type" name="searchType">
            <option value="employee_name" ${searchEmployeeVO.searchType eq 'employee_name' ? 'selected' : ''}>사원명</option>
            <option value="department_name" ${searchEmployeeVO.searchType eq 'department_name' ? 'selected' : ''}>부서명</option>
            <option value="job_name" ${searchEmployeeVO.searchType eq 'job_name' ? 'selected' : ''}>직무명</option>
          </select>
    
          <input type="text" name="searchKeyWord" value="${searchEmployeeVO.searchKeyWord}" />
          <button type="button" id="search-btn">검색</button>
         </form>
         <ul class="pagination">
            <c:if test="${searchEmployeeVO.hasPrevGroup}">
                <li class="page-item first">
                    <a class="page-link" href="javascript:search(0);"><img src="/images/chevron-double-left.svg"/></a>
                </li>
                <li class="page-item prev">
                    <a class="page-link" href="javascript:search(${searchEmployeeVO.prevGroupStartPageNo});"><img src="/images/chevron-left.svg"/></a>
                </li>
            </c:if>
            <c:forEach begin="${searchEmployeeVO.groupStartPageNo}" end="${searchEmployeeVO.groupEndPageNo}" step="1" var="p">
                <li class="${searchEmployeeVO.pageNo eq p ? 'active' : ''} page-item">
                    <a class="page-link" href="javascript:search(${p});">${p+1}</a>
                </li>
            </c:forEach>
            <c:if test="${searchEmployeeVO.hasNextGroup}">
                <li class="page-item next">
                    <a class="page-link" href="javascript:search(${searchEmployeeVO.nextGroupStartPageNo});"><img src="/images/chevron-right.svg"/></a>
                </li>
                <li class="page-item last">
                    <a class="page-link" href="javascript:search(${searchEmployeeVO.pageCount-1});"><img src="/images/chevron-double-right.svg"/></a>
                </li>
            </c:if>
        </ul>
        </nav>
      </div>
</body>    
</html>


  
