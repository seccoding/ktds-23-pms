$().ready(function () {
  $(document).keyup(function () {
    var outTtl = $("#out-ttl").val();

    if (outTtl.length === 0) {
      var ttlDom = $("<div>제목을 입력해주세요</div>");
      ttlDom.addClass("ttlAlert");
      $(".ttlAlert").remove();
      $(".ttlInput").append(ttlDom);
    } else {
      $(".ttlAlert").remove();
    }
  });

  $("button").on("click", function () {
    var prjId = $("#prj-id").val();
    var outType = $("#out-type").val();
    var outVer = $("#out-ver").val();
    var outTtl = $("#out-ttl").val();
    var fileArr = $("#file").prop("files");
    var file = fileArr[0];

    if (file === undefined) {
      loadModal({
        content: "파일은 필수입니다",
        showNegativeBtn: false,
      });
      return;
    }

    formData = new FormData();
    formData.append("prjId", prjId);
    formData.append("outType", outType);
    formData.append("outVer", outVer);
    formData.append("outTtl", outTtl);
    formData.append("file", file);

    $.ajax({
      url: "/ajax/output/write",
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
          location.href = "/output";
        }
      },
    });
  });
});
