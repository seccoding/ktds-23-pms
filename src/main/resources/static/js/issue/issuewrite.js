$().ready(function () {
  var editors = loadEditor(".editor", "내용을 입력하세요.");

  // $("#rqm-id").on("change click", function() {
  //   var rqmId = $("#rqm-id option:selected").val();

  // });

  $("button").on("click", function (event) {
    var isCntnt = "";
    event.preventDefault();

    isCntnt = editors.getData("isCntnt");

    var fileArr = $("#file").prop("files");
    var file = fileArr[0];

    var rqmId = $("#rqm-id").val();
    var isTtl = $("#issue-title").val();
    var isId = $(".grid").data("id");

    var formData = new FormData();
    formData.append("file", file);
    formData.append("isId", isId);
    formData.append("isTtl", isTtl);
    formData.append("rqmId", rqmId);
    formData.append("isCntnt", isCntnt);

    $("#is-content").val(isCntnt);
    var type = $(this).data("type");
    var url = "/ajax/issue/" + type;

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
          location.href = "/issue";
        }
      },
    });
  });
});
