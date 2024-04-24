<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>지식관리 상세 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script
      type="text/javascript"
      src="/js/knowledge/knowledgeview.js"
    ></script>
  </head>
  <body>
    <h1>지식관리 게시글 조회</h1>

    <div class="grid" data-id="${knowledgeVO.knlId}">
      <label for="knlTtl">제목</label>
      <div>${knowledgeVO.knlTtl}</div>

      <label for="name">작성자 ID</label>
      <div>${knowledgeVO.crtrId}</div>

      <label for="name">수정자 ID</label>
      <div>${knowledgeVO.mdfrId}</div>

      <label for="originFileName">첨부파일</label>
      <div>
        <a href="/knowledge/file/download/${knowledgeVO.knlId}">
          ${knowledgeVO.originFileName}
        </a>
      </div>

      <label for="crtDt">등록일</label>
      <div>${knowledgeVO.crtDt}</div>

      <label for="mdfDt">수정일</label>
      <div>${knowledgeVO.mdfDt}</div>

      <label for="knlCntnt">내용</label>
      <div>${knowledgeVO.knlCntnt}</div>

      <label for="knlCnt">조회수</label>
      <div >${knowledgeVO.knlCnt}</div>

      <label for="knlRecCnt">추천수</label>
      <div id="knlRecCnt">${knowledgeVO.knlRecCnt}</div>

      <div class="btn-group">
        <button type="button" class= "recommend-knowledge" value="${knowledgeVO.knlId}">
          추천하기</button>
          
        <button> 
          <a href="/knowledge/modify/${knowledgeVO.knlId}">수정</a>
        </button>
        <button>
          <a class="delete-knowledge" href="javascript:void(0);">삭제</a>
        </button>
      </div>

      <script>
        // $(".recommend-knowledge").on("click",(e)=> {
        //   // e.preventDefault();
        //   const response = $.ajax({
        //     method:"PUT",
        //     url:"/ajax/knowledge/recommend/${knowledgeVO.knlId}",
        //     success:({data})=> $("#knlRecCnt").html(data.result)
        //   });
        // })

        $().ready(function () {
          knlId = $(".recommend-knowledge").val();
          
          $(".recommend-knowledge").on("click", function() {
            $.post("/knowledge/recommend/" + knlId, function(response) {
              console.log(response.data.result);
              if(response.data.resultStatus) {
                // 여기서 dom의 추천수를 올려서 보여주는 로직
              } else {
                return;
              }
            })
          })
        })
      </script>
  </body>
</html>
