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
    <table>
      <colgroup>
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
      </colgroup>
      <thead class="table">
        <tr>
          <th>프로젝트명</th>
          <th>요구사항 아이디</th>
          <th>요구사항 제목</th>
          <th>요구사항 내용</th>
          <th>요구사항 상태1</th>
          <th>요구사항 상태2</th>
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
                    href="/project/requirement/view?prjId=${requirememt.prjId}&rqmId=${requirememt.rqmId}"
                    >${requirememt.rqmTtl}</a
                  >
                </td>
                <td>${requirememt.rqmCntnt}</td>
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
        <a href="/project/requirement/write">신규</a>
      </p>
    </div>
  </body>
</html>
