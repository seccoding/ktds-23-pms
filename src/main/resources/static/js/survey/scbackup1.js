function deleteSurveyQuestion(srvQstDom, seqNum) {
    var nextQst = srvQstDom.nextAll(".survey-question");

    var chooseValue = confirm("정말 삭제하시겠습니까?");
    if (chooseValue) {
        nextQst.each(function() {
            var srvId = $(this).data("srv-id");
            var newSrvQst = $(this).children(".survey-question-middle").children("input").val();
            var currentSrvSeqNum = $(this).children(".survey-question-middle").children("div").text();
            
            var newSrvSeqNum = currentSrvSeqNum - 1;
            $(this).children(".survey-question-middle").children("div").text(newSrvSeqNum);

            var currentTypeYn = $(this).data("type-yn");
            var newTypeYn = 'N';
            if (currentTypeYn) {
                newTypeYn = currentTypeYn;
            }

            if (!newSrvQst) {
                $.post("/ajax/survey/modify/next/" + srvId, { seq: newSrvSeqNum, typeYn: newTypeYn });
            } else {
                $.post("/ajax/survey/modify/" + srvId, {
                    srvQst: newSrvQst,
                    mdfrId: "0509004",
                    seq: newSrvSeqNum,
                    typeYn: newTypeYn
                });
            }                 
        });
        srvQstDom.remove();
        return seqNum - 1;
    }
    return seqNum;
}

function deleteSurveyAnswer(ansDom, updateQstNumCallback) {
    var ansToDel = ansDom.closest("li");
    var nextAnsSeq = ansToDel.nextAll("li").find("div:first-child");

    nextAnsSeq.each(function() {
        var currentAnsSeqNum = parseInt($(this).text(), 10);
        var nextAnsSeqNum = currentAnsSeqNum - 1;
        $(this).text(nextAnsSeqNum);
    });

    ansToDel.remove();
    if (typeof updateQstNumCallback === "function") {
        updateQstNumCallback(-1);
    }
}

function addAnswerItem(ulDom, qstNum, updateQstNumCallback) {
    var nextAnsDom = $("<li></li>");
    var nextAnsSeqDom = $("<div></div>").text(qstNum);
    var nextAnsInputDom = $("<input/>").attr('type', 'text').attr('placeholder', '답변명');
    var nextLinkInputDom = $("<input/>").attr('type', 'text').attr('placeholder', '연결');
    
    nextAnsDom.append(nextAnsSeqDom, nextAnsInputDom, nextLinkInputDom);

    if (qstNum > 2) {
        var deleteSrvAnsButtonDom = $("<button></button>").attr("type", "button").text("제거");
        deleteSrvAnsButtonDom.on("click", function() {
            deleteSurveyAnswer($(this), function(change) {
                updateQstNumCallback(change);
            });
        });
        nextAnsDom.append(deleteSrvAnsButtonDom);
    }

    ulDom.append(nextAnsDom);

    return qstNum + 1;
}

function handleSelectiveTypeClick(srvQstDom, typeYn, seqNum) {
    var srvQstBottomDom = srvQstDom.find(".survey-question-bottom");
    srvQstBottomDom.empty();
    typeYn = 'N';
    srvQstDom.data("type-yn", typeYn);
    
    var ulDom = $("<ul></ul>");
    var qstNum = 1;

    for (let i = 0; i < 2; i++) {
        qstNum = addAnswerItem(ulDom, qstNum, function(change) {
            qstNum += change;
        });
    }

    var addSrvQstButtonDom = $("<button></button>")
        .attr('type', 'button')
        .addClass("add-survey-question")
        .text("답변 항목 추가")
        .on("click", function() {
            qstNum = addAnswerItem(ulDom, qstNum, function(change) {
                qstNum += change;
            });
        });

    var deleteSrvQstButtonDom = $("<button></button>")
        .attr("type", "button")
        .text("문항 삭제")
        .on("click", function() {
            var srvQstDom = $(this).closest(".survey-question");
            seqNum = deleteSurveyQuestion(srvQstDom, seqNum);
        });

    srvQstBottomDom.append(ulDom, addSrvQstButtonDom, deleteSrvQstButtonDom);
}

