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
        var nameValue = $(this).val();
        console.log(nameValue);
        
        // 카테고리 선택
        for(var item in categories){
            if(categories[item].includes(nameValue)){
                $(this).closest(".form-grid").find("#select-prdtCtgr").val(item);
                break;
            }
        }
    };


    // 비품명이 바뀔때마다 함수 호출
    $("#select-prdtName").on("change", autoPrdtCtgrSelect);



    // 신청 버튼
    $(".add-button").on("click", function(){
        var url = "/ajax/product/apply";

        var formData = {};

        $("form").each(function(index, form) {

            var selectedPrdtName = $(form).find("#select-prdtName").val();
            var selectedPrdtCtgr = $(form).find("#select-prdtCtgr").val();
            var selectedApplyStock = $(form).find("#apply-stock").val();

            console.log(selectedPrdtCtgr);
            console.log(selectedApplyStock);

            $.post("/ajax/product/apply",
                { prdtName: selectedPrdtName },
                function (response) {
                    // 선택된 비품의 정보들
                    oneProduct = response.data.oneProduct;

                    console.log(oneProduct.prdtCtgr);
                    console.log(oneProduct.curStr);

                    if(selectedPrdtCtgr !== oneProduct.prdtCtgr){
                        alert("카테고리가 맞지 않습니다. 해당 비품명의 카테고리는 "+oneProduct.prdtCtgr+"입니다.");
                        return false;
                    }
                    if(selectedApplyStock !== oneProduct.curStr){
                        alert("신청 수량 "+selectedApplyStock+"개가 현재 재고수 "+oneProduct.curStr+"보다 많습니다.");
                        return false;
                    }
                }
            );




            formData["borrowList["+index+"].productVO.prdtName"] = $(form).find("#select-prdtName").val();
            formData["borrowList["+index+"].productVO.prdtCtgr"] = $(form).find("#select-prdtCtgr").val();
            formData["borrowList["+index+"].productVO.curStr"] = $(form).find("#apply-stock").val();
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
    });
    
    
});