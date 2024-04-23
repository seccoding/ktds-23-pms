$().ready(function () {

    function reloadSubMenu(pid) {
        $.get("/ajax/menu/" + pid, function (response) {
            var subMenuTable = $(".sub-menu").find("tbody");
            subMenuTable.html("");

            var menuList = response.data.menuList;

            codeList.forEach((subCommonCode) => {
                var trDom = $("<tr></tr>");
                trDom.attr({
                    "data-id": subCommonCode.cmcdId,
                    "data-pid": subCommonCode.cmcdPid,
                    "data-code-name": subCommonCode.cmcdName,
                    "data-crt-dt": subCommonCode.crtDt,
                    "data-crtr-id": subCommonCode.crtrId,
                    "data-mdf-dt": subCommonCode.mdfDt,
                    "data-mdfr-id": subCommonCode.mdfrId,
                });

                trDom.on("click", function () {
                    clearSubCodeInfo();

                    $(this).closest("tbody").find("tr").removeClass("active");
                    $(this).addClass("active");

                    $("#subCmcdId").attr("disabled", "disabled");

                    var commonCodeInfo = $(".sub-code-info");
                    commonCodeInfo.find("#subCmcdId").val($(this).data("id"));
                    commonCodeInfo.find("#subCmcdPid").val($(this).data("pid"));
                    commonCodeInfo.find("#subCmcdName").val($(this).data("code-name"));
                    commonCodeInfo.find("#subCrtDt").text($(this).data("crt-dt"));
                    commonCodeInfo.find("#subCrtrId").text($(this).data("crtr-id"));
                    commonCodeInfo.find("#subMdfDt").text($(this).data("mdf-dt") || "");
                    commonCodeInfo.find("#subMdfrId").text($(this).data("mdfr-id") || "");

                    $("#modifySubCode").removeAttr("disabled");
                    $("#delSubCode").removeAttr("disabled");
                    $("#newSubCode").removeAttr("disabled");
                });

                var codeIdTdDom = $("<td></td>");
                codeIdTdDom.text(subCommonCode.cmcdId);

                var codeNameTdDom = $("<td></td>");
                codeNameTdDom.text(subCommonCode.cmcdName);

                trDom.append(codeIdTdDom);
                trDom.append(codeNameTdDom);

                subCodeTable.append(trDom);
            });
        });
    }

    function clearSubMenuInfo() {
        var subMenuInfo = $(".sub-menu");
        subMenuInfo.find("#subCmcdId").val("");
        subMenuInfo.find("#subCmcdPid").val("");
        subMenuInfo.find("#subCmcdName").val("");
        subMenuInfo.find("#subCrtDt").text("");
        subMenuInfo.find("#subCrtrId").text("");
        subMenuInfo.find("#subMdfDt").text("");
        subMenuInfo.find("#subMdfrId").text("");

        $("#modifySubCode").attr("disabled", "disabled");
        $("#delSubCode").attr("disabled", "disabled");
        $("#newSubMenu").removeAttr("disabled");
        $("#newSubMenu").text("신규");
        $("#newSubMenu").attr("data-mode", "new");
    }

    $("table.main-menu")
        .find("tr")
        .on("click", function () {
            $(this).closest("tbody").find("tr").removeClass("active");
            $(this).addClass("active");

            var menuInfo = $(".menu-info").not(".sub-menu-info").not(".detail-menu-info");
            menuInfo.find("#id").val($(this).data("id"));
            menuInfo.find("#name").val($(this).data("name"));
            menuInfo.find("#role").val($(this).data("role"));
            menuInfo.find("#url").val($(this).data("url"));
            menuInfo.find("#parent").val($(this).data("parent"));

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
})