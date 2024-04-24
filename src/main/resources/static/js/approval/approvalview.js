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
          if(response.data.result && response.data.next) {
            alert("결재가 승인되었습니다.");
          } else {
            alert(response.data.errorMessage);
          }
      });
    }
  });

  // 결재 반려
  $("#btn-appr-sts-no").on("click", function() {
    var apprSts = $(this).data("appr-sts");


    var params = {apprSts:apprSts};

    if(modalButton) {
      $.post()
    }
  });

  // 결재목록 이동
  $("#btn-list-appr").on("click", function() {
    location.href = "/approval/list"
  });

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
	
    // // 모달
    // $("#modal-button").on("click", function() {
    
    //   var apprId = $(this).data('appr-id');
    //   var apprSts = $(this).data('appr-sts');
    //   console.log(apprId);
    //   console.log(apprSts);

    //   $("#confirmButton").on("click", function(){
    //   var rejectionReason = $("#rejectionReason").val();
    //   console.log(rejectionReason);
      
    //   $.post("/ajax/approval/statuschange/" + apprId
    //         ,{ apprSts: apprSts }, function (response) {
    //       if(response.data.result && response.data.next) {
    //         location.reload();
    //       }
    //   });
    // })
    // });
    
    // // 모달 버튼을 클릭했을 때 모달이 나타나도록 설정
    // $("#modal-button").on("click", function(){
    //   $("#modalWrap").show();
    // })
    
    // // 취소 버튼을 클릭했을 때 모달을 닫는 동작 설정
    // $("#modal-cancel-button").on("click", function(){
    //   $$("#modalWrap").hide();
    // });	 
  
    // // 모달을 닫는 X 버튼에 대한 동작 설정
    // $("#closeBtn").on("click", function() {
    //     $("#modalWrap").hide();
    // });  
    
    // // 취소 버튼을 클릭했을 때 모달을 닫는 동작 설정
    // $("#cancelButton").on("click", function() {
    //           $("#modalWrap").hide();
    // });
  });