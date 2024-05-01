$().ready(function (){
    var empId = $("#empId").data("id")
    $(".delete-employee").on("click", function () {
        if(confirm("해당 사원을 퇴사 처리 하시겠습니까?")) {
            $.get("/ajax/employee/delete?empId="+empId , function(res){
                if(res.data.isSuccess){
                    loadModal({
                        content: "퇴사 처리가 완료됐습니다.",
                        fnPositiveBtnHandler: function () {
                            location.href = res.data.next;
                        },
                    });
                }else{
                    loadModal({
                        content: "퇴사 처리 중 오류가 발생했습니다.",
                        fnPositiveBtnHandler: function () {
                            location.reload();
                        },
                    });
                }
            });
        }
    });

   
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


    $(".change-job").on("click", function(){
        $(".job-select-box").val($(".job-select-box").data("origin"))
        $(".job-modal")[0].showModal()
    })

    $(".change-job-cancel").on("click", function(){
        $(".job-modal")[0].close()
    })

    $(".change-job-btn").on("click", function(){
        var selectjobId = $(".job-select-box").val()
        var pastjobId = $(".job-select-box").data("origin")
        console.log(pastjobId)
        console.log(selectjobId)
        if(selectjobId==pastjobId){
            loadModal({
                content: "동일한 직무로 변경할 수 없습니다.",
            });
            return
        }
        var reason = $("#job-change-note").val()
        $.post("/ajax/change/job", {
            empId:empId,
            jobId:selectjobId,
            "jobHistoryVO.pastJobId":pastjobId,
            "jobHistoryVO.cnNote":reason,
        }, function(res){
            if(res.data.isSuccess){
                loadModal({
                    content: "직무를 변경했습니다.",
                    fnPositiveBtnHandler: function () {
                        location.href = res.data.next;
                    },
                });
            }else{
                loadModal({
                    content: "직무 변경 중 오류가 발생했습니다.",
                    fnPositiveBtnHandler: function () {
                        location.reload();
                    },
                });
            }
        });
    })
    

 
});