<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비품 관리 목록</title>
<jsp:include page="../commonheader.jsp" />
<jsp:include page="../commonmodal.jsp" />
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
        margin-bottom: 1rem;
    }
    #add-modal{
        padding: 3rem;
    }
    table.table > tbody td[colspan] {
        text-align: center;
    }
    .btn-group{
        text-align: right;
    }
    .check-option {
        text-align: right;
        margin: 0 2rem 1rem 0;
    }
</style>
<script type="text/javascript" src="/js/product/managelist.js"></script>
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
    <dialog id="add-modal" class="modal-window">
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
        <div class="btn-group">
            <button type="button" value="추가" id="add-count-btn" class="button">추가</button>
            <button type="button" value="취소" id="cancel-btn" class="button">취소</button>

        </div>

    </dialog>
    
    <h2>비품 관리 목록</h2>
    <div class="flex">
        <div>총 ${productList.productCnt}건의 비품이 조회되었습니다.</div>
    </div>
    <div class="grid">

        <div class="btn-group">
            <button type="button" class="create-product">생성</button>
        </div>
        <div class="check-option">
            <input type="checkbox" id="product-exist-search" 
                   name="existed-product" value="existed-product-checked"/>
            <label for="product-exist-search"></label>
            <label for="product-exist-search">재고가 있는 비품만 조회</label>
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
                    <%-- productList의 내용이 존재한다면 (1개 이상 있다면) --%>
                    <c:when test="${not empty productList.productList}">
                        <%-- 내용을 반복하면서 보여주고 --%>
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
                    <%-- productList의 내용이 존재하지 않는다면 --%>
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

            <ul class="pagination">
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