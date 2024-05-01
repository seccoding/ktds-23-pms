$().ready(function(){

    // 체크박스 전체선택,선택해제 
    $("#appr-item-checked-all").on("click", function() {
        var targetClass = $(this).data("target-class");
        var isChecked = $(this).prop("checked");
        $("." + targetClass).prop("checked", isChecked);
    });
    $("#m-appr-item-checked-all").on("click", function() {
        var targetClass = $(this).data("target-class");
        var isChecked = $(this).prop("checked");
        $("." + targetClass).prop("checked", isChecked);
    });

    // 추가버튼
    var addPrdtModal = $(".modal-confirm-window");
    var addPrdtBtn = $(".confirm-confirm-button");
    var prdtValues = $(".target-prdt-dtl-id");

    $("#btn-add-prdt-modal").on("click", function() {
    
        if(deleteItem.length === 0) {

            loadModal({
                content: "추가할 비품이 없습니다.",
                fnPositiveBtnHandler: function () {
                    alertModal[0].close();
                },
                showNegativeBtn: false,
            });
            return;
        }

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
            itemsArray.forEach(function(item) {
                if(modalItem === item) {
                    $("input[id=" + modalItem + "]").prop('disabled', true);
                    $("input[id=" + modalItem + "]").prop('checked', false);
                }
            });
        });

        for(var i = 0 ; i < deleteItem.length; i++) {
            $("input[id=" + deleteItem[i] + "]").prop('disabled', false);
        }

    });

    // $("input[id=" + $(".btn-remove-prdt").data("delete-item") + "]").prop('disabled', false);
        // 추가 버튼 클릭
        addPrdtBtn.on("click", function() {
            
            var checkedProducts = [];
            $('input[type="checkbox"][name="mPrdtId"]:checked').each(function () {
                    console.log($(this).val());
                    checkedProducts.push($(this).val());
                
            })

            console.log("checkedProducts", checkedProducts)

            $.post("/ajax/approval/addProduct", { addProducts : checkedProducts },  
                function(response) {
                    var borrowList = response.data.borrowList
                    console.log(borrowList);
                    console.log(borrowList.length);

                    // 선택한 비품 추가!
                    for (var borrow of borrowList) {
                        // var borrow = borrowList[i];
                        console.log("번호번호 >>> ", borrow.prdtMngId);

                        var borrowList = $("#prdt-list");
                        var borrowListDom = $("<tr></tr>");

                        // 체크박스 추가
                        var borrowCheckDom = $("<input type='checkbox'>");
                        borrowCheckDom.addClass("target-prdt-dtl-id");
                        borrowCheckDom.attr({
                            'id' : "x"+borrow.prdtMngId, 
                            'name' : "prdtId", 
                            'value' : borrow.prdtMngId, 
                        });
                        var borrowCheckLabelDom = $("<label>");
                        borrowCheckLabelDom.attr({
                            'for' : "x"+borrow.prdtMngId,
                        });
                        var borrowCheckTdDom = $("<td></td>").append(borrowCheckDom, borrowCheckLabelDom);
                        borrowListDom.append(borrowCheckTdDom);

                        // 비품번호 추가
                        var prdtMngIdInputDom = $("<input type='text'>");
                        prdtMngIdInputDom.attr({
                            'value' : borrow.prdtMngId, 
                            'readonly' : true,
                        });
                        var prdtMngIdInputTdDom = $("<td></td>").append(prdtMngIdInputDom);
                        borrowListDom.append(prdtMngIdInputTdDom);

                        // 종류 추가
                        var prdtCtgrInputDom = $("<input type='text'>");
                        prdtCtgrInputDom.attr({
                            'value' : borrow.productVO.prdtCtgr, 
                            'readonly' : true,
                        });
                        var prdtCtgrInputTdDom = $("<td></td>").append(prdtCtgrInputDom);
                        borrowListDom.append(prdtCtgrInputTdDom);

                        // 품목 추가
                        var prdtNameInputDom = $("<input type='text'>");
                        prdtNameInputDom.attr({
                            'value' : borrow.productVO.prdtName, 
                            'readonly' : true,
                        });
                        var prdtNameInputTdDom = $("<td></td>").append(prdtNameInputDom);
                        borrowListDom.append(prdtNameInputTdDom);

                        // 대여일 추가
                        var brrwDtInputDom = $("<input type='text'>");
                        brrwDtInputDom.attr({
                            'value' : borrow.brrwDt,
                            'readonly' : true,
                        });
                        var brrwDtInputTdDom = $("<td></td>").append(brrwDtInputDom);
                        borrowListDom.append(brrwDtInputTdDom);

                        // 삭제 추가
                        var prdtItemRemoveBtn = $("<button class='btn-remove-prdt'>삭제</button>");
                        prdtItemRemoveBtn.addClass("btn-remove-prdt");
                        prdtItemRemoveBtn.data("delete-item", borrow.prdtMngId);
                        prdtItemRemoveBtn.on("click", productRemoveHandler);
                        var prdtItemRemoveBtnDom = $("<td></td>").append(prdtItemRemoveBtn);
                        borrowListDom.append(prdtItemRemoveBtnDom);
                        borrowList.append(borrowListDom);
                        
                    }
                });

            // 비품 추가 모달 닫기
            deleteItem = [];

            // $('input[type="checkbox"][name="mPrdtId"]').each(function () {
            //     $(this).prop('checked', true);
            
            // });

            console.log(deleteItem);
            addPrdtModal[0].close(); 
        });

    // 비품 추가 모달 닫기
    $(".modal-confirm-close").on("click", function () {
        addPrdtModal[0].close();
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
        }); 

        formData.dmdId = $("#empId").val();
        // formData.apprMngId = $("#apprMngId").val();
        formData.apprTtl = $("#apprTtl").val();
        formData.apprCntnt = $("#apprCntnt").val();
        if($("#apprCtgr").val() === '비품변경') {
            formData.apprCtgr = '902';
        }

        if(Object.keys(formData).length < 5) {
            
            loadModal({
                content: "변경신청할 비품을 체크해주세요.",
                fnPositiveBtnHandler: function () {
                    alertModal[0].close();
                    
                },
                showNegativeBtn: false,
            });
            return;
        }

        loadModal({
            content: "작성한 기안서를 상신합니다.",
            fnPositiveBtnHandler: function () {
                $.ajax({
                    url: "/ajax/approval/write", 
                    type: "POST",
                    data: formData,
                    success: function(response) {
                        var data = response.data;
                        var errors = data.errors;

                        if(data.result && data.next) {
                            location.href = data.next;

                        } else if(errors) {
                            console.log(errors, "errors");
                            for (var key in errors) {
                                var errorMessage = errors[key];

                                for (var i in errorMessage) {
                                    var message = errorMessage[i];
                                    
                                    if(key === 'apprTtl') {
                                        $("#apprTtl").attr({
                                            "placeholder" : message,
                                        });
                                        $("#apprTtl").css({
                                            "box-shadow" : "rgba(253, 54, 54, 0.5) 0px 0px 0.25em",
                                        });
                                    }
                                    if(key === 'apprCntnt') {
                                        $("#apprCntnt").attr({
                                            "placeholder" : message,
                                        });
                                        $("#apprCntnt").css({
                                            "box-shadow" : "rgba(253, 54, 54, 0.5) 0px 0px 0.25em",
                                        });
                                    }
                                }
                            } 
                        }
                    },
                });
            },
            showNegativeBtn: true,fnNegativeBtnHandler: function () {
                alertModal[0].close();
                return;
            },
        });

    });

    // 에러 css 초기화
    $("#apprTtl").on( "keyup", function() {
        $("#apprTtl").removeAttr("placeholder");
        $("#apprTtl").removeAttr("style");
    });
    $("#apprCntnt").on( "keyup", function() {
        $("#apprCntnt").removeAttr("placeholder");
        $("#apprCntnt").removeAttr("style");
    });

    // 기안서 작성 취소 버튼
    $("#btn-appr-cancel").on("click", function() {     
        
        loadModal({
            content: "기안서 작성을 취소하시겠습니까?",
            fnPositiveBtnHandler: function () {
                location.href = "/approval/progresslist";
            },
            showNegativeBtn: true,fnNegativeBtnHandler: function () {
                alertModal[0].close();
            },
        });
    });
    
    // 삭제버튼 누르면 제거
    $(".btn-remove-prdt").on("click", productRemoveHandler);
});

var deleteItem = [];
function productRemoveHandler() {
    // deleteItem = [];
    var length = $("#prdt-list").find("tr").length;
    if (length === 1) {
        loadModal({
            content: "하나 이상의 비품에 대해 변경 신청이 가능합니다.",
            fnPositiveBtnHandler: function () {
                alertModal[0].close();
                return;
            },
            showNegativeBtn: false,
        });
    }
    deleteItem.push($("input[id=" + $(this).data("delete-item") + "]").val());
    console.log("Product Delete Handler: ", deleteItem)
    $(this).closest("tr").remove(); 
}