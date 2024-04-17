$().ready(function () {
  $(".delete-knowledge").on("click", function () {
    var chooseValue = confirm(
      "이 게시글을 정말 삭제하시겠습니까?\n삭제작업은 복구할 수 없습니다."
    );

    if (chooseValue) {
      location.href = "/knowledge/delete/" + knlId;
    }
  });
});
