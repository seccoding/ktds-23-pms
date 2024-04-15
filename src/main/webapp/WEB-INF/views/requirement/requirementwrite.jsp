<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>요구사항 상세 페이지</title>

    <style>
      .grid {
        display: grid;
        grid-template-columns: 200px 1fr;
        grid-template-rows: repeat(6 40px);
      }
    </style>
  </head>

  <body>
    <div class="grid">
      <c:if test="${not empty requirememt.projectVO.prjName}">
        <div>프로젝트명</div>
        <div>${requirememt.projectVO.prjName}</div>
      </c:if>

      <div>요구사항 제목</div>
      <input type="text" name="rqmId" value="${requirement.rqmTtl}" />

      <div>요구사항 내용</div>
      <input type="text" name="rqmId" value="${requirement.rqmCntnt}" />
      <!--체크박스 선택창-->
      <div>일정상태</div>
      <select name="scdStsVOCmcdName" id="scdStsVO-cmcdName">
        <option value="501">대기중</option>
        <option value="502">진행중</option>
        <!-- <option value="503" >연기필요</option> -->
      </select>

      <!--체크박스 선택창-->
      <div>진행상태</div>
      <select name="rqmStsVOCmcdName" id="rqmStsVO-cmcdName">
        <option value="601">분석중</option>
        <option value="602">설계중</option>
        <option value="603">개발중</option>
        <option value="604">단위테스트진행중</option>
        <option value="605">개발완료</option>
      </select>

      <!--날짜선택창-->
      <label for="start-date">시작일</label>
      <input type="date" name="start-date" value="${requirement.strtDt}" />
      <!--날짜선택창-->
      <label for="end-date">종료예정일</label>
      <input type="date" name="end-date" value="${requirement.endDt}" />

      <!--담당자 확인자 테스터 테스트결과는 아직 어떤 기준으로 해야하는지 알 수 없음-->

      <button id="submitBtn" type="button" a="">제출</button>
    </div>
  </body>
</html>
