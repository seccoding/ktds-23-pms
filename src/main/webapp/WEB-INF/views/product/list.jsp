<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비품 목록</title>
<jsp:include page="../commonheader.jsp" />
<style type="text/css">
    div.grid div.right-align {
        text-align: right;
    }
    .btn-group {
        margin: 0 0 1rem 2rem;;
    }
</style>
<script type="text/javascript" src="/js/product/list.js"></script>
</head>
<body>
    <h2>비품목록</h2>
    <div class="flex">
        <div>총 ${productList.productCnt}건의 비품이 조회되었습니다.</div>
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

        <div class="btn-group">
            <button type="button" class="apply-product">신청</button>
        </div>

        <table class="table">
            <thead>
                <tr>
                    <th>비품ID</th>
                    <th>비품명</th>
                    <th>카테고리</th>
                    <th>소모품여부</th>
                    <th>재고</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty productList.productList}">
                        <c:forEach items="${productList.productList}" var="product">
                            <tr>
                                <td>${product.prdtId}</td>
                                <td>${product.prdtName}</td>
                                <td>${product.prdtCtgr}</td>
                                <td>${product.onceYn}</td>
                                <td>${product.curStr}</td>
                            </tr>
                        </c:forEach>
    
                    </c:when>
                    
                </c:choose>
    
            </tbody>
        </table>
    </div>
</body>
</html>