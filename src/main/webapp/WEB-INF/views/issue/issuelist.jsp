<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이슈 목록 페이지</title>
<jsp:include page="../commonheader.jsp" />
<script type="text/javascript" src="/js/issue/issuelist.js"></script>
</head>
<body>
<div>
	총 ${issueList.issueCnt}건의 게시글이 검색되었습니다.
</div>
<table class="table">
    <colgroup>
        <col width="40px" />
        <col width="60px" />
        <col width="80px" />
        <col width="80px" />
        <col width="50px" />
        <col width="80px" />
        <col width="50px" />
        <col width="50px" />
        <col width="50px" />
    </colgroup>
    <thead>
        <tr>
            <th>
                <input type="checkbox" id="checked-all" data-target-class="target-issue-id"/>
                <label for="checked-all"></label>
            </th>
            <th>프로젝트</th>
            <th>요구사항명</th>
            <th>이슈제목</th>
            <th>등록자</th>
            <th>등록일</th>
            <th>이슈상태</th>
            <th>조회수</th>
            <th>난이도</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${not empty issueList.issueList}">
                <c:forEach items="${issueList.issueList}" var="issue" varStatus="loop">
                    <tr>
                        <td>
                            <input type="checkbox" class="target-issue-id" id="target-issue-id-${loop.index}" value="${issue.isId}"/>
                            <label for="target-issue-id-${loop.index}"></label>
                        </td>
                        <td>${issue.projectVO.prjName}</td>
                        <td>${issue.requirementVO.rqmTtl}</td>
                        <td><a href="/issue/view?isId=${issue.isId}">${issue.isTtl}</a></td>
                        <td>${issue.employeeVO.empName}</td>
                        <td>${issue.crtDt}</td>
                        <td>${issue.isSts}</td>
                        <td>${issue.isCnt}</td>
                        <td>${issue.isLv}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="9">
                      <a href="/issue/write">등록된 이슈가 없습니다.</a>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>

<!-- Paginator 시작 -->
<div>
    <form id="search-form">
      <input type="hidden" id="page-no" name="pageNo" value="0" />
      <select id="list-size" name="listSize">
        <option value="10" ${searchIssueVO.listSize eq 10 ? 'selected' : ''}>10개</option>
        <option value="20" ${searchIssueVO.listSize eq 20 ? 'selected' : ''}>20개</option>
        <option value="30" ${searchIssueVO.listSize eq 30 ? 'selected' : ''}>30개</option>
        <option value="50" ${searchIssueVO.listSize eq 50 ? 'selected' : ''}>50개</option>
        <option value="100" ${searchIssueVO.listSize eq 100 ? 'selected' : ''}>100개</option>
      </select>

      <select id="search-type" name="searchType">
        <option value="project" ${searchIssueVO.searchType eq 'project' ? 'selected' : ''}>프로젝트명</option>
        <option value="requirement" ${searchIssueVO.searchType eq 'requirement' ? 'selected' : ''}>요구사항명</option>  
        <option value="title" ${searchIssueVO.searchType eq 'title' ? 'selected' : ''}>이슈제목</option>
        <option value="content" ${searchIssueVO.searchType eq 'content' ? 'selected' : ''}>내용</option>
        <option value="creator" ${searchIssueVO.searchType eq 'creator' ? 'selected' : ''}>작성자</option>
        <option value="originFileName" ${searchIssueVO.searchType eq 'originFileName' ? 'selected' : ''}>첨부파일명</option>
        <option value="status" ${searchIssueVO.searchType eq 'status' ? 'selected' : ''}>이슈상태</option>
      </select>

      <input type="text" name="searchKeyword" value="${searchIssueVO.searchKeyword}" />
      <button type="button" id="search-btn">검색</button>
      <button type="button" id="cancel-search-btn">초기화</button>

      <ul class="page-nav">
        <c:if test="${searchIssueVO.hasPrevGroup}">
          <li><a href="javascript:search(0);">처음</a></li>
          <li>
            <a
              href="javascript:search(${searchIssueVO.prevGroupStartPageNo});"
              >이전</a
            >
          </li>
        </c:if>

        <!-- Page 번호를 반복하며 노출한다. -->
        <c:forEach
          begin="${searchIssueVO.groupStartPageNo}"
          end="${searchIssueVO.groupEndPageNo}"
          step="1"
          var="p"
        >
          <li class="${searchIssueVO.pageNo eq p ? 'active' : ''}">
            <a href="javascript:search(${p});">${p+1}</a>
          </li>
        </c:forEach>

        <c:if test="${searchIssueVO.hasNextGroup}">
          <li>
            <a
              href="javascript:search(${searchIssueVO.nextGroupStartPageNo});"
              >다음</a
            >
          </li>
          <li>
            <a href="javascript:search(${searchIssueVO.pageCount - 1});"
              >마지막</a
            >
          </li>
        </c:if>
      </ul>
    </form>
  </div>
<!-- Paginator 끝 -->

<div>
    <button>
        <a href="/issue/write">신규등록</a>
    </button>
    <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'Y'}">
      <button>
          <a href="/issue/excel/download">엑셀다운</a>
      </button>
      <button>
          <a id="deleteMassiveIssue" href="javascript:void(0);">일괄삭제</a>
      </button>
    </c:if>   
</div>
</body>
</html>