$().ready(function () {
    // 엔터키 방지
    $('form').on('keypress', function(e) {
        if (e.which == 13) {
            e.preventDefault();  // 엔터 키 이벤트를 방지합니다.
            return false;        // 브라우저의 기본 동작을 막습니다.
        }
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


    // 날짜 체크, 프로젝트 종료일이 프로젝트 시작일보다 빠르면 alert 를 발생하고,
    // 프로젝트 종료일을 초기화한다.
    $('#end-date').change(function () {
        var startDate = $('#start-date').val();
        var endDate = $('#end-date').val();

        // 시작일과 종료일을 비교합니다.
        if (startDate && endDate && endDate <= startDate) {
            // 종료일이 시작일보다 이전이거나 같다면 경고를 표시하고 초기화합니다.
            alert('종료일은 시작일보다 이후여야 합니다. 날짜를 다시 설정해주세요.');
            $('#end-date').val(''); // 종료일 초기화
        }
    });

    // 날짜 최소, 최대 값 설정
    var today = new Date();
    var startOfYear = new Date(today.getFullYear(), 0, 1); // 올해의 1월 1일
    var endOfFiveYears = new Date(today.getFullYear() + 5, 11, 31); // 5년 후의 12월 31일

    // 날짜를 YYYY-MM-DD 형식으로 변환
    var minStartDate = startOfYear.toISOString().slice(0, 10);
    var maxEndDate = endOfFiveYears.toISOString().slice(0, 10);

    // 시작일(input#start-date)에 min 속성 설정
    $('#start-date').attr('min', minStartDate);
    $('#start-date').attr('max', maxEndDate); // 시작일의 max를 종료일의 max와 동일하게 설정할 수 있습니다.

    // 종료일(input#end-date)에 min과 max 속성 설정
    $('#end-date').attr('min', minStartDate); // 종료일의 min을 시작일의 min과 동일하게 설정할 수 있습니다.
    $('#end-date').attr('max', maxEndDate);

    // data-list Employee 기능 추가시 주석 해제
    // $('#pm-selector').on('input', function() {
    //     var value = $(this).val();
    //     var empId = $('#employee-list').find('option[value="' + value + '"]').data('emp-id');
    //     $('#employee-id').val(empId);
    // });
});