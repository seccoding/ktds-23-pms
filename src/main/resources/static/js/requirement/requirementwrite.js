$().ready(function () {
  var editors = loadEditor(
    ".editor",
    "내용을 입력하세요.",
    "${requirement.rqmCntnt}"
  );

  // var dom = $("<p>데이터가 들어가지나요?<P>");
  // editors.setData(dom);
  // var dataq = editors.getData();
  // console.log("dom :" + dom);
  // console.log("getData :" + dataq);

  $("#prj-id").on("change click", function () {
    var prjId = $("#prj-id option:selected").val();
    console.log("prjId :" + prjId);
    $.get("/requirement/teammate/" + prjId, function (response) {
      var prjTeammateList = response.data.prjTeammateList;
      $("#dvlrp-check").empty();
      $("#cfrmr-check").empty();
      $("#tstr-check").empty();

      for (i = 0; i < prjTeammateList.length; i++) {
        //담당개발자
        $("#dvlrp-check").append(
          "<option value=" +
            prjTeammateList[i].tmId +
            ">" +
            prjTeammateList[i].employeeVO.empName +
            "</option>"
        );
        //확인자
        $("#cfrmr-check").append(
          "<option value=" +
            prjTeammateList[i].tmId +
            ">" +
            prjTeammateList[i].employeeVO.empName +
            "</option>"
        );
        //테스터
        $("#tstr-check").append(
          "<option value=" +
            prjTeammateList[i].tmId +
            ">" +
            prjTeammateList[i].employeeVO.empName +
            "</option>"
        );
      }
    });
  });

  $("button").on("click", function (event) {
    var rqmCntnt = "";
    event.preventDefault();

    rqmCntnt = editors.getData();

    $("#rqm-cntnt").val(rqmCntnt);
    $("#writeForm").submit();
  });
});
