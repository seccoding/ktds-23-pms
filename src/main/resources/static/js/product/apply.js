$().ready(function(){

    // 신청 form 추가
    $(".plus-btn").on("click", function () {

        var formDom = $("<form></form>");
        formDom.attr("action", "/product/apply");
        formDom.attr("method", "post");
        formDom.attr("enctype", "multipart/form-data");

        var formData = $("form").html();
        formDom.append(formData);

        $(".form-group").append(formDom);

        // 새로운 form에 비품명 선택 이벤트 바인딩
        formDom.find("#select-prdtName").on("change", autoPrdtCtgrSelect);
        formDom.find("#select-prdtName").on("change", autoPrdtCurStrSelect);
    });


    // 신청 form 삭제
    $(".minus-btn").on("click", function () {
        var formCount = $("form").length;
        if(formCount > 1){
            $("form:last").remove();
        }
        else{
            var deleteConfirm = confirm("비품 신청을 취소하시겠습니까?");
            if(deleteConfirm){
                location.href = "/product/list";
            }
        }
    });


    // 카테고리 목록
    var categories = {
        "컴퓨터기기": ["모니터", "마우스", "키보드", "프린터"],
        "컴퓨터용품": ["마이크", "화상카메라"],
        "저장장치": ["USB"],
        "가구": ["의자", "책상"],
        "가전": ["탁상용 선풍기", "가습기"],
        "사무용품": ["연필", "볼펜", "형광펜", "노트", "메모지"],
        "프린터용품": ["복사지", "토너"],
    };


    // 비품명 선택 시, 카테고리를 자동으로 선택해주는 함수
    function autoPrdtCtgrSelect(){

        // 선택한 비품명
        var namevalue = $(this).val();

        // 선택된 비품명에 속한 form을 가져옴
        var parentForm = $(this).closest("form");
        
        // 카테고리 선택
        for(var item in categories){
            if(categories[item].includes(namevalue)){
                parentForm.find("#select-prdtCtgr").val(item);
                break;
            }
        }

        
    };


    function autoPrdtCurStrSelect(){

        // 선택한 비품명
        var namevalue = $(this).val();

        // 선택된 비품명에 속한 form을 가져옴
        var parentForm = $(this).closest("form");

        var url = "/ajax/product/apply/" + namevalue;

        $.get(url, { productName: namevalue },
            function(response){
                var curstr = response.data.oneProductCurStr;
                console.log("재고: " + curstr + "개");
                parentForm.find("#apply-stock").attr("max", curstr);
            }
        );
    }


    // 비품명이 바뀔때마다 함수 호출
    // $("#select-prdtName").on("change", autoPrdtCtgrSelect);
    // $("#select-prdtName").on("change", autoPrdtCurStrSelect);
    $(".form-group").on("change", "#select-prdtName", autoPrdtCtgrSelect);
    $(".form-group").on("change", "#select-prdtName", autoPrdtCurStrSelect);



    // 신청 버튼
    $(".add-button").on("click", function(){
        var url = "/ajax/product/apply";

        var formData = {};

        $("form").each(function(index, form) {
            formData["borrowList["+index+"].productVO.prdtName"] = $(form).find("#select-prdtName").val();
            formData["borrowList["+index+"].productVO.prdtCtgr"] = $(form).find("#select-prdtCtgr").val();
            formData["borrowList["+index+"].productVO.curStr"] = $(form).find("#apply-quantity").val();
            formData["borrowList["+index+"].brrwDt"] = $(form).find("#apply-date").val();
        });

        var addConfirm = confirm("신청하시겠습니까?");
        if(addConfirm){
            $.post(url, formData, 
                function (response) {
                    location.href = response.data.next;
                }
            );
        }
        else{
            location.reload();
        }
        
    });

    
    $(".cancel-button").on("click", function() {
        var cancelConfirm = confirm("취소하시겠습니까?");
        if(cancelConfirm){
            location.href = "/product/list";
        }


        // var alertModal = $(".modal-confirm-window");
        // var modalButton = $(".confirm-confirm-button");
        // var modalButton1 = $(".cancel-confirm-button");
        // var modalText = $(".modal-confirm-text");
        // modalText.text("취소하시겠습니까?");
        // modalButton.text("확인");
        // modalButton1.text("취소");


        // // 확인 버튼 클릭 시
        // $(modalButton).on("click", function () {
        //     location.href = "/product/list";
        // });

        // // 취소 버튼 클릭 시
        // $(modalButton1).on("click", function () {
        //     alertModal[0].close();
        // });


        // alertModal[0].showModal();

        // // Modal 창 닫기 버튼 클릭
        // $(".modal-confirm-close").on("click", function () {
        //     alertModal[0].close();
        // });
        
    });


    
    
});