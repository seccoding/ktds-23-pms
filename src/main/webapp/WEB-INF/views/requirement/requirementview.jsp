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
      <div>프로젝트명</div>
      <div>${requirement.projectVO.prjName}</div>
      <div>요구사항 아이디</div>
      <div>${requirement.rqmId}</div>
      <div>요구사항 제목</div>
      <div>${requirement.rqmTtl}</div>
      <div>요구사항 내용</div>
      <div>${requirement.rqmCntnt}</div>
      <div>요구사항 상태1</div>
      <div>${requirement.scdStsVO.cmcdName}</div>
      <div>요구사항 상태1</div>
      <div>${requirement.rqmStsVO.cmcdName}</div>
    </div>

    <div>
      <a
        href="/project/${requirement.prjId}/requirement/${requirement.rqmId}/write"
        >수정</a
      >
    </div>
  </body>
</html>
