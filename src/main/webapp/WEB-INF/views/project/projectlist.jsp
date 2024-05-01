<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>프로젝트 리스트 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/project/projectlist.js"></script>
    <script type="text/javascript" src="/js/modal.js"></script>
    <link rel="stylesheet" href="/css/project/projectlist.css"/>
</head>
<body>
<div>

    <div>
        총 ${projectList.projectCount} 건의 프로젝트가 검색되었습니다.
    </div>

    <div>
        <table class="table">
            <colgroup>
                <col width="30%"/>
                <col width="14%"/>
                <col width="14%"/>
                <col width="14%"/>
                <col width="14%"/>
                <col width="14%"/>
            </colgroup>
            <thead>
            <tr>
                <th>프로젝트</th>
                <th>고객사</th>
                <th>수행부서</th>
                <th>프로젝트 상태</th>
                <th>시작일</th>
                <th>종료일</th>
            </tr>
            </thead>
            <tbody>
            <!-- jstl > choose when otherwise / if ~ elif ~ else-->
            <c:choose>
                <%-- projectList 내용이 존재한다면 --%>
                <c:when test="${not empty projectList.projectList}">
                    <%-- 내용을 반복해서 보여줌 --%>
                    <c:forEach items="${projectList.projectList}" var="project">
                        <tr class="project-row" data-project-id="${project.prjId}">
                            <td>
                                <a href="/project/view?prjId=${project.prjId}">${project.prjName}</a>
                            </td>
                            <td>${project.clntInfo}</td>
                            <td>${project.deptVO.deptName}</td>
                            <td>${project.prjStsCode.cmcdName}</td>
                            <td>${project.strtDt}</td>
                            <td>${project.endDt}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <%-- projectList의 내용이 존재하지 않는다면 --%>
                <c:otherwise>
                    <tr>
                        <td colspan="6">
                            <a href="/project/write">
                                등록된 프로젝트가 없습니다.
                            </a>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>

    <!-- Paginator 시작 -->
    <div class="search">
        <form id="search-form">
            <div class="search-keyword">
                <div class="search-category">
                    <input type="hidden" id="page-no" name="pageNo" value="0"/>
                    <select id="list-size" name="listSize">
                        <option value="10" ${searchProjectVO.listSize eq 10 ? 'selected' : ''}>10개</option>
                        <option value="20" ${searchProjectVO.listSize eq 20 ? 'selected' : ''}>20개</option>
                        <option value="30" ${searchProjectVO.listSize eq 30 ? 'selected' : ''}>30개</option>
                        <option value="50" ${searchProjectVO.listSize eq 50 ? 'selected' : ''}>50개</option>
                        <option value="100" ${searchProjectVO.listSize eq 100 ? 'selected' : ''}>100개</option>
                    </select>

                    <select id="status" name="searchStatus">
                        <option value="all" ${searchProjectVO.searchStatus eq 'all' ? 'selected' : ''}>전체상태</option>
                        <c:forEach items="${commonCodeList}" var="code">
                            <option value="${code.cmcdId}" ${searchProjectVO.searchStatus eq code.cmcdId ? 'selected' : ''}>${code.cmcdName}</option>
                        </c:forEach>
                    </select>

                    <select id="search-type" name="searchType">
                        <option value="" selected disabled hidden>검색 옵션</option>
                        <option value="project" ${searchProjectVO.searchType eq 'project' ? 'selected' : ''}>프로젝트명
                        </option>
                        <option value="client" ${searchProjectVO.searchType eq 'client' ? 'selected' : ''}>고객사명</option>
                        <option value="department" ${searchProjectVO.searchType eq 'department' ? 'selected' : ''}>수행부서명
                        </option>
                    </select>
                </div>
                <div class="search-text">
                    <input type="text" name="searchKeyword" value="${searchProjectVO.searchKeyword}"/>
                    <button type="button" id="search-btn">검색</button>
                    <button type="button" id="cancel-search-btn">초기화</button>
                </div>
            </div>

            <c:if test="${sessionScope._LOGIN_USER_.admnCode eq '301'}">
                <button class="submit-btn btn-group" type="button" onclick="location.href='/project/write'">프로젝트 등록
                </button>
            </c:if>

            <ul class="pagination">
                <c:if test="${searchProjectVO.hasPrevGroup}">
                    <li class="page-item first">
                        <a href="javascript:search(0);"><img src="/images/chevron-double-left.svg"/></a></li>
                    <li class="page-item prev">
                        <a
                                href="javascript:search(${searchProjectVO.prevGroupStartPageNo});"
                        ><img src="/images/chevron-left.svg"/></a
                        >
                    </li>
                </c:if>

                <!-- Page 번호를 반복하며 노출한다. -->
                <c:forEach
                        begin="${searchProjectVO.groupStartPageNo}"
                        end="${searchProjectVO.groupEndPageNo}"
                        step="1"
                        var="p"
                >
                    <li class="${searchProjectVO.pageNo eq p ? 'active' : ''} page-item">
                        <a class="page-link" href="javascript:search(${p});">${p+1}</a>
                    </li>
                </c:forEach>

                <c:if test="${searchProjectVO.hasNextGroup}">
                    <li class="page-item next">
                        <a
                                href="javascript:search(${searchProjectVO.nextGroupStartPageNo});"
                        ><img src="/images/chevron-right.svg"/></a
                        >
                    </li>
                    <li class="page-item last">
                        <a href="javascript:search(${searchProjectVO.pageCount - 1});"
                        ><img src="/images/chevron-double-right.svg"/></a
                        >
                    </li>
                </c:if>
            </ul>
        </form>
    </div>

    <!-- Paginator 끝 -->
</div>
</body>

</html>