$().ready(function () {
  $("#regist-btn").on("click", function () {
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
        mngrYn: $("#mngrYn").val(),
        next: $("#nextUrl").val(),
      },
      function (response) {
        var errors = response.data.errors;
        var next = response.data.next;

        if (errors) {
          //비어있을때
          if (
            !$("#empId").val() ||
            !$("#empName").val() ||
            !$("#hireDt").val() ||
            !$("#addr").val() ||
            !$("#brth").val() ||
            !$("#email").val() ||
            !$("#deptId").val() ||
            !$("#jobId").val() ||
            !$("#pstnId").val()
          ) {
            alert("빨간색은 필수 입력값입니다");
            if (!$("#empId").val()) {
              $("div.empId").css("color", "#f00");
            } else {
              $("div.empId").css("color", "#000");
            }

            if (!$("#empName").val()) {
              $("div.empName").css("color", "#f00");
            } else {
              $("div.empName").css("color", "#000");
            }

            if (!$("#hireDt").val()) {
              $("div.hireDt").css("color", "#f00");
            } else {
              $("div.hireDt").css("color", "#000");
            }

            if (!$("#addr").val()) {
              $("div.addr").css("color", "#f00");
            } else {
              $("div.addr").css("color", "#000");
            }

            if (!$("#brth").val()) {
              $("div.brth").css("color", "#f00");
            } else {
              $("div.brth").css("color", "#000");
            }

            if (!$("#email").val()) {
              $("div.email").css("color", "#f00");
            } else {
              $("div.email").css("color", "#000");
            }

            if (!$("#deptId").val()) {
              $("div.deptId").css("color", "#f00");
            } else {
              $("div.deptId").css("color", "#000");
            }

            if (!$("#jobId").val()) {
              $("div.jobId").css("color", "#f00");
            } else {
              $("div.jobId").css("color", "#000");
            }

            if (!$("#pstnId").val()) {
              $("div.pstnId").css("color", "#f00");
            } else {
              $("div.pstnId").css("color", "#000");
            }
          }
        }
        //empId형식이 아닐때
        //email형식이 아닐때
        //부서Id형식이 아닐때
        //직무Id형식이 아닐떄
        //직급Id형식이 아닐때

        if (next) {
          location.href = next;
        }
      }
    );
  });
});
