<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>요구사항 상세 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script
      type="text/javascript"
      src="/js/requirement/requirementview.js"
    ></script>

    <style>
      .grid {
        display: grid;

        grid-template-columns: 200px 1fr;
        grid-template-rows: 60px 40px 40px 40px 600px 40px;
      }
      .grid :nth-child(3) {
        grid-column: 1 / -1;
      }
      .grid :nth-child(4) {
        grid-column: 1 / -1;
      }

      .flex-child {
        display: flex;
        /* grid-template-columns: 15% 15% 15% 15% 15% 15%;
        grid-template-rows: 40px; */
      }
    </style>
  </head>

  <body>
    <div
      class="grid"
      data-rqm-id="${requirement.rqmId}"
      data-prj-id="${requirement.prjId}"
      data-session-id="${sessionScope._LOGIN_USER_.empId}"
      data-admin-code="${sessionScope._LOGIN_USER_.admnCode}"
      data-crtr-id="${requirement.crtrId}"
    >
      <div class="title">요구사항 제목</div>
      <div class="title">${requirement.rqmTtl}</div>
      <div class="item flex-child">
        <!-- 프로젝트 좌측 나머지 우측가야됨 -->
        <div class="sub-item">프로젝트명</div>
        <div class="sub-item">${requirement.projectVO.prjName}</div>
        <div class="sub-item">작성자</div>
        <div class="sub-item">${requirement.crtrIdVO.empName}</div>
        <div class="sub-item">작성일</div>
        <div class="sub-item">${requirement.crtDt}</div>
      </div>
      <!-- 일정상태 진행상태 각각 반반  -->
      <div class="item flex-child">
        <div class="sub-item">일정상태</div>
        <div class="sub-item">${requirement.scdStsVO.cmcdName}</div>
        <div class="sub-item">진행상태</div>
        <div class="sub-item">${requirement.rqmStsVO.cmcdName}</div>
      </div>
      <!-- 담당자 목록 3등분 -->
      <div class="item">담당자 목록</div>
      <div class="item">
        <div class="flex-child">
          <div class="sub-item">담당개발자</div>
          <div class="sub-item">${requirement.dvlrpVO.empName}</div>
          <div class="sub-item">확인자</div>
          <div class="sub-item">${requirement.cfrmrVO.empName}</div>
          <div class="sub-item">테스터</div>
          <div class="sub-item">${requirement.tstrVO.empName}</div>
        </div>
      </div>

      <div class="item">요구사항 내용</div>
      <div class="item">${requirement.rqmCntnt}</div>

      <div class="item">파일</div>
      <div class="item">
        <a href="/requirement/downloadFile/${requirement.rqmId}"
          >${requirement.rqmFile}</a
        >
      </div>
    </div>

    <div>
      <button>
        <a id="modify" href="javascript:void(0)">수정</a>
      </button>
      <button>
        <a id="delete" href="javascript:void(0)">삭제</a>
      </button>

      <c:choose>
        <c:when test="${requirement.scdStsVO.cmcdName != '연기필요'}">
          <button>
            <a id="delay-request" href="javascript:void(0)">연기요청</a>
          </button>
        </c:when>
        <c:otherwise>
          <c:if test='${employeeVO.getAdmnCode() == "301" or isPmAndPl }'>
            <button>
              <a id="approve" href="javascript:void(0)" data-approve="true"
                >승인</a
              >
            </button>
            <button>
              <a id="refuse" href="javascript:void(0)" data-approve="false"
                >거절</a
              >
            </button>
          </c:if>
        </c:otherwise>
      </c:choose>
    </div>
  </body>
</html>
