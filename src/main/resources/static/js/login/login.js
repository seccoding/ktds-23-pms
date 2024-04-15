$().ready(function () {
  $("#login-btn").on("click", function () {
	
    $.post(
      "/ajax/employee/login",
      {
        empId: $("#empId").val(),
        pwd: $("#pwd").val(),
        nextUrl: $("#nextUrl").val(),
      },
      function (response) {
        var errors = response.data.errors;
        var next = response.data.next;
        
        if (errors) {
            for (var key in errors) {
                 console.log(errors[key]);
                //  if (errors[key] === 2) {
                    // var message = errors[0] + errors[1] + errors[2]
                //  }
                
                //   var errorDiv = $("<div></div>");
                //   errorDiv.addClass("error");
                // var values = errors[key];
                // for (var i in values) {
                    // var errorValue = Values[i];
                // var error = $("<div></div>");
                // error.text(errorValue);
                // error.append(error);
            //   }
            //   $("input[name=" + key + "]").after(errorDiv);
          }
          /**
           * 사번과 비밀번호를 둘다 입력하지 않은경우(사번, 비번 입력해주세요)
           * 사번을 입력했으나 사번 형식이 아닌경우 (사번형식으로 입력해주세요)
           * 사번을 입력했으나 사번 형식이 아니고 비밀번호를 입력하지 않은경우 (사번형식으로 입력해주세요
           *                                                        , 비밀번호를 입력해주세요)
           * 사번은 일치하나 비밀번호를 입력하지 않은경우 (비밀번호를 입력해 주세요)
           * 
           */
        //   if (empId === "") {
        //     alert(errors.empId[0]);
        //   } 
        //   else if(empId !== null) {
        //     alert(errors.empId[1]);
        //   }
        //    else if(errors.empId == null){
        //     alert(errors.empId[1]);
        //   } else if(errors.pwd){
        //     alert(errors.pwd);
        //   }
          
        }
        if (next) {
          location.href = next;
        }
      }
    );
  });
});
