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

        var formData = {};

        $("form").each(function(index, form) {
            formData["productList["+index+"].prdtName"] = $(form).find("#prdtName").val();
            formData["productList["+index+"].prdtCtgr"] = $(form).find("#prdtCtgr").val();
            formData["productList["+index+"].onceYn"] = $(form).find("#onceYn").val()==="소모품" ? "Y" : "N";
            formData["productList["+index+"].curStr"] = $(form).find("#curStr").val();
            formData["productList["+index+"].productManagementVO.buyDt"] = $(form).find("#buyDt").val();
            formData["productList["+index+"].productManagementVO.prdtPrice"] = $(form).find("#prdtPrice").val();
            
        });

        var addConfirm = confirm("추가하시겠습니까?");
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


    $(".product-cancel").on("click", function() {
        var reConfirm = confirm("취소하시겠습니까?");
        if(reConfirm){
            location.href="/product/manage/list";
        }
    });
    

})