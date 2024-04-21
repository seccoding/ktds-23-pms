$().ready(function() {
    $(".prj-list").on("click", function() {
        var prjId = $(this).data("prj-id");
        var srvSts = $(this).data("srv-sts");

        if (srvSts === "N" || srvSts === "W") {
            location.href = '/survey/write/' + prjId;
        } 
        else {
            location.href = '/project/search';
        }
    });
});