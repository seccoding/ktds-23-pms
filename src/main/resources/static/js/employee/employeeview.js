$().ready(function (){
    $(".delete-employee").on("click", function () {
        var chooseValue = confirm("사원정보를 삭제하시겠습니까?");
        var empId = $(this).closest(".grid").data("empId");
        if(chooseValue) {
            location.href = "/employee/delete" + empId;

        }
    })

 
});