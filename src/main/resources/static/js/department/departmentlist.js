$().ready(function () {
  $(".emp-team-create").ready;

  $(".emp-team-create").on("click", function () {
    var modal = $(".modal-employee-list");
    modal[0].showModal();
  });
  $(".confirm-confirm-button").on("click", function () {
    var data = {};
    var list = $(".special-hidden-datalist").text().split(",");
    var tmId = $("#codeTmId").text();
    var deptId = $("#codeDeptId").text();
    list.forEach((item, idx) => {
      (data["employeeList[" + idx + "].empId"] = item),
        (data["employeeList[" + idx + "].teamVO.tmId"] = tmId);
      data["employeeList[" + idx + "].deptId"] = deptId;
    });
    $.post("/ajax/department/team/employee/add", data, function (res) {
      if (res.data.nopartof > 0) {
        var alertModal = $(".modal-window");
        var confirmButton = $(".confirm-button");
        var modalText = $(".modal-text");
        modalText.text(
          res.data.nopartof + "명의 인원은 관할 부서 사원이 아닙니다."
        );
        confirmButton.text("확인");
        alertModal[0].showModal();
        confirmButton.on("click", function () {
          alertModal[0].close();
        });
      }
      if (res.data.nopartof == 0 && res.data.alreadyexist) {
        var alertModal = $(".modal-window");
        var confirmButton = $(".confirm-button");
        var modalText = $(".modal-text");
        modalText.text("이미 존재하는 사원입니다.");
        confirmButton.text("확인");
        alertModal[0].showModal();
        confirmButton.on("click", function () {
          alertModal[0].close();
        });
      } else if (res.data.success) {
        var alertModal = $(".modal-window");
        var confirmButton = $(".confirm-button");
        var modalText = $(".modal-text");
        modalText.text(
          "이미 존재하거나 관할 부서 사원이 아닌 사원을 제외하고 추가완료에 성공했습니다!"
        );
        confirmButton.text("확인");
        alertModal[0].showModal();
        confirmButton.on("click", function () {
          alertModal[0].close();
        });
      } else {
        var alertModal = $(".modal-window");
        var confirmButton = $(".confirm-button");
        var modalText = $(".modal-text");
        modalText.text("추가 중 오류가 발생하였습니다!");
        confirmButton.text("확인");
        alertModal[0].showModal();
        confirmButton.on("click", function () {
          alertModal[0].close();
        });
      }
    });
  });

  function clearDepartmentInfo() {
    var subCommonCodeInfo = $(".code-info");
    subCommonCodeInfo.find("#codeDeptId").text("");
    subCommonCodeInfo.find("#codeDeptName").text("");
    subCommonCodeInfo.find("#codeDeptLeadId").text("");
    subCommonCodeInfo.find("#codeDeptCrtDt").text("");
  }

  function clearTeamInfo() {
    var subCommonCodeInfo = $(".sub-code-info");
    subCommonCodeInfo.find("#codeTmId").text("");
    subCommonCodeInfo.find("#codeTmName").text("");
    subCommonCodeInfo.find("#codeTmDepartment").text("");
    subCommonCodeInfo.find("#codeTmLeadId").text("");
    subCommonCodeInfo.find("#codeTmCrtDt").text("");
  }
  function clearEmployeeInfo() {
    var subSubCommonCodeInfo = $(".sub-sub-code-info");
    subSubCommonCodeInfo.find("#codeEmpId").text("");
    subSubCommonCodeInfo.find("#codeEmpName").text("");
    subSubCommonCodeInfo.find("#codeEmpEmail").text("");
    subSubCommonCodeInfo.find("#codeEmpCntct").text("");
    subSubCommonCodeInfo.find("#codeEmpPstn").text("");
    $("#profile").removeAttr("src");
    $("#profile").attr({ src: "/images/login.png" });
  }

  $(".departmentListClickFunction").on("click", function () {
    $(".emp-team-create").addClass("hidden");
    clearDepartmentInfo();
    clearTeamInfo();
    clearEmployeeInfo();

    $(this).closest("tbody").find("tr").removeClass("active");
    $(".sub-sub-employee").find("tr").removeClass("active");
    $(".sub-sub-employee").find("tbody").html("");

    $(this).addClass("active");

    clearTeamInfo();
    reloadSubTeam($(this).data("dept-id"));

    var commonCodeInfo = $(".code-info");

    commonCodeInfo.find("#codeDeptId").text($(this).data("dept-id"));
    commonCodeInfo.find("#codeDeptName").text($(this).data("dept-name"));
    commonCodeInfo.find("#codeDeptLeadId").text($(this).data("dept-lead-id"));
    commonCodeInfo.find("#codeDeptCrtDt").text($(this).data("dept-crdt"));
  });

  function reloadSubTeam(deptId) {
    // var deptId = $(this).data("dept-id");

    $.get("/ajax/department/search/" + deptId, function (response) {
      var subTeamTable = $(".sub-team").find("tbody");
      subTeamTable.html("");
      var teamList = response.data.teamList;

      teamList.forEach((subTeam) => {
        var trDom = $("<tr></tr>");
        trDom.attr({
          "data-id": subTeam.tmId,
          "data-name": subTeam.tmName,
          "data-crdt": subTeam.tmCrDt,
          "data-tm-lead-id": subTeam.tmLeadId,
          "data-tm-dept-id": subTeam.deptId,
        });
        trDom.on("click", function () {
          $(this).closest("tbody").find("tr").removeClass("active");
          $(this).addClass("active");
          $(".emp-team-create").removeClass("hidden");

          clearTeamInfo();
          clearEmployeeInfo();
          var teamInfo = $(".sub-code-info");
          teamInfo.find("#codeTmId").text($(this).data("id"));
          teamInfo.find("#codeTmName").text($(this).data("name"));
          teamInfo.find("#codeTmDepartment").text($(this).data("tm-dept-id"));
          teamInfo.find("#codeTmLeadId").text($(this).data("tm-lead-id"));
          teamInfo.find("#codeTmCrtDt").text($(this).data("crdt"));

          $.get(
            "/ajax/department/search/findemployee/" + $("#codeTmId").text(),
            function (response) {
              var employeeLists = response.data.employeeList;
              var subEmployee = $(".sub-sub-employee").find("tbody");
              subEmployee.html("");
              employeeLists.forEach((employee) => {
                var empTrDom = $("<tr></tr>");
                empTrDom.attr({
                  "data-emp-id": employee.empId,
                  "data-emp-name": employee.empName,
                  "data-emp-email": employee.email,
                  "data-emp-cntct": employee.cntct,
                  "data-emp-profile": employee.prfl,
                  "data-emp-pstn": employee.commonCodeVO.cmcdName,
                });
                var empIdTdDom = $("<td></td>");
                var empNameTdDom = $("<td></td>");
                empIdTdDom.text(employee.empId);
                empNameTdDom.text(employee.empName);
                empTrDom.append(empIdTdDom);
                empTrDom.append(empNameTdDom);
                subEmployee.append(empTrDom);

                empTrDom.on("click", function () {
                  $(".employee-info-enter").removeClass("hidden");
                  $(this).closest("tbody").find("tr").removeClass("active");
                  $(this).addClass("active");

                  clearEmployeeInfo();
                  var employeeInfo = $(".sub-sub-code-info");

                  $("#profile").attr({ src: $(this).data("emp-profile") });
                  employeeInfo
                    .find("#codeEmpPstn")
                    .text($(this).data("emp-pstn"));
                  employeeInfo.find("#codeEmpId").text($(this).data("emp-id"));
                  employeeInfo
                    .find("#codeEmpName")
                    .text($(this).data("emp-name"));
                  employeeInfo
                    .find("#codeEmpEmail")
                    .text($(this).data("emp-email"));
                  employeeInfo
                    .find("#codeEmpCntct")
                    .text($(this).data("emp-cntct"));
                  employeeInfo
                    .find("#codeEmpPstn")
                    .text($(this).data("emp-pstn"));
                });
              });
            }
          );
        });

        var tmIdTdDom = $("<td></td>");
        tmIdTdDom.text(subTeam.tmId);

        var tmNameTdDom = $("<td></td>");
        tmNameTdDom.text(subTeam.tmName);

        trDom.append(tmIdTdDom);
        trDom.append(tmNameTdDom);

        subTeamTable.append(trDom);
      });
    });
  }
  $(".department-create").on("click", function () {
    var modal = $(".create-modal");
    modal[0].showModal();
  });

  $("#dep-cancel-button").on("click", function () {
    location.reload();
  });

  $(".department-delete-button").on("click", function () {
    var deptId = $("#codeDeptId").text();
    $.get("/ajax/department/candelete/" + deptId, function (response) {
      if (response.data.possible) {
        var alertModal = $(".modal-confirm-window");
        var confirmButton = $(".confirm-confirm-button");
        var cancelButton = $(".cancel-confirm-button");
        var modalText = $(".modal-confirm-text");
        modalText.text("정말로 삭제하시겠습니까?");
        confirmButton.text("확인");
        cancelButton.text("취소");
        alertModal[0].showModal();

        confirmButton.on("click", function () {
          $.get("/ajax/department/delete/" + deptId, function (delResponse) {
            if (delResponse.data.success) {
              var alertModal = $(".modal-window");
              var confirmButton = $(".confirm-button");
              var modalText = $(".modal-text");
              modalText.text("삭제에 성공하였습니다.");
              confirmButton.text("확인");
              alertModal[0].showModal();
              confirmButton.on("click", function () {
                location.href = delResponse.data.next;
              });
            } else {
              var alertModal = $(".modal-window");
              var confirmButton = $(".confirm-button");
              var modalText = $(".modal-text");
              modalText.text("삭제중 오류가 발생했습니다.");
              confirmButton.text("확인");
              alertModal[0].showModal();
              confirmButton.on("click", function () {
                location.reload();
              });
            }
          });
        });
      } else {
        var alertModal = $(".modal-window");
        var confirmButton = $(".confirm-button");
        var modalText = $(".modal-text");
        modalText.text("부서 내에 팀이 존재하여 삭제할 수 없습니다.");
        confirmButton.text("확인");
        alertModal[0].showModal();
        confirmButton.on("click", function () {
          location.reload();
        });
      }
    });
  });

  $(".team-delete").on("click", function () {
    var tmId = $("#codeTmId").text();
    $.get("/ajax/department/team/candelete/" + tmId, function (response) {
      if (response.data.possible) {
        var alertModal = $(".modal-confirm-window");
        var confirmButton = $(".confirm-confirm-button");
        var cancelButton = $(".cancel-confirm-button");
        var modalText = $(".modal-confirm-text");
        modalText.text("정말로 삭제하시겠습니까?");
        confirmButton.text("확인");
        cancelButton.text("취소");
        alertModal[0].showModal();

        confirmButton.on("click", function () {
          $.get("/ajax/department/team/delete/" + tmId, function (delResponse) {
            if (delResponse.data.success) {
              var alertModal = $(".modal-window");
              var confirmButton = $(".confirm-button");
              var modalText = $(".modal-text");
              modalText.text("삭제에 성공하였습니다.");
              confirmButton.text("확인");
              alertModal[0].showModal();
              confirmButton.on("click", function () {
                location.reload();
              });
            } else {
              var alertModal = $(".modal-window");
              var confirmButton = $(".confirm-button");
              var modalText = $(".modal-text");
              modalText.text("삭제중 오류가 발생했습니다.");
              confirmButton.text("확인");
              alertModal[0].showModal();
              confirmButton.on("click", function () {
                location.reload();
              });
            }
          });
        });
      } else {
        var alertModal = $(".modal-window");
        var confirmButton = $(".confirm-button");
        var modalText = $(".modal-text");
        modalText.text("팀내에 사원이 존재하여 삭제할 수 없습니다.");
        confirmButton.text("확인");
        alertModal[0].showModal();
        confirmButton.on("click", function () {
          location.reload();
        });
      }
    });
  });

  $(".dep-submit-button").on("click", function () {
    var departmentName = $("#department-name").val();
    var departmentLeader = $("#department-leader").val();
    $.post(
      "/ajax/department/create",
      { deptName: departmentName, deptLeadId: departmentLeader },
      function (response) {
        location.href = response.data.nextUrl;
        var message=response.data.message
		if(message){
			alert(message)
		}
		else{
			location.href = response.data.nextUrl;
		}
        
      }
    );
  });

  $(".team-create").on("click", function () {
    var modal = $(".create-modal-team");
    $("#department-selectbox").val($("#codeDeptId").text());

    modal[0].showModal();
  });

  $("#team-cancel-button").on("click", function () {
    location.reload();
  });

  $(".team-submit-button").on("click", function () {
    var teamName = $("#team-name").val();

    var teamLeader = $("#team-leader").val();

    var teamDepartment = $(".department-selectbox").val();

    $.post(
      "/ajax/team/create",
      { tmName: teamName, tmLeadId: teamLeader, deptId: teamDepartment },
      function (response) {
        location.href = response.data.nextUrl;
      }
    );
  });

  $(".department-modify").on("click", function () {
    var modal = $(".modify-modal-dept");

    var departmentId = modal.find("#modify-select-box").val();
    $.get(
      "/ajax/department/show?departmentId=" + departmentId,
      function (response) {
        var dataDept = response.data.oneDepartment;
        modal.find("#mod-dept-id").text(dataDept.deptId);
        modal.find("#department-name-mod").val(dataDept.deptName);
        modal.find("#mod-dept-crd-dt").text(dataDept.deptCrDt);
        modal.find("#department-leader-mod").val(dataDept.deptLeadId);
      }
    );

    modal[0].showModal();
  });

  $("#modify-select-box").on("change", function () {
    var deptId = $(this).val();
    console.log(deptId);
    $.get("/ajax/department/show?departmentId=" + deptId, function (response) {
      var dataDept = response.data.oneDepartment;
      $("#mod-dept-id").text(dataDept.deptId);
      $("#department-name-mod").val(dataDept.deptName);
      $("#mod-dept-crd-dt").text(dataDept.deptCrDt);
      $("#department-leader-mod").val(dataDept.deptLeadId);
    });
  });
  $("#dep-modify-cancel-button").on("click", function () {
    location.reload();
  });
  $("#dep-modify-submit-button").on("click", function () {
    $.post(
      "/ajax/department/modify",
      {
        deptId: $("#mod-dept-id").text(),
        deptName: $("#department-name-mod").val(),
        deptLeadId: $("#department-leader-mod").val(),
      },
      function (response) {
        var returnUrl = response.data.next;
        var message=response.data.message
		console.log(message);
		
		if(message){
			confirm(message);
		}
		else{
			location.href =returnUrl;
		}
		
		
		
      }
    );
  });

  $(".team-modify").on("click", function () {
    var modal = $(".modify-modal-team");

    var teamId = modal.find("#modify-team-select-box").val();
    $.get("/ajax/team/show?teamId=" + teamId, function (response) {
      var dataTm = response.data.oneTeam;
      console.log(dataTm);
      modal.find("#mod-team-id").text(dataTm.tmId);
      modal.find("#team-name-mod").val(dataTm.tmName);
      modal.find("#mod-team-crd-dt").text(dataTm.tmCrDt);
      modal.find("#team-leader-mod").val(dataTm.tmLeadId);
      modal.find("#team-dept-mod").val(dataTm.deptId);
    });

    modal[0].showModal();
  });

  $("#modify-team-select-box").on("change", function () {
    var teamId = $(this).val();
    $.get("/ajax/team/show?teamId=" + teamId, function (response) {
      var dataTm = response.data.oneTeam;
      $("#mod-team-id").text(dataTm.tmId);
      $("#team-name-mod").val(dataTm.tmName);
      $("#mod-team-crd-dt").text(dataTm.deptCrDt);
      $("#team-leader-mod").val(dataTm.tmLeadId);
      $("#team-dept-mod").val(dataTm.deptId);
    });
  });
  $("#tm-modify-cancel-button").on("click", function () {
    location.reload();
  });
  $("#tm-modify-submit-button").on("click", function () {
    $.post(
      "/ajax/team/modify",
      {
        tmId: $("#mod-team-id").text(),
        tmName: $("#team-name-mod").val(),
        tmLeadId: $("#team-leader-mod").val(),
        deptId: $("#team-dept-mod").val(),
      },
      function (response) {
        var returnUrl = response.data.next;
        var message=response.data.message
		if(message){
			confirm(message)
		}
		else{
			location.href = returnUrl;   
		}
      }
    );
  });

  $(".employee-info-enter").on("click", function () {
    var id = $("#codeEmpId").text();
    console.log(id);
    location.href = "/employee/view?empId=" + id;
  });
});
