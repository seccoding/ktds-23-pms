$().ready(function() {
	$("#regist-btn").on("click", function() {
		$(".error").remove();
		$.post(
			"/ajax/employee/regist",
			{
				empId: $("#empId").val(),
				pwd: $("#pwd").val(),
				empName: $("#empName").val(),
				hireDt: $("#hireDt").val(),
				prfl: $("#prfl").val(),
				cntct: $("#cntct").val(),
				addr: $("#addr").val(),
				brth: $("#brth").val(),
				email: $("#email").val(),
				pstnId: $("#pstnId").val(),
				deptId: $("#deptId").val(),
				jobId: $("#jobId").val(),
				mngrYn: ($("#mngrYn").is(":checked")) ? "Y" : "N",
				next: $("#nextUrl").val(),
			},
			function(response) {
				var errors = response.data.errors;
				var next = response.data.next;
				var errorMessage = response.data.errorMessage;

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
					alert(errorMessage);
				}

				if (next) {
					location.href = next;
				}

			}
		);
	});
});
