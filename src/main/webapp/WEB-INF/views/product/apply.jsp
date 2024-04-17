<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비품 신청</title>
    <jsp:include page="../commonheader.jsp" />
    <script type="text/javascript" src="/js/product/apply.js" ></script>
    <style type="text/css">
        .form-grid {
            display: grid;
            grid-template-columns: repeat(2, 15rem);
            grid-template-rows: repeat(2, 4rem);
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
    <jsp:include page="../layout/layout.jsp" />
        <h2>비품 신청 정보</h2>
        <div class="form-group">
            <form action="/product/apply" method="post" enctype="multipart/form-data">
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
                        <label for="curStr">신청 수량</label>
                        <input type="number" id="curStr" name="curStr"/>
                    </div>
    
                    <div>
                        <label for="first-apply-date">신청일</label>
                        <input type="date" id="first-apply-date" />
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
    <jsp:include page="../layout/layout_close.jsp" />
</body>
</html>