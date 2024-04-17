$().ready(function(){
    $("#search-btn").on("click", function () {
        search(0);
    });

    /* 
    엔터키 눌렀을 때 form 전송이 안되도록 함
    
     */
    $("form")
    .find("input")
    .on("keydown", function (event) {
        if(event.keyCode === 13){
            var noSubmit = $(this).data("no-submit");
            if(noSubmit !== undefined){
                event.preventDefault();
            }
        }
    })

    $(".product-item").on("click", function(){
        var id = $(this).data("product")
        location.href = "/product/manage/view?prdtId=" + id
    })

    
})