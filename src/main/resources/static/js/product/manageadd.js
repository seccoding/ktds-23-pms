$().ready(function(){

    $(".plus-btn").on("click", function () {
        /* 새로운 form 추가 */
        /* <form action="/product/manage/add" method="post" enctype="multipart/form-data"></form> */
        var formDom = $("<form></form>");
        formDom.attr("action", "/product/manage/add");
        formDom.attr("method", "post");
        formDom.attr("enctype", "multipart/form-data");

        /* <div class="form-grid"></div> */
        var formGridDom = $("<div></div>");
        formGridDom.addClass("form-grid");

        /*
        <div>
            <label for="product-name">비품명</label>
            <input type="text" id="product-name" />
        </div> 
        */
        var productNameDom = $("<div></div>");

        var productLabel = $("<label></label>");
        productLabel.attr("for", "prdtName");
        productLabel.text("비품명");

        var productInput = $("<input />");
        productInput.attr("type", "text");
        productInput.attr("id", "prdtName");
        productInput.attr("name", "prdtName");

        productNameDom.append(productLabel);
        productNameDom.append(productInput);


        /*
        <div>
            <label for="product-category">카테고리</label>
            <select id="search-type" name="searchType" >
                <option value="productId" >컴퓨터기기</option>
                <option value="productId" >사무용품</option>
                <option value="productId" >프린터용품</option>
            </select>
        </div> 
        */
        var categoryDom = $("<div></div>");

        var categoryLabel = $("<label></label>");
        categoryLabel.attr("for", "prdtCtgr");
        categoryLabel.text("카테고리");

        var categorySelect = $("<select></select>");
        categorySelect.attr("id", "prdtCtgr");
        categorySelect.attr("name", "prdtCtgr");

        var categoryOption1 = $("<option></option>");
        categoryOption1.attr("value", "prdtCtgr");
        categoryOption1.text("컴퓨터기기");

        var categoryOption2 = $("<option></option>");
        categoryOption2.attr("value", "prdtCtgr");
        categoryOption2.text("사무용품");

        var categoryOption3 = $("<option></option>");
        categoryOption3.attr("value", "prdtCtgr");
        categoryOption3.text("프린터용품");

        categorySelect.append(categoryOption1);
        categorySelect.append(categoryOption2);
        categorySelect.append(categoryOption3);

        categoryDom.append(categoryLabel);
        categoryDom.append(categorySelect);


        /* 
        <div>
            <label for="product-type">소모품 분류</label>
            <select id="search-type" name="searchType" >
                <option value="productId" >소모품</option>
                <option value="productId" >비소모품</option>
            </select>
        </div>
        */
        var productTypeDom = $("<div></div>");

        var productTypeLabel = $("<label></label>");
        productTypeLabel.attr("for", "onceYn");
        productTypeLabel.text("소모품 분류");

        var productTypeSelect = $("<select></select>");
        productTypeSelect.attr("id", "onceYn");
        productTypeSelect.attr("name", "onceYn");

        var productTypeOption1 = $("<option></option>");
        productTypeOption1.attr("value", "onceYn");
        productTypeOption1.text("소모품");

        var productTypeOption2 = $("<option></option>");
        productTypeOption2.attr("value", "onceYn");
        productTypeOption2.text("비소모품");

        productTypeSelect.append(productTypeOption1);
        productTypeSelect.append(productTypeOption2);

        productTypeDom.append(productTypeLabel);
        productTypeDom.append(productTypeSelect);


        /* 
        <div>
            <label for="quantity">재고수</label>
            <input type="number" id="quantity" />
        </div>
        */
        var quantityDom = $("<div></div>");

        var quantityLabel = $("<label></label>");
        quantityLabel.attr("for", "curStr");
        quantityLabel.text("재고수");

        var quantityInput = $("<input />");
        quantityInput.attr("type", "number");
        quantityInput.attr("id", "curStr");
        quantityInput.attr("name", "curStr");

        quantityDom.append(quantityLabel);
        quantityDom.append(quantityInput);


        /* 
        <div>
            <label for="first-buy-date">초기 구매일</label>
            <input type="date" id="first-buy-date" />
        </div>
        */
        var buyDateDom = $("<div></div>");

        var buyDateLabel = $("<label></label>");
        buyDateLabel.attr("for", "buyDt");
        buyDateLabel.text("구매일");

        var buyDateInput = $("<input />");
        buyDateInput.attr("type", "date");
        buyDateInput.attr("id", "buyDt");
        buyDateInput.attr("name", "buyDt");

        buyDateDom.append(buyDateLabel);
        buyDateDom.append(buyDateInput);


        /* 
        <div>
            <label for="first-price">초기 가격</label>
            <input type="text" id="first-price" />
        </div>
        */
        var firstPriceDom = $("<div></div>");

        var firstPriceLabel = $("<label></label>");
        firstPriceLabel.attr("for", "prdtPrice");
        firstPriceLabel.text("가격");

        var firstPriceInput = $("<input />");
        firstPriceInput.attr("type", "text");
        firstPriceInput.attr("id", "prdtPrice");
        firstPriceInput.attr("name", "prdtPrice");

        firstPriceDom.append(firstPriceLabel);
        firstPriceDom.append(firstPriceInput);



        formGridDom.append(productNameDom);
        formGridDom.append(categoryDom);
        formGridDom.append(productTypeDom);
        formGridDom.append(quantityDom);
        formGridDom.append(buyDateDom);
        formGridDom.append(firstPriceDom);

        
        var hrTag = $("<hr />");

        formDom.append(hrTag);
        formDom.append(formGridDom);
        formDom.append(hrTag);

        $(".form-group").append(formDom);
    });

    $(".minus-btn").on("click", function () {
        var formCount = $("form").length;
        if(formCount > 1){
            $("form:last").remove();
        }
        else{
            var deleteConfirm = confirm("비품 추가를 취소하시겠습니까?");
            if(deleteConfirm){
                location.href = "/product/manage/list";
            }
        }
    })


    $(".product-add").on("click", function(){
        var url = "/ajax/product/manage/add";
        $.post(url, 
            {
                prdtName: $("#prdtName").val(),
                prdtCtgr: $("#prdtCtgr").val(),
                onceYn: $("#onceYn").val(),
                curStr: $("#curStr").val(),
                buyDt: $("#buyDt").val(),
                prdtPrice: $("#prdtPrice").val(),

        }, 
        function (response) {
            var addConfirm = confirm("추가하시겠습니까?");
            if(addConfirm){
                location.href = response.data.next;
            }
        });
    });

    $(".product-cancel").on("click", function() {
        var reConfirm = confirm("취소하시겠습니까?");
        if(reConfirm){
            location.href="/product/manage/list";
        }
    });
    

})