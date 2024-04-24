$().ready(function () {
    function clearMainMenuInfo() {
        var MainMenuInfo = $(".main-menu-info");
        MainMenuInfo.find("#mainId").val("");
        MainMenuInfo.find("#mainMenuName").val("");

        $("#modifyMainMenu").attr("disabled", "disabled");
        $("#delMainMenu").attr("disabled", "disabled");
        $("#newMainMenu").removeAttr("disabled");
        $("#newMainMenu").text("신규");
        $("#newMainMenu").attr("data-mode", "new");
    }

    function reloadMainMenu() {
        $.get("/ajax/menu/reload", function (response) {
            var menuTable = $(".main-menu").find("tbody");
            menuTable.html("");
            var menuList = response.data.menuList;

            clearMainMenuInfo();
            clearSubMenuInfo();
            clearDetailMenuInfo();

            menuList.forEach((menu) => {
                var trDom = $("<tr></tr>");
                trDom.attr({
                    "data-id": menu.id,
                    "data-name": menu.name,
                    "data-role": menu.role,
                    "data-url": menu.url,
                    "data-parent": menu.parent,
                });

                trDom.on("click", function () {
                    $(this).closest("tbody").find("tr").removeClass("active");
                    $(this).addClass("active");

                    clearSubMenuInfo();
                    clearDetailMenuInfo();

                    var mainMenuInfo = $(".main-menu-info");
                    mainMenuInfo.find("#mainId").val($(this).data("id"));
                    mainMenuInfo.find("#mainRole").val($(this).data("role"));
                    mainMenuInfo.find("#mainMenuName").val($(this).data("name"));

                    $("#modifyMainMenu").removeAttr("disabled");
                    $("#delMainMenu").removeAttr("disabled");
                    $("#newMainMenu").removeAttr("disabled");
                });

                var mainMenuIdTdDom = $("<td></td>");
                mainMenuIdTdDom.text(menu.id);

                var mainMenuNameTdDom = $("<td></td>");
                mainMenuNameTdDom.text(menu.name);

                var mainMenuRoleTdDom = $("<td></td>");
                mainMenuRoleTdDom.text(menu.role);

                trDom.append(mainMenuIdTdDom);
                trDom.append(mainMenuNameTdDom);
                trDom.append(mainMenuRoleTdDom);

                menuTable.append(trDom);
            });
        });
    }

    // 서브 메뉴 불러오기
    $("table.main-menu")
        .on("click", "tr", function () {
            $(this).closest("tbody").find("tr").removeClass("active");
            $(this).addClass("active");

            var menuInfo = $(".menu-info").not(".sub-menu-info").not(".detail-menu-info");
            menuInfo.find("#mainId").val($(this).data("id"));
            menuInfo.find("#mainMenuName").val($(this).data("name"));
            menuInfo.find("#mainRole").val($(this).data("role"));

            $("#mainParent").attr("disabled", "disabled");

            $("#modifyMainMenu").removeAttr("disabled");
            $("#delMainMenu").removeAttr("disabled");
            $("#newMainMenu").removeAttr("disabled");

            $("#newMainMenu").attr("data-mode", "new");
            $("#newMainMenu").text("신규");

            clearSubMenuInfo();

            var pid = $(this).data("id");
            reloadSubMenu(pid);
        });

    function reloadSubMenu(pid) {
        $.get("/ajax/menu/" + pid, function (response) {
            var subMenuTable = $(".sub-menu").find("tbody");
            subMenuTable.html("");

            var menuList = response.data.menuList;

            menuList.forEach((subMenu) => {
                var trDom = $("<tr></tr>");

                trDom.attr({
                    "data-id": subMenu.id,
                    "data-name": subMenu.name,
                    "data-role": subMenu.role,
                    "data-url": subMenu.url,
                    "data-parent": subMenu.parent,
                });

                trDom.on("click", function () {
                    clearSubMenuInfo();

                    $(this).closest("tbody").find("tr").removeClass("active");
                    $(this).addClass("active");

                    $("#subId").attr("disabled", "disabled");

                    var subMenuInfo = $(".sub-menu-info");
                    subMenuInfo.find("#subId").val($(this).data("id"));
                    subMenuInfo.find("#subParent").val($(this).data("parent"));
                    subMenuInfo.find("#subUrl").val($(this).data("url"));
                    subMenuInfo.find("#subMenuName").val($(this).data("name"));
                    subMenuInfo.find("#subRole").val($(this).data("role"));

                    $("#modifySubMenu").removeAttr("disabled");
                    $("#delSubMenu").removeAttr("disabled");
                    $("#newSubMenu").removeAttr("disabled");
                });

                var subMenuIdTdDom = $("<td></td>");
                subMenuIdTdDom.text(subMenu.id);

                var subMenuNameTdDom = $("<td></td>");
                subMenuNameTdDom.text(subMenu.name);

                var subRoleTdDom = $("<td></td>");
                subRoleTdDom.text(subMenu.role);

                trDom.append(subMenuIdTdDom);
                trDom.append(subMenuNameTdDom);
                trDom.append(subRoleTdDom);

                subMenuTable.append(trDom);
            });
        });
    }

    function clearSubMenuInfo() {
        var subMenuInfo = $(".sub-menu-info");
        subMenuInfo.find("#subId").val("");
        subMenuInfo.find("#subParent").val("");
        subMenuInfo.find("#subUrl").val("");
        subMenuInfo.find("#subMenuName").val("");
        subMenuInfo.find("#subRole").val("");

        $("#modifySubMenu").attr("disabled", "disabled");
        $("#delSubMenu").attr("disabled", "disabled");
        $("#newSubMenu").removeAttr("disabled");
        $("#newSubMenu").text("신규");
        $("#newSubMenu").attr("data-mode", "new");
    }

    // 디테일 메뉴 불러오기
    $("table.sub-menu")
        .on("click", "tr", function () {
            $(this).closest("tbody").find("tr").removeClass("active");
            $(this).addClass("active");

            var subMenuInfo = $(".menu-info").not(".main-menu-info").not(".detail-menu-info");
            subMenuInfo.find("#subId").val($(this).data("id"));
            subMenuInfo.find("#subParent").val($(this).data("parent"));
            subMenuInfo.find("#subUrl").val($(this).data("url"));
            subMenuInfo.find("#subMenuName").val($(this).data("name"));
            subMenuInfo.find("#subRole").val($(this).data("role"));

            $("#mainParent").attr("disabled", "disabled");

            $("#modifySubMenu").removeAttr("disabled");
            $("#delSubMenu").removeAttr("disabled");
            $("#newSubMenu").removeAttr("disabled");

            $("#newSubMenu").attr("data-mode", "new");
            $("#newSubMenu").text("신규");

            clearDetailMenuInfo();

            var pid = $(this).data("id");
            reloadDetailMenu(pid);
        });

    function clearDetailMenuInfo() {
        var detailMenuInfo = $(".detail-menu-info");
        detailMenuInfo.find("#detailId").val("");
        detailMenuInfo.find("#detailParent").val("");
        detailMenuInfo.find("#detailUrl").val("");
        detailMenuInfo.find("#detailMenuName").val("");

        $("#modifyDetailMenu").attr("disabled", "disabled");
        $("#delDetailMenu").attr("disabled", "disabled");
        $("#newDetailMenu").removeAttr("disabled");
        $("#newDetailMenu").text("신규");
        $("#newDetailMenu").attr("data-mode", "new");
    }

    function reloadDetailMenu(pid) {
        $.get("/ajax/menu/" + pid, function (response) {
            var detailMenuTable = $(".detail-menu").find("tbody");
            detailMenuTable.html("");

            var detailMenuList = response.data.menuList;
            console.log(detailMenuList);

            detailMenuList.forEach((detailMenu) => {
                var trDom = $("<tr></tr>");

                trDom.attr({
                    "data-id": detailMenu.id,
                    "data-name": detailMenu.name,
                    "data-role": detailMenu.role,
                    "data-url": detailMenu.url,
                    "data-parent": detailMenu.parent,
                });

                trDom.on("click", function () {
                    clearDetailMenuInfo();

                    $(this).closest("tbody").find("tr").removeClass("active");
                    $(this).addClass("active");

                    $("#detailId").attr("disabled", "disabled");

                    var detailMenuInfo = $(".detail-menu-info");
                    detailMenuInfo.find("#detailId").val($(this).data("id"));
                    detailMenuInfo.find("#detailParent").val($(this).data("parent"));
                    detailMenuInfo.find("#detailUrl").val($(this).data("url"));
                    detailMenuInfo.find("#detailMenuName").val($(this).data("name"));

                    $("#modifyDetailMenu").removeAttr("disabled");
                    $("#delDetailMenu").removeAttr("disabled");
                    $("#newDetailMenu").removeAttr("disabled");
                });

                var detailMenuIdTdDom = $("<td></td>");
                detailMenuIdTdDom.text(detailMenu.id);

                var detailMenuNameTdDom = $("<td></td>");
                detailMenuNameTdDom.text(detailMenu.name);

                trDom.append(detailMenuIdTdDom);
                trDom.append(detailMenuNameTdDom);

                detailMenuTable.append(trDom);
            });
        });
    }

    // 생성 수정 삭제 영역
    $("#newMainMenu").on("click", function () {
        $("#mainId").removeAttr("disabled");
        var newButton = $(this);
        var mode = newButton.attr("data-mode");
        console.log("mode", mode);
        if (mode === "new") {
            clearMainMenuInfo();
            newButton.attr("data-mode", "save");
            $("#mainId").focus();
            $("#newMainMenu").text("저장");
        } else {
            $.post(
                "/ajax/menu/new",
                {
                    id: $("#mainId").val(),
                    name: $("#mainMenuName").val(),
                    role: $("#mainRole").val()
                },
                function (response) {
                    if (response.data.result) {
                        $("#mainId").attr("disabled", "disabled");
                        newButton.attr("data-mode", "new");
                        reloadMainMenu();
                    }
                }
            );
        }
    });

    $("#newSubMenu").on("click", function () {
        $("#subId").removeAttr("disabled");
        var newButton = $(this);
        var mode = newButton.attr("data-mode");

        if (mode === "new") {
            clearSubMenuInfo();
            newButton.attr("data-mode", "save");
            $("#subParent").val($("#mainId").val());
            $("#subId").focus();
            $("#newSubMenu").text("저장");
        } else {
            $.post(
                "/ajax/menu/new",
                {
                    id: $("#subId").val(),
                    name: $("#subMenuName").val(),
                    role: $("#subRole").val(),
                    url: $("#subUrl").val(),
                    parent: $("#subParent").val(),
                },
                function (response) {
                    if (response.data.result) {
                        $("#subId").attr("disabled", "disabled");
                        newButton.attr("data-mode", "new");
                        reloadSubMenu($("#mainId").val());
                    }
                }
            );
        }
    });

    $("#newDetailMenu").on("click", function () {
        $("#detailId").removeAttr("disabled");
        var newButton = $(this);
        var mode = newButton.attr("data-mode");

        if (mode === "new") {
            clearDetailMenuInfo();
            newButton.attr("data-mode", "save");
            $("#detailParent").val($("#subId").val());
            $("#detailId").focus();
            $("#newDetailMenu").text("저장");
        } else {
            $.post(
                "/ajax/menu/new",
                {
                    id: $("#detailId").val(),
                    name: $("#detailMenuName").val(),
                    url: $("#detailUrl").val(),
                    parent: $("#detailParent").val(),
                },
                function (response) {
                    if (response.data.result) {
                        $("#detailId").attr("disabled", "disabled");
                        newButton.attr("data-mode", "new");
                        reloadDetailMenu($("#subId").val());
                    }
                }
            );
        }
    });

    $("#modifyMainMenu").on("click", function () {
        if (confirm("저장하시겠습니까?")) {
            $.post(
                "/ajax/menu/update",
                {
                    id: $("#mainId").val(),
                    name: $("#mainMenuName").val(),
                    role: $("#mainRole").val(),
                },
                function (response) {
                    if (response.data.result) {
                        reloadMainMenu();
                    }
                }
            );
        } else {
            alert("저장을 취소했습니다.");
        }
    });

    $("#modifySubMenu").on("click", function () {
        if (confirm("저장하시겠습니까?")) {
            $.post(
                "/ajax/menu/update",
                {
                    id: $("#subId").val(),
                    name: $("#subMenuName").val(),
                    role: $("#subRole").val(),
                    url: $("#subUrl").val(),
                    parent: $("#subParent").val(),
                },
                function (response) {
                    if (response.data.result) {
                        reloadSubMenu($("#subParent").val());
                    }
                }
            );
        } else {
            alert("저장을 취소했습니다.");
        }
    });

    $("#modifyDetailMenu").on("click", function () {
        if (confirm("저장하시겠습니까?")) {
            $.post(
                "/ajax/menu/update",
                {
                    id: $("#detailId").val(),
                    name: $("#detailMenuName").val(),
                    url: $("#detailUrl").val(),
                    parent: $("#detailParent").val(),
                },
                function (response) {
                    if (response.data.result) {
                        reloadDetailMenu($("#detailParent").val());
                    }
                }
            );
        } else {
            alert("저장을 취소했습니다.");
        }
    });

    $("#delMainMenu").on("click", function () {
        if (confirm("삭제하시겠습니까?")) {
            $.get(
                "/ajax/menu/delete/" + $("#mainId").val(),
                function (response) {
                    if (response.data.result) {
                        reloadMainMenu();
                    }
                }
            );
        } else {
            alert("삭제를 취소했습니다.");
        }
    });

    $("#delSubMenu").on("click", function () {
        if (confirm("삭제하시겠습니까?")) {
            $.get(
                "/ajax/menu/delete/" + $("#subId").val(),
                function (response) {
                    if (response.data.result) {
                        reloadSubMenu($("#mainId").val());
                    }
                }
            );
        } else {
            alert("삭제를 취소했습니다.");
        }
    });

    $("#delDetailMenu").on("click", function () {
        if (confirm("삭제하시겠습니까?")) {
            $.get(
                "/ajax/menu/delete/" + $("#detailId").val(),
                function (response) {
                    if (response.data.result) {
                        reloadDetailMenu($("#subId").val());
                    }
                }
            );
        } else {
            alert("삭제를 취소했습니다.");
        }
    });
})