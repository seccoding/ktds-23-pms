<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이슈 관리 상세 페이지</title>
</head>
<body>
	<div class="grid" data-id="${issueVO.isTtl}">
        <label for="subject">제목</label>
        <div>${issueVO.isTtl}</div>
  
        <label for="name">작성자</label>
        <div>${issueVO.crtrId}</div>
  
        <label for="viewCnt">조회수</label>
        <div>${issuVO.isCnt}</div>
  
        <label for="originFileName">첨부파일</label>
        <div>
        </div>
  
        <label for="crtDt">등록일</label>
        <div>${issueVO.crtDt}</div>
  
        <label for="mdfyDt">수정일</label>
        <div>${issueVO.mdfDt}</div>
  
        <label for="content">내용</label>
        <div>${issueVO.isCntnt}</div>
    </div>
</body>
</html>