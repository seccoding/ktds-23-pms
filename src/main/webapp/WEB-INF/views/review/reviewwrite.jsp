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
          grid-template-columns: 1fr;
          grid-template-rows: 28px 28px 320px 1fr;
          row-gap: 10px;
        }
	    .right-align {
	        text-align: right; 
	        margin-top: 10px; /* 상단 여백 */
	    }
	    label[for="rvCntnt"] {
        display: none;
    	}
    	
    	.input-form {
            padding: 20px;
            border-radius: 4px;
            border: 1px solid #ccc;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 600px;
            margin: 40px auto;
        }

        .form-row {
            margin-bottom: 10px;
        }

        .form-row label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .error-red{
        	color: #f00;
        }
        
        textarea {
        	resize:none;
        }
        
      </style>
    </head>
    <body>
  	  <div class="input-form overflow-scroll">
      <h1>후기 작성</h1>
      <form action="/review/write" method="post">
        <div class="grid">
          <input type="hidden" id="prjId" name="prjId" value="${project.prjId}" />
  
  		<div>
          <label for="prjId">프로젝트명</label>
          <input type="text" id="prjName" name="prjName" value="${project.prjName}" readonly/>
  		</div>
          <!-- <label for="crtrId">작성인</label> -->
          <!-- select ? -->
          <input type="hidden" id="crtrId" name="crtrId" value="${crtrId}" />
  
          <!-- <label for="crtDt">작성 날짜</label> -->
          <input type="hidden" name="crtDt" value="" />
  
          <!-- <label for="mdfDt">수정 날짜</label> -->
          <input type="hidden" name="mdfDt" value="" />
  
          <input type="hidden" id="delYn" name="delYn" value="N" />
  
          <label for="rvCntnt">
          </label>
          <c:forEach items="${errorMessage.rvCntnt}" var="error">
          <div class="error-red">${error}</div>
          </c:forEach>
          
          <textarea id="content" name="rvCntnt" style="height: 300px">${reviewVO.rvCntnt}</textarea>
          
          </div>
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
      </div>    
      </form>
    </body>
  </html>