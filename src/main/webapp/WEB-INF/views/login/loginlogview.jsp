<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>로그기록 확인 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/login/loginlogview.js"></script>
</head>
<body>

<div>

</div>
<h2>로그 기록 관리</h2>
<div class="grid">
    <form id="search-form">
        <input type="hidden" id="page-no" name="pageNo" value="0" />
        <div>
            <select id="search-type" name="searchType">
                <option value="employeeVO.empName" ${loginLogVO.searchType eq 'employeeVO.empName' ? 'selected' : ''}>사원 이름</option>
                <option value="empId" ${loginLogVO.searchType eq 'empId' ? 'selected' : ''}>사원번호</option>
                <option value="lgnSccDt" ${loginLogVO.searchType eq 'lgnSccDt' ? 'selected' : ''}>로그인 시간</option>
                <option value="lgtDt" ${loginLogVO.searchType eq 'lgtDt' ? 'selected' : ''}>로그아웃 시간</option>
            </select>
            <input type="text" name="searchKeyword" value="${loginLogVO.searchKeyword}" />
            <button type="button" id="search-btn">검색</button>
        </div>
    </form>
    <table class="table">
        <thead>
        <tr>
            <th>로그 아이디</th>
            <th>사원번호</th>
            <th>사원이름</th>
            <th>로그인 시간</th>
            <th>로그아웃 시간</th>
            <th>삭제 여부</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty loginLogList.loginLogList}">
                <c:forEach items="${loginLogList.loginLogList}" var="loginLog">
                    <tr>
                        <td>${loginLog.logId}</td>
                        <td>${loginLog.empId}</td>
                        <td>${loginLog.employeeVO.empName}</td>
                        <td>${loginLog.lgnSccDt}</td>
                        <td>${loginLog.lgtDt}</td>
                        <td>${loginLog.delYn}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="6">
                        <span>로그 기록이 없습니다.</span>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</div>
</body>
</html>
