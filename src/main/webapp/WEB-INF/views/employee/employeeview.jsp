<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사원 상세정보 </title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <jsp:include page="../commonmodal.jsp"></jsp:include>
    <script type="text/javascript" src="/js/employee/employeeview.js"></script>
     <style type="text/css">
        
        .grid-container{
          display: grid;
          grid-template-columns: 1fr;
          grid-template-rows: auto;
          gap: 1rem;
        }
        .info-container{
          display: grid;
          grid-template-columns: repeat(2, 1fr);
          gap: 1rem;
        }
        .table-container {
          flex: 1 1 calc(50% - 1rem);
          margin: 0.1rem;
          padding: 0.5rem;
          background-color: var(--box-bg);
        }
        .table-container > * + * {
          border-left: none;
        }
        .table-container > *:nth-child(2n) {
          border-right: none;
        }
        .table-container > *:nth-last-child(-n+2) {
          border-bottom: none;
        }
        .grid {
          display: grid;
          grid-template-columns: 1fr 1fr;
          gap: 1rem;
        }
      
       
        .photo{
          width: 10rem;
          height: 10rem;
          
        }
        label{
            font-weight: bold;
        }
        .change1{
          margin-top: 1rem;
          margin-bottom: 1rem;
        }
        .change1 > h5 {
          margin-left: 5rem;
          margin-bottom: 0.5rem;
        }
        .change-table{
          width: 90%;
          margin-left: 5%;
          background-color: var(--box-bg);
        }
        .overflow-table{
          height: 8.735625rem;
          overflow-y: auto;
        }
        .btn-group{
          text-align: right;
          padding-bottom: 1rem;
          padding-right: 3.5rem;
        }
        .box-width {
          width: 90%;
        }
        .box-align{
          display: flex;
          justify-content: center;  
        }
      </style>
