<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>후기 목록</title>
    <style>
      /* 검색 폼 스타일 */
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
    </style>
    <jsp:include page="../commonheader.jsp"></jsp:include>
  </head>
  <body>
    <div>
      <!-- 검색 폼 -->
      <form id="search-form">
        <input type="hidden" id="page-no" name="pageNo" value="0"/>
        <select id="list-size" name="listSize">
          <option value="10" ${searchReviewVO.listSize eq 10 ? 'selected' : ''}>10개</option>
          <option value="20" ${searchReviewVO.listSize eq 20 ? 'selected' : ''}>20개</option>
          <option value="30" ${searchReviewVO.listSize eq 30 ? 'selected' : ''}>30개</option>
        </select>

        <select id="search-type" name="searchType">
          <option value="title" ${searchReviewVO.searchType eq 'title' ? 'selected' : ''}>제목</option>
          <option value="content" ${searchReviewVO.searchType eq 'content' ? 'selected' : ''}>내용</option>
          <option value="title_content" ${searchReviewVO.searchType eq 'title_content' ? 'selected' : ''}>제목 + 내용</option>
          <option value="email" ${searchReviewVO.searchType eq 'email' ? 'selected' : ''}>작성자</option>
        </select>

        <input type="text" name="searchKeyword" value="${searchReviewVO.searchKeyword}"/>
        <button type="button" id="search-btn">검색</button>
        <button type="button" id="cancel-search-btn">초기화</button>
      </form>

      <table class="table">
        <colgroup>
          <col width="*" />
          <col width="150px" />
          <col width="150px" />
          <col width="150px" />
          <col width="150px" />
          <col width="150px" />
        </colgroup>
        <thead>
          <tr>
            <th>프로젝트 명</th>
            <th>고객사</th>
            <th>수행부서</th>
            <th>상태</th>
            <th>시작일</th>
            <th>종료일</th>
            <th>후기결과보기</th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty reviewlist.reviewList}">
              <c:forEach items="${reviewlist.reviewList}" var="review">
                <tr>
                  <td>
                    <a href="/review/prjId/${review.projectVO.prjId}/write">${review.projectVO.prjName}</a>
                  </td>
                  <td>${review.projectVO.clntInfo}</td>
                  <td>${review.projectVO.deptId}</td>
                  <td>${review.projectVO.prjSts}</td>
                  <td>${review.projectVO.strtDt}</td>
                  <td>${review.projectVO.endDt}</td>
                  <td onclick="location.href='/review/viewresult?prjId=${review.prjId}'">후기결과보기</td>
                </tr>
              </c:forEach>
            </c:when>
          </c:choose>
        </tbody>
      </table>
    </div>
  </body>
</html>
