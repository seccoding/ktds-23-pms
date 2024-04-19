$().ready(function () {
  $(".departmentListClickFunction").on("click", function () {
    clearCodeInfo();
    clearSubCodeInfo();

    $(this).closest("tbody").find("tr").removeClass("active");
    $(this).addClass("active");

    clearSubCodeInfo();

    var commonCodeInfo = $(".code-info");
    commonCodeInfo.find("#codeDeptId").text("내일부터할것");
  });

  function reloadSubTeam(deptId) {
    $.get("/ajax/department/search/" + deptId, function (response) {
      var subTeamTable = $(".sub-team").find("tbody");
      subTeamTable.html("");
      var teamList = response.data.teamList;

      teamList.forEach((subTeam) => {
        var trDom = $("<tr></tr>");
        trDom.attr({
          "data-id": subTeam.tmId,
          "data-name": subTeam.tmName,
          "data-crdt": subTeam.tmCrDt,
          "data-tm-lead-id": subTeam.tmLeadId,
          "data-tm-dept-id": subTeam.deptId,
        });
        trDom.on("click", function () {
          $(this).closest("tbody").find("tr").removeClass("active");
          $(this).addClass("active");

          clearSubCodeInfo();

          var departmentInfo = $(".sub-code-info");
          departmentInfo.find("#codeTmId").text($(this).data("id"));
          departmentInfo.find("#codeTmName").text($(this).data("name"));
          departmentInfo
            .find("#codeTmDepartment")
            .text($(this).data("tmDeptId"));
          departmentInfo.find("#codeTmLeadId").text($(this).data("tmLeadId"));
          departmentInfo.find("#codeTmCrtDt").text($(this).data("crdt"));
        });

        var tmIdTdDom = $("<td></td>");
        tmIdTdDom.text(subTeam.tmId);

        var tmNameTdDom = $("<td></td>");
        tmNameTdDom.text(subTeam.tmName);

        trDom.append(tmIdTdDom);
        trDom.append(tmNameTdDom);

        subCodeTable.append(trDom);
      });
    });
  }
  $(".department-create").on("click", function () {
    var modal = $(".create-modal");
    modal[0].showModal();
  });

  $("#dep-cancel-button").on("click", function () {
    location.reload();
  });

  $(".dep-submit-button").on("click", function () {
    var departmentName = $("#department-name").val();
    var departmentLeader = $("#department-leader").val();
    $.post(
      "/ajax/department/create",
      { deptName: departmentName, deptLeadId: departmentLeader },
      function (response) {
        location.href = response.data.nextUrl;
      }
    );
  });

  $(".team-create").on("click", function () {
    var modal = $(".create-modal-team");
    modal[0].showModal();
  });
  $("#team-cancel-button").on("click", function () {
    location.reload();
  });

  $(".team-submit-button").on("click", function () {
    var teamName = $("#team-name").val();
    console.log(teamName);
    var teamLeader = $("#team-leader").val();
    var teamDepartment = $("#team-department").val();
    $.post(
      "/ajax/team/create",
      { tmName: teamName, tmLeadId: teamLeader, deptId: teamDepartment },
      function (response) {
        location.href = response.data.nextUrl;
      }
    );
  });

  $(".department-modify").on("click", function () {
    var modal = $(".modify-modal-dept");

    var departmentId = modal.find("#modify-select-box").val();
    $.get(
      "/ajax/department/show?departmentId=" + departmentId,
      function (response) {
        var dataDept = response.data.oneDepartment;
        modal.find("#mod-dept-id").text(dataDept.deptId);
        modal.find("#department-name-mod").val(dataDept.deptName);
        modal.find("#mod-dept-crd-dt").text(dataDept.deptCrDt);
        modal.find("#department-leader-mod").val(dataDept.deptLeadId);
      }
    );

    modal[0].showModal();
  });

  $("#modify-select-box").on("change", function () {
    var deptId = $(this).val();
    console.log(deptId);
    $.get("/ajax/department/show?departmentId=" + deptId, function (response) {
      var dataDept = response.data.oneDepartment;
      $("#mod-dept-id").text(dataDept.deptId);
      $("#department-name-mod").val(dataDept.deptName);
      $("#mod-dept-crd-dt").text(dataDept.deptCrDt);
      $("#department-leader-mod").val(dataDept.deptLeadId);
    });
  });
  $("#dep-modify-cancel-button").on("click", function () {
    location.reload();
  });
  $("#dep-modify-submit-button").on("click", function () {
    $.post(
      "/ajax/department/modify",
      {
        deptId: $("#mod-dept-id").text(),
        deptName: $("#department-name-mod").val(),
        deptLeadId: $("#department-leader-mod").val(),
      },
      function (response) {
        var returnUrl = response.data.next;
        location.href = returnUrl;
      }
    );
  });

  $(".team-modify").on("click", function () {
    var modal = $(".modify-modal-team");

    var teamId = modal.find("#modify-team-select-box").val();
    $.get("/ajax/team/show?teamId=" + teamId, function (response) {
      var dataTm = response.data.oneTeam;
      console.log(dataTm);
      modal.find("#mod-team-id").text(dataTm.tmId);
      modal.find("#team-name-mod").val(dataTm.tmName);
      modal.find("#mod-team-crd-dt").text(dataTm.tmCrDt);
      modal.find("#team-leader-mod").val(dataTm.tmLeadId);
      modal.find("#team-dept-mod").val(dataTm.deptId);
    });

    modal[0].showModal();
  });

  $("#modify-team-select-box").on("change", function () {
    var teamId = $(this).val();
    $.get("/ajax/team/show?teamId=" + teamId, function (response) {
      var dataTm = response.data.oneTeam;
      $("#mod-team-id").text(dataTm.tmId);
      $("#team-name-mod").val(dataTm.tmName);
      $("#mod-team-crd-dt").text(dataTm.deptCrDt);
      $("#team-leader-mod").val(dataTm.tmLeadId);
      $("#team-dept-mod").val(dataTm.deptId);
    });
  });
  $("#tm-modify-cancel-button").on("click", function () {
    location.reload();
  });
  $("#tm-modify-submit-button").on("click", function () {
    $.post(
      "/ajax/team/modify",
      {
        tmId: $("#mod-team-id").text(),
        tmName: $("#team-name-mod").val(),
        tmLeadId: $("#team-leader-mod").val(),
        deptId: $("#team-dept-mod").val(),
      },
      function (response) {
        var returnUrl = response.data.next;
        location.href = returnUrl;
      }
    );
  });
});
