<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비품 상세 목록</title>
<jsp:include page="../commonheader.jsp" />
</head>
<body>
<jsp:include page="../layout/layout.jsp" />
    <h2>비품 상세 목록</h2>
    <div class="flex">
        <div>총 ${productManagementList.productManagementCnt}건의 비품이 조회되었습니다.</div>
        <div class="flex">

        </div>
    </div>
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
        </table>
        <tbody>
            <c:choose>
                <c:when test="${not empty productManagementList.productManagementList}">
                    <c:foreach items="${productManagementList.productManagementList}" var="product">
                        <tr>
                            <td>${product.prdtMngId}</td>
                            <td>${product.prdtName}</td>
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
                            <td>
                                <button>수정</button>
                                <button>삭제</button>
                            </td>
                        </tr>
                    </c:foreach>

                </c:when>
                
            </c:choose>

        </tbody>
    </div>
<jsp:include page="../layout/layout_close.jsp" />
</body>
</html>