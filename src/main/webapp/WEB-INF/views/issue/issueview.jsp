<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>이슈 관리 상세 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/issue/issueview.js"></script>
    <jsp:include page="../commonmodal.jsp"></jsp:include>
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
    <div class="grid" data-id="${issueVO.isId}" data-crtr-id="${issuVO.crtrId}">
      <span style="display: none" hidden id="login-email">${sessionScope._LOGIN_USER_.empId}</span>
      <div class="main-info">
        <div class="grid info-border">
          <!-- <label for="subject">제목</label> -->
          <div class="sub-item">${issueVO.isTtl}</div>

          <!-- 프로젝트 이름 요구사항 이름 -->
          <label class="sub-item" for="name">프로젝트</label>
          <div class="sub-item">${issueVO.projectVO.prjName}</div>

          <label class="sub-item" for="name">요구사항</label>
          <div class="sub-item">${issueVO.requirementVO.rqmTtl}</div>

          <label class="sub-item" for="name">작성자</label>
          <div class="sub-item">${issueVO.employeeVO.empName}</div>

          <label class="sub-item" for="crtDt">작성일</label>
          <div class="sub-item">${issueVO.crtDt}</div>

          <!-- <label for="mdfyDt">수정일</label>
          <div>${issueVO.mdfDt}</div> -->
        </div>
      </div>

      <div class="sub-info">
        <div class="grid info-border">
          <label class="sub-item" for="originFileName">첨부파일</label>
          <div class="sub-item">
            <a href="/issue/file/download/${issueVO.isId}">
              ${issueVO.originFileName}
            </a>
          </div>

          <label class="sub-item" for="viewCnt">이슈상태</label>
          <div class="sub-item">${issueVO.isSts}</div>

          <label class="sub-item" for="viewCnt">난이도</label>
          <div class="sub-item">${issueVO.isLv}</div>

          <label class="sub-item" for="viewCnt">조회수</label>
          <div class="sub-item">${issueVO.isCnt}</div>
        </div>
      </div>

      <div class="content-info info-border">
        <div class="content-border">
          <div class="sub-item">${issueVO.isCntnt}</div>
        </div>
      </div>
    </div>

    <div class="btn-wrapper">
      <button>
        <a href="/issue">목록</a>
      </button>
      <c:if
        test="${sessionScope._LOGIN_USER_.empId eq issueVO.crtrId || sessionScope._LOGIN_USER_.mngrYn eq 'Y'}"
      >
        <button>
          <a id="modify" href="javascript:void(0)">수정</a>
        </button>
        <button>
          <a class="delete-issue" href="javascript:void(0);">삭제</a>
        </button>
      </c:if>
    </div>
    <div class="replies">
      <div class="reply-items"></div>
      <div class="write-reply">
        <textarea
          id="txt-reply"
          name="rplCntnt"
          data-issue-id="${issueVO.isId}"
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
