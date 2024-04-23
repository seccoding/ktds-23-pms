$().ready(function() {

    // 체크박스 전체선택,선택해제 
    $("#prdt-check-all").on("click", function() {
        var targetClass = $(this).data("target-class");
        var isChecked = $(this).prop("checked");
        $("." + targetClass).prop("checked", isChecked);
    });

    // 기안서 작성
    $(".btn-appr-write").on("click", function() {
        location.href = "/approval/write";
    })
    // 검색
    $("#search-btn").on("click", function() {
        search(0);
    });
    // 초기화
    $("#cancel-search-btn").on("click", function() {
        location.href = "/approval/list";
    })
	
    // $("#search-btn").on("click", function(){
	// 	var searchKeyword = $("#searchKeyword").val();
    //     console.log("searchKeyword:", searchKeyword)
        
    //     // searchType 가져오기
    //     var searchType = $(".search-category select").val();
    //     console.log("searchType:", searchType);
        
        
    //     $.post("/approval/list", 
    //         {
    //             searchKeyword: $("#searchKeyword").val(),
    //             searchType: $(".search-category select").val(),
    //     }, 
    //     function (response) {
			
    //         var html = "";
    //         $.each(response.apprList.apprList, function(index, approval) {
    //             html += "<tr>";
    //             html += "<td><input type='checkbox' class='target-appr-id' name='targetApprId' value='" + approval.apprId + "'></td>";
    //             html += "<td>" + approval.dmdDt + "</td>";
    //             html += "<td>" + (approval.apprCtgr === '902' ? "<span>비품변경</span>" : "") + "</td>";
    //             html += "<td>" + approval.apprId + "</td>";
    //             html += "<td><a href='/approval/approvalview?apprId=" + approval.apprId + "'>" + approval.apprTtl + "</a></td>";
    //             html += "<td>" + approval.employeeVO.empName + "</td>";
    //             html += "<td>";
    //             if (approval.apprSts !== '802') {
    //                 html += "<button onclick='state(\"" + approval.apprId + "\")'>결재</button>";
    //             }
    //             if (approval.apprSts === '801') {
    //                 html += "<button>결재대기</button>";
    //             }
    //             if (approval.apprSts === '802') {
    //                 html += "<button>결재승인</button>";
    //             }
    //             if (approval.apprSts === '803') {
    //                 html += "<button>결재반려</button>";
    //             }
    //             html += "</td>";
    //             html += "</tr>";
    //         });
    //         // 테이블 업데이트
    //         $(".table tbody").html(html);
        
    //     });
		
	// }) 
});

function search(pageNo) {
    var serachForm = $("#search-form");
    // var listSize = $("list-size");
    $("#page-no").val(pageNo);

    // id가 search-form인 요소에 속성을 부여한다. (get)
    serachForm.attr("method", "get").submit();
}