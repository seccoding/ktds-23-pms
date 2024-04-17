<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>부서/팀 조회</title>
    <jsp:include page="../commonheader.jsp" />
    <style>
      .create-modal,
      .create-modal-team {
        position: absolute;

        justify-content: center;
        top: 40%;
        left: 30%;

        width: 40%;
        height: 40%;
        padding: 2rem;

        background-color: rgba(F, F, F, 0.8);
      }
      h4 {
        text-align: center;
        margin-bottom: 2rem;
      }
      .grid {
        display: grid;
        grid-template-columns: 0.5fr 1fr;
        grid-template-rows: 1fr 1fr;
        margin-bottom: 6rem;
      }
      .grid-team {
        display: grid;
        grid-template-columns: 0.5fr 1fr;
        grid-template-rows: 1fr 1fr 1fr;
        margin-bottom: 6rem;
      }
      .flex {
        display: flex;
        flex-direction: row-reverse;
      }
      .button {
        width: 5rem;
        height: 2rem;
        color: #fff;
        border: 0px;
        font-weight: bold;
        border-radius: 0.25rem;
        background-color: var(--btn-color);
        margin-left: 1rem;
        margin-right: 2rem;
      }
      .grid > input,
      div {
        align-items: center;
        text-align: center;
        margin-top: 1rem;
      }
      .table-div {
        padding: 1rem;
        margin: 1rem;
        border-radius: 1rem;
        border: 1px solid #bbb;

        max-height: 25rem;
        overflow-y: scroll;
      }
    </style>
    <script
      type="text/javascript"
      src="/js/department/departmentlist.js"
    ></script>
  </head>
  <body>
    <dialog class="create-modal">
      <h4>부서 등록</h4>
      <div class="grid">
        <div class="grid-item">부서명</div>
        <input id="depratment-name" type="text" class="grid-item" />
        <div class="grid-item">부서장ID</div>
        <input id="depratment-leader" type="text" class="grid-item" />
      </div>
      <div class="flex">
        <input
          class="button"
          id="dep-cancel-button"
          type="button"
          value="취소"
        />
        <input class="button dep-submit-button" type="button" value="등록" />
      </div>
    </dialog>

    <dialog class="create-modal-team">
      <h4>팀 등록</h4>
      <div class="grid">
        <div class="grid-item">팀명</div>
        <input id="team-name" type="text" class="grid-item" />
        <div class="grid-item">팀장ID</div>
        <input id="team-leader" type="text" class="grid-item" />
        <div class="grid-item">담당부서</div>
        <input id="team-department" type="text" class="grid-item" />
      </div>
      <div class="flex">
        <input
          class="button"
          id="team-cancel-button"
          type="button"
          value="취소"
        />
        <input class="button team-submit-button" type="button" value="등록" />
      </div>
    </dialog>
    <h1>부서/팀 조회</h1>
    <div class="table-div">
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
    <input type="button" class="department-create button" value="부서 등록" />
    <input type="button" class="team-create button" value="팀 등록" />
  </body>
</html>
