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
        grid-template-rows: repeat(9 40px);
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
      <div>프로젝트명</div>
      <div>${requirement.projectVO.prjName}</div>
      <div>요구사항 아이디</div>
      <div id="rqmId" data-rqm-id="${requirement.rqmId}">
        ${requirement.rqmId}
      </div>
      <div>요구사항 제목</div>
      <div>${requirement.rqmTtl}</div>

      <div>담당개발자</div>
      <div>${requirement.dvlrpVO.empName}</div>
      <div>확인자</div>
      <div>${requirement.cfrmrVO.empName}</div>
      <div>테스터</div>
      <div>${requirement.tstrVO.empName}</div>

      <div>요구사항 내용</div>
      <div>${requirement.rqmCntnt}</div>

      <div>일정상태</div>
      <div>${requirement.scdStsVO.cmcdName}</div>
      <div>진행상태</div>
      <div>${requirement.rqmStsVO.cmcdName}</div>
      <div>파일</div>
      <div>
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
