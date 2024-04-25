<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>지식관리 목록</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script
      type="text/javascript"
      src="/js/knowledge/knowledgelist.js"
    ></script>
  </head>
  <body>
    <div class="grid">
      <div>총 ${knowledgeList.knowledgeCnt} 건의 게시글이 검색되었습니다.</div>
      <table class="table">
        <colgroup>
          <col width="40px" />
          <col width="100px" />
          <col width="150px" />
          <col width="100px" />
          <col width="*" />
          <col width="80px" />
          <col width="80px" />
          <col width="150px" />
          <col width="150px" />
        </colgroup>
        <thead>
          <tr>
            <th>
              <input type="checkbox" id="checked-all" data-target-class="target-knl-id">
              <label for="checked-all"></label>
            </th>
            <th>프로젝트</th>
            <th>요구사항명</th>
            <th>등록자</th>
            <th>제목</th>
            <th>조회수</th>
            <th>추천수</th>
            <th>작성일자</th>
            <th>수정일자</th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty knowledgeList.knowledgeList}">
              <c:forEach items="${knowledgeList.knowledgeList}" var="knowledge" varStatus="loop">
              <tr>
                <td>
                  <input type="checkbox" class="target-knl-id" id="target-knl-id-${loop.index}" value="${knowledge.knlId}">
                  <label for="target-knl-id-${loop.index}"></label>
                </td>
                  <td>${knowledge.projectVO.prjName}</td>
                  <td>${knowledge.requirementVO.rqmTtl}</td>
                  <td>${knowledge.crtrId}</td>
                  <td>
                    <a
                      class="ellipsis"
                      href="/knowledge/view?knlId=${knowledge.knlId}"
                    >
                      ${knowledge.knlTtl}</a
                    >
                  </td>
                  <td>${knowledge.knlCnt}</td>
                  <td>${knowledge.knlRecCnt}</td>
                  <td>${knowledge.crtDt}</td>
                  <td>${knowledge.mdfDt}</td>
                </tr>
              </c:forEach>
            </c:when>
            <%-- knowledgeList 의 내용이 존재하지 않는다면 --%>
            <c:otherwise>
              <tr>
                <td colspan="6"></td>
                <a href="/knowledge/write">
                  등록된 게시글이 없습니다. 첫 번째 글의 주인공이 되어보세요!
                </a>
              </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
      </table>
    </div>

    <!-- 검색 -->
    <nav aria-label="Page navigation">
      <form id="search-form">
        <input type="hidden" id="page-no" name="pageNo" value="0">
        <select name="listSize" id="list-size">
          <option value="10" ${searchKnowledgeVO.listSize eq 10 ? 'selected' : ''}>10개</option>
          <option value="20" ${searchKnowledgeVO.listSize eq 20 ? 'selected' : ''}>20개</option>
          <option value="30" ${searchKnowledgeVO.listSize eq 30 ? 'selected' : ''}>30개</option>
          <option value="50" ${searchKnowledgeVO.listSize eq 50 ? 'selected' : ''}>50개</option>
          <option value="100" ${searchKnowledgeVO.listSize eq 100 ? 'selected' : ''}>100개</option>
        </select>

        <select name="searchType" id="search-type">
          <option value="prjName" ${searchKnowledgeVO.searchType eq 'prjName' ? 'selected' : ''}>프로젝트</option>
          <option value="rqmTtl" ${searchKnowledgeVO.searchType eq 'rqmTtl' ? 'selected' : ''}>요구사항명</option>
          <option value="knlTtl" ${searchKnowledgeVO.searchType eq 'knlTtl' ? 'selected' : ''}>제목</option>
          <option value="knlCntnt" ${searchKnowledgeVO.searchType eq 'knlCntnt' ? 'selected' : ''}>내용</option>
          <option value="knlTtl_knlCntnt" ${searchKnowledgeVO.searchType eq 'knlTtl_knlCntnt' ? 'selected' : ''}>제목 + 내용</option>
          <option value="crtrId" ${searchKnowledgeVO.searchType eq 'crtrId' ? 'selected' : ''}>등록자</option>
        </select>

        <input type="text" name="searchKeyword" value="${searchKnowledgeVO.searchKeyword}">
        <button type="button" id="search-btn">검색</button>
        <button type="button" id="search-btn-cancel">초기화</button>

        <!-- pagination -->
        <ul class="pagination">
          <c:if test="${searchKnowledgeVO.hasPrevGroup}">
              <li class="page-item first">
                  <a class="page-link" href="javascript:search(0);"><img src="/images/chevron-double-left.svg"/></a>
              </li>
              <li class="page-item prev">
                  <a class="page-link" href="javascript:search(${searchQnaVO.prevGroupStartPageNo});"><img src="/images/chevron-left.svg"/></a>
              </li>
          </c:if>
          <c:forEach begin="${searchKnowledgeVO.groupStartPageNo}" end="${searchKnowledgeVO.groupEndPageNo}" step="1" var="p">
              <li class="${searchKnowledgeVO.pageNo eq p ? 'active' : ''} page-item">
                  <a class="page-link" href="javascript:search(${p});">${p+1}</a>
              </li>
          </c:forEach>
          <c:if test="${searchKnowledgeVO.hasNextGroup}">
              <li class="page-item next">
                  <a class="page-link" 
                  
                  href="javascript:search(${searchKnowledgeVO.nextGroupStartPageNo});"><img src="/images/chevron-right.svg"/></a>
              </li>
              <li class="page-item last">
                  <a class="page-link" href="javascript:search(${searchKnowledgeVO.groupCount - 1});"><img src="/images/chevron-double-right.svg"/></a>
              </li>
          </c:if>
      </ul>
      </form>
    </nav>
<!-- Paginator 끝 -->
    <div>
      <button class="btn-group">
      <a class="btn-group" href="/knowledge/write">새 글 등록</a>
    </button>
    <c:if test="${sessionScope._LOGIN_USER_.admnCode eq '301'}">
    <button>
      <a href="/knowledge/excel/download">엑셀 다운</a>
      </button>
        <button>
          <a id="deleteMassiveKnowledge" href="javaScript:void(0)">일괄 삭제</a>
        </button>
      </c:if>
    </div>
  </body>
</html>
