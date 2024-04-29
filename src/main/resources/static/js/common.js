// grid
window.onload = function () {
  var gridArray = document.querySelectorAll(".grid");

  gridArray.forEach((_, index) => {
    var dom = gridArray[index];
    var data = dom.dataset;

    dom.style["display"] = "grid";
    dom.style["grid-template-columns"] = data["gridColumns"];
    dom.style["grid-template-rows"] = data["gridRows"];

    if (data["gap"]) {
      dom.style["gap"] = data["gap"];
    }

    if (!data["gap"] && data["columnGap"]) {
      dom.style["column-gap"] = data["columnGap"];
    }

    if (!data["gap"] && data["rowGap"]) {
      dom.style["row-gap"] = data["rowGap"];
    }
  });

  var gridItemArray = document.querySelectorAll(".grid > *");
  gridItemArray.forEach((_, index) => {
    var dom = gridItemArray[index];
    var data = dom.dataset;

    if (data["columns"]) {
      dom.style["grid-column"] = data["columns"];
    }

    if (data["rows"]) {
      dom.style["grid-row"] = data["rows"];
    }
  });
};

// 사이드바 서브메뉴 접는 기능
$().ready(function () {
  $(".modal-confirm-close").on("click", function () {
    location.reload();
  });

  $(".fixed[data-fixed-top]").each(function () {
    var top = $(this).data("fixed-top");
    if (top === "fix") {
      top = $(this).offset().top;
    }
    $(this).css("top", top);
  });

  var isMainLayout =
    window.location.pathname === "/" || window.location.pathname === "";
  window.name = isMainLayout ? "main" : "sub";
  sessionTimer();
  if (!isMainLayout) {
    var framePath = window.parent.getLocationPathInFrame();

    // 자동 로그아웃되어서 로그인 페이지가 iframe에 보여질 경우
    // Logout 한다.
    if (framePath == "/employee/login") {
      window.parent.location = "/employee/login";
    }

    var menuObject = window.parent.findMenuObject(framePath);

    var activeFrameDataset = window.parent.getActiveFrameDataset();

    if (
      menuObject.role &&
      menuObject.id &&
      menuObject.url &&
      menuObject.id !== "8"
    ) {
      var menuId = activeFrameDataset.menuId;

      var url = $(window.parent.document)
        .find(".dropdown-menu")
        .find("a[data-menu-id=" + menuId + "]")
        .data("menu-url");

      if (menuObject.url !== url) {
        window.parent.menuAnchorClickHandler(
          null,
          $(window.parent.document)
            .find(".dropdown-menu")
            .find("a[data-menu-id=" + menuObject.id + "]")
        );
        //return;
      }
    }

    var menuTree = window.parent.makeTree(menuObject);
    var menuPath = window.parent.makePath(menuTree);

    var parentDocument = $(window.parent.document);
    var locationTree = parentDocument.find(".location-tree");
    locationTree.html("");

    menuPath.forEach(function (data, i) {
      var listItem = $("<li></li>");
      listItem.text(data.name);
      listItem.css("cursor", "pointer");
      if (data.url && menuPath.length - 1 > i) {
        listItem.on("click", function () {
          var frame = window.parent.document.querySelector(
            ".frame-active > iframe"
          );
          frame.contentDocument.location.href = data.url;
        });
      }

      locationTree.append(listItem);
    });
  }

  $(".menu-tab-prev").on("click", function () {
    var menuTabList = $(".content").find(".menu-tab");
    var scrollLeft = menuTabList.scrollLeft();

    if (scrollLeft < 0) {
      menuTabList.scrollLeft(0);
    } else {
      menuTabList.scrollLeft(scrollLeft - 50);
    }
  });

  $(".menu-tab-next").on("click", function () {
    var menuTabList = $(".content").find(".menu-tab");
    var scrollLeft = menuTabList.scrollLeft();

    menuTabList.scrollLeft(scrollLeft + 50);
  });
});

