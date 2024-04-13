$().ready(function () {
    // input을 datepicker로 선언
    $("#start-datepicker").datepicker({
        dateFormat: 'yy-mm-dd',
        showOtherMonths: true,
        showMonthAfterYear: true,
        changeYear: true,
        changeMonth: true,
        showOn: "both",
        buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
        buttonImageOnly: true,
        buttonText: "선택",
        yearSuffix: "년",
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        dayNamesMin: ['일','월','화','수','목','금','토'],
        yearRange: "c-0:c+3", // 현재 년도에서 시작하여 3년 후까지
        onClose: function(selectedDate) {
            $("#end-datepicker").datepicker("option", "minDate", selectedDate);
        }
    })

    $("#end-datepicker").datepicker({
        dateFormat: 'yy-mm-dd',
        showOtherMonths: true,
        showMonthAfterYear: true,
        changeYear: true,
        changeMonth: true,
        showOn: "both",
        buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
        buttonImageOnly: true,
        buttonText: "선택",
        yearSuffix: "년",
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        dayNamesMin: ['일','월','화','수','목','금','토'],
        yearRange: "c-0:c+3", // 현재 년도에서 시작하여 3년 후까지
        onClose: function(selectedDate) {
            $("#start-datepicker").datepicker("option", "maxDate", selectedDate);
        }
    });
});