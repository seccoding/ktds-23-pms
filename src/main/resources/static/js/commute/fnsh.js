$().ready(function () {
  $("#fnsh-btn").on("click", function () {
    $.get(
        "/commute/fnsh",
        
    )
  });
});
