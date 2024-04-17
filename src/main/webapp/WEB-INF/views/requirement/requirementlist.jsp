<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>요구사항 리스트 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/requirement/requirementlist.js"></script>
  </head>
  <body>
    <label for="prj-id">프로젝트명</label>
      <select name="prjId" id="prj-id">
        <option value="ALL" selected>모두선택</option>
        <c:forEach items="${projectList.projectList}" var="project">
          <c:choose>
              <c:when test="${project.prjId eq prjId}">
                <option value="${project.prjId}" selected>${project.prjName}</option>
              </c:when>
              <c:otherwise>
                <option value="${project.prjId}">${project.prjName}</option>
              </c:otherwise>
          </c:choose>
        </c:forEach>
      </select> 
      <button id="search-prj-id">검색</button>

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
            <c:forEach items="${resultList.requirementList}" var="requirememt">
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