</head>
<body>
  
  <dialog class="pstn-modal">
    <select class="pstn-select-box" data-origin="${employeeVO.pstnId}">
      <c:forEach items="${positionList}" var="posi" >
        <option value="${posi.cmcdId}">${posi.cmcdName}</option>
      </c:forEach>
    </select>
    <label for="pstn-change-note">변경사유</label>
    <input type="text" id="pstn-change-note">
    <div>
      <button class="change-pstn-btn">변경</button>
      <button class="change-pstn-cancel">취소</button>
    </div>
  </dialog>

  <dialog class="job-modal">
    <select class="job-select-box" data-origin="${employeeVO.jobId}">
      <c:forEach items="${jobList}" var="job" >
        <option value="${job.jobId}">${job.jobName}</option>
      </c:forEach>
    </select>
    <label for="job-change-note">변경사유</label>
    <input type="text" id="job-change-note">
    <div>
      <button class="change-job-btn">변경</button>
      <button class="change-job-cancel">취소</button>
    </div>
  </dialog>

    <h3 style="margin: 1rem auto; text-align: center;">${employeeVO.empName} ${employeeVO.commonCodeVO.cmcdName} 정보란</h3>
    
    <div class="change1">
    <c:choose>
        <c:when test="${sessionScope._LOGIN_USER_.empId eq employeeVO.empId || sessionScope._LOGIN_USER_.admnCode eq '301'}">
          <div class="btn-group">
            <button class="backto-list">
              <a href="/employee/search">목록</a>
            </button>
            <button> 
              <a href="/employee/modify/${employeeVO.empId}">수정</a>
            </button>
            <c:if test="${sessionScope._LOGIN_USER_.admnCode eq '301'}">
              <button class="change-pstn">직급 변경</button>
              <button class="change-job">직무 변경</button>
              <button class="delete-employee">퇴사 처리</button>
            </c:if>
          </div>
        </c:when>
      </c:choose>
    </div>

    <!-- 그리드 시작 -->
      <div class="change1 box-align">
      <div class="info-container box-width">
          <div class="grid-container" data-id="${employeeVO.empId}">
            <div style="text-align: center;">
              <c:choose>
                <c:when test="${not empty employeeVO.originPrflFileName}">
                  <img src="/employee/file/download/${employeeVO.originPrflFileName}" alt="프로필 사진" class="photo">
                </c:when>
                <c:otherwise>
                  <img src="/images/login.png" alt="프로필 사진" class="photo">
                </c:otherwise>
              </c:choose>
            </div>
            <div class="table-container">
              <div class="grid">
                <label for="deptName">부서</label>
              <div>${employeeVO.departmentVO.deptName}</div>

              <c:if test="${empty employeeVO.teamList}">
                <label for="noneTmName">팀</label>
                <div id="noneTmName">소속된 팀이 존재하지 않습니다.</div>
              </c:if>
              <c:forEach items="${employeeVO.teamList}" var="teamList">
                <label for="tmName">팀</label>
                <div id="tmName">${teamList.tmName}</div>
              </c:forEach>

              <label for="jobName">직무</label>
              <div>${employeeVO.jobVO.jobName}</div>

              </div>
            </div>  
          
          </div>  

          <div class="grid-container" data-id="${employeeVO.empId}">
            <div class="table-container">
              <div class="grid">
                <label for="empName">사원 이름</label>
                <div>${employeeVO.empName}</div>
        
                <label for="empId">사원 ID</label>
                <div id="empId" data-id="${employeeVO.empId}">${employeeVO.empId}</div>
        
                <label for="workSts">재직 상태</label>
                <div> 
                  ${employeeVO.workSts eq '201' ? '재직' : 
                    employeeVO.workSts eq '202' ? '휴직' : 
                    employeeVO.workSts eq '203' ? '퇴직예정' : 
                    employeeVO.workSts eq '204' ? '퇴직' : ''} 
                </div>
        
                <label for="hireYear">입사연차</label>
                <div>${employeeVO.hireYear}</div>
        
                <label for="hireDt">입사일</label>
                <div>${employeeVO.hireDt}</div>

                <label for="pstn">직급</label>
                <div>${employeeVO.commonCodeVO.cmcdName}</div>
        
                <label for="cntct">연락처</label>
                <div>${employeeVO.cntct}</div>
        
                <label for="addr">주소</label>
                <div>${employeeVO.addr}</div>
        
                <label for="brth">생년월일</label>
                <div>${employeeVO.brth}</div>
        
                <label for="email">이메일</label>
                <div>${employeeVO.email}</div>
              </div>  
            </div> 

          </div>

        </div>
      </div>
    <c:choose>
        <c:when test="${sessionScope._LOGIN_USER_.empId eq employeeVO.empId || sessionScope._LOGIN_USER_.admnCode eq '301'}">
          <div class="change1">
            <h5>직무 변경 사항</h5>
              <div class="overflow-table">

            
            <table class="job-change change-table">
              <thead>
                <tr>

                  <th>순서</th>
                  <th>이전 직무</th>
                  <th>근무 시작일</th>
                  <th>근무 종료일</th>
                  <th>변경 사유</th>
                </tr>
              </thead>
              <tbody>
                <c:if test="${fn:length(jobHistList) == 0}">
                  <tr>
                    <td colspan="5">
                      내역이 존재하지 않습니다.

                    </td>
                  </tr>
                </c:if>
                <c:forEach items="${jobHistList}" var="jH" varStatus="item">
                  <tr>
                    <td>${item.index+1}</td>
                    <td>${jH.jobVO.jobName}</td>
                    <td>${jH.jobStrtDt}</td>
                    <td>${jH.jobEndDt}</td>
                    <td>${jH.cnNote}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
          </div>
          <div class="change1">
            <h5>직급 변경 사항</h5>
            <div class="overflow-table">

            <table class="position-change change-table">
              <thead>
                <tr>

                  <th>순서</th>
                  <th>이전 직급</th>
                  <th>근무 시작일</th>
                  <th>근무 종료일</th>
                  <th>변경 사유</th>
                </tr>
              </thead>
              <tbody>
                <c:if test="${fn:length(positionHistList) == 0}">
                  <tr>
                    <td colspan="5">
                      내역이 존재하지 않습니다.

                    </td>
                  </tr>
                </c:if>
                <c:forEach items="${positionHistList}" var="posotionHist" varStatus="item">
                  <tr>
                    <td>${item.count}</td>
                    <td>${posotionHist.commonVO.cmcdName}</td>
                    <td>${posotionHist.pstnStrtDt}</td>
                    <td>${posotionHist.pstnEndDt}</td>
                    <td>${posotionHist.cnNote}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
            </div>
          </div>
          <div class="change1">
            <h5>부서 변경 사항</h5>
            <div class="overflow-table">

            <table class="dept-change change-table">
              <thead>
                <tr>

                  <th>순서</th>
                  <th>이전 부서</th>
                  <th>근무 시작일</th>
                  <th>근무 종료일</th>
                  <th>변경 사유</th>
                </tr>
              </thead>
              <tbody>
                <c:if test="${fn:length(departmentHistList) == 0}">
                  <tr>
                    <td colspan="5">
                      내역이 존재하지 않습니다.

                    </td>
                  </tr>
                </c:if>
                <c:forEach items="${departmentHistList}" var="deptHist" varStatus="item">
                  <tr>
                    <td>${item.count}</td>
                    <td>${deptHist.departmentVO.deptName}</td>
                    <td>${deptHist.deptStrtDt}</td>
                    <td>${deptHist.deptEndDt}</td>
                    <td>${deptHist.cnNote}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
            </div>
          </div>
        </c:when>
        <c:otherwise>
          <div class="btn-group">
            <button class="backto-list">
              <a href="/employee/search">목록</a>
            </button>
          </div>
        </c:otherwise>
      </c:choose>
</body>
</html>