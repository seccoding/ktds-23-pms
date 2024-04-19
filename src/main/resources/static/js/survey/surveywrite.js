$().ready(function() {
    var prjId = $(".survey-body").data("id");
    var seqNum = 0;
    var typeYn = 'N';
    $("#btn-add-srv-qst").on("click", function () {
        seqNum++;
        var srvQstDom = $("<div></div>");
        srvQstDom.addClass("survey-question");
        srvQstDom.attr("data-type-yn", typeYn);

        var srvQstTopDom = $("<div></div>");
        srvQstTopDom.addClass("survey-question-top");

        var spanDom = $("<span></span>");
        spanDom.text("문항 형태");
        var selectiveTypeButtonDom = $("<button></button>");
        selectiveTypeButtonDom.attr("type", "button");
        selectiveTypeButtonDom.text("선택형");
        var descriptiveTypeButtonDom = $("<button></button>");
        descriptiveTypeButtonDom.attr("type", "button");
        descriptiveTypeButtonDom.text("서술형");

        srvQstTopDom.append(spanDom);
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
        addSrvQstButtonDom.text("답변 항목 추가");

        var qstNum = 3;
        $(addSrvQstButtonDom).on("click", function() {
            var nextAnsDom = $("<li></li>");
            var nextAnsSeqDom = $("<div></div>");
            nextAnsSeqDom.text(qstNum);
            qstNum++;
            var nextAnsInputDom = $("<input/>");
            nextAnsInputDom.attr('type', 'text');
            nextAnsInputDom.attr('placeholder', '답변명');
            var nextLinkInputDom = $("<input/>");
            nextLinkInputDom.attr('type', 'text');
            nextLinkInputDom.attr('placeholder', '연결');
            var deleteSrvQstButtonDom = $("<button></button>");
            deleteSrvQstButtonDom.attr("type", "button");
            deleteSrvQstButtonDom.text("제거");

            $(deleteSrvQstButtonDom).on("click", function() {
                var ansToDel = $(this).closest(nextAnsDom);
                var nextAnsSeq = ansToDel.nextAll("li").find("div");
                
                nextAnsSeq.each(function() {                   
                    var sqpId = $(this).closest("li").children("input").first().data("sqp-id");
                    var newAns = $(this).closest("li").children("input").first().val();
                    var newNextId = $(this).closest("li").children("input").last().val();

                    var currentAnsSeqNum = $(this).text();
                    var nextAnsSeqNum = currentAnsSeqNum - 1;
                    $(this).text(nextAnsSeqNum);

                    if (sqpId) {
                        $.post("/ajax/survey/answer/modify/" + sqpId, {
                            sqpCntnt: newAns,
                            nextId: newNextId,
                            mdfrId: '0509004',
                            seq: nextAnsSeqNum
                        });
                    }
                });
                ansToDel.remove();
                qstNum--;                
            });

            nextAnsDom.append(nextAnsSeqDom);
            nextAnsDom.append(nextAnsInputDom);
            nextAnsDom.append(nextLinkInputDom);
            nextAnsDom.append(deleteSrvQstButtonDom);
            ulDom.append(nextAnsDom);
        });

        var deleteSrvQstButtonDom = $("<button></button>");
        deleteSrvQstButtonDom.attr("type", "button");
        deleteSrvQstButtonDom.text("문항 삭제");

        $(deleteSrvQstButtonDom).on("click", function() {
            var qstToDel = $(this).closest(srvQstDom);
            var nextQst = qstToDel.nextAll(srvQstDom);
 
            var chooseValue = confirm("정말 삭제하시겠습니까?");
            if (chooseValue) {
                nextQst.each(function() {
                    var srvId = $(this).data("srv-id");
                    var newSrvQst = $(this).children("div").eq(1).children("input").val();
                    var currentSrvSeqNum = $(this).children("div").eq(1).children("div").text();
                    
                    var newSrvSeqNum = currentSrvSeqNum - 1;

                    $(this).children("div").eq(1).children("div").text(newSrvSeqNum);

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
                    }
                    else {
                        $.post("/ajax/survey/modify/" + srvId, {
                            srvQst: newSrvQst,
                            mdfrId: "0509004",
                            seq: newSrvSeqNum,
                            typeYn: newTypeYn
                        });
                    }                 
                });
                qstToDel.remove();
                seqNum--;
            }
        });

        srvQstBottomDom.append(ulDom);
        srvQstBottomDom.append(addSrvQstButtonDom);
        srvQstBottomDom.append(deleteSrvQstButtonDom);

        srvQstDom.append(srvQstTopDom);
        srvQstDom.append(srvQstMiddleDom);
        srvQstDom.append(srvQstBottomDom);
        $(".survey-body").append(srvQstDom);

        $(selectiveTypeButtonDom).on("click", function() {
            $(this).closest(srvQstDom).find(srvQstBottomDom).empty();
            typeYn = 'N';
            $(this).closest(srvQstDom).data("type-yn", typeYn);
            
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
            addSrvQstButtonDom.text("답변 항목 추가");
    
            qstNum = 3;
            $(addSrvQstButtonDom).on("click", function() {
                var nextAnsDom = $("<li></li>");
                var nextAnsSeqDom = $("<div></div>");
                nextAnsSeqDom.text(qstNum);
                qstNum++;
                var nextAnsInputDom = $("<input/>");
                nextAnsInputDom.attr('type', 'text');
                nextAnsInputDom.attr('placeholder', '답변명');
                var nextLinkInputDom = $("<input/>");
                nextLinkInputDom.attr('type', 'text');
                nextLinkInputDom.attr('placeholder', '연결');
                var deleteSrvQstButtonDom = $("<button></button>");
                deleteSrvQstButtonDom.attr("type", "button");
                deleteSrvQstButtonDom.text("제거");
    
                $(deleteSrvQstButtonDom).on("click", function() {
                    $(this).closest(nextAnsDom).remove();
                    qstNum--;
                });
    
                nextAnsDom.append(nextAnsSeqDom);
                nextAnsDom.append(nextAnsInputDom);
                nextAnsDom.append(nextLinkInputDom);
                nextAnsDom.append(deleteSrvQstButtonDom);
                ulDom.append(nextAnsDom);
            });
    
            var deleteSrvQstButtonDom = $("<button></button>");
            deleteSrvQstButtonDom.attr("type", "button");
            deleteSrvQstButtonDom.text("문항 삭제");

            $(deleteSrvQstButtonDom).on("click", function() {
                var qstToDel = $(this).closest(srvQstDom);
                var nextQst = qstToDel.nextAll(srvQstDom);
     
                var chooseValue = confirm("정말 삭제하시겠습니까?");
                if (chooseValue) {
                    nextQst.each(function() {
                        var srvId = $(this).data("srv-id");
                        var newSrvQst = $(this).children("div").eq(1).children("input").val();
                        var currentSrvSeqNum = $(this).children("div").eq(1).children("div").text();
                        
                        var newSrvSeqNum = currentSrvSeqNum - 1;
    
                        $(this).children("div").eq(1).children("div").text(newSrvSeqNum);
    
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
                        }
                        else {
                            $.post("/ajax/survey/modify/" + srvId, {
                                srvQst: newSrvQst,
                                mdfrId: "0509004",
                                seq: newSrvSeqNum,
                                typeYn: newTypeYn
                            });
                        }                 
                    });
                    qstToDel.remove();
                    seqNum--;
                }
            });
    
            srvQstBottomDom.append(ulDom);
            srvQstBottomDom.append(addSrvQstButtonDom);
            srvQstBottomDom.append(deleteSrvQstButtonDom);

        });

        $(descriptiveTypeButtonDom).on("click", function() {
            $(this).closest(srvQstDom).find(srvQstBottomDom).empty();
            typeYn = 'Y';
            $(this).closest(srvQstDom).data("type-yn", typeYn);
            srvQstBottomDom.append(deleteSrvQstButtonDom);

            $(deleteSrvQstButtonDom).on("click", function() {
                var qstToDel = $(this).closest(srvQstDom);
                var nextQst = qstToDel.nextAll(srvQstDom);
     
                var chooseValue = confirm("정말 삭제하시겠습니까?");
                if (chooseValue) {
                    nextQst.each(function() {
                        var srvId = $(this).data("srv-id");
                        var newSrvQst = $(this).children("div").eq(1).children("input").val();
                        var currentSrvSeqNum = $(this).children("div").eq(1).children("div").text();
                        
                        var newSrvSeqNum = currentSrvSeqNum - 1;
    
                        $(this).children("div").eq(1).children("div").text(newSrvSeqNum);
    
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
                        }
                        else {
                            $.post("/ajax/survey/modify/" + srvId, {
                                srvQst: newSrvQst,
                                mdfrId: "0509004",
                                seq: newSrvSeqNum,
                                typeYn: newTypeYn
                            });
                        }                 
                    });
                    qstToDel.remove();
                    seqNum--;
                }
            });
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

    $("#btn-compl-srv").on("click", function() {
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
    });
    setInterval(function() {
        $("#btn-compl-srv").trigger("click");
    }, 10000);
});
