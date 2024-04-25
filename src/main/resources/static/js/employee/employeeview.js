$().ready(function (){
    var empId = $("#empId").data("id")
    $(".delete-employee").on("click", function () {
       
        if(confirm("해당 사원을 퇴사 처리 하시겠습니까?")) {
            $.get("/ajax/employee/delete?empId="+empId , function(res){
                if(res.data.isSuccess){
                    alert("퇴사 처리가 완료됐습니다.")
                    location.href = res.data.next
                }else{
                    alert("퇴사 처리 중 오류가 발생했습니다.")
                    location.reload()
                }
            })

        }
    })



 
});