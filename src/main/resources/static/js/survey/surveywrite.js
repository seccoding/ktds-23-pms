$().ready(function() {
    var prjId = $(".survey-body").data("prj-id");
    
    $.get("/ajax/survey/write/" + prjId, function(response) {
        var surveys = response.data.surveys;
        var firstQst = surveys[0].srvQst;
        srvQstDom = $("<div></div>");
        srvQstDom.text(firstQst);

        srvPickDom = $("<div></div>");
        srvPickUlDom = $("<ul></ul>");
        srvPickLiDom = $("<li></li>");
        console.log(surveys[0].surveyQuestionPickVO);

        
    });

    $("#next-srv-btn").on("click", function() {
        console.log(prjId);
    });
});