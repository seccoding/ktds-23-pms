$() .ready(function(){
    $("#deleteEmployee").on("click", function () {
    var checkedItems = $(".target-employee-id:checked")

    var itemsArray=[];
    checkedItems.each(function (index, data){
        itemsArray.push($(data).val());
    });

    //서버로 전송
    $.post(
        "/ajax/employee/delete",
        {deleteItems: itemsArray}, 
        function (response) {
            var result = response.data.result;
            if(result) {
                location.reload();
            }
        }
    );
});

    $("#list-size").on("change", function () {
        search(0);
    });
    $("#search-btn").on("click", function () {
        search(0);
    });

});
