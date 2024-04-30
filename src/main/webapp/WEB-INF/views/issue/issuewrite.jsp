<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>이슈 작성 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <jsp:include page="../ckeditor.jsp" />
    <script type="text/javascript" src="/js/issue/issuewrite.js"></script>
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
      action="/issue/write"
      method="post"
      enctype="multipart/form-data"
    >
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

        <label for="issue-title">이슈 제목</label>
        <div>
          <input
            type="text"
            id="issue-title"
            name="isTtl"
            value="${issueVO.isTtl}"
          />
        </div>

        <label for="file">첨부파일</label>
        <input type="file" name="file" id="file" />

        <label for="content">이슈 내용</label>
        <div class="hereCkEditor5">
          <%-- 여기가 editor 생성부 --%>
          <div class="editor" data-name="isCntnt" data-init-content=""></div>
        </div>
      </div>
    </form>

    <div class="right-align">
      <button type="button" data-type="write">등록</button>
      <button onclick="location.href='/issue'">취소</button>
    </div>
  </body>
</html>
