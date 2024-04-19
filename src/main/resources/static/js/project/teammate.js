$().ready(function () {
    $("button[name='deleteTeammate']").on("click", function() {
        // Save the teammate ID in data attribute
        $("#delete-alert-modal").data('teammateId', $(this).val());
        $(".modal-text").text("팀원을 삭제하시겠습니까?");
        $("#delete-alert-modal").show();
    });

    $("#modal-delete-button").on("click" ,function() {
        var teammateId = $("#delete-alert-modal").data('teammateId');
        $.get("/ajax/teammate/delete/" + teammateId, function(response) {
            var oneDeleteResult = response.data.result;
            console.log(oneDeleteResult);
            if (oneDeleteResult) {
                location.reload();
            }
        });

        $("#delete-alert-modal").hide();

    });

    $("button[name='new-teammate']").on("click", function () {
        var deptId = $("#add-alert-modal").data('dept-id');

        $.get("/ajax/department-teammate/" + deptId, function(response) {
            var teammateList = response.data.teammateList;

            $("#modal-add-team-list").empty();

            teammateList.forEach(function(teammate) {
                $select.append($('<option></option>').val(teammate.id).text(teammate.name));
            });
        })

        $("#add-alert-modal").show();
    })

    $("#modal-add-button").on("click" ,function() {
        $.post()
    });

    $("#modal-cancel-button").click(function() {
        $("#delete-alert-modal").hide();
        $("#add-alert-modal").hide();
    });

    // 전체체크 로직
    $("#checked-all").on("change", function () {
        // 영향을 받을 다른 체크박스를 조회한다.
        var targetClass = $(this).data("target-class");

        // checked-all의 체크 상태를 가져온다.
        // 체크가 되어있다면 true, 아니라면 false
        var isChecked = $(this).prop("checked");

        $("." + targetClass).prop("checked", isChecked);
    });

    // 체크된 아이템들을 삭제하기 위한 모달을 띄우는 로직
    $("#delete-massive-teammate").on("click", function () {
        var checkedItems = $(".target-teammate-id:checked");

        // 선택된 체크박스가 없다면 early return
        if(checkedItems.length === 0) {
            alert("삭제할 팀원을 선택하세요.");
            return;
        }

        // 선택된 체크박스의 ID들을 배열로 추출
        var itemsArray = checkedItems.map(function () {
            return $(this).val();
        }).get();

        // 모달에 선택된 팀원들의 ID들을 저장
        $("#delete-alert-modal").data('teammateIds', itemsArray);
        $(".modal-text").text("선택한 팀원들을 삭제하시겠습니까?");
        $("#delete-alert-modal").show();
    });

    // 모달 내 삭제 확인 버튼의 로직
    $("#modal-delete-button").click(function() {
        // 모달에서 저장된 팀원들의 ID들을 가져옴
        var teammateIds = $("#delete-alert-modal").data('teammateIds');

        // 서버로 삭제 요청을 보내는 로직
        $.post("/ajax/teammate/delete/massive", { deleteItems: teammateIds }, function(response) {
            var deleteMassiveResult = response.data.result;
            if (deleteMassiveResult) {
                location.reload(); // 페이지 새로고침
            }
        });
        $("#delete-alert-modal").hide();
    });

    // 모달 내 취소 버튼의 로직
    $("#modal-cancel-button").click(function() {
        $("#delete-alert-modal").hide();
    });

    // // 체크 된 팀원 삭제 로직
    // $("#delete-massive-teammate").on("click", function () {
    //     // 선택된 체크박스만 가져온다.
    //     var checkedItems = $(".target-teammate-id:checked");
    //
    //     // 선택된 체크박스만 반복하며 서버로 보낼 파라미터를 생성한다.
    //     var itemsArray = [];
    //     checkedItems.each(function (index, data) {
    //         itemsArray.push($(data).val());
    //     });
    //
    //     // 서버로 전송한다 (ajax)
    //     $.post(
    //         "/ajax/teammate/delete/massive",
    //         { deleteItems: itemsArray },
    //         function (response) {
    //             var deleteMassiveResult = response.data.result;
    //             if (deleteMassiveResult) {
    //                 // 삭제가 완료되면 현재페이지를 새로고침한다.
    //                 location.reload();
    //             }
    //         }
    //     );
    // });
})