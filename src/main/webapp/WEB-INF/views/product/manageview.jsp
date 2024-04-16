<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비품관리 정보</title>
<jsp:include page="../commonheader.jsp" />
<script type="text/javascript" src="/js/product/manageview.js" ></script>
<style>
    .main-grid{
        display: grid;
        grid-template-columns: 6rem 1fr 6rem 1fr;
        grid-template-rows: 1fr 1fr 1fr;
        gap: 1rem;
        margin: 2rem 0;
    }
    .bar{
        border-bottom: 2px solid gray;
        display: flex;
        justify-content: flex-end;
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
        margin: 2rem 1rem;
    }
</style>
</head>
<body >
    <jsp:include page="../layout/layout.jsp" />
    <div class="body" data-paramid="${productVO.prdtId}">
        <dialog class="modify-modal">
            <form>
                <div class="modal-grid">
                    <p>비품관리ID</p>
                    <p class="manage-id"></p>
                    <p>비품명</p>
                    <p class="product-name"></p>
                    <p>구매가격</p>
                    <input type="number" class="price"></input>
                    <p>구매일</p>
                    <input type="date" class="buy-day"></input>
                    <p>분실상태</p>
                    <select class="select">
                        <option value="O">O</option>
                        <option value="X">X</option>
                    </select>
                    <p>분실신고일</p>
                    <input type="date" class="lost-day"></input>
                </div>
                <input type="submit" />
            </form>
        </dialog>
        <h2>비품관리 정보</h2>
        <div class="bar">수정저장 아이콘</div>
        <div class="main-grid">
            <div>비품 ID</div>
            <div>${productVO.prdtId}</div>
            <div>재고수</div>
            <div>${productVO.curStr}</div>
            <div>비품명</div>
            <div>${productVO.prdtName}</div>
            <div>소모품 분류</div>
            <div>${productVO.onceYn}</div>
            <div>카테고리</div>
            <div>${productVO.prdtCtgr}</div>
        </div>
        
        <div>${productVO.prdtName}에 대한 상세 정보가 ${productDetailList.productManagementCnt}건 존재합니다.</div>
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
                        <c:when test="${not empty productDetailList.productManagementList}">
                            <c:forEach items="${productDetailList.productManagementList}" var="product">
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
    <jsp:include page="../layout/layout_close.jsp" />
</body>
</html>