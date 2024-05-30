$().ready(function () {

	if (window.browser) {
		window.browser.history.deleteAll();
	}

  $("#empId, #pwd").on("keydown", function (enter) {
    if (enter.code === 'Enter') {
      $("#login-btn").click();
    }
  });

  $(".confirm-confirm-button").on("keydown", function (enter) {
    if (enter.code === 'Enter') {
      errorsModal[0].close();
    }
  });

  $("#login-btn").on("click", function () {
    $(".errorEndDt").remove();
    $(".errorRestDt").remove();
    $(".endDt").remove();
    $(".restDt").remove();

    $.post(
      "/member/login-proc",
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

        var errorsH2 = $("<h2>로그인 오류</h2></br></hr></br>");
        var errorsModal = $(".modal-confirm-window");
        var errorsModalButton = $(".confirm-confirm-button");
        var errorsModalCancelButton = $(".cancel-confirm-button");
        var errorsModalText = $(".modal-confirm-text");

        errorsModal.css({
          "height" : "300px",
          "overflow" : "hidden"
        })

        errorsModalCancelButton.css({
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

        $(".confirm-confirm-button").on("click", function () {
          errorsModal[0].close();
        });

        if (errors) {
          $(".modal-confirm-text").html("");
          // errorsModal[0].html("");
          // 사번 + 비번 둘다 입력되지 않을때
          if (!$("#empId").val() && !$("#pwd").val()) {

            errorsModalText.prepend(errors.empId.pwd);
            errorsModalText.prepend(errors.empId[0]);
            errorsModalText.prepend(errorsH2);
            errorsModalButton.text("확인");
            errorsModal[0].showModal();

          }
          //입력받았지만 Id형식이 아닐때(숫자 7자리 or "system"포함되어있는지)
          else if (errors.empId) {
            $(".modal-confirm-text").html("");
            errorsModalText.prepend(errors.empId[0]);
            errorsModalText.prepend(errorsH2);
            errorsModalButton.text("확인");
            errorsModal[0].showModal();
          }
          else if (!$("#pwd").val()) {
            $(".modal-confirm-text").html("");
            errorsModalText.prepend(errors.pwd);
            errorsModalText.prepend(errorsH2);
            errorsModalButton.text("확인");
            errorsModal[0].showModal();
          }
        }

        if (errorMessage) {
          $(".modal-confirm-text").html("");
          errorsModalText.prepend(errorMessage);
          errorsModalText.prepend(errorsH2);
          errorsModalButton.text("확인");
          errorsModal[0].showModal();
        }

        if (errorUseNow) {
          $(".modal-confirm-text").html("");
          errorsModalText.prepend(errorUseNow);
          errorsModalText.prepend(errorsH2);
          errorsModalButton.text("확인");
          errorsModal[0].showModal();
        }


        var errorEndDt = response.data.errorEndDt;
        var errorRestDt = response.data.errorRestDt;

        if (errorRestDt) {
          var restDtDiv = $("<div></div>");
          var restDt = $("<div></div>");

          restDtDiv.addClass("errorRestDt");
          restDtDiv.text(errorRestDt.substring(0, 12));

          restDt.addClass("restDt");
          restDt.text(errorRestDt.substring(12));

          $(".id").after(restDt);
          $(".id").after(restDtDiv);

          $("div.errorRestDt, div.restDt").css({
            "color": "red",
          });

        }

        if (errorEndDt) {
          var EndDtDiv = $("<div></div>");
          var endDt = $("<div></div>");

          EndDtDiv.addClass("errorEndDt");
          EndDtDiv.text(errorEndDt.substring(0, 10));

          endDt.addClass("endDt");
          endDt.text(errorEndDt.substring(10));

          $(".id").after(endDt);
          $(".id").after(EndDtDiv);

          $("div.errorEndDt, div.endDt").css({
            "color": "red",
          });

        }

        if (next) {
          location.href = next;
        }
      }
    );
  });
});
