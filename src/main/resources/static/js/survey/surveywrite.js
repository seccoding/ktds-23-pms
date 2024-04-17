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

        var firstLiDom = $("<li></li>");
        var firstLiDivDom = $("<div></div>");
        firstLiDivDom.text(1);
        var firstLiFirstInputDom = $("<input/>");
        firstLiFirstInputDom.attr('type', 'text');
        firstLiFirstInputDom.attr('placeholder', '답변명');
        var firstLiSecondInputDom = $("<input/>");
        firstLiSecondInputDom.attr('type', 'text');
        firstLiSecondInputDom.attr('placeholder', '연결');

        firstLiDom.append(firstLiDivDom);
        firstLiDom.append(firstLiFirstInputDom);
        firstLiDom.append(firstLiSecondInputDom);

        var secondLiDom = $("<li></li>");
        var secondLiDivDom = $("<div></div>");
        secondLiDivDom.text(2);
        var secondLiFirstInputDom = $("<input/>");
        secondLiFirstInputDom.attr('type', 'text');
        secondLiFirstInputDom.attr('placeholder', '답변명');
        var secondLiSecondInputDom = $("<input/>");
        secondLiSecondInputDom.attr('type', 'text');
        secondLiSecondInputDom.attr('placeholder', '연결');

        secondLiDom.append(secondLiDivDom);
        secondLiDom.append(secondLiFirstInputDom);
        secondLiDom.append(secondLiSecondInputDom);

        ulDom.append(firstLiDom);
        ulDom.append(secondLiDom);

        var addSrvQstButtonDom = $("<button></button>");
        addSrvQstButtonDom.attr('type', 'button');
        addSrvQstButtonDom.text("답변 항목 추가");

        var qstNum = 3;
        $(addSrvQstButtonDom).on("click", function() {
            var nextLiDom = $("<li></li>");
            var nextLiDivDom = $("<div></div>");
            nextLiDivDom.text(qstNum);
            qstNum++;
            var nextLiFirstInputDom = $("<input/>");
            nextLiFirstInputDom.attr('type', 'text');
            nextLiFirstInputDom.attr('placeholder', '답변명');
            nextLiSecondInputDom = $("<input/>");
            nextLiSecondInputDom.attr('type', 'text');
            nextLiSecondInputDom.attr('placeholder', '연결');
            var deleteSrvQstButtonDom = $("<button></button>");
            deleteSrvQstButtonDom.attr("type", "button");
            deleteSrvQstButtonDom.text("제거");

            $(deleteSrvQstButtonDom).on("click", function() {
                $(this).closest(nextLiDom).remove();
            });

            nextLiDom.append(nextLiDivDom);
            nextLiDom.append(nextLiFirstInputDom);
            nextLiDom.append(nextLiSecondInputDom);
            nextLiDom.append(deleteSrvQstButtonDom);
            ulDom.append(nextLiDom);
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

            var firstLiDom = $("<li></li>");
            var firstLiDivDom = $("<div></div>");
            firstLiDivDom.text(1);
            var firstLiFirstInputDom = $("<input/>");
            firstLiFirstInputDom.attr('type', 'text');
            firstLiFirstInputDom.attr('placeholder', '답변명');
            var firstLiSecondInputDom = $("<input/>");
            firstLiSecondInputDom.attr('type', 'text');
            firstLiSecondInputDom.attr('placeholder', '연결');
    
            firstLiDom.append(firstLiDivDom);
            firstLiDom.append(firstLiFirstInputDom);
            firstLiDom.append(firstLiSecondInputDom);
    
            var secondLiDom = $("<li></li>");
            var secondLiDivDom = $("<div></div>");
            secondLiDivDom.text(2);
            var secondLiFirstInputDom = $("<input/>");
            secondLiFirstInputDom.attr('type', 'text');
            secondLiFirstInputDom.attr('placeholder', '답변명');
            var secondLiSecondInputDom = $("<input/>");
            secondLiSecondInputDom.attr('type', 'text');
            secondLiSecondInputDom.attr('placeholder', '연결');
    
            secondLiDom.append(secondLiDivDom);
            secondLiDom.append(secondLiFirstInputDom);
            secondLiDom.append(secondLiSecondInputDom);
    
            ulDom.append(firstLiDom);
            ulDom.append(secondLiDom);
    
            var addSrvQstButtonDom = $("<button></button>");
            addSrvQstButtonDom.attr('type', 'button');
            addSrvQstButtonDom.text("답변 항목 추가");
    
            qstNum = 3;
            $(addSrvQstButtonDom).on("click", function() {
                var nextLiDom = $("<li></li>");
                var nextLiDivDom = $("<div></div>");
                nextLiDivDom.text(qstNum);
                qstNum++;
                var nextLiFirstInputDom = $("<input/>");
                nextLiFirstInputDom.attr('type', 'text');
                nextLiFirstInputDom.attr('placeholder', '답변명');
                nextLiSecondInputDom = $("<input/>");
                nextLiSecondInputDom.attr('type', 'text');
                nextLiSecondInputDom.attr('placeholder', '연결');
                var deleteSrvQstButtonDom = $("<button></button>");
                deleteSrvQstButtonDom.attr("type", "button");
                deleteSrvQstButtonDom.text("제거");
    
                $(deleteSrvQstButtonDom).on("click", function() {
                    $(this).closest(nextLiDom).remove();
                    qstNum--;
                });
    
                nextLiDom.append(nextLiDivDom);
                nextLiDom.append(nextLiFirstInputDom);
                nextLiDom.append(nextLiSecondInputDom);
                nextLiDom.append(deleteSrvQstButtonDom);
                ulDom.append(nextLiDom);
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
