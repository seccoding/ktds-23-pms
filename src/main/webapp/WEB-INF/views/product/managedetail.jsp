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
    /* .modify-modal{
        top: 10rem;
        left: 40%;
       
    } */
    .modal-grid{
        display: grid;
        grid-template-columns: 6rem 1fr;
        grid-template-rows: repeat(6, 1fr);
        gap: 1rem;
        margin: 2rem 1rem;
    }
    table.table > tbody td[colspan] {
        text-align: center;
    }
    .modal-btn-group{
        text-align: right;
    }
    .sub-t{
        margin-bottom: 2rem;
    }
    .center{
        text-align: center;
    }
    .modal-window{
        height: 450px;
        width: 400px;
        top: 10%;
    }
    .bold{
        font-weight: bold;
        margin: auto 0;
    }
</style>

<script type="text/javascript" src="/js/product/managedetail.js"></script>
</head>
<body>
    <div class="body" data-paramid="${productVO.prdtId}">
        <dialog class="modify-modal modal-window" >
            <div class="modal-grid">
                <p class="bold">비품관리ID</p>
                <p style="margin: auto 0;" class="manage-id"></p>
                <p class="bold">비품명</p>
                <p style="margin: auto 0;" class="product-name"></p>
                <p class="bold">구매가격</p>
                <input type="number" class="price" min="0"/>
                <p class="bold">구매일</p>
                <input type="date" class="buy-day" />
                <p class="bold">분실상태</p>
                <select class="select">
                    <option value="O">O</option>
                    <option value="X">X</option>
                </select>
                <p class="bold">분실신고일</p>
                <input type="date" class="lost-day" />
            </div>
            <div class="modal-btn-group">
                <button type="button" value="수정" id="modify-btn">수정</button>
                <button type="button" value="취소" id="cancel-btn">취소</button>
            </div>
        </dialog>
        <h2>비품 상세 목록</h2>
        <div class="flex sub-t">
            <div>총 ${productManagementList.productManagementCnt}건의 비품이 조회되었습니다.</div>
            
        </div>
        <div class="grid">

            <table class="table">
                <thead>
                    <tr>
                        <th width="15%">비품관리 ID</th>
                        <th width="18%">비품명</th>
                        <th width="11%" class="center">가격</th>
                        <th width="10%" class="center">구매일</th>
                        <th width="8%" class="center">대여여부</th>
                        <th width="8%" class="center">분실상태</th>
                        <th width="10%" class="center">분실신고일</th>
                        <th width="20%" class="center">관리</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <%-- productManagementList의 내용이 존재한다면 (1개 이상 있다면) --%>
                        <c:when test="${not empty productManagementList.productManagementList}">
                            <%-- 내용을 반복하면서 보여주고 --%>
                            <c:forEach items="${productManagementList.productManagementList}" var="product">
                                <tr>
                                    <td>${product.prdtMngId}</td>
                                    <td>${product.productVO.prdtName}</td>
                                    <td class="center">${product.prdtPrice}</td>
                                    <td class="center">${product.buyDt}</td>
                                    <c:choose>
                                        <c:when test="${product.brrwYn eq 'Y'}">
                                            <td class="center">O</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="center">X</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${product.lostYn eq 'Y'}">
                                            <td class="center lostdata">O</td>
                                            <td class="center">${product.lostDt}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="center lostdata">-</td>
                                            <td class="center">-</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td class="center">
                                        <button class="modify" 
                                                data-product="${product.prdtMngId}" 
                                                data-name="${product.productVO.prdtName}" 
                                                data-lost-status="${product.lostYn}">수정</button>
                                        <button class="remove" data-product="${product.prdtMngId}">삭제</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <%-- productManagementList의 내용이 존재하지 않는다면 --%>
                        <c:otherwise>
                            <tr>
                                <td colspan="8" class="center">
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
                <option value="productManagementId" ${searchProductVO.searchType eq 'productManagementId' ? 'selected' : ''}>비품관리ID</option>
                <option value="productName" ${searchProductVO.searchType eq 'productName' ? 'selected' : ''}>비품명</option>
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
    </div>
</body>
</html>