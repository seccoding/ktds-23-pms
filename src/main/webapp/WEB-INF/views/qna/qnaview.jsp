<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Qna 상세 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/qna/qnaview.js"></script>
    <style>
      .btn-wrapper {
        text-align: right;
      }
    </style>
  </head>
  <body>
    <div class="grid" data-id="${qnaVO.qaId}" data-crtr-id="${qnaVO.crtrId}">
      <span style="display: none" hidden id="login-email"
        >${sessionScope._LOGIN_USER_.empId}</span
      >
      <label for="qaTtl">제목</label>
      <div>${qnaVO.qaTtl}</div>

      <label for="name">작성자 ID</label>
      <div>${qnaVO.crtrId}</div>

      <!-- <label for="name">수정자 ID</label>
      <div>${qnaVO.mdfrId}</div> -->

      <label for="originFileName">첨부파일</label>
      <div>
        <a href="/qna/file/download/${qnaVO.qaId}"> ${qnaVO.originFileName} </a>
      </div>

      <label for="crtDt">등록일</label>
      <div>${qnaVO.crtDt}</div>

      <label for="mdfDt">수정일</label>
      <div>${qnaVO.mdfDt}</div>

      <label for="knlCntnt">내용</label>
      <div>${qnaVO.qaCntnt}</div>

      <label for="knlCnt">조회수</label>
      <div>${qnaVO.qaCnt}</div>

      <label for="qaRecCnt">추천수</label>
      <div id="qaRecCnt">${qnaVO.qaRecCnt}</div>

      <div class="btn-wrapper">
        <button type="button" class="recommend-qna" value="${qnaVO.qaId}">
          추천하기
        </button>
        <button>
          <a href="/qna">목록</a>
        </button>
        <c:if
          test="${sessionScope._LOGIN_USER_.empId eq qnaVO.crtrId || sessionScope._LOGIN_USER_.admnCode eq '301'}"
        >
          <button>
            <a href="/qna/modify/${qnaVO.qaId}">수정</a>
          </button>
          <button>
            <a class="delete-qna" href="javascript:void(0);">삭제</a>
          </button>
        </c:if>
      </div>
    </div>
    <div class="replies">
      <div class="reply-items"></div>
      <div class="write-reply">
        <textarea
          id="txt-reply"
          name="rplCntnt"
          data-issue-id="${qnaVO.qaId}"
        ></textarea>
        <button id="btn-save-reply" data-mode="">등록</button>
        <button id="btn-cancel-reply">취소</button>
      </div>
    </div>
  </body>
</html>
