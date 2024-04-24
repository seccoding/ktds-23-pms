$().ready(function() {
    var seqNum = 0; // 현재 문항 인덱스
    var questions = [];
    var prjId = $(".survey-body").data("prj-id");

    function loadQuestion() {
        if (seqNum < questions.length) {
            var question = questions[seqNum];
            var srvId = question.srvId;
            var srvQst = question.srvQst;

            $(".survey-question").empty(); // 기존 문항을 지우고 새 문항을 로드

            var srvQstDom = $("<div></div>").text(srvQst);
            $(".survey-question").append(srvQstDom);

            var srvPickDom = $("<div></div>");
            var srvPickUlDom = $("<ul></ul>");

            $.get("/ajax/survey/write/pick/" + srvId, function(pickResponse) {
                var picks = pickResponse.data.picks;
                if (picks && picks.length > 0) {
                    for (var i = 0; i < picks.length; i++) {
                        var srvPickLiDom = $("<li></li>");
                        var radioInput = $("<input>", {
                            type: "radio",
                            name: "surveyOption" + seqNum,
                            value: picks[i].sqpId,
                            'data-next-id': picks[i].nextId // nextId를 데이터 속성으로 추가
                        });
                        var label = $("<label>").text(picks[i].sqpCntnt).prepend(radioInput);
                        srvPickLiDom.append(label);
                        srvPickUlDom.append(srvPickLiDom);
                    }
                    srvPickDom.append(srvPickUlDom);
                    $(".survey-question").append(srvPickDom);
                } else {
                    var textAreaDom = $("<textarea></textarea>", {
                        placeholder: "여기에 답변을 작성하세요",
                        css: {
                            width: "100%",
                            minHeight: "100px"
                        },
                        id: "freeResponse"
                    });
                    srvPickDom.append(textAreaDom);
                    $(".survey-question").append(srvPickDom);
                }
            });
        } else {
            $("#next-srv-btn").text("종료").off('click').on('click', function() {
                window.location.href = "/your-next-page.html"; // 종료 버튼 클릭시 리디렉션
            });
            $(".survey-question").empty().text("모든 설문을 완료했습니다.");
        }
    }

    $.get("/ajax/survey/write/" + prjId, function(response) {
        questions = response.data.questions;
        loadQuestion(); // 초기 문항 로드
    });

    $("#next-srv-btn").on("click", function() {
        if (validateResponse()) {
            var nextId = $("input[type='radio'][name='surveyOption" + seqNum + "']:checked").data('next-id');
            if (nextId !== undefined && nextId !== null) {
                seqNum = findQuestionIndexBySeq(nextId); // nextId로 문항 인덱스 찾기
                loadQuestion();
            } else {
                seqNum++; // 다음 문항으로 인덱스 증가
                loadQuestion();
            }
        } else {
            alert("답변을 선택하거나 입력해주세요.");
        }
    });

    function validateResponse() {
        var responseGiven = false;
        if ($("input[type='radio'][name='surveyOption" + seqNum + "']:checked").length > 0) {
            responseGiven = true;
        }
        if ($("#freeResponse").length > 0 && $("#freeResponse").val().trim() !== "") {
            responseGiven = true;
        }
        return responseGiven;
    }

    function findQuestionIndexBySeq(seq) {
        for (var i = 0; i < questions.length; i++) {
            if (questions[i].seq === seq) {
                return i;
            }
        }
        return seqNum; // 찾지 못했다면 현재 인덱스 반환
    }
});