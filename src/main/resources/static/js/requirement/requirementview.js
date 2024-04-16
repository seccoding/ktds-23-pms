$().ready(function () {
  $("#approve, #refuse").on("click", function () {
    var dalayApprove = $(this).data("approve");
    var url = window.location.href;
    var rqmId = $("#rqmId").data("rqmId");

    // console.log(dalayApprove);
    // console.log(url);
    // console.log(rqmId);

    $.get(
      "/project/requirement/delayaccess?rqmId=" + rqmId,
      { dalayApprove: dalayApprove },
      function (respose) {
        var result = respose.data.result;
        var dalayApprove = respose.data.dalayApprove;
        if (dalayApprove && result) {
          alert("승인 요청 성공");
        } else if (!dalayApprove && result) {
          alert("거절 요청 성공");
        } else if (!result) {
          alert("요청 실패: 관리자에게 문의해주세요");
        }
        location.href = url;
      }
    );
  });
});
