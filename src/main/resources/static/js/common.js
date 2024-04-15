$().ready(function () {

    // 날짜 체크 1, 프로젝트 종료일이 프로젝트 시작일보다 빠르면 alert 를 발생하고,
    // 프로젝트 종료일을 초기화한다.
    $('#end-date').on("change", function () {
        var startDate = $('#start-date').val();
        var endDate = $('#end-date').val();

        // 시작일과 종료일을 비교
        if (startDate && endDate && endDate <= startDate) {
            // 종료일이 시작일보다 이전이거나 같다면 경고를 표시하고 초기화
            alert('종료일은 시작일보다 이후여야 합니다. 날짜를 다시 설정해주세요.');
            $('#end-date').val(''); // 종료일 초기화
        }
    });

    // 날짜 체크 2, 프로젝트 종료일이 선택된 후, 프로젝트 시작일을 수정하는 경우 alert 발생
    // 프로젝트 시작일 유지
    $('#start-date').on("change", function () {
            var startDate = $('#start-date').val();
            var endDate = $('#end-date').val();

            if (startDate && endDate && endDate <= startDate) {
                alert('시작일은 종료일보다 이전이여야 합니다. 날짜를 다시 설정해주세요.');
                $('#start-date').val(''); // 시작일 초기화
            }
        }
    );

    // 날짜 최소, 최대 값 설정
    var today = new Date();
    var startOfYear = new Date(today.getFullYear(), 0, 1); // 올해의 1월 1일
    var endOfFiveYears = new Date(today.getFullYear() + 5, 11, 31); // 5년 후의 12월 31일

    // 날짜를 YYYY-MM-DD 형식으로 변환
    var minStartDate = startOfYear.toISOString().slice(0, 10);
    var maxEndDate = endOfFiveYears.toISOString().slice(0, 10);

    // 시작일(input#start-date)에 min 속성 설정
    $('#start-date').attr('min', minStartDate);
    $('#start-date').attr('max', maxEndDate); // 시작일의 max를 종료일의 max와 동일하게 설정할 수 있습니다.

    // 종료일(input#end-date)에 min과 max 속성 설정
    $('#end-date').attr('min', minStartDate); // 종료일의 min을 시작일의 min과 동일하게 설정할 수 있습니다.
    $('#end-date').attr('max', maxEndDate);

})

// 검색
function search(pageNo) {
    var searchForm = $("#search-form");
    $("#page-no").val(pageNo);

    searchForm.attr("method", "get").submit();
}

$().ready(function () {
  $(".sidebar-submenu").each(function () {
    $(this)
      .find(".sidebar-submenu-content")
      .on("click", function (event) {
        event.preventDefault();
        $(this).find(".dropdown-icon").toggleClass("active");
        var dropdown_content = $(this).siblings(".dropdown-menu");
        var dropdown_content_lis = dropdown_content.find("a");
        var active_height =
          dropdown_content_lis.eq(0).outerHeight() *
          dropdown_content_lis.length;
        dropdown_content
          .toggleClass("active")
          .css(
            "height",
            dropdown_content.hasClass("active") ? active_height + "px" : "0"
          );
      });
  });
});