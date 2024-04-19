$().ready(function () {
  $(".delete-button").click(function () {
    // const id = $(".delete-button").closest("tr").attr("id");
    const id = $(this).closest("tr").attr("id");
    // console.log("id: " + id);
    if (confirm("후기를 삭제하시겠습니까?")) {
      $.ajax({
        url: "/ajax/review/viewresult/" + id + "/delete",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
          console.log(data.data.result);
          if (data.data.result === true) {
            $("#" + id).remove();
            alert("삭제를 성공했습니다!");
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
    }
  });

  $("#search-btn").on("click", function () {
    search(0);
  });

});

function showModalWithReviewContent(reviewContent) {
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
}

$(document).ready(function () {
  // ellipsis 클래스를 가진 요소가 클릭되었을 때 showModalWithReviewContent 함수 호출
  $(".ellipsis").click(function () {
    var reviewContent = $(this).text(); // 클릭된 ellipsis의 텍스트(후기 내용) 가져오기
    showModalWithReviewContent(reviewContent); // 모달 창에 후기 내용 표시
  });
});


function search(pageNo) {
  var searchForm = $("#search-form");
  // var listSize = $("#list-size");
  $("#page-no").val(pageNo);

  searchForm.attr("method", "get").submit();
}

