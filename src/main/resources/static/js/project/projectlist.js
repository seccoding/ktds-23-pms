$().ready(function () {
    // 엔터키 방지
    $('form').on('keypress', function(e) {
        if (e.which == 13) {
            e.preventDefault();  // 엔터 키 이벤트를 방지합니다.
            return false;        // 브라우저의 기본 동작을 막습니다.
        }
    });

    $(".project-row").on( "mouseenter", function() {
        // 마우스가 해당 행 위에 올라갔을 때 스타일 변경
        $(this).addClass("hovered-row");
    }).on("mouseleave", function() {
        // 마우스가 해당 행 위에서 벗어났을 때 스타일 변경 해제
        $(this).removeClass("hovered-row");
    });

    $(".project-row").on("click", function() {
        // 클릭한 행의 프로젝트 ID 가져오기
        var projectId = $(this).data("project-id");
        // 프로젝트 페이지로 이동
        location.href = "/project/view?prjId=" + projectId;
    });

    $("#status").on("change", function () {
        search(0);
    });

    $("#list-size").on("change", function () {
        search(0);
    });

    $("#search-btn").on("click", function () {
        search(0);
    });

    $("#cancel-search-btn").on("click", function () {
        location.href = "/project/search";
    });
})
