$().ready(function() {

    // console.log($(".hidden-data").text());

    if($(".hidden-data").text().length > 0) {

        loadModal({
            content: $(".hidden-data").text(),
            fnPositiveBtnHandler: function () {
                alertDialog[0].close();
            }, showNegativeBtn: false,
        });
    }
    // // 기안서 작성 -> 대여비품 목록 확인
    // var alertDialog = $(".alert-dialog");
    // if(alertDialog && alertDialog.length > 0) {
    //     alertDialog[0].showModal();
    // }
    // $(".alert-button").on("click", function() {
    //     alertDialog[0].close();
    // });

    // 전체목록
    $("#btn-all-appr").on("click", function() {
        location.href = "/approval/home";
    });

    // 기안서 작성
    $("#btn-appr-write").on("click", function() {

        loadModal({
            content: "기안서를 작성하시겠습니까?",
            fnPositiveBtnHandler: function () {
            location.href = "/approval/write";

            },fnNegativeBtnHandler: function () {
                alertModal[0].close();
            },
        });

        // var alertModal = $(".modal-confirm-window");
        // var modalButton = $(".confirm-confirm-button");
        // var modalButton1 = $(".cancel-confirm-button");
        // var modalText = $(".modal-confirm-text");
        // modalText.text("기안서를 작성하시겠습니까?");
        // modalButton.text("확인");
        // modalButton1.text("취소");
        // alertModal[0].showModal();

        // $(modalButton).on("click", function() {
        //     location.href = "/approval/write";
        // });

        // $(modalButton1).on("click", function() {
        //     alertModal[0].close();
        // });
        
    });
});