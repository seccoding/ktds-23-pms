$().ready(function(){

    $(".plus-btn").on("click", function () {

        var formDom = $("<form></form>");
        formDom.attr("action", "/product/apply");
        formDom.attr("method", "post");
        formDom.attr("enctype", "multipart/form-data");

        var formData = $("form").html();
        formDom.append(formData);

        $(".form-group").append(formDom);
    });


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


    $(".add-button").on("click", function(){
        var url = "/ajax/product/apply";

        var formData = {};

        $("form").each(function(index, form) {
            formData["borrowList["+index+"].productVO.prdtName"] = $(form).find("#select-prdtName").val();
            formData["borrowList["+index+"].productVO.prdtCtgr"] = $(form).find("#select-prdtCtgr").val();
            formData["borrowList["+index+"].productVO.curStr"] = $(form).find("#apply-quantity").val();
            formData["borrowList["+index+"].brrwDt"] = $(form).find("#apply-date").val();
        });

        var addConfirm = confirm("추가하시겠습니까?");
        if(addConfirm){
            var num = $(".apply-quantity").val();
            if(num > curstr){
                alert("현재 재고수(" + curstr + ") 보다 신청 수량이 많습니다! 다시 신청해주세요");
            }
            else{
                $.post(url, formData, 
                    function (response) {
                        location.href = response.data.next;
                    }
                );
            }
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


    var categories = {
        "컴퓨터기기": ["모니터", "마우스", "키보드", "프린터"],
        "컴퓨터용품": ["마이크", "화상카메라"],
        "저장장치": ["USB"],
        "가구": ["의자", "책상"],
        "가전": ["탁상용 선풍기", "가습기"],
        "사무용품": ["연필", "볼펜", "형광펜", "노트", "메모지"],
        "프린터용품": ["복사지", "토너"],
    };



    var oneProduct;
    var curstr;

    $("#select-prdtName").on("change", function () {
        var nameValue = $(this).val();
        console.log(nameValue);
        
        for(var item in categories){
            if(categories[item].includes(nameValue)){
                $("#select-prdtCtgr").val(item);
                break;
            }
        }

        $.post("/ajax/product/apply",
            { prdtName: nameValue },
            function (response) {
                oneProduct = response.data.oneProduct;
                curstr = oneProduct.curStr;
            }
        );
        

    });
    
})