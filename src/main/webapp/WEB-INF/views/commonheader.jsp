<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<link rel="stylesheet" href="/css/common.css"/>
<script type="text/javascript" src="/js/lib/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/modal.js"></script>
<script>
    $().ready(function () {

        var pwdMessage = "${pwdMessage}";

        if (pwdMessage) {
            confirm("지금변경할래?" + "\n" + "확인을 누르면 수정페이지로 이동합니다");
        }

    })
</script>