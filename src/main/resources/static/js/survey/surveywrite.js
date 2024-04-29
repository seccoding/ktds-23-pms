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

            var srvQstDom = $("<div></div>").addClass("questionnaire-question").text(srvQst);
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
                            'data-next-id': picks[i].nextId, // nextId를 데이터 속성으로 추가
                            'data-content': picks[i].sqpCntnt
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
                $.post("/survey/response/" + prjId);
                window.location.href = "/survey/list"; // 종료 버튼 클릭시 리디렉션
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
            // 현재 선택한 옵션 또는 작성한 답변 가져오기
            var responseContent;
            var selectedRadio = $("input[type='radio'][name='surveyOption" + seqNum + "']:checked");
            if (selectedRadio.length > 0) {
                responseContent = selectedRadio.data('content');
                var nextId = selectedRadio.data('next-id');
            } else {
                responseContent = $("#freeResponse").val().trim();
            }
            
            // 현재 문항의 ID 가져오기
            var srvId = questions[seqNum].srvId;
    
            // AJAX POST 요청으로 데이터 전송
            $.post("/ajax/survey/response/" + srvId, {
                srvId: srvId,
                srvRplCntnt: responseContent
                })
                .done(function(response) {
                    // POST 성공 시 처리할 내용 추가
                    console.log("응답이 성공적으로 전송되었습니다.");
                    if (nextId !== undefined && nextId !== null) {
                        seqNum = findQuestionIndexBySeq(nextId); // nextId로 문항 인덱스 찾기
                        loadQuestion();
                    } else {
                        seqNum++; // 다음 문항으로 인덱스 증가
                        loadQuestion();
                    }
                })
                .fail(function(xhr, status, error) {
                    // POST 실패 시 처리할 내용 추가
                    console.error("응답을 전송하는 동안 오류가 발생했습니다:", error);
                });
        } else {
            alert("답변을 선택하거나 입력해주세요.");
        }
    });

    $(document).on('change', 'input[type="radio"]', function() {
        $('li').removeClass('selected');
        $(this).closest('li').addClass('selected');
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