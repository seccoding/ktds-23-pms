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
    
        // 상신버튼
        $("#btn-appr-regist").on("click", function() {
        
        alert($("#apprMngId").val());
    
        // prdtId 속성을 가진 요소들을 선택
        var prdtId = $('input[name="prdtId"]:checked');
        var formData = {};
        // 각 요소의 값을 배열에 담기
        prdtId.each(function(index) {
            formData["productListVO[" + index + "].prdtId"] = ($(this).val());
            // console.log($(this).val());
        }); 
    
        console.log(formData);
    
        // if(formData.productListVO.length < 1) {
        //   var checkItem = confirm("변경할 비품을 하나 이상 선택해주세요");
        //   return;
        // }
    
        formData.dmdId = $("#empId").val();
        formData.apprMngId = $("#apprMngId").val();
        formData.apprCtgr = $("#apprCtgr").val();
        formData.apprTtl = $("#apprTtl").val();
    
        $.ajax({
            url: "/ajax/approval/write", 
            type: "POST",
            data: formData,
            success: function(response) {
            console.log(response);
            var data = response.data; // reponse 체크해볼것!!! 페이지 이동안함
            if(data.result && data.next) {
                location.href = data.next;
            } else {
                alert(response.data.errorMessage);
            }
            },
        });
        });
    
        // 취소버튼
        $("#btn-appr-cancel").on("click", function() {
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