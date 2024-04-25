$().ready(function () {
    var editors = loadEditor(
      ".editor",
      "내용을 입력하세요.",
      "${issueVO.isCntnt}"
    );

    $("#submit").on("click", function (event) {
      var isCntnt = "";
      event.preventDefault();

      isCntnt = editors.getData();
      var fileArr = $("#file").prop("files");
      var file = fileArr[0];  
      var isTtl = $("#issue-title").val();
      var rqmId = $("#rqm-id").val();

      var formData = new FormData();
      formData.append("file", file);
      formData.append("isTtl", isTtl);
      formData.append("rqmId", rqmId);
      formData.append("isCntnt", isCntnt);

      $.ajax({
        url: "/ajax/issue/write",
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
            location.href = "/issue";
          }
        },
      });
    });
});
  