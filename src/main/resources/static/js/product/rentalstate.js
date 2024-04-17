$().ready(function(){
    $(".return-btn").on("click", function(){
        var id = $(this).data("id")
        var manageId = $(".manage-id").text()
        if(confirm("정말 반납하시겠습니까?")){
            $.get("/ajax/product/rentalstate/return", {
                brrwHistId:id,
                prdtMngId:manageId
            })
            location.reload();
        }
    })
})
