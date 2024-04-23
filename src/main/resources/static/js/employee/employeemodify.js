$().ready(function () {
    
    var empId = $("#empId").val()
    
    $.get("/ajax/employee/modify?empId="+empId, function(res){
         $("#dept-select").val(res.data.employeeDept).prop("selected")
    })

    $(".dept-select").on("change", function(){
        $("#dept-change-cmt").removeClass("hidden")
    })

    $(".save-modify").on("click", function(){
        console.log($("#dept-select option:selected").val())
        $.post("/ajax/employee/modify", {
            empId:empId,
            empName:$("#empName").val(),
            workSts:$("#workSts").val(),
            hireYear:$("#hireYear").val(),
            hireDt:$("#hireDt").val(),
            deptId: $("#dept-select option:selected").val(),
            "departmentHistoryVO.cnNote":$("#dept-change-cmt").val()

        }, function(res){
            if(res.data.isSuccess){
                alert("수정이 성공했습니다.")
                location.href = res.data.next
            }else{
                alert("수정 중 오류가 발생했습니다.")
            }
        })
    })

    var dialog = $(".alert-dialog");
    if(dialog.length > 0) {
        dialog[0].showModal();
    }
})