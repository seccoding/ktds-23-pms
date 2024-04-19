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
        top: 30%;
        left: 30%;

        width: 40%;
        height: 45%;
        padding: 2rem;

        background-color: rgba(F, F, F, 0.8);
      }
      .modify-modal-dept,
      .modify-modal-team {
        position: absolute;

        justify-content: center;
        top: 20%;
        left: 30%;

        width: 40%;
        height: 55%;
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
      div,
      p {
        align-items: center;
        text-align: left;
        margin-top: 2rem;
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
        <div class="grid-item">담당부서ID</div>
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

    <dialog class="modify-modal-dept">
      <select id="modify-select-box">
        <c:forEach
          items="${onlyDepartmentList.departmentList}"
          var="department"
        >
          <option class="modify-data-id" value="${department.deptId}">
            ${department.deptName}
          </option>
        </c:forEach>
      </select>
      <div class="grid">
        <p>부서 ID</p>
        <p id="mod-dept-id"></p>
        <div class="grid-item">부서명</div>
        <input id="department-name-mod" type="text" class="grid-item" />
        <p>부서생성날짜</p>
        <p id="mod-dept-crd-dt"></p>
        <div class="grid-item">부서장ID</div>
        <input id="department-leader-mod" type="text" class="grid-item" />
      </div>

      <div class="flex">
        <input
          class="button"
          id="dep-modify-cancel-button"
          type="button"
          value="취소"
        />
        <input
          class="button"
          id="dep-modify-submit-button"
          type="button"
          value="수정"
        />
      </div>
    </dialog>
    <dialog class="modify-modal-team">
      <select id="modify-team-select-box">
        <c:forEach items="${onlyTeamList.teamList}" var="team">
          <option class="modify-team-data-id" value="${team.tmId}">
            ${team.tmName}
          </option>
        </c:forEach>
      </select>
      <div class="grid">
        <p>팀 ID</p>
        <p id="mod-team-id"></p>
        <div class="grid-item">팀명</div>
        <input id="team-name-mod" type="text" class="grid-item" />
        <p>팀생성날짜</p>
        <p id="mod-team-crd-dt"></p>
        <div class="grid-item">팀장ID</div>
        <input id="team-leader-mod" type="text" class="grid-item" />
        <div class="grid-item">담당부서ID</div>
        <input id="team-dept-mod" type="text" class="grid-item" />
      </div>

      <div class="flex">
        <input
          class="button"
          id="tm-modify-cancel-button"
          type="button"
          value="취소"
        />
        <input
          class="button"
          id="tm-modify-submit-button"
          type="button"
          value="수정"
        />
      </div>
    </dialog>

    <h1>부서/팀 조회</h1>
    <div
      class="grid"
      data-gap="0.5rem"
      data-grid-columns="1fr 1fr 1fr"
      data-grid-rows="30.7rem 17rem"
    >
      <div class="overflow-scroll">
        <h4 class="fixed" style="background-color: var(--body-bg)">부서</h4>
        <div>
          <table class="fit-parent find-department">
            <thead
              class="fixed"
              data-fixed-top="1.52rem"
              style="background-color: var(--body-bg)"
            >
              <tr>
                <th>부서ID</th>
                <th>부서명</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${departmentList}" var="department">
                <tr
                  class="departmentListClickFunction"
                  data-dept-id="${department.deptId}"
                  data-dept-name="${department.deptName}"
                  data-dept-crdt="${department.deptCrDt}"
                  data-dept-lead-id="${department.deptLeadId}"
                >
                  <td>${departmnet.deptId}</td>
                  <td>${departmnet.deptName}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
      <div class="overflow-scroll">
        <h4 class="fixed" style="background-color: var(--body-bg)">팀</h4>
        <div>
          <table class="fit-parent sub-team">
            <thead
              class="fixed"
              data-fixed-top="1.52rem"
              style="background-color: var(--body-bg)"
            >
              <tr>
                <th>팀ID</th>
                <th>팀명</th>
              </tr>
            </thead>
            <tbody></tbody>
          </table>
        </div>
      </div>
      <div class="overflow-scroll">
        <h4 class="fixed" style="background-color: var(--body-bg)">사원</h4>
        <div>
          <table class="fit-parent sub-sub-employee">
            <thead
              class="fixed"
              data-fixed-top="1.52rem"
              style="background-color: var(--body-bg)"
            >
              <tr>
                <th>사원ID</th>
                <th>사원명</th>
              </tr>
            </thead>
            <tbody></tbody>
          </table>
        </div>
      </div>

      <div class="overflow-scroll">
        <h4 style="background-color: var(--body-bg)">부서 정보</h4>
        <div
          class="grid code-info"
          data-grid-columns="1fr 1fr"
          data-grid-rows="1fr 1fr 1fr 1fr 1fr"
        >
          <label for="codeDeptId">부서 ID</label>
          <div id="codeDeptId"></div>
          <label for="codeDeptName">부서명</label>
          <div id="codeDeptName"></div>

          <label for="codeDeptLeadId">부서장 ID</label>
          <div data-columns="2 / -1" id="codeDeptLeadId"></div>

          <label for="codeDeptCrtDt">생성일</label>
          <div id="codeDeptCrtDt"></div>

          <div data-columns="1 / -1" style="text-align: right">
            <input
              type="button"
              class="department-create button"
              value="부서 등록"
            />
            <input
              type="button"
              class="department-modify button"
              value="부서 변경"
            />
          </div>
        </div>
      </div>
      <div class="overflow-scroll">
        <h4 style="background-color: var(--body-bg)">팀 정보</h4>
        <div
          class="grid code-info sub-code-info"
          data-grid-columns="1fr 1fr"
          data-grid-rows="1fr 1fr 1fr 1fr 1fr"
        >
          <label for="codeTmId">팀 ID</label>
          <div id="codeTmId"></div>
          <label for="codeTmName">팀 명</label>
          <div id="codeTmName"></div>

          <label for="codeTmDepartment">담당 부서 ID</label>
          <div data-columns="2 / -1" id="codeTmDepartment"></div>
          <label for="codeTmLeadId">팀장 ID</label>
          <div data-columns="2 / -1" id="codeTmLeadId"></div>

          <label for="codeTmCrtDt">생성일</label>
          <div id="codeTmCrtDt"></div>

          <div data-columns="1 / -1" style="text-align: right">
            <input type="button" class="team-create button" value="팀 등록" />
            <input type="button" class="team-modify button" value="팀 변경" />
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
