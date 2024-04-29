$().ready(function () {
    $('form').on('keypress', function(e) {
        if (e.which == 13) {
            e.preventDefault();  
            return false;    
        }
    });

    $("#deleteMassiveIssue").on("click", function() {
      var checkedItems = $(".target-issue-id:checked");
      var itemsArray = [];
      checkedItems.each(function (index, data) {
        itemsArray.push($(data).val());
      });
      $.post(
        "/ajax/issue/delete/massive",
        { deleteItems: itemsArray },
        function(response) {
          var result = response.data.result;
          if (result) {
            location.reload();
          }
        }
      )
    });

    $("#checked-all").on("change", function () {
        var targetClass = $(this).data("target-class");
    
        var isChecked = $(this).prop("checked");
    
        $("." + targetClass).prop("checked", isChecked);
    });

    $("#list-size").on("change", function () {
      search(0);
    });

    $("#search-btn").on("click", function () {
      search(0);
    });

    $("#cancel-search-btn").on("click", function () {
        location.href = "/issue";
    });
});