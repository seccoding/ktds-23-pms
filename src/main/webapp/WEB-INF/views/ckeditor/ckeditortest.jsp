<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ckeditor Write Exam</title>
    <jsp:include page="../commonheader.jsp"/>
    <jsp:include page="../ckeditor.jsp"/>
    <script type="text/javascript">
        window.onload = function () {
            var editors = loadEditor(".editor", "내용을 입력하세요.");

            var content = "";

            $("button").on("click", function (event) {
                event.preventDefault();

                content = editors.getData("dataTag");

                $("#content").val(content);

                $("#writeForm").submit();
            });
        }
    </script>
</head>
<body>
<div id="container">
    <form id="writeForm" action="/ckeditor/save" method="post">
        <input name="title" type="text" placeholder="제목"/><br>

        <div class="hereCkEditor5">
            <%--    여기가 editor 생성부  --%>
            <div class="editor" data-tag="dataTag"></div>
            <%--    여기가 실제 데이터가 담기는 곳    --%>
            <input type="hidden" name="content" id="content"/>

        </div>
        <button type="button">등록</button>

    </form>
</div>
</body>
</html>
