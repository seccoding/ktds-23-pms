<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>지식관리 목록</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <jsp:include page="../commonmodal.jsp" />
    <script
      type="text/javascript"
      src="/js/knowledge/knowledgelist.js"
    ></script>
    <style>
      .search-keyword {
          display: flex;
      }
      .create-btn {
        text-align: right;
      }
  </style>
  </head>
  <body>
    <div class="grid">
      <div>총 ${knowledgeList.knowledgeCnt} 건의 게시글이 검색되었습니다.</div>
      <table class="table">
        <colgroup>
          <col width="40px" />
          <col width="120px" />
          <col width="180px" />
          <col width="*" />
          <col width="120px" />
          <col width="80px" />
          <col width="80px" />
          <col width="130px" />
          <!-- <col width="150px" /> -->
        </colgroup>
        <thead>
          <tr>
            <th>
              <c:if test="${sessionScope._LOGIN_USER_.admnCode eq '301'}">
              <input type="checkbox" id="checked-all" data-target-class="target-knl-id">
              <label for="checked-all"></label>
            </c:if>
            </th>
            <th>프로젝트</th>
            <th>요구사항</th>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
            <th>추천수</th>
            <th>작성일</th>
            <!-- <th>수정일자</th> -->
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty knowledgeList.knowledgeList}">
              <c:forEach items="${knowledgeList.knowledgeList}" var="knowledge" varStatus="loop">
              <tr>
                <td>
                  <c:if test="${sessionScope._LOGIN_USER_.admnCode eq '301'}">
                  <input type="checkbox" class="target-knl-id" id="target-knl-id-${loop.index}" value="${knowledge.knlId}">
                </c:if>
                  <label for="target-knl-id-${loop.index}"></label>
                </td>
                  <td>${knowledge.projectVO.prjName}</td>
                  <td>${knowledge.requirementVO.rqmTtl}</td>
                  <td>
                    <a
                    class="ellipsis"
                    href="/knowledge/view?knlId=${knowledge.knlId}"
                    >
                    ${knowledge.knlTtl}</a
                    >
                  </td>
                  <td>${knowledge.crtrId}</td>
                  <td>${knowledge.knlCnt}</td>
                  <td>${knowledge.knlRecCnt}</td>
                  <td>${knowledge.crtDt}</td>
                  <!-- <td>${knowledge.mdfDt}</td> -->
                </tr>
              </c:forEach>
            </c:when>
            <%-- knowledgeList 의 내용이 존재하지 않는다면 --%>
            <c:otherwise>
              <tr>
                <td colspan="6"></td>
                <a href="/knowledge/write">
                  등록된 게시글이 없습니다!
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
        <div class="search-keyword">
            <input type="hidden" id="page-no" name="pageNo" value="0">
            <select name="listSize" id="list-size">
              <option value="10" ${searchKnowledgeVO.listSize eq 10 ? 'selected' : ''}>10개</option>
              <option value="20" ${searchKnowledgeVO.listSize eq 20 ? 'selected' : ''}>20개</option>
              <option value="30" ${searchKnowledgeVO.listSize eq 30 ? 'selected' : ''}>30개</option>
              <option value="50" ${searchKnowledgeVO.listSize eq 50 ? 'selected' : ''}>50개</option>
            </select>

            <select name="searchType" id="search-type">
              <option value="" selected disabled hidden>검색 옵션</option>
              <option value="prjName" ${searchKnowledgeVO.searchType eq 'prjName' ? 'selected' : ''}>프로젝트</option>
              <option value="rqmTtl" ${searchKnowledgeVO.searchType eq 'rqmTtl' ? 'selected' : ''}>요구사항</option>
              <option value="knlTtl" ${searchKnowledgeVO.searchType eq 'knlTtl' ? 'selected' : ''}>제목</option>
              <option value="knlCntnt" ${searchKnowledgeVO.searchType eq 'knlCntnt' ? 'selected' : ''}>내용</option>
              <option value="knlTtl_knlCntnt" ${searchKnowledgeVO.searchType eq 'knlTtl_knlCntnt' ? 'selected' : ''}>제목 + 내용</option>
              <option value="crtrId" ${searchKnowledgeVO.searchType eq 'crtrId' ? 'selected' : ''}>작성자</option>
              <option value="originFileName" ${searchQnaVO.searchType eq 'originFileName' ? 'selected' : ''}>첨부파일명</option>
            </select>
        

            <div class="search-text">
              <input type="text" name="searchKeyword" value="${searchKnowledgeVO.searchKeyword}">
              <button type="button" id="search-btn">검색</button>
              <button type="button" id="search-btn-cancel">초기화</button>
            </div>
        </div>

        <div class="create-btn">
          <button>
            <a href="/knowledge/write">신규등록</a>
          </button>
          <c:if test="${sessionScope._LOGIN_USER_.admnCode eq '301'}">
            <button>
              <a href="/knowledge/excel/download">엑셀다운</a>
            </button>
            <button>
              <a id="deleteMassiveKnowledge" href="javaScript:void(0)">일괄삭제</a>
            </button>
          </c:if>
        </div>
        

        <!-- pagination -->
        <ul class="pagination">
          <c:if test="${searchKnowledgeVO.hasPrevGroup}">
              <li class="page-item first">
                  <a class="page-link" href="javascript:search(0);"><img src="/images/chevron-double-left.svg"/></a>
              </li>
              <li class="page-item prev">
                  <a class="page-link" href="javascript:search(${searchKnowledgeVO.prevGroupStartPageNo});"><img src="/images/chevron-left.svg"/></a>
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

  </body>
</html>