$().ready(function () {
  // Pagination active 기능
  $(".page-item").on("click", function () {
    $(this).addClass("active");
  });

  // 날짜 체크 1, 프로젝트 종료일이 프로젝트 시작일보다 빠르면 alert 를 발생하고,
  // 프로젝트 종료일을 초기화한다.
  $("#end-date").on("change", function () {
    var startDate = $("#start-date").val();
    var endDate = $("#end-date").val();

    // 시작일과 종료일을 비교
    if (startDate && endDate && endDate <= startDate) {
      // 종료일이 시작일보다 이전이거나 같다면 경고를 표시하고 초기화
      alert("종료일은 시작일보다 이후여야 합니다. 날짜를 다시 설정해주세요.");
      $("#end-date").val(""); // 종료일 초기화
    }
  });

  // 날짜 체크 2, 프로젝트 종료일이 선택된 후, 프로젝트 시작일을 수정하는 경우 alert 발생
  // 프로젝트 시작일 유지
  $("#start-date").on("change", function () {
    var startDate = $("#start-date").val();
    var endDate = $("#end-date").val();

    if (startDate && endDate && endDate <= startDate) {
      alert("시작일은 종료일보다 이전이여야 합니다. 날짜를 다시 설정해주세요.");
      $("#start-date").val(""); // 시작일 초기화
    }
  });

  // 날짜 최소, 최대 값 설정
  var today = new Date();
  var startOfYear = new Date(today.getFullYear(), 0, 1); // 올해의 1월 1일
  var endOfFiveYears = new Date(today.getFullYear() + 5, 11, 31); // 5년 후의 12월 31일

  // 날짜를 YYYY-MM-DD 형식으로 변환
  var minStartDate = startOfYear.toISOString().slice(0, 10);
  var maxEndDate = endOfFiveYears.toISOString().slice(0, 10);

  // 시작일(input#start-date)에 min 속성 설정
  $("#start-date").attr("min", minStartDate);
  $("#start-date").attr("max", maxEndDate); // 시작일의 max를 종료일의 max와 동일하게 설정할 수 있습니다.

  // 종료일(input#end-date)에 min과 max 속성 설정
  $("#end-date").attr("min", minStartDate); // 종료일의 min을 시작일의 min과 동일하게 설정할 수 있습니다.
  $("#end-date").attr("max", maxEndDate);
});

var menuTabQueue = [];

function menuClickHandler(event) {
  event.preventDefault();

  $(this).find(".dropdown-icon").toggleClass("active");
  var dropdown_content = $(this).siblings(".dropdown-menu");
  var dropdown_content_lis = dropdown_content.find("a");
  var active_height =
    dropdown_content_lis.eq(0).outerHeight() * dropdown_content_lis.length;
  dropdown_content
    .toggleClass("active")
    .css(
      "height",
      dropdown_content.hasClass("active") ? active_height + "px" : "0"
    );
}

function menuAnchorClickHandler(event, target) {
  var anchor = event ? $(this) : target;
  var frameList = $(".content").find(".frame-list");
  var menuTabList = $(".content").find(".menu-tab");

  $(".sidebar").find(".dropdown-menu").find("a").removeClass("active");
  anchor.addClass("active");

  var menuId = anchor.data("menu-id");
  var existsFrame =
    menuTabList.find("li[data-menu-id=" + menuId + "]").length > 0;

  if (existsFrame) {
    menuTabList.find("li[data-menu-id=" + menuId + "]").click();
    return;
  }

  var menuName = anchor.text();
  var menuUrl = anchor.data("menu-url");

  var menuTabItem = $("<li></li>");
  menuTabItem.addClass("active-tab");
  menuTabItem.text(menuName);
  menuTabItem.attr("data-menu-id", menuId);
  menuTabItem.attr("id", menuId);

  if (menuUrl !== "/main/dashboard") {
    var menuTabCloseButton = $("<span>X</span>");
    menuTabCloseButton.addClass("close-tab");
    menuTabCloseButton.on("click", function () {
      var tabItemLength = menuTabList.find("li").length;
      if (tabItemLength == 1) {
        return;
      }

      var clickedTabItem = $(this).closest("li");
      clickedTabItem.remove();
      frameList
        .find("li[data-menu-id=" + clickedTabItem.data("menu-id") + "]")
        .remove();

      menuTabQueue = menuTabQueue.filter((id) => id != menuId);

      var tabs = [];
      menuTabList.find("li").each(function () {
        tabs.push($(this).data("menu-id"));
      });
      sessionStorage.setItem("activetabs", tabs);

      var latestMenuId = menuTabQueue[menuTabQueue.length - 1];
      menuTabList.find("li[data-menu-id=" + latestMenuId + "]").click();

      var itemTotalWidth = 0;
      menuTabList.find("li").each(function () {
        itemTotalWidth += $(this).outerWidth(true);
      });

      var needScroll = itemTotalWidth > $(".frame-list").innerWidth();
      if (needScroll) {
        $(".menu-tab-prev, .menu-tab-next").show();
      } else {
        $(".menu-tab-prev, .menu-tab-next").hide();
      }
    });
    menuTabItem.append(menuTabCloseButton);
  }

  menuTabItem.on("click", function () {
    var menuId = $(this).data("menu-id");
    menuTabList.find("li").removeClass("active-tab");
    menuTabList.find("li[data-menu-id=" + menuId + "]").addClass("active-tab");

    frameList.find("li").removeClass("frame-active");
    frameList.find("li[data-menu-id=" + menuId + "]").addClass("frame-active");

    if (menuId !== "8") {
      var sidebarMenu = $(".sidebar")
        .find(".dropdown-menu")
        .find("a[data-menu-id=" + menuId + "]")
        .closest(".sidebar-submenu");
      var dropdownMenu = sidebarMenu.find(".dropdown-menu");
      if (dropdownMenu.not(".active").length > 0) {
        sidebarMenu.find(".sidebar-submenu-content").click();
      }

      $(".sidebar").find(".dropdown-menu").find("a").removeClass("active");
      $(".sidebar")
        .find(".dropdown-menu")
        .find("a[data-menu-id=" + menuId + "]")
        .addClass("active");

      $(".sidebar")
        .find(".dropdown-menu")
        .find("a[data-menu-id=" + menuId + "]")
        .focus();

      frameList
        .find("li[data-menu-id=" + menuId + "]")
        .find("iframe")
        .attr("src", menuUrl);

      if (menuTabQueue[menuTabQueue.length - 1] != menuId) {
        menuTabQueue.push(menuId);
      }
    }
    location.href = "/#";
    location.href = "/#" + menuId;
  });

  menuTabList.find("li").removeClass("active-tab");
  menuTabList.append(menuTabItem);

  var tabs = [];
  menuTabList.find("li").each(function () {
    tabs.push($(this).data("menu-id"));
  });
  sessionStorage.setItem("activetabs", tabs);

  var menuFrameItem = $("<li></li>");
  menuFrameItem.addClass("frame-active");
  menuFrameItem.attr("data-menu-id", menuId);
  menuFrameItem.attr("data-menu-name", menuName);

  var menuFrame = $("<iframe></iframe>");
  menuFrame.attr("src", menuUrl);
  menuFrameItem.append(menuFrame);

  frameList.find("li").removeClass("frame-active");
  frameList.append(menuFrameItem);

  if (menuTabQueue[menuTabQueue.length - 1] != menuId) {
    menuTabQueue.push(menuId);
  }

  var itemTotalWidth = 0;
  menuTabList.find("li").each(function () {
    itemTotalWidth += $(this).outerWidth(true);
  });

  var needScroll = itemTotalWidth > $(".frame-list").innerWidth();
  if (needScroll) {
    menuTabList.scrollLeft(Number.MAX_SAFE_INTEGER);
    $(".menu-tab-prev, .menu-tab-next").show();
  } else {
    $(".menu-tab-prev, .menu-tab-next").hide();
  }
}

