$().ready(function() {
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
        location.href = "/survey/list";
    });

    
    $(".prj-list").on("click", function() {
        var mngrYn = $("#is-manager").val();
        var isPM = $("#is-pm").val();
        var prjId = $(this).data("prj-id");
        var srvSts = $(this).data("srv-sts");
        var srvYn = $(this).find('.survey-yn').data('survey-yn');

        if (isPM === "true" && (srvSts === "N" || srvSts === "W" || !srvSts)) {
            location.href = '/survey/create?prjId=' + prjId;
        }
        else if (mngrYn === "Y" && isPM === "false" && (srvSts === "N" || srvSts === "W" || !srvSts)) {
            location.href = "/project/view?prjId=" + prjId;
        }
        else if ((mngrYn === "Y" || isPM === "true") && srvSts === "Y") {
            location.href = "/survey/view?prjId=" + prjId;
        }
        else if (srvYn === "Y") {
            alert("해당 프로젝트의 설문은 이미 작성하였습니다.");
        }
        else {
            location.href = '/survey/write?prjId=' + prjId;
        }
    });
});