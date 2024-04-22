<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <jsp:include page="../commonheader.jsp"/>
    <script type="text/javascript" src="/js/menu/menuManage.js"></script>
    <title>메뉴 수정 페이지</title>
</head>
<body>
<%--대분류, 중분류, 소분류 필요--%>
<%--그리드 column을 3개로 나눠야 한다.--%>

<div
        class="grid"
        data-gap="0.5rem"
        data-grid-columns="1fr 1fr 1fr"
        data-grid-rows="30.7rem 17rem"
>
    <div class="overflow-scroll">
        <h4 class="fixed" style="background-color: var(--body-bg)">대분류</h4>
        <div>
            <table class="fit-parent main-menu">
                <thead
                        class="fixed"
                        data-fixed-top="1.52rem"
                        style="background-color: var(--body-bg)"
                >
                <tr>
                    <th>Menu ID</th>
                    <th>Menu Name</th>
                    <th>Role</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${menuList}" var="mainMenu">
                    <tr
                            data-id="${mainMenu.id}"
                            data-name="${mainMenu.name}"
                            data-role="${mainMenu.role}"
                            data-url="${mainMenu.url}"
                            data-parent="${mainMenu.parent}"
                    >
                        <td>${mainMenu.id}</td>
                        <td>${mainMenu.name}</td>
                        <td>${mainMenu.role}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="overflow-scroll">
        <h4 class="fixed" style="background-color: var(--body-bg)">중분류</h4>
        <div>
            <table class="fit-parent sub-menu">
                <thead
                        class="fixed"
                        data-fixed-top="1.52rem"
                        style="background-color: var(--body-bg)"
                >
                <tr>
                    <th>Menu ID</th>
                    <th>Menu Name</th>
                    <th>Role</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>

    <div class="overflow-scroll">
        <h4 class="fixed" style="background-color: var(--body-bg)">소분류</h4>
        <div>
            <table class="fit-parent detail-menu">
                <thead
                        class="fixed"
                        data-fixed-top="1.52rem"
                        style="background-color: var(--body-bg)"
                >
                <tr>
                    <th>Menu ID</th>
                    <th>Menu Name</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>

</div>

</body>
</html>
