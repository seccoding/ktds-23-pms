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
    <style>
      .flex-row {
        display: flex;
        flex-direction: row;
        margin-bottom: 10px;
      }
      button {
        margin-left: 10px;
        align-self: center;
      }
      button[id="new"] {
        margin-left: auto;
        margin-right: 10px;
      }
    </style>
  </head>
  <body>
    <div>총 ${resultList.count}건의 요구사항이 검색됬습니다.</div>

    <table class="table">
      <colgroup>
        <col width="40px" />
        <col width="160px" />
        <col width="600px" />
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
      </colgroup>
      <thead>
        <tr>
          <th>
            <input
              type="checkbox"
              id="checked-all"
              data-target-class="target-rqm-id"
            />
            <label for="checked-all"></label>
          </th>
          <th>프로젝트</th>
          <th>제목</th>
          <th>일정상태</th>
          <th>진행상태</th>
          <th>작성자</th>
          <th>작성일</th>
        </tr>
      </thead>
      <tbody>
        <c:choose>
          <c:when test="${not empty resultList}">
            <c:forEach
              items="${resultList.requirementList}"
              var="requirememt"
              varStatus="status"
            >
              <tr>
                <td>
                  <input
                    type="checkbox"
                    class="target-rqm-id"
                    id="checked-requirement-${status.index}"
                    value="${requirememt.rqmId}"
                  />
                  <label for="checked-requirement-${status.index}"></label>
                </td>
                <td>${requirememt.projectVO.prjName}</td>
                <td>
                  <a
                    href="/requirement/view?prjId=${requirememt.prjId}&rqmId=${requirememt.rqmId}"
                    >${requirememt.rqmTtl}</a
                  >
                </td>
                <td>${requirememt.scdStsVO.cmcdName}</td>
                <td>${requirememt.rqmStsVO.cmcdName}</td>
                <td>${requirememt.crtrIdVO.empName}</td>
                <td>${requirememt.crtDt}</td>
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

    <div class="flex-row">
      <label for="prj-id"></label>
      <select name="prjId" id="prj-id">
        <option value="" selected>프로젝트</option>
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
      <button id="new"><a href="/requirement/write">신규</a></button>
    </div>

    <nav aria-label="Page navigation">
      <!--pagination-->
      <ul class="pagination">
        <c:if test="${resultList.count > 0}">
          <!--처음-->
          <c:if test="${requirementSearch.hasPrevGroup}">
            <li class="page-item first">
              <a href="javascript:search(0)"
                ><img src="/images/chevron-double-left.svg"
              /></a>
            </li>
          </c:if>
          <!--이전-->
          <c:if test="${requirementSearch.hasPrevGroup}">
            <li class="page-item prev">
              <a
                href="javascript:search(${requirementSearch.prevGroupStartPageNo})"
                ><img src="/images/chevron-left.svg"
              /></a>
            </li>
          </c:if>
          <!-- 각 페이지 링크 -->
          <c:forEach
            varStatus="status"
            begin="${requirementSearch.groupStartPageNo}"
            end="${requirementSearch.groupEndPageNo}"
          >
            <li
              class="${requirementSearch.pageNo eq requirementSearch.groupStartPageNo+status.count-1 ? 'active' : ''} page-item"
            >
              <a
                class="page-link"
                href="javascript:search(${requirementSearch.groupStartPageNo+status.count-1})"
                >${requirementSearch.groupStartPageNo+status.count}</a
              >
            </li>
          </c:forEach>
          <!--다음-->
          <c:if test="${requirementSearch.hasNextGroup}">
            <a
              href="javascript:search(${requirementSearch.nextGroupStartPageNo})"
              ><img src="/images/chevron-right.svg" /></a
          ></c:if>
          <!--마지막-->
          <c:if test="${requirementSearch.hasNextGroup}"
            ><a href="javascript:search(${requirementSearch.pageCount-1})"
              ><img src="/images/chevron-double-right.svg" /></a
          ></c:if>
        </c:if>
      </ul>
    </nav>
  </body>
</html>
