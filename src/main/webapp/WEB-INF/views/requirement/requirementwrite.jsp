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
    <script type="text/javascript">
      window.onload = function () {};
    </script>
    <title>요구사항 신규작성 페이지</title>

    <style>
      .grid {
        display: grid;
        grid-template-columns: 200px 1fr;
        grid-template-rows: repeat(8, 40px) 1fr 40px 40px 40px;
      }
    </style>
  </head>

  <body>
    <form
      name="requirementForm"
      id="writeForm"
      action="/requirement/write"
      method="post"
      enctype="multipart/form-data"
    >
      <div class="grid">
        <!--프로젝트명 선택창 todo 서버에서 정보 가져와서 for문 돌리기-->
        <label for="prj-id">프로젝트</label>
        <select name="prjId" id="prj-id">
          <option value="">프로젝트를 선택해주세요</option>
          <c:forEach items="${projectList.projectList}" var="project">
            <option value="${project.prjId}">${project.prjName}</option>
          </c:forEach>
        </select>

        <label for="rqm-ttl">요구사항 제목</label>
        <input
          type="text"
          id="rqm-ttl"
          name="rqmTtl"
          value="${requirement.rqmTtl}"
        />

        <label for="dvlrp">담당개발자</label>
        <select id="dvlrp-check" name="dvlrp" id="dvlrp">
          <option value="">프로젝트를 선택해주세요</option>
        </select>

        <label for="cfrmr">확인자</label>
        <select id="cfrmr-check" name="cfrmr" id="cfrmr">
          <option value="">프로젝트를 선택해주세요</option>
        </select>

        <label for="tstr">테스터</label>
        <select id="tstr-check" name="tstr" id="tstr">
          <option value="">프로젝트를 선택해주세요</option>
        </select>

        <!--날짜선택창-->
        <label for="start-date">시작일</label>
        <input
          type="date"
          id="start-date"
          name="strtDt"
          value="${requirement.strtDt}"
        />
        <!--날짜선택창-->
        <label for="end-date">종료예정일</label>
        <input
          type="date"
          id="end-date"
          name="endDt"
          value="${requirement.endDt}"
        />
        <label for="file">첨부파일</label>
        <input type="file" id="file" name="file" />

        <!-- ckeditor를 이용한 내용넣기-->
        <label for="rqm-cntnt">요구사항 내용</label>
        <div class="hereCkEditor5">
          <%-- 여기가 editor 생성부 --%>
          <div class="editor" data-name="rqmCntnt"></div>
          <input
            type="text"
            id="rqm-cntnt"
            name="rqmCntnt"
            style="visibility: hidden"
          />
        </div>

        <!--체크박스 일정상태 선택창 todo 서버에서 정보 가져와서 for문 돌리기-->
        <label for="scd-sts">일정상태</label>
        <select name="scdSts" id="scd-sts" value="${requirement.rqmSts}">
          <c:forEach items="${scdSts}" var="scdSts">
            <c:if test="${ scdSts.cmcdId != '503'}">
              <option value="${scdSts.cmcdId}">${scdSts.cmcdName}</option>
            </c:if>
          </c:forEach>
        </select>

        <!--체크박스 진행상태 선택창 todo 서버에서 정보 가져와서 for문 돌리기-->
        <label for="rqm-sts">진행상태</label>
        <select name="rqmSts" id="rqm-sts" value="${requirement.rqmSts}">
          <c:forEach items="${rqmSts}" var="rqmSts">
            <option value="${rqmSts.cmcdId}">${rqmSts.cmcdName}</option>
          </c:forEach>
        </select>

        <button type="button">등록</button>
      </div>
    </form>
  </body>
</html>
