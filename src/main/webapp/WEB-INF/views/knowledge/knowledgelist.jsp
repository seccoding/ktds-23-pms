<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>지식관리 목록</title>
    <jsp:include page="./member/member.jsp"></jsp:include>
    <script
      type="text/javascript"
      src="/js/knowledge/knowledgelist.js"
    ></script>
    <style type="text/css">
      div.grid {
        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: 28px 1fr auto 28px;
        row-gap: 10px;
      }
    </style>
  </head>
  <body>
    <div class="grid">
      <div>총 ${knowledgeList.knowledgeCnt} 건의 게시글이 검색되었습니다.</div>
      <table class="table">
        <colgroup>
          <col width="100px" />
          <col width="180px" />
          <col width="100px" />
          <col width="*" />
          <col width="80px" />
          <col width="80px" />
          <col width="100px" />
        </colgroup>
        <thead>
          <tr>
            <th>프로젝트명</th>
            <th>요구사항제목</th>
            <th>등록자</th>
            <th>제목</th>
            <th>조회수</th>
            <th>추천수</th>
            <th>작성일자</th>
          </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty knowledgeList.knowledgeList}">
              <c:forEach items="${knowledgeList.knowledgeList}" var="knowledge">
                <tr>
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
                </tr>
              </c:forEach>
            </c:when>
            <%-- boardList 의 내용이 존재하지 않는다면 --%>
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
    <div>
      <a class="btn-group" href="/knowledge/write">새 글 등록</a>
    </div>

    <!-- 엑셀 다운로드 -->
    <!-- <c:if test="${not empty sessionScope._LOGIN_USER_}">
      <div class="right-align">
        <c:if test="${sessionScope._LOGIN_USER_.adminYn eq 'Y'}">
        <a href="/board/excel/download2">엑셀 다운로드</a>
      </c:if>
        <a href="/board/write">게시글 등록</a>
      <c:if test="${sessionScope._LOGIN_USER_.adminYn eq 'Y'}">
        <a id="deleteMassiveBoard" href="javaScript:void(0)">일괄 삭제</a>
        <a id="uploadExcelfile" href="javaScript:void(0)">게시글 일괄 등록</a>
        <input type="file" id="excelfile" style="display: none;">
      </c:if>
      </div>
    </c:if> -->
  </div>
  </div>
  </body>
</html>
