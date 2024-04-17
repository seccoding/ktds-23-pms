<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비품 추가</title>
    <jsp:include page="../commonheader.jsp" />
    <script type="text/javascript" src="/js/product/manageadd.js" ></script>
    <style type="text/css">
        .form-grid {
            display: grid;
            grid-template-columns: repeat(2, 15rem);
            grid-template-rows: repeat(3, 4rem);
            position: relative;
            text-align: left;
            align-items: center;
            justify-content: center;
            border: 1px solid #333;
            border-radius: 1rem;
            margin-bottom: 3rem;
        }
        .form-grid input {
            width: 8rem;
        }
        .plus-minus {
            position: absolute;
            right: 4rem;
            bottom: 10rem;
        }
        .plus, .minus {
            width: 30px;
            height: 30px;
        }
        .btn-group {
            text-align: right;
        }
    </style>
</head>
<body>
    <jsp:include page="../layout/layout.jsp" />
        <h2>비품 추가 정보</h2>
        <form action="/product/manage/add" method="post" enctype="multipart/form-data">
            <div class="form-grid">
                <div>
                    <label for="product-name">비품명</label>
                    <input type="text" id="product-name">
                </div>

                <div>
                    <label for="product-category">카테고리</label>
                    <select id="search-type" name="searchType" >
                        <option value="productId" >컴퓨터기기</option>
                        <option value="productId" >사무용품</option>
                        <option value="productId" >프린터용품</option>
                    </select>
                </div>

                <div>
                    <label for="product-type">소모품 분류</label>
                    <select id="search-type" name="searchType" >
                        <option value="productId" >소모품</option>
                        <option value="productId" >비소모품</option>
                    </select>
                </div>

                <div>
                    <label for="quantity">재고수</label>
                    <input type="number" id="quantity">
                </div>

                <div>
                    <label for="first-buy-date">초기 구매일</label>
                    <input type="date" id="first-buy-date">
                </div>

                <div>
                    <label for="first-price">초기 가격</label>
                    <input type="text" id="first-price">
                </div>

            </div>
        </form>

        <div class="plus-minus">
            <button class="plus">+</button>
            <button class="minus">-</button>
        </div>

        <div class="btn-group">
            <button class="product-add">추가</button>
            <button class="product-cancel">취소</button>
        </div>
    <jsp:include page="../layout/layout_close.jsp" />
</body>
</html>