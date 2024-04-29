<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>후기 결과 조회</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/review/reviewresult.js"></script>
    <!-- <link rel="stylesheet" href="/css/common.css" /> -->
    <style type="text/css">

      div.grid {
        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: 1fr 28px 1fr;
        row-gap: 10px;
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
  <jsp:include page="../commonmodal.jsp"/>
  <div>
	총 ${reviewList.reviewCnt}건의 후기가 검색되었습니다.
	후기 답변 현황 : ${reviewList.reviewCnt} / ${reviewList.reviewCnt}
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
<!--                  <a href="#" class="delete-button">삭제하기!</a> -->
				<button href = "#" class="delete-button">삭제</button>
               </div>
             </td>
           </tr>
         </c:forEach> 
       </tbody>
     </table>
    

  <!----------------------------------------------------  Paginator 시작 -------------------------------------------------------->
  <nav aria-label="Page navigation">
        <form id="search-form">
            <div class="search-keyword">
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
            </div>
            <ul class="pagination">
                <c:if test="${SearchReviewVO.hasPrevGroup}">
                    <li class="page-item first">
                        <a class="page-link" href="javascript:search(0);"><img src="/images/chevron-double-left.svg"/></a>
                    </li>
                    <li class="page-item prev">
                        <a class="page-link" href="javascript:search(${SearchReviewVO.prevGroupStartPageNo});"><img src="/images/chevron-left.svg"/></a>
                    </li>
                </c:if>
                <c:forEach begin="${SearchReviewVO.groupStartPageNo}" end="${SearchReviewVO.groupEndPageNo}" step="1" var="p">
                    <li class="${SearchReviewVO.pageNo eq p ? 'active' : ''} page-item">
                        <a class="page-link" href="javascript:search(${p});">${p+1}</a>
                    </li>
                </c:forEach>
                <c:if test="${SearchReviewVO.hasNextGroup}">
                    <li class="page-item next">
                        <a class="page-link" href="javascript:search(${SearchReviewVO.nextGroupStartPageNo});"><img src="/images/chevron-right.svg"/></a>
                    </li>
                    <li class="page-item last">
                        <a class="page-link" href="javascript:search(${SearchReviewVO.groupCount - 1});"><img src="/images/chevron-double-right.svg"/></a>
                    </li>
                </c:if>
            </ul>
        </form>
    </nav>
<!----------------------------------------------------  Paginator 끝 -------------------------------------------------------->


