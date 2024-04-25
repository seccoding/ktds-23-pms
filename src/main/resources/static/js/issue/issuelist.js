$().ready(function () {
    $('form').on('keypress', function(e) {
        if (e.which == 13) {
            e.preventDefault();  
            return false;    
        }
    });

    $("#deleteMassiveIssue").on("click", function () {


      var alertModal = $(".modal-confirm-window");
        var modalButton = $(".confirm-confirm-button");
        var modalButton1 = $(".cancel-confirm-button");
        var modalText = $(".modal-confirm-text");
        modalText.text("이 이슈를 정말 삭제하시겠습니까?");
        modalButton.text("확인");
        modalButton1.text("취소");
        alertModal[0].showModal();
        $(".confirm-confirm-button").on("click", function() {
          confirm = true;
          if (confirm) {
            

            var checkedItems = $(".target-issue-id:checked");
    
        var itemsArray = [];
        checkedItems.each(function (index, data) {
          itemsArray.push($(data).val());
        });
    
        $.post(
          "/ajax/issue/delete/massive",
          { deleteItems: itemsArray },
          function (response) {
            var result = response.data.result;
            if (result) {
              location.reload();
            }
          }
        );



          }
        })
        $(".cancel-confirm-button").on("click", function() {
          location.reload();
        })






        
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