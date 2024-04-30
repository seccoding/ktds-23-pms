$().ready(function () {

    $("#search-btn").on("click", function () {
        search();
    });


    // $("#search-type").on("change", function () {
    //     $.get(
    //         "/loginlog/view",
    //         {},
    //         function (response) {
    //             var loginDom = $("<option>1개월</option><option>2개월</option>");
    //             $("#login-type").append(loginDom);
    //         }
    //     );
    // });
});
