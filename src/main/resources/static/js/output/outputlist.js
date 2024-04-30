$().ready(function () {
  $("#search-output").on("click", function () {
    var prjId = $("#prj-id option:selected").val();
    var outType = $("#out-type option:selected").val();
    var outVer = $("#out-ver option:selected").val();
    console.log("outVer :" + outVer);
    console.log("outType :" + outType);

    $.get(
      "/output/search?prjId=" + prjId,
      {
        outType: outType,
        outVer: outVer,
      },
      (location.href =
        "/output/search?prjId=" +
        prjId +
        "&outType=" +
        outType +
        "&outVer=" +
        outVer)
    );
  });

  //전체 체크박스로 하위체크박스 체크
  $("#checked-all").on("change", function () {
    var targetClass = $(this).data("target-class");
    var isChecked = $(this).prop("checked");

    $("." + targetClass).prop("checked", isChecked);
  });

  //하위 체크박스로 전체 체크박스 체크
  $(".target-out-id").on("change", function () {
    var length = $("input[class=target-out-id").length;
    var allChecked = true;

    for (i = 0; i < length; i++) {
      var isChecked = $("#checked-out-" + i).is(":checked");
      if (!isChecked) {
        allChecked = false;
      }
    }
    $("#checked-all").prop("checked", allChecked);
  });

  $(".delete").on("click", function () {
    var outId = $(this).data("out-id");
    loadModal({
      content: "정말로 삭제하겠습니까",
      fnPositiveBtnHandler: function () {
        $.post("/output/delete/" + outId, function (response) {
          var error = response.data.error;
          var result = response.data.result;
          var url = response.data.url;
          if (error) {
            loadModal({
              content: error,
              showNegativeBtn: false,
            });
          } else {
            if (result) {
              location.href = url;
            } else {
              loadModal({
                content: "오류가 발생했습니다, 관리자에게 문의바랍니다",
                showNegativeBtn: false,
              });
            }
          }
        });
      },
    });
    // console.log("outId :" + outId);
    // if (confirm("정말로 삭제하겠습니까")) {
    //   location.href = "/output/delete/" + outId;
    // } else {
    // }

    // /output/delete/${output.outId}?prjId=${prjId}
  });
});

function search(pageNo) {
  var prjId = $("#prj-id option:selected").val();
  var outType = $("#out-type option:selected").val();
  var outVer = $("#out-ver option:selected").val();
  var pageNo = pageNo;

  $.get(
    "/output/search?prjId=" + prjId,
    {
      prjId: prjId,
      outType: outType,
      pageNo: pageNo,
      outVer: outVer,
    },
    (location.href = "/output/search?prjId=" + prjId)
  );

  location.href =
    "/output/search?prjId=" +
    prjId +
    "&outType=" +
    outType +
    "&pageNo=" +
    pageNo +
    "&outVer=" +
    outVer;
}
