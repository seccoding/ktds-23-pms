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

    var paramId = $(".body").data("paramid");
    $(".remove").on("click", function(){
        var productId = $(this).data("product");
        
        
        // var prdtId = $(this).closest("detail-table").data("prdt")
        var url = "/ajax/product/manage/view/delete/"+productId
        if(confirm("정말 삭제하시겠습니까?")){
            $.get(url, function(res){
                if(res.data.result){
                    alert("정상적으로 삭제되었습니다.")
                }else{
                    alert("삭제 중 오류가 발생되었습니다.")

                }
                location.href = "/product/manage/detail"

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
            if(res.data.result){
                alert("정상적으로 수정되었습니다.")
            }else{
                alert("수정 중 오류가 발생했습니다.")
            }
            location.href = res.data.detailUrl
        })
    })
})