$().ready(function () {
  function reloadSubCommonCode(pid) {
    $.get("/ajax/commoncode/" + pid, function (response) {
      var subCodeTable = $(".sub-common-code").find("tbody");
      subCodeTable.html("");
      var codeList = response.data.codeList;

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

  function reloadCommonCode() {
    $.get("/ajax/commoncode/reload", function (response) {
      var codeTable = $(".common-code").find("tbody");
      codeTable.html("");
      var codeList = response.data.codeList;

      clearCodeInfo();
      clearSubCodeInfo();

      codeList.forEach((commonCode) => {
        var trDom = $("<tr></tr>");
        trDom.attr({
          "data-id": commonCode.cmcdId,
          "data-pid": commonCode.cmcdPid,
          "data-code-name": commonCode.cmcdName,
          "data-crt-dt": commonCode.crtDt,
          "data-crtr-id": commonCode.crtrId,
          "data-mdf-dt": commonCode.mdfDt,
          "data-mdfr-id": commonCode.mdfrId,
        });

        trDom.on("click", function () {
          $(this).closest("tbody").find("tr").removeClass("active");
          $(this).addClass("active");

          clearSubCodeInfo();

          var commonCodeInfo = $(".code-info");
          commonCodeInfo.find("#cmcdId").val($(this).data("id"));
          commonCodeInfo.find("#cmcdPid").val($(this).data("pid"));
          commonCodeInfo.find("#cmcdName").val($(this).data("code-name"));
          commonCodeInfo.find("#crtDt").text($(this).data("crt-dt"));
          commonCodeInfo.find("#crtrId").text($(this).data("crtr-id"));
          commonCodeInfo.find("#mdfDt").text($(this).data("mdf-dt") || "");
          commonCodeInfo.find("#mdfrId").text($(this).data("mdfr-id") || "");

          $("#modifyCode").removeAttr("disabled");
          $("#delCode").removeAttr("disabled");
          $("#newCode").removeAttr("disabled");
        });

        var codeIdTdDom = $("<td></td>");
        codeIdTdDom.text(commonCode.cmcdId);

        var codeNameTdDom = $("<td></td>");
        codeNameTdDom.text(commonCode.cmcdName);

        trDom.append(codeIdTdDom);
        trDom.append(codeNameTdDom);

        codeTable.append(trDom);
      });
    });
  }

  function clearCodeInfo() {
    var subCommonCodeInfo = $(".code-info");
    subCommonCodeInfo.find("#cmcdId").val("");
    subCommonCodeInfo.find("#cmcdPid").val("");
    subCommonCodeInfo.find("#cmcdName").val("");
    subCommonCodeInfo.find("#crtDt").text("");
    subCommonCodeInfo.find("#crtrId").text("");
    subCommonCodeInfo.find("#mdfDt").text("");
    subCommonCodeInfo.find("#mdfrId").text("");

    $("#modifyCode").attr("disabled", "disabled");
    $("#delCode").attr("disabled", "disabled");
    $("#newCode").removeAttr("disabled");
    $("#newCode").text("신규");
  }

  function clearSubCodeInfo() {
    var subCommonCodeInfo = $(".sub-code-info");
    subCommonCodeInfo.find("#subCmcdId").val("");
    subCommonCodeInfo.find("#subCmcdPid").val("");
    subCommonCodeInfo.find("#subCmcdName").val("");
    subCommonCodeInfo.find("#subCrtDt").text("");
    subCommonCodeInfo.find("#subCrtrId").text("");
    subCommonCodeInfo.find("#subMdfDt").text("");
    subCommonCodeInfo.find("#subMdfrId").text("");

    $("#modifySubCode").attr("disabled", "disabled");
    $("#delSubCode").attr("disabled", "disabled");
    $("#newSubCode").removeAttr("disabled");
    $("#newSubCode").text("신규");
    $("#newSubCode").attr("data-mode", "new");
  }

  $("#newCode").on("click", function () {
    $("#cmcdId").removeAttr("disabled");
    var newButton = $(this);
    var mode = newButton.attr("data-mode");
    console.log("mode", mode);
    if (mode === "new") {
      clearCodeInfo();
      newButton.attr("data-mode", "save");
      $("#cmcdId").focus();
      $("#newCode").text("저장");
    } else {
      $.post(
        "/ajax/commoncode/new",
        { cmcdId: $("#cmcdId").val(), cmcdName: $("#cmcdName").val() },
        function (response) {
          if (response.data.result) {
            $("#cmcdId").attr("disabled", "disabled");
            newButton.attr("data-mode", "new");
            reloadCommonCode();
          }
        }
      );
    }
  });

  $("#newSubCode").on("click", function () {
    $("#subCmcdId").removeAttr("disabled");
    var newButton = $(this);
    var mode = newButton.attr("data-mode");

    if (mode === "new") {
      clearSubCodeInfo();
      newButton.attr("data-mode", "save");
      $("#subCmcdPid").val($("#cmcdId").val());
      $("#subCmcdId").focus();
      $("#newSubCode").text("저장");
    } else {
      $.post(
        "/ajax/commoncode/new",
        {
          cmcdId: $("#subCmcdId").val(),
          cmcdName: $("#subCmcdName").val(),
          cmcdPid: $("#cmcdId").val(),
        },
        function (response) {
          if (response.data.result) {
            $("#subCmcdId").attr("disabled", "disabled");
            newButton.attr("data-mode", "new");
            reloadSubCommonCode($("#cmcdId").val());
          }
        }
      );
    }
  });

  $("#modifyCode").on("click", function () {
    if (confirm("저장하시겠습니까?")) {
      $.post(
        "/ajax/commoncode/update",
        {
          cmcdId: $("#cmcdId").val(),
          cmcdName: $("#cmcdName").val(),
          cmcdPid: $("#cmcdPid").val(),
        },
        function (response) {
          if (response.data.result) {
            reloadCommonCode();
          }
        }
      );
    } else {
      alert("저장을 취소했습니다.");
    }
  });

  $("#modifySubCode").on("click", function () {
    if (confirm("저장하시겠습니까?")) {
      $.post(
        "/ajax/commoncode/update",
        {
          cmcdId: $("#subCmcdId").val(),
          cmcdName: $("#subCmcdName").val(),
          cmcdPid: $("#subCmcdPid").val(),
        },
        function (response) {
          if (response.data.result) {
            reloadSubCommonCode($("#subCmcdPid").val());
          }
        }
      );
    } else {
      alert("저장을 취소했습니다.");
    }
  });

  $("#delCode").on("click", function () {
    if (confirm("삭제하시겠습니까?")) {
      $.get(
        "/ajax/commoncode/delete/" + $("#cmcdId").val(),
        function (response) {
          if (response.data.result) {
            reloadCommonCode();
          }
        }
      );
    } else {
      alert("삭제를 취소했습니다.");
    }
  });

  $("#delSubCode").on("click", function () {
    if (confirm("삭제하시겠습니까?")) {
      $.get(
        "/ajax/commoncode/delete/" + $("#subCmcdId").val(),
        function (response) {
          if (response.data.result) {
            reloadSubCommonCode($("#cmcdId").val());
          }
        }
      );
    } else {
      alert("삭제를 취소했습니다.");
    }
  });

  $("table.common-code")
    .find("tr")
    .on("click", function () {
      $(this).closest("tbody").find("tr").removeClass("active");
      $(this).addClass("active");

      var commonCodeInfo = $(".code-info").not(".sub-code-info");
      commonCodeInfo.find("#cmcdId").val($(this).data("id"));
      commonCodeInfo.find("#cmcdPid").val($(this).data("pid"));
      commonCodeInfo.find("#cmcdName").val($(this).data("code-name"));
      commonCodeInfo.find("#crtDt").text($(this).data("crt-dt"));
      commonCodeInfo.find("#crtrId").text($(this).data("crtr-id"));
      commonCodeInfo.find("#mdfDt").text($(this).data("mdf-dt") || "");
      commonCodeInfo.find("#mdfrId").text($(this).data("mdfr-id") || "");

      $("#cmcdId").attr("disabled", "disabled");

      $("#modifyCode").removeAttr("disabled");
      $("#delCode").removeAttr("disabled");
      $("#newCode").removeAttr("disabled");
      $("#newCode").attr("data-mode", "new");
      $("#newCode").text("신규");

      clearSubCodeInfo();

      var pid = $(this).data("id");
      reloadSubCommonCode(pid);
    });
});
