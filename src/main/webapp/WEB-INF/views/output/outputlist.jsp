<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>산출물관리 리스트 페이지</title>
<jsp:include page="../commonheader.jsp"></jsp:include>
</head>
<body>

    <div>${outputList.listCnt}</div>
	<table class="table">
        
        <thead>
            <tr>
                <th>프로젝트</th>
                <th>산출물 제목</th>
                <th>산출물 종류</th>
                <th>버전</th>
                <th>파일명 내용</th>
                <th>등록일</th>
                <th>수정일</th>
              </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty outputList}">
                
                <c:forEach var="output" items="${outputList.outputList}">
                    <tr> 
                        <td>${output.project.prjName}</td> 
                        <td>${output.outTtl}</td>   
                        <td>${output.outTypeVO.cmcdName}</td>   
                        <td>${output.outVer}</td>   
                        <td>${output.outFile}</td>   
                        <td>${output.crtDt}</td>   
                        <td>${output.mdfDt}</td>   
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
	<button><a href="/output/write">신규</a></button>
</body>
</html>