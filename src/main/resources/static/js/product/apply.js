$().ready(function(){

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
            <label for="select-prdtName">비품명</label>
            <select name="select-prdtName" id="select-prdtName">
                <option value="">선택</option>
                <c:forEach items="${productListVO.productList}" var="product">
                    <option value="${product.prdtName}" >${product.prdtName}</option>
                </c:forEach>
            </select>
        </div>
        */

        var prdtListValue = $(".productName").data("prdtList");
        var prdtNameValue = $(".productName").data("prdtName");

        console.log(prdtListValue);
        console.log(prdtNameValue);


        var productNameDom = $("<div></div>");

        var productLabel = $("<label></label>");
        productLabel.attr("for", "select-prdtName");
        productLabel.text("비품명");

        var productSelect = $("<select></select>");
        productSelect.attr("name", "select-prdtName");
        productSelect.attr("id", "select-prdtName");

        var selectOption = $("<option></option>");
        selectOption.attr("value", "");
        selectOption.text("비품명 선택");

        var nameForEach = $("<c:forEach></c:forEach>");
        nameForEach.attr("items", prdtListValue);
        nameForEach.attr("var", "product");

        var forEachOption = $("<option></option>");
        forEachOption.attr("value", prdtNameValue);
        forEachOption.text(prdtNameValue);


        nameForEach.append(forEachOption);


        productSelect.append(selectOption);
        productSelect.append(nameForEach);


        productNameDom.append(productLabel);
        productNameDom.append(productSelect);


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
            <label for="select-prdtCtgr">카테고리</label>
            <select name="select-prdtCtgr" id="select-prdtCtgr">
                <option value="">선택</option>
                <c:forEach items="${categoryList.productList}" var="category">
                    <option value="${category.prdtCtgr}" >${category.prdtCtgr}</option>
                </c:forEach>
            </select>
        </div>
        */
        var ctgrListValue = $(".category-list").data("ctgrList");
        var categoryValue = $(".category-list").data("category");

        console.log(ctgrListValue);
        console.log(categoryValue);

        var categoryDom = $("<div></div>");

        var categoryLabel = $("<label></label>");
        categoryLabel.attr("for", "select-prdtCtgr");
        categoryLabel.text("카테고리");

        var categorySelect = $("<select></select>");
        categorySelect.attr("name", "select-prdtCtgr");
        categorySelect.attr("id", "select-prdtCtgr");

        var ctgrSelectOption = $("<option></option>");
        ctgrSelectOption.attr("value", "");
        ctgrSelectOption.text("카테고리 선택");

        var categoryForEach = $("<c:forEach></c:forEach>");
        categoryForEach.attr("items", ctgrListValue);
        categoryForEach.attr("var", "category");

        var ctgrforEachOption = $("<option></option>");
        ctgrforEachOption.attr("value", categoryValue);
        ctgrforEachOption.text(categoryValue);


        categoryForEach.append(ctgrforEachOption);


        categorySelect.append(ctgrSelectOption);
        categorySelect.append(categoryForEach);


        categoryDom.append(categoryLabel);
        categoryDom.append(categorySelect);


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
        formGridDom.append(quantityDom);
        formGridDom.append(categoryDom);
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
    });


    $(".add-button").on("click", function(){
        var url = "/ajax/product/apply";

        // $.post(url, 
        //     {
        //         prdtName: $("#prdtName").val(),
        //         prdtCtgr: $("#prdtCtgr").val(),
        //         curStr: $("#apply-quantity").val(),
        //         data: $("#apply-date").val(),
        // }, 
        // function (response) {
        //     location.href = response.data.next;
        // });
    });

    
    $(".cancel-button").on("click", function() {
        var cancelConfirm = confirm("취소하시겠습니까?");
        if(cancelConfirm){
            location.href = "/product/list";
        }
    });


    var categories = {
        "컴퓨터기기": ["모니터", "마우스", "키보드", "프린터"],
        "컴퓨터용품": ["마이크", "화상카메라"],
        "저장장치": ["USB"],
        "가구": ["의자", "책상"],
        "가전": ["탁상용 선풍기", "가습기"],
        "사무용품": ["연필", "볼펜", "형광펜", "노트", "메모지"],
        "프린터용품": ["복사지", "토너"],
    };

    $("#select-prdtName").on("change", function () {
        var nameValue = $(this).val();
        
        for(var item in categories){
            if(categories[item].includes(nameValue)){
                var itemValue = item;
                $("#select-prdtCtgr").val(itemValue);
                break;
            }
        }
    });
})