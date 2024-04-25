    $().ready(function(){

        // 체크박스 전체선택,선택해제 
        $("#appr-item-checked-all").on("click", function() {
            var targetClass = $(this).data("target-class");
            var isChecked = $(this).prop("checked");
            $("." + targetClass).prop("checked", isChecked);
        });


        // 추가버튼
        var addPrdtModal = $(".modal-confirm-window");
        var addPrdtBtn = $(".confirm-confirm-button");
        var prdtValues = $(".target-prdt-dtl-id");

        $("#btn-add-prdt-modal").on("click", function() {
            // 대여 목록 모달 열기
            addPrdtModal[0].showModal();

            var itemsArray = prdtValues.map(function () {
                return $(this).val();
            }).get();

            // jsp 비품id
            // console.log(itemsArray);
            
            var modalValues = $(".modal-item");
            var modalItemArray = modalValues.map(function () {
                return $(this).val();
            }).get();

            // modal 비품id 
            // console.log(modalItemArray);

            modalItemArray.forEach(function(modalItem) {
                console.log(modalItem);
                itemsArray.forEach(function(item) {
                    console.log(item);
                    if(modalItem === item) {
                        $("input[id=" + modalItem + "]").prop('disabled', true);
                    }
                });
            });
            $("input[id=" + $(".btn-remove-prdt").data("delete-item") + "]").prop('disabled', false);
            // 추가 버튼 클릭
            $(addPrdtBtn).on("click", function() {
                alert("???");
            });

        });

        // 비품 추가 모달 닫기
        $(".modal-confirm-close").on("click", function () {
            location.reload();
        });
        $(".cancel-confirm-button").on("click", function() {
            addPrdtModal[0].close();
        });
    
        // 상신버튼
        $("#btn-appr-regist").on("click", function() {
            
            // alert($("#apprMngId").val());
            // prdtId 속성을 가진 요소들을 선택
            var prdtId = $('input[name="prdtId"]:checked');
            var formData = {};
            // 각 요소의 값을 배열에 담기
            prdtId.each(function(index) {
                formData["productListVO[" + index + "].prdtId"] = ($(this).val());
                // console.log($(this).val());
        }); 

        // console.log(formData);
    
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

        // 삭제버튼 누르면 제거
        $(".btn-remove-prdt").on("click", function() {
            var length = $("#prdt-list").find("tr").length;
            if (length === 1) {
                alert("삭제할 수 없습니다.\n 하나 이상의 비품을 신청해주세요.");
                return;
            }
            
            console.log($("input[id=" + $(this).data("delete-item") + "]").val());
            $(this).closest("tr").remove(); 
            
            // $("input[id=" + $(this).data("delete-item") + "]").prop('disabled', false);
            
        });
    });