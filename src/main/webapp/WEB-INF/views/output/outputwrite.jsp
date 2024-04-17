<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/output/outputwrite.js"></script>
    <title>산출물관리 신규작성 페이지</title>
  </head>
  <style>
    .grid {
      display: grid;
      grid-template-columns: 200px 1fr;
      grid-template-rows: repeat(6 40px);
    }
  </style>
  <body>
    <form method="post" enctype="multipart/form-data">
      <div class="grid">
        <!--프로젝트명 선택창 todo 서버에서 정보 가져와서 for문 돌리기-->
        <label for="prj-id">프로젝트명</label>
        <select name="prjId" id="prj-id">
          <c:forEach items="${projectList.projectList}" var="project">
            <option value="${project.prjId}">${project.prjName}</option>
          </c:forEach>
        </select>

        <label for="out-type">산출물 종류</label>
        <select name="outType" id="out-type">
          <c:forEach items="${outputType}" var="output">
            <option value="${output.cmcdId}">${output.cmcdName}</option>
          </c:forEach>
        </select>

        <label for="out-ttl">산출물 제목</label>
        <div class="ttlInput">
          <input type="text" name="outTtl" id="out-ttl" />
        </div>

        <label for="file">산출물 파일</label>
        <input type="file" id="file" name="file" />

        <!--담당자 확인자 테스터 테스트결과는 아직 어떤 기준으로 해야하는지 알 수 없음-->

        <input id="submitBtn" type="submit" value="제출" />
      </div>
    </form>
  </body>
</html>
