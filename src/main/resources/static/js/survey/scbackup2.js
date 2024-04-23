function handleDeleteQuestionItem(event) {
    var qstToDel = $(event.currentTarget).closest(".survey-question");
    var nextQst = qstToDel.nextAll(".survey-question");

    var chooseValue = confirm("정말 삭제하시겠습니까?");
    if (chooseValue) {
        nextQst.each(function() {
            var srvId = $(this).data("srv-id");
            var newSrvQst = $(this).children(".survey-question-middle").find("input").val();
            var currentSrvSeqNum = parseInt($(this).find(".survey-question-middle div").text(), 10);
            var newSrvSeqNum = currentSrvSeqNum - 1;

            $(this).children(".survey-question-middle").find("div").text(newSrvSeqNum);

            var currentTypeYn = $(this).data("type-yn");
            var newTypeYn = 'N';
            if (currentTypeYn) {
                newTypeYn = currentTypeYn;
            }

            if (!newSrvQst) {
                $.post("/ajax/survey/modify/next/" + srvId, {
                    seq: newSrvSeqNum,
                    typeYn: newTypeYn
                });
            } else {
                $.post("/ajax/survey/modify/" + srvId, {
                    srvQst: newSrvQst,
                    mdfrId: "0509004",
                    seq: newSrvSeqNum,
                    typeYn: newTypeYn
                });
            }
        });
        qstToDel.remove();
        seqNum = recalculateSeqNum();
    }
}

function addAnswerItem(ulDom) {
    var nextAnsSeqNum = ulDom.find("li").length + 1;

    var nextAnsDom = $("<li></li>");
    var nextAnsSeqDom = $("<div></div>").text(nextAnsSeqNum);
    var nextAnsInputDom = $("<input/>").attr('type', 'text').attr('placeholder', '답변명');
    var nextLinkInputDom = $("<input/>").attr('type', 'text').attr('placeholder', '연결');
    var deleteSrvAnsButtonDom = $("<button></button>").attr("type", "button").addClass("delete-survey-answer").text("제거");

    deleteSrvAnsButtonDom.on("click", function() {
        handleDeleteAnswerItem($(this));
        renumberAnswerItems(ulDom);
    });

    nextAnsDom.append(nextAnsSeqDom, nextAnsInputDom, nextLinkInputDom, deleteSrvAnsButtonDom);
    ulDom.append(nextAnsDom);
}

function handleDeleteAnswerItem(event) {
    var ansToDel = $(event.currentTarget).closest("li");
    var ulDom = ansToDel.parent();

    ansToDel.remove();
    renumberAnswerItems(ulDom);

    ulDom.find("li").each(function() {
        var currentAnsSeqNum = parseInt($(this).find("div:first-child").text(), 10);
        var sqpId = $(this).children("input").first().data("sqp-id");
        var newAns = $(this).children("input").first().val();
        var newNextId = $(this).children("input").last().val();

        if (sqpId) {
            $.post("/ajax/survey/answer/modify/" + sqpId, {
                sqpCntnt: newAns,
                nextId: newNextId,
                mdfrId: '0509004',
                seq: currentAnsSeqNum
            });
        }
    });
}

function handleSelectiveTypeClick(srvQstDom) {
    var srvQstBottomDom = $(srvQstDom).find(".survey-question-bottom");
    $(srvQstBottomDom).empty();
    var typeYn = 'N';
    $(srvQstDom).data("type-yn", typeYn);

    var ulDom = $("<ul></ul>");
    for (var i = 1; i <= 2; i++) {
        var ansDom = $("<li></li>");
        var ansSeqDom = $("<div></div>").text(i);
        var ansInputDom = $("<input/>").attr('type', 'text').attr('placeholder', '답변명');
        var linkInputDom = $("<input/>").attr('type', 'text').attr('placeholder', '연결');
        ansDom.append(ansSeqDom, ansInputDom, linkInputDom);
        ulDom.append(ansDom);
    }

    var addSrvAnsButtonDom = $("<button></button>")
        .attr('type', 'button')
        .addClass("add-survey-answer")
        .text("답변 항목 추가");

    var deleteSrvQstButtonDom = $("<button></button>")
        .attr("type", "button")
        .addClass("delete-survey-question")
        .text("문항 삭제");

    srvQstBottomDom.append(ulDom, addSrvAnsButtonDom, deleteSrvQstButtonDom);
}

