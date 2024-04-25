<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>지식관리 수정 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <jsp:include page="../ckeditor.jsp" />
    <script type="text/javascript" src="/js/knowledgemodify.js"></script>
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

      <h1>지식관리 수정</h1>
      <form
        action="/ajax/knowledge/modify/${knowledgeVO.knlId}"
        method="post"
        enctype="multipart/form-data"
      >
        <div class="grid">
          <label for="title">제목</label>
          <input
            id="title"
            type="text"
            name="knlTtl"
            value="${knowledgeVO.knlTtl}"
          />

          <label for="file">첨부파일</label>
          <div>
            <input type="file" name="file" id="file" />
            현재 업로드 된 파일: ${knowledgeVO.originFileName}
          </div>

          <!-- ckeditor -->
          <label for="knl-cntnt">내용</label>
          <div class="hereCkEditor5">
            <%-- editor 생성부 --%>
            <div class="editor" data-name="knlCntnt"></div>
          </div>

          <div class="btn-group">
            <div class="right-align">
              <input type="submit" value="저장" />
            </div>
          </div>
        </div>
      </form>
    </body>
  </head>
</html>
