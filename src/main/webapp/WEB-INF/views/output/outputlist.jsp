<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>산출물관리 리스트 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/output/outputlist.js"></script>
  </head>
  <body>
    <label for="prj-id"></label>
    <select name="prjId" id="prj-id">
      <option value="" selected>프로젝트</option>
      <c:forEach items="${projectList.projectList}" var="project">
        <c:choose>
          <c:when test="${project.prjId eq outputSearchVO.prjId}">
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

    <label for="out-type"></label>
    <select name="outType" id="out-type">
      <option value="" selected>산출물타입</option>
      <c:forEach items="${commonCodeList}" var="commonCode">
        <c:choose>
          <c:when test="${commonCode.cmcdId eq outputSearchVO.outType}">
            <option value="${commonCode.cmcdId}" selected>
              ${commonCode.cmcdName}
            </option>
          </c:when>
          <c:otherwise>
            <option value="${commonCode.cmcdId}">${commonCode.cmcdName}</option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select>

    <button id="search-output">검색</button>
    <button id="reset"><a href="/output/search?prjId=">초기화</a></button>

    <table class="table">
      <colgroup>
        <col width="40px" />
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
        <col width="160px" />
        <col width="40px" />
        <col width="40px" />
      </colgroup>
      <thead>
        <tr>
          <th>
            <input
              type="checkbox"
              id="checked-all"
              data-target-class="target-out-id"
            />
            <label for="checked-all"></label>
          </th>
          <th>프로젝트</th>
          <th>산출물 제목</th>
          <th>산출물 종류</th>
          <th>버전</th>
          <th>파일명</th>
          <th>등록일</th>
          <th>수정일</th>
          <th>수정</th>
          <th>삭제</th>
        </tr>
      </thead>
      <tbody>
        <c:choose>
          <c:when test="${not empty outputList}">
            <c:forEach
              var="output"
              items="${outputList.outputList}"
              varStatus="status"
            >
              <tr>
                <td>
                  <input
                    type="checkbox"
                    class="target-out-id"
                    id="checked-out-${status.index}"
                    value="${output.outId}"
                  />
                  <label for="checked-out-${status.index}"></label>
                </td>
                <td>${output.project.prjName}</td>
                <td>${output.outTtl}</td>
                <td>${output.outTypeVO.cmcdName}</td>
                <td>${output.outVer}</td>
                <td>
                  <a href="/output/downloadFile/${output.outId}"
                    >${output.outFile}</a
                  >
                </td>
                <td>${output.crtDt}</td>
                <td>${output.mdfDt}</td>
                <td>
                  <button>
                    <a id="modify-btn" href="/output/modify/${output.outId}"
                      >수정</a
                    >
                  </button>
                </td>
                <td>
                  <button>
                    <a href="/output/delete/${output.outId}?prjId=${prjId}"
                      >삭제</a
                    >
                  </button>
                </td>
              </tr>
            </c:forEach>
          </c:when>
          <c:otherwise> </c:otherwise>
        </c:choose>
      </tbody>
    </table>

    <!--pagination-->
    <ul class="page-nav">
      <c:if test="${outputList.listCnt > 0}">
        <!--처음-->
        <c:if test="${outputSearchVO.hasPrevGroup}">
          <li><a href="javascript:search(0)">처음</a></li>
        </c:if>
        <!--이전-->
        <c:if test="${outputSearchVO.hasPrevGroup}">
          <li>
            <a href="javascript:search(${outputSearchVO.prevGroupStartPageNo})"
              >이전</a
            >
          </li>
        </c:if>
        <!-- 각 페이지 링크 -->
        <c:forEach
          varStatus="status"
          begin="${outputSearchVO.groupStartPageNo}"
          end="${outputSearchVO.groupEndPageNo}"
        >
          <li>
            <a
              href="javascript:search(${outputSearchVO.groupStartPageNo+status.count-1})"
              >${outputSearchVO.groupStartPageNo+status.count}</a
            >
          </li>
        </c:forEach>
        <!--다음-->
        <c:if test="${outputSearchVO.hasNextGroup}">
          <a href="javascript:search(${outputSearchVO.nextGroupStartPageNo})"
            >다음</a
          ></c:if
        >
        <!--마지막-->
        <c:if test="${outputSearchVO.hasNextGroup}"
          ><a href="javascript:search(${outputSearchVO.pageCount})"
            >마지막</a
          ></c:if
        >
      </c:if>
    </ul>
    <div>
      <button><a href="/output/write">신규</a></button>
    </div>
  </body>
</html>
