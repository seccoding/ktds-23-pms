<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>이슈 수정</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <jsp:include page="../ckeditor.jsp" />
    <script type="text/javascript" src="/js/issue/issuewrite.js"></script>
  </head>
  <body>
    <h1>이슈 수정</h1>
    <form action="/issue/modify/${issueVO.isId}" method="post" enctype="multipart/form-data">
      <div class="grid">
          <label for="issue-title">이슈명</label>
          <input type="text" id="issue-title" name="isTtl" value="${issueVO.isTtl}"/>

          <label for="file">첨부파일</label>
          <div>
            <input type="file" name="file" id="file" />
            현재 업로드된 파일: ${issueVO.originFileName}
          </div>

          <div>
            <label for="content">이슈 내용</label>
            <div class="hereCkEditor5">
                <%-- 여기가 editor 생성부 --%>
                <div class="editor" data-name="isCntnt"></div>
                <input
                  type="text"
                  id="is-content"
                  name="isCntnt"
                  style="visibility: hidden"
                  value="${issueVO.isCntnt}"
                />
            </div>
          </div>

          <button id="submit" type="button">저아</button>
          
      </div>
    </form>
  </body>
</html>