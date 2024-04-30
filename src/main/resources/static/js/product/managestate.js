$().ready(function(){

    $("#rental-item-list").data("checkstatus")?$("#rental-item-list").prop("checked", true):$("#rental-item-list").prop("checked", false)

    function search(pageNo) {
        var searchForm = $("#search-form");
        $("#page-no").val(pageNo);
        $("#is-check").val($("#rental-item-list").is(":checked"));
    
        searchForm.attr("method", "get").attr("action", "/product/manage/state").submit();
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

    $("#rental-item-list").on("click", function(){
        

        search($(".active").find("a").text()-1);
        // $(".return-state-list").html("")
        // $.get("/ajax/manage/state",{
        //     searchType:$("#search-type").val(),
        //     searchKeyword:$("input[name=searchKeyword]").val()
        // }, function(res){
        //     var list = []
        //     if($("#rental-item-list").is(":checked")){
        //         list = res.data.notReturnList
        //     }else{
        //         list=res.data.AllBorrowList
        //     }

        //     if(list.length == 0){
        //         $(".return-state-list").append(`<tr>
        //         <td colspan="6">
        //             등록된 비품이 존재하지 않습니다.
        //         </td>
        //     </tr>`)
        //     }else{
        //         list.forEach(item=>{
        //             var trDom = $("<tr></tr>")
        //             var tdDom1 = $("<td></td>")
        //             var tdDom2 = $("<td></td>")
            
        //             trDom.append($("<td>"+item.prdtMngId+"</td>"))
        //             trDom.append($("<td>"+item.productVO.prdtName+"</td>"))
        //             trDom.append($("<td>"+item.brrwId+"</td>"))
        //             trDom.append($("<td>"+item.brrwDt+"</td>"))
                   
        //             if(item.productVO.onceYn == 'Y'){
        //                 tdDom1.text("-")
        //                 trDom.append(tdDom1)
        //                 tdDom2.text("-")
        //                 trDom.append(tdDom2)
        //             }else if(item.rtnDt != null){
        //                 tdDom1.text(item.rtnDt)
        //                 trDom.append(tdDom1)
        //                 tdDom2.text("반납완료")
        //                 trDom.append(tdDom2)
        //             }else{
        //                 tdDom1.text("-")
        //                 trDom.append(tdDom1)
        //                 tdDom2.text("대여중")
        //                 trDom.append(tdDom2)
        //             }

        //             $(".return-state-list").append(trDom)
        //         })
        //     }

        // })
    })

})