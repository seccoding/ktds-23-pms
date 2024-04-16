<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>후기 목록</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <head>
      <meta charset="UTF-8" />
      <title>후기 작성하기~!</title>
  
      <style type="text/css">
        div.grid {
          display: grid;
          grid-template-columns: 80px 1fr;
          grid-template-rows: 28px 28px 320px 1fr;
          row-gap: 10px;
        }
      </style>
    </head>
    <body>
      <h1>후기 작성</h1>
      <form action="/review/write" method="post">
        <div class="grid">
          <input type="hidden" id="rvId" name="rvId" value="RV_20240412_000001" />
  
          <label for="prjId">프로젝트명</label>
          <input type="text" id="prjId" name="prjId" value="PRJ-240409-000012" />
  
          <!-- <label for="crtrId">작성인</label> -->
          <input type="hidden" id="crtrId" name="crtrId" value="0005020" />
  
          <!-- <label for="crtDt">작성 날짜</label> -->
          <input type="hidden" name="crtDt" value="2024-04-12 14:38:47.000" />
  
          <!-- <label for="mdfDt">수정 날짜</label> -->
          <input type="hidden" name="mdfDt" value="2024-04-12 14:38:47.000" />
  
          <input type="hidden" id="delYn" name="delYn" value="N" />
  
          <label for="rvCntnt">후기 내용</label>
          <textarea id="content" name="rvCntnt" style="height: 300px">
  ${reviewVO.rvCntnt}</textarea
          >
  
          <div class="btn-group">
          	<!-- <div class="right-align">
              <input type="submit" value="임시저장" />
            </div> -->
            <div class="right-align">
              <input type="submit" value="저장" />
            </div>
          </div>
        </div>
      </form>
    </body>
  </html>