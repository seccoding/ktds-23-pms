$().ready(function () {

    var pwdMessage = "${pwdMessage}";

    if (pwdMessage) {
        confirm( "비밀번호를 변경한지 90일이 지났습니다." + "\n" + "확인을 누르면 수정페이지로 이동합니다");
    }

})