$().ready(function () {
    $(".delete-issue").on("click", function () {
      var chooseValue = confirm(
        "이 게시글을 정말 삭제하시겠습니까?\n삭제작업은 복구할 수 없습니다."
      );
  
      var isId = $(this).closest(".grid").data("isId");
  
      if (chooseValue) {
        location.href = "/issue/delete/" + isId;
      }
    });
  });
  