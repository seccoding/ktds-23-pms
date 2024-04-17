$().ready(function(){
	
  $(".delete-button").click(function () {
    
    const id = $(this).closest("div").data("id");
    
    if (confirm("쪽지를 삭제하시겠습니까?")) {
      $.ajax({
        url: "/ajax/memo/delete/" + id ,
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
          console.log(data.data.result);
          if(data.data.result === true){
			$("#" + id).remove();
		    // 이전 페이지의 URL을 세션 스토리지에 저장
		    sessionStorage.setItem("previousPage", document.referrer);
		    // 디버그용 메시지
		    console.log("이전 페이지 URL:", document.referrer);
		    console.log("저장된 URL:", sessionStorage.getItem("previousPage"));
		    // 이전 페이지로 리다이렉션
		    var previousPage = sessionStorage.getItem("previousPage");
		    if (previousPage) {
		        window.location.href = previousPage;
		    } else {
		        // 이전 페이지가 없는 경우, 기본 페이지로 이동
		        window.location.href = "/memo/sent";
		    }
		  } else {
			alert("삭제에 실패했습니다. 잠시후 재시도해주세요.")
		  }
        },
        error: function (request, status, error) {
          console.log("error...");
        },
        complete: function () {
          console.log("complete...");
        },
      });
    }
  });
});