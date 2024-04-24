<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>지식관리 작성 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <jsp:include page="../ckeditor.jsp" />
    <script
      type="text/javascript"
      src="/js/knowledge/knowledgewrite.js"
    ></script>
    <script type="text/javascript">
      window.onload = function () {
        var editors = loadEditor(
          ".editor",
          "내용을 입력하세요.",
          "${knowledge.knlCntnt}"
        );
        var knlCntnt = "";

        $("button").on("click", function (event) {
          event.preventDefault();

          knlCntnt = editors.getData();

          $("#knl-cntnt").val(knlCntnt);

          $("#writeForm").submit();
        });
      };
    </script>
    <body>
      <c:if test="${not empty errorMessage}">
        <dialog class="alert-dialog">
          <h1>${errorMessage}</h1>
        </dialog>
      </c:if>

      <h1>지식관리 작성</h1>
      <form
        id="writeForm"
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

          <!-- ckeditor -->
          <label for="knl-cntnt">내용</label>
          <div class="hereCkEditor5">
            <%-- editor 생성부 --%>
            <div class="editor" data-name="knlCntnt"></div>
          </div>
        </div>
      </form>
      <div class="btn-group">
        <div class="right-align">
          <button id="submit-btn" type="button">저장</button>
        </div>
      </div>
    </body>
  </head>
</html>
