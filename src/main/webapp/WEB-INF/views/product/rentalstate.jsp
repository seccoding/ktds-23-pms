<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비품 대여 현황</title>
<jsp:include page="../commonheader.jsp" />
<style type="text/css">
    div.grid div.right-align {
        text-align: right;
    }
</style>
</head>
<body>
    <jsp:include page="../layout/layout.jsp" />
    <h2>비품 대여 현황</h2>
    <div class="flex">
        <div>대여중인 비품은 ${userRentalState.borrowCnt}건입니다.</div>
        <div class="flex">

        </div>
    </div>
    <div class="grid">
        <div>
            <form id="search-form">
                <input type="hidden" id="page-no" name="pageNo" value="0" />
                <div class="right-align">
                    <div class="check-option">
                        <input type="checkbox" id="product-exist-search" />
                        <label for="product-exist-search"></label>
                        <label for="product-exist-search">재고가 있는 비품만 조회</label>
                    </div>
                    
                    <select id="search-type" name="searchType" >
                        <option value="productId" ${productVO.searchType eq 'productId' ? 'selected' : ''}>비품ID</option>
                        <option value="productName" ${productVO.searchType eq 'productName' ? 'selected' : ''}>비품명</option>
                        <option value="category" ${productVO.searchType eq 'category' ? 'selected' : ''}>카테고리</option>
                        <option value="noSelect" ${productVO.searchType eq 'noSelect' ? 'selected' : ''}>선택 안함</option>
                    </select>
        
                    <input type="text" name="searchKeyword" value="${productVO.searchKeyword}"/>
                    <button type="button" id="search-btn">검색</button>
                </div>
            </form>
        </div>

        <table class="table">
            <thead>
                <tr>
                    <th>비품명</th>
                    <th>비품관리 ID</th>
                    <th>대여일</th>
                    <th>반납일</th>
                    <th>반납신청</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty userRentalState.borrowList}">
                        <c:forEach items="${userRentalState.borrowList}" var="product">
                            <tr>
                                <td>${product.productVO.prdtName}</td>
                                <td>${product.prdtMngId}</td>
                                <td>${product.brrwDt}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty product.rtnDt}">
                                            ${product.rtnDt}
                                        </c:when>
                                        <c:otherwise>-</c:otherwise>
                                    </c:choose>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty product.rtnDt}">
                                            <button>반납</button>
                                        </c:when>
                                        <c:otherwise>반납완료</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
    
                    </c:when>
                    
                </c:choose>
    
            </tbody>
        </table>
    </div>
    <jsp:include page="../layout/layout_close.jsp" />
</body>
</html>