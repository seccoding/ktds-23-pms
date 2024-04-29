$().ready(function () {
  $("#approve, #refuse").on("click", function () {
    var dalayApprove = $(this).data("approve");
    var url = window.location.href;
    var rqmId = $("#rqmId").data("rqmId");

    $.post(
      "/project/requirement/delayaccess?rqmId=" + rqmId,
      { dalayApprove: dalayApprove },
      function (response) {
        var result = response.data.result;
        var dalayApprove = response.data.dalayApprove;
        if (dalayApprove && result) {
          loadModal({
            content: "승인 요청 성공",
            fnPositiveBtnHandler: function () {
              location.href = url;
            },
            showNegativeBtn: false,
          });
        } else if (!dalayApprove && result) {
          loadModal({
            content: "거절 요청 성공",
            fnPositiveBtnHandler: function () {
              location.href = url;
            },
            showNegativeBtn: false,
          });
        } else if (!result) {
          loadModal({
            content: "요청 실패: 관리자에게 문의해주세요",
            fnPositiveBtnHandler: function () {
              location.href = url;
            },
            showNegativeBtn: false,
          });
        }
      }
    );
  });

  $("#delay-request").on("click", function () {
    var rqmId = $("#rqm-info").data("rqmId");
    var url = window.location.href;

    $.post(
      "/project/requirement/delaycall?rqmId=" + rqmId,
      {},
      function (response) {
        var result = response.data.result;
        if (result) {
          loadModal({
            content: "요청 성공",
            fnPositiveBtnHandler: function () {
              location.href = url;
            },
            showNegativeBtn: false,
          });
        } else {
          loadModal({
            content: "요청 실패: 관리자에게 문의해주세요",
            fnPositiveBtnHandler: function () {
              location.href = url;
            },
            showNegativeBtn: false,
          });
        }
      }
    );
  });

  $("#delete").on("click", function () {
    var rqmId = $("#rqm-info").data("rqm-id");
    loadModal({
      content: "삭제하시겠습니까?",
      fnPositiveBtnHandler: function () {
        $.post(
          "/project/requirement/delete",
          { rqmId: rqmId },
          function (response) {
            var result = response.data.result;
            var url = response.data.url;
            if (result) {
              loadModal({
                content: "삭제완료",
                fnPositiveBtnHandler: function () {
                  location.href = url;
                },
              });
              location.href = url;
            } else {
              loadModal({
                content: "삭제권한이 없습니다",
              });
            }
          }
        );
      },
    });
  });

  $("#modify").on("click", function () {
    var rqmId = $("#rqm-info").data("rqm-id");
    var prjId = $("#rqm-info").data("prj-id");
    var sessionId = $("#rqm-info").data("session-id");
    var crtrId = $("#rqm-info").data("crtr-id");
    var adminCode = $("#rqm-info").data("admin-code");

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
