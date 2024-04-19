$().ready(function () {
  $("#list-size").on("change", function () {
    search(0);
  });
  
  
  
  $("#checked-all").on("change", function () {
    var targetClass = $(this).data("target-class");

    // checked-all 의 체크 상태를 가져온다.
    // 체크가 되어있다면 true 아니라면 false
    var isChecked = $(this).prop("checked");

    $("." + targetClass).prop("checked", isChecked);
  });
});
