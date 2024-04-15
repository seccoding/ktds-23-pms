$().ready(function () {
    // 엔터키 방지
    $('form').on('keypress', function (e) {
        if (e.which == 13) {
            e.preventDefault();  // 엔터 키 이벤트를 방지합니다.
            return false;        // 브라우저의 기본 동작을 막습니다.
        }
    });

    $("#btn-create").on("click", function () {
        var reqYn = $("#requirement-check").is(":checked") ? "Y" : "N";
        var outYn = $("#output-check").is(":checked") ? "Y" : "N";
        var isYn = $("#issue-check").is(":checked") ? "Y" : "N";
        var knlYn = $("#knowledge-check").is(":checked") ? "Y" : "N";
        var qaYn = $("#qna-check").is(":checked") ? "Y" : "N";

        $.post("/ajax/project/write",
            {
                prjName: $("#project-name").val(),
                clntInfo: $("#client-info").val(),
                reqYn: reqYn,
                outYn: outYn,
                isYn: isYn,
                knlYn: knlYn,
                qaYn: qaYn,
                deptId: $("#dept-list").val(),
                pmId: $("#pm-selector").val(),
                strtDt: $("#start-date").val(),
                endDt: $("#end-date").val()
            }
            , function (response) {
                if (response.data.next) {
                    location.href = response.data.next;
                }

                handleValidationErrors(response, function (error) {
                    for (var key in error) {
                        var errorMessages = error[key];
                        for (var i in errorMessages) {
                            var errorMessage = errorMessages[i]
                            if (errorMessage) {
                                alertModal("입력값을 확인하세요!", errorMessage);
                                return;
                            }
                        }
                    }
                })
            });
    });

    var dialog = $(".alert-dialog");
    if (dialog.length > 0) {
        dialog[0].showModal();

        dialog.on("click", function () {
            this.close();
        });
    }

    $('#requirement-check').on("change", function () {
        // 체크 상태에 따라 하위 체크박스 활성화/비활성화
        var isChecked = $(this).is(':checked');
        $('#issue-check').prop('disabled', !isChecked);
        $('#knowledge-check').prop('disabled', !isChecked);
        $('#qna-check').prop('disabled', !isChecked);
        if (!isChecked) {
            // 상위 체크박스 해제 시 하위 체크박스들도 해제
            $('#issue-check').prop('checked', false);
            $('#knowledge-check').prop('checked', false);
            $('#qna-check').prop('checked', false);
        }
    });

    // data-list Employee 기능 추가시 주석 해제
    // $('#pm-selector').on('input', function() {
    //     var value = $(this).val();
    //     var empId = $('#employee-list').find('option[value="' + value + '"]').data('emp-id');
    //     $('#employee-id').val(empId);
    // });
});