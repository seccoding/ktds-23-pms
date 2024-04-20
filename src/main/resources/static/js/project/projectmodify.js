$().ready(function () {
    // 엔터키 방지
    $('form').on('keypress', function (e) {
        if (e.which == 13) {
            e.preventDefault();  // 엔터 키 이벤트를 방지합니다.
            return false;        // 브라우저의 기본 동작을 막습니다.
        }
    });

    // 요구사항 체크박스
    // 페이지 로드 시 change 이벤트를 수동으로 트리거하여 초기 상태를 적용
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

    $('#requirement-check').trigger('change');

    // 부서 값 히든 인풋 적용
    $("#dept-list").on("change", function () {
        var selectedDeptValue = $(this).val();
        $("#hidden-dept-id").val(selectedDeptValue);
    })

    // 담당자 부분
    $('#employee-list').hide();

    // 인풋 필드에 포커스가 생기면 드롭다운을 보여줌
    $('#pm-search').on('focus', function () {
        $('#employee-list').show();
    });

    // 인풋 필드에 텍스트를 입력하면 필터링
    $('#pm-search').on('input', function () {
        var value = $(this).val().toLowerCase();
        $('#employee-list .option-custom').filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    // 옵션 클릭 시 인풋 필드에 텍스트를 채우고, 숨겨진 필드에 empId를 저장
    $('#employee-list').on('click', '.option-custom', function () {
        var text = $(this).text();
        var empId = $(this).data('emp-id');
        $('#pm-search').val(text);
        $('#hidden-pm-id').val(empId);
        $('#employee-list').hide();
    });

    // 인풋 필드 외부를 클릭하면 드롭다운을 숨김
    $(document).on('click', function (e) {
        if (!$(e.target).closest('#pm-search').length) {
            $('#employee-list').hide();
        }
    });

    // 수정
    // var params = new URLSearchParams(window.location.search);
    // var projectId = params.get("prjId");

    var projectId = $("#btn-modify").val();

    $("#btn-modify").on("click", function () {
        var reqYn = $("#requirement-check").is(":checked") ? "Y" : "N";
        var outYn = $("#output-check").is(":checked") ? "Y" : "N";
        var isYn = $("#issue-check").is(":checked") ? "Y" : "N";
        var knlYn = $("#knowledge-check").is(":checked") ? "Y" : "N";
        var qaYn = $("#qna-check").is(":checked") ? "Y" : "N";

        $.post("/ajax/project/modify/" + projectId,
            {
                prjId: projectId,
                prjName: $("#project-name").val(),
                clntInfo: $("#client-info").val(),
                prjSts: $("#status").val(),
                reqYn: reqYn,
                outYn: outYn,
                isYn: isYn,
                knlYn: knlYn,
                qaYn: qaYn,
                deptId: $("#hidden-dept-id").val(),
                pmId: $("#hidden-pm-id").val(),
                strtDt: $("#start-date").val(),
                endDt: $("#end-date").val()
            }
            , function (response) {
                if (response.data.next) {
                    location.href = response.data.next;
                }

                $(".error").remove();

                var errors = response.data.errors;

                if (errors) {
                    for (var key in errors) {
                        var errorDiv = $("<div></div>");
                        errorDiv.addClass("error");

                        var values = errors[key];

                        for (var i in values) {
                            var errorValue = values[i];
                            var error = $("<div></div>");
                            error.text(errorValue);
                            errorDiv.append(error);
                            $("input[name=" + key + "]").after(errorDiv);
                        }
                    }
                }
            });
    });

    // 비활성화 드롭다운
    $('#pm-search').prop('disabled', true);

    // 부서 선택 시, 드롭다운 활성화
    $('#dept-list').change(function() {
        var deptId = $(this).val();

        if (deptId) {
            $.get("/ajax/department-teammate/" + deptId, function (response) {
                $("#employee-list").empty();

                response.data.teammateList.forEach(function (emp) {
                    var empElement = $("<div></div>").addClass('option-custom')
                        .attr('data-emp-id', emp.empId)
                        .text(emp.empName + '-' + emp.departmentVO.deptName);

                    $("#employee-list").append(empElement);
                });
            });

            $('#pm-search').prop('disabled', false).val(''); // 담당자 검색 필드 활성화 및 초기화
            $('#hidden-dept-id').val(deptId);
        } else {
            $('#pm-search').prop('disabled', true);
            $('#hidden-dept-id').val('');
            $('#employee-list').empty();
        }
    });
});