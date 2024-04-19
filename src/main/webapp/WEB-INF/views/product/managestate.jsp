<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비품 대여 현황(관리자)</title>
<jsp:include page="../commonheader.jsp" />
<style type="text/css">
    div.grid div.right-align {
        text-align: right;
    }
</style>
<script type="text/javascript" src="/js/product/managestate.js"></script>
</head>
<body>
    <h2>비품 대여 현황(관리자)</h2>
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
                        <option value="prdtMngId" ${productVO.searchType eq 'prdtMngId' ? 'selected' : ''}>비품관리ID</option>
                        <option value="productName" ${productVO.searchType eq 'productName' ? 'selected' : ''}>비품명</option>
                        <option value="borrowId" ${productVO.searchType eq 'borrowId' ? 'selected' : ''}>대여자ID</option>
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
                    <th>비품관리 ID</th>
                    <th>비품명</th>
                    <th>대여자 ID</th>
                    <th>대여일</th>
                    <th>반납일</th>
                    <th>대여상태</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty productState.borrowList}">
                        <c:forEach items="${productState.borrowList}" var="product">
                            <tr>
                                <td>${product.prdtMngId}</td>
                                <td>${product.productVO.prdtName}</td>
                                <td>${product.brrwId}</td>
                                <td>${product.brrwDt}</td>
                                <c:choose>
                                    <c:when test="${not empty product.rtnDt}">
                                        <td>${product.rtnDt}</td>
                                        <td>반납완료</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>-</td>
                                        <td>대여중</td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </tbody>
        </table>
    </div>
</body>
</html>