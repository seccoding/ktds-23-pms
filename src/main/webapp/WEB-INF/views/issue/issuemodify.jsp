<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>이슈 수정</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <style type="text/css">
    </style>
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

        <label for="content">내용</label>
            <textarea type="text" id="is-content" name="isCntnt" value="${issueVO.isCntnt}">${issueVO.isCntnt}</textarea>

        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
      </div>
    </form>
  </body>
</html>