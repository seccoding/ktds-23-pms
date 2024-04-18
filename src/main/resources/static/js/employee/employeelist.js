$().ready(function () {
    $("#list-size").on("change", function () {
        search(0);
    });
  
    $("#search-btn").on("click", function () {
        search(0);
    });

});