function handleDescriptiveTypeClick(srvQstDom, typeYn, seqNum) {
    var srvQstBottomDom = srvQstDom.find(".survey-question-bottom");
    srvQstBottomDom.empty();
    typeYn = 'Y'; // 서술형으로 타입 변경
    srvQstDom.data("type-yn", typeYn);

    var deleteSrvQstButtonDom = $("<button></button>")
        .attr("type", "button")
        .text("문항 삭제")
        .on("click", function() {
            var srvQstDom = $(this).closest(".survey-question");
            seqNum = deleteSurveyQuestion(srvQstDom, seqNum);
        });

    srvQstBottomDom.append(deleteSrvQstButtonDom);
}

$().ready(function() {
    var prjId = $(".survey-body").data("prj-id");
    var seqNum = 0;
    var typeYn = 'N';
    // var srvId = $(".survey-question").data("srv-id");
    // if (srvId) {
    //     $(".survey-body").append();
    // }

    $.get("/ajax/survey/get/" + prjId, function(response) {
        var surveys = response.data.surveys;

        for (let i in surveys) {
            var srvId = surveys[i].srvId;
            var srvQst = surveys[i].srvQst;
            var srvQstDom = $("<div></div>");
            srvQstDom.addClass("survey-question");

            var srvQstTopDom = $("<div></div>");
            srvQstTopDom.addClass("survey-question-top");

            var selectiveTypeButtonDom = $("<button></button>");
            selectiveTypeButtonDom.attr("type", "button");
            selectiveTypeButtonDom.addClass("selective-type");
            selectiveTypeButtonDom.text("선택형");

            $(".survey-body").on("click", ".selective-type", function() {
                var srvQstDom = $(this).closest(".survey-question");
                handleSelectiveTypeClick(srvQstDom, typeYn, seqNum);
            });
            
            var descriptiveTypeButtonDom = $("<button></button>");
            descriptiveTypeButtonDom.attr("type", "button");
            descriptiveTypeButtonDom.addClass("descriptive-type");
            descriptiveTypeButtonDom.text("서술형");

            $(".survey-body").on("click", ".descriptive-type", function() {
                var srvQstDom = $(this).closest(".survey-question");
                handleDescriptiveTypeClick(srvQstDom, typeYn, seqNum);
            });

            srvQstTopDom.append(selectiveTypeButtonDom);
            srvQstTopDom.append(descriptiveTypeButtonDom);

            var srvQstMiddleDom = $("<div></div>");
            srvQstMiddleDom.addClass("survey-question-middle");

            var seqDom = $("<div></div>");
            seqDom.text(surveys[i].seq);
            
            var srvQstInputDom = $("<input/>")
            srvQstInputDom.attr('type', 'text');
            srvQstInputDom.attr('placeholder', '질문 입력');
            srvQstInputDom.val(srvQst);

            srvQstMiddleDom.append(seqDom);
            srvQstMiddleDom.append(srvQstInputDom);
            //바텀돔
            var srvQstBottomDom = $("<div></div>");
            srvQstBottomDom.addClass("survey-question-bottom");

            var ulDom = $("<ul></ul>");
            ulDom.addClass("survey-question-list");
            ulDom.attr("data-srv-id", srvId);
            
            if (surveys[i].typeYn === "N") {
                var addSrvQstButtonDom = $("<button></button>");
                addSrvQstButtonDom.attr('type', 'button');
                addSrvQstButtonDom.addClass("add-survey-question");
                addSrvQstButtonDom.text("답변 항목 추가");

                $(".survey-body").off("click", ".add-survey-question").on("click", ".add-survey-question", function(event) {
                    var ulDom = $(this).prev("ul");
                
                    var lastAnsSeqDom = ulDom.find("li:last-child div:first-child");
                    var lastAnsSeqNum = lastAnsSeqDom.length ? parseInt(lastAnsSeqDom.text(), 10) : 0;
                
                    var qstNum = lastAnsSeqNum + 1;
                
                    qstNum = addAnswerItem(ulDom, qstNum, function(change) {
                    });
                });
            }


            var deleteSrvQstButtonDom = $("<button></button>");
            deleteSrvQstButtonDom.attr("type", "button");
            deleteSrvQstButtonDom.text("문항 삭제");

            $(".survey-body").on("click", ".delete-survey-question", function() {
                var srvQstDom = $(this).closest(".survey-question");
                seqNum = deleteSurveyQuestion(srvQstDom, seqNum);
            });

            srvQstBottomDom.append(ulDom);
            if (surveys[i].typeYn === "N") {
                srvQstBottomDom.append(addSrvQstButtonDom);
            }
            srvQstBottomDom.append(deleteSrvQstButtonDom);

            srvQstDom.append(srvQstTopDom);
            srvQstDom.append(srvQstMiddleDom);
            srvQstDom.append(srvQstBottomDom);
            $(".survey-body").append(srvQstDom);       
        }

        // 답변 선택지 추가
        $("ul.survey-question-list").each(function() {
            var ulDom = $(this);
            var srvId = ulDom.data("srv-id");

            $.get("/ajax/survey/get/pick/" + srvId, function(pickResponse) {
                var picks = pickResponse.data.picks;
    
                for (var j in picks) {
                    var AnsDom = $("<li></li>");
                    var AnsSeqDom = $("<div></div>");
                    AnsSeqDom.text(picks[j].seq);
                    var AnsInputDom = $("<input/>");
                    AnsInputDom.attr('type', 'text');
                    AnsInputDom.attr('placeholder', '답변명');
                    AnsInputDom.val(picks[j].sqpCntnt);
                    var LinkInputDom = $("<input/>");
                    LinkInputDom.attr('type', 'text');
                    LinkInputDom.attr('placeholder', '연결');
                    LinkInputDom.val(picks[j].nextId);
    
                    AnsDom.append(AnsSeqDom);
                    AnsDom.append(AnsInputDom);
                    AnsDom.append(LinkInputDom);
                    ulDom.append(AnsDom);
                }
            });
        })
        
    });

    $("#btn-add-srv-qst").on("click", function () {
        seqNum++;
        var srvQstDom = $("<div></div>");
        srvQstDom.addClass("survey-question");
        srvQstDom.attr("data-type-yn", typeYn);

        var srvQstTopDom = $("<div></div>");
        srvQstTopDom.addClass("survey-question-top");

        var selectiveTypeButtonDom = $("<button></button>");
        selectiveTypeButtonDom.attr("type", "button");
        selectiveTypeButtonDom.text("선택형");
        var descriptiveTypeButtonDom = $("<button></button>");
        descriptiveTypeButtonDom.attr("type", "button");
        descriptiveTypeButtonDom.text("서술형");

        srvQstTopDom.append(selectiveTypeButtonDom);
        srvQstTopDom.append(descriptiveTypeButtonDom);

        var srvQstMiddleDom = $("<div></div>");
        srvQstMiddleDom.addClass("survey-question-middle");

        var seqDom = $("<div></div>");
        seqDom.text(seqNum);
        
        var srvQstInputDom = $("<input/>")
        srvQstInputDom.attr('type', 'text');
        srvQstInputDom.attr('placeholder', '질문 입력');

        srvQstMiddleDom.append(seqDom);
        srvQstMiddleDom.append(srvQstInputDom);

        var srvQstBottomDom = $("<div></div>");
        srvQstBottomDom.addClass("survey-question-bottom");

        var ulDom = $("<ul></ul>");

        var firstAnsDom = $("<li></li>");
        var firstAnsSeqDom = $("<div></div>");
        firstAnsSeqDom.text(1);
        var firstAnsInputDom = $("<input/>");
        firstAnsInputDom.attr('type', 'text');
        firstAnsInputDom.attr('placeholder', '답변명');
        var firstLinkInputDom = $("<input/>");
        firstLinkInputDom.attr('type', 'text');
        firstLinkInputDom.attr('placeholder', '연결');

        firstAnsDom.append(firstAnsSeqDom);
        firstAnsDom.append(firstAnsInputDom);
        firstAnsDom.append(firstLinkInputDom);

        var secondAnsDom = $("<li></li>");
        var secondAnsSeqDom = $("<div></div>");
        secondAnsSeqDom.text(2);
        var secondAnsInputDom = $("<input/>");
        secondAnsInputDom.attr('type', 'text');
        secondAnsInputDom.attr('placeholder', '답변명');
        var secondLinkInputDom = $("<input/>");
        secondLinkInputDom.attr('type', 'text');
        secondLinkInputDom.attr('placeholder', '연결');

        secondAnsDom.append(secondAnsSeqDom);
        secondAnsDom.append(secondAnsInputDom);
        secondAnsDom.append(secondLinkInputDom);

        ulDom.append(firstAnsDom);
        ulDom.append(secondAnsDom);

        var addSrvQstButtonDom = $("<button></button>");
        addSrvQstButtonDom.attr('type', 'button');
        addSrvQstButtonDom.addClass("add-survey-question");
        addSrvQstButtonDom.text("답변 항목 추가");

        var qstNum = 3;
        $(addSrvQstButtonDom).on("click", function() {
            qstNum = addAnswerItem(ulDom, qstNum, function(change) {
                qstNum += change;
            });
        });

        var deleteSrvQstButtonDom = $("<button></button>");
        deleteSrvQstButtonDom.attr("type", "button");
        deleteSrvQstButtonDom.text("문항 삭제");

        $(deleteSrvQstButtonDom).on("click", function() {
            var srvQstDom = $(this).closest(".survey-question");
            seqNum = deleteSurveyQuestion(srvQstDom, seqNum);
        });

        srvQstBottomDom.append(ulDom);
        srvQstBottomDom.append(addSrvQstButtonDom);
        srvQstBottomDom.append(deleteSrvQstButtonDom);

        srvQstDom.append(srvQstTopDom);
        srvQstDom.append(srvQstMiddleDom);
        srvQstDom.append(srvQstBottomDom);
        $(".survey-body").append(srvQstDom);

        $(selectiveTypeButtonDom).on("click", function() {
            var srvQstDom = $(this).closest(".survey-question");
            handleSelectiveTypeClick(srvQstDom, typeYn, seqNum);
        });

        $(descriptiveTypeButtonDom).on("click", function() {
            var srvQstDom = $(this).closest(".survey-question");
            handleDescriptiveTypeClick(srvQstDom, typeYn, seqNum);
        });

        $.post("/ajax/survey/create/" + prjId, {
            crtrId: '0509004',
            seq: seqDom.text(),
            typeYn: typeYn
        },
        function(response) {
            srvQstDom.attr("data-srv-id", response.data.srvId);
        });
    });

    $("#btn-compl-srv").on("click", function() {
        var isEmptyQst = false;
        var isEmptyAns = false;

        $(this).parent().find("form").children("div").children("div").each(function() {
            var srvQst = $(this).children("div").eq(1).find("input").val();

            if (!srvQst) {
                isEmptyQst = true;              
            }
        });

        if (isEmptyQst) {
            alert("질문 내용을 입력하지 않은 항목이 있습니다.");
            return;
        }

        $(this).parent().find("form").children("div").children("div").children("div").last().find("li").each(function() {
            var ansDom = $(this).find("input").first();
            var answer = ansDom.val();

            if (!answer) {
                isEmptyAns = true;
            }
        });

        if (isEmptyAns) {
            alert("답변 내용을 입력하지 않은 항목이 있습니다.");
            return;
        }

        $(this).parent().find("form").children("div").children("div").each(function() {
            var that = this;
            var srvId = $(this).data("srv-id");
            var srvQst = $(this).children("div").eq(1).find("input").val();
            var newTypeYn = $(this).data("type-yn");

            if (newTypeYn) {
                typeYn = newTypeYn;
            }

            $.post("/ajax/survey/createbody/" + prjId, {
                srvId: srvId,
                srvQst: srvQst,
                typeYn: typeYn
            }, function(response) {
                $(that).children("div").last().find("li").each(function() {
                    var ansDom = $(this).find("input").first();
                    var answer = ansDom.val();
                    var ansNum = $(this).find("div").text();

                    var nextQstIdDom = $(this).find("input").last();
                    var nextQstId = nextQstIdDom.val();

                    if ((ansDom.data("sqp-id") && ansDom.data("answer") !== answer) 
                            || (nextQstIdDom.data("sqp-id") && nextQstIdDom.data("next-question-id") !== nextQstId)) {
                        $.post("/ajax/survey/answer/modify/" + ansDom.data("sqp-id"), {
                            sqpCntnt: answer,
                            nextId: nextQstId,
                            mdfrId: '0509004',
                            seq: ansNum
                        }, function(response) {
                            console.log(response.data.result);
                        });
                    }
                    else if(!ansDom.data("sqp-id")) {
                        $.post("/ajax/survey/answer/" + srvId, {
                            srvId: srvId,
                            sqpCntnt: answer,
                            nextId: nextQstId,
                            crtrId: '0509004',
                            seq: ansNum
                        }, function(response) {
                            ansDom.attr({"data-sqp-id": response.data.sqpId, "data-answer": answer});
                            nextQstIdDom.attr({"data-sqp-id": response.data.sqpId, "data-next-question-id": nextQstId});
                        });
                    }
                });
            });
        });       
        var chooseValue = confirm("설문 작성을 완료하시겠습니까?");
        if (chooseValue) {
            location.href = "/survey/list";
        }
    });

    function automaticInsert() {
        $(".survey-question").each(function() {
            var that = this;
            var srvId = $(this).data("srv-id");
            var srvQst = $(this).find(".survey-question-middle input[type='text']").val();
            var typeYn = $(this).data("type-yn");
    
            $.post("/ajax/survey/createbody/" + prjId, {
                srvId: srvId,
                srvQst: srvQst,
                typeYn: typeYn
            }, function(response) {
                $(that).children("div").last().find("li").each(function() {
                    var ansDom = $(this).find("input").first();
                    var answer = ansDom.val();
                    var ansNum = $(this).find("div").text();

                    var nextQstIdDom = $(this).find("input").last();
                    var nextQstId = nextQstIdDom.val();

                    if ((ansDom.data("sqp-id") && ansDom.data("answer") !== answer) 
                            || (nextQstIdDom.data("sqp-id") && nextQstIdDom.data("next-question-id") !== nextQstId)) {
                        $.post("/ajax/survey/answer/modify/" + ansDom.data("sqp-id"), {
                            sqpCntnt: answer,
                            nextId: nextQstId,
                            mdfrId: '0509004',
                            seq: ansNum
                        }, function(response) {
                            console.log(response.data.result);
                        });
                    }
                    else if(!ansDom.data("sqp-id")) {
                        $.post("/ajax/survey/answer/" + srvId, {
                            srvId: srvId,
                            sqpCntnt: answer,
                            nextId: nextQstId,
                            crtrId: '0509004',
                            seq: ansNum
                        }, function(response) {
                            ansDom.attr({"data-sqp-id": response.data.sqpId, "data-answer": answer});
                            nextQstIdDom.attr({"data-sqp-id": response.data.sqpId, "data-next-question-id": nextQstId});
                        });
                    }
                });
            });
        });       
    }
    setInterval(automaticInsert, 10000);
});