function handleValidationErrors(ajaxResponse, fnHandlerCallback) {
  if (ajaxResponse && ajaxResponse.data && ajaxResponse.data.errors) {
    fnHandlerCallback(ajaxResponse.data.errors);
  }
}

// 검색
function search(pageNo) {
  var searchForm = $("#search-form");
  $("#page-no").val(pageNo);

  searchForm.attr("method", "get").submit();
}

function callMainFunction(fnCallback) {
  var isMainLayout = window.location.pathname === "/";
  if (!isMainLayout) {
    fnCallback(window.parent, $(window.parent.document));
  }
}

if (window.name !== "main") {
  $(window.parent.document)
    .find("iframe")
    .each(function () {
      $(this.contentDocument)
        .ajaxStart(function () {
          // TODO Ajax Start 처리
        })
        .ajaxComplete(function () {
          // Ajax가 완료되면 세션 타이머를 초기화한다.
          sessionTimer();
        });
    });
}

var sessionTimerObject;
function sessionTimer() {
  var target = window.name === "main" ? window : window.parent;

  if (target.sessionTimerObject) {
    target.clearTimeout(target.sessionTimerObject);
  }

  var time = 20 * 60;
  target.sessionTimerObject = target.setTimeout(function logoutTimer() {
    time--;

    if (time <= 0) {
      target.location.href = "/employee/logout";
    }
    var minutes = parseInt(time / 60);
    var seconds = time % 60;
    $(target.document)
      .find(".header-timer")
      .text(minutes + "분 " + seconds + "초");

    target.sessionTimerObject = target.setTimeout(logoutTimer, 1000);
  }, 1000);
}

function loadModal({
  content = "",
  positiveBtnName = "확인",
  fnPositiveBtnHandler = () => {},
  showNegativeBtn = true,
  negatgiveBtnName = "취소",
  fnNegativeBtnHandler = () => {},
}) {
  $.get("/html/newmodal.html", function (modalHtml) {
    var modal = $(modalHtml);
    $("body").prepend(modal);

    var alertModal = $(".modal-confirm-window");
    var modalButton = $(".confirm-confirm-button");
    var modalButton1 = $(".cancel-confirm-button");
    var modalText = $(".modal-confirm-text");
    modalText.text(content);
    modalButton.text(positiveBtnName);
    modalButton.on("click", function () {
      try {
        fnPositiveBtnHandler();
      } finally {
        alertModal[0].close();
        modal.remove();
      }
    });

    modalButton1.text(negatgiveBtnName);
    modalButton1.on("click", function () {
      try {
        fnNegativeBtnHandler();
      } finally {
        alertModal[0].close();
        modal.remove();
      }
    });

    if (!showNegativeBtn) {
      modalButton1.css("display", "none");
    }
    alertModal[0].showModal();
  });
}
