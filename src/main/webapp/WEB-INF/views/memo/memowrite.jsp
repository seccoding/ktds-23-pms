<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지 쓰기</title>
<jsp:include page="../commonheader.jsp" />
<jsp:include page="../ckeditor.jsp"/>
    <script type="text/javascript">
        window.onload = function () {
            var editors = loadEditor(".editor", "내용을 입력하세요.");

            var content = "";

            $("button").on("click", function (event) {
                event.preventDefault();

                content = editors.getData("dataTag");
				console.log(content);
				
				
				
                $("#memoCntnt").val(content);

                $("#writeForm").submit();
            });
        }
    </script>
</head>
<body>

	<h1>쪽지 쓰기</h1>
    <form id="writeForm" action="/memo/write" method="post" enctype="multipart/form-data">
      <div class="grid">
        <label for="rcvId">받는사람</label>
        <input
          id="rcvId"
          type="text"
          name="rcvId"
          value="${memoVO.rcvId}"
          placeholder="여러 명은 쉼표(,)로 구분해주세요."
        />
		<button type="button" onclick="alert('주소록 구현필요')">주소록</button>
        
        <div class="editor" data-tag="dataTag"></div><br>
        <input type="hidden" name="memoCntnt" id="memoCntnt" />

        
          <button type="button">보내기</button>
          
      </div>
    </form>
    

</body>
</html>