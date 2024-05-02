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
      <jsp:include page="../commonmodal.jsp"/>
    <style type="text/css">
	   .title {
            margin-bottom: 3rem;
       }
		.modal-confirm-text1 {
	    text-align: center;
	    color: #000000;
	    font-size: 0.8rem;
	    overflow: auto;
	    word-break: break-all;
        word-wrap: break-word;
	  	}
       
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
        width: 500px;
        display: inline-block;
      }
      .modal-window1,
	  .modal-confirm-window1 {
			position: absolute;
			border-radius: 20px;
			border: 0;
			box-shadow: 0px 8px 15px 0px rgba(0, 0, 0, 0.1);
			transition: box-shadow 0.3s ease;

			justify-content: center;
			top: 30%;
			left: 30%;

			width: 600px;
			height: fit-content;
			padding: 1rem;
 
			background-color: rgba(F, F, F, 0.8);
		}
		.modal-close1,
		.modal-confirm-close1 {
			text-align: right;
			color: gray;
		}
		.modal-close1:hover,
		.modal-confirm-close1:hover {
			color: black;
		}
		.grid-modal1,
		.grid-confirm-modal1 {
			display: grid;
			grid-template-rows: 1fr 6fr 1fr;
		}
		.modal-content1 {
			align-items: center;
			display: flex;
			justify-content: space-around;
			vertical-align: middle;
			overflow-y: auto;
			max-height: 11rem;
		}
		.modal-confirm-content1 {
			display: flex;
			flex-direction: column;
			justify-content: flex-start;
			align-items: center;
			overflow-y: auto;
			max-height: 11rem;
			
		}
		.input-space,
		.input-confirm-space {
			text-align: right;
			align-items: end;
		}
    </style>
  </head>
  
  <body>

  <div>
	후기 답변 현황 : ${reviewList.reviewCnt} / ${teammateCount}
  </div>
	
    <table class="table">
      <colgroup>
         <col width="150px" />
         <col width="200px" />
         <col width="*" />
       </colgroup>
       <thead>
         <tr>
         	<th>
                <input type="checkbox" id="checked-all" data-target-class="target-review-id" />
     	 		      <label for="checked-all"></label>
            </th>
           <th>리뷰 Id</th>
           <th>후기 내용</th>
           <!-- <th>삭제</th> -->
         </tr>
       </thead>
       <tbody>
       <c:choose>
       <c:when test="${not empty reviewList.reviewList}">
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
             <!-- <td class="btn-group">
               <div class="right-align">
				<button href = "#" class="delete-button">삭제</button>
               </div>
             </td> -->
           </tr>
         </c:forEach> 
         </c:when>
         	<c:otherwise>
                <tr>
                    <td colspan="2">
                        <div>등록된 후기가 없습니다.</div>
                    </td>
                </tr>
            </c:otherwise>
         </c:choose>
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
	    <button id="deleteMassiveReview" href="javascript:void(0)">삭제</button>
	    
    	<!--<a id="deleteMassiveMReview" href="javascript:void(0)">삭제</a>-->
	    
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

</body>
</html>

