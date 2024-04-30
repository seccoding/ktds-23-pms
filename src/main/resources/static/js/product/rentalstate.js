$().ready(function () {
  $("#search-btn").on("click", function () {
    search(0);
  });

  $("#list-size").on("change", function () {
    search(0);
  });

  /* 
  엔터키 눌렀을 때 form 전송이 안되도록 함
   var alertModal = $(".modal-window");
          var modalButton = $(".confirm-button");
          var modalText = $(".modal-text");
          modalText.text("수정 중 오류가 발생했습니다.");
          modalButton.text("확인");

          alertModal[0].showModal();
          $(".confirm-button").on("click", function () {
            alertModal[0].close();
          });



  */
  $("form")
    .find("input")
    .on("keydown", function (event) {
      if (event.keyCode === 13) {
        var noSubmit = $(this).data("no-submit");
        if (noSubmit !== undefined) {
          event.preventDefault();
        }
      }
    });

  $("#checked-all").on("click", function () {
    var isChecked = $(this).prop("checked");
    $(".target-checkbox").prop("checked", isChecked);
  });

  $(".target-checkbox").on("click", function () {
    var total = $(".target-checkbox").length;
    var checkedBox = $(".target-checkbox:checked").length;
    total === checkedBox
      ? $("#checked-all").prop("checked", true)
      : $("#checked-all").prop("checked", false);
  });

  $(".return-btn").on("click", function () {
    var id = $(this).val();
    var manageId = $(this).data("prdtmgid");
    var confirm = false;
    loadModal({
      content: "정말 반납하시겠습니까?",
      fnPositiveBtnHandler: function () {
        confirm = true;
        if (confirm == true) {
          $.get(
            "/ajax/product/rentalstate/return",
            {
              brrwHistId: id,
              prdtMngId: manageId,
            },
            function (res) {
              if (res.data.isSuccess) {
                loadModal({
                  content: "정상적으로 반납처리되었습니다.",
                  fnPositiveBtnHandler: function () {
                    location.reload();
                    alertModal[0].close();
                  },
                  showNegativeBtn: false,
                });

              } else {
                loadModal({
                  content: "반납처리 중 오류가 발생했습니다.",
                  fnPositiveBtnHandler: function () {
                    alertModal[0].close();
                  },
                  showNegativeBtn: false,
                });

              }
            }
          );
        }
      },
      showNegativeBtn: true,
      fnNegativeBtnHandler: function () {
        confirm = false;
        location.reload();
      },
    });

  });

  $(".selected-change-apply").on("click", function () {
    var url = "/ajax/product/apply";

    var changeApplyPrdts = $(".target-checkbox:checked");

    var param = {};

    if (changeApplyPrdts.length == 0) {
      loadModal({
        content: "선택된 항목이 없습니다.",
        fnPositiveBtnHandler: function () {
          alertModal[0].close();
          location.href = res.data.next;
        },
        showNegativeBtn: false,
      });

     
    }

    changeApplyPrdts.each(function (index, data) {
      param["borrowList[" + index + "].brrwHistId"] = $(data).val(); // 대여이력 ID
      param["borrowList[" + index + "].prdtMngId"] = $(data).data("prdtmgid"); // 비품관리 ID
      param["borrowList[" + index + "].productVO.prdtName"] =
        $(data).data("productName"); // 비품명
    });

    $.post(url, param, function (res) {
      var result = res.data.isSuccess;
      if (result) {
        loadModal({
          content: "선택한 항목의 변경신청이 완료되었습니다.",
          fnPositiveBtnHandler: function () {
            location.reload();
          },
          showNegativeBtn: false,
        });

       
      } else {
        loadModal({
          content: "선택한 항목의 변경신청 처리 중 오류가 발생하였습니다.",
          fnPositiveBtnHandler: function () {
            alertModal[0].close();
            location.href = res.data.next;
          },
          showNegativeBtn: false,
        });

        
      }
    });
  });

  $(".selected-return").on("click", function () {
    var brrwProducts = $(".target-checkbox:checked");
    console.log(brrwProducts);
    var param = {};
    brrwProducts.each(function (idx, data) {
      param["borrowList[" + idx + "].brrwHistId"] = $(data).val();
      param["borrowList[" + idx + "].prdtMngId"] = $(data).data("prdtmgid");
    });
    console.log(param);
    if (brrwProducts.length == 0) {
      loadModal({
        content: "선택된 항목이 없습니다.",
        fnPositiveBtnHandler: function () {
          alertModal[0].close();
          location.href = res.data.next;
        },
        showNegativeBtn: false,
      });

    }

    $.get("/ajax/product/rentalstate/selectedreturn", param, function (res) {
      if (res.data.isSuccess) {
        loadModal({
          content: "선택한 항목의 비품이 모두 반납처리 되었습니다.",
          fnPositiveBtnHandler: function () {
            location.reload();
          },
          showNegativeBtn: false,
        });

       
      } else {
        loadModal({
          content: "선택한 항목의 반납처리 중 오류가 발생 되었습니다.",
          fnPositiveBtnHandler: function () {
            alertModal[0].close();
            location.href = res.data.next;
          },
          showNegativeBtn: false,
        });

       
      }
    });
  });
});
