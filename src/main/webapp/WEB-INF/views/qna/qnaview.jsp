<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Qna 상세 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/qna/qnaview.js"></script>
    <link rel="stylesheet" href="/css/reply/reply.css"/>
    <style>
      .grid {
        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: 130px 130px 400px;
      }

      .info-border {
        padding: 0;
        margin: 0;
        border: 1px solid #ccc;
        border-radius: 4px;
        margin-bottom: 30px;
        margin-left: 5px;
        margin-right: 16px;
      }
      .main-info > .grid {
        display: grid;
        grid-template-columns: 100px 1fr 100px 1fr;
        grid-template-rows: 40px 40px 40px;
      }
      .main-info > .grid > .sub-item:first-child {
        grid-column: 1/-1;
      }

      .sub-info > .grid {
        display: grid;
        grid-template-columns: 100px 1fr 100px 1fr;
        grid-template-rows: 40px 40px 40px;
      }

      .sub-item {
        align-self: center;
        margin-left: 5px;
      }
      .content-border {
        margin: 10px;
      }

      .btn-wrapper {
        text-align: right;
      }
    </style>
  </head>
  <body>
    <div class="grid" data-id="${qnaVO.qaId}" data-crtr-id="${qnaVO.crtrId}">
      <span style="display: none" hidden id="login-email">${sessionScope._LOGIN_USER_.empId}</span>
      <div class="main-info">
        <div class="grid info-border">
          <!-- <label for="qaTtl">제목</label> -->
          <div class="sub-item">${qnaVO.qaTtl}</div>

          <!-- 프로젝트 이름 요구사항 이름 -->
          <label class="sub-item" for="name">프로젝트</label>
          <div class="sub-item">${qnaVO.projectVO.prjName}</div>

          <label class="sub-item" for="name">요구사항</label>
          <div class="sub-item">${qnaVO.requirementVO.rqmTtl}</div>

          <label class="sub-item" for="name">작성자</label>
          <div class="sub-item">${qnaVO.crtrId}</div>

          <label class="sub-item" for="crtDt">작성일</label>
          <div class="sub-item">${qnaVO.crtDt}</div>

          <!-- <label for="name">수정자</label>
          <div>${qnaVO.mdfrId}</div> -->

          <!-- <label for="mdfDt">수정일</label>
          <div>${qnaVO.mdfDt}</div> -->
        </div>
      </div>

      <div class="sub-info">
        <div class="grid info-border">
          <label class="sub-item" for="originFileName">첨부파일</label>
          <div class="sub-item">
            <a href="/qna/file/download/${qnaVO.qaId}">
              ${qnaVO.originFileName}
            </a>
          </div>

          <label class="sub-item" for="knlCnt">조회수</label>
          <div class="sub-item">${qnaVO.qaCnt}</div>

          <label class="sub-item" for="qaRecCnt">추천수</label>
          <div class="sub-item" id="qaRecCnt">${qnaVO.qaRecCnt}</div>
        </div>
      </div>

      <div class="content-info info-border">
        <div class="content-border">
          <!-- <label for="knlCntnt">내용</label> -->
          <div class="sub-item">${qnaVO.qaCntnt}</div>
        </div>
      </div>
    </div>

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

    <div class="replies">
      <div class="reply-items"></div>
      <div class="write-reply">
        <textarea
          id="txt-reply"
          name="rplCntnt"
          data-issue-id="${qnaVO.qaId}"
          placeholder="댓글을 작성해보세요!"
        ></textarea>
        <div class="btn-wrapper">
          <button id="btn-save-reply" data-mode="">등록</button>
          <button id="btn-cancel-reply">취소</button>
        </div>
      </div>
    </div>
  </body>
</html>
