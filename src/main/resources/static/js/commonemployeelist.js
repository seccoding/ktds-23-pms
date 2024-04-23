$().ready(function () {
  $(".modal-list-close").on("click", function () {
    location.reload();
  });

  $("#earch-form")
    .find("button")
    .on("keydown", function (event) {
      if (event.keyCode === 13) {
        var noSubmit = $(this).data("no-submit");
        if (noSubmit !== undefined) {
          event.preventDefault();
        }
      }
    });
  $("#search-form")
    .find("input")
    .on("keydown", function (event) {
      if (event.keyCode === 13) {
        var noSubmit = $(this).data("no-submit");
        if (noSubmit !== undefined) {
          event.preventDefault();
        }
      }
    });
  $("#list-size").on("change", function () {});

  $("#search-btn").on("click", function () {
    var body = $(".emp-table").find("tbody");
    body.html("");

    $.get(
      "/ajax/employeelist/search",
      {
        searchType: $("#search-type").val(),
        searchKeyWord: $("#search-keyword").val(),
        listSize: $("#list-size").val(),
      },
      function (response) {
        var result = response.data.result.employeeList;
        for (var i in result) {
          var item = result[i];
          var trDom = $("<tr></tr>");
          var tdDom0 = $("<td></td>");
          var tdDom1 = $("<td></td>");
          var tdDom2 = $("<td></td>");
          var tdDom3 = $("<td></td>");
          var tdDom4 = $("<td></td>");
          var tdDom5 = $("<td></td>");
          var input = $("<input></input>");
          input
            .prop("type", "checkbox")
            .prop("class", "target-emp-id")
            .prop("id", "target-emp-id-" + i)
            .prop("name", "targetEmpId")
            .prop("value", "${item.empId}");
          var label = $("<label></label>");
          label.prop("for", "target-emp-id-" + i);
          tdDom0.append(input);
          tdDom0.append(label);
          trDom.append(tdDom0);

          tdDom1.text(item.empId);
          trDom.append(tdDom1);
          tdDom2.text(item.empName);
          trDom.append(tdDom2);
          tdDom3.text(item.departmentVO.deptName);
          trDom.append(tdDom3);
          tdDom4.text(item.jobVO.jobName);
          trDom.append(tdDom4);
          tdDom5.text(item.brth);
          trDom.append(tdDom5);
          body.append(trDom);
        }

        $("#checked-all").on("click", function () {
          var isChecked = $(this).prop("checked");
          $(".target-emp-id").prop("checked", isChecked);
        });

        $(".target-emp-id").on("change", function () {
          var totalCheckboxes = $(".target-emp-id").length;
          var checkedCheckboxes = $(".target-emp-id:checked").length;
          var allChecked = totalCheckboxes === checkedCheckboxes;
          $("#checked-all").prop("checked", allChecked);
        });
        // result.employeeList.forEach((item) => {
        // });
      }
    );
  });

  //   $("#checked-all").on("click", function () {
  //     var targetClass = $(this).data("target-class");

  //     // checked-all 의 체크 상태를 가져온다.
  //     // 체크가 되어있다면 true 아니라면 false
  //     var isChecked = $(this).prop("checked");

  //     $("." + targetClass).prop("checked", isChecked);
  //   });
});
