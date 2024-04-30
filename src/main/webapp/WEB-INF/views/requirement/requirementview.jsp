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

      .flex-row {
        display: flex;
        flex-direction: row;
      }
      .sub-item {
        align-self: center;
        margin-left: 5px;
      }
      .content-border {
        margin: 10px;
      }
      button[class="sub-item"] {
        margin-left: auto;
        margin-right: 40px;
      }
    </style>
  </head>

  <body>
    <div
      class="grid"
      id="rqm-info"
      data-rqm-id="${requirement.rqmId}"
      data-prj-id="${requirement.prjId}"
      data-session-id="${sessionScope._LOGIN_USER_.empId}"
      data-admin-code="${sessionScope._LOGIN_USER_.admnCode}"
      data-crtr-id="${requirement.crtrId}"
    >
      <div class="main-info">
        <div class="grid info-border">
          <div class="sub-item">${requirement.rqmTtl}</div>
          <div class="sub-item">프로젝트</div>
          <div class="sub-item">${requirement.projectVO.prjName}</div>
          <div class="sub-item">작성자</div>
          <div class="sub-item">${requirement.crtrIdVO.empName}</div>
          <div class="sub-item">기간</div>
          <div class="sub-item">
            ${requirement.strtDt} ~ ${requirement.endDt}
          </div>
          <div class="sub-item">작성일</div>
          <div class="sub-item">${requirement.crtDt}</div>
        </div>
      </div>
      <div class="sub-info">
        <div class="grid info-border">
          <div class="sub-item">일정상태</div>
          <div class="flex-row">
            <div class="sub-item">${requirement.scdStsVO.cmcdName}</div>

            <c:if test="${requirement.rqmSts != '605'}">
              <c:choose>
                <c:when test="${requirement.scdStsVO.cmcdName != '연기필요'}">
                  <button class="sub-item">
                    <a id="delay-request" href="javascript:void(0)">연기</a>
                  </button>
                </c:when>
                <c:otherwise>
                  <c:if
                    test='${employeeVO.getAdmnCode() == "301" or isPmAndPl }'
                  >
                    <button class="sub-item">
                      <a
                        id="approve"
                        href="javascript:void(0)"
                        data-approve="true"
                        >승인</a
                      >
                    </button>
                    <button class="sub-item">
                      <a
                        id="refuse"
                        href="javascript:void(0)"
                        data-approve="false"
                        >거절</a
                      >
                    </button>
                  </c:if>
                </c:otherwise>
              </c:choose>
            </c:if>
          </div>

          <div class="sub-item">담당개발자</div>
          <div class="sub-item">${requirement.dvlrpVO.empName}</div>

          <div class="sub-item">진행상태</div>
          <div class="sub-item">${requirement.rqmStsVO.cmcdName}</div>

          <div class="sub-item">확인자</div>
          <div class="sub-item">${requirement.cfrmrVO.empName}</div>

          <div class="sub-item">파일</div>
          <div class="sub-item">
            <a href="/requirement/downloadFile/${requirement.rqmId}"
              >${requirement.rqmFile}</a
            >
          </div>

          <div class="sub-item">테스터</div>
          <div class="flex-row">
            <div class="sub-item">${requirement.tstrVO.empName}</div>
            <c:if test='${requirement.rqmSts eq "604"}'>
              <button class="sub-item">
                <a id="test-result" href="javascript:void(0)">결과제출</a>
              </button>
            </c:if>
          </div>
        </div>
      </div>
      <div class="content-info info-border">
        <div class="content-border">
          <div>${requirement.rqmCntnt}</div>
        </div>
      </div>
    </div>

    <div>
      <button>
        <a id="modify" href="javascript:void(0)">수정</a>
      </button>
      <button>
        <a id="delete" href="javascript:void(0)">삭제</a>
      </button>
    </div>
  </body>
</html>
