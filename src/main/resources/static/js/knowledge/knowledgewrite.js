$().ready(function () {
  var editors = loadEditor(".editor", "내용을 입력하세요.");
  // var dialog = $(".alert-dialog");
  // if (dialog.length > 0) {
  //   dialog[0].showModal();
  // }

  $("#submit-btn").on("click", function (event) {
    var knlCntnt = "";
    event.preventDefault();

    knlCntnt = editors.getData("knlCntnt");

    var fileArr = $("#file").prop("files");
    var file = fileArr[0];

    var knlTtl = $("#knlTtl").val();
    var rqmId = $("#rqm-id").val();
    var knlId = $(this).data("key");

    var formData = new FormData();
    formData.append("file", file);
    formData.append("knlTtl", knlTtl);
    formData.append("rqmId", rqmId);
    formData.append("knlCntnt", knlCntnt);
    formData.append("knlId", knlId);

    $("#knlCntnt").val(knlCntnt);
    var type = $(this).data("type");
    var url = "/ajax/knowledge/" + type;

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
          location.href = "/knowledge";
        }
      },
    });
  });
});
