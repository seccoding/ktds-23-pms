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
    table.table > tbody td[colspan] {
        text-align: center;
    }
    .sub-head-flex{
        display: flex;
        justify-content: space-between;
    }
</style>
<script type="text/javascript" src="/js/product/managestate.js"></script>
</head>
<body>
    <h2>비품 대여 현황(관리자)</h2>
    <div class="sub-head-flex">
        <div>대여중인 비품은 ${productState.borrowCnt}건입니다.</div>
        <div>
            <input id="rental-item-list" type="checkbox" />
            <label for="rental-item-list"></label>
            <label for="rental-item-list">대여중인 비품만 보기</label>
        </div>
    </div>
    <div class="grid">

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
            <tbody class="return-state-list">
                <c:choose>
                    <c:when test="${not empty productState.borrowList}">
                        <c:forEach items="${productState.borrowList}" var="product">
                            <tr>
                                <td>${product.prdtMngId}</td>
                                <td>${product.productVO.prdtName}</td>
                                <td>${product.brrwId}</td>
                                <td>${product.brrwDt}</td>
                                <c:choose>
                                    <c:when test="${product.productVO.onceYn eq 'Y'}">
                                        <td>-</td>
                                        <td>-</td>
                                    </c:when>
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
                    <c:otherwise>
                        <tr>
                            <td colspan="6">
                                등록된 비품이 존재하지 않습니다.
                            </td>
                        </tr>
                    </c:otherwise>
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
                <option value="productManagementId" ${searchBorrowVO.searchType eq 'productManagementId' ? 'selected' : ''}>비품관리ID</option>
                <option value="productName" ${searchBorrowVO.searchType eq 'productName' ? 'selected' : ''}>비품명</option>
                <option value="borrowId" ${searchBorrowVO.searchType eq 'borrowId' ? 'selected' : ''}>대여자ID</option>
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
</body>
</html>