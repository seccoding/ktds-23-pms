<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>화면접근기록 확인 페이지</title>
    <jsp:include page="../commonheader.jsp"></jsp:include>
</head>
<body>

<div>

</div>

<div>
    <table class="table">
        <thead>
        <tr>
            <th>화면접근기록 아이디</th>
            <th>사원번호</th>
            <th>접근한 화면의 URL</th>
            <th>접근 시간</th>
            <th>삭제 여부</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty visitedList.visitedList}">
                <c:forEach items="${visitedList.visitedList}" var="visited">
                    <tr>
                        <td>${visited.accsId}</td>
                        <td>${visited.empId}</td>
                        <td>${visited.accsUrl}</td>
                        <td>${visited.accsDt}</td>
                        <td>${visited.delYn}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="5">
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
