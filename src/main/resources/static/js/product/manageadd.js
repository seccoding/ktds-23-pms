$().ready(function(){

    $("#add-group")

    $("#cancel").on("click", function(){
        var reConfirm = confirm("취소하시겠습니까?")
        if(reConfirm){
            location.href="/product/manage/list"
        }
    })

    $("#add").on("click", function(){
        $.post("/ajax/product/manage/add", function(res){

        })
    })
})