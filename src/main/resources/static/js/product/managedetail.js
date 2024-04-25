$().ready(function () {
  function confirmModal(a) {
    var alertModal = $(".modal-confirm-window");
    var modalButton = $(".confirm-confirm-button");
    var modalButton1 = $(".cancel-confirm-button");
    var modalText = $(".modal-confirm-text");
    modalText.text(a);
    modalButton.text("확인");
    modalButton1.text("취소");
    alertModal[0].showModal();
    var confirm = false;

    $(".confirm-confirm-button").on("click", function () {
      alertModal[0].close();
      confirm = true;
    });
    $(".cancel-confirm-button").on("click", function () {
      alertModal[0].close();
      confirm = false;
    });
    return confirm;
  }
  $("#search-btn").on("click", function () {
    search(0);
  });

  $("#list-size").on("change", function () {
    search(0);
  });

  /* 
    엔터키 눌렀을 때 form 전송이 안되도록 함

    $(".test-button").on("click", function () {
        var alertModal = $(".modal-window");
        var modalButton = $(".confirm-button");
        var modalText = $(".modal-text");
        modalText.text("프로젝트를 삭제하시겠습니까?");
        modalButton.text("확인");
        alertModal[0].showModal();
    
     */
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

  var paramId = $(".body").data("paramid");
  $(".remove").on("click", function () {
    var productId = $(this).data("product");

    // var prdtId = $(this).closest("detail-table").data("prdt")
    var url = "/ajax/product/manage/view/delete/" + productId;

    var alertModal = $(".modal-confirm-window");
    var modalButton = $(".confirm-confirm-button");
    var modalButton1 = $(".cancel-confirm-button");
    var modalText = $(".modal-confirm-text");
    modalText.text("정말로 삭제하시겠습니까?");
    modalButton.text("확인");
    modalButton1.text("취소");
    alertModal[0].showModal();
    var confirm = false;

    $(".confirm-confirm-button").on("click", function () {
      confirm = true;
      if (confirm == true) {
        $.get(url, function (res) {
          if (res.data.result) {
            var alertModal = $(".modal-window");
            var modalButton = $(".confirm-button");
            var modalText = $(".modal-text");
            modalText.text("정상적으로 삭제되었습니다.");
            modalButton.text("확인");
  
            alertModal[0].showModal();
            $(".confirm-button").on("click", function () {
              alertModal[0].close();
              location.href = "/product/manage/detail";
            });
          } else {
            var alertModal = $(".modal-window");
            var modalButton = $(".confirm-button");
            var modalText = $(".modal-text");
            modalText.text("삭제 중 오류가 발생되었습니다.");
            modalButton.text("확인");
  
            alertModal[0].showModal();
            $(".confirm-button").on("click", function () {
              alertModal[0].close();
            });
          }
          
        });
      }
    });
    $(".cancel-confirm-button").on("click", function () {
      confirm = false;
      alertModal[0].close();
    });

    
  });

  $(".modify").on("click", function () {
    var modifyModal = $(".modify-modal");
    var id = $(this).data("product");
    var name = $(this).data("name");
    var url = "/ajax/product/manage/view/modify/" + id;

    $.get(url, function (res) {
      var product = res.data.product;

      $(".manage-id").text(product.prdtMngId);
      $(".product-name").text(name);
      $(".price").val(product.prdtPrice);
      $(".buy-day").val(product.buyDt.split(" ")[0]);
      $(".lost-day").val(product.lostDt ? product.lostDt.split(" ")[0] : "");
      console.log(product.lostYn);
      if (product.lostYn === "Y") {
        $(".select").val("O");
      } else {
        $(".select").val("X");
      }
    });

    modifyModal[0].showModal();
  });

  $("#cancel-btn").on("click", function () {
    location.reload();
  });

  $("#modify-btn").on("click", function () {
    console.log($(".buy-day").val());
    $.post(
      "/ajax/product/manage/view/modify",
      {
        prdtMngId: $(".manage-id").text(),
        prdtPrice: $(".price").val(),
        buyDt: $(".buy-day").val(),
        lostYn: $(".select").val() === "O" ? "Y" : "N",
        lostDt: $(".lost-day").val(),
        prdtId: paramId,
      },
      function (res) {
        if (res.data.result) {
          var alertModal = $(".modal-window");
          var modalButton = $(".confirm-button");
          var modalText = $(".modal-text");
          modalText.text("정상적으로 수정되었습니다.");
          modalButton.text("확인");

          alertModal[0].showModal();
          $(".confirm-button").on("click", function () {
            alertModal[0].close();
            location.href = res.data.detailUrl;
          });
        } else {
          var alertModal = $(".modal-window");
          var modalButton = $(".confirm-button");
          var modalText = $(".modal-text");
          modalText.text("수정 중 오류가 발생했습니다.");
          modalButton.text("확인");

          alertModal[0].showModal();
          $(".confirm-button").on("click", function () {
            alertModal[0].close();
            location.href = res.data.detailUrl;
          });
        }
        
      }
    );
  });
});
