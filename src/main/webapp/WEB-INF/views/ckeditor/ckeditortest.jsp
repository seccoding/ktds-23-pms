<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ckeditor Write Exam</title>
    <jsp:include page="../commonheader.jsp"/>
    <jsp:include page="../ckeditor.jsp"/>
    <script type="text/javascript">
        window.onload = function() {
            var editors = loadEditor(".editor", "내용을 입력하세요.");

            $("button").on("click", function(event) {
                event.preventDefault();

                var content = editors.getData(0);
                console.log(content);

                var content = editors.getData(1);
                console.log(content);

                editors.each(function(key, editor) {
                    console.log(key);
                    editor.getData();
                })

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
        <div class="editor"></div><br>
        <input type="hidden" name="content" id="content" />

        <button type="button">등록</button>
    </form>
</div>
</body>
</html>
