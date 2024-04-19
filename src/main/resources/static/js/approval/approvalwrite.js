$().ready(function(){

  // 체크박스 전체선택,선택해제 
  $("#appr-item-checked-all").on("click", function() {
      var targetClass = $(this).data("target-class");
      var isChecked = $(this).prop("checked");
      $("." + targetClass).prop("checked", isChecked);
  });

  // 삭제버튼 누르면 제거
  $("#prdt-list").on("click", "#btn-remove-prdt", function() {
    var length = $("#prdt-list").find("tr").length;
    if (length === 1) {
      alert("삭제할 수 없습니다.\n 하나 이상의 비품을 신청해주세요.");
      return;
    }
    $(this).closest('tr').remove(); 
  });


  // 취소버튼
  $("#btn-appr-cancle").on("click", function() {
    // checked된 비품의 번호 선택해서 가져오기
    var checkedPrdtItem = 'input[name=targetPrdtDtlId]:checked';
    var checkedPrdtItemSelect = $(checkedPrdtItem);
    var checkedPrdtItemList = [];

    checkedPrdtItemSelect.each(function() {
      console.log($(this).val());
      checkedPrdtItemList.push($(this).val());
    });

    var checkedData = {
      "empId" : empId, 
      "checkedPrdtItemList" : checkedPrdtItemList}
    


    // var checkedPrdtItemList = [];

    // $('input:checkbox[name=targetPrdtDtlId]').each(function (index) {
    //   if($(this).is(":checked")==true){
    //       console.log($(this).val());
    //       var checkedPrdtItem = $(this).val();
    //       checkedPrdtItemList.push(checkedPrdtItem);
    //     }
    // });

    var chooseValue = confirm("비품 변경을 취소합니다.");

    if(chooseValue) {
      location.href = "/product/rentalstate";
    }
  });
  // "행 추가" 버튼 클릭 시 새로운 행 추가
  // $("#btn-add-prdt").on("click", function(){

  //   var prdtList = $("#prdt-list");
  //   var prdtDom = $("<tr></tr>");
  //   var prdtItemCheckDom = $("<input type='checkbox'>");
  //   var prdtItemRemoveBtn = $("<button class='btn-remove-prdt'>삭제</button>");
  //   var prdtItemCheckDom = $("<td></td>").append("<input type='checkbox'>");
  //   prdtDom.append(prdtItemCheckDom);
  //   for (var i = 0; i < 3; i++) {
  //       var prdtItemInputDom = $("<td></td>").append("<input type='text'>");
  //       prdtDom.append(prdtItemInputDom);
  //   }
  //   var prdtItemRemoveBtnDom = $("<td></td>").append(prdtItemRemoveBtn);
  //   prdtDom.append(prdtItemRemoveBtnDom);
  //   prdtList.append(prdtDom);
  // });
  

  // // 모달 관련 이벤트 처리
  // const $modal = $("#modalWrap"); // 모달 창 요소 가져오기
  // const $closeBtn = $("#closeBtn"); // 모달을 닫는 버튼(X) 요소 가져오기

  // $(document).on("click", ".openModalBtn", function() {
  //   $modal.css("display", "block"); // 모달을 열기 버튼을 클릭하면 모달을 보이게 함
  // });

  // $closeBtn.click(function() {
  //   $modal.css("display", "none"); // 모달을 닫는 버튼(X)을 클릭하면 모달을 숨김
  // });

  // $(window).click(function(event) {
  //   if (event.target === $modal[0]) {
  //     $modal.css("display", "none"); // 모달 외부를 클릭하면 모달을 숨김
  //   }
  // });
});