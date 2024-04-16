$().ready(function() {
    $("#btn-add-srv-qst").on("click", function() {
        var srvQstDom = $("<div></div>");
        srvQstDom.addClass("survey-question");

        var srvQstTopDom = $("<div></div>");
        srvQstTopDom.addClass("survey-question-top");

        var seqDom = $("<div></div>");
        seqDom.text(1);
        srvQstTopDom.append(seqDom);

        var srvQstInputDom = $("<input/>")
        srvQstInputDom.attr('type', 'text');
        srvQstInputDom.attr('placeholder', '질문 입력');
        srvQstTopDom.append(srvQstInputDom);

        var srvQstMiddleDom = $("<div></div>");
        srvQstMiddleDom.addClass("survey-question-middle");

        var spanDom = $("<span></span>");
        spanDom.text("문항 형태");
        var selectiveTypeButtonDom = $("<button></button>");
        selectiveTypeButtonDom.text("선택형");
        var descriptiveTypeButtonDom = $("<button></button>");
        descriptiveTypeButtonDom.text("서술형");

        srvQstMiddleDom.append(spanDom);
        srvQstMiddleDom.append(selectiveTypeButtonDom);
        srvQstMiddleDom.append(descriptiveTypeButtonDom);

        var srvQstBottomDom = $("<div></div>");
        srvQstBottomDom.addClass("survey-question-bottom");

        var ulDom = $("<ul></ul>");

        var firstLiDom = $("<li></li>");
        var firstLiDivDom = $("<div></div>");
        firstLiDivDom.text("답변 1");
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
        secondLiDivDom.text("답변 2");
        var secondLiFirstInputDom = $("<input/>");
        secondLiFirstInputDom.attr('type', 'text');
        secondLiFirstInputDom.attr('placeholder', '답변명');
        var secondLiSecondInputDom = $("<input/>");
        secondLiSecondInputDom.attr('type', 'text');
        secondLiSecondInputDom.attr('placeholder', '연결');

        secondLiDom.append(secondLiDivDom);
        secondLiDom.append(secondLiFirstInputDom);
        secondLiDom.append(secondLiSecondInputDom);
        
        var thirdLiDom = $("<li></li>");
        var thirdLiDivDom = $("<div></div>");
        thirdLiDivDom.text("답변 3");
        var thirdLiFirstInputDom = $("<input/>");
        thirdLiFirstInputDom.attr('type', 'text');
        thirdLiFirstInputDom.attr('placeholder', '답변명');
        var thirdLiSecondInputDom = $("<input/>");
        thirdLiSecondInputDom.attr('type', 'text');
        thirdLiSecondInputDom.attr('placeholder', '연결');

        thirdLiDom.append(thirdLiDivDom);
        thirdLiDom.append(thirdLiFirstInputDom);
        thirdLiDom.append(thirdLiSecondInputDom);

        ulDom.append(firstLiDom);
        ulDom.append(secondLiDom);
        ulDom.append(thirdLiDom);

        var addSrvQstButtonDom = $("<button></button>");
        addSrvQstButtonDom.text("답변 항목 추가");

        $(addSrvQstButtonDom).on("click", function() {
            var nextLiDom = $("<li></li>");
            var nextLiDivDom = $("<div></div>");
            nextLiDivDom.text("답변 4");
            var nextLiFirstInputDom = $("<input/>");
            nextLiFirstInputDom.attr('type', 'text');
            nextLiFirstInputDom.attr('placeholder', '답변명');
            var nextLiSecondInputDom = $("<input/>");
            nextLiSecondInputDom.attr('type', 'text');
            nextLiSecondInputDom.attr('placeholder', '연결');
            var deleteSrvQstButtonDom = $("<button></button>");
            deleteSrvQstButtonDom.text("제거");

            nextLiDom.append(nextLiDivDom);
            nextLiDom.append(nextLiFirstInputDom);
            nextLiDom.append(nextLiSecondInputDom);
            nextLiDom.append(deleteSrvQstButtonDom);
            ulDom.append(nextLiDom);
        });

        srvQstBottomDom.append(ulDom);
        srvQstBottomDom.append(addSrvQstButtonDom);

        var lastDivDom = $("<div></div>");

        var insertSrvQstButtonDom = $("<button></button>");
        insertSrvQstButtonDom.text("문항 완성");
        var deleteSrvQstButtonDom = $("<button></button>");
        deleteSrvQstButtonDom.text("문항 삭제");

        lastDivDom.append(insertSrvQstButtonDom);
        lastDivDom.append(deleteSrvQstButtonDom);

        srvQstDom.append(srvQstTopDom);
        srvQstDom.append(srvQstMiddleDom);
        srvQstDom.append(srvQstBottomDom);
        srvQstDom.append(lastDivDom);
        $(".survey-body").append(srvQstDom);
    });
});
