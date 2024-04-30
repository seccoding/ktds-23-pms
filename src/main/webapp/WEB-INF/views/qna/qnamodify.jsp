<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Qna 수정 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <jsp:include page="../ckeditor.jsp" />
    <script type="text/javascript" src="/js/qna/qnawrite.js"></script>
  </head>
  <style>
    #modify-btn {
      text-align: right;
    }
  </style>
  <body>
    <form
      action="/ajax/qna/modify/${qnaVO.qaId}"
      method="put"
      enctype="multipart/form-data"
    >
      <div class="grid">
        <label for="title">제목</label>
        <input id="qaTtl" type="text" name="qaTtl" value="${qnaVO.qaTtl}" />

        <label for="file">첨부파일</label>
        <div>
          <input type="file" name="file" id="file" />
          현재 업로드 된 파일: ${qnaVO.originFileName}
        </div>

        <div>
          <!-- ckeditor -->
          <label for="qa-cntnt">내용</label>
          <div class="hereCkEditor5">
            <%-- editor 생성부 --%>
            <div
              class="editor"
              data-name="qaCntnt"
              data-init-content="${qnaVO.qaCntnt}"
            ></div>
          </div>
        </div>
      </div>
    </form>
    <div id="modify-btn">
      <button
        id="submit-btn"
        data-type="modify"
        type="button"
        data-key="${qnaVO.qaId}"
      >
        수정
      </button>
    </div>
  </body>
</html>
