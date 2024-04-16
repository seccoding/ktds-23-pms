<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../commonheader.jsp" />
</head>
<body>
    <jsp:include page="../layout/layout.jsp" />
    ${productVO.prdtId}
    <div>${productVO.prdtName}에 대한 상세 정보가 ${productDetailList.productManagementCnt}건 존재합니다.</div>
    <div class="grid">
        <table class="table">
            <thead>
                <tr>
                    <th>비품관리 ID</th>
                    <th>비품명</th>
                    <th>가격</th>
                    <th>구매일</th>
                    <th>대여여부</th>
                    <th>분실상태</th>
                    <th>분실신고일</th>
                    <th>관리</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty productDetailList.productManagementList}">
                        <c:forEach items="${productDetailList.productManagementList}" var="product">
                            <tr>
                                <td>${product.prdtMngId}</td>
                                <td>${product.productVO.prdtName}</td>
                                <td>${product.prdtPrice}</td>
                                <td>${product.buyDt}</td>
                                <c:choose>
                                    <c:when test="${product.brrwYn eq 'Y'}">
                                        <td>O</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>X</td>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${product.lostYn eq 'Y'}">
                                        <td>O</td>
                                        <td>${product.lostDt}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>-</td>
                                        <td>-</td>
                                    </c:otherwise>
                                </c:choose>
                                <td class="flex">
                                    <button>수정</button>
                                    <button>삭제</button>
                                </td>
                            </tr>
                        </c:forEach>

                    </c:when>
                    
                </c:choose>
            </tbody>
        </table>
    <jsp:include page="../layout/layout_close.jsp" />
</body>
</html>