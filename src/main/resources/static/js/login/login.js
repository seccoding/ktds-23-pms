$().ready(function () {
  $("#login-btn").on("click", function () {

      $(".errorEndDt").remove();
      $(".errorRestDt").remove();

    $.post(
      "/ajax/employee/login",
      {
        empId: $("#empId").val(),
        pwd: $("#pwd").val(),
        nextUrl: $("#nextUrl").val(),
      },
      function (response) {
        var errors = response.data.errors;
        var errorUseNow = response.data.errorUseNow;
        var next = response.data.next;


        if (errors) { 
          // 사번 + 비번 둘다 입력되지 않을때
          if (!$("#empId").val() && !$("#pwd").val()) {
            alert(errors.empId[0] + "\n" + errors.pwd[0]);
          }
          //입력받았지만 Id형식이 아닐때(숫자 7자리 or "system"포함되어있는지)
          else if (errors.empId) {
            alert(errors.empId[0]);
        }

          else if (!$("#pwd").val()) {
            alert(errors.pwd);
          }
        }

        if (errorUseNow) {
          alert(errorUseNow);
        }

        var errorEndDt = response.data.errorEndDt;

        if (errorEndDt) {
            var EndDtDiv = $("<div></div>");
            EndDtDiv.addClass("errorEndDt");
            EndDtDiv.text(errorEndDt);

            $("#loginForm").after(EndDtDiv);
        }

        var errorRestDt = response.data.errorRestDt;

          if (errorRestDt) {
              var RestDiv = $("<div></div>");
              RestDiv.addClass("errorRestDt");
              RestDiv.text(errorRestDt);

              $("#loginForm").after(RestDiv);
          }

        if (next) {
          location.href = next;
        }
      }
    );
  });
});
