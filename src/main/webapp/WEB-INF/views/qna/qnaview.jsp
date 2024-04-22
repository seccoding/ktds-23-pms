<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Qna 상세 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script
      type="text/javascript"
      src="/js/qna/qnaview.js"
    ></script>
    <link rel="stylesheet"  type="text/css" href="/static/css/qna/qna.css">
  </head>
  <body>
    <h1>Qna 게시글 조회</h1>

    <div class="grid" data-id="${qnaVO.qaId}">
      <label for="qaTtl">제목</label>
      <div>${qnaVO.qaTtl}</div>

      <label for="name">작성자 ID</label>
      <div>${qnaVO.crtrId}</div>

      <label for="name">수정자 ID</label>
      <div>${qnaVO.mdfrId}</div>

      <label for="originFileName">첨부파일</label>
      <div>
        <a href="/qna/file/download/${qnaVO.qaId}">
          ${qnaVO.originFileName}
        </a>
      </div>

      <label for="crtDt">등록일</label>
      <div>${qnaVO.crtDt}</div>

      <label for="mdfDt">수정일</label>
      <div>${qnaVO.mdfDt}</div>

      <label for="knlCntnt">내용</label>
      <div>${qnaVO.qaCntnt}</div>

      <label for="knlCnt">조회수</label>
      <div >${qnaVO.qaCnt}</div>

      <label for="qaRecCnt">추천수</label>
      <div id="qaRecCnt">${qnaVO.qaRecCnt}</div>

      <div class="btn-group">
        <button type="button" class= "recommend-qna">
          추천하기</button>
          
        <button> 
          <a href="/qna/modify/${qnaVO.qaId}">수정</a>
        </button>
        <button>
          <a class="delete-qna" href="javascript:void(0);">삭제</a>
        </button>
      </div>

      <script>
        $(".recommend-qna").on("click",(e)=> {
          // e.preventDefault();
          const response = $.ajax({
            method:"PUT",
            url:"/ajax/qna/recommend/${qnaVO.qaId}",
            success:({data})=> $("#qaRecCnt").html(data.result)
          });
        })
      </script>



      <div class="answer">
        <div class="title">
          ${employeeVO.empId}님, 답변해주세요!
        </div>
        <button class="answer-btn" style="border-color: #60a4f0;
        background-color: #6cacf2;
        color: #fff;" >
          <span>답변하기</span>
        </button>

        <div class="answer-form" style="display: none;"> 
          <!-- 답변 입력란 -->
          <textarea placeholder="답변을 작성해주세요!"></textarea>
          <button class="submit-answer-btn" style="border-color: #60a4f0;
          background-color: #6cacf2;
          color: #fff;">답변 게시</button>
      </div>
        
      </div>
  </body>
</html>
