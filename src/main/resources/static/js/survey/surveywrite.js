$().ready(function() {
    var seqNum = 0;
    var prjId = $(".survey-body").data("id");
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
                var closestUlDom = ansToDel.closest(ulDom);
                ansToDel.remove();
                
                var allAnsSeqLength = closestUlDom.find("li").find("div").length;

                var allAnsSeqList = new Array();

                console.log(closestUlDom.find("li").find("div"));
                // allAnsSeqList.push(closestUlDom.find("li").find("div"));

                // for (var i = 0; i < allAnsSeq; i++){
                    // allAnsSeqList[i].push(allAnsSeq);
                // }

                // console.log(allAnsSeqList);
                
                // for (var i = 0; i < allAnsSeqLength; i++) {
                //     allAnsSeqList[i].push(i + 1);
                // }

                // nextAns.closest(ulDom).find("li").each(function(response) {
                //     var newAnsNum = 1;
                //     $(this).find("div").text(newAnsNum);
                //     newAnsNum++;
                // });
                
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

        var typeYn = 'N';
        $(selectiveTypeButtonDom).on("click", function() {
            $(this).closest(srvQstDom).find(srvQstBottomDom).empty();
            typeYn = 'N';
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

        $(insertSrvQstButtonDom).on("click", function() {
            var srvQst = $(srvQstInputDom).val();
            var that = this;
            $.post("/ajax/survey/writebody/" + prjId, {
                srvId: srvQstDom.data("srv-id"),
                srvQst: srvQst,
                typeYn: typeYn
            }, function(response) {
                $(that).closest(".survey-question-bottom").find("li").each(function() {
                    var answerDom = $(this).find("input").first();
                    var answer = answerDom.val();
                    var ansNum = $(this).find("div").text();

                    var nextQuestionIdDom = $(this).find("input").last();
                    var nextQuestionId = nextQuestionIdDom.val();

                    if ((answerDom.data("sqp-id") && answerDom.data("answer") !== answer) 
                            || (nextQuestionIdDom.data("sqp-id") && nextQuestionIdDom.data("next-question-id") !== nextQuestionId)) {
                        console.log(answer);
                        $.post("/ajax/survey/answer/modify/" + answerDom.data("sqp-id"), {
                            sqpCntnt: answer,
                            nextId: nextQuestionId,
                            mdfrId: '0509004'
                        }, function(response) {
                            console.log(response.data.result);
                        });
                    }
                    // else if((answerDom.data("sqp-id") && answerDom.data("answer") == answer) 
                    //         && (nextQuestionIdDom.data("sqp-id") && nextQuestionIdDom.data("next-question-id") == nextQuestionId)) {

                    // }
                    else if(!answerDom.data("sqp-id")) {
                        $.post("/ajax/survey/answer/" + srvQstDom.data("srv-id"), {
                            srvId: srvQstDom.data("srv-id"),
                            sqpCntnt: answer,
                            nextId: nextQuestionId,
                            crtrId: '0509004',
                            seq: ansNum
                        }, function(response) {
                            // answer input 에 data를 추가.
                            answerDom.attr({"data-sqp-id": response.data.sqpId, "data-answer": answer});
                            nextQuestionIdDom.attr({"data-sqp-id": response.data.sqpId, "data-next-question-id": nextQuestionId});
                        });
                    }
                });
            });
            
            
        });

        // $(insertSrvQstButtonDom).on("click", function() {
        //     var srvQst = $(srvQstInputDom).val();
        //     var closestQstNum = $(this).closest(srvQstBottomDom)
        //                  .closest(srvQstDom).find(srvQstMiddleDom)
        //                  .find(seqDom).text();
                    
        //     $.post("/ajax/survey/write/" + prjId, {
        //         srvQst: srvQst,
        //         crtrId: '0509004',
        //         seq: closestQstNum,
        //         typeYn: typeYn,
        //     }, 
        //     function(response) {
        //         var result = response.data.result;
        //         if(result){
        //             alert("성공!")
        //         }
        //         else{
        //             alert("실패!")
        //         }             
        //     });
        // });
    });
});
