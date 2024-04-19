$().ready(function(){
    $(".return-btn").on("click", function(){
        var id = $(this).val()
        var manageId = $(this).data("prdtmgid")
        if(confirm("정말 반납하시겠습니까?")){
            $.get("/ajax/product/rentalstate/return", {
                brrwHistId:id,
                prdtMngId:manageId  
            }, function(res){
                if(res.data.isSuccess){
                    alert("정상적으로 반납처리되었습니다.")
                }else{
                    alert("반납처리 중 오류가 발생했습니다.")

                }
                location.href = res.data.next
            })
        }
    })

    $(".selected-return").on("click", function(){
        
        var brrwProducts = $(".checkbox:checked")
        console.log(brrwProducts)
        var param = {};
        brrwProducts.each(function(idx, data){
            param["borrowList["+idx+"].brrwHistId"] = $(data).val();
            param["borrowList["+idx+"].prdtMngId"] = $(data).data("prdtmgid");
           
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
