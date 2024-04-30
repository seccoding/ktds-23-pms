$().ready(function () {
	
	if (window.browser) {
		window.browser.history.deleteAll();
	}

  $("#empId, #pwd").on("keydown", function (enter) {
    if (enter.code === 'Enter') {
      $("#login-btn").click();
    }
  });
	
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
        // var errorCountFive = response.data.errorCountFive;
        var errorMessage = response.data.errorMessage;
        var next = response.data.next;


        if (errors) {

          var errorsH2 = $("<h2>로그인 오류</h2></br></hr></br>");
          var errorsModal = $(".modal-confirm-window");
          var errorsModalButton = $(".confirm-confirm-button");
          var errorsModalCancleButton = $(".cancel-confirm-button");
          var errorsModalText = $(".modal-confirm-text");

          errorsModal.css({
            "height" : "300px",
            "overflow" : "hidden"
          })

          errorsModalCancleButton.css({
            "display" : "none"
          });

          errorsModalButton.css({
            "position" : "relative",
            "right" : "90px",
            "bottom" : "60px"
          });

          errorsModalText.css({
            "position" : "relative",
            "bottom" : "20px"

          });


          // 사번 + 비번 둘다 입력되지 않을때
          if (!$("#empId").val() && !$("#pwd").val()) {

            errorsModalText.prepend(errors.empId.pwd);
            errorsModalText.prepend(errors.empId[0]);
            errorsModalText.prepend(errorsH2);
            errorsModalButton.text("확인");
            errorsModal[0].showModal();

            $(".confirm-confirm-button").on("click", function () {
              $(".modal-confirm-window").remove();
            })
          }
          //입력받았지만 Id형식이 아닐때(숫자 7자리 or "system"포함되어있는지)
          else if (errors.empId) {
            alert(errors.empId[0]);
          } else if (!$("#pwd").val()) {
            alert(errors.pwd);
          }
        }

        if (errorMessage) {
          alert(errorMessage);
        }

        if (errorUseNow) {
          alert(errorUseNow);
        }


        var errorEndDt = response.data.errorEndDt;

        if (errorEndDt) {
          var EndDtDiv = $("<div style='gap: 5px; font-size: 5px'></div>");
          EndDtDiv.addClass("errorEndDt");
          EndDtDiv.text(errorEndDt);

          $(".workCheck").after(EndDtDiv);
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
