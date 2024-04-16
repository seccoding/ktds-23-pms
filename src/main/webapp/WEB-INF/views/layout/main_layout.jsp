<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>비품 목록</title>
    <jsp:include page="../commonheader.jsp" />
  </head>
  <body>
    <jsp:include page="../layout/layout.jsp" />
    <div class="menu-tab-wrapper">
      <span class="menu-tab-prev"><<</span>
      <ul class="menu-tab"></ul>
      <span class="menu-tab-next">>></span>
    </div>
    <ul class="frame-list">
      <li data-menu-id="error" data-menu-name="에러" class="frame-active">
        <iframe src="/error"></iframe>
      </li>
      <li data-menu-id="error" data-menu-name="에러2">
        <iframe src="/error"></iframe>
      </li>
    </ul>
    <jsp:include page="../layout/layout_close.jsp" />
  </body>
</html>
