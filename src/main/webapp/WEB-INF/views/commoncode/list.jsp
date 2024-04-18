<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <jsp:include page="../commonheader.jsp" />
    <script type="text/javascript" src="/js/commoncode/list.js"></script>
  </head>
  <body>
    <div
      class="grid"
      data-gap="0.5rem"
      data-grid-columns="1fr 1fr"
      data-grid-rows="30.7rem 17rem"
    >
      <div class="overflow-scroll">
        <h4 class="fixed" style="background-color: var(--body-bg)">대분류</h4>
        <div>
          <table class="fit-parent common-code">
            <thead
              class="fixed"
              data-fixed-top="1.52rem"
              style="background-color: var(--body-bg)"
            >
              <tr>
                <th>Code ID</th>
                <th>Code Name</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${mainCodeList}" var="mainCode">
                <tr
                  data-id="${mainCode.cmcdId}"
                  data-pid="${mainCode.cmcdPid}"
                  data-code-name="${mainCode.cmcdName}"
                  data-crt-dt="${mainCode.crtDt}"
                  data-crtr-id="${mainCode.crtrId}"
                  data-mdf-dt="${mainCode.mdfDt}"
                  data-mdfr-id="${mainCode.mdfrId}"
                >
                  <td>${mainCode.cmcdId}</td>
                  <td>${mainCode.cmcdName}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
      <div class="overflow-scroll">
        <h4 class="fixed" style="background-color: var(--body-bg)">중분류</h4>
        <div>
          <table class="fit-parent sub-common-code">
            <thead
              class="fixed"
              data-fixed-top="1.52rem"
              style="background-color: var(--body-bg)"
            >
              <tr>
                <th>Code ID</th>
                <th>Code Name</th>
              </tr>
            </thead>
            <tbody></tbody>
          </table>
        </div>
      </div>

      <div class="overflow-scroll">
        <h4 style="background-color: var(--body-bg)">코드 정보</h4>
        <div
          class="grid code-info"
          data-grid-columns="95px 1fr 95px 1fr"
          data-grid-rows="1fr 1fr 1fr 1fr 1fr"
        >
          <label for="cmcdId">Code ID</label>
          <input type="text" name="cmcdId" id="cmcdId" />
          <label for="cmcdPid">Code PID</label>
          <input type="text" disabled name="cmcdPid" id="cmcdPid" />

          <label for="cmcdName">Code Name</label>
          <input
            data-columns="2 / -1"
            type="text"
            name="cmcdName"
            id="cmcdName"
          />

          <label for="crtDt">생성일</label>
          <div id="crtDt"></div>
          <label for="crtrId">생성자</label>
          <div id="crtrId"></div>

          <label for="mdfDt">수정일</label>
          <div id="mdfDt"></div>
          <label for="mdfrId">수정자</label>
          <div id="mdfrId"></div>

          <div data-columns="1 / -1" style="text-align: right">
            <button type="button" id="modifyCode">수정</button>
            <button type="button" id="delCode">삭제</button>
            <button type="button" id="newCode" data-mode="new">신규</button>
          </div>
        </div>
      </div>

      <div class="overflow-scroll">
        <h4 style="background-color: var(--body-bg)">코드 정보</h4>
        <div
          class="grid code-info sub-code-info"
          data-grid-columns="95px 1fr 95px 1fr"
          data-grid-rows="1fr 1fr 1fr 1fr 1fr"
        >
          <label for="subCmcdId">Code ID</label>
          <input type="text" name="cmcdId" id="subCmcdId" />
          <label for="subCmcdPid">Code PID</label>
          <input type="text" disabled name="cmcdPid" id="subCmcdPid" />

          <label for="subCmcdName">Code Name</label>
          <input
            data-columns="2 / -1"
            type="text"
            name="cmcdName"
            id="subCmcdName"
          />

          <label for="subCrtDt">생성일</label>
          <div id="subCrtDt"></div>
          <label for="subCrtrId">생성자</label>
          <div id="subCrtrId"></div>

          <label for="subMdfDt">수정일</label>
          <div id="subMdfDt"></div>
          <label for="subMdfrId">수정자</label>
          <div id="subMdfrId"></div>

          <div data-columns="1 / -1" style="text-align: right">
            <button type="button" id="modifySubCode">수정</button>
            <button type="button" id="delSubCode">삭제</button>
            <button type="button" id="newSubCode" data-mode="new">신규</button>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
