// 검색
function search(pageNo) {
    var searchForm = $("#search-form");
    $("#page-no").val(pageNo);

    searchForm.attr("method", "get").submit();
}
