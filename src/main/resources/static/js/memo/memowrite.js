$().ready(function () {
  // CKEditor 로드
  var editors = loadEditor(".editor", "내용을 입력하세요.");

  // CKEditor가 로드 완료되면 추가 작업을 수행
  editors.on("instanceReady", function () {
    // 여기서 추가 작업 가능
    console.log("CKEditor 로드 완료");

    // 만약 CKEditor가 로드된 후에 다른 페이지를 인클루드하려면
    $.get("../commonemployeelist.jsp", function (data) {
      $("#include-target").html(data); // JSP 내용 로드
    });
  });

  $(".address").click(function () {
    var modal = $(".modal-employee-list");
    modal[0].showModal();
  });
});
