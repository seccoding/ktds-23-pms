window.onload = function () {
	 
    // 삭제 링크를 클릭하면
    var deleteAnchor = document.querySelector(".delete-board");

    deleteAnchor.addEventListener("click", function (apprid) {
      // 사용자에게 진짜 삭제할것이냐 물어보고
      deleteAnchor.addEventListener("click", function () {
          // 사용자에게 진짜 삭제할것이냐 물어보고
          var chooseValue = confirm(
            "이 게시글을 정말 삭제하시겠습니까?\n삭제작업은 복구할 수 없습니다."
          );
          // chooseValue 가 true 라면 "확인" 을 클릭.
          // chooseValue 가 false 라면 "취소" 를 클릭.
          // 삭제를 하려한다면 삭제처리 해줄것이고
          // 그렇지 않다면 아무일도 하지 않아야 한다.
          if (chooseValue) {
            location.href = "/approval/delete/${approvalvo.apprid}";
          }
        });
    });
};