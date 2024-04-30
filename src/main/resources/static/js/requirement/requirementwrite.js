$().ready(function () {
  var editors = loadEditor(".editor", "내용을 입력하세요.");
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

    rqmCntnt = editors.getData("rqmCntnt");

    var fileArr = $("#file").prop("files");
    var file = fileArr[0];

    var prjId = $("#prj-id").val();
    var rqmTtl = $("#rqm-ttl").val();
    var dvlrp = $("#dvlrp-check").val();
    var cfrmr = $("#cfrmr-check").val();
    var tstr = $("#tstr-check").val();
    var strtDt = $("#start-date").val();
    var endDt = $("#end-date").val();
    var scdSts = $("#scd-sts").val();
    var rqmSts = $("#rqm-sts").val();
    var rqmId = $(".grid").data("rqm-id");

    var formData = new FormData();
    formData.append("file", file);
    formData.append("prjId", prjId);
    formData.append("rqmTtl", rqmTtl);
    formData.append("dvlrp", dvlrp);
    formData.append("tstr", tstr);
    formData.append("cfrmr", cfrmr);
    formData.append("strtDt", strtDt);
    formData.append("endDt", endDt);
    formData.append("scdSts", scdSts);
    formData.append("rqmSts", rqmSts);
    formData.append("rqmCntnt", rqmCntnt);
    formData.append("rqmId", rqmId);

    $("#rqm-cntnt").val(rqmCntnt);
    var type = $(this).data("type");
    var url = "/ajax/requirement/" + type;

    $.ajax({
      url: url,
      type: "POST",
      data: formData,
      processData: false,
      contentType: false,
      data: formData,
      success: function (response) {
        var errors = response.data.error;
        $(".error").remove();
        if (errors) {
          for (var key in errors) {
            var errorDiv = $("<div></div>");
            errorDiv.addClass("error");

            var values = errors[key];

            for (var i in values) {
              var errorValue = values[i];
              var error = $("<div></div>");
              error.text(errorValue);
              errorDiv.append(error);
              $("input[name=" + key + "]").after(errorDiv);
              $("select[name=" + key + "]").after(errorDiv);
            }
          }
        }
        if (response.data.result) {
          location.href = "/requirement/search?prjId=";
        }
      },
    });
  });
});
