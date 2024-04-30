$().ready(function () {
  var editors = loadEditor(".editor", "내용을 입력하세요.");

  // var dialog = $(".alert-dialog");
  // if (dialog.length > 0) {
  //   dialog[0].showModal();
  // }

  $("#submit-btn").on("click", function (event) {
    var qaCntnt = "";
    event.preventDefault();

    qaCntnt = editors.getData("qaCntnt");

    var fileArr = $("#file").prop("files");
    var file = fileArr[0];

    var qaTtl = $("#qaTtl").val();
    var rqmId = $("#rqm-id").val();
    var qaId = $(this).data("key");

    var formData = new FormData();
    formData.append("file", file);
    formData.append("qaTtl", qaTtl);
    formData.append("rqmId", rqmId);
    formData.append("qaCntnt", qaCntnt);
    formData.append("qaId", qaId);

    $("#qaCntnt").val(qaCntnt);
    var type = $(this).data("type");
    var url = "/ajax/qna/" + type;

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
          location.href = "/qna";
        }
      },
    });
  });
});
