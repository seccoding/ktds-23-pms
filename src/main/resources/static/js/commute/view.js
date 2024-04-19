$().ready(function(){
    $.post(
        "/ajax/commute/view",
        {
        },
        function(response){
            var commuteData = response.data.commuteData;

        }
    )
});