function handleDescriptiveTypeClick(event) {
    var srvQstDom = $(event.currentTarget).closest(".survey-question");
    var srvQstBottomDom = srvQstDom.find(".survey-question-bottom");
    srvQstBottomDom.empty();

    var typeYn = 'Y';
    srvQstDom.data("type-yn", typeYn);

    var deleteSrvQstButtonDom = $("<button></button>")
        .attr("type", "button")
        .addClass("delete-survey-question")
        .text("문항 삭제");

    srvQstBottomDom.append(deleteSrvQstButtonDom);
}

function recalculateSeqNum() {
    var maxSeqNum = 0;
    $(".survey-body .survey-question").each(function() {
        var currentSeqNum = parseInt($(this).find(".survey-question-middle div").text(), 10);
        if (currentSeqNum > maxSeqNum) {
            maxSeqNum = currentSeqNum;
        }
    });
    return maxSeqNum + 1;
}

function renumberAnswerItems(ulDom) {
    ulDom.find("li").each(function(index) {
        $(this).find("div:first-child").text(index + 1);
    });
}

$(document).ready(function() {
    var prjId = $(".survey-body").data("prj-id");
    var typeYn = 'N';

    $("#btn-add-srv-qst").on("click", function() {
        var seqNum = recalculateSeqNum();
        var srvQstDom = $("<div></div>");
        srvQstDom.addClass("survey-question");
        srvQstDom.attr("data-type-yn", typeYn);

        var srvQstTopDom = $("<div></div>");
        srvQstTopDom.addClass("survey-question-top");

        var selectiveTypeButtonDom = $("<button></button>");
        selectiveTypeButtonDom.attr("type", "button");
        selectiveTypeButtonDom.addClass("selective-type-button");
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

        var addSrvAnsButtonDom = $("<button></button>")
            .attr('type', 'button')
            .addClass("add-survey-answer")
            .text("답변 항목 추가");

        var deleteSrvQstButtonDom = $("<button></button>");
        deleteSrvQstButtonDom.attr("type", "button");
        deleteSrvQstButtonDom.addClass("delete-survey-question");
        deleteSrvQstButtonDom.text("문항 삭제");

        srvQstBottomDom.append(ulDom);
        srvQstBottomDom.append(addSrvAnsButtonDom);
        srvQstBottomDom.append(deleteSrvQstButtonDom);

        srvQstDom.append(srvQstTopDom);
        srvQstDom.append(srvQstMiddleDom);
        srvQstDom.append(srvQstBottomDom);
        $(".survey-body").append(srvQstDom);

        $(descriptiveTypeButtonDom).on("click", function() {
            $(this).closest(srvQstDom).find(srvQstBottomDom).empty();
            typeYn = 'Y';
            $(this).closest(srvQstDom).data("type-yn", typeYn);
            srvQstBottomDom.append(deleteSrvQstButtonDom);
        });

        $.post("/ajax/survey/write/" + prjId, {
            crtrId: '0509004',
            seq: seqDom.text(),
            typeYn: typeYn
        },
        function(response) {
            srvQstDom.attr("data-srv-id", response.data.srvId);
        });
    });

    $(document).on("click", ".delete-survey-question", handleDeleteQuestionItem);
    $(document).on("click", ".delete-survey-answer", handleDeleteAnswerItem);
    $(document).on("click", ".add-survey-answer", function() {
        var ulDom = $(this).closest(".survey-question-bottom").find("ul");
        addAnswerItem(ulDom);
    });
    $(document).on("click", ".selective-type-button", function() {
        var srvQstDom = $(this).closest(".survey-question");
        handleSelectiveTypeClick(srvQstDom);
    });
    $(document).on("click", ".descriptive-type-button", function(event) {
        handleDescriptiveTypeClick(event);
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

            $.post("/ajax/survey/writebody/" + prjId, {
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
        var thisForAutoInsert = $("#btn-compl-srv").parent().find("form").children("div").children("div");
        thisForAutoInsert.each(function() {
            var that = thisForAutoInsert;
            var srvId = thisForAutoInsert.data("srv-id");
            var srvQst = thisForAutoInsert.children("div").eq(1).find("input").val();
            var newTypeYn = thisForAutoInsert.data("type-yn");

            if (newTypeYn) {
                typeYn = newTypeYn;
            }

            $.post("/ajax/survey/writebody/" + prjId, {
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