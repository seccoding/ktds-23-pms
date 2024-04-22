<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비품 대여 현황</title>
<jsp:include page="../commonheader.jsp" />
<script type="text/javascript" src="/js/product/rentalstate.js"></script>
<style>
    .flex{
        display: flex;
        justify-content: flex-end;
    }
    .flex > button{
        width: auto;
        margin: 0 0.5rem;
        padding: 0.2rem 0.4rem;
    }
    div.grid div.right-align {
        text-align: right;
    }
    
    .disabled-check {
        width: 1.2rem;
        height: 1.2rem;
        border: 1px solid #000;
        /* background-color: var(--secondary); */
        display: inline-block;
        vertical-align: middle;
        border-radius: 0.3rem;
        user-select: none; /* 드래그 금지 */
        margin: 0.3rem;
        font-size: 1rem;
        position: relative;
        top: 3px;

    }
    input[type="checkbox"] {
        display: inline-block;
    }
</style>
</head>
<body>
    <h2>비품 대여 현황</h2>
    <div class="flex">
        <div>대여중인 비품은 ${userRentalState.borrowCnt}건입니다.</div>
    </div>
    <div class="grid">
        <div>
            <form id="search-form">
                <div class="right-align">
                    
                    <select id="search-type" name="searchType" >
                        <option value="productId" ${productVO.searchType eq 'productId' ? 'selected' : ''}>비품ID</option>
                        <option value="productName" ${productVO.searchType eq 'productName' ? 'selected' : ''}>비품명</option>
                        <option value="category" ${productVO.searchType eq 'category' ? 'selected' : ''}>카테고리</option>
                    </select>
        
                    <input type="text" name="searchKeyword" value="${productVO.searchKeyword}"/>
                    <button type="button" id="search-btn">검색</button>
                </div>
            </form>
        </div>

        <table class="table">
            <thead>
                <tr>
                    <th>
                        <input type="checkbox" class="checkbox" id="checked-all"/>
                        <!-- <label for="checkbox1"></label>
                        <label for="checkbox1"></label> -->
                    </th>
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
                                <td>
                                    <c:choose>
                                        <c:when test="${product.productVO.onceYn eq 'Y' || not empty product.rtnDt}">
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" class="checkbox target-checkbox" value="${product.brrwHistId}" data-prdtmgid="${product.prdtMngId}"/>
                                            <!-- <label for="checkbox1"></label>
                                            <label for="checkbox1"></label> -->
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${product.productVO.prdtName}</td>
                                <td class="manage-id">${product.prdtMngId}</td>
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
                                        <c:when test="${product.productVO.onceYn eq 'Y'}">
                                            -
                                        </c:when>
                                        <c:when test="${empty product.rtnDt}">
                                            <button class="return-btn" value="${product.brrwHistId}" data-prdtmgid="${product.prdtMngId}">반납</button>
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
    <div class="flex">
        <button>선택항목 변경신청</button>
        <button class="selected-return">선택항목 반납</button>
    </div>
    
</body>
</html>