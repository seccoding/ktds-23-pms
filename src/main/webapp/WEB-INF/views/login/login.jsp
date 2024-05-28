<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>로그인 페이지</title>
    <jsp:include page="../commonmodal.jsp" />
    <style type="text/css">
        html {
            background-color: #ffffff;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .container {
            min-height: 100%;
            display: grid;
            grid-template-columns: 100px 1fr 100px;
            grid-template-rows: 200px 1fr 10px 200px;
            row-gap: 10px;
            column-gap: 10px;
        }

        .container > .item {
            display: grid;
        }

        .item:nth-of-type(2) {
            height: 500px;
        }

        .header, .footer {
            grid-column: 1 / 4;
        }

        .grid > div:nth-child(5) {
            position: relative;
            right: 0;
        }

        .grid > div:last-child {
            width: 70%;
            padding-left: 30px;
        }

        .logo {
            position: sticky;
            left: 45vw;
            top: 5vh;
        }

        .logo2 {
            width: 250px;
            height: 250px;
            position: relative;
            top: 40px;
            left: 30px;
        }

        .loginLogo {
            position: relative;
            width: 30px;
            height: 30px;
            right: 35px;
            top: 32px;
        }

        .passwordLogo {
            position: relative;
            width: 40px;
            height: 40px;
            right: 42px;
            top: 32px;
        }

        form {
            display: grid;
            grid-template-columns: 300px 500px;
            justify-content: center;
        }

        form > div:nth-child(1) {
            background-color: #FD3636FF;
            border-bottom-left-radius: 10px;
            border-top-left-radius: 10px;
        }

        form > div:nth-child(2) {
            padding-top: 170px;
            padding-left: 100px;
            /*background-color: rgba(100, 100, 100, 0.05);*/
            border-bottom-right-radius: 10px;
            border-top-right-radius: 10px;
        }

        .formBackground {
            background-color: rgba(100, 100, 100, 0.1);
        }

        input[type="text"], input[type="password"] {
            font-size: 18px;
            color: #222222;
            border: none;
            border-bottom: solid #aaaaaa 1px;
            padding-bottom: 5px;
            padding-left: 10px;
            position: relative;
            background: none;
            z-index: 5;
        }

        input::placeholder {
            color: #FD3636FF;
        }

        input:focus {
            outline: none;
        }

        span {
            display: block;
            position: absolute;
            bottom: 0;
            left: 0;
            background-color: #666;
            width: 0;
            height: 2px;
            border-radius: 2px;
            transition: 0.5s;
        }

        label {
            position: absolute;
            color: #aaa;
            left: 10px;
            font-size: 15px;
            bottom: 8px;
            transition: all .2s;
        }

        input:focus ~ label, input:valid ~ label {
            font-size: 16px;
            bottom: 30px;
            color: #666;
            font-weight: bold;
        }

        input:focus ~ span, input:valid ~ span {
            width: 100%;
        }

        .leftForm {
            color: #ffffff;
        }

        .id, .pwd {
            position: relative;
            width: 240px;
            margin-top: 20px;
        }

        .inputText {
            position: relative;
            left: 35px;
            bottom: 30px;
        }

        .id {
            bottom: 25px;
        }

        .pwd {
            bottom: 35px;
        }

        #empId, #pwd {
            width: 240px;
        }

        button textarea {
            margin: 0;
            font-family: inherit;
            font-size: inherit;
            line-height: inherit;
        }

        button {
            width: 240px;
            height: 2.5rem;
            color: #fff;
            border: 0px;
            font-weight: bold;
            border-radius: 0.25rem;
            background-color: #EE6953FF;
            cursor: pointer;
        }

        button:hover {
            color: #fff;
            background-color: #FD3636FF;
        }

        button:disabled {
            background-color: var(--border-color);
        }

        #loginForm {
            position: sticky;
        }

        .login-btn {
            position: relative;
            bottom: 20px;
        }

        .footer {
            display: grid;
            justify-items: center;
            position: relative;
            bottom: 200px;
            gap: 9px;
        }

        .footer > div {
            font-size: 15px;
        }

        .login-btn {
            margin-top: 10px;
        }
    </style>
    <%--    <link rel="stylesheet" href="../css/common.css"/>--%>
    <script type="text/javascript" src="/js/lib/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/login/login.js"></script>
</head>
<body>
<div class="container">
    <div class="item header">
<%--        <img class="logo" src="/images/logo.png" alt=""/>--%>
    </div>
    <div class="item"></div>
    <div class="item">
        <input type="hidden" name="next" id="next" value="${nextUrl}"/>
        <form id="loginForm">
            <div class="leftForm">
                <!-- <img class="logo2" src="/images/logo2.png"> PMS 로고 삽입 자리-->
            </div>
            <div class="formBackground">
                <div class="inputText">
                    <div class="id">
                        <img class="loginLogo" src="/images/login.png"/>
                        <input id="empId" type="text" name="empId" value="${employeeVO.empId}" required/>
                        <label for="empId">ID</label>
                        <span></span>
                    </div>
                    <div class="pwd">
                        <img class="passwordLogo" src="/images/header-login.png"/>
                        <input id="pwd" type="password" name="pwd" value="${employeeVO.pwd}" required/>
                        <label for="pwd">PASSWORD</label>
                        <span></span>
                    </div>
                    <div class="login-btn">
                        <button type="button" id="login-btn">LOGIN</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="item"></div>
</div>
<div class="footer">
    <div>PMS 는 회사 임직원 관리, 비품 관리 및 예정 프로젝트를 관리하는 프로그램입니다.</div>
    <div>로그인 후 이용하실 수 있으며 임/직원 신규 등록은 경영지원부(tel:123-1234)로 문의 부탁드립니다.</div>
    <div>회사 정보(KtdsUniversity 이메일 : kwon@kt.ds)</div>
    <div>회사 주소 : 효령로 176</div>
    <div>

    </div>
    <br/>
    <br/>
    <div>@COPYRIGHT KTDS UNIVERSITY 2008 ALL RIGHTS RESERVED.</div>
</div>
</body>
</html>
