$().ready(function() {
    function redirectToURL(prjId) {
        var srvSts = "${survey.srvSts}";
    
        if (srvSts === "N" || srvSts === "W") {
            location.href = '/survey/write/' + prjId;
        } 
        else {
            location.href = '/project/search';
        }
    }
});