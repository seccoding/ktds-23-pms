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

    $(".plus-btn").on("click", function () {
        /* 새로운 form 추가 */
        /* <form action="/product/apply" method="post" enctype="multipart/form-data"></form> */
        var formDom = $("<form></form>");
        formDom.attr("action", "/product/apply");
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
            <label for="quantity">신청 수량</label>
            <input type="number" id="quantity" />
        </div>
        */
        var quantityDom = $("<div></div>");

        var quantityLabel = $("<label></label>");
        quantityLabel.attr("for", "curStr");
        quantityLabel.text("신청 수량");

        var quantityInput = $("<input />");
        quantityInput.attr("type", "number");
        quantityInput.attr("id", "curStr");
        quantityInput.attr("name", "curStr");

        quantityDom.append(quantityLabel);
        quantityDom.append(quantityInput);


        /* 
        <div>
            <label for="first-buy-date">신청일</label>
            <input type="date" id="first-apply-date" />
        </div>
        */
        var applyDateDom = $("<div></div>");

        var applyDateLabel = $("<label></label>");
        applyDateLabel.attr("for", "first-apply-date");
        applyDateLabel.text("신청일");

        var applyDateInput = $("<input />");
        applyDateInput.attr("type", "date");
        applyDateInput.attr("id", "first-apply-date");

        applyDateDom.append(applyDateLabel);
        applyDateDom.append(applyDateInput);



        formGridDom.append(productNameDom);
        formGridDom.append(categoryDom);
        formGridDom.append(quantityDom);
        formGridDom.append(applyDateDom);

        
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
            var deleteConfirm = confirm("비품 신청을 취소하시겠습니까?");
            if(deleteConfirm){
                location.href = "/product/list";
            }
        }
    })
    

    

})