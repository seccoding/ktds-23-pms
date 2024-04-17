$().ready(function () {
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
});
