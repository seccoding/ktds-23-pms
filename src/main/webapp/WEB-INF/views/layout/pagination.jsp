<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
     <!-- 
     사용법
     0. 밑에 코드 전체 긁어서 복붙
     1. <nav aria-label="Page navigation"></nav> 추가
     2. <ul class="pagination"></ul> 똑같이 추가
     3. <c:if test="${searchApproval.hasPrevGroup}"></c:if> 자신 searchVO로 변경 
     -->
    <nav aria-label="Page navigation">
        <form id="search-form">
            <div class="search-keyword">
            <!-- searchKeyword 검색 박스 부분 각자 알아서! -->
            </div>
            <ul class="pagination">
                <c:if test="${searchApproval.hasPrevGroup}">
                    <li class="page-item first">
                        <a class="page-link" href="javascript:search(0);"><img src="/images/chevron-double-left.svg"/></a>
                    </li>
                    <li class="page-item prev">
                        <a class="page-link" href="javascript:search(${searchApproval.prevGroupStartPageNo});"><img src="/images/chevron-left.svg"/></a>
                    </li>
                </c:if>
                <c:forEach begin="${searchApproval.groupStartPageNo}" end="${searchApproval.groupEndPageNo}" step="1" var="p">
                    <li class="${searchApproval.pageNo eq p ? 'active' : ''} page-item">
                        <a class="page-link" href="javascript:search(${p});">${p+1}</a>
                    </li>
                </c:forEach>
                <c:if test="${searchApproval.hasNextGroup}">
                    <li class="page-item next">
                        <a class="page-link" href="javascript:search(${searchApproval.nextGroupStartPageNo});"><img src="/images/chevron-right.svg"/></a>
                    </li>
                    <li class="page-item last">
                        <a class="page-link" href="javascript:search(${searchApproval.groupCount - 1});"><img src="/images/chevron-double-right.svg"/></a>
                    </li>
                </c:if>
            </ul>
        </form>
    </nav>
</body>
</html>

