<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>회원가입 페이지</title>
    <style type="text/css">
      .grid {
        display: grid;
        grid-template-columns: 100px 1fr 100px 1fr;
        grid-template-rows: 35px 35px 35px 35px 35px 1fr;
        column-gap: 0;
        gap: 0;
      }
      
      
      input {
        width: 90%;
        height: 25px;
      }

      .footer {
        position: absolute;
        bottom: 0;
      }
    </style>
    <script type="text/javascript" src="/js/lib/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/employee/regist.js"></script>
  </head>
  <body>
    <div class="header">
      <h2>홈페이지 로고 + 회원가입</h2>
      <div>경영지원부 소속 사원들만 회원가입을 진행 할 수 있습니다</div>
    </div>
    <div class="content">
      <form id="registForm" method="post">
        <input type="hidden" name="next" id="next" value="${nextUrl}" />
        <div class="grid">
          <div class="empId">
            <label for="empId">사원번호: </label>
          </div>
          <div>
            <input
              id="empId"
              type="text"
              name="empId"
              value="${employeeVO.empId}"
            />
          </div>
          <div class="pwd">
            <label for="pwd">비밀번호: </label>
          </div>
          <div>
            <input
              id="pwd"
              type="password"
              name="pwd"
              value="${employeeVO.pwd}"
            />
          </div>
          <div class="empName">
            <label for="empName">사원이름: </label>
          </div>
          <div>
            <input
              id="empName"
              type="text"
              name="empName"
              value="${employeeVO.empName}"
            />
          </div>
          <div class="hireDt">
            <label for="hireDt">입사일: </label>
          </div>
          <div>
            <input
              id="hireDt"
              type="date"
              name="hireDt"
              value="${employeeVO.hireDt}"
            />
          </div>
          <div>
            <label for="prfl">프로필 사진(URL?): </label>
          </div>
          <div>
            <input
              id="prfl"
              type="text"
              name="prfl"
              value="${employeeVO.prfl}"
            />
          </div>
          <div>
            <label for="cntct">연락처: </label>
          </div>
          <div>
            <input
              id="cntct"
              type="text"
              name="cntct"
              value="${employeeVO.cntct}"
            />
          </div>
          <div class="addr">
            <label for="addr">주소: </label>
          </div>
          <div>
            <input
              id="addr"
              type="text"
              name="addr"
              value="${employeeVO.addr}"
            />
          </div>
          <div class="brth">
            <label for="brth">생년월일: </label>
          </div>
          <div>
            <input
              id="brth"
              type="date"
              name="brth"
              value="${employeeVO.brth}"
            />
          </div>
          <div class="email">
            <label for="email">이메일: </label>
          </div>
          <div>
            <input
              id="email"
              type="email"
              name="email"
              value="${employeeVO.email}"
            />
          </div>
          <div class="pstnId">
            <label for="pstnId">직급번호: </label>
          </div>
          <div>
            <input
              id="pstnId"
              type="text"
              name="pstnId"
              value="${employeeVO.pstnId}"
              placeholder="101~110"
            />
          </div>
          <div class="deptId">
            <label for="deptId">부서번호: </label>
          </div>
          <div>
            <input
              id="deptId"
              type="text"
              name="deptId"
              value="${employeeVO.deptId}"
              placeholder="DEPT_000000_000000"
            />
          </div>
          <div class="jobId">
            <label for="jobId">직무번호: </label>
          </div>
          <div>
            <input
              id="jobId"
              type="text"
              name="jobId"
              value="${employeeVO.jobId}"
              placeholder="JOB_000000_000000"
            />
          </div>
          <div>
            <label for="mngrYn">임원여부: </label>
          </div>
          <div>
            <input
              id="mngrYn"
              type="text"
              name="mngrYn"
              value="${employeeVO.mngrYn}"
            />
          </div>
          <div><button type="button" id="regist-btn">회원가입</button></div>
          
        </div>
      </form>
    </div>
    <div class="footer">
      <div>회원 가입 문의: 경영지원부 (전화번호):123-1234</div>
      <div>회사 정보(이름, 사업자 번호, 대표명, 전호번호, 이메일)</div>
      <div>회사 주소</div>
      <div>COPYRIGHT</div>
    </div>
  </body>
</html>
