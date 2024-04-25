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
    </style>
  </head>
  <body>
    <jsp:include page="../commonemployeelist.jsp" />
    <h1>쪽지 쓰기</h1>
    <form id="writeForm" action="/memo/write" method="post">
      <div class="grid">
        <div class="memowrite-content">
          <label for="rcvId">받는사람</label>
          <input
            id="rcvId"
            type="text"
            name="rcvId"
            value="${memoVO.rcvId}"
            placeholder="여러 명은 쉼표(,)로 구분해주세요."
          />
          <div id="special-hidden-datalist" style="display: none"></div>
          <button class="address">주소록</button>
          <c:forEach items="${errorMessage.rcvId}" var="error">
            <div>${error}</div>
          </c:forEach>
        </div>
        <div class="memowrite-content">
          <label for="memoTtl">제목 </label>
          <c:forEach items="${errorMessage.memoTtl}" var="error">
            <div>${error}</div>
          </c:forEach>
          <input
            id="memoTtl"
            type="text"
            name="memoTtl"
            value="${memoVO.memoTtl}"
            placeholder="제목을 입력해주세요."
          />
        </div>
        <label for="memoCntnt">내용</label>
        <c:forEach items="${errorMessage.memoCntnt}" var="error">
          <div>${error}</div>
        </c:forEach>
        <textarea
          name="memoCntnt"
          id="memoCntnt"
          value="${memoVO.memoCntnt}"
          cols="30"
          rows="30"
        ></textarea>
        <button type="button" id="sendButton">보내기</button>
      </div>
    </form>
  </body>
</html>
