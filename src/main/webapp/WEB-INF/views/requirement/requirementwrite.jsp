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
      <div>프로젝트명 ${requirememt.projectVO.prjName}</div>
      <input
        type="text"
        name="prgId"
        value="${requirememt.projectVO.prjName}"
      />
      <div>요구사항 아이디</div>
      <input type="text" name="rqmId" value="${requirement.rqmId}" />
      <div>요구사항 제목</div>
      <div>${requirement.rqmTtl}</div>
      <div>요구사항 내용</div>
      <div>${requirement.rqmCntnt}</div>
      <div>요구사항 상태1</div>
      <div>${requirement.scdStsVO.cmcdName}</div>
      <div>요구사항 상태1</div>
      <div>${requirement.rqmStsVO.cmcdName}</div>
    </div>
  </body>
</html>
