<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>후기 결과 조회</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <!-- <link rel="stylesheet" href="/css/common.css" /> -->
    <style type="text/css">

      div.grid {
        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: 1fr 28px 1fr;
        row-gap: 10px;
      }
      #search-form {
        position: fixed; /* 화면 스크롤에 고정 */
        bottom: 20px; /* 하단 여백 설정 */
        left: 50%; 
        transform: translateX(-50%); 
        background-color: #f9f9f9;
        padding: 10px;
        border-radius: 5px;
        box-shadow: 0px 2px 5px #000000;
        z-index: 999; 
      }
      .ellipsis {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        width: 150px;
        display: inline-block;
      }

    </style>
        <script type="text/javascript" src="/js/review/reviewresult.js"></script>
        <script type="text/javascript" src="/js/review/reviewlist.js"></script>
  </head>
  
  <body>
  <div>
	총 ${reviewList.reviewCnt}건의 후기가 검색되었습니다.
  </div>
	<button id="deleteMassiveReview" href="javascript:void(0)">삭제</button>
    <!--<a id="deleteMassiveMReview" href="javascript:void(0)">삭제</a>-->
  
  
    <table class="table">
      <colgroup>
         <col width="150px" />
         <col width="*" />
         <col width="150px" />
       </colgroup>
       <thead>
         <tr>
         	<th>
                <input type="checkbox" id="checked-all" data-target-class="target-review-id" />
     	 		      <label for="checked-all"></label>
            </th>
           <th>리뷰 Id</th>
           <th>후기 내용</th>
           <th>삭제</th>
         </tr>
       </thead>
       <tbody>
         <c:forEach items="${reviewList.reviewList}" var="review" varStatus="loop">
           <tr id="${review.rvId}">
             	<td>
                 <input type="checkbox" 
                 		class = "target-review-id" 
                 		id="target-review-id-${loop.index}" 
                 		name="targetReviewId" 
                 		value="${review.rvId}" />
     	 		       <label for="target-review-id-${loop.index}"></label>
               </td>
             <td>${review.rvId}</td>
             <td class="center-align ellipsis">${review.rvCntnt}</td>
             <td class="btn-group">
               <div class="right-align">
                 <a href="#" class="delete-button">삭제하기!</a>
               </div>
             </td>
           </tr>
         </c:forEach> 
       </tbody>
     </table>
    </body>
  </html>

  <!----------------------------------------------------  Paginator 시작 -------------------------------------------------------->
  <div>
	  <form id="search-form">
	    <!-- value=0 은 아무것도 안치고 엔터눌렀을때 에러나는것을 방지하는 하는 역할-->
	    <input type="hidden" id="page-no" name="pageNo" value="0"/>
      	<input type="hidden" name="prjId" value= ${SearchReviewVO.prjId} />

	    <select id="list-size" name="listSize">
	      <option value="10" ${SearchReviewVO.listSize eq 10 ? 'selected' : ''}>10개</option>
	      <option value="20" ${SearchReviewVO.listSize eq 20 ? 'selected' : ''}>20개</option>
	      <option value="30" ${SearchReviewVO.listSize eq 30 ? 'selected' : ''}>30개</option>
	
	    </select>
	
	    <!--검색하는 타입을 만들어줌-->
	    <select id="search-type" name="searchType">
	      <option value="content" ${SearchReviewVO.searchType eq 'content' ? 'selected' : ''}>내용</option>
	    </select>
	
	    <input type="text" name="searchKeyword" value="${SearchReviewVO.searchKeyword}"/>
	    <button type="button" id="search-btn">검색</button>
	
	    <ul class="page-nav">
	      <c:if test="${SearchReviewVO.hasPrevGroup}">
	        <!-- '처음'을 클릭하면, javascript에 있는 search함수에 0을 파라미터로 전달해서 form을 전송시킴 -->
	        <li><a href="javascript:search(0);">처음</a></li>
	        <li>
	          <a
	            href="javascript:search(${SearchReviewVO.prevGroupStartPageNo});"
	            >이전</a
	          >
	        </li>
	      </c:if>
	
	      <!--page번호를 반복하여 노출한다.-->
	      <c:forEach
	        begin="${SearchReviewVO.groupStartPageNo}"
	        end="${SearchReviewVO.groupEndPageNo}"
	        step="1"
	        var="p"
	      >
	        <!--페이지번호(pageNo) 는 항상 0부터 시작 -->
	        <li class="${SearchReviewVO.pageNo eq p ? 'active' : ''}">
	          <a href="javascript:search(${p});">${p+1}</a>
	        </li>
	      </c:forEach>
	
	      <c:if test="${SearchReviewVO.hasNextGroup}">
	        <li>
	          <a
	            href="javascript:search(${SearchReviewVO.nextGroupStartPageNo});"
	            >다음</a
	          >
	        </li>
	        <li>
	          <a href="javascript:search(${SearchReviewVO.pageCount - 1});"
	            >마지막</a
	          >
	        </li>
	      </c:if>
	    </ul>
	</form>
</div>
<!----------------------------------------------------  Paginator 끝 -------------------------------------------------------->


