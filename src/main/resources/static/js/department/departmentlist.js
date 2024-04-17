$().ready(function () {
  $(".department-create").on("click", function () {
    var modal = $(".create-modal");
    modal[0].showModal();
  });

  $("#dep-cancel-button").on("click", function () {
    location.reload();
  });

  $(".dep-submit-button").on("click", function () {
    var departmentName = $("#depratment-name").val();
    var departmentLeader = $("#depratment-leader").val();
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
});
