$().ready(function() {
    var prjId = $(".survey-body").data("prj-id");
    console.log(prjId);

    $.get("/ajax/survey/write/" + prjId, function(response) {
        var surveys = response.data.surveys;
        console.log(surveys);
    });
});