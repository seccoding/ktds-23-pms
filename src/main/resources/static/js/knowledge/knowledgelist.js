$().ready(function () {
  $("form").on("keypress", function (e) {
    if (e.which == 13) {
      e.preventDefault();
      return false;
    }
  });

  $("#deleteMassiveKnowledge").on("click", function () {
    var checkedItems = $(".target-knl-id:checked");
    var itemsArray = [];
    checkedItems.each(function (index, data) {
      itemsArray.push($(data).val());
    });

    if (itemsArray.length === 0) {
      loadModal({
        content: "삭제할 게시글을 선택하세요.",
        showNegativeBtn: false,
      });
    } else {
      loadModal({
        content: "게시글을 일괄 삭제 하시겠습니까?",
        fnPositiveBtnHandler: function () {
          $.post(
            "/ajax/knowledge/delete/massive",
            { deleteItems: itemsArray },
            function (response) {
              var result = response.data.result;
              if (result) {
                location.reload();
              }
            }
          );
        },
      });
    }
  });

  // checked-all
  $("#checked-all").on("change", function () {
    // 영향을 받을 다른 체크박스를 조회
    var targetClass = $(this).data("target-class");

    // checked-all의 체크 상태를 가져온다.
    // 체크가 되어있다면 true, 아니라면 false
    var ischecked = $(this).prop("checked");

    $("." + targetClass).prop("checked", ischecked);
  });

  $("#list-size").on("change", function () {
    search(0);
  });

  $("#search-btn").on("click", function () {
    search(0);
  });

  $("#cancel-search-btn").on("click", function () {
    location.href = "/knowledge";
  });
});
