$().ready(function () {
  $("#search").on("click", {}, function () {
    var prjId = $("#prj-id option:selected").val();
    var scdSts = $("#scd-sts option:selected").val();
    var rqmSts = $("#rqm-sts option:selected").val();

    $.get(
      "/requirement/search?prjId=" + prjId,
      {
        prjId: prjId,
        scdSts: scdSts,
        rqmSts: rqmSts,
      },
      (location.href = "/requirement/search?prjId=" + prjId)
    );

    location.href =
      "/requirement/search?prjId=" +
      prjId +
      "&scdSts=" +
      scdSts +
      "&rqmSts=" +
      rqmSts;
  });

  $("#reset").on("click", function () {
    var prjId = "";
    var scdSts = "";
    var rqmSts = "";
    $.get(
      "/requirement/search?prjId=" + prjId,
      {
        prjId: prjId,
        scdSts: scdSts,
        rqmSts: rqmSts,
      },
      (location.href = "/requirement/search?prjId=" + prjId)
    );
    location.href =
      "/requirement/search?prjId=" +
      prjId +
      "&scdSts=" +
      scdSts +
      "&rqmSts=" +
      rqmSts;
  });
  //전체 체크박스로 하위체크박스 체크
  $("#checked-all").on("change", function () {
    var targetClass = $(this).data("target-class");
    var isChecked = $(this).prop("checked");

    $("." + targetClass).prop("checked", isChecked);
  });
  //하위 체크박스로 전체 체크박스 체크
  $(".target-rqm-id").on("change", function () {
    var length = $("input[class=target-rqm-id").length;
    console.log("length :" + length);
    var allChecked = true;

    for (i = 0; i < length; i++) {
      var isChecked = $("#checked-requirement-" + i).is(":checked");
      if (!isChecked) {
        allChecked = false;
      }
    }
    $("#checked-all").prop("checked", allChecked);
  });
});

function search(pageNo) {
  var prjId = $("#prj-id option:selected").val();
  var scdSts = $("#scd-sts option:selected").val();
  var rqmSts = $("#rqm-sts option:selected").val();
  var pageNo = pageNo;

  $.get(
    "/requirement/search?prjId=" + prjId,
    {
      prjId: prjId,
      scdSts: scdSts,
      rqmSts: rqmSts,
      pageNo: pageNo,
    },
    (location.href = "/requirement/search?prjId=" + prjId)
  );

  location.href =
    "/requirement/search?prjId=" +
    prjId +
    "&scdSts=" +
    scdSts +
    "&rqmSts=" +
    rqmSts +
    "&pageNo=" +
    pageNo;
}
