$().ready(function () {
  $(".plus-btn").on("click", function () {
    /* 새로운 form 추가 */
    /* <form action="/product/manage/add" method="post" enctype="multipart/form-data"></form> */
    var formDom = $("<form></form>");
    formDom.attr("action", "/product/manage/add");
    formDom.attr("method", "post");
    formDom.attr("enctype", "multipart/form-data");

    var formData = $("form").html();
    formDom.append(formData);

    $(".form-group").append(formDom);

    // 오늘 날짜로 기본설정
    var today = new Date().toISOString().substring(0, 10);
    formDom.find("#buyDt").val(today);
  });

  $(".minus-btn").on("click", function () {
    var formCount = $("form").length;
    if (formCount > 1) {
      $("form:last").remove();
    } else {
      loadModal({
        content: "비품 추가를 취소하시겠습니까?",
        fnPositiveBtnHandler: function () {
          location.href = "/product/manage/list";
        },
      });

      // var deleteConfirm = confirm("비품 추가를 취소하시겠습니까?");
      // if(deleteConfirm){
      //     location.href = "/product/manage/list";
      // }
    }
  });


  // 오늘 날짜로 기본설정
  var today = new Date().toISOString().substring(0, 10);
  $("#buyDt").val(today);


  $("#buyDt").on("change", function () {
    if ($(this).val() > today) {
        loadModal({
          content: "구매일은 오늘 날짜 이후로 설정할 수 없습니다.",
          fnPositiveBtnHandler: function () {
            $(".lost-day").val("");
            alertModal[0].close();
          },
          showNegativeBtn: false,
        });
    }
  });


  $(".product-add").on("click", function () {
    var url = "/ajax/product/manage/add";

    var formData = {};

    


    if( $("#prdtName").val() == "" || $("#prdtCtgr").val() == "" || $("#curStr").val() == "" ||
        $("#buyDt").val() == "" || $("#prdtPrice").val() == "" ) {

        loadModal({
            content: "빈칸에 값을 입력해주세요.",
            fnPositiveBtnHandler: function () {
                alertModal[0].close();
            },
            showNegativeBtn: false,
        });
    }
    else if ($("#prdtName").val() != "" && $("#prdtCtgr").val() != "" && $("#curStr").val() > 50 &&
             $("#buyDt").val() != "" && $("#prdtPrice").val() != ""){

        loadModal({
            content: "최대 추가 재고수는 50개입니다.",
            fnPositiveBtnHandler: function () {
                alertModal[0].close();
            },
            showNegativeBtn: false,
        });
    }
    else {

        var inputname = $("#prdtName").val();

        var getUrl = "/ajax/product/manage/add/" + inputname;

        var existResult;

        $.get(getUrl, { inputName: inputname }, function (response) {
            existResult = response.data.result;
            if(existResult) {
                loadModal({
                    content: "비품이 이미 존재합니다.",
                    fnPositiveBtnHandler: function () {
                        alertModal[0].close();
                    },
                    showNegativeBtn: false,
                });
            }
            else {
                $("form").each(function (index, form) {
                    formData["productList[" + index + "].prdtName"] = $(form)
                    .find("#prdtName")
                    .val();
                    formData["productList[" + index + "].prdtCtgr"] = $(form)
                    .find("#prdtCtgr")
                    .val();
                    formData["productList[" + index + "].onceYn"] =
                    $(form).find("#onceYn").val() === "소모품" ? "Y" : "N";
                    formData["productList[" + index + "].curStr"] = $(form)
                    .find("#curStr")
                    .val();
                    formData["productList[" + index + "].productManagementVO.buyDt"] = $(form)
                    .find("#buyDt")
                    .val();
                    formData["productList[" + index + "].productManagementVO.prdtPrice"] = $(
                    form
                    )
                    .find("#prdtPrice")
                    .val();
                });
            
                loadModal({
                    content: "추가하시겠습니까?",
                    fnPositiveBtnHandler: function () {
                    $.post(url, formData, function (response) {
                        location.href = response.data.next;
                    });
                    },
                    fnNegativeBtnHandler: function () {
                    alertModal[0].close();
                    },
                });
            }
        });
        
    }



    // var addConfirm = confirm("추가하시겠습니까?");
    // if (addConfirm) {
    //   $.post(url, formData, function (response) {
    //     location.href = response.data.next;
    //   });
    // } else {
    //   location.reload();
    // }
  });

  $(".product-cancel").on("click", function () {
    loadModal({
      content: "취소하시겠습니까?",
      fnPositiveBtnHandler: function () {
        location.href = "/product/manage/list";
      },
      showNegativeBtn: false,
    });

    // var reConfirm = confirm("취소하시겠습니까?");
    // if (reConfirm) {
    //   location.href = "/product/manage/list";
    // }
  });
});
