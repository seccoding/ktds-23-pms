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
    </style>
    <!-- <script type="text/javascript" src="/js/boardlist.js"></script> -->
  </head>
  <body>
    <jsp:include page="../layout/layout.jsp" />
    <!-- <dialog></dialog> -->
    <div class="main">
      <div class="grid">
        <table class="table">
          <colgroup>
            <col width="80px" />
            <col width="*" />
            <col width="150px" />
          </colgroup>
          <thead>
            <tr>
              <th>리뷰 Id</th>
              <th>후기 내용</th>
              <th>삭제</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${reviewList.reviewList}" var="review">
              <tr id="${review.rvId}">
                <td>${review.rvId}</td>
                <td class="center-align">${review.rvCntnt}</td>
                <td class="btn-group">
                  <div class="right-align">
                    <%-- <a href="/review/delete/${review.rvId}">삭제</a> --%>
                    <a href="#" class="delete-button">삭제</a>
                  </div>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>

        <%--
        <c:if test="${not empty sessionScope._LOGIN_USER_}">
          <div class="right-align">
            <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'Y'}">
              <a id="deleteMassiveReview" href="javascript:void(0);"
                >일괄삭제</a
              >
            </c:if>
          </div>
        </c:if>
        --%>
      </div>
    </div>
  </body>
  <script type="text/javascript" src="/js/review/reviewresult.js"></script>
</html>
