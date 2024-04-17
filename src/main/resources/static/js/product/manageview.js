$().ready(function(){
    var paramId = $(".body").data("paramid");
    $(".remove").on("click", function(){
        var productId = $(this).data("product");
        
        
        // var prdtId = $(this).closest("detail-table").data("prdt")
        var url = "/ajax/product/manage/view/delete/"+productId
        if(confirm("정말 삭제하시겠습니까?")){
            $.get(url, function(){
                location.href = "/product/manage/view?prdtId="+paramId

            })
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
        console.log($(".buy-day").val())
        $.post("/ajax/product/manage/view/modify",{
            prdtMngId:$(".manage-id").text(),
            prdtPrice:$(".price").val(),
            buyDt:$(".buy-day").val(),
            lostYn:$(".select").val()==="O"?"Y":"N",
            lostDt:$(".lost-day").val(),
            prdtId:paramId
        }, function(res){
            location.href = res.data.next
        })
    })
})