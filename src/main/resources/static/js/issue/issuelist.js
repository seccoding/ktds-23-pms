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

      if (itemsArray.length === 0) {
        loadModal({
          content: "삭제할 이슈를 선택하세요.",
          showNegativeBtn: false
        });
      } else {
        loadModal({
          content: "이슈를 일괄 삭제 하시겠습니까?",
          fnPositiveBtnHandler: function() {
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
          }
        });
      }
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