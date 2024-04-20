$().ready(function(){

    $(".plus-btn").on("click", function () {
        /* 새로운 form 추가 */
        /* <form action="/product/manage/add" method="post" enctype="multipart/form-data"></form> */
        var formDom = $("<form></form>");
        formDom.attr("action", "/product/manage/add");
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
            var deleteConfirm = confirm("비품 추가를 취소하시겠습니까?");
            if(deleteConfirm){
                location.href = "/product/manage/list";
            }
        }
    });


    $(".product-add").on("click", function(){
        var url = "/ajax/product/manage/add";

        // var formData = {};

        // $("form").each(function(index, form) {
        //     formData["productList["+index+"].prdtName"] = $(form).find("#prdtName").val();
        //     formData["productList["+index+"].prdtCtgr"] = $(form).find("#prdtCtgr").val();
        //     formData["productList["+index+"].onceYn"] = $(form).find("#onceYn").val()==="소모품" ? "Y" : "N";
        //     formData["productList["+index+"].curStr"] = $(form).find("#curStr").val();
        //     formData["productList["+index+"].buyDt"] = $(form).find("#buyDt").val();
        //     formData["productList["+index+"].prdtPrice"] = $(form).find("#prdtPrice").val();
        // });


        // var allFormData = ''; // 모든 form 데이터를 담을 변수 초기화
        // $('form').each(function () {// 모든 form 요소를 반복
        //     var formData = $(this).serialize(); //현재 form의 데이터를 가져옴 a11FormData += formData + '&'; // 모든 form 데이터에 추가
        //     allFormData += formData + '&';
        // });
        // // 마지막'&' 문자 제거
        // allFormData = allFormData. slice(0, -1) ;
        
        // $.post(url, allFormData, 
        // function (response) {
        //     var addConfirm = confirm("추가하시겠습니까?");
        //     if(addConfirm){
        //         location.href = response.data.next;
        //     }
        // });

        $.post(url, 
            {
                prdtName: $("#prdtName").val(),
                prdtCtgr: $("#prdtCtgr").val(),
                onceYn: $("#onceYn").val()==="소모품" ? "Y" : "N",
                curStr: $("#curStr").val(),
                buyDt: $("#buyDt").val(),
                prdtPrice: $("#prdtPrice").val(),

        }, 
        function (response) {
            var addConfirm = confirm("추가하시겠습니까?");
            if(addConfirm){
                location.href = response.data.next;
            }
        });
        
    });


    $(".product-cancel").on("click", function() {
        var reConfirm = confirm("취소하시겠습니까?");
        if(reConfirm){
            location.href="/product/manage/list";
        }
    });
    

})