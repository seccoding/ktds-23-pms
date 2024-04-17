<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>지식관리 작성 페이지</title>
    \
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/modal.js"></script>
    <script type="text/javascript" src="/js/knowledgewrite.js"></script>
    <style type="text/css">
      /* div인데 클래스가 grid 인 것 */
      div.grid {
        display: grid;
        grid-template-columns: 80px 1fr;
        grid-template-rows: 28px 28px 320px 1fr;
        row-gap: 10px;
      }
    </style>
    <body>
      <jsp:include page="../layout/layout.jsp" />

      <c:if test="${not empty errorMessage}">
        <dialog class="alert-dialog">
          <h1>${errorMessage}</h1>
        </dialog>
      </c:if>

      <h1>지식관리 작성</h1>
      <form
        action="/knowledge/write"
        method="post"
        enctype="multipart/form-data"
      >
        <!-- 요구사항 ID 선택창 -->
        <div>
          <label for="=rqm-id">요구사항제목</label>
          <select name="rqmId" id="rqm-id">
            <c:forEach items="${requirement}" var="requirement">
              <option value="${requirement.rqmId}">
                ${requirement.rqmTtl}
              </option>
            </c:forEach>
          </select>
        </div>

        <div class="grid">
          <label for="title">제목</label>
          <input
            id="knlTtl"
            type="text"
            name="knlTtl"
            value="${knowledgeVO.knlTtl}"
          />

          <label for="file">첨부파일</label>
          <input type="file" name="file" id="file" />

          <label for="content">내용</label>
          <textarea id="knlCntnt" name="knlCntnt" style="height: 300px">
  ${knowledgeVO.knlCntnt}</textarea
          >

          <div class="btn-group">
            <div class="right-align">
              <input type="submit" value="저장" />
            </div>
          </div>
        </div>
      </form>
      <jsp:include page="../layout/layout_close.jsp" />
    </body>
  </head>
</html>
