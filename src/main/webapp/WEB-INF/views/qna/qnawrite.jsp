<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Qna 작성 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <jsp:include page="../ckeditor.jsp" />
    <script type="text/javascript" src="/js/qnawrite.js"></script>
    <script type="text/javascript">
      window.onload = function () {
        var editors = loadEditor(
          ".editor",
          "내용을 입력하세요.",
          "${qna.qaCntnt}"
        );
        var qaCntnt = "";

        $("button").on("click", function (event) {
          event.preventDefault();

          qaCntnt = editors.getData();

          $("#qa-cntnt").val(qaCntnt);

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

      <h1>Qna 작성</h1>
      <form
        id="writeForm"
        action="/ajax/qna/write"
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
          <input id="qaTtl" type="text" name="qaTtl" value="${qnaVO.qaTtl}" />

          <label for="file">첨부파일</label>
          <input type="file" name="file" id="file" />

          <!-- ckeditor -->
          <label for="qa-cntnt">내용</label>
          <div class="hereCkEditor5">
            <%-- editor 생성부 --%>
            <div class="editor" data-name="qaCntnt"></div>
            <input
              type="text"
              id="qaCntnt"
              name="qaCntnt"
              style="visibility: hidden"
            />
          </div>
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
