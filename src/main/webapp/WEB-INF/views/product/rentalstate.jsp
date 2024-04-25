<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비품 대여 현황</title>
<jsp:include page="../commonheader.jsp" />
<jsp:include page="../commonmodal.jsp" />
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
                                <td data-productName="${product.productVO.prdtName}">${product.productVO.prdtName}</td>
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

    <!-- Paginator 시작 -->
    <div>
        <form id="search-form">
            <input type="hidden" id="page-no" name="pageNo" value="0"/>
            <select id="list-size" name="listSize">
                <option value="10" ${searchBorrowVO.listSize eq 10 ? 'selected' : ''}>10개</option>
                <option value="20" ${searchBorrowVO.listSize eq 20 ? 'selected' : ''}>20개</option>
                <option value="30" ${searchBorrowVO.listSize eq 30 ? 'selected' : ''}>30개</option>
                <option value="50" ${searchBorrowVO.listSize eq 50 ? 'selected' : ''}>50개</option>
                <option value="100" ${searchBorrowVO.listSize eq 100 ? 'selected' : ''}>100개</option>
            </select>

            <select id="search-type" name="searchType" >
                <option value="productName" ${searchBorrowVO.searchType eq 'productName' ? 'selected' : ''}>비품명</option>
                <option value="productManagementId" ${searchBorrowVO.searchType eq 'productManagementId' ? 'selected' : ''}>비품관리ID</option>
            </select>

            <input type="text" name="searchKeyword" value="${searchBorrowVO.searchKeyword}"/>
            <button type="button" id="search-btn">검색</button>

            <ul class="pagination">
                <c:if test="${searchBorrowVO.hasPrevGroup}">
                    <li class="page-item first">
                        <a href="javascript:search(0);"><img src="/images/chevron-double-left.svg"/></a></li>
                    <li class="page-item prev">
                        <a
                                href="javascript:search(${searchProjectVO.prevGroupStartPageNo});"
                        ><img src="/images/chevron-left.svg"/></a
                        >
                    </li>
                </c:if>

                <!-- Page 번호를 반복하며 노출한다. -->
                <c:forEach
                        begin="${searchBorrowVO.groupStartPageNo}"
                        end="${searchBorrowVO.groupEndPageNo}"
                        step="1"
                        var="p"
                >
                    <li class="${searchBorrowVO.pageNo eq p ? 'active' : ''} page-item">
                        <a class="page-link" href="javascript:search(${p});">${p+1}</a>
                    </li>
                </c:forEach>

                <c:if test="${searchBorrowVO.hasNextGroup}">
                    <li class="page-item next">
                        <a
                                href="javascript:search(${searchProductVO.nextGroupStartPageNo});"
                        ><img src="/images/chevron-right.svg"/></a
                        >
                    </li>
                    <li class="page-item last">
                        <a href="javascript:search(${searchProductVO.pageCount - 1});"
                        ><img src="/images/chevron-double-right.svg"/></a
                        >
                    </li>
                </c:if>
            </ul>
        </form>
    </div>

    <!-- Paginator 끝 -->


    <div class="flex">
        <button class="selected-change-apply">선택항목 변경신청</button>
        <button class="selected-return">선택항목 반납</button>
    </div>
    
</body>
</html>