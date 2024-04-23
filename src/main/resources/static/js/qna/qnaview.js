$().ready(function () {
  $(".answer-btn").on("click", function () {
    // 답변 입력란을 보여준다.
    $(".answer-form").show();
  });

  $(".delete-qna").on("click", function () {
    var chooseValue = confirm(
      "이 게시글을 정말 삭제하시겠습니까?\n삭제작업은 복구할 수 없습니다."
    );

    var qaId = $(this).closest(".grid").data("id");
    console.log("qaId  " + qaId);

    if (chooseValue) {
      location.href = "/qna/delete/" + qaId;
    }
  });
});
