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
</head>
<body>
    <jsp:include page="../layout/layout.jsp" />
        <h2>비품 추가 정보</h2>
        <div class="add-product-group" id="add-group">
            <div class="grid">
    
            </div>

        </div>
        <div class="btn-group">
            <button id="cancel">취소</button>
            <button id="add">추가</button>
        </div>
    <jsp:include page="../layout/layout_close.jsp" />
</body>
</html>