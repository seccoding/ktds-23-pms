<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>부서/팀 조회</title>
    <jsp:include page="../commonheader.jsp" />
    <script
      type="text/javascript"
      src="/js/department/departmentlist.js"
    ></script>
    <style>
      html {
        width: 100%;
        height: 100%;
      }
      .hidden {
        display: none;
      }
      .create-modal {
        outline: none;
        position: absolute;

        border-radius: 20px;
        border: 0;
        box-shadow: 0px 8px 15px 0px rgba(0, 0, 0, 0.1);
        transition: box-shadow 0.3s ease;
        justify-content: center;
        top: 30%;
        left: 30%;

        width: 30%;
        height: 45%;
        padding: 2.5rem;

        background-color: rgba(F, F, F, 0.8);
      }
      .create-modal-team {
        position: absolute;

        border-radius: 20px;
        border: 0;
        box-shadow: 0px 8px 15px 0px rgba(0, 0, 0, 0.1);
        transition: box-shadow 0.3s ease;
        justify-content: center;
        top: 25%;
        left: 30%;

        width: 30%;
        height: 50%;
        padding: 2rem;

        background-color: rgba(F, F, F, 0.8);
      }
      .modify-modal-dept {
        position: absolute;

        border-radius: 20px;
        border: 0;
        box-shadow: 0px 8px 15px 0px rgba(0, 0, 0, 0.1);
        transition: box-shadow 0.3s ease;
        justify-content: center;
        top: 20%;
        left: 30%;

        width: 30%;
        height: 60%;
        padding: 2rem;

        background-color: rgba(F, F, F, 0.8);
      }
      .modify-modal-team {
        position: absolute;

        border-radius: 20px;
        border: 0;
        box-shadow: 0px 8px 15px 0px rgba(0, 0, 0, 0.1);
        transition: box-shadow 0.3s ease;
        justify-content: center;
        top: 17.5%;
        left: 30%;

        width: 30%;
        height: 65%;
        padding: 2rem;

        background-color: rgba(F, F, F, 0.8);
      }
      h4 {
        text-align: center;
        margin-bottom: 2rem;
      }
      .dept-list-modal > .grid {
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
        justify-content: space-between;
      }

      .button {
        width: 5rem;
        height: 2rem;
        color: #fff;
        border: 0px;
        font-weight: bold;
        border-radius: 0.25rem;
        background-color: var(--btn-color);
        margin: 0 0.5rem;
      }
      .dept-list-modal > .grid > input,
      .dept-list-modal > .grid > div,
      .dept-list-modal > .grid > select,
      .dept-list-modal > .grid > p {
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
      .employee-info-enter {
        margin-top: 1.9rem;
        width: 7rem;
      }
      .margin-seperate {
        padding: 1rem;
        margin-top: 1rem;
        background-color: var(--box-bg);
      }
    </style>
  </head>
  <body>
    <jsp:include page="../commonmodal.jsp" />
    <jsp:include page="../commonemployeelist.jsp" />

    <dialog class="create-modal dept-list-modal">
      <h4>부서 등록</h4>

      <div class="grid">
        <div class="grid-item">부서명</div>
        <input id="department-name" type="text" class="grid-item" />
        <div class="grid-item">부서장ID</div>
        <select id="department-leader" class="grid-item">
          <c:forEach items="${empList}" var="item">
            <option value="${item.empId}">
              ${item.empId} (${item.empName})
            </option>
          </c:forEach>
        </select>
        <!-- <input id="department-leader" type="text" class="grid-item" /> -->
      </div>
      <div class="flex" style="justify-content: flex-end">
        <input class="button dep-submit-button" type="button" value="등록" />
        <input
          class="button"
          id="dep-cancel-button"
          type="button"
          value="취소"
        />
      </div>
    </dialog>

    <dialog class="create-modal-team dept-list-modal">
      <h4>팀 등록</h4>
      <div class="grid">
        <div class="grid-item">팀명</div>
        <input id="team-name" type="text" class="grid-item" />
        <div class="grid-item">팀장ID</div>
        <select id="team-leader" class="grid-item"></select>
        <!-- <input id="team-leader" type="text" class="grid-item" /> -->
        <div class="grid-item">담당부서ID</div>
        <select
          class="grid-item department-selectbox"
          id="department-selectbox"
        >
          <c:forEach
            items="${onlyDepartmentList.departmentList}"
            var="department"
          >
            <option class="enter-data-id" value="${department.deptId}">
              ${department.deptName}
            </option>
          </c:forEach>
        </select>
      </div>
      <div class="flex" style="justify-content: flex-end">
        <input class="button team-submit-button" type="button" value="등록" />
        <input
          class="button"
          id="team-cancel-button"
          type="button"
          value="취소"
        />
      </div>
    </dialog>

    <dialog class="modify-modal-dept dept-list-modal">
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
        <select id="department-leader-mod" class="grid-item"></select>
        <!-- <input id="department-leader-mod" type="text" class="grid-item" /> -->
      </div>

      <div class="flex" style="justify-content: flex-end">
        <input
          class="button"
          id="dep-modify-submit-button"
          type="button"
          value="수정"
        />
        <input
          class="button"
          id="dep-modify-cancel-button"
          type="button"
          value="취소"
        />
      </div>
    </dialog>
    <dialog class="modify-modal-team dept-list-modal">
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
        <select id="team-leader-mod" class="grid-item"></select>
        <!-- <input id="team-leader-mod" type="text" class="grid-item" /> -->
        <div class="grid-item">담당부서ID</div>
        <input id="team-dept-mod" type="text" class="grid-item" />
      </div>

      <div class="flex" style="justify-content: flex-end">
        <input
          class="button"
          id="tm-modify-submit-button"
          type="button"
          value="수정"
        />
        <input
          class="button"
          id="tm-modify-cancel-button"
          type="button"
          value="취소"
        />
      </div>
    </dialog>

    <div
      class="grid"
      data-gap="0.5rem"
      data-grid-columns="1fr 1fr 1fr"
      data-grid-rows="35rem 20rem"
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
                  <td>${department.deptId}</td>
                  <td>${department.deptName}</td>
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
            <colgroup>
              <col style="width: 50%" />
              <col style="width: 50%" />
            </colgroup>
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
            <colgroup>
              <col style="width: 50%" />
              <col style="width: 50%" />
            </colgroup>
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

      <div class="overflow-scroll margin-seperate">
        <h4>부서 정보</h4>
        <div
          class="grid code-info"
          data-grid-columns="1fr 1fr"
          data-grid-rows="1fr 1fr 1fr 1fr 1fr"
        >
          <label for="codeDeptId">부서 ID</label>
          <div id="codeDeptId"></div>
          <label for="codeDeptName">부서명</label>
          <div id="codeDeptName"></div>

          <label for="codeDeptCrtDt">생성일</label>
          <div id="codeDeptCrtDt"></div>
          <label for="codeDeptLeadId">부서장 ID</label>
          <div data-columns="2 / -1" id="codeDeptLeadId"></div>
        </div>

        <c:if test="${sessionScope._LOGIN_USER_.admnCode eq '301'}">
          <div
            data-columns="1 / -1"
            style="text-align: right; margin-top: 1rem"
          >
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
            <input
              type="button"
              class="department-delete-button button"
              value="부서 삭제"
            />
          </div>
        </c:if>
      </div>
      <div class="overflow-scroll margin-seperate">
        <h4>팀 정보</h4>
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
        </div>
        <c:if test="${sessionScope._LOGIN_USER_.admnCode eq '301'}">
          <div
            data-columns="1 / -1"
            style="text-align: right; margin-top: 1.5rem"
          >
            <input
              type="button"
              class="emp-team-create button hidden"
              value="사원 등록"
            />
            <div
              id="special-hidden-datalist"
              class="special-hidden-datalist hidden"
            ></div>
            <input type="button" class="team-create button" value="팀 등록" />
            <input type="button" class="team-modify button" value="팀 변경" />
            <input type="button" class="team-delete button" value="팀 삭제" />
          </div>
        </c:if>
      </div>
      <div class="overflow-scroll margin-seperate">
        <h4>사원 정보</h4>
        <div class="grid" style="display: flex" data-grid-columns="1fr 3fr">
          <div>
            <img
              src="/images/login.png"
              id="profile"
              alt="profile"
              width="120"
              height="160"
              style="margin-right: 0.5rem"
            />
          </div>
          <div
            class="grid code-info sub-code-info sub-sub-code-info"
            data-grid-columns=" 1fr 1fr"
            data-grid-rows="1fr 1fr 1fr 1fr"
          >
            <label for="codeEmpId">사원 ID</label>
            <div id="codeEmpId"></div>
            <label for="codeEmpName">사원명</label>
            <div id="codeEmpName"></div>
            <label for="codeEmpPstn">직급</label>
            <div id="codeEmpPstn"></div>

            <label for="codeEmpEmail">이메일</label>
            <div data-columns="2 / -1" id="codeEmpEmail"></div>
            <label for="codeEmpCntct">전화번호</label>
            <div data-columns="2 / -1" id="codeEmpCntct"></div>
          </div>
        </div>
        <div data-columns="1 / -1" style="text-align: right">
          <input
            type="button"
            class="employee-info-enter button hidden"
            value="상세 정보 보기"
          />
        </div>
      </div>
    </div>
  </body>
</html>
