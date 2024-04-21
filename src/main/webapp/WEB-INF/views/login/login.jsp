<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>로그인 페이지</title>
    <link rel="stylesheet" href="../css/common.css"/>
    <style type="text/css">
        html {
            background-color: #ffffff;
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

        img {
            position: absolute;
            margin-left: 30vw;


        }

        form {
            display: grid;
            grid-template-columns: 300px 500px;
            justify-content: center;
        }

        form > div:nth-child(1) {
            background-color: #007bff;
            border-bottom-left-radius: 10px;
            border-top-left-radius: 10px;
        }

        form > div:nth-child(2) {
            padding-top: 170px;
            padding-left: 100px;
            background-color: rgba(100, 100 ,100, 0.05);
            border-bottom-right-radius: 10px;
            border-top-right-radius: 10px;
        }

        .inputText > div:nth-child(even) {
            margin-bottom: 17px;
        }

        input {
            width: 80%;
            height: 25px;
        }

        .leftForm {
            color: #ffffff;
            font-size: 30px;
        }

        .footer {
            position: absolute;
            bottom: 0;
        }

        input[type="password"] {
            width: 16rem;
            height: 2rem;
            border: 0;
            border-radius: var(--box-border-radius);
            padding-left: 10px;
            outline: none;
            background-color: var(--border-color);
        }
    </style>
    <script type="text/javascript" src="/js/lib/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/login/login.js"></script>
</head>
<body>
<div class="container">
    <div class="item header">
        <img src="/images/logo.png" alt="" />
    </div>
    <div class="item"></div>
    <div class="item">
        <input type="hidden" name="next" id="next" value="${nextUrl}"/>
        <form id="loginForm">
            <div class="leftForm">
                <span>PMS</span>
            </div>
            <div class="inputText">
                <div><label for="empId">사원번호</label></div>
                <div><input id="empId" type="text" name="empId" value="${employeeVO.empId}"/></div>
                <div><label for="pwd">비밀번호</label></div>
                <div><input id="pwd" type="password" name="pwd" value="${employeeVO.pwd}"/></div>
                <div>
                    <button type="button" id="login-btn">로그인</button>
                </div>
            </div>
        </form>
    </div>
    <div class="item"></div>
</div>
<div class="footer">
    <div>
        <div>
            management는 회사 임직원 관리, 비품 관리 및 예정 프로젝트를 관리하는
            프로그램입니다. 로그인후 이용하실 수 있으며 임/직원 신규 등록은
            경영지원부(tel:123-1234)로 문의 부탁드립니다.
        </div>
    </div>
    <div>회원 가입 문의: 경영지원부 (전화번호):123-1234</div>
    <div>회사 정보(이름, 사업자 번호, 대표명, 전호번호, 이메일)</div>
    <div>회사 주소</div>
    <div>

    </div>
    <div>COPYRIGHT</div>
</div>
</body>
</html>
