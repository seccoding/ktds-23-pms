$().ready(function(){
    
    $(".product-add").on("click", function(){
        $.post("/ajax/product/manage/add", function(res){
            
        })
    });

    $(".product-cancel").on("click", function() {
        var reConfirm = confirm("취소하시겠습니까?");
        if(reConfirm){
            location.href="/product/manage/list"
        }
    });

    $(".plus").on("click", function () {

    });
    
    /* 새로운 form 추가 */
    /* <form action="/product/manage/add" method="post" enctype="multipart/form-data">
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
    </form> */
})