<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script
      type="text/javascript"
      src="/js/requirement/requirementlist.js"
    ></script>
    <title>요구사항 리스트 페이지</title>
  </head>
  <body>
    <label for="prj-id"></label>
    <select name="prjId" id="prj-id">
      <option value="ALL" selected>프로젝트</option>
      <c:forEach items="${projectList.projectList}" var="project">
        <c:choose>
          <c:when test="${project.prjId eq prjId}">
            <option value="${project.prjId}" selected>
              ${project.prjName}
            </option>
          </c:when>
          <c:otherwise>
            <option value="${project.prjId}">${project.prjName}</option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select>

    <label for="scd-sts"></label>
    <select name="scdSts" id="scd-sts" value="${requirement.rqmSts}">
      <option value="" selected>일정상태</option>
      <c:forEach items="${scdSts}" var="scdSts">
        <c:choose>
          <c:when test="${searchOption.scdSts eq scdSts.cmcdId}">
            <option value="${scdSts.cmcdId}" selected>
              ${scdSts.cmcdName}
            </option>
          </c:when>
          <c:otherwise>
            <option value="${scdSts.cmcdId}">${scdSts.cmcdName}</option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select>

    <!--체크박스 진행상태 선택창 todo 서버에서 정보 가져와서 for문 돌리기-->
    <label for="rqm-sts"></label>
    <select name="rqmSts" id="rqm-sts" value="${requirement.rqmSts}">
      <option value="" selected>진행상태</option>
      <c:forEach items="${rqmSts}" var="rqmSts">
        <c:choose>
          <c:when test="${searchOption.rqmSts eq rqmSts.cmcdId}">
            <option value="${rqmSts.cmcdId}" selected>
              ${rqmSts.cmcdName}
            </option>
          </c:when>
          <c:otherwise>
            <option value="${rqmSts.cmcdId}">${rqmSts.cmcdName}</option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select>

    <button id="search">검색</button>
    <button id="reset">초기화</button>

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
              <td>현재 요구사항이 없습니다</td>
            </tr>
          </c:otherwise>
        </c:choose>
      </tbody>
    </table>

        <ul class="page-nav">
      <c:if test="${resultList.count > 0}">
        <!--처음 그룹링크-->
        <c:if test="${requirementSearch.hasPrevGroup}">
          <li><a href="javascript:search(0)">처음</a></li>
        </c:if>
        <!--이전 그룹링크-->
        <c:if test="${requirementSearch.hasPrevGroup}">
          <li>
            <a href="${requirementSearch.prevGroupStartPageNo}">이전</a>
          </li>
        </c:if>
        <!-- 각 페이지 링크 -->
        <c:forEach
          varStatus="status"
          begin="${requirementSearch.groupStartPageNo}"
          end="${requirementSearch.groupEndPageNo}"
        >
          <li>
            <a
              href="javascript:search(${requirementSearch.groupStartPageNo+status.count-1})"
              >${requirementSearch.groupStartPageNo+status.count}</a
            >
          </li>
        </c:forEach>
        <c:if test="${requirementSearch.hasNextGroup}">
          <a href="javascript:search(${requirementSearch.nextGroupStartPageNo})"
            >다음</a
          ></c:if
        >
        <c:if test="${requirementSearch.hasNextGroup}"
          ><a href="javascript:search(${requirementSearch.pageCount})"
            >마지막</a
          ></c:if
        >
      </c:if>
</ul>

    <div>
      <p>
        <button><a href="/requirement/write">신규</a></button>
      </p>
    </div>
  </body>
</html>
