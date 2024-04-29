<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보낸 편지함 목록</title>
<jsp:include page="../commonheader.jsp" />
<script type="text/javascript" src="/js/memo/memolist.js"></script>
<style>
    .memotop{
        text-align: left;
    }
    .ellipsis {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        width: 7rem;
        display: inline-block;
      }
    .memotable {
        margin-bottom: 1rem;
    }
    .container-memo {
        display: grid;
        grid-template-columns: 1fr 1fr;
        height: 100vh; /* 뷰포트 전체 높이 */
    }

    .left-memo {
        flex: 1;
        overflow-x: auto;
    }
    .right-memo {
        flex: 1;
        overflow-x: auto;
    }
</style>
</head>
<body>
    <jsp:include page="../commonmodal.jsp" />
    <div class="container-memo">
        <div class="left-memo">
            <div class="memotable">
                <div class="memotop"> 총 ${memoList.memoCnt} 건의 메모가 검색되었습니다.</div>
                    <table>
                        <colgroup>
                            <col width="*" />
                            <col width="*" />
                            <col width="*" />
                            <col width="*" />
                            <col width="*" />
                            <col width="*" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>
                                    <input type="checkbox" id="checked-all" data-target-class="target-memo-id" />
                                    <label for="checked-all"></label>
                                </th>
                                <th>제목</th>
                                <th>받는 사람</th>
                                <th>보낸 사람</th>
                                <th>보낸 날짜</th>
                                <th>확인 여부</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty memoList.memoList}">
                                    <c:forEach items="${memoList.memoList}" var="memo" varStatus="loop">
                                        <tr>
                                            <td>
                                                <input type="checkbox" class = "target-memo-id" id="target-memo-id-${loop.index}" name="targetMemoId" value="${memo.memoId}" />
                                                <label for="target-memo-id-${loop.index}"></label>
                                            </td>
                                            <td class="left-align">
                                                <a class="ellipsis Sent-loadLink" href="#" data-url="/memo/sent/view?id=${memo.memoId}">
                                                    ${memo.memoTtl}
                                                </a>
                                            </td>
                                            <td class="center-align">${memo.rcvId} ${memo.rcvName} : (${memo.rcvEmail})</td>
                                            <td class="center-align">${memo.crtrId} ${memo.crtrName} : (${memo.crtrEmail})</td>
                                            <td class="center-align">${memo.crtDt}</td>
                                            <td class="center-align">
                                                <c:if test="${memo.readYn eq 'Y'}">
                                                    <span class="badge bg-success">확인</span>
                                                </c:if>
                                                <c:if test="${memo.readYn eq 'N'}">
                                                    <span class="badge bg-label-danger">미확인</span>
                                                </c:if>
                                            </td>     
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="6" style="width: 1482px; text-align: center;">
                                            <a href="/memo/write">
                                                등록된 쪽지가 없습니다.
                                            </a>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>

                <!-- Paginator 시작 -->
                <nav aria-label="Page navigation">
                    <form id="search-form">
                        <div class="search-keyword">
                            <div class="search-category">
                                <input type="hidden" id="page-no" name="pageNo" value="0"/>
                                <select id="list-size" name="listSize">
                                    <option value="10" ${searchMemoVO.listSize eq 10 ? 'selected' : ''}>10개</option>
                                    <option value="20" ${searchMemoVO.listSize eq 20 ? 'selected' : ''}>20개</option>
                                    <option value="30" ${searchMemoVO.listSize eq 30 ? 'selected' : ''}>30개</option>
                                    <option value="50" ${searchMemoVO.listSize eq 50 ? 'selected' : ''}>50개</option>
                                    <option value="100" ${searchMemoVO.listSize eq 100 ? 'selected' : ''}>100개</option>
                                </select>

                                <select id="status" name="searchStatus">
                                    <option value="all" ${searchMemoVO.searchStatus eq 'all' ? 'selected' : ''}>전체쪽지</option>
                                    <option value="Y" ${searchMemoVO.searchStatus eq 'Y' ? 'selected' : ''}>읽은 쪽지</option>
                                    <option value="N" ${searchMemoVO.searchStatus eq 'N' ? 'selected' : ''}>안 읽은 쪽지</option>
                                </select>

                                <select id="search-type" name="searchType">
                                    <option value="" selected disabled hidden>검색 옵션</option>
                                    <option value="rcvId" ${searchMemoVO.searchType eq 'rcvId' ? 'selected' : ''}>수신자 사원번호</option>
                                    <option value="empName" ${searchMemoVO.searchType eq 'empName' ? 'selected' : ''}>수신자 이름</option>
                                    <option value="email" ${searchMemoVO.searchType eq 'email' ? 'selected' : ''}>수신자 이메일</option>
                                </select>
                                <input type="text" name="searchKeyword" value="${searchMemoVO.searchKeyword}"/>
                                <button type="button" id="search-btn">검색</button>
                                <button href="javascript:void(0)" id="deleteMassiveMemo">삭제</button>
                            </div>
                        </div> 

                        <ul class="pagination">
                            <c:if test="${searchMemoVO.hasPrevGroup}">
                                <li class="page-item first">
                                    <a class="page-link" href="javascript:search(0);"><img src="/images/chevron-double-left.svg"/></a>
                                </li>
                                <li class="page-item prev">
                                    <a class="page-link" href="javascript:search(${searchMemoVO.prevGroupStartPageNo});"><img src="/images/chevron-left.svg"/></a>
                                </li>
                            </c:if>
                            <c:forEach begin="${searchMemoVO.groupStartPageNo}" end="${searchMemoVO.groupEndPageNo}" step="1" var="p">
                                <li class="${searchMemoVO.pageNo eq p ? 'active' : ''} page-item">
                                    <a class="page-link" href="javascript:search(${p});">${p+1}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${searchMemoVO.hasNextGroup}">
                                <li class="page-item next">
                                    <a class="page-link" href="javascript:search(${searchMemoVO.nextGroupStartPageNo});"><img src="/images/chevron-right.svg"/></a>
                                </li>
                                <li class="page-item last">
                                    <a class="page-link" href="javascript:search(${searchMemoVO.groupCount - 1});"><img src="/images/chevron-double-right.svg"/></a>
                                </li>
                            </c:if>
                        </ul>
                    </form>
                </nav>
            </div>
        <div class="right-memo" id="sent-memo-detail"></div>
            <!-- Paginator 끝 -->
    </div>

        

</div>
</body>
</html>