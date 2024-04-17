<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/lib/jquery-3.7.1.min.js"></script>
    <script type="text/javascript">
        $().ready(function () {

            var pwdMessage = "${pwdMessage}";

            if (pwdMessage) {
                alert(pwdMessage);
            }

        })
    </script>
</head>
<body>
<h1>메인페이지입니다.</h1>

<div class="main">
      <div class="main-common">
        <div>${sessionScope._LOGIN_USER_.empName}님 환영합니다</div>
		<div>${sessionScope._LOGIN_USER_.empId}님 환영합니다</div>
		<div><a href="/employee/logout">로그아웃</a></div>
      </div>
      <div class="main-common">
        <!-- 컨텐트 등록시 삭제 -->
        <img
          src="https://i.namu.wiki/i/c7SknQhaSBgDtCBjXrIuceZPSGiUM4X5qTRAlibrwBcZfiLTaF-fbY7zeJ1Pdm0ILSdrCKoO-m73mMfnPdckNQ.webp"
          alt=""
        />
      </div>
      <div class="main-common">
        <div>보낸쪽지</div>
        <div>받은쪽지 중 안읽은 쪽지?</div>
        <div>쪽지 보관함</div>
      </div>
      <div class="main-common">graph</div>
      <div class="main-common">project</div>
    </div>

</body>
</html>
