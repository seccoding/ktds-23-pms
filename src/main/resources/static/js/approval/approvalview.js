  $().ready(function() {
      
  var apprId = $("#grid-container").data("appr-id");
  var url = "/ajax/approval/statuschange/" + apprId; 

  // 결재승인
  $("#btn-appr-sts-ok").on("click", function() {
    var apprSts = $(this).data("appr-sts");
    var chooseValue = confirm("결재를 승인합니다.");
    var params = {apprSts:apprSts};

    if(chooseValue) {
      $.post(url, params, function(response) {
          if(response.data.result) {
            alert("결재가 승인되었습니다.");
            location.reload();
          } else {
            alert(response.data.errorMessage);
          }
      });
    }
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
              alert("결재가 반려되었습니다.");
              location.reload();
            } else {
              alert(response.data.errorMessage);
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
    alert("확인");
    // 비품 반납 확인 모달
    var returnPrdtModal = $(".modal-confirm-window");
    var returnBtn = $(".confirm-confirm-button");
    var modalButton1 = $(".cancel-confirm-button");
    var modalText = $(".modal-confirm-text");
    modalText.text("대여 비품을 반납합니다.");
    returnBtn.text("확인");
    modalButton1.text("취소");
    returnPrdtModal[0].showModal();

    $(returnBtn).on("click", function() {
      $.post("/ajax/approval/unusablePrdt", 
      { apprId : apprId }, 
      function(response) {
        if(response.data.result) {
          alert("기대여 비품이 반납되었습니다.");
          location.reload();
        } else {
          alert(response.date.errorMessage);
        }
      });
      returnPrdtModal[0].close();
    });
  });

  // 확인 모달 닫기
  $(".modal-confirm-close").on("click", function () {
    location.reload();
  });

  // 확인 모달 취소
  $(".cancel-confirm-button").on("click", function() {
    location.reload();
  })

  // 결재목록 이동
  // $("#btn-list-appr").on("click", function() {
  //  role & sts
  // });

  // 결재내역 삭제
  $("#btn-delete-appr").on("click", function() {
    
    var chooseValue = confirm("결재 내역을 삭제합니다.");
    if(chooseValue) {
      $.get("/ajax/approval/delete/" + apprId, function(response) {
          if(response.data.result && response.data.next) {
            location.href = response.data.next;
          }
          alert(response.data.errorMessage);
      });
    }
  });
});