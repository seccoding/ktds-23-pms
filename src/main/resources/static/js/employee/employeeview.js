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

    $(".change-pstn").on("click", function(){
        $(".pstn-select-box").val($(".pstn-select-box").data("origin"))
        $(".pstn-modal")[0].showModal()
    })

    $(".change-pstn-cancel").on("click", function(){
        $(".pstn-modal")[0].close()
    })

    $(".change-pstn-btn").on("click", function(){
        var selectPstnId = $(".pstn-select-box").val()
        var pastPstnId = $(".pstn-select-box").data("origin")
        console.log(pastPstnId)
        console.log(selectPstnId)
        if(selectPstnId==pastPstnId){
            alert("동일한 직급으로 변경할 수 없습니다.")
            return
        }
        var reason = $("#pstn-change-note").val()
        $.post("/ajax/change/position", {
            empId:empId,
            pstnId:selectPstnId,
            "positionHistoryVO.pastPstnId":pastPstnId,
            "positionHistoryVO.cnNote":reason,
        }, function(res){
            if(res.data.isSuccess){
                alert("직급을 변경했습니다.")
            }else{
                alert("직급 변경 중 오류가 발생했습니다.")
            }
            location.href = res.data.next
        })
    })

 
});