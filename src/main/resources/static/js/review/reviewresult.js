$().ready(function () {
  $(".modal-close").on("click", function () {
    location.reload();
  });

  $(".confirm-button").on("click", function () {
    $.ajax({
      url: "/ajax/review/viewresult/" + id + "/delete",
      type: "GET",
      dataType: "json",
      contentType: "application/json",
      success: function (data) {
        console.log(data.data.result);
        if (data.data.result === true) {
          $("#" + id).remove();
          /*alert("삭제를 성공했습니다!");*/
          window.location.reload();
        } else {
          alert("삭제에 실패했습니다. 잠시후 재시도해주세요.");
        }
      },
      error: function (request, status, error) {
        console.log("error...");
      },
      complete: function () {
        console.log("complete...");
      },
    });
  });

  $("#deleteMassiveReview").off("click");

  $("#deleteMassiveReview").on("click", function (event) {
    event.preventDefault();

    // 선택된 체크박스만 가져온다.
    var checkedItems = $(".target-review-id:checked");
    // 선택된 체크박스만 반복하며 서버로 보낼 파라미터를 생성한다.
    var itemsArray = [];
    checkedItems.each(function (index, data) {
      itemsArray.push($(data).val());
    });
    console.log(itemsArray);
    // 서버로 전송한다(ajax)

    /*var alertModal = $(".modal-window");
    var modalButton = $(".confirm-button");
    var modalText = $(".modal-text");
    modalText.text("쪽지를 삭제하시겠습니까?");
    modalButton.text("확인");
    alertModal[0].showModal();
    */

    loadModal({
      content: "선택한 후기를 삭제하시겠습니까?",
      fnPositiveBtnHandler: function () {
        $.post(
          "/ajax/review/delete/massive",
          { reviewIds: itemsArray },
          function (response) {
            var result = response.data.result;
            if (result) {
              // 삭제가 완료되면 현재페이지를 새로고침한다.
              location.reload();
            }
          }
        );
      },
    });
  });

  $(".mngr-yn").on("click", function () {
    loadModal({
      content: "후기 작성 대상이 아닙니다.",
      fnPositiveBtnHandler: function () {},
      showNegativeBtn: false,
    });
  });

  $(".review-yn").on("click", function () {
    loadModal({
      content: "이미 후기를 작성하셨습니다.",
      fnPositiveBtnHandler: function () {},
      showNegativeBtn: false,
    });
  });

  $(".ellipsis").on("click", function () {
    var reviewContent = $(this).text();
    loadmyModal(
      {
        content: reviewContent,
        fnPositiveBtnHandler: function () {},
        showNegativeBtn: false,
      }
      /*$(".modal-confirm-text").css({
			"paddig":"1rem", 

			"color": "black",
  			"font-size": "13px",
    		"font-weight": "normal",
 			"overflow": "auto"}),*/
    );
  });

  $(".modal-confirm-close").on("click", function () {
    location.reload();
  });

  $("#list-size").on("change", function () {
    search(0);
  });

  $("#checked-all").on("change", function () {
    var targetClass = $(this).data("target-class");

    // checked-all 의 체크 상태를 가져온다.
    // 체크가 되어있다면 true 아니라면 false
    var isChecked = $(this).prop("checked");

    $("." + targetClass).prop("checked", isChecked);
  });

  $(".delete-button").click(function () {
    // const id = $(".delete-button").closest("tr").attr("id");
    const id = $(this).closest("tr").attr("id");

    $(".prjId").on("click", function () {
      $(location).attr(
        "href",
        "/review/viewresult?prjId=" + $(this).attr("id")
      );
    });
    var alertModal = $(".modal-window");
    var modalButton = $(".confirm-button");
    var modalText = $(".modal-text");
    modalText.text("후기를 삭제하시겠습니까?");
    modalButton.text("확인");
    alertModal[0].showModal();
    // console.log("id: " + id);
    /*    if (confirm("후기를 삭제하시겠습니까?")) {
     */

    $("#search-btn").on("click", function () {
      removeSpaces(); // 공백 제거
      search(0);
    });
  });
});

/*function showModalWithReviewContent(reviewContent) {
  $.get("/html/modal.html", function (modalHtml) {
    // 모달의 제목 설정
    var title = "Review Detail";

    // 모달 HTML에 제목과 후기 내용 삽입
    modalHtml = modalHtml.replaceAll("#title#", title);
    modalHtml = modalHtml.replaceAll("#html#", reviewContent);

    // 모달 요소 생성
    var reviewModal = $(modalHtml);

    // 닫기 버튼에 이벤트 리스너 추가
    reviewModal.find("button").on("click", function (event) {
      reviewModal[0].close();
    });

    // 모달을 body의 맨 앞에 삽입하여 보이도록 함
    $("body").prepend(reviewModal);

    // showModal() 메서드를 사용하여 모달을 표시
    reviewModal[0].showModal();
  });
}*/

function loadmyModal({
  content = "",
  positiveBtnName = "확인",
  fnPositiveBtnHandler = () => {},
  showNegativeBtn = true,
  negatgiveBtnName = "취소",
  fnNegativeBtnHandler = () => {},
}) {
  $.get("/html/newmodal.html", function (modalHtml) {
    var modal = $(modalHtml);
    $("body").prepend(modal);

    var alertModal = $(".modal-confirm-window").addClass(
      "modal-confirm-window1"
    );
    var modalButton = $(".confirm-confirm-button").addClass(
      "confirm-confirm-button1"
    );
    var modalButton1 = $(".cancel-confirm-button").addClass(
      "cancel-confirm-button1"
    );
    var modalText = $(".modal-confirm-text").addClass("modal-confirm-text1");
    modalText.text(content);
    modalButton.text(positiveBtnName);
    modalButton.on("click", function () {
      try {
        fnPositiveBtnHandler();
      } finally {
        alertModal[0].close();
        modal.remove();
      }
    });

    modalButton1.text(negatgiveBtnName);
    modalButton1.on("click", function () {
      try {
        fnNegativeBtnHandler();
      } finally {
        alertModal[0].close();
        modal.remove();
      }
    });

    if (!showNegativeBtn) {
      modalButton1.css("display", "none");
    }
    alertModal[0].showModal();
  });
}

function search(pageNo) {
  var searchForm = $("#search-form");
  // var listSize = $("#list-size");
  $("#page-no").val(pageNo);

  searchForm.attr("method", "get").submit();
}

function removeSpaces() {
  var inputField = document.querySelector("input[name='searchKeyword']");
  inputField.value = inputField.value.replace(/\s+/g, ""); // 모든 공백 제거
}
