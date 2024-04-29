<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비품 신청</title>
    <jsp:include page="../commonheader.jsp" />
    <jsp:include page="../commonmodal.jsp" />

    <script type="text/javascript" src="/js/product/apply.js" ></script>
    <style type="text/css">
        .form-grid {
            display: grid;
            grid-template-columns: repeat(2, 20rem);
            grid-template-rows: repeat(2, 4rem);
            position: relative;
            align-items: center;
            justify-content: center;
            border-radius: 1rem;
        }
        .form-grid input {
            width: 8rem;
        }
        .plus-minus {
            position: absolute;
            right: 4rem;
            bottom: 7.5rem;
        }
        .plus-btn, .minus-btn {
            width: 30px;
            height: 30px;
        }
        .btn-group {
            text-align: right;
            margin: 2rem;
        }
    </style>
</head>
<body>
    <h2>비품 신청 정보</h2>
    <div class="form-group">
        <form action="/product/apply" method="post" enctype="multipart/form-data">
            <hr />
            <div class="form-grid">
                <div class="productName">
                    <label for="select-prdtName">비품명</label>
                    <select name="select-prdtName" id="select-prdtName">
                        <option value="">비품명 선택</option>
                        <c:forEach items="${nameList.productList}" var="name">
                            <option value="${name.prdtName}" >${name.prdtName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="product-quantity">
                    <label for="apply-quantity">신청 수량</label>
                    <input type="number" min="1" id="apply-quantity" value="1"/>
                </div>

                <div class="category-list" data-ctgrList="${categoryList.productList}" data-category="${category.prdtCtgr}">
                    <label for="select-prdtCtgr">카테고리</label>
                    <select name="select-prdtCtgr" id="select-prdtCtgr" disabled>
                        <option value="">카테고리 선택</option>
                        <c:forEach items="${categoryList.productList}" var="category">
                            <option value="${category.prdtCtgr}" >${category.prdtCtgr}</option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <label for="apply-date">신청일</label>
                    <input type="date" id="apply-date" readonly />
                </div>
            </div>
            <hr />
        </form>
    </div>

    <div class="plus-minus">
        <button class="plus-btn">+</button>
        <button class="minus-btn">-</button>
    </div>

    <div class="btn-group">
        <button class="add-button" type="button">추가</button>
        <button class="cancel-button" type="button">취소</button>
    </div>
</body>
</html>