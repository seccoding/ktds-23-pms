$().ready(function () {
  // 신청 form 추가
  $(".plus-btn").on("click", function () {
    var formDom = $("<form></form>");
    formDom.attr("action", "/product/apply");
    formDom.attr("method", "post");
    formDom.attr("enctype", "multipart/form-data");


    var formData = $("form").html();
    formDom.append(formData);

    $(".form-group").append(formDom);

    // 새로운 form에 비품명 선택 이벤트 바인딩
    formDom.find("#select-prdtName").on("change", autoPrdtStockCategorySelect);

    var today = new Date().toISOString().substring(0, 10);

    // 오늘 날짜로 기본설정
    formDom.find("#apply-date").val(today);
  });

  // 신청 form 삭제
  $(".minus-btn").on("click", function () {
    var formCount = $("form").length;
    if (formCount > 1) {
      $("form:last").remove();
    } else {
      loadModal({
        content: "비품 신청을 취소하시겠습니까?",
        fnPositiveBtnHandler: function () {
          location.href = "/product/list";
        },
        showNegativeBtn: true,
        fnNegativeBtnHandler: function () {
        alertModal[0].close();
        },
      });
    }
  });

  
  // 이미 선택된 비품명을 저장할 배열
  var selectedProductNames = [];


  var curstr; // 비품의 재고 수
  var category // 비품의 카테고리

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  // 첫번째 form에 함수 적용해야됨
  function autoPrdtStockCategorySelect() {
    // 선택한 비품명
    var namevalue = $(this).val();

    console.log(selectedProductNames);


    // 선택된 비품명에 속한 form을 가져옴
    var parentForm = $(this).closest("form");

    var url = "/ajax/product/apply/" + namevalue;

    $.get(url, { productName: namevalue }, function (response) {
      curstr = response.data.stockAndCategory.curStr;
      category = response.data.stockAndCategory.prdtCtgr;
      console.log("재고: " + curstr + "개");
      console.log("카테고리: " + category);

      parentForm.find("#apply-quantity").attr("max", curstr);
      parentForm.find("#select-prdtCtgr").val(category);
    });
  }
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


  // 비품명이 바뀔때마다 함수 호출
  $(".form-group").on("change", "#select-prdtName", function () {
    // 선택한 비품명
    var namevalue = $(this).val();

    console.log(selectedProductNames);

    // 이미 선택된 비품명인지 확인
    if(selectedProductNames.includes(namevalue)){
        console.log(selectedProductNames);
        loadModal({
            content: "이미 선택된 비품입니다. 다른 비품을 선택해주세요.",
            fnPositiveBtnHandler: function () {
                // 선택을 취소하고 비품명을 초기화
                // ~~~~~~~~~~~~~~~~~~~~~~~~~~
                // 중복되는 비품명일 시에 값 초기화가 안됨
                $("#select-prdtName").val("");
                // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            },
            showNegativeBtn: false,
        });
    }
    else {
        // 새로운 비품명을 선택한 경우, 선택된 비품명 배열에 추가
        selectedProductNames.push(namevalue);
    }

  });


  var today = new Date().toISOString().substring(0, 10);
  console.log(today);

  // 오늘 날짜로 기본설정
  $("#apply-date").val(today);


  // 신청 버튼
  $(".add-button").on("click", function () {
    var url = "/ajax/product/apply";

    var formData = {};

    var errorMessage = [];

    $("form").each(function (index, form) {
        formData["borrowList[" + index + "].productVO.prdtName"] = $(form)
            .find("#select-prdtName")
            .val();
        formData["borrowList[" + index + "].productVO.prdtCtgr"] = $(form)
            .find("#select-prdtCtgr")
            .val();
        formData["borrowList[" + index + "].productVO.curStr"] = $(form)
            .find("#apply-quantity")
            .val();
        formData["borrowList[" + index + "].brrwDt"] = $(form)
            .find("#apply-date")
            .val();


        var quantityVal = $(form).find("#apply-quantity").val();

        // 신청수량이 재고수보다 많으면 배열에 에러메시지 push
        if(quantityVal > curstr){
            var message = (index+1);
            errorMessage.push(message);
        }

    });

    loadModal({
        content: "신청하시겠습니까?",
        fnPositiveBtnHandler: function () {
            // 에러메시지가 담겨있으면 modal 보여줌
            if(errorMessage.length > 0){
                loadModal({
                    content: errorMessage + " 번 폼의 신청수량이 재고수보다 많습니다.",
                    fnPositiveBtnHandler: function () {
                    // location.reload();
                    },
                    showNegativeBtn: false,
                });
            }
            // 에러메시지가 없다면 비품 신청
            else {
                $.post(url, formData, function (response) {
                    location.href = response.data.next;
                });
            }


        },
        fnNegativeBtnHandler: function () {
        alertModal[0].close();
        },
    });
  });

  $(".cancel-button").on("click", function () {
        loadModal({
            content: "취소하시겠습니까",
            fnPositiveBtnHandler: function () {
            location.href = "/product/list";
            },
            showNegativeBtn: true,
            fnNegativeBtnHandler: function () {
            alertModal[0].close();
            },
        });
  });
});
