$().ready(function () {


    var empId = $("#empId").text()
    var canAdd = true;

    $.get("/ajax/employee/modify?empId="+empId, {
        deptId : $("#dept-select").val()
    }, function(res){
        $("#dept-select").val(res.data.employeeDept).prop("selected")

    })

    $(".change-dept-btn").on("click", function(){
        if($(".grid").data("teamlist").length!=0){
            console.log("불가능")
            alert("팀이 존재하여 부서를 변경할 수 없습니다.")
            location.reload();
        }else{
            console.log("가능")
            $("#add-team-select option").remove()
            $("#dept-change-cmt").removeClass("hidden")
            $("#hidden-selectbox").removeClass("hidden")

        }
    })

    $("#dept-select").on("change", function(){
        $("#will-add-team").html("")
    })

    $(".delete-team").on("click", function(){
        var tmName = $("#tmName").val()
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

    $("#confirmPwd").on("input", function() {
        var newPwd = $("#newPwd").val();
        var confirmPwd = $(this).val();

        if (newPwd === confirmPwd) {
            $("#password-match-status").text("비밀번호가 일치합니다.").css("color", "green");
        } else {
            $("#password-match-status").text("비밀번호가 일치하지 않습니다.").css("color", "red");
        }
    });




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

                res.data.empTeamList.forEach(team=>{
                    if(team.tmId==$("#add-team-select").val()){
                        alert("이미 속해있는 팀입니다.")
                        canAdd = false;
                    }

                })

                if(canAdd){
                    if($("#"+$("#add-team-select").val()).length==0){
                        var pDom = $("<p></p>")
                        pDom.text($("#add-team-select option:selected").text())
                        pDom.prop("id", $("#add-team-select").val()).prop("class", "will-add-team-list")
                        $("#will-add-team").append(pDom)
                        $("#will-add-team").removeClass("hidden")

                    }
                }

            })
        })

    })

    $(".save-modify").on("click", function(){
        var willAddList = {}

        $(".will-add-team-list")?.each((idx, item)=>{
            console.log($(item))
            console.log($(item).attr("id")+"!!!!")
            willAddList["teamList["+idx+"].tmId"]=$(item).attr("id")
        })
        console.log(willAddList)
        willAddList.empId = empId
        willAddList.empName = $("#empName").val()
        willAddList.workSts = $("#workSts").attr("id")
        willAddList.hireYear = $("#hireYear").val()
        willAddList.hireDt = $("#hireDt").val()
        willAddList.cntct = $("#cntct").val()
        willAddList.newPwd = $("#pwd").val()
        willAddList.addr = $("#addr").val()
        willAddList.brth = $("#brth").val()
        willAddList.email = $("#email").val()
        willAddList.deptId = $("#dept-select option:selected").val()
        willAddList["departmentHistoryVO.cnNote"] = $("#dept-change-cmt").val()
        console.log(willAddList)



        $.post("/ajax/employee/modify", willAddList, function(res){
                if(res.data.isSuccess){
                    alert("수정이 성공했습니다.")
                    location.href = res.data.next
                }else{
                    alert("수정 중 오류가 발생했습니다.")
                }
            }
        )

    })
})