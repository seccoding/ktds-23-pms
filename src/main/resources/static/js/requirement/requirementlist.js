$().ready(function(){
    $("#search-prj-id").on("click",{},function(){
        var prjId = $("#prj-id option:selected").val();

        location.href = "/requirement/search?prjId="+prjId;

        // $.get("/requirement/search?prjId="+prjId,{prjId:prjId},function(){
            
        // })
    })
});