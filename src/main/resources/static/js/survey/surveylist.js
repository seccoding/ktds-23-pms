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

    
    $(".prj-list").on("click", function(event) {
        var clickedElement = $(event.target);
        var mngrYn = $("#is-manager").val();
        var isPM = $("#is-pm").val();
        var prjId = $(this).data("prj-id");
        var srvSts = $(this).data("srv-sts");
        var srvYn = $(this).find('.survey-yn').data('survey-yn');

        if (clickedElement.hasClass('survey-view')) {
            var prjId = $(this).data("prj-id");
            
            if (isPM === "true" && (srvSts === "N" || srvSts === "W" || !srvSts)) {
                location.href = '/survey/create?prjId=' + prjId;
                event.preventDefault();  // 링크의 기본 동작 중지
                return false;  // 이벤트 전파 중지
            }
            else if ((mngrYn === "Y" || isPM === "true") && srvSts === "Y") {
                location.href = "/survey/view?prjId=" + prjId;
                event.preventDefault();  // 링크의 기본 동작 중지
                return false;  // 이벤트 전파 중지
            }
            else {
                loadModal({
                    content: "아직 설문이 생성되지 않았습니다.",
                    fnPositiveBtnHandler: function () {},
                    showNegativeBtn: false
                });
                event.preventDefault();  // 링크의 기본 동작 중지
                return false;  // 이벤트 전파 중지
            }
        }

        if (clickedElement.tagName === 'A' || $(clickedElement).closest('a').length) {
            return;
        }   

        if (mngrYn === "Y" && srvSts === "Y") {
            location.href = "/survey/view?prjId=" + prjId;
        }
        else if (mngrYn === "Y" && (srvSts === "N" || srvSts === "W" || !srvSts)) {
            loadModal({
                content: "아직 설문이 생성되지 않았습니다.",
                fnPositiveBtnHandler: function () {},
                showNegativeBtn: false
            });
        }
        else if (srvYn === "Y") {
            loadModal({
                content: "해당 프로젝트의 설문은 이미 작성하였습니다.",
                fnPositiveBtnHandler: function () {},
                showNegativeBtn: false
            });
        }
        else if ((srvSts === "N" || srvSts === "W" || !srvSts) && srvYn === "N") {
            loadModal({
                content: "아직 설문이 생성되지 않았습니다.",
                fnPositiveBtnHandler: function () {},
                showNegativeBtn: false
            });
        }
        else if (mngrYn === "N" && srvSts === "Y" && srvYn === "N") {
            location.href = '/survey/write?prjId=' + prjId;
        }
        // else if (mngrYn === "Y" && isPM === "false" && (srvSts === "N" || srvSts === "W" || !srvSts)) {
        //     loadModal({
        //         content: "아직 설문이 생성되지 않았습니다.",
        //         fnPositiveBtnHandler: function () {},
        //         showNegativeBtn: false
        //     });
        // }
    });
});