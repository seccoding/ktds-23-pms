<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 목록</title>
<jsp:include page="../commonheader.jsp"></jsp:include>
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
</style>
</head>
<body>
    <input type="hidden" id="is-manager" value="${sessionScope._LOGIN_USER_.mngrYn}">
    <input type="hidden" id="is-pm" value="${isPM}">
    <div>
        <table>
            <colgroup>
                <col width="150px" />
                <col width="150px" />
                <col width="150px" />
                <col width="150px" />
                <col width="150px" />
                <col width="150px" />
                <col width="150px" />
            </colgroup>
            <thead>
                <tr>
                    <th>프로젝트 명</th>
                    <th>고객사</th>
                    <th>수행부서</th>
                    <th>상태</th>
                    <th>시작일</th>
                    <th>종료일</th>
                    <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'Y' || isPM}">
                        <th>설문지 생성 여부</th>
                    </c:if>
                    <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'N' && !isPM}">
                        <th>설문 작성 여부</th>
                    </c:if>
                    <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'Y' || isPM}">
                        <th>설문 결과</th>
                    </c:if>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty surveyList.projectList}">
                        <c:forEach items="${surveyList.projectList}" var="survey">
                            <tr class="prj-list" data-prj-id="${survey.prjId}" data-srv-sts="${survey.srvSts}">     
                                <td>${survey.prjName}</td>
                                <td>${survey.clntInfo}</td>
                                <td>${survey.deptVO.deptName}</td>
                                <td>${survey.prjSts}</td>
                                <td>${survey.strtDt}</td>
                                <td>${survey.endDt}</td>
                                <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'Y' || isPM}">
                                    <td><c:out value="${empty survey.srvSts ? 'N' : survey.srvSts}" /></td>
                                </c:if>
                                <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'N' && !isPM}">
                                    <c:forEach items="${teammate.projectTeammateList}" var="teammate">
                                        <td class="survey-yn" data-survey-yn="${teammate.srvYn}">${teammate.srvYn}</td>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${sessionScope._LOGIN_USER_.mngrYn eq 'Y' || isPM}">
                                    <th><a href="survey/result?prjId=${survey.prjId}">설문 결과</a></th>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="8">
                                <div>참여 중인 프로젝트가 없거나 프로젝트의 설문 관리가 불가능한 상태인 것 같습니다.</div>
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

                    <select id="status" name="searchStatus">
                        <option value="all" ${searchProjectVO.searchStatus eq 'all' ? 'selected' : ''}>전체상태</option>
                        <c:forEach items="${commonCodeList}" var="code">
                            <option value="${code.cmcdId}" ${searchProjectVO.searchStatus eq code.cmcdId ? 'selected' : ''}>${code.cmcdName}</option>
                        </c:forEach>
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
                        <a
                                href="javascript:search(${searchSurveyVO.prevGroupStartPageNo});"
                        ><img src="/images/chevron-left.svg"/></a
                        >
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
                        <a
                                href="javascript:search(${searchSurveyVO.nextGroupStartPageNo});"
                        ><img src="/images/chevron-right.svg"/></a
                        >
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