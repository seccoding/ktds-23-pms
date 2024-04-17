$().ready(function () {
  $(document).keyup(function () {
    var outTtl = $("#out-ttl").val();

    if (outTtl.length === 0) {
      var ttlDom = $("<div>제목을 입력해주세요</div>");
      ttlDom.addClass("ttlAlert");
      $(".ttlAlert").remove();
      $(".ttlInput").append(ttlDom);
    } else {
      $(".ttlAlert").remove();
    }
  });
});
