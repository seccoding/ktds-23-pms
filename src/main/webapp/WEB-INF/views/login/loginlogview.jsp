<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>로그기록 확인 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
</head>
<body>

<div>

</div>

<div>
    <table class="table">
        <thead>
        <tr>
            <th>로그 아이디</th>
            <th>사원번호</th>
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
                        <td>${loginLog.lgnSccDt}</td>
                        <td>${loginLog.lgtDt}</td>
                        <td>${loginLog.delYn}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="5">
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
