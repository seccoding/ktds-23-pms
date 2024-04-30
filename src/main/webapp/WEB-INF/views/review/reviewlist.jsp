<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>후기 목록</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    
    <script type="text/javascript" src="/js/review/reviewresult.js"></script>
    <style type="text/css">
      div.grid {
        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: 1fr 28px 1fr;
        row-gap: 10px;
      }
    </style>
  </head>
  <body>
    <jsp:include page="../commonmodal.jsp"/>
    <div>
      <table class="table">
        <colgroup>
          <col width="*" />
          <col width="*" />
          <col width="*" />
          <col width="*" />
          <col width="*" />
          <col width="*" />
        </colgroup>
        <thead>
          <tr>
            <th>프로젝트 명</th>
            <th>고객사</th>
            <th>수행부서</th>
            
            <th>시작일</th>
            <th>종료일</th>
            <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'Y' || isPM}">
            <th>후기결과보기</th></c:if>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty reviewlist.reviewList}">
              <c:forEach items="${reviewlist.reviewList}" var="review">
                <tr>
	              <c:choose>
				    <c:when test="${sessionScope._LOGIN_USER_.mngrYn eq 'Y'}">
				          <td class="mngr-yn" data-review-yn="${review.mngrYn}">
							${review.projectVO.prjName}</td>
				    </c:when>
				    
				    <c:when test="${review.projectTeammateVO.rvYn eq 'Y'}">
						  <td class="review-yn" data-review-yn="${review.rvYn}">
							${review.projectVO.prjName}</td>
				    </c:when>
				    
				       <c:otherwise>
				          <td>
				             <a href="/review/prjId/${review.projectVO.prjId}/write">
				                ${review.projectVO.prjName}
				             </a>
						  <!-- <a href="#" class="delete-button">삭제하기!</a> -->
				          </td>
				      </c:otherwise>
				   </c:choose>
                  <td>${review.projectVO.clntInfo}</td>
                  <td>${review.departmentVO.deptName}</td>
                  
                  <td>${review.projectVO.strtDt}</td>
                  <td>${review.projectVO.endDt}</td>
                  <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'Y' || isPM}">
                  <td
                    onclick="location.href='/review/viewresult?prjId=${review.prjId}'"
                  >
                    후기결과보기
                  </td>
                  </c:if>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="6">
                        <div>참여 중인 프로젝트가 없거나 프로젝트의 후기 관리가 불가능한 상태입니다.</div>
                    </td>
                </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>
    
  </body>
</html>





     <nav aria-label="Page navigation">
        <form id="search-form">
            <div class="search-keyword">
			<input type="hidden" id="page-no" name="pageNo" value="0"/>
	        <select id="list-size" name="listSize">
	          <option value="10" ${SearchReviewVO.listSize eq 10 ? 'selected' : ''}>10개</option>
	          <option value="20" ${SearchReviewVO.listSize eq 20 ? 'selected' : ''}>20개</option>
	          <option value="30" ${SearchReviewVO.listSize eq 30 ? 'selected' : ''}>30개</option>
	
	        </select>
	
	        <!--검색하는 타입을 만들어줌-->
	        <select id="search-type" name="searchType">
	          <option value="prjName" ${SearchReviewVO.searchType eq 'prjName' ? 'selected' : ''}>프로젝트 명</option>
	          <option value="clntInfo" ${SearchReviewVO.searchType eq 'clntInfo' ? 'selected' : ''}>고객사</option>
	          <option value="deptName" ${SearchReviewVO.searchType eq 'deptName' ? 'selected' : ''}>수행부서</option>
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
                    <li class="${searchApproval.pageNo eq p ? 'active' : ''} page-item">
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