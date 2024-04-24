$().ready(function() {
	$("#regist-btn").on("click", function() {
		$(".error").remove();

		var file = $("#prfl")[0].files[0];

		var formData = new FormData();

		formData.append("file", file);
		formData.append("empId", $("#empId").val());
		formData.append("pwd", $("#pwd").val());
		formData.append("empName", $("#empName").val());
		formData.append("hireDt", $("#hireDt").val());
		formData.append("cntct", $("#cntct").val());
		formData.append("addr", $("#addr").val());
		formData.append("brth", $("#brth").val());
		formData.append("email", $("#email").val());
		formData.append("pstnId", $("#pstnId").val());
		formData.append("deptId", $("#deptId").val());
		formData.append("jobId", $("#jobId").val());
		formData.append("mngrYn", $("#mngrYn").val());
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
			},
		});
	});
});
