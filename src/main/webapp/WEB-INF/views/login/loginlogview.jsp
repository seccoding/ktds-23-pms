<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>로그기록 확인 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
    <script type="text/javascript" src="/js/login/loginlogview.js"></script>
    <style>
        #search-type {
            margin-top: 5px;
            margin-bottom: 5px;
        }
    </style>
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
                <c:if test="${sessionScope._LOGIN_USER_.admnCode eq '301'}">
                    <option value="employeeVO.empName" ${loginLogVO.searchType eq 'employeeVO.empName' ? 'selected' : ''}>사원 이름</option>
                    <option value="empId" ${loginLogVO.searchType eq 'empId' ? 'selected' : ''}>사원번호</option>
                </c:if>
                <option value="lgnSccDt" ${loginLogVO.searchType eq 'lgnSccDt' ? 'selected' : ''}>로그인 시간</option>
                <option value="lgtDt" ${loginLogVO.searchType eq 'lgtDt' ? 'selected' : ''}>로그아웃 시간</option>
            </select>
            <select id="log-type" name="loginType" >
                <option value="today" ${loginLogVO.loginType eq 'today' ? 'selected' : ''}>오늘</option>
                <option value="oneMonth" ${loginLogVO.loginType eq 'oneMonth' ? 'selected' : ''}>1개월</option>
                <option value="twoMonth" ${loginLogVO.loginType eq 'twoMonth' ? 'selected' : ''}>2개월</option>
                <option value="thrMonth" ${loginLogVO.loginType eq 'thrMonth' ? 'selected' : ''}>3개월</option>
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
