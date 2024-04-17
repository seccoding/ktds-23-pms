$().ready(function () {
  $("#approve, #refuse").on("click", function () {
    var dalayApprove = $(this).data("approve");
    var url = window.location.href;
    var rqmId = $("#rqmId").data("rqmId");

    $.get(
      "/project/requirement/delayaccess?rqmId=" + rqmId,
      { dalayApprove: dalayApprove },
      function (response) {
        var result = response.data.result;
        var dalayApprove = response.data.dalayApprove;
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

  $("#delay-request").on("click", function () {
    var rqmId = $("#rqmId").data("rqmId");
    var url = window.location.href;

    $.get(
      "/project/requirement/delaycall?rqmId=" + rqmId,
      {},
      function (response) {
        var result = response.data.result;
        if (result) {
          alert("요청 성공");
        } else {
          alert("요청 실패: 관리자에게 문의해주세요");
        }
        location.href = url;
      }
    );
  });
});
