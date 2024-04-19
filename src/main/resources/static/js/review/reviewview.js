// $().ready(function () {
// 	var deleteReply = function (event) {
// 	    console.log("삭제를 클릭함", event);
// 	    var target = event.currentTarget;
// 	    var reply = $(target).closest(".reply");
// 	    var replyId = reply.data("reply-id");
// 	    // console.log("replyId", replyId);

// 	    // txt-reply에서 mode라는 데이터 제거 -> 입력란이 수정모드가아닌 상태로 되돌아가는것
// 	    $("#txt-reply").removeData("mode"); //현재 모드를 없애는것. mode에 저장되어있는 데이터변수를 textreply에서 지우는것
// 	    // txt-reply에서 target이라는 데이터 제거 -> 수정대상이 더이상 지정되지 않음을 의미.
// 	    // (수정작업이 완료되면 해당데이터는 필요하지 않으므로)
// 	    $("#txt-reply").removeData("target"); //mode에 저장되어있는 데이터변수를 textreply에서 지우므로 다음 댓글을 나중에 달수있게됨

// 	    if (confirm("댓글을 삭제하시겠습니까?")) {
// 	      $.get("/ajax/board/reply/delete/" + replyId, function (response) {
// 	        var result = response.result;
// 	        if (result) {
// 	          loadReplies(boardId);
// 	          $("txt-reply").val("");
// 	        }
// 	      });
// 	    }
// 	  };
// });
