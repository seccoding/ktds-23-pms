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
    <style>
      .grid {
        display: grid;
        grid-template-columns: 200px 1fr;
        grid-template-rows: repeat(3, 40px) 1fr;
      }
      .right-align {
        text-align: right;
      }
      .error {
        display: inline-block;
        margin-left: 20px;
      }
    </style>
  </head>
  <body>
    <form
      id="writeForm"
      action="/knowledge/write"
      method="post"
      enctype="multipart/form-data"
    >
      <!-- 요구사항 ID 선택창 -->
      <div class="grid">
        <label for="rqm-id">요구사항</label>
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

        <label for="title">지식 제목</label>
        <div>
          <input
            id="knlTtl"
            type="text"
            name="knlTtl"
            value="${knowledgeVO.knlTtl}"
          />
        </div>

        <label for="file">첨부파일</label>
        <input type="file" name="file" id="file" />

        <label for="knl-cntnt">지식 내용</label>
        <!-- ckeditor -->
        <div class="hereCkEditor5">
          <%-- editor 생성부 --%>
          <div class="editor" data-name="knlCntnt" data-init-content=""></div>
        </div>
      </div>
    </form>

    <div class="right-align">
      <button id="submit-btn" type="button" data-type="write">등록</button>
      <button onclick="location.href='/knowledge'">취소</button>
    </div>
  </body>
</html>
