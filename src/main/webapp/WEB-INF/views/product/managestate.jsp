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
        <div>대여중인 비품은 ${productState.borrowCnt}건입니다.</div>
        <div class="flex">

        </div>
    </div>
    <div class="grid">
        <div>
            <form id="search-form">
                <div class="right-align">
                    
                    <select id="search-type" name="searchType" >
                        <option value="prdtMngId" ${productVO.searchType eq 'prdtMngId' ? 'selected' : ''}>비품관리ID</option>
                        <option value="productName" ${productVO.searchType eq 'productName' ? 'selected' : ''}>비품명</option>
                        <option value="borrowId" ${productVO.searchType eq 'borrowId' ? 'selected' : ''}>대여자ID</option>
                        
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