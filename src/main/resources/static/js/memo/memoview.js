$().ready(function () {
  $(".modal-close").on("click", function () {
    location.reload();
  });

  $(".delete-button").click(function () {
    const id = $(this).parent("div").parent("div").data("id");

    var alertModal = $(".modal-window");
    var modalButton = $(".confirm-button");
    var modalText = $(".modal-text");
    modalText.text("쪽지를 삭제하시겠습니까?");
    modalButton.text("확인");
    alertModal[0].showModal();

    $(".confirm-button").on("click", function () {
      $.ajax({
        url: "/ajax/memo/delete/" + id,
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
          console.log(data.data.result);
          if (data.data.result === true) {
            $("#" + id).remove();
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
  });

  // list 버튼이 삭제됨..
  // $(".list-button").click(function () {
  //   sessionStorage.setItem("previousPage", document.referrer);
  //   var previousPage = sessionStorage.getItem("previousPage");
  //   if (previousPage) {
  //     window.location.href = previousPage;
  //   } else {
  //     window.location.href = "/memo/sent";
  //   }
  // });

  // $(".write-button").click(function () {
  //   window.location.href = "/memo/write";
  // });

  $(".write-button").click(function () {
    var crtrId = $("#memodetail-crtrId").data("crtrid"); // 데이터 속성 가져오기
    console.log(crtrId);
    sessionStorage.setItem("crtrId", crtrId); // 세션 스토리지에 저장
    window.location.href = "/memo/write"; // 새로운 페이지로 이동
  });

  $(".save-button").click(function () {
    const id = $(this).parent("div").parent("div").data("id");

    var alertModal = $(".modal-window");
    var modalButton = $(".confirm-button");
    var modalText = $(".modal-text");
    modalText.text("쪽지를 저장하시겠습니까?");
    modalButton.text("확인");
    alertModal[0].showModal();

    $(".confirm-button").click(function () {
      $.ajax({
        url: "/ajax/memo/save/" + id,
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
          console.log(data.data.result);
          if (data.data.result === true) {
            $("#" + id).text();
            window.location.href = "/memo/storage";
          } else {
            alert("저장에 실패했습니다. 잠시후 재시도해주세요.");
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
  });
});
