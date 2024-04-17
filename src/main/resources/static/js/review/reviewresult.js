$().ready(function () {
  $(".delete-button").click(function () {
    // const id = $(".delete-button").closest("tr").attr("id");
    const id = $(this).closest("tr").attr("id");
    // console.log("id: " + id);
    if (confirm("후기를 삭제하시겠습니까?")) {
      $.ajax({
        url: "/ajax/review/viewresult/" + id + "/delete",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
          console.log(data.data.result);
          if(data.data.result === true){
			$("#" + id).remove();
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
