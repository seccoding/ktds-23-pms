$().ready(function () {
  var dialog = $(".alert-dialog");
  if (dialog.length > 0) {
    dialog[0].showModal();

    $("#submit-btn").on("click", function (event) {
      var qaCntnt = "";
      event.preventDefault();

      qaCntnt = editors.getData();
      var fileArr = $("#file").prop("files");
      var file = fileArr[0];
      var qaTtl = $("#qaTtl").val();
      var rqmId = $("#rqm-id").val();

      var formData = new FormData();
      formData.append("file", file);
      formData.append("qaTtl", qaTtl);
      formData.append("rqmId", rqmId);
      formData.append("qaCntnt", qaCntnt);

      // $("#qa-cntnt").val(qaCntnt);

      $.ajax({
        url: "/ajax/qna/write",
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
            location.href = "/qna";
          }
        },
      });
    });
  }
});
