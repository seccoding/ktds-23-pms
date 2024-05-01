<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>화면접근기록 확인 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/login/visitedlogview.js"></script>
    <style>
        .ellipsis {
            overflow: hidden;
            white-space: normal;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            word-break: keep-all;
        }
    </style>
</head>
<body>

<div>

</div>

<div class="grid">
    <h2>화면접근기록 관리</h2>
    <form id="search-form">
        <input type="hidden" id="page-no" name="pageNo" value="0"/>
        <div>
            <select id="search-type" name="searchType">
                <c:if test="${sessionScope._LOGIN_USER_.admnCode eq '301'}">
                    <option value="employeeVO.empName" ${visitedVO.searchType eq 'employeeVO.empName' ? 'selected' : ''}>사원 이름</option>
                    <option value="empId" ${visitedVO.searchType eq 'empId' ? 'selected' : ''}>사원번호</option>
                </c:if>
                <option value="accsUrl" ${visitedVO.searchType eq 'accsUrl' ? 'selected' : ''}>방문한 URL</option>
                <option value="accsDt" ${visitedVO.searchType eq 'accsDt' ? 'selected' : ''}>방문한 시간</option>
            </select>
            <select id="log-type" name="visitedType" >
                <option value="today" ${visitedVO.visitedType eq 'today' ? 'selected' : ''}>오늘</option>
                <option value="oneMonth" ${visitedVO.visitedType eq 'oneMonth' ? 'selected' : ''}>1개월</option>
                <option value="twoMonth" ${visitedVO.visitedType eq 'twoMonth' ? 'selected' : ''}>2개월</option>
                <option value="thrMonth" ${visitedVO.visitedType eq 'thrMonth' ? 'selected' : ''}>3개월</option>
            </select>
            <input type="text" name="searchKeyword" value="${visitedVO.searchKeyword}"/>
            <button type="button" id="search-btn">검색</button>
        </div>
    </form>
    <table class="table">
        <thead>
        <tr>
            <th>화면접근기록 아이디</th>
            <th>사원번호</th>
            <th>사원이름</th>
            <th style="max-width: 630px;">접근한 화면의 URL</th>
            <th>접근 시간</th>
            <th>삭제 여부</th>
        </tr>
        </thead>
        <tbody id="table">
        <c:choose>
            <c:when test="${not empty visitedList.visitedList}">
                <c:forEach items="${visitedList.visitedList}" var="visited">
                    <tr>
                        <td>${visited.accsId}</td>
                        <td>${visited.empId}</td>
                        <td>${visited.employeeVO.empName}</td>
                        <td class="ellipsis">${visited.accsUrl}</td>
                        <td>${visited.accsDt}</td>
                        <td>${visited.delYn}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="6">
                        <span>화면 접근 기록이 없습니다.</span>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
</body>
</html>
