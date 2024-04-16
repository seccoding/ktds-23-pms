<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <jsp:include page="../commonheader.jsp"></jsp:include>
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
    <jsp:include page="../layout/layout.jsp"></jsp:include>
    <div>${projectList.projectCount}</div>
    <form
      action="/output/write"
      method="post"
      enctype="multipart/form-data"
    >
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
        <input type="text" name="outTtl" id="out-ttl" value="" />

        
       
        <!--담당자 확인자 테스터 테스트결과는 아직 어떤 기준으로 해야하는지 알 수 없음-->

        <input id="submitBtn" type="submit" value="제출"></input>
      </div>
    </form>

    <jsp:include page="../layout/layout_close.jsp"></jsp:include>
  </body>
</html>
