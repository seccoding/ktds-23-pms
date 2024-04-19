$().ready(function(){
    var paramId = $(".body").data("paramid");
    $(".remove").on("click", function(){
        var productId = $(this).data("product");
        var brrwYn = $(this).data("brrwyn");
        var lostYn = $(this).data("lostyn");
        var onceYn = $(this).data("onceyn");
        var url = "/ajax/product/manage/view/delete/"+productId
        if(brrwYn=='Y'&& lostYn=='N'&&onceYn=='N'){
            alert("대여중인 비품은 삭제할 수 없습니다.")
        }else{
            if(confirm("정말 삭제하시겠습니까?")){
                $.get(url, function(res){
                    if(res.data.result){
                        alert("정상적으로 삭제되었습니다.")
                    }else{
                        alert("삭제 중 오류가 발생되었습니다.")

                    }
                    location.href = "/product/manage/view?prdtId="+paramId
    
                })
            }

        }
        
    })

    $(".modify").on("click", function(){
        var modifyModal = $(".modify-modal")
        var id = $(this).data("product")
        var name = $(this).data("name")
        var url = "/ajax/product/manage/view/modify/"+id

        $.get(url, function(res){
            var product = res.data.product

            $(".manage-id").text(product.prdtMngId)
            $(".product-name").text(name)
            $(".price").val(product.prdtPrice)
            $(".buy-day").val(product.buyDt.split(" ")[0])
            $(".lost-day").val(product.lostDt?product.lostDt.split(" ")[0]:"")
            console.log(product.lostYn)
            if(product.lostYn==='Y'){
                $(".select").val("O")
            }else{
                $(".select").val("X")
            }
        })

        modifyModal[0].showModal()

    })

    $("#cancel-btn").on("click", function(){
        location.reload()
    })

    $("#modify-btn").on("click", function(){
        $.post("/ajax/product/manage/view/modify",{
            prdtMngId:$(".manage-id").text(),
            prdtPrice:$(".price").val(),
            buyDt:$(".buy-day").val(),
            lostYn:$(".select").val()==="O"?"Y":"N",
            lostDt:$(".lost-day").val(),
            prdtId:paramId
        }, function(res){
            if(res.data.result){
                alert("정상적으로 수정되었습니다.")
            }else{
                alert("수정 중 오류가 발생했습니다.")
            }
            location.href = res.data.next
        })
    })
    $(".product-name-modify").val($(".product-name-origin").text())
    if($(".product-onceyn-origin").text()==='Y'){
        $(".product-onceyn-modify").val('Y').prop('seleted')
    }else{
        $(".product-onceyn-modify").val('N').prop('seleted')
    }
    $(".product-ctgr-modify").val($(".product-ctgr-origin").text())
    
    $(".fi-rr-pencil").on("click", function(){
        $(".main-grid").toggleClass("hidden")
        $(".fi-rr-pencil").toggleClass("hidden")
        $(".fi-rs-disk").toggleClass("hidden")

    })
    $(".fi-rs-disk").on("click", function(){
        $.post("/ajax/product/manage/view/modifymain", {
            prdtId:paramId,
            prdtName:$(".product-name-modify").val(), 
            prdtCtgr:$(".product-ctgr-modify").val(),
            onceYn:$(".product-onceyn-modify").val()
        }, function(res){
            if(res.data.result){
                alert("정상적으로 수정되었습니다.")
            }else{
                alert("수정 중 오류가 발생했습니다.")
            }
            location.href = res.data.next
        })
        
    })
    
})