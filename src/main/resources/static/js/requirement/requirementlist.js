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
    var prjId = "ALL";
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
