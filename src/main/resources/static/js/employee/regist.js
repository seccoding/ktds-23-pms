$().ready(function() {

	

	

	$(".confirm-confirm-button").on("keydown", function (enter) {
		if (enter.code === 'Enter') {
			errorsModal[0].close();
		}
	});

	$("#regist-btn").on("click", function() {
		$(".error").remove();

		var errorsH2 = $("<h2>회원가입 오류</h2></br></hr></br>");
		var errorsModal = $(".modal-confirm-window");
		var errorsModalButton = $(".confirm-confirm-button");
		var errorsModalCancelButton = $(".cancel-confirm-button");
		var errorsModalText = $(".modal-confirm-text");

		$(".confirm-confirm-button").on("click", function() {
			errorsModal[0].close();
			registSuccessModal[0].close();
		});

		errorsModal.css({
			"height": "300px",
			"overflow": "hidden"
		})

		errorsModalCancelButton.css({
			"display": "none"
		});

		errorsModalButton.css({
			"position": "relative",
			"right": "115px",
			"bottom": "20px",
			"width": "200px",
		});

		errorsModalText.css({
			"position": "relative",
			"bottom": "10px"
		});

		var file = $("#prfl")[0].files[0];
		var formData = new FormData();

		formData.append("file", file);
		formData.append("pwd", $("#pwd").val());
		formData.append("confirmPwd", $("#confirmPwd").val());
		formData.append("empName", $("#empName").val());
		formData.append("hireDt", $("#hireDt").val());
		formData.append("cntct", $("#cntct").val());
		formData.append("addr", $("#addr").val());
		formData.append("brth", $("#brth").val());
		formData.append("pstnId", $("#pstnId").val());
		formData.append("deptId", $("#deptId").val());
		formData.append("jobId", $("#jobId").val());

		if ($("#mngrYn").is(":checked")) {
			formData.append("mngrYn", "Y");
		} else {
			
		}

		/*if($("#pstnId option:selected").val("110").eq()){
			formData.append("mngrYn", "Y");
		} else{
			formData.append("mngrYn", "N");
		}*/

		formData.append("next", $("#nextUrl").val());

		$.ajax({
			url: "/ajax/employee/regist",
			type: "POST",
			data: formData,
			processData: false,
			contentType: false,
			success: function(response) {

				var errors = response.data.errors;
				var next = response.data.next;
				var errorMessage = response.data.errorMessage;

				console.log(errors);


				if (errors) {
					for (var key in errors) {
						var errorDiv = $("<div></div>");
						errorDiv.addClass("error");

						var values = errors[key];

						for (var i in values) {
							var errorValue = values[i];
							var error = $("<div></div>");
							error.text(errorValue);
							errorDiv.append(error);
							$("input[name=" + key + "]").after(errorDiv);
						}
					}
					$("div.error").css({
						"color": "red",
					});
				}

				if (errorMessage) {
					$(".modal-confirm-text").html("");

					errorsModalText.prepend(errorMessage);
					errorsModalText.prepend(errorsH2);
					errorsModalButton.text("확인");
					errorsModal[0].showModal();

				}

				if (next) {
					$(".modal-confirm-text").html("");

					var registSuccessH2 = $("<h2>회원가입 완료</h2></br></hr></br>");
					var registSuccessModal = $(".modal-confirm-window");
					var registSuccessModalButton = $(".confirm-confirm-button");
					var registSuccessModalCancelButton = $(".cancel-confirm-button");
					var registSuccessModalText = $(".modal-confirm-text");

					registSuccessModal.css({
						"height": "300px",
						"overflow": "hidden"
					})

					registSuccessModalCancelButton.css({
						"display": "none"
					});

					registSuccessModalButton.css({
						"position": "relative",
						"right": "115px",
						"bottom": "10px",
						"width": "200px",
					});

					registSuccessModalText.css({
						"position": "relative",
						"bottom": "10px"
					});

					registSuccessModalText.prepend($("<div>사원정보 조회 페이지로 이동합니다.</div>"));
					registSuccessModalText.prepend($("<div>회원가입이 완료되었습니다.</div>"));
					registSuccessModalText.prepend(registSuccessH2);
					registSuccessModalButton.text("확인");
					registSuccessModal[0].showModal();

					$(".modal-confirm-button").on("click", function () {
						registSuccessModal[0].close();
					});

					$(".modal-confirm-window").on("close", function () {
						location.href = next;
					});

				}
			},
		});
	});
});
