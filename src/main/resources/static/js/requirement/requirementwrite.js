$().ready(function () {
  $("#prj-id").on("change click", function () {
    var prjId = $("#prj-id option:selected").val();

    $.get("/requirement/teammate/" + prjId, function (response) {
      var prjTeammateList = response.data.prjTeammateList;
      $("#dvlrp-check").empty();
      $("#cfrmr-check").empty();
      $("#tstr-check").empty();

      for (i = 0; i < prjTeammateList.length; i++) {
        //담당개발자
        $("#dvlrp-check").append(
          "<option value" +
            prjTeammateList[i].prjTmId +
            ">" +
            prjTeammateList[i].employeeVO.empName +
            "</option>"
        );
        //확인자
        $("#cfrmr-check").append(
          "<option value" +
            prjTeammateList[i].prjTmId +
            ">" +
            prjTeammateList[i].employeeVO.empName +
            "</option>"
        );
        //테스터
        $("#tstr-check").append(
          "<option value" +
            prjTeammateList[i].prjTmId +
            ">" +
            prjTeammateList[i].employeeVO.empName +
            "</option>"
        );
      }
    });
  });
});
