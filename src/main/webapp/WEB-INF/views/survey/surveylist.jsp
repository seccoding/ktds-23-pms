<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 목록</title>
<jsp:include page="../commonheader.jsp"></jsp:include>
<jsp:include page="../commonmodal.jsp"></jsp:include>
<script type="text/javascript" src="/js/survey/surveylist.js"></script>
<style>
.search {
    justify-content: center;
}

.search-keyword {
    display: flex;
}

.search-keyword > * {
    padding-right: 0.825rem;
}

.search-category {
    padding-bottom: 11px;
}
table {
    width: 100%; /* 테이블이 페이지 전체 너비를 차지하도록 설정 */
    table-layout: auto; /* 테이블 레이아웃을 고정 */
}

colgroup > col:first-child {
    width: auto; /* 첫 번째 열은 가변적으로 너비 설정 */
}

colgroup > col:not(:first-child) {
    width: 150px; /* 나머지 열은 150px로 고정 */
}

th, td {
    overflow: hidden; /* 내용이 너비를 초과하는 경우 숨김 처리 */
    text-overflow: ellipsis; /* 너무 긴 문자열은 ...로 표시 */
    white-space: nowrap; /* 내용을 한 줄로 표시 */
}
.survey-view, .prj-list {
    cursor: pointer;
}
.prj-list.no-click {
    cursor: default;
}
.table {
    margin-bottom: 1rem;
}
</style>
</head>
<body>
    <input type="hidden" id="is-manager" value="${sessionScope._LOGIN_USER_.mngrYn}">
    <input type="hidden" id="is-pm" value="${isPM}">
    <div>
        <table class="table">
            <colgroup>
                <col style="width: 34%;"> 
                <col style="width: 12%;"> 
                <col style="width: 12%;">
                <col style="width: 12%;">
                <col style="width: 12%;">
                <col style="width: 12%;">
                <col style="width: 6%;">
            </colgroup>
            <thead>
                <tr>
                    <th>프로젝트 명</th>
                    <th>고객사</th>
                    <th>수행부서</th>
                    <th>시작일</th>
                    <th>종료일</th>
                    <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'Y' || isPM}">
                        <th>설문지 생성</th>
                    </c:if>
                    <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'N'}">
                        <th>설문 작성</th>
                    </c:if>
                    <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'Y' || isPM}">
                        <th></th>
                    </c:if>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty surveyList.projectList}">
                        <c:forEach items="${surveyList.projectList}" var="project">
                            <tr class="prj-list" data-prj-id="${project.prjId}" data-srv-sts="${project.srvSts}">     
                                <td>${project.prjName}</td>
                                <td>${project.clntInfo}</td>
                                <td>${project.deptVO.deptName}</td>
                                <td>${project.strtDt}</td>
                                <td>${project.endDt}</td>
                                <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'Y' || isPM}">    
                                    <td class="survey-status survey-view"><c:out value="${empty project.srvSts ? 'N' : project.srvSts}" /></td>
                                </c:if>
                                <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'N'}">
                                    <c:forEach items="${surveyYn}" var="surveyYn">
                                        <c:if test="${surveyYn.prjId == project.prjId}">
                                            <td class="survey-yn" data-survey-yn="${surveyYn.srvYn}">${surveyYn.srvYn}</td>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'Y' || isPM}">
                                    <td><a href="result?prjId=${project.prjId}">설문 결과</a></th>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="7">
                                <div>참여 중인 프로젝트가 없거나 프로젝트의 설문 관리가 불가능한 상태입니다.</div>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
    <div class="search">
        <form id="search-form">
            <div class="search-keyword">
                <div class="search-category">
                    <input type="hidden" id="page-no" name="pageNo" value="0"/>
                    <select id="list-size" name="listSize">
                        <option value="10" ${searchSurveyVO.listSize eq 10 ? 'selected' : ''}>10개</option>
                        <option value="20" ${searchSurveyVO.listSize eq 20 ? 'selected' : ''}>20개</option>
                        <option value="30" ${searchSurveyVO.listSize eq 30 ? 'selected' : ''}>30개</option>
                        <option value="50" ${searchSurveyVO.listSize eq 50 ? 'selected' : ''}>50개</option>
                        <option value="100" ${searchSurveyVO.listSize eq 100 ? 'selected' : ''}>100개</option>
                    </select>

                    <select id="search-type" name="searchType">
                        <option value="" selected disabled hidden>검색 옵션</option>
                        <option value="project" ${searchSurveyVO.searchType eq 'project' ? 'selected' : ''}>프로젝트명
                        </option>
                        <option value="client" ${searchSurveyVO.searchType eq 'client' ? 'selected' : ''}>고객사명</option>
                        <option value="department" ${searchSurveyVO.searchType eq 'department' ? 'selected' : ''}>수행부서명
                        </option>
                    </select>
                </div>
                <div class="search-text">
                    <input type="text" name="searchKeyword" value="${searchSurveyVO.searchKeyword}"/>
                    <button type="button" id="search-btn">검색</button>
                    <button type="button" id="cancel-search-btn">초기화</button>
                </div>
            </div>

            <ul class="pagination">
                <c:if test="${searchSurveyVO.hasPrevGroup}">
                    <li class="page-item first">
                        <a href="javascript:search(0);"><img src="/images/chevron-double-left.svg"/></a></li>
                    <li class="page-item prev">
                        <a href="javascript:search(${searchSurveyVO.prevGroupStartPageNo});"><img src="/images/chevron-left.svg"/></a>
                    </li>
                </c:if>

                <!-- Page 번호를 반복하며 노출한다. -->
                <c:forEach
                        begin="${searchSurveyVO.groupStartPageNo}"
                        end="${searchSurveyVO.groupEndPageNo}"
                        step="1"
                        var="p"
                >
                    <li class="${searchSurveyVO.pageNo eq p ? 'active' : ''} page-item">
                        <a class="page-link" href="javascript:search(${p});">${p+1}</a>
                    </li>
                </c:forEach>

                <c:if test="${searchSurveyVO.hasNextGroup}">
                    <li class="page-item next">
                        <a><img src="/images/chevron-right.svg"/></a>
                    </li>
                    <li class="page-item last">
                        <a href="javascript:search(${searchSurveyVO.pageCount - 1});"
                        ><img src="/images/chevron-double-right.svg"/></a
                        >
                    </li>
                </c:if>
            </ul>
        </form>
    </div>
</body>
</html>