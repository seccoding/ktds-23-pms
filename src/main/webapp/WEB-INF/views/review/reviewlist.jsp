<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>후기 목록</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
  </head>
  <body>
    <div>
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
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty reviewlist.reviewList}">
              <c:forEach items="${reviewlist.reviewList}" var="review">
                <tr>
                  <td>${review.projectVO.prjName}</td>
                  <td>${review.projectVO.clntInfo}</td>
                  <td>${review.projectVO.deptId}</td>
                  <td>${review.projectVO.prjSts}</td>
                  <td>${review.projectVO.strtDt}</td>
                  <td>${review.projectVO.endDt}</td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <tr>
                <td colspan="7">
                  <a href="/survey/write"> 후기작성~ </a>
                </td>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>
  </body>
</html>
