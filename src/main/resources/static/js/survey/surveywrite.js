$().ready(function() {
    $("#btn-add-srv-qst").on("click", function() {
        var srvQstDom = $("<div></div>");
        srvQstDom.addClass("survey-question");

        var srvQstTopDom = $("<div></div>");
        srvQstTopDom.addClass("survey-question-top");

        var seqDom = $("<div></div>");
        seqDom.text(1);
        seqDom.append(srvQstTopDom);

        var srvQstInputDom = $("<input/>")
        srvQstInputDom.attr('type', 'text');
        srvQstInputDom.attr('placeholder', '질문 입력');
        srvQstInputDom.append(srvQstTopDom);

        srvQstTopDom.append(srvQstDom);
        srvQstDom.append(".survey-body");
    });
});
