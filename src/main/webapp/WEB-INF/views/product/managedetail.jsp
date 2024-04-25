<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비품 상세 목록</title>
<jsp:include page="../commonheader.jsp" />
<jsp:include page="../commonmodal.jsp" />
<style type="text/css">
    div.grid div.right-align {
        text-align: right;
    }
    .modify-modal{
        top: 10rem;
        left: 40%;
       
    }
    .modal-grid{
        display: grid;
        grid-template-columns: 6rem 1fr;
        grid-template-rows: repeat(6, 3rem);
        gap: .3rem;
        margin: 4rem 2rem;
    }
</style>

<script type="text/javascript" src="/js/product/managedetail.js"></script>
</head>
<body>
    <div class="body" data-paramid="${productVO.prdtId}">
        <dialog class="modify-modal">
            <form>
                <div class="modal-grid">
                    <p>비품관리ID</p>
                    <p class="manage-id"></p>
                    <p>비품명</p>
                    <p class="product-name"></p>
                    <p>구매가격</p>
                    <input type="number" class="price" min="0"/>
                    <p>구매일</p>
                    <input type="date" class="buy-day" />
                    <p>분실상태</p>
                    <select class="select">
                        <option value="O">O</option>
                        <option value="X">X</option>
                    </select>
                    <p>분실신고일</p>
                    <input type="date" class="lost-day" />
                </div>
                <input type="button" value="취소" id="cancel-btn"/>
                <input type="button" value="수정" id="modify-btn"/>
            </form>
        </dialog>
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
                <tbody>
                    <c:choose>
                        <c:when test="${not empty productManagementList.productManagementList}">
                            <c:forEach items="${productManagementList.productManagementList}" var="product">
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
                                        <button class="modify" data-product="${product.prdtMngId}" data-name="${product.productVO.prdtName}">수정</button>
                                        <button class="remove" data-product="${product.prdtMngId}">삭제</button>
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
                <option value="10" ${searchProductVO.listSize eq 10 ? 'selected' : ''}>10개</option>
                <option value="20" ${searchProductVO.listSize eq 20 ? 'selected' : ''}>20개</option>
                <option value="30" ${searchProductVO.listSize eq 30 ? 'selected' : ''}>30개</option>
                <option value="50" ${searchProductVO.listSize eq 50 ? 'selected' : ''}>50개</option>
                <option value="100" ${searchProductVO.listSize eq 100 ? 'selected' : ''}>100개</option>
            </select>

            <select id="search-type" name="searchType" >
                <option value="productManagementId" ${searchProductVO.searchType eq 'productManagementId' ? 'selected' : ''}>비품관리ID</option>
                <option value="productName" ${searchProductVO.searchType eq 'productName' ? 'selected' : ''}>비품명</option>
            </select>

            <input type="text" name="searchKeyword" value="${searchProductVO.searchKeyword}"/>
            <button type="button" id="search-btn">검색</button>

            <ul class="pagination">
                <c:if test="${searchProductVO.hasPrevGroup}">
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
                        begin="${searchProductVO.groupStartPageNo}"
                        end="${searchProductVO.groupEndPageNo}"
                        step="1"
                        var="p"
                >
                    <li class="${searchProductVO.pageNo eq p ? 'active' : ''} page-item">
                        <a class="page-link" href="javascript:search(${p});">${p+1}</a>
                    </li>
                </c:forEach>

                <c:if test="${searchProductVO.hasNextGroup}">
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
    </div>
</body>
</html>