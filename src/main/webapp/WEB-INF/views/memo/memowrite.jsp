<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>쪽지 쓰기</title>
    <jsp:include page="../commonheader.jsp" />
    <style>
      .ck-editor__editable_inline {
        min-height: 500px;
      }
    </style>
    <script type="text/javascript" src="/js/memo/memowrite.js"></script>

    <style>
      .memowrite-content {
        margin-bottom: 1rem;
      }
      input[type="text"] {
        width: 30rem;
      }
      .error-color {
        color: #f00;
      }
      .input-form {
        padding: 20px;
        border-radius: 4px;
        border: 1px solid #ccc;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        width: 80%;
        max-width: 600px;
        margin: 40px auto;
      }

      .form-row {
        margin-bottom: 10px;
      }
      .form-row-content {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
      }
      .form-row label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
      }
      .top-mz {
        margin-top: 10px;
        text-align: right;
      }
      textarea {
        resize: none;
      }
    </style>
  </head>
  <body>
    <jsp:include page="../commonemployeelist.jsp" />
    <div class="input-form overflow-scroll">
      <h1>쪽지 쓰기</h1>
      <form id="writeForm" action="/memo/write" method="post">
        <div class="grid">
          <div class="memowrite-content">
            <div class="form-row">
              <label for="rcvId">받는사람</label>
              <c:forEach items="${errorMessage.rcvId}" var="error">
                <div class="error-color">${error}</div>
              </c:forEach>
              <input
                id="rcvId"
                type="text"
                name="rcvId"
                value="${memoVO.rcvId}"
                placeholder="여러 명은 쉼표(,)로 구분해주세요."
              />
              <div id="special-super-datalist" style="display: none"></div>
              <button class="address">주소록</button>
            </div>
          </div>
          <div class="memowrite-content">
            <div class="form-row">
              <label for="memoTtl">제목 </label>
              <c:forEach items="${errorMessage.memoTtl}" var="error">
                <div class="error-color">${error}</div>
              </c:forEach>
              <input
                id="memoTtl"
                type="text"
                name="memoTtl"
                value="${memoVO.memoTtl}"
                placeholder="제목을 입력해주세요."
              />
            </div>
          </div>
          <div class="form-row-content">
            <label for="memoCntnt">내용</label>
          </div>
          <c:forEach items="${errorMessage.memoCntnt}" var="error">
            <div class="error-color">${error}</div>
          </c:forEach>
          <div id="memocntnt-replace" style="display: none"></div>
          <textarea
            name="memoCntnt"
            id="memoCntnt"
            cols="30"
            rows="18"
            placeholder="쪽지 내용을 입력해주세요."
          >
<c:if test="${not empty memoVO.memoCntnt}">${memoVO.memoCntnt}</c:if></textarea
          >

          <div class="form-row top-mz">
            <button type="button" id="sendButton">보내기</button>
          </div>
        </div>
      </form>
    </div>
  </body>
</html>
