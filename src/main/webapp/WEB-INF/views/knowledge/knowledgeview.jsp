<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>지식관리 상세 페이지</title>
    <script
      type="text/javascript"
      src="/js/knowledge/knowledgedetail.js"
    ></script>
    <style type="text/css">
      div.grid {
        display: grid;
        grid-template-columns: 80px 1fr;
        grid-template-rows: repeat(6, 30px) auto auto 1fr;
        row-gap: 10px;
      }
    </style>
  </head>
  <body>
    <h1>지식관리 게시글 조회</h1>

    <div class="grid" data-id="${knowledgedetail.knlTtl}">
      <label for="knlTtl">제목</label>
      <div>${knowledgeVO.knlTtl}</div>

      <label for="name">작성자 ID</label>
      <div>${knowledgeVO.crtrId}</div>

      <label for="name">수정자 ID</label>
      <div>${knowledgeVO.mdfrId}</div>

      <label for="fileName">첨부파일</label>
      <div>
        <a href="/knowledge/file/download/${knowledgeVO.knlTtl}">
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
      <div>${knowledgeVO.knlCnt}</div>

      <label for="knlRecCnt">추천수</label>
      <div>${knowledgeVO.knlRecCnt}</div>

      <div>
        <a href="/knowledge/modify/${knowledgeVO.knlId}">수정</a>
        <a  class="delete-knowledge" href="javaScript:void(0);">삭제</a>
      </div>


    

  </body>
</html>
