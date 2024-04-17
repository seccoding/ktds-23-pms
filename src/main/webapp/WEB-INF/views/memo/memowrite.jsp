<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지 쓰기</title>
<jsp:include page="../commonheader.jsp" />
</head>
<body>

	<h1>쪽지 쓰기</h1>
    <form action="/memo/write" method="post" enctype="multipart/form-data">
      <div class="grid">
        <label for="rcvId">받는사람</label>
        <input
          id="rcvId"
          type="text"
          name="rcvId"
          value="${memoVO.rcvId}"
        />
		<button type="button" onclick="alert('주소록 구현필요')">주소록</button>
        <label for="memoCntnt">내용</label>
        <textarea
          id="memoCntnt"
          name="memoCntnt"
          style="height: 300px"
          value="${memoVO.memoCntnt}"
        ></textarea>
        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="보내기"/>
          </div>
        </div>
      </div>
    </form>
    

</body>
</html>