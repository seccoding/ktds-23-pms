<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사원 상세정보 </title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/employeeview.js"></script>
     <style type="text/css">
        div.grid {
          display: grid;
          grid-template-columns: 80px 1fr;
          grid-template-rows: repeat(6, 28px) auto auto 1fr;
          row-gap: 10px;
        }
      </style>
</head>
<body>
    <h2>상세정보</h2>
    <label for="prfl">프로필 사진</label>
    <div>
        <a href="/employee/file/download/${employeeVO.empId}">
            ${employeeVO.prfl}
        </a>
    </div>

    <div class="grid" data-id="${employeeVO.empId}">
        <label for="empName">사원 이름</label>
        <div>${employeeVO.empName}</div>

        <label for="empId">사원 ID</label>
        <div>${employeeVO.empId}</div>

        <label for="workSts">재직 상태</label>
        <div>${employeeVO.workSts}</div>

        <label for="hireYear">입사연차</label>
        <div>${employeeVO.hireYear}</div>

        <label for="hireDt">입사일</label>
        <div>${employeeVO.hireDt}</div>

        <label for="deptName">부서</label>
        <div>${employeeVO.departmentVO.deptName}</div>

        <label for="tmName">팀</label>
        <div>${employeeVO.teamVO.tmName}</div>

        <label for="jobName">직무</label>
        <div>${employeeVO.jobVO.jobName}</div>

        <label for="cntct">연락처</label>
        <div>${employeeVO.cntct}</div>

        <label for="addr">주소</label>
        <div>${employeeVO.addr}</div>

        <label for="brth">생년월일</label>
        <div>${employeeVO.brth}</div>

        <label for="email">이메일</label>
        <div>${employeeVO.email}</div>
  <c:if
        test="${sessionScope._LOGIN_USER_.empId eq employeeVO.empId || sessionScope._LOGIN_USER_.mngrYn eq 'Y'}"
      >
      <div class="btn-group">
        <button class="backto-list">
          <a href="/employee/search">목록</a>
        </button>
        <button> 
        <a href="/employee/modify/${employeeVO.empId}">수정</a>
      </button>
      </div>
      </c:if>
    </div>
</body>
</html>