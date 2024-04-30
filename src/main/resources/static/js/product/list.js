$().ready(function(){

    $("#product-exist").data("checkstatus") ? $("#product-exist").prop("checked", true) : $("#product-exist").prop("checked", false);

    function search(pageNo) {
        var searchForm = $("#search-form");
        $("#page-no").val(pageNo);
        $("#is-check").val($("#product-exist").is(":checked"));
    
        searchForm.attr("method", "get").attr("action", "/product/list").submit();
    }


    $("#search-btn").on("click", function () {
        search(0);
    });

    $("#list-size").on("change", function () {
        search(0);
    });

    /* 
    엔터키 눌렀을 때 form 전송이 안되도록 함
    
     */

    $("#product-exist").on("click", function(){
        search($(".active").find("a").text()-1);
    });


    $("form")
    .find("input")
    .on("keydown", function (event) {
        if(event.keyCode === 13){
            var noSubmit = $(this).data("no-submit");
            if(noSubmit !== undefined){
                event.preventDefault();
            }
        }
    });


    $(".apply-product").on("click", function () {
        location.href = "/product/apply";
    });


    
})