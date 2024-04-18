$().ready(function() {
    var seqNum = 0;
    var prjId = $(".survey-body").data("id");
    var typeYn = 'N';
    $("#btn-add-srv-qst").on("click", function() {
        seqNum++;
        var srvQstDom = $("<div></div>");
        srvQstDom.addClass("survey-question");

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

        var insertSrvQstButtonDom = $("<button></button>");
        insertSrvQstButtonDom.attr("type", "button");
        insertSrvQstButtonDom.text("문항 완성");
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
        srvQstBottomDom.append(insertSrvQstButtonDom);

        srvQstDom.append(srvQstTopDom);
        srvQstDom.append(srvQstMiddleDom);
        srvQstDom.append(srvQstBottomDom);
        $(".survey-body").append(srvQstDom);

        
        $(selectiveTypeButtonDom).on("click", function() {
            $(this).closest(srvQstDom).find(srvQstBottomDom).empty();
            typeYn = 'N';
            $(this).closest(srvQstDom).attr("data-type-yn", typeYn);
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
    
            var insertSrvQstButtonDom = $("<button></button>");
            insertSrvQstButtonDom.attr("type", "button");
            insertSrvQstButtonDom.text("문항 완성");
            var deleteSrvQstButtonDom = $("<button></button>");
            deleteSrvQstButtonDom.attr("type", "button");
            deleteSrvQstButtonDom.text("문항 삭제");

            $(deleteSrvQstButtonDom).on("click", function() {
                var chooseValue = confirm("정말 삭제하시겠습니까?");
                if (chooseValue) {
                    $(this).closest(srvQstDom).remove();
                    seqNum--;
                }
            });
    
            srvQstBottomDom.append(ulDom);
            srvQstBottomDom.append(addSrvQstButtonDom);
            srvQstBottomDom.append(deleteSrvQstButtonDom);
            srvQstBottomDom.append(insertSrvQstButtonDom);
        });

        $(descriptiveTypeButtonDom).on("click", function() {
            $(this).closest(srvQstDom).find(srvQstBottomDom).empty();
            typeYn = 'Y';
            $(this).closest(srvQstDom).attr("data-type-yn", typeYn);
            srvQstBottomDom.append(deleteSrvQstButtonDom);
            srvQstBottomDom.append(insertSrvQstButtonDom);
        });

        $.post("/ajax/survey/write/" + prjId, {
            crtrId: '0509004',
            seq: seqDom.text(),
            typeYn: typeYn
        },
        function(response) {
            srvQstDom.attr("data-srv-id", response.data.srvId);
        });

        $("#btn-compl-srv").on("click", function() {
            

            console.log(this.closest("body"));
            
            // .each(function() {
            //     var srvQst = $(this).children("div").first().find("input").val();
            //     console.log(srvQst);
            // });
        });

        // $(insertSrvQstButtonDom).on("click", function() {
        //     var srvQst = $(srvQstInputDom).val();
        //     var that = this;
        //     $.post("/ajax/survey/writebody/" + prjId, {
        //         srvId: srvQstDom.data("srv-id"),
        //         srvQst: srvQst,
        //         typeYn: typeYn
        //     }, function(response) {
        //         $(that).closest(".survey-question-bottom").find("li").each(function() {
        //             var answerDom = $(this).find("input").first();
        //             var answer = answerDom.val();
        //             var ansNum = $(this).find("div").text();

        //             var nextQuestionIdDom = $(this).find("input").last();
        //             var nextQuestionId = nextQuestionIdDom.val();

        //             if ((answerDom.data("sqp-id") && answerDom.data("answer") !== answer) 
        //                     || (nextQuestionIdDom.data("sqp-id") && nextQuestionIdDom.data("next-question-id") !== nextQuestionId)) {
        //                 $.post("/ajax/survey/answer/modify/" + answerDom.data("sqp-id"), {
        //                     sqpCntnt: answer,
        //                     nextId: nextQuestionId,
        //                     mdfrId: '0509004',
        //                     seq: ansNum
        //                 }, function(response) {
        //                     console.log(response.data.result);
        //                 });
        //             }
 
        //             else if(!answerDom.data("sqp-id")) {
        //                 $.post("/ajax/survey/answer/" + srvQstDom.data("srv-id"), {
        //                     srvId: srvQstDom.data("srv-id"),
        //                     sqpCntnt: answer,
        //                     nextId: nextQuestionId,
        //                     crtrId: '0509004',
        //                     seq: ansNum
        //                 }, function(response) {
        //                     answerDom.attr({"data-sqp-id": response.data.sqpId, "data-answer": answer});
        //                     nextQuestionIdDom.attr({"data-sqp-id": response.data.sqpId, "data-next-question-id": nextQuestionId});
        //                 });
        //             }
        //         });
        //     });
        // });
    });
});
