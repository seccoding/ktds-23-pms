<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>쪽지 상세 페이지</title>
<jsp:include page="../commonheader.jsp" />
<script type="text/javascript" src="/js/memo/memoview.js"></script>
<style>
  .memodetail {
    font-size: 1rem;
    margin-bottom: 1rem;
  }
  .grid {
    margin-left: 2rem;
    margin-top: 2rem;
    margin-right: 2rem;
  }
  .content-box {
    border: 1px solid #ccc; /* 테두리 */
    padding: 10px; /* 내부 여백 */
    background-color: #f9f9f9; /* 배경 색상 */
    border-radius: 5px; /* 테두리 둥글게 */
    margin-bottom: 1rem;
    font-size: 1.1rem;
    height: 27rem;
    overflow-y: auto;
  }
</style>
</head>
<body>
	  <jsp:include page="../commonmodal.jsp" />
    <div class="grid" data-id="${memoVO.memoId}">
      
      <div class="memodetail"><label for="title"></label>
       <h2> ${memoVO.memoTtl} </h2></div>

      <div class="memodetail"><label for="rcvId">받은 사람</label>
      ${memoVO.rcvId} ${memoVO.rcvName} ${memoVO.rcvEmail}</div>
      
      <div class="memodetail" id="memodetail-crtrId" data-crtrid="${memoVO.crtrId}"><label for="crtrId">보낸 사람</label>
      ${memoVO.crtrId} ${memoVO.crtrName} ${memoVO.crtrEmail}</div>

      <c:if test="${url eq '/memo/receive/view'}">
        <div class="memodetail"><label for="crtDt">받은 날짜</label>
        ${memoVO.crtDt}</div>
      </c:if>

      <c:if test="${url eq '/memo/sent/view' || url eq '/memo/storage/view'}">
        <div class="memodetail"><label for="crtDt">받은 날짜</label>
        ${memoVO.crtDt}</div>
      </c:if>

      <div class="memodetail"><label for="content">쪽지 내용</label></div>
      <div class="content-box">${memoVO.memoCntnt}</div>

      <div class="flex">
      <c:if test="${url eq '/memo/receive/view'}">
      <button href="" class="write-button">답장</button>
      </c:if>

  	  <button href="#" class="delete-button">삭제</button>

	    <c:if test="${url eq '/memo/receive/view'}">
        <button href="#" class="save-button">저장</button>
	    </c:if>
      
      <div>
     
    </div>
</body>
</html>