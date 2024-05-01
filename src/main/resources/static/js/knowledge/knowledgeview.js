$().ready(function () {
  // $(".delete-knowledge").on("click", function () {
  //   var chooseValue = confirm(
  //     "이 게시글을 정말 삭제하시겠습니까?\n삭제작업은 복구할 수 없습니다."
  //   );

  //   var knlId = $(this).closest(".grid").data("id");
  //   console.log("knlId  " + knlId);

  //   if (chooseValue) {
  //     location.href = "/knowledge/delete/" + knlId;
  //   }
  // });

  $(".delete-knowledge").on("click", function () {
    loadModal({
      content:
        "이 게시글을 정말 삭제하시겠습니까?\n삭제작업은 복구할 수 없습니다.",
      fnPositiveBtnHandler: function () {
        var knlId = $(".grid").data("id");
        location.href = "/knowledge/delete/" + knlId;
      },
      fnNegativeBtnHandler: function () {
        // var knlId = $(".grid").data("id");
        // location.href = "/knowledge/view/" + knlId;
      },
    });
  });

  var modifyReply = function (event) {
    var target = event.currentTarget;
    var reply = $(target).closest(".reply");
    var rplId = reply.data("reply-id");
    var content = reply.find(".contents").text();
    $("#txt-reply").val(content);
    $("#txt-reply").focus();

    $("#txt-reply").data("mode", "modify");
    $("#txt-reply").data("target", rplId);
  };
  var deleteReply = function (event) {
    var target = event.currentTarget;
    var reply = $(target).closest(".reply");
    var rplId = reply.data("reply-id");

    $("#txt-reply").removeData("mode");
    $("#txt-reply").removeData("target");

    if (deleteReply) {
      loadModal({
        content: "댓글을 삭제하시겠습니까?",
        fnPositiveBtnHandler: function() {
          $.get("/ajax/knowledge/reply/delete/" + rplId, function(response) {
            var result = response.data.result;
            if (result) {
              loadReplies(pPostId);
              $("#txt-reply").val("");
            }
          })
        }
      })
    }
  };
  var reReply = function (event) {
    var target = event.currentTarget;
    var reply = $(target).closest(".reply");
    var rplPid = reply.data("reply-id");

    $("#txt-reply").data("mode", "re-reply");
    $("#txt-reply").data("target", rplPid);
    $("#txt-reply").focus();
  };

  var loadReplies = function (pPostId) {
    $.get("/ajax/knowledge/reply/" + pPostId, function (response) {
      var replies = response.data.knowledgeReplies;

      for (var i in replies) {
        var reply = replies[i];

        /***********************이미 불러온 댓글 수정*************************/
        // 이미 불러온 댓글인지 확인
        var appendedReply = $(".reply[data-reply-id=" + reply.rplId + "]");
        var isAppendedReply = appendedReply.length > 0;
        // 이미 불러온 댓글이며, 삭제가 안된 댓글일 경우
        if (isAppendedReply && reply.delYn === "N") {
          appendedReply.find(".contents").text(reply.rplCntnt);
          var modifyDate = appendedReply.find(".mdfDt");
          if (modifyDate.length !== 0) {
            modifyDate.text("(수정: " + (reply.mdfDt !== null ? reply.mdfDt : "") + ")");
          } else {
            var mdfyDtDom = $("<span></span>");
            mdfyDtDom.addClass("mdfDt");
            mdfyDtDom.text(reply.mdfDt !== null ? "(수정: " + reply.mdfDt + ")" : "");
            appendedReply.find(".datetime").append(mdfyDtDom);
          }
          continue;
        }
        // 이미 불러온 댓글인데, 삭제가 된 댓글일 경우
        else if (isAppendedReply && reply.delYn === "Y") {
          appendedReply.text("삭제된 댓글입니다.");
          appendedReply.css({
            color: "#F33",
          });
          continue;
        }

        var appendedParentReply = $(
          ".reply[data-reply-id=" + reply.rplPid + "]"
        );

        /***********************새로운 댓글 추가*************************/
        // <div class="reply" data-reply-id="댓글번호" style="padding-left: (level - 1) * 40px">
        var replyDom = $("<div></div>");
        replyDom.addClass("reply");

        replyDom.attr("data-reply-id", reply.rplId);
        replyDom.data("reply-id", reply.rplId);
        replyDom.css({
          "padding-left": (reply.level === 1 ? 0 : reply.level - 1) * 40 + "px",
          color: "#333",
        });

        if (reply.delYn === "Y") {
          replyDom.css({
            color: "#F33",
          });
          replyDom.text("삭제된 댓글입니다.");
        } else {
          // <div class="author">사용자명 (사용자이메일)</div>
          var authorDom = $("<div></div>");
          authorDom.addClass("author");
          authorDom.text(reply.employeeVO.empName);
          replyDom.append(authorDom);

          // <div class="datetime">
          var datetimeDom = $("<div></div>");
          datetimeDom.addClass("datetime");

          // <span class="crtdt">등록: 등록날짜</span>
          var crtDtDom = $("<span></span>");
          crtDtDom.addClass("crtdt");
          crtDtDom.text("등록: " + reply.crtDt);
          datetimeDom.append(crtDtDom);

          if (reply.mdfDt !== null) {
            // <span class="mdfydt">(수정: 수정날짜)</span>
            var mdfyDtDom = $("<span></span>");
            mdfyDtDom.addClass("mdfDt");
            mdfyDtDom.text("(수정: " + reply.mdfDt + ")");
            datetimeDom.append(mdfyDtDom);
          }
          replyDom.append(datetimeDom);

          // <pre class="content">댓글 내용</pre>
          var contentDom = $("<div></div>");
          contentDom.addClass("contents");
          contentDom.text(reply.rplCntnt);

          replyDom.append(contentDom);

          var loginEmail = $("#login-email").text();
          var controlDom = $("<div></div>");
          controlDom.addClass("control");

          if (reply.crtrId === loginEmail) {
            // <span class="modify-reply">수정</span>
            var modifyReplyDom = $("<span></span>");
            modifyReplyDom.addClass("modify-reply");
            modifyReplyDom.text("수정");
            modifyReplyDom.attr("data-reply-id", reply.rplId);
            modifyReplyDom.on("click", modifyReply);

            controlDom.append(modifyReplyDom);

            // <span class="delete-reply">삭제</span>
            var deleteReplyDom = $("<span></span>");
            deleteReplyDom.addClass("delete-reply");
            deleteReplyDom.text("삭제");
            deleteReplyDom.on("click", deleteReply);
            controlDom.append(deleteReplyDom);

            // <span class="re-reply">답변하기</span>
            var reReplyDom = $("<span></span>");
            reReplyDom.addClass("re-reply");
            reReplyDom.text("답변하기");
            reReplyDom.on("click", reReply);
            controlDom.append(reReplyDom);
          } else {
            // <span class="re-reply">답변하기</span>
            var reReplyDom = $("<span></span>");
            reReplyDom.addClass("re-reply");
            reReplyDom.text("답변하기");
            reReplyDom.on("click", reReply);
            controlDom.append(reReplyDom);
          }

          replyDom.append(controlDom);
        }

        // 일반 댓글은 reply-items의 자식으로 추가한다.
        if (!appendedParentReply.length > 0) {
          $(".reply-items").append(replyDom);
        }
        // 대댓글은 원 댓글의 자식으로 추가한다.
        else {
          appendedParentReply.after(replyDom);
        }
      }
    });
  };

  var pPostId = $(".grid").data("id");
  loadReplies(pPostId);

  $("#btn-save-reply").on("click", function () {
    var reply = $("#txt-reply").val();
    var mode = $("#txt-reply").data("mode");
    var target = $("#txt-reply").data("target");

    if (reply.trim() !== "") {
      var body = { rplCntnt: reply.trim() };
      var url = "/ajax/knowledge/reply/" + pPostId;

      if (mode === "re-reply") {
        body.rplPid = target;
      }

      if (mode === "modify") {
        url = "/ajax/knowledge/reply/modify/" + target;
      }
    }

    $("#txt-reply").removeData("mode");
    $("#txt-reply").removeData("target");

    $.post(url, body, function (response) {
      var result = response.data.result;
      // var pPostId = response.data.pPostId;
      if (result) {
        loadReplies(pPostId);
        $("#txt-reply").val("");
      } else {
        alert("댓글을 등록할 수 없습니다. 잠시 후 시도해주세요.");
      }
    });
  });
  $("#btn-cancel-reply").on("click", function () {
    $("#txt-reply").val("");
    $("#txt-reply").removeData("mode");
    $("#txt-reply").removeData("target");
  });

  /*********************************추천*************************************/
  $(".recommend-knowledge").on("click", function () {
    var knlId = $(".recommend-knowledge").val();

    $.post("/knowledge/recommend/" + knlId, function (response) {
      loadModal({
        content: response.data.result,
        fnPositiveBtnHandler: function () {
          location.reload();
        },
        showNegativeBtn: false,
      });

      // var alertModal = $(".modal-confirm-window");
      // var modalButton = $(".confirm-confirm-button");

      // var modalButton1 = $(".cancel-confirm-button");
      // var modalText = $(".modal-confirm-text");
      // modalText.text(response.data.result);
      // modalButton.text("확인");
      // modalButton..on("click", function () {
      //   location.reload();
      // });

      // modalButton1.css("display", "none");
      // alertModal[0].showModal();
      // $("#knlRecCnt").html(response.data.count);
    });
  });
});
