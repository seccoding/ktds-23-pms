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
  </head>
  <body>
    <h1>지식관리 작성</h1>
    <form
      id="writeForm"
      action="/knowledge/write"
      method="post"
      enctype="multipart/form-data"
    >
      <!-- 요구사항 ID 선택창 -->
      <div class="grid">
        <label for="=rqm-id">요구사항</label>
        <div>
          <select name="rqmId" id="rqm-id">
            <option value="">요구사항을 선택해주세요</option>
            <c:forEach items="${requirement}" var="requirement">
              <option value="${requirement.rqmId}">
                ${requirement.rqmTtl}
              </option>
            </c:forEach>
          </select>
        </div>

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
        <button id="submit-btn" type="button" data-type="write">저장</button>
      </div>
    </form>
  </body>
</html>
