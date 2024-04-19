<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비품 관리 목록</title>
<jsp:include page="../commonheader.jsp" />
<style type="text/css">
    div.grid div.right-align {
        text-align: right;
    }
    .table-item{
        text-align: center;
    }
    .btn-group {
        margin: 0 0 1rem 2rem;;
    }
    .add-grid{
        display: grid;
        grid-template-columns: 5rem 1fr;
        grid-template-rows: repeat(4, 1fr);
        gap: 1rem;
    }
    #add-modal{
        padding: 3rem;
    }
</style>
<script type="text/javascript" src="/js/product/managelist.js"></script>
</head>
<body>
    <dialog id="add-modal">
        <h4>비품 재고 추가</h3>
        <div class="add-grid">
            <p>비품</p>
            <p class="add-product"></p>
            <p>추가수량</p>
            <input type="number" min="1" class="add-count"/>
            <p>구매가격</p>
            <input type="number" min="0" class="buy-price"/>
            <p>구매일</p>
            <input type="date" class="buy-day"/>
        </div>
        <input type="button" value="추가" id="add-count-btn"/>
        <input type="button" value="취소" id="cancel-btn"/>
    </dialog>
    
    <h2>비품 관리 목록</h2>
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
            <button type="button" class="create-product">생성</button>
        </div>

        <table class="table">
            <thead>
                <tr>
                    <th>비품ID</th>
                    <th>비품명</th>
                    <th>카테고리</th>
                    <th>소모품여부</th>
                    <th>재고</th>
                    <th>항목관리</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty productList.productList}">
                        <c:forEach items="${productList.productList}" var="product">
                            <tr >
                                <td class="product-item" data-product="${product.prdtId}">${product.prdtId}</td>
                                <td class="product-item" data-product="${product.prdtId}">${product.prdtName}</td>
                                <td class="product-item" data-product="${product.prdtId}">${product.prdtCtgr}</td>
                                <td class="product-item" data-product="${product.prdtId}">${product.onceYn}</td>
                                <td class="product-item" data-product="${product.prdtId}">${product.curStr}</td>
                                <td>
                                    <button class="add-product-count" data-productname="${product.prdtName}" data-productid="${product.prdtId}">재고추가</button>
                                    <button class="remove-product" data-product="${product.prdtId}">삭제</button>
                                </td>
                            </tr>
                            
                        </c:forEach>
                        
                    </c:when>
                    
                </c:choose>
                
            </tbody>
        </table>
    </div>
</body>
</html>