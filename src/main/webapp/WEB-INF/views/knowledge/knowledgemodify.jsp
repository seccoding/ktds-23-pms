<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>지식관리 수정 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <jsp:include page="../ckeditor.jsp" />
    <script
      type="text/javascript"
      src="/js/knowledge/knowledgewrite.js"
    ></script>

    <body>
      <h1>지식관리 수정</h1>
      <form
        id="writeForm"
        action="/ajax/knowledge/modify/${knowledgeVO.knlId}"
        method="post"
        enctype="multipart/form-data"
      >
        <div>
          <label for="title">지식 제목</label>
          <input
            id="knlTtl"
            type="text"
            name="knlTtl"
            value="${knowledgeVO.knlTtl}"
          />
        </div>

        <div>
          <label for="file">첨부파일</label>
          <input type="file" name="file" id="file" />
          현재 업로드 된 파일: ${knowledgeVO.originFileName}
        </div>

        <div>
          <!-- ckeditor -->
          <label for="knl-cntnt">내용</label>
          <div class="hereCkEditor5">
            <%-- editor 생성부 --%>
            <div class="editor" data-name="knlCntnt"></div>
            <input
              type="text"
              id="knlCntnt"
              name="knlCntnt"
              style="visibility: hidden"
            />
          </div>
        </div>
      </form>
      <div>
        <button id="submit-btn" type="button" data-type="modify">저장</button>
      </div>
    </body>
  </head>
</html>
