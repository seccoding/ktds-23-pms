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
    <style>
      #modify-btn {
        text-align: right;
      }
    </style>
  </head>
  <body>
    <form
      id="writeForm"
      action="/issue/modify/?isId=${issueVO.isId}"
      method="post"
      enctype="multipart/form-data"
    >
      <div class="grid" data-id="${issueVO.isId}">
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
                <div 
                  class="editor" 
                  data-name="isCntnt"
                  data-init-content="${issueVO.isCntnt}">
                </div>
            </div>
          </div>
    </form>
      <div id="modify-btn">
        <button type="button" data-type="modify">수정</button>
      </div>  
  </body>
</html>