<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>쪽지 상세 페이지</title>
<jsp:include page="../commonheader.jsp" />
<script type="text/javascript" src="/js/memo/memoview.js"></script>
</head>
<body>
	          
	<h1>쪽지 조회</h1>
    <div class="grid" data-id="${memoVO.memoId}">
      <label for="name">받은 사람</label>
      <div>${memoVO.rcvId}</div>

      <label for="crtDt">보낸 날짜</label>
      <div>${memoVO.crtDt}</div>

      <label for="content">내용</label>
      <div>${memoVO.memoCntnt}</div>

     
	  <a href="#" class="delete-button">삭제</a>
      
     
    </div>
</body>
</html>