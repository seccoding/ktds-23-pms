<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비품 상세 목록</title>
<jsp:include page="../commonheader.jsp" />
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
            <div>
                <form id="search-form">
                    <div class="right-align">
                        
                        <select id="search-type" name="searchType" >
                            <option value="productManagementId" ${productManagementVO.searchType eq 'productManagementId' ? 'selected' : ''}>비품관리ID</option>
                            <option value="productName" ${productManagementVO.searchType eq 'productName' ? 'selected' : ''}>비품명</option>
                        </select>
            
                        <input type="text" name="searchKeyword" value="${productManagementVO.searchKeyword}"/>
                        <button type="button" id="search-btn">검색</button>
                    </div>
                </form>
            </div>

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
    </div>
</body>
</html>