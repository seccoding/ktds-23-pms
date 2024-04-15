<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이슈 목록 페이지</title>
<jsp:include page="../commonheader.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../layout/layout.jsp"></jsp:include>
<div>
	총 ${issueList.isCnt}건의 게시글이 검색되었습니다.
</div>
<table>
    <thead>
        <tr>
            <th>프로젝트명</th>
            <th>요구사항ID</th>
            <th>제목</th>
            <th>등록자</th>
            <th>등록일</th>
            <th>이슈상태</th>
            <th>조회수</th>
            <th>난이도</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${not empty issueList.issueList}">
                <c:forEach items="${issueList.issueList}" var="issue">
                    <tr>
                        <td></td>
                        <td>${issue.rqmId}</td>
                        <td>${issue.isTtl}</td>
                        <td>${issue.crtrId}</td>
                        <td>${issue.crtDt}</td>
                        <td>${issue.isSts}</td>
                        <td>${issue.isCnt}</td>
                        <td>${issue.isLv}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="8">
                      <a href="/issue/write">
                        등록된 이슈가 없습니다.
                      </a>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
        
    </tbody>
</table>
</body>
</html>