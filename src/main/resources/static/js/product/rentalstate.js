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

    $(".selected-return").on("click", function(){
        //var returnList = []
        
        var brrwProducts = $(".checkbox:checked")
        var param = {};
        brrwProducts.each(function(idx, data){
            //returnList.push($(data).val())
            param["borrowList["+idx+"].brrwHistId"] = $(data).val();
        })
        console.log(param)
        
        $.get("/ajax/product/rentalstate/selectedreturn", 
            param, 
            function(res){
                if(res.data.isSuccess){
                    alert("선택한 항목의 비품이 모두 반납처리 되었습니다.")
                }else{
                    alert("선택한 항목의 반납처리 중 오류가 발생 되었습니다.")
                    
                }
                location.href = res.data.next
            }
        )
    })
})
