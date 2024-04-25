$().ready(function () {

    var empId = $("#empId").text();
    var deptId = $("#deptId").text();
    var pwdMessage = $("#pwdCheck").data("pwd");

    if (pwdMessage) {

        var passwordH2 = $("<h2>비밀번호 변경 공지</h2>");
        var passwordContent = $("<span>비밀번호를 변경한지 90일이 지났습니다.</span></br><span>비밀번호를 변경해주세요.</span></br>");
        var passwordAlertModal = $(".modal-confirm-window");
        var passwordAlertModalButton = $(".confirm-confirm-button");
        var passwordAlertModalCancleButton = $(".cancel-confirm-button");
        var passwordAlertModalText = $(".modal-confirm-text");
        var aTegjavascript = $("<a href='javascript:void(0)'></a>")

        passwordAlertModalText.prepend(passwordContent);
        passwordAlertModalText.prepend(passwordH2);
        passwordAlertModalButton.text("변경하기");
        passwordAlertModalCancleButton.text("30일 후 변경");
        passwordAlertModalCancleButton.append(aTegjavascript);
        passwordAlertModal[0].showModal();

        $(".confirm-confirm-button").on("click", function () {
            $.get(
                "/employee/modify?empId="+empId,
                {
                    deptId: $("#deptId").val()
                }, function (response) {
                    $("#dept").val(response.data.employeeDept).prop("selected");
                },
                location.href = "/employee/modify/"+empId

        )
        });

        $(".cancel-confirm-button").on("click", function () {
            $.post(
                "/ajax/login/pwdCnDt",
                {
                    empId: empId,
                    next: "redirect:/",
                },

            );
        })

        $(".cancel-confirm-button").on("click", function () {
            $(".modal-confirm-window").remove();
        })
    }

})