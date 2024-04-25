$().ready(function () {
  $(".address").click(function (event) {
    event.preventDefault(); // 폼 제출 방지

    var modal = $(".modal-employee-list");
    modal[0].showModal();
  });
  $("#sendButton").click(function () {
    $("#writeForm").submit(); // 폼 제출
  });

  // MutationObserver 설정
  var observer = new MutationObserver(function (mutationsList, observer) {
    mutationsList.forEach(function (mutation) {
      if (mutation.type === "characterData" || mutation.type === "childList") {
        var newValue = $("#special-hidden-datalist").text(); // div에서 새 값 가져오기
        $("#rcvId").val(newValue); // input에 값 설정
      }
    });
  });

  // 감시할 div 선택
  var targetNode = $("#special-hidden-datalist")[0]; // jQuery 객체를 DOM 요소로 변환

  // 감시 설정
  var config = {
    attributes: false,
    childList: true,
    subtree: true,
    characterData: true,
  };
  observer.observe(targetNode, config);
});
