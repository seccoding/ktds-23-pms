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
                            data-icon="${mainMenu.icon}"
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

    <div class="overflow-scroll">
        <h4 style="background-color: var(--body-bg)">메뉴 정보</h4>
        <div
                class="grid menu-info main-menu-info"
                data-grid-columns="95px 1fr 95px 1fr"
                data-grid-rows="1fr 1fr 1fr 1fr 1fr"
        >
            <label for="mainId">ID</label>
            <input type="text" name="id" id="mainId" />

            <label for="mainMenuName">Name</label>
            <input type="text" name="name" id="mainMenuName" />

            <label for="mainRole">권한</label>
            <select id="mainRole" name="role">
                <option value="ADMIN">ADMIN</option>
                <option value="USER">USER</option>
            </select>

            <div data-columns="2 / 2"></div>

            <div data-columns="1 / -1" style="text-align: right">
                <button type="button" id="modifyMainMenu">수정</button>
                <button type="button" id="delMainMenu">삭제</button>
                <button type="button" id="newMainMenu" data-mode="new">신규</button>
            </div>
        </div>
    </div>

    <div class="overflow-scroll">
        <h4 style="background-color: var(--body-bg)">메뉴 정보</h4>
        <div
                class="grid menu-info sub-menu-info"
                data-grid-columns="95px 1fr 95px 1fr"
                data-grid-rows="1fr 1fr 1fr 1fr 1fr"
        >
            <label for="subId">ID</label>
            <input type="text" name="id" id="subId" />
            <label for="subParent">P-ID</label>
            <input type="text" disabled name="parent" id="subParent" />

            <label for="subUrl">URL</label>
            <input
                    data-columns="2 / -1"
                    type="text"
                    name="parent"
                    id="subUrl"
            />


            <label for="subMenuName">Name</label>
            <input type="text" name="name" id="subMenuName" />
            <label for="subRole">권한</label>
            <select id="subRole" name="role">
                <option value="ADMIN">ADMIN</option>
                <option value="USER">USER</option>
            </select>

            <div data-columns="1 / -1" style="text-align: right">
                <button type="button" id="modifySubMenu">수정</button>
                <button type="button" id="delSubMenu">삭제</button>
                <button type="button" id="newSubMenu" data-mode="new">신규</button>
            </div>
        </div>
    </div>

    <div class="overflow-scroll">
        <h4 style="background-color: var(--body-bg)">메뉴 정보</h4>
        <div
                class="grid menu-info detail-menu-info"
                data-grid-columns="95px 1fr 95px 1fr"
                data-grid-rows="1fr 1fr 1fr 1fr 1fr"
        >
            <label for="detailId">ID</label>
            <input type="text" name="id" id="detailId" />
            <label for="detailParent">P-ID</label>
            <input type="text" disabled name="parent" id="detailParent" />

            <label for="detailUrl">URL</label>
            <input
                    data-columns="2 / -1"
                    type="text"
                    name="url"
                    id="detailUrl"
            />

            <label for="detailMenuName">Name</label>
            <input type="text" name="name" id="detailMenuName" />

            <div data-columns="1 / -1" style="text-align: right">
                <button type="button" id="modifyDetailMenu">수정</button>
                <button type="button" id="delDetailMenu">삭제</button>
                <button type="button" id="newDetailMenu" data-mode="new">신규</button>
            </div>
        </div>
    </div>


</div>

</body>
</html>
