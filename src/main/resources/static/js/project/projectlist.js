$().ready(function () {
    // 엔터키 방지
    $('form').on('keypress', function(e) {
        if (e.which == 13) {
            e.preventDefault();  // 엔터 키 이벤트를 방지합니다.
            return false;        // 브라우저의 기본 동작을 막습니다.
        }
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
