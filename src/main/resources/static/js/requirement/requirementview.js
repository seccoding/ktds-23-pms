$().ready(function () {
  $("#approve, #refuse").on("click", function () {
    var dalayApprove = $(this).data("approve");
    var url = window.location.href;
    var rqmId = $(".grid").data("rqmId");

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
                showNegativeBtn: false,
              });
              location.href = url;
            } else {
              loadModal({
                content: "삭제권한이 없습니다",
                showNegativeBtn: false,
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

    if (adminCode == 301 || crtrId == sessionId) {
      location.href =
        "/project/requirement/modify?prjId=" + prjId + "&rqmId=" + rqmId;
    } else {
      loadModal({
        content: "권한이 없습니다",
        showNegativeBtn: false,
      });
    }
  });

  $("#test-result").on("click", function () {
    var rqmId = $(".grid").data("rqmId");
    var url = window.location.href;

    loadModal({
      content: "테스트 결과를 체크해주세요",
      positiveBtnName: "성공",
      fnPositiveBtnHandler: function () {
        $.post(
          "/ajax/project/requirement/testresult?rqmId=" + rqmId,
          { testApprove: true },
          function (response) {
            var error = response.data.error;
            var result = response.data.result;
            var errorMassage = response.data.errorMassage;

            if (error) {
              loadModal({
                content: errorMassage,
                showNegativeBtn: false,
              });
            } else {
              if (result) {
                loadModal({
                  content: "결과입력 성공",
                  showNegativeBtn: false,
                  fnPositiveBtnHandler: function () {
                    location.href = url;
                  },
                });
              } else {
                loadModal({
                  content: "결과입력에 문제가 있습니다, 관리자에 문의해주세요",
                  showNegativeBtn: false,
                });
              }
            }
          }
        );
      },
      negatgiveBtnName: "실패",
      fnNegativeBtnHandler: function () {
        $.post(
          "/ajax/project/requirement/testresult?rqmId=" + rqmId,
          { testApprove: false },
          function (response) {
            var error = response.data.error;
            var result = response.data.result;

            if (error) {
              loadModal({
                content: error,
                showNegativeBtn: false,
              });
            } else {
              if (result) {
                loadModal({
                  content: "결과입력 성공",
                  showNegativeBtn: false,
                  fnPositiveBtnHandler: function () {
                    location.href = url;
                  },
                });
              } else {
                loadModal({
                  content: "결과입력에 문제가 있습니다, 관리자에 문의해주세요",
                  showNegativeBtn: false,
                });
              }
            }
          }
        );
      },
    });
  });
});
