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

  $("#btn-appr-sts-no").on("click", function() {

    var apprId = $(this).data('appr-id');
    var apprSts = $(this).data('appr-sts');

    console.log(apprId);
    console.log(apprSts);
    
    var chooseValue = confirm(
      "결재를 반려합니다."
    );

    if(chooseValue) {
      $.post("/ajax/approval/statuschange/" + apprId
          ,{ apprSts: apprSts }, function (response) {
        if(response.data.result && response.data.next) {
          location.reload();
        }
      });
    }
  });
});