$().ready(function () {
    $("#login-btn").on("click", function () {
        $.post("/ajax/employee/login", {
                empId: $("#empId").val(),
                pwd: $("#pwd").val(),
                nextUrl: $("#nextUrl").val(),
            },
            function (response) {
//                var errors = response.data.errors;
                var next = response.data.next;

//                for(var key in errors){
//                    var errorDiv = $("<div></div>");
//                    errorDiv.addClass("error");
//                    
//                    var values = errors[key];
//                    for(var i in values){
//                        var errorempIdalue = Values[i];
//
//                        var error = $("<div></div>");
//                        error.text(errorValue);
//                        error.append(error);
//                    }
//                    $("input[name="+key+"]").after(errorDiv);
//                }

                if (next) {
                    location.href = next;
                }
            }
        );
    });
});
