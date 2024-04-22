$().ready(function() {
  $("#list-size").on("change", function () {
        search(0);
    });
	
    $("#search-btn").on("click", function(){
		var searchKeyword = $("#searchKeyword").val();
        console.log("searchKeyword:", searchKeyword)
        
        // searchType 가져오기
        var searchType = $(".search-category select").val();
        console.log("searchType:", searchType);
        
        
        $.post("/approval/list", 
            {
                searchKeyword: $("#searchKeyword").val(),
                searchType: $(".search-category select").val(),
        }, 
        function (response) {
			
            var html = "";
            $.each(response.apprList.apprList, function(index, approval) {
                html += "<tr>";
                html += "<td><input type='checkbox' class='target-appr-id' name='targetApprId' value='" + approval.apprId + "'></td>";
                html += "<td>" + approval.dmdDt + "</td>";
                html += "<td>" + (approval.apprCtgr === '902' ? "<span>비품변경</span>" : "") + "</td>";
                html += "<td>" + approval.apprId + "</td>";
                html += "<td><a href='/approval/approvalview?apprId=" + approval.apprId + "'>" + approval.apprTtl + "</a></td>";
                html += "<td>" + approval.employeeVO.empName + "</td>";
                html += "<td>";
                if (approval.apprSts !== '802') {
                    html += "<button onclick='state(\"" + approval.apprId + "\")'>결재</button>";
                }
                if (approval.apprSts === '801') {
                    html += "<button>결재대기</button>";
                }
                if (approval.apprSts === '802') {
                    html += "<button>결재승인</button>";
                }
                if (approval.apprSts === '803') {
                    html += "<button>결재반려</button>";
                }
                html += "</td>";
                html += "</tr>";
            });
            // 테이블 업데이트
            $(".table tbody").html(html);
        
        });
		
	}) 
});