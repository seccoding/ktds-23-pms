<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>메모 상세 페이지</title>
</head>
<body>
	<h1>메모 조회</h1>
    <div class="grid" data-id="${memoVO.memoId}">
      <label for="name">받은 사람</label>
      <div>${memoVO.rcvId}</div>

      <label for="crtDt">보낸 날짜</label>
      <div>${memoVO.crtDt}</div>

      <label for="content">내용</label>
      <div>${memoVO.memoCntnt}</div>

        <div class="btn-group">
          <div class="right-align">
            
            <a class="delete-board" href="javascript:void(0);">삭제</a>
          </div>
        </div>
    </div>
</body>
</html>