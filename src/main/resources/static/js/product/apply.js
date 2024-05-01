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

    // 오늘 날짜로 기본설정
    var today = new Date().toISOString().substring(0, 10);
    formDom.find("#apply-date").val(today);

  });

  // 신청 form 삭제
  $(".minus-btn").on("click", function () {
    var formCount = $("form").length;

    // form의 index번호를 통해 배열의 index 값 변경
    var formIndex = $("form:last").index();

    console.log(selectedProductNames);
    

    if (formCount > 1) {
        selectedProductNames[formIndex] = "";
        $("form:last").remove();
        console.log(selectedProductNames);

      
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

  // "이미 선택된 비품입니다. 다른 비품을 선택해주세요." 메시지 출력을 담당하는 배열
  var existMessage = "";

  var curstr; // 비품의 재고 수
  var category // 비품의 카테고리

  
  // 카테고리와 최대 수량을 설정해주는 함수
  function autoPrdtStockCategorySelect() {
    // 선택한 비품명
    var namevalue = $(this).val();


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


  // 비품명이 바뀔때마다 함수 호출
  $(".form-group").on("change", "#select-prdtName", autoPrdtStockCategorySelect);
  $(".form-group").on("change", "#select-prdtName", function () {

    // 선택한 비품명
    var namevalue = $(this).val();

    // form의 index번호를 통해 배열의 index 값 변경
    var formIndex = $(this).closest("form").index();

    console.log("formIndex: " + formIndex);

    


    /**
     * index번째 selectedProductNames 배열에 값이 비어있고 
     * selectedProductNames 배열에 namevalue가 포함되어 있지 않다면 
     * selectedProductNames 배열에 namevalue 값을 추가하고
     * 
     * index번째 selectedProductNames 배열에 값이 존재하고
     * selectedProductNames 배열에 namevalue가 포함되어 있지 않다면
     * index번째 selectedProductNames 배열의 값을 업데이트 하고
     * 
     * index번째 selectedProductNames 배열에 값이 존재하고
     * selectedProductNames 배열에 namevalue가 포함되어 있다면
     * index번째 selectedProductNames 배열의 값을 초기화하고
     */
    console.log("selectedProductNames: " + selectedProductNames);
    console.log("selectedProductNames[formIndex]: " + selectedProductNames[formIndex]);
    console.log("!!");

    
    if(selectedProductNames[formIndex] === undefined && !(selectedProductNames.includes(namevalue)) ) {
        // 배열에 namevalue 값이 포함되어 있지 않다면
        // 배열에 namevalue 값 추가
        selectedProductNames.push(namevalue);

        console.log(selectedProductNames);
        console.log("1");
    }
    else if(selectedProductNames[formIndex] !== undefined && !(selectedProductNames.includes(namevalue)) ) {
        // index번째 배열의 값이 존재하고, 배열에 namevalue 값이 포함되어 있지 않다면
        // index번째 배열의 값 업데이트
        selectedProductNames[formIndex] = namevalue;

        console.log(selectedProductNames);
        console.log("2");
    }
    else if(selectedProductNames[formIndex] !== undefined && selectedProductNames.includes(namevalue) ) {
        // index 배열에 값이 존재하고, 배열에 namevalue 값이 포함되어 있으면

        // index번째 배열의 값을 비우고, 선택했던 비품명의 val 값 초기화
        if(existMessage.length > 0) {
            // 에러 메시지가 존재한다면

            selectedProductNames[formIndex] = "";

            // 에러메시지가 쌓이지않도록 빈 문자열을 배열에 추가한 후 비품명 값 초기화
            existMessage += "";

            var currentForm = $(this).closest("form"); // 현재 폼 가져오기
            var selectPrdtName = currentForm.find("#select-prdtName");


            loadModal({
                content: existMessage,
                fnPositiveBtnHandler: function () {
                    // 현재 폼 내의 비품명을 초기화
                    selectPrdtName.val("");
                },
                showNegativeBtn: false,
            });
            console.log("3");
        }
        else {
            // 에러 메시지가 비어있다면

            selectedProductNames[formIndex] = "";
            
            // 에러메시지를 배열에 추가한 후 비품명 값 초기화
            existMessage += "이미 선택된 비품입니다. 다른 비품을 선택해주세요.";

            var currentForm = $(this).closest("form"); // 현재 폼 가져오기
            var selectPrdtName = currentForm.find("#select-prdtName");


            loadModal({
                content: existMessage,
                fnPositiveBtnHandler: function () {
                    // 현재 폼 내의 비품명을 초기화
                    selectPrdtName.val("");
                },
                showNegativeBtn: false,
            });
            console.log("4");

        }

        console.log(selectedProductNames);
        console.log("5");


    }
    else {
        // selectedProductNames.includes(namevalue)
        // 배열에 namevalue가 포함되어 있다면
        // 선택했던 비품명의 val 값 초기화 + 에러메시지 모달창 출력
        if(existMessage.length > 0) {
            // 에러 메시지가 존재한다면

            selectedProductNames[formIndex] = "";

            // 에러메시지가 쌓이지않도록 빈 문자열을 배열에 추가한 후 비품명 값 초기화
            existMessage += "";

            var currentForm = $(this).closest("form"); // 현재 폼 가져오기
            var selectPrdtName = currentForm.find("#select-prdtName");


            loadModal({
                content: existMessage,
                fnPositiveBtnHandler: function () {
                    // 현재 폼 내의 비품명을 초기화
                    selectPrdtName.val("");
                },
                showNegativeBtn: false,
            });
            console.log("6");
        }
        else {
            // 에러 메시지가 비어있다면

            selectedProductNames[formIndex] = "";
            
            // 에러메시지를 배열에 추가한 후 비품명 값 초기화
            existMessage += "이미 선택된 비품입니다. 다른 비품을 선택해주세요.";

            var currentForm = $(this).closest("form"); // 현재 폼 가져오기
            var selectPrdtName = currentForm.find("#select-prdtName");


            loadModal({
                content: existMessage,
                fnPositiveBtnHandler: function () {
                    // 현재 폼 내의 비품명을 초기화
                    selectPrdtName.val("");
                },
                showNegativeBtn: false,
            });
            console.log("7");

        }

        console.log(selectedProductNames);
        console.log("8");

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
            content: "취소하시겠습니까?",
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
