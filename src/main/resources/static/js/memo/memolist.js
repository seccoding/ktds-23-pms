$().ready(function () {
  $(".modal-close").on("click", function () {
    location.reload();
  });

  sessionStorage.removeItem("crtrId");
  $(".Receive-loadLink").click(function (event) {
    event.preventDefault(); // 기본 동작 방지
    var targetElement = $(this);
    var id = $(this).data("memo-id");
    var targetUrl = $(this).data("url");
    $("#receive-memo-detail").load(targetUrl, function () {
      $.ajax({
        url: "/ajax/memo/status/" + id, // 엔드포인트 URL
        type: "GET",

        dataType: "json",
        contentType: "application/json",
        success: function (response) {
          console.log(">>>>>" + response.data.result.readYn);
          if (response.data.result.readYn === "Y") {
            // '읽음' 상태라면
            var parentTd = targetElement.closest("tr").find("td.center-align"); // 정확한 부모 요소 찾기
            parentTd
              .find("span.badge")
              .removeClass("bg-label-danger")
              .addClass("bg-success")
              .text("확인"); // '미확인'을 '확인'으로 변경
          }
        },
        error: function () {
          console.error("메모 상태 확인에 실패했습니다."); // 오류 로그 출력
        },
      });
    });
  });

  $(".Sent-loadLink").click(function (event) {
    event.preventDefault(); // 기본 동작 방지
    var targetUrl = $(this).data("url");
    $("#sent-memo-detail").load(targetUrl);
  });

  $(".Storage-loadLink").click(function (event) {
    event.preventDefault(); // 기본 동작 방지
    var targetUrl = $(this).data("url");
    $("#storage-memo-detail").load(targetUrl);
  });

  // 엔터키 방지
  $("form").on("keypress", function (e) {
    if (e.which == 13) {
      e.preventDefault(); // 엔터 키 이벤트를 방지
      return false; // 브라우저의 기본 동작 막기
    }
  });

  $("#deleteMassiveMemo").off("click"); // 기존 리스너 제거
  $("#deleteMassiveMemo").on("click", function (event) {
    event.preventDefault(); // 기본 동작 막기

    // 선택된 체크박스만 가져온다.
    var checkedItems = $(".target-memo-id:checked");
    // 선택된 체크박스만 반복하며 서버로 보낼 파라미터를 생성한다.
    var itemsArray = [];
    checkedItems.each(function (index, data) {
      itemsArray.push($(data).val());
    });

    var alertModal = $(".modal-window");
    var modalButton = $(".confirm-button");
    var modalText = $(".modal-text");

    // 모달 창을 보여주기 전에 기존 이벤트 리스너를 모두 제거하고, 새로운 이벤트 리스너를 추가한다.
    modalButton.off("click"); // 기존 이벤트 리스너 제거
    modalText.text("쪽지를 삭제하시겠습니까?");
    modalButton.text("확인");
    // setTimeout(function () {
    //   alertModal[0].showModal();
    // }, 100); 만약 리스너도 말을 안들으면.. 조금 늦게 열어보자..
    alertModal[0].showModal();

    // 서버로 전송한다(ajax)
    $(".confirm-button").on("click", function () {
      $.get(
        "/ajax/memo/delete/massive",
        { memoIds: itemsArray },
        function (response) {
          var result = response.data.result;
          if (result) {
            // 삭제가 완료되면 현재페이지를 새로고침한다.
            location.reload();
          }
        }
      );
      console.log(response);
    });
  });

  $(".target-memo-id").on("change", function () {
    var targetClass = $("#checked-all").data("target-class");
    var totalCheckboxes = $("." + targetClass).length;
    var checkedCheckboxes = $("." + targetClass + ":checked").length;
    console.log(totalCheckboxes, checkedCheckboxes);
    console.log(targetClass);
    var allChecked = totalCheckboxes === checkedCheckboxes;
    $("#checked-all").prop("checked", allChecked);
  });

  $("#checked-all").on("change", function () {
    var targetClass = $(this).data("target-class");

    // checked-all 의 체크 상태를 가져온다.
    // 체크가 되어있다면 true 아니라면 false
    var isChecked = $(this).prop("checked");

    $("." + targetClass).prop("checked", isChecked);
  });

  $("#status").on("change", function () {
    search(0);
  });

  $("#list-size").on("change", function () {
    search(0);
  });

  $("#search-btn").on("click", function () {
    removeSpaces(); // 공백 제거
    search(0);
  });

  $("#search-btn").keydown(function (event) {
    if (event.key === "Enter") {
      removeSpaces();
      search(0);
    }
  });
});

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
