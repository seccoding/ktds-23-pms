$().ready(function () {
  $(".delete-knowledge").on("click", function () {
    var chooseValue = confirm(
      "이 게시글을 정말 삭제하시겠습니까?\n삭제작업은 복구할 수 없습니다."
    );

    var knlId = $(this).closest(".grid").data("knlId");

    if (chooseValue) {
      location.href = "/knowledge/delete/" + knlId;
    }

    // $(".recommend-knowledge").on("click", function () {
    //   var
    // })
  });

  // $(".recommend-knowledge").on("click", function () {
  //   alert("체크");

  //   try {
  //     const response = $.ajax({
  //       method: "get",
  //       url: "/ajax/Knowledge/recommend/{knlId}",
  //     });
  //     if (response.status !== 200) throw res;
  //   } catch (error) {
  //     console.error(error);
  //   }
  // });

  // var recommendKnowledge = function (event) {
  //   $.get("/ajax/Knowledge/recommend/" + knlId, function (response) {
  //     var result = response.data.result;
  //     console.log(result);
  //   });
  // };
});
