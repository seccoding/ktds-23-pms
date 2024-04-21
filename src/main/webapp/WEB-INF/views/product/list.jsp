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
<script>
    $().ready( function () {
        $("#product-exist-search").change(function(){
            var checked = $(this).is(":checked");
            if (checked) {
                $("table.table tbody tr").each(function(){
                    var stockQuantity = parseInt($(this).find("td:eq(4)").text());
                    if (stockQuantity < 1) {
                        $(this).hide();
                    } else {
                        $(this).show();
                    }
                });
            } else {
                $("table.table tbody tr").show();
            }
        });
    });
</script>
</head>
<body>
    <h2>비품목록</h2>
    <div class="flex">
        <div>총 ${productList.productCnt}건의 비품이 조회되었습니다.</div>
    </div>
    <div class="grid">
        <div>
            <form id="search-form">
                <div class="right-align">
                    <div class="check-option">
                        <input type="checkbox" id="product-exist-search" 
                               name="existed-product" value="existed-product-checked"/>
                        <label for="product-exist-search"></label>
                        <label for="product-exist-search">재고가 있는 비품만 조회</label>
                    </div>
                    
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
                                <td class="current-quantity">${product.curStr}</td>
                            </tr>
                        </c:forEach>
    
                    </c:when>
                    
                </c:choose>
    
            </tbody>
        </table>

        <!-- Paginator 시작 -->
        <div class="paginator-bar">
            <input type="hidden" id="page-no" name="pageNo" value="0" />
            <select id="list-size" name="listSize">
                <!-- listSize의 값이 option 값과 같다면 selected 옵션 주기 -->
                <option value="10" ${productVO.listSize eq 10 ? 'selected' : ''}>10개</option>
                <option value="20" ${productVO.listSize eq 20 ? 'selected' : ''}>20개</option>
                <option value="30" ${productVO.listSize eq 30 ? 'selected' : ''}>30개</option>
                <option value="50" ${productVO.listSize eq 50 ? 'selected' : ''}>50개</option>
                <option value="100" ${productVO.listSize eq 100 ? 'selected' : ''}>100개</option>
            </select>

            <ul class="page-nav">
            <c:if test="${productVO.hasPrevGroup}">
                <!-- javascript에 있는 search 함수를 실행해라. 0을 전달 -->
                <li><a href="javascript:search(0);">처음</a></li>
                <li>
                <a
                    href="javascript:search(${productVO.prevGroupStartPageNo});"
                    >이전</a
                >
                </li>
            </c:if>
            <!-- Page 번호를 반복하며 노출한다. -->
            <c:forEach
                begin="${productVO.groupStartPageNo}"
                end="${productVO.groupEndPageNo}"
                step="1"
                var="p"
            >
                <li class="${productVO.pageNo eq p ? 'active' : ''}">
                <a href="javascript:search(${p});">${p+1}</a>
                </li>
            </c:forEach>

            <c:if test="${productVO.hasNextGroup}">
                <li>
                <a
                    href="javascript:search(${productVO.nextGroupStartPageNo});"
                    >다음</a
                >
                </li>
                <li>
                <a href="javascript:search(${productVO.pageCount - 1});"
                    >마지막</a
                >
                </li>
            </c:if>
            </ul>
        </div>
        <!-- Paginator 끝 -->
    </div>
</body>
</html>