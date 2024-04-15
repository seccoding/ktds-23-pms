<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <jsp:include page="../commonheader.jsp" />
    <script type="text/javascript" src="/js/commoncode/list.js"></script>
  </head>
  <body>
    <h1>대분류</h1>
    <div>
      <c:forEach items="${mainCodeList}" var="mainCode">
        <div
          class="code-item"
          data-append-to=".code-sub-items"
          data-code-id="${mainCode.cmcdId}"
        >
          ${mainCode.cmcdName}
        </div>
      </c:forEach>
    </div>

    <h1>중분류</h1>
    <div
      class="code-sub-items"
      data-sub-class=".code-sub-item"
      data-append-to=".code-sub-items2"
    ></div>

    <h1>소분류</h1>
    <div class="code-sub-items2" data-sub-class=".code-sub-item2"></div>
  </body>
</html>
