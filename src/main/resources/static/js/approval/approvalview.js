$().ready(function() {
  // 중복제거 고려
  $("#btn-appr-sts-ok").on("click", function() {

    var apprId = $(this).data('appr-id');
    var apprSts = $(this).data('appr-sts');

    console.log(apprId);
    console.log(apprSts);

    var chooseValue = confirm(
      "결재를 승인합니다."
    );

    if(chooseValue) {
      $.post("/ajax/approval/statuschange/" + apprId
          ,{ apprSts: apprSts }, function (response) {
        if(response.data.result && response.data.next) {
          location.reload();
      }});
    };
  });

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