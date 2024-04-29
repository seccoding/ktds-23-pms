$().ready(function () {
  $("#search-form")
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
          tdDom1.addClass("emp-id-data");
          var tdDom2 = $("<td></td>");
          tdDom2.addClass("emp-name-data");
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
          var isChecked = $(this).prop("checked");

          // 새로운 테이블의 tbody 요소
          var checkedTableBody = $(".checked-emp-table tbody");

          // 기존의 데이터를 모두 비움
          checkedTableBody.empty();

          // 모든 사원 데이터에 대해 처리
          $(".emp-table tbody tr").each(function () {
            var empId = $(this).find(".emp-id-data").text(); // 사원 ID
            var empName = $(this).find(".emp-name-data").text(); // 사원 이름

            // 새로운 테이블에 행 추가
            var newRow = $("<tr></tr>");
            newRow.append("<td class='checked-id'>" + empId + "</td>");
            newRow.append("<td>" + empName + "</td>");
            newRow.append(
              "<td><button class='cancel-button'>취소</button></td>"
            );

            checkedTableBody.append(newRow);
          });

          // 전체 선택 체크박스를 체크하는 경우
          if (isChecked) {
            $(".target-emp-id").prop("checked", true); // 모든 사원 선택
          } else {
            $(".target-emp-id").prop("checked", false); // 모든 사원 선택 해제
          }
        });

        $(".target-emp-id").on("change", function () {
          var totalCheckboxes = $(".target-emp-id").length;
          var checkedCheckboxes = $(".target-emp-id:checked").length;
          var allChecked = totalCheckboxes === checkedCheckboxes;
          $("#checked-all").prop("checked", allChecked);

          var checkedItems = $(".target-emp-id:checked"); // 선택된 체크박스들

          // 새로운 테이블의 tbody 요소
          var checkedTableBody = $(".checked-emp-table tbody");

          // 기존의 데이터를 모두 비움
          //checkedTableBody.empty();

          // 선택된 각 사원에 대해 처리
          checkedItems.each(function () {
            var empId = $(this).closest("tr").find(".emp-id-data").text(); // 사원 ID
            var empName = $(this).closest("tr").find(".emp-name-data").text(); // 사원 이름

            // empId가 테이블에 이미 존재하는지 확인
            var existingRow = $(".checked-emp-table tbody tr").filter(
              function () {
                return $(this).find(".checked-id").text() === empId;
              }
            );

            // 테이블에 존재하지 않는 경우에만 행 추가
            if (existingRow.length === 0) {
              // 새로운 테이블에 행 추가
              var newRow = $("<tr></tr>");
              newRow.append("<td class='checked-id'>" + empId + "</td>");
              newRow.append("<td>" + empName + "</td>");
              newRow.append(
                "<td><button class='cancel-button'>취소</button></td>"
              );

              checkedTableBody.append(newRow);
            }
          });
        });

        // result.employeeList.forEach((item) => {
        // });
      }
    );
    $(".checked-emp-table").on("click", ".cancel-button", function () {
      var row = $(this).closest("tr");
      var empId = row.find("td:first-child").text(); // 해당 행의 사원 ID

      // 사원 ID에 해당하는 체크박스를 찾아 체크를 해제합니다.
      $(".emp-table tbody tr").each(function () {
        if ($(this).find(".emp-id-data").text() === empId) {
          $(this).find(".target-emp-id").prop("checked", false);
          return false; // 반복문 종료
        }
      });

      row.remove();
      $("#checked-all").prop("checked", false);
    });
  });

  $("#common-modal-search-btn").on("click", function () {
    var list = [];

    $(".checked-id").each((idx, item) => {
      list.push($(item).text());
    });

    $("#special-hidden-datalist").text(list);
    var alertModal = $(".modal-confirm-window");
    var confirmButton = $(".confirm-confirm-button");
    var cancelButton = $(".cancel-confirm-button");
    var modalText = $(".modal-confirm-text");
    modalText.text("정말로 해당 사원들을 등록하시겠습니까?");
    confirmButton.text("확인");
    cancelButton.text("취소");
    alertModal[0].showModal();

    confirmButton.on("click", function () {
      $(".modal-confirm-window")[0].close();
      $(".modal-employee-list")[0].close();
      $("#special-super-datalist").text(list);
    });

    cancelButton.on("click", function () {
      $(".modal-confirm-window")[0].close();
      $(".modal-employee-list")[0].close();
    });
  });
  $(".modal-list-close").on("click", function () {
    $(".modal-employee-list")[0].close();
  });
  $(".modal-employee-list").keydown(function (event) {
    if (event.key === "Enter") {
      // Enter 키가 눌렸을 때
      event.preventDefault(); // 기본 동작 방지
      $("#search-btn").click(); // 검색 버튼 클릭
    }
  });
});
