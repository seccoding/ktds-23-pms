<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <jsp:include page="../ckeditor.jsp" />
    <script
      type="text/javascript"
      src="/js/requirement/requirementwrite.js"
    ></script>
    <%--
    <script type="text/javascript">
      window.onload = function () {
        var editors = loadEditor(".editor", "내용을 입력하세요.");
        var rqmCntnt = "";

        $("button").on("click", function (event) {
          event.preventDefault();

          rqmCntnt = editors.getData();

          $("#rqm-cntnt").val(rqmCntnt);

          $("#writeForm").submit();
        });
      };
    </script>
    --%>
    <style>
      .grid {
        display: grid;
        grid-template-columns: 200px 1fr;
        grid-template-rows: repeat(8, 40px) 1fr 40px 40px 40px;
      }
      .error {
        display: inline-block;
        margin-left: 20px;
      }
    </style>

    <title>요구사항 수정페이지</title>
  </head>

  <body>
    <form
      id="writeForm"
      action="/requirement/modify?rqmId=${requirement.rqmId}"
      method="post"
      enctype="multipart/form-data"
    >
      <div class="grid" data-rqm-id="${requirement.rqmId}">
        <!--프로젝트명 선택창 todo 서버에서 정보 가져와서 for문 돌리기-->
        <label for="prj-id">프로젝트명</label>
        <div>
          <select name="prjId" id="prj-id">
            <c:forEach items="${projectList.projectList}" var="project">
              <c:choose>
                <c:when test="${project.prjId eq requirement.prjId}">
                  <option value="${project.prjId}" selected>
                    ${project.prjName}
                  </option>
                </c:when>
                <c:otherwise>
                  <option value="${project.prjId}">${project.prjName}</option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </select>
        </div>

        <label for="rqm-ttl">요구사항 제목</label>
        <div>
          <input
            type="text"
            id="rqm-ttl"
            name="rqmTtl"
            value="${requirement.rqmTtl}"
          />
        </div>

        <label for="dvlrp">담당개발자</label>
        <div>
          <select id="dvlrp-check" name="dvlrp" id="dvlrp">
            <c:forEach items="${prjTeammateList}" var="prjTeammateList">
              <c:choose>
                <c:when test="${prjTeammateList.tmId eq requirement.dvlrp}">
                  <option value="${prjTeammateList.tmId}" selected>
                    ${prjTeammateList.employeeVO.empName}
                  </option>
                </c:when>
                <c:otherwise>
                  <option value="${prjTeammateList.tmId}">
                    ${prjTeammateList.employeeVO.empName}
                  </option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </select>
        </div>

        <label for="cfrmr">확인자</label>
        <div>
          <select id="cfrmr-check" name="cfrmr" id="cfrmr">
            <c:forEach items="${prjTeammateList}" var="prjTeammateList">
              <c:choose>
                <c:when test="${prjTeammateList.tmId eq requirement.cfrmr}">
                  <option value="${prjTeammateList.tmId}" selected>
                    ${prjTeammateList.employeeVO.empName}
                  </option>
                </c:when>
                <c:otherwise>
                  <option value="${prjTeammateList.tmId}">
                    ${prjTeammateList.employeeVO.empName}
                  </option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </select>
        </div>
        <label for="tstr">테스터</label>
        <div>
          <select id="tstr-check" name="tstr" id="tstr">
            <c:forEach items="${prjTeammateList}" var="prjTeammateList">
              <c:choose>
                <c:when test="${prjTeammateList.tmId eq requirement.tstr}">
                  <option value="${prjTeammateList.tmId}" selected>
                    ${prjTeammateList.employeeVO.empName}
                  </option>
                </c:when>
                <c:otherwise>
                  <option value="${prjTeammateList.tmId}">
                    ${prjTeammateList.employeeVO.empName}
                  </option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </select>
        </div>

        <!--날짜선택창-->

        <label for="start-date">시작일</label>
        <div>
          <input
            type="date"
            id="start-date"
            name="strtDt"
            value="${requirement.strtDt}"
          />
        </div>

        <!--날짜선택창-->
        <label for="end-date">종료예정일</label>
        <div>
          <input
            type="date"
            id="end-date"
            name="endDt"
            value="${requirement.endDt}"
          />
        </div>
        <label for="file">첨부파일</label>
        <input type="file" id="file" name="file" />

        <!-- ckeditor를 이용한 내용넣기-->
        <label for="rqm-cntnt">요구사항 내용</label>
        <div class="hereCkEditor5">
          <%-- 여기가 editor 생성부 --%>
          <div
            class="editor"
            data-name="rqmCntnt"
            data-init-content="${requirement.rqmCntnt}"
          ></div>
        </div>

        <!--체크박스 일정상태 선택창 todo 서버에서 정보 가져와서 for문 돌리기-->
        <label for="scd-sts">일정상태</label>
        <select name="scdSts" id="scd-sts" value="${requirement.rqmSts}">
          <c:forEach items="${scdSts}" var="scdSts">
            <c:if test="${ scdSts.cmcdId != '503'}">
              <c:choose>
                <c:when test="${scdSts.cmcdId eq requirement.scdSts}">
                  <option value="${scdSts.cmcdId}" selected>
                    ${scdSts.cmcdName}
                  </option>
                </c:when>
                <c:otherwise>
                  <option value="${scdSts.cmcdId}">${scdSts.cmcdName}</option>
                </c:otherwise>
              </c:choose>
            </c:if>
          </c:forEach>
        </select>

        <!--체크박스 진행상태 선택창 todo 서버에서 정보 가져와서 for문 돌리기-->
        <label for="rqm-sts">진행상태</label>
        <select name="rqmSts" id="rqm-sts" value="${requirement.rqmSts}">
          <c:forEach items="${rqmSts}" var="rqmSts">
            <c:if test="${ rqmSts.cmcdId != '605'}">
              <c:choose>
                <c:when test="${rqmSts.cmcdId eq requirement.rqmSts}">
                  <option value="${rqmSts.cmcdId}" selected>
                    ${rqmSts.cmcdName}
                  </option>
                </c:when>
                <c:otherwise>
                  <option value="${rqmSts.cmcdId}">${rqmSts.cmcdName}</option>
                </c:otherwise>
              </c:choose>
            </c:if>
          </c:forEach>
        </select>

        <!--담당자 확인자 테스터 테스트결과는 아직 어떤 기준으로 해야하는지 알 수 없음-->

        <button type="button" data-type="modify">등록</button>
      </div>
    </form>
  </body>
</html>
