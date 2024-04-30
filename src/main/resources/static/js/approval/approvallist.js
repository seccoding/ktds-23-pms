$().ready(function() {

    // 체크박스 전체선택,선택해제 
    $("#prdt-check-all").on("click", function() {
        var targetClass = $(this).data("target-class");
        var isChecked = $(this).prop("checked");
        $("." + targetClass).prop("checked", isChecked);
    });
    // 기안서 작성
    $("#btn-appr-write").on("click", function() {
        location.href = "/approval/write";
    });
    // 검색
    $("#search-btn").on("click", function() {
        search(0);
    });

    // 초기화
    $("#cancel-search-btn").on("click", function() {
        var path = $(location).attr('pathname');
        location.href = path;
    });

    // 날짜 검색
    $("#btn-search-date").on("click", function() {
        search(0);
    });
});

function search(pageNo) {
    var serachForm = $("#search-form");
    $("#page-no").val(pageNo);

    serachForm.attr("method", "get").submit();
}