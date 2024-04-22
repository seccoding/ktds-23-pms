  $().ready(function() {
      
  var apprId = $("#grid-container").data("appr-id");

  // 결재승인
  $("#btn-appr-sts-ok").on("click", function() {

    var apprSts = $(this).data('appr-sts');
    var chooseValue = confirm(
      "결재를 승인합니다."
    );

    // 1.결재 승인
    if(chooseValue) {
      $.post("/ajax/approval/statuschange/" + apprId
          ,{ apprSts: apprSts }, function (response) {
        if(response.data.result && response.data.next) {
          // 2. 기대여비품 반납
          var returnPrdt = confirm("기 대여 비품 반납???"); // 확인 후 신규제공으로 변경경
          if(returnPrdt) {
            $.post("/ajax/apprDtl/unusablePrdt", 
            {
              apprId : apprId
            }, 
            function(response) {
              if(response.data.result) {
                // 3. 새로운 비품대여
                var newPrdtBorrow = confirm("신규 비품 제공??"); // 확인 후 삭제
                if(newPrdtBorrow) {
                  $.post("/ajax/product/newPrdtBorrow", 
                    {apprId : apprId}, 
                    function(response) {
                      if(response.data.result) {
                        location.href = response.data.next;
                      }
                    });
                }
                location.href = response.data.next;
              }
            }); // 신규 비품 제공 로직 작성
          } 
          location.reload();
      }});
    };
  });

  // 결재 반려
  $("#btn-appr-sts-no").on("click", function() {

    var apprSts = $(this).data('appr-sts');
    var chooseValue = confirm( "결재를 반려합니다.");

    if(chooseValue) {
      $.post("/ajax/approval/statuschange/" + apprId ,
        { apprSts: apprSts, 
          reject: reject  
        }, 
        function (response) {
          console.log(response);
          if(response.data.result && response.data.next) {
            location.reload();
          }
        });
      // 반려사유 입력 모달
      $("#confirmButton").on("click", function(){
          var reject = $("#rejectionReason").val();
          console.log(reject);
          $.post("/ajax/approval/statuschange/" + apprId, 
            { 
              apprSts: apprSts, 
              reject: reject, 
            }, 
            function (response) {
              console.log(response);
              if(response.data.result && response.data.next) {
                location.reload();
              }
          });
      });
    }
  });

  // 결재목록 이동
  $("#btn-list-appr").on("click", function() {
    location.href = "/approval/list"
  });

  // 결재내역 삭제
  $("#btn-delete-appr").on("click", function() {
    
    var chooseValue = confirm(
      "결재 내역을 삭제합니다."
    );
    if(chooseValue) {
      $.get("/approval/delete/" + apprId, function(response) {
          if(response.data.result && response.data.next) {
            location.href = response.data.next;
          }
          alert(response.data.errorMessage);
      });
    }
  });
	
    // 모달
    $("#modal-button").on("click", function() {
    
      var apprId = $(this).data('appr-id');
      var apprSts = $(this).data('appr-sts');
      console.log(apprId);
      console.log(apprSts);

      $("#confirmButton").on("click", function(){
      var rejectionReason = $("#rejectionReason").val();
      console.log(rejectionReason);
      
      $.post("/ajax/approval/statuschange/" + apprId
            ,{ apprSts: apprSts }, function (response) {
          if(response.data.result && response.data.next) {
            location.reload();
          }
      });
    })
    });
    
    // 모달 버튼을 클릭했을 때 모달이 나타나도록 설정
    $("#modal-button").on("click", function(){
      $("#modalWrap").show();
    })
    
    // 취소 버튼을 클릭했을 때 모달을 닫는 동작 설정
    $("#modal-cancel-button").on("click", function(){
      $$("#modalWrap").hide();
    });	 
  
    // 모달을 닫는 X 버튼에 대한 동작 설정
    $("#closeBtn").on("click", function() {
        $("#modalWrap").hide();
    });  
    
    // 취소 버튼을 클릭했을 때 모달을 닫는 동작 설정
    $("#cancelButton").on("click", function() {
              $("#modalWrap").hide();
    });
  });