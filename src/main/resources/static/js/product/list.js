$().ready(function(){
    $("#search-btn").on("click", function () {
        search(0);
    });

    $("#list-size").on("change", function () {
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
    });


    $(".apply-product").on("click", function () {
        location.href = "/product/apply";
    });

})