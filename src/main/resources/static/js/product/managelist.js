$().ready(function () {

  $("#product-exist-search").data("checkstatus") ? $("#product-exist-search").prop("checked", true) : $("#product-exist-search").prop("checked", false);

  function search(pageNo) {
    var searchForm = $("#search-form");
    $("#page-no").val(pageNo);
    $("#is-check").val($("#product-exist-search").is(":checked"));

    searchForm.attr("method", "get").attr("action", "/product/manage/list").submit();
  }


  $("#search-btn").on("click", function () {
    search(0);
  });

  $("#list-size").on("change", function () {
    search(0);
  });

  /* 
    엔터키 눌렀을 때 form 전송이 안되도록 함

    var alertModal = $(".modal-window");
        var modalButton = $(".confirm-button");
        var modalText = $(".modal-text");
        modalText.text("선택한 항목의 반납처리 중 오류가 발생 되었습니다.");
        modalButton.text("확인");

        alertModal[0].showModal();
        $(".confirm-button").on("click", function () {
          alertModal[0].close();
        });
    
     */


  $("#product-exist-search").on("click", function(){
    // var checked = $(this).is(":checked");
    // if (checked) {
    //     $("table.table tbody tr").each(function(){
    //         var stockQuantity = parseInt($(this).find("td:eq(4)").text());
    //         if (stockQuantity < 1) {
    //             $(this).hide();
    //         } else {
    //             $(this).show();
    //         }
    //     });
    // } else {
    //     $("table.table tbody tr").show();
    // }


    search($(".active").find("a").text()-1);

  });


  $("form")
    .find("input")
    .on("keydown", function (event) {
      if (event.keyCode === 13) {
        var noSubmit = $(this).data("no-submit");
        if (noSubmit !== undefined) {
          event.preventDefault();
        }
      }
    });

  $(".product-item").on("click", function () {
    var id = $(this).data("product");
    console.log(id);
    location.href = "/product/manage/view?prdtId=" + id;
  });

  $(".create-product").on("click", function () {
    location.href = "/product/manage/add";
  });

  $(".add-product-count").on("click", function () {
    $("#add-modal")[0].showModal();
    var productName = $(this).data("productname");
    var productId = $(this).data("productid");
    $(".add-product").text(productId + " (" + productName + ")");

    $(".add-count").val(1);

    console.log($(".add-product").text().split(" ")[0]);
  });

  $("#cancel-btn").on("click", function () {
    location.reload();
  });

  $("#add-count-btn").on("click", function () {
    if ($(".buy-day").val() == "" || $(".buy-price").val() == "") {
      loadModal({
        content: "빈칸에 값을 입력해주세요.",
        fnPositiveBtnHandler: function () {
          alertModal[0].close();
          location.href = res.data.next;
        },
        showNegativeBtn: false,
      });

      // var alertModal = $(".modal-window");
      // var modalButton = $(".confirm-button");
      // var modalText = $(".modal-text");
      // modalText.text("빈칸에 값을 입력해주세요");
      // modalButton.text("확인");

      // alertModal[0].showModal();
      // $(".confirm-button").on("click", function () {
      //   alertModal[0].close();
      //   location.href = res.data.next;
      // });
    } else {
      $.post(
        "/ajax/product/manage/list/add",
        {
          prdtId: $(".add-product").text().split(" ")[0],
          prdtPrice: $(".buy-price").val(),
          buyDt: $(".buy-day").val(),
          "productVO.prdtId": $(".add-product").text().split(" ")[0],
          "productVO.curStr": $(".add-count").val(),
        },
        function (res) {
          console.log(res);
          if (res.data.result) {
            loadModal({
              content: "정상적으로 추가되었습니다.",
              fnPositiveBtnHandler: function () {
                location.href = res.data.next;
                alertModal[0].close();
              },
              showNegativeBtn: false,
            });

            // var alertModal = $(".modal-window");
            // var modalButton = $(".confirm-button");
            // var modalText = $(".modal-text");
            // modalText.text("정상적으로 추가되었습니다.");
            // modalButton.text("확인");

            // alertModal[0].showModal();
            // $(".confirm-button").on("click", function () {
            //   location.href = res.data.next;
            // });
          } else {
            loadModal({
              content: "오류가 발생되었습니다.",
              fnPositiveBtnHandler: function () {
                alertModal[0].close();
                location.href = res.data.next;
              },
              showNegativeBtn: false,
            });

            // var alertModal = $(".modal-window");
            // var modalButton = $(".confirm-button");
            // var modalText = $(".modal-text");
            // modalText.text("오류가 발생되었습니다.");
            // modalButton.text("확인");

            // alertModal[0].showModal();
            // $(".confirm-button").on("click", function () {
            //   alertModal[0].close();
            //   location.href = res.data.next;
            // });
          }
        }
      );
    }
  });

  $(".remove-product").on("click", function () {
    var id = $(this).data("product");
    $.get("/product/manage/list/iscandel?prdtId=" + id, function (res) {
      if (res.data.canDel) {
        var confirm = false;
        loadModal({
          content: "정말 삭제하시겠습니까?",
          fnPositiveBtnHandler: function () {
            confirm = true;
            if (confirm == true) {
              $.get("/product/manage/list/del?prdtId=" + id, function (res) {
                if (res.data.result) {
                  loadModal({
                    content: "삭제가 완료되었습니다.",
                    fnPositiveBtnHandler: function () {
                      location.href = res.data.next;
                      alertModal[0].close();
                    },
                    showNegativeBtn: false,
                  });

                  // var alertModal = $(".modal-window");
                  // var modalButton = $(".confirm-button");
                  // var modalText = $(".modal-text");
                  // modalText.text("삭제가 완료되었습니다.");
                  // modalButton.text("확인");

                  // alertModal[0].showModal();
                  // $(".confirm-button").on("click", function () {
                  //   alertModal[0].close();
                  //   location.href = res.data.next;
                  // });
                } else {
                  loadModal({
                    content: "삭제 중 오류가 발생했습니다.",
                    fnPositiveBtnHandler: function () {
                      alertModal[0].close();
                      location.href = res.data.next;
                    },
                    showNegativeBtn: false,
                  });

                  // var alertModal = $(".modal-window");
                  // var modalButton = $(".confirm-button");
                  // var modalText = $(".modal-text");
                  // modalText.text("삭제 중 오류가 발생했습니다.");
                  // modalButton.text("확인");

                  // alertModal[0].showModal();
                  // $(".confirm-button").on("click", function () {
                  //   alertModal[0].close();
                  //   location.href = res.data.next;
                  // });
                }
              });
            }
          },
          fnNegativeBtnHandler: function () {
            alertModal[0].close();
            confirm = false;
          },
        });

        // var alertModal = $(".modal-confirm-window");
        // var modalButton = $(".confirm-confirm-button");
        // var modalButton1 = $(".cancel-confirm-button");
        // var modalText = $(".modal-confirm-text");
        // modalText.text("정말 삭제하시겠습니까?");
        // modalButton.text("확인");
        // modalButton1.text("취소");
        // alertModal[0].showModal();
        // var confirm = false;

        // $(".confirm-confirm-button").on("click", function () {
        //   confirm = true;

        //   // if (confirm == true) {
        //   //   $.get("/product/manage/list/del?prdtId=" + id, function (res) {
        //   //     if (res.data.result) {
        //   //       var alertModal = $(".modal-window");
        //   //       var modalButton = $(".confirm-button");
        //   //       var modalText = $(".modal-text");
        //   //       modalText.text("삭제가 완료되었습니다.");
        //   //       modalButton.text("확인");

        //   //       alertModal[0].showModal();
        //   //       $(".confirm-button").on("click", function () {
        //   //         alertModal[0].close();
        //   //         location.href = res.data.next;
        //   //       });
        //   //     } else {
        //   //       var alertModal = $(".modal-window");
        //   //       var modalButton = $(".confirm-button");
        //   //       var modalText = $(".modal-text");
        //   //       modalText.text("삭제 중 오류가 발생했습니다.");
        //   //       modalButton.text("확인");

        //   //       alertModal[0].showModal();
        //   //       $(".confirm-button").on("click", function () {
        //   //         alertModal[0].close();
        //   //         location.href = res.data.next;
        //   //       });
        //   //     }
        //   //   });
        //   // }
        // });
        // $(".cancel-confirm-button").on("click", function () {
        //   alertModal[0].close();
        //   confirm = false;
        // });
      } else {
        loadModal({
          content: "해당 비품은 상세 품목이 존재하여 삭제할 수 없습니다.",
          fnPositiveBtnHandler: function () {
            alertModal[0].close();
          },
          showNegativeBtn: false,
        });

        // var alertModal = $(".modal-window");
        // var modalButton = $(".confirm-button");
        // var modalText = $(".modal-text");
        // modalText.text("해당 비품은 상세 품목이 존재하여 삭제할 수 없습니다.");
        // modalButton.text("확인");

        // alertModal[0].showModal();
        // $(".confirm-button").on("click", function () {
        //   alertModal[0].close();
        // });
      }
    });
  });
});
