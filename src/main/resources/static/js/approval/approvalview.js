$().ready(function() {
      
  var apprId = $("#grid-container").data("appr-id");
  var url = "/ajax/approval/statuschange/" + apprId; 

  // 승인,신규대여 버튼 비활성화
  var apprRntlArray = []; 
  // var apprRntl = $(".appr-rntl").data("appr-rntl");
  $(".appr-rntl").each(function() {
    apprRntlArray.push($(this).data("appr-rntl"));
  })
  for(var checkedRntl of apprRntlArray) {
    if(checkedRntl === 'N') {
      $("#btn-appr-sts-ok").attr({
        "disabled" : true
      });
      $("#btn-brrw-prdt").attr({
        "disabled" : true
      });
    }
  }

  // 결재승인
  $("#btn-appr-sts-ok").on("click", function() {
    var apprSts = $(this).data("appr-sts");
    var rntlSts = $(this).data("rntl-sts");

    var params = {
      apprSts : apprSts, 
      rntlSts : rntlSts, 
    };
    // var chooseValue = confirm("결재를 승인합니다.");
    loadModal({
      content: "결재를 승인합니다.",
      fnPositiveBtnHandler: function () {

        $.post(url, params, function(response) {
          if(response.data.result) {

            loadModal({
              content: "결재가 승인되었습니다.",
              fnPositiveBtnHandler: function () {
                location.reload();
              },showNegativeBtn: false,
            });
          } else {
            loadModal({
              content: response.data.errorMessage,
              fnPositiveBtnHandler: function () {
                location.reload();
              },showNegativeBtn: false,
            });

          }
        });

      }, fnNegativeBtnHandler: function () {
        alertModal[0].close();
      },

  });

  });

  // 결재반려
  $("#btn-appr-sts-no").on("click", function() {

    
    // 결재 반려 확인 모달
    var alertModal = $(".modal-confirm-window");
    var modalButton = $(".confirm-confirm-button");
    var modalButton1 = $(".cancel-confirm-button");
    var modalText = $(".modal-confirm-text");
    modalText.text("결재를 반려하시겠습니까?");
    modalButton.text("반려");
    modalButton1.text("취소");
    alertModal[0].showModal();

    // 반려 사유 입력 모달
    $(modalButton).on("click", function() {
      alertModal[0].close();
      
      var rjctModal = $(".modal-confirm-window");
      var modalBody = $(".modal-confirm-content");
      var rjctBtn = $(".confirm-confirm-button");
      var modalButton1 = $(".cancel-confirm-button");
      var modalText = $(".modal-confirm-text");
      var rjctDivDom = $('<div></<div>');
      var rjctInputDom = $('<input id="arrpRjct" type="text" name="arrpRjct"/>');
      rjctDivDom.append(rjctInputDom);
      modalBody.append(rjctDivDom);
      modalBody.css({
        "flex-direction" : "column", 
        "justify-content" : "center",
      });
      modalText.text("결재사유를 입력해주세요");
      rjctBtn.text("반려");
      modalButton1.text("취소");
      rjctModal[0].showModal();

      // 반려 기능 수행
      $(rjctBtn).on("click", function() {

        var apprSts = $("#btn-appr-sts-no").data("appr-sts");
        var arrpRjct = $(rjctInputDom).val();
        var params = {
          apprSts : apprSts, 
          arrpRjct : arrpRjct
        };
        
        if(arrpRjct !== "" && arrpRjct !== null) {
          $.post(url, params, function(response) {
            if(response.data.result) {
              // alert("결재가 반려되었습니다.");
              loadModal({
                content: "결재가 반려되었습니다.",
                fnPositiveBtnHandler: function () {
                  location.reload();
                },
                showNegativeBtn: false,
              });
              // location.reload();
            } else {
              // alert(response.data.errorMessage);
              loadModal({
                content: response.data.errorMessage,
                fnPositiveBtnHandler: function () {
                  location.reload();
                },
                showNegativeBtn: false,
              });
            }
          });
          rjctModal[0].close();
        } else {
          rjctDivDom.html("");
          var alertDiv = $("<span>결재 사유를 입력해주세요.</span>");
          rjctDivDom.append(alertDiv);
        }
      });

    });
  });

  // 비품 반납
  $("#btn-return-prdt").on("click", function() {
    // 비품 반납 확인 모달
    var returnPrdtModal = $(".modal-confirm-window");
    var returnBtn = $(".confirm-confirm-button");
    var modalButton1 = $(".cancel-confirm-button");
    var modalText = $(".modal-confirm-text");
    modalText.text("변경 신청한 비품을 반납합니다.");
    returnBtn.text("확인");
    modalButton1.text("취소");
    returnPrdtModal[0].showModal();

    // 반납
    $(returnBtn).on("click", function() {
      var rntlSts = $("#btn-return-prdt").data("rntl-sts");
      $.post("/ajax/approval/unusablePrdt", 
        { apprId : apprId, 
          rntlSts : rntlSts }, 
        function(response) {
          if(response.data.result) {

            loadModal({
              content: "기대여 비품이 반납되었습니다.",
              fnPositiveBtnHandler: function () {
                location.reload();
              },
              showNegativeBtn: false,
            });
            
          } else {

            loadModal({
              content: response.date.errorMessage,
              fnPositiveBtnHandler: function () {
                location.reload();
              },
              showNegativeBtn: false,
            });
          }
      });
      returnPrdtModal[0].close();
    });
  });
  
  // 신규 비품 대여
  $("#btn-brrw-prdt").on("click", function() {
    var rntlSts = $(this).data("rntl-sts");

    // 비품 반납 확인 모달
    var brrwPrdtModal = $(".modal-confirm-window");
    var brrwBtn = $(".confirm-confirm-button");
    var modalButton1 = $(".cancel-confirm-button");
    var modalText = $(".modal-confirm-text");
    modalText.text("신규 비품을 대여합니다.");
    brrwBtn.text("승인");
    modalButton1.text("취소");
    brrwPrdtModal[0].showModal();

    // 대여
    $(brrwBtn).on("click", function() {
      console.log(apprId, "!!!!!!!!!");
      $.post("/ajax/product/newprdtborrow", 
      { apprId : apprId, 
        rntlSts : rntlSts }, 
      function(response) {
        if(response.data.result) {
          loadModal({
            content: "신규 비품이 대여되었습니다.",
            fnPositiveBtnHandler: function () {
              location.reload();
            },
            showNegativeBtn: false,
          });
        } else {
          loadModal({
            content: response.date.errorMessage,
            fnPositiveBtnHandler: function () {
              location.reload();
            },
            showNegativeBtn: false,
          });
        }
      });
      brrwPrdtModal[0].close();
    });
  });

  // 확인 모달 닫기
  $(".modal-confirm-close").on("click", function () {
    $(".modal-confirm-window")[0].close();
  });

  // 확인 모달 취소
  $(".cancel-confirm-button").on("click", function() {
    $(".modal-confirm-window")[0].close();
  })

  // 결재내역 삭제
  $("#btn-delete-appr").on("click", function() {

    loadModal({
      content: "결재 내역을 삭제합니다.",
      fnPositiveBtnHandler: function () {
        $.get("/ajax/approval/delete/" + apprId, function(response) {
          if(response.data.result && response.data.next) {
            location.href = response.data.next;
          }
          loadModal({
            content: response.data.errorMessage,
            fnPositiveBtnHandler: function () {
              alertModal[0].close();
            },
            showNegativeBtn: false,
          });
          // alert(response.data.errorMessage);
      });
      },
      showNegativeBtn: true,fnNegativeBtnHandler: function () {
        alertModal[0].close();
      },
    });

  });

});