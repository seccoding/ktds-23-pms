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