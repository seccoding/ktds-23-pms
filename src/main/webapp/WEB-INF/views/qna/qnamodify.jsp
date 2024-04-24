<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Qna 수정 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/qnamodify.js"></script>
    <body>
      <c:if test="${not empty errorMessage}">
        <dialog class="alert-dialog">
          <h1>${errorMessage}</h1>
        </dialog>
      </c:if>

      <h1>Qna 수정</h1>
      <form
        action="/qna/modify/${qnaVO.qaId}"
        method="post"
        enctype="multipart/form-data"
      >
        <div class="grid">
          <label for="title">제목</label>
          <input id="title" type="text" name="qaTtl" value="${qnaVO.qaTtl}" />

          <label for="file">첨부파일</label>
          <div>
            <input type="file" name="file" id="file" />
            현재 업로드 된 파일: ${qnaVO.originFileName}
          </div>

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
      </form>
      <div class="btn-group">
        <div class="right-align">
          <button id="submit-btn" type="button">저장</button>
        </div>
    </body>
  </head>
</html>
