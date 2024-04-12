<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>로그인 페이지</title>
    <style type="text/css">
        .grid {
            display: grid;
            grid-template-rows: 35px 35px 35px 35px 35px 1fr;
            gap: 10px;
        }
        .grid > div:nth-child(5) {
            position: relative;
            right: 0;
        }
        .grid > div:last-child {
            width: 70%;
            padding-left: 30px;
        }
        input {
            width: 80%;
            height: 25px;
        }

        .footer {
            position: absolute;
            bottom: 0;
        }
    </style>
    <script type="text/javascript" src="/js/lib/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/login/login.js"></script>
</head>
<body>
<div class="header">
    <h2>홈페이지 로고 + 로그인</h2>
</div>
<div class="content">
    <form id="loginForm">
        <input type="hidden" name="next" id="next" value="${nextUrl}" />
        <div class="grid">
            <div><label for="empId">사원번호</label></div>
            <div><input id="empId" type="text" name="empId" value="${employeeVO.empId}"/></div>
            <div><label for="pwd">비밀번호</label></div>
            <div><input id="pwd" type="password" name="pwd" value="${employeeVO.pwd}"/></div>
            <div><button type="button" id="login-btn">로그인</button></div>
            <div>
                menagement는 회사 임직원 관리, 비품 관리 및 예정 프로젝트를 관리하는
                프로그램입니다. 로그인후 이용하실 수 있으며 임/직원 신규 등록은
                경영지원부(tel:123-1234)로 문의 부탁드립니다.
            </div>
        </div>
    </form>
</div>
<div class="footer">
    <div>회원 가입 문의: 경영지원부 (전화번호):123-1234</div>
    <div>회사 정보(이름, 사업자 번호, 대표명, 전호번호, 이메일)</div>
    <div>회사 주소</div>
    <div>COPYRIGHT</div>
</div>
</body>
</html>
