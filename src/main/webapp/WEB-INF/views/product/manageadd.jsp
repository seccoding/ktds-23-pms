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
            text-align: center;
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
            bottom: 10rem;
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
    <h2>비품 추가 정보</h2>
    <div class="form-group">
        <form action="/product/manage/add" method="post" enctype="multipart/form-data">
            <hr />
            <div class="form-grid">
                <div>
                    <label for="prdtName">비품명</label>
                    <input type="text" id="prdtName" name="prdtName"/>
                </div>

                <div>
                    <label for="prdtCtgr">카테고리</label>
                    <select id="prdtCtgr" name="prdtCtgr" >
                        <option value="prdtCtgr" >컴퓨터기기</option>
                        <option value="prdtCtgr" >사무용품</option>
                        <option value="prdtCtgr" >프린터용품</option>
                    </select>
                </div>

                <div>
                    <label for="onceYn">소모품 분류</label>
                    <select id="onceYn" name="onceYn" >
                        <option value="onceYn" >소모품</option>
                        <option value="onceYn" >비소모품</option>
                    </select>
                </div>

                <div>
                    <label for="curStr">재고수</label>
                    <input type="number" id="curStr" name="curStr"/>
                </div>

                <div>
                    <label for="buyDt">구매일</label>
                    <input type="date" id="buyDt" name="buyDt"/>
                </div>

                <div>
                    <label for="prdtPrice">가격</label>
                    <input type="text" id="prdtPrice" name="prdtPrice"/>
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
        <button class="product-add">추가</button>
        <button class="product-cancel">취소</button>
    </div>
</body>
</html>