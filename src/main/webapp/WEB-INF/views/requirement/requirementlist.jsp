<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>요구사항 리스트 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
  </head>
  <body>
    <table class="table">
      <colgroup>
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
      </colgroup>
      <thead>
        <tr>
          <th>프로젝트명</th>
          <th>요구사항 아이디</th>
          <th>요구사항 제목</th>
          <th>일정상태</th>
          <th>진행상태</th>
        </tr>
      </thead>
      <tbody>
        <c:choose>
          <c:when test="${not empty resultList}">
            <c:forEach items="${resultList}" var="requirememt">
              <tr>
                <td>${requirememt.projectVO.prjName}</td>
                <td>${requirememt.rqmId}</td>
                <td>
                  <a
                    href="/requirement/view?prjId=${requirememt.prjId}&rqmId=${requirememt.rqmId}"
                    >${requirememt.rqmTtl}</a
                  >
                </td>
                <td>${requirememt.scdStsVO.cmcdName}</td>
                <td>${requirememt.rqmStsVO.cmcdName}</td>
              </tr>
            </c:forEach>
          </c:when>

          <c:otherwise>
            <tr>
              <td>2</td>
            </tr>
          </c:otherwise>
        </c:choose>
      </tbody>
    </table>

    <div>
      <p>
        <button><a href="/requirement/write">신규</a></button>
      </p>
    </div>
  </body>
</html>
