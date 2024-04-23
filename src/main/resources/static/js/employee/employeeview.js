$().ready(function (){
    $(".delete-employee").on("click", function () {
        var chooseValue = confirm("사원정보를 삭제하시겠습니까?");
        var empId = $(this).closest(".grid").data("empId");
        if(chooseValue) {
            location.href = "/employee/delete" + empId;

        }
    })

    var empId = $("#empId").data("id")

    $.get("/ajax/change/department?empId="+empId, function(res){
        var deptHistList = res.data.departmentHistoryList
        var body = $(".dept-change").find("tbody")
        var trDom = $("<tr></tr>")
        var tdDom = $("<td></td>")

        for(var i in deptHistList){
            var item = deptHistList[i]
            tdDom.text(i+1)
            trDom.append(tdDom)
            tdDom.text(item.departmentVO.deptName)
            trDom.append(tdDom)
            tdDom.text(item.deptStrtDt)
            trDom.append(tdDom)
            tdDom.text(item.deptEndDt)
            trDom.append(tdDom)
            tdDom.text(item.cnNote)
            trDom.append(tdDomd)
            body.append(trDom)
        }
    })

 
});