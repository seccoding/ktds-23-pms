$().ready(function () {
  $("#deleteMassiveMemo").on("click", function () {
    // 선택된 체크박스만 가져온다.
    var checkedItems = $(".target-memo-id:checked");
    // 선택된 체크박스만 반복하며 서버로 보낼 파라미터를 생성한다.
    var itemsArray = [];
    checkedItems.each(function (index, data) {
      itemsArray.push($(data).val());
    });
    // 서버로 전송한다(ajax)
    $.post(
      "/ajax/memo/delete/massive",
      { memoIds: itemsArray },
      function (response) {
        var result = response.data.result;
        if (result) {
          // 삭제가 완료되면 현재페이지를 새로고침한다.
          location.reload();
        }
      }
    );
  });

  $("#checked-all").on("change", function () {
    var targetClass = $(this).data("target-class");

    // checked-all 의 체크 상태를 가져온다.
    // 체크가 되어있다면 true 아니라면 false
    var isChecked = $(this).prop("checked");

    $("." + targetClass).prop("checked", isChecked);
  });

  $("#status").on("change", function () {
    search(0);
  });

  $("#list-size").on("change", function () {
    search(0);
  });

  $("#search-btn").on("click", function () {
    search(0);
  });
});

function search(pageNo) {
  var searchForm = $("#search-form");
  // var listSize = $("#list-size");
  $("#page-no").val(pageNo);

  searchForm.attr("method", "get").submit();
}
