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

    $.post(
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

  $("#delete").on("click", function () {
    var rqmId = $(".grid").data("rqm-id");

    $.post(
      "/project/requirement/delete",
      { rqmId: rqmId },
      function (response) {
        var result = response.data.result;
        var url = response.data.url;
        if (result) {
          alert("삭제완료");
          location.href = url;
        } else {
          alert("삭제권한이 없습니다");
        }
      }
    );
  });

  $("#modify").on("click", function () {
    var rqmId = $(".grid").data("rqm-id");
    var prjId = $(".grid").data("prj-id");

    var sessionId = $(".grid").data("session-id");
    var crtrId = $(".grid").data("crtr-id");
    var adminCode = $(".grid").data("admin-code");
    if (adminCode == 302) {
      if (crtrId != sessionId) {
        alert("권한이 없습니다");
        return;
      }
    }

    location.href =
      "/project/requirement/modify?prjId=" + prjId + "&rqmId=" + rqmId;
  });
});
