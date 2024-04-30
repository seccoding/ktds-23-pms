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
        margin: 1rem 0 1rem 2rem;
    }
    table.table > tbody td[colspan] {
        text-align: center;
    }
    .check-option {
        text-align: right;
    }
    .flex{
        display: flex;
    }
    .sub-title{
        justify-content: space-between;
    }
    .btn-group{
        flex-direction: column;
        align-items: end;
        margin: 0 1rem;
    }
    .center{
        text-align: center;
    }
    tr{
        border-bottom: 1px solid darkgray;
    }
</style>


<script type="text/javascript" src="/js/product/list.js"></script>
<script>

    function search(pageNo) {
        console.log($("#product-exist").is(":checked")+"!!!")
        var searchForm = $("#search-form");
        $("#page-no").val(pageNo);
        $("#is-check").val($("#product-exist").is(":checked"));
        console.log($("#is-check").val()+"????")
        searchForm.attr("method", "get").attr("action", "/product/list").submit();
    }
</script>
</head>
<body>
    <h2>비품목록</h2>
    <div class="flex sub-title">
        <div>총 ${productList.productCnt}건의 비품이 조회되었습니다.</div>
        <div class="btn-group flex ">
            <button type="button" class="apply-product">신청</button>
            <div class="check-option">
                <input type="checkbox" id="product-exist" 
                name="existed-product" value="existed-product-checked" data-checkstatus="${isCheck}" />
                <label for="product-exist"></label>
                <label for="product-exist">재고가 있는 비품만 조회</label>
            </div>
        </div>
    </div>
    
    <div class="grid">

        <table class="table">
            <thead>
                <tr>
                    <th width="25%">비품ID</th>
                    <th width="30%">비품명</th>
                    <th width="20%">카테고리</th>
                    <th width="12%" class="center">소모품여부</th>
                    <th width="12%" class="center">재고</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                	<%-- productList의 내용이 존재한다면 (1개 이상 있다면) --%>
                    <c:when test="${not empty productList.productList}">
                        <%-- 내용을 반복하면서 보여주고 --%>
                        <c:forEach items="${productList.productList}" var="product">
                            <tr>
                                <td>${product.prdtId}</td>
                                <td>${product.prdtName}</td>
                                <td>${product.prdtCtgr}</td>
                                <td class="center"> 
                                    <c:choose>
                                        <c:when test="${product.onceYn eq 'Y'}">
                                            <span class="badge bg-label-warning">소모품</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-label-info">비소모품</span>

                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td class="current-quantity center">
                                    <c:choose>
                                        <c:when test="${product.curStr eq 0}">
                                            <span style="color: var(--main-color);">${product.curStr}</span>
                                            
                                        </c:when>
                                        <c:otherwise>
                                            ${product.curStr}

                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
    
                    </c:when>
                    <%-- productList의 내용이 존재하지 않는다면 --%>
                    <c:otherwise>
                        <tr>
                            <td colspan="5" class="center">
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
            <input type="hidden" id="is-check" name="isCheck" value="false" />
            <input type="hidden" id="page-no" name="pageNo" value="0"/>
            <select id="list-size" name="listSize">
                <option value="10" ${searchProductVO.listSize eq 10 ? 'selected' : ''}>10개</option>
                <option value="20" ${searchProductVO.listSize eq 20 ? 'selected' : ''}>20개</option>
                <option value="30" ${searchProductVO.listSize eq 30 ? 'selected' : ''}>30개</option>
                <option value="50" ${searchProductVO.listSize eq 50 ? 'selected' : ''}>50개</option>
                <option value="100" ${searchProductVO.listSize eq 100 ? 'selected' : ''}>100개</option>
            </select>

            <select id="search-type" name="searchType" >
                <option value="productId" ${searchProductVO.searchType eq 'productId' ? 'selected' : ''}>비품ID</option>
                <option value="productName" ${searchProductVO.searchType eq 'productName' ? 'selected' : ''}>비품명</option>
                <option value="category" ${searchProductVO.searchType eq 'category' ? 'selected' : ''}>카테고리</option>
            </select>

            <input type="text" name="searchKeyword" value="${searchProductVO.searchKeyword}"/>
            <button type="button" id="search-btn">검색</button>

            <ul class="pagination" style="margin-top: 1rem;">
                <c:if test="${searchProductVO.hasPrevGroup}">
                    <li class="page-item first">
                        <a href="javascript:search(0);"><img src="/images/chevron-double-left.svg"/></a></li>
                    <li class="page-item prev">
                        <a
                                href="javascript:search(${searchProductVO.prevGroupStartPageNo});"
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

    
</body>
</html>