$().ready(function () {
    
    var empId = $("#empId").val()
    
    $.get("/ajax/employee/modify/", {
        deptId : $("#dept-select").val()
    }, function(res){
         $("#dept-select").val(res.data.employeeDept).prop("selected")
        
    })

    $(".dept-select").on("change", function(){

        if($(".grid").data("teamlist").length!=0){
            alert("팀이 존재하여 부서를 변경할 수 없습니다.")
            location.reload();
        }else{
        $("#add-team-select option").remove()
        $("#dept-change-cmt").removeClass("hidden")
        
    }
    })

    $(".delete-team").on("click", function(){
        var tmName = $("#tmName").text()
        if(confirm(tmName+"에서 삭제하시겠습니까?")){
            $.get("/ajax/employee/delete/team", {
                empId:empId,
                "teamVO.tmId":$(this).data("tmid")
            }, function(res){
                if(res.data.result){
                    alert("삭제되었습니다.")
                    location.href = res.data.next
                }else{
                    alert("삭제 중 오류가 발생되었습니다.")
                }
            })
        }
    })

    $(".save-modify").on("click", function(){
        if($("#dept-select option:selected").val()!=$(".dept-select").data("origin")){
           
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
                }
                )
        }
    })

    var dialog = $(".alert-dialog");
    if(dialog.length > 0) {
        dialog[0].showModal();
    }

    $("#add-team").on("click", function(){
        $("#add-team-select option").remove()
        var dialog = $(".team-modal");
        dialog[0].showModal();
        $.get("/ajax/employee/modify?empId="+empId, {
            deptId : $("#dept-select").val()
        }, function(res){
             res.data.teamList.forEach(team=>{
                 var optionDom = $("<option></option>")
                 optionDom.prop("value", team.tmId)
                 optionDom.text(team.tmName)
                 $("#add-team-select").append(optionDom)
    
             })
        })

        $("#add-team-cancel").on("click", function(){
            dialog[0].close();
        })

        $("#add-team-final").on("click", function(){
            $.get("/ajax/employee/modify?empId="+empId, function(res){
                var canAdd = true;
                 res.data.empTeamList.forEach(team=>{
                    if(team.tmId==$("#add-team-select").val()){
                        alert("이미 속해있는 팀입니다.")
                        canAdd = false;
                    }
        
                 })
                 if(canAdd){
                     $.post("/ajax/employee/modify/addteam", {
                        empId:empId,
                        "teamVO.tmId":$("#add-team-select").val()
                     }, function(res){
                        if(res.data.isSuccess){
                            alert("팀을 추가했습니다.")
                            location.href = res.data.next
                        }else{
                            alert("팀 추가 중 오류가 발생했습니다.")
    
                        }
                     })

                 }
            })
        })

    })
})