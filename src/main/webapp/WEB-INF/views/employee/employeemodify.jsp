<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>사원 정보수정</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/employee/employeemodify.js"></script>
    <style>
      .hidden {
        display: none;
      }
      .modal {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
      }
      .flex-col {
        display: flex;
        flex-direction: column;
        margin: 2rem;
      }
      .flex-col > div {
        margin: 1rem;
      }
      label {
        font-weight: bold;
      }
      .origin-dept {
        width: 30rem;
      }

      input[type="password"], input[type="date"] {
        width: 16rem;
        height: 2rem;
        border: 0;
        border-radius: var(--box-border-radius);
        padding-left: 10px;
        outline: none;
        background-color: var(--border-color);
      }
    </style>
  </head>
  <body>
    <h2>사원 정보수정</h2>
    <div id="myModal" class="modal hidden">
      <div id="modal-content" class="flex-col">
        <h3 id="modal-title"></h3>
        <div id="modal-message"></div>
        <button id="modal-close-btn">닫기</button>
      </div>
    </div>
    <!-- <form
    action="/employee/modify/${employeeVO.empId}"
    method="post"
    enctype="multipart/form-data"> -->

    <div class="grid" data-teamlist="${employeeVO.teamList}">
      <label for="empId">사원 ID</label>
      <input type="text" id="empId" name="empId" value="${employeeVO.empId}" disabled/>

      <label for="empName">사원 이름</label>
      <input type="text" id="empName" name="empName" value="${employeeVO.empName}" />

      <label for="cntct">연락처</label>
      <input type="text" id="cntct" value="${employeeVO.cntct}" />

      <label for="addr">주소</label>
      <input type="text" id="addr" value="${employeeVO.addr}" />

      <label for="brth">생년월일</label>
      <input type="text" id="brth" value="${employeeVO.brth}" />

      <label for="email">이메일</label>
      <input type="text" id="email" value="${employeeVO.email}" />

      <label for="newPwd">비밀번호</label>
      <input type="password" id="newPwd" name="newPwd" value="${employeeVO.newPwd}" style="width : 30rem" />

      <label for="confirmPwd">비밀번호 확인</label>
      <input type="password" id="confirmPwd" name="confirmPwd" value="${employeeVO.confirmPwd}" style="width: 30rem;" />
      <span id="password-match-status"></span>

      <!-- 개인 정보 수정 화면 -->
      <c:choose>
        <c:when test="${sessionScope._LOGIN_USER_.admnCode eq '301'}">
          <label for="workSts">재직 상태</label>
          <div>
            <select id="workSts" name="workSts" value="${employeeVO.workSts}">
              <option value="201">재직중</option>
              <option value="202">휴직중</option>
              <option value="203">퇴사예정</option>
              <option value="204">퇴사</option>
            </select>
          </div>
          <label for="hireYear">입사 연차</label>
          <input type="text" id="hireYear" name="hireYear" value="${employeeVO.hireYear}" disabled />

          <label for="hireDt">입사일</label>
          <input type="date" id="hireDt" name="hireDt" value="${employeeVO.hireDt}" style="width: 30rem;" />

          <label for="posiHist">직급</label>
          <input type="text" id="posiHist" name="posiHist" value="${employeeVO.commonCodeVO.cmcdName}" />

          <c:if test="${empty employeeVO.teamList}">
            <label for="noneTmName">팀</label>
            <div id="noneTmName">소속된 팀이 존재하지 않습니다.</div>
          </c:if>
          <label for="tmName">팀</label>
          <c:forEach items="${employeeVO.teamList}" var="teamList">
            <div style="margin-bottom: 0.2rem">
              <input type="text" id="tmName" id="tmName" value="${teamList.tmName}" disabled />
              <button class="delete-team" data-tmid="${teamList.tmId}">삭제</button>
            </div>
          </c:forEach>
          <div id="will-add-team" class="hidden">
            <div>추가 예정 팀</div>
          </div>

          <button id="add-team">팀 추가</button>
          <dialog class="team-modal modal">
            <div class="flex-col">
              <div>추가할 팀을 선택해주세요</div>
              <select id="add-team-select">
               
              </select>
              <div>
                <button id="add-team-final">추가</button>
                <button id="add-team-cancel">취소</button>
              </div>
            </div>
          </dialog>

          <label for="dept-select">부서</label>
            <div style="margin-bottom: 0.2rem">
              <input class="origin-dept" value="${employeeVO.departmentVO.deptName}" disabled />
              <button class="change-dept-btn">부서변경</button>
            </div>
          <div id="hidden-selectbox" class="hidden">
            <select id="dept-select" class="dept-select" data-origin="${employeeVO.deptId}">
              <c:forEach items="${departmentlist.departmentList}" var="department">
                <option value="${department.deptId}">${department.deptName}</option>
              </c:forEach>
            </select>

            <input type="text" placeholder="부서 변경 사유를 입력해주세요." id="dept-change-cmt" class="hidden" />
          </div>
        </c:when>

        
        <c:otherwise>
          <label for="workSts">재직 상태</label>
          <select id="workSts" name="workSts" value="${employeeVO.workSts}" disabled>
            <option value="201">재직중</option>
            <option value="202">휴직중</option>
            <option value="203">퇴사예정</option>
            <option value="204">퇴사</option>
          </select>
          <label for="hireYear">입사 연차</label>
          <input type="text" id="hireYear" name="hireYear" value="${employeeVO.hireYear}" disabled />

          <label for="hireDt">입사일</label>
          <input type="text" id="hireDt" name="hireDt" value="${employeeVO.hireDt}" disabled />

          <label for="posiHist">직급</label>
          <input type="text" id="posiHist" name="posiHist" value="${employeeVO.commonCodeVO.cmcdName}" disabled />

          <c:if test="${empty employeeVO.teamList}">
            <label for="noneTmName">팀</label>
            <div id="noneTmName">소속된 팀이 존재하지 않습니다.</div>
          </c:if>
          <c:forEach items="${employeeVO.teamList}" var="teamList">
            <input
              style="margin-bottom: 0.2rem"
              type="text"
              id="tmName"
              id="tmName"
              value="${teamList.tmName}"
              disabled
            />
          </c:forEach>

          <label for="dept-select">부서</label>
          <input class="origin-dept" value="${employeeVO.departmentVO.deptName}" disabled />
        </c:otherwise>
      </c:choose>

      
      <div class="btn-group">
        <div class="right-align" style="padding-top: 1rem;">
          <button type="button" class="save-modify">저장</button>
        </div>
      </div>
    </div>
   
  </body>
</html>
