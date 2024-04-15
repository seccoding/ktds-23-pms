$().ready(function () {
  $("#approve").on("click", function () {
    var dalayApprove = true;

    console.log(dalayApprove);
    var rqmId = $("#rqmId").data("rqmId");

    console.log(rqmId);

    $.get(
      "/project/requirement/delayaccess?rqmId=" + rqmId,
      { dalayApprove: dalayApprove },
      function (respose) {
        alert("post 실행");
      }
    );
  });
});
