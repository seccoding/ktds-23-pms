$().ready(function() {
    $(".prj-list").on("click", function() {
        var prjId = $(this).data("prj-id");
        var srvSts = $(this).data("srv-sts");

        if (srvSts === "N" || srvSts === "W") {
            location.href = '/survey/create?prjId=' + prjId;
        } 
        else {
            location.href = '/survey/write?prjId=' + prjId;
        }
    });
});