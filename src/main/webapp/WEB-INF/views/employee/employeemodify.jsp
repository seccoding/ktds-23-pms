<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사원 정보수정</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/employee/employeemodify.js"></script>
    <style>
        .hidden{
            display: none;
        }
    </style>
</head>
<body>
    <h2>사원 정보수정</h2>
    <!-- <form 
    action="/employee/modify/${employeeVO.empId}"
    method="post"
    enctype="multipart/form-data"> -->

    <div class="grid">
        <label for="empId">사원 ID</label>
        <input type="text" id="empId"
                name="empId" value="${employeeVO.empId}"/>

        <label for="empName">사원 이름</label>
        <input type="text" id="empName"
                name="empName" value="${employeeVO.empName}"/>

        <label for="workSts">재직 상태</label>
        <input type="text" id="workSts"
               name="workSts" value="${employeeVO.workSts}"/>

        <label for="hireYear">입사 연차</label>
        <input type="text" id="hireYear"
        name="hireYear" value="${employeeVO.hireYear}"/>

        <label for="hireDt">입사일</label>
        <input type="text" id="hireDt"
        name="hireDt" value="${employeeVO.hireDt}"/>

        <label for="dept-select">부서</label>
        <div>
            <select id="dept-select" class="dept-select">
                <c:forEach items="${departmentlist.departmentList}" var="department">
                    <option  value="${department.deptId}" >${department.deptName}</option>
                </c:forEach>
            </select>
    
            <input type="text" placeholder="부서 변경 사유를 입력해주세요." id="dept-change-cmt" class="hidden"/>

        </div>
        
        <!-- <input type="text" id="deptName"
        name="deptName" value="${employeeVO.departmentVO.deptName}"/> -->

        <div class="btn-group">
            <div class="right-align">
                <!-- <input type="submit" value="저장"/> -->
                <button class="save-modify">저장</button>
            </div>
        </div>
    </div>
    <!-- </form> -->
</body>
</html>