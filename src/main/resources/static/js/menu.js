const menuList = [
  {
    id: "1",
    name: "사원",
    role: "USER",
    url: null,
    icon: "/images/sidebar-employee.png",
    children: [
      {
        id: "1-1",
        name: "사원 정보 조회",
        role: "USER",
        url: "/employee/search",
      },
      {
        id: "1-2",
        name: "사원 이력 정보 조회",
        role: "ADMIN",
        url: "/employee/search/history",
      },
    ],
  },
  {
    id: "2",
    name: "부서",
    role: "USER",
    url: null,
    icon: "/images/sidebar-department.png",
    children: [
      {
        id: "2-1",
        name: "부서/ 팀 조회",
        role: "USER",
        url: "/department/search",
      },
    ],
  },
  {
    id: "3",
    name: "비품",
    role: "USER",
    url: null,
    icon: "/images/sidebar-product.png",
    children: [
      {
        id: "3-1",
        name: "비품 조회",
        role: "USER",
        url: "/product/list",
      },
      {
        id: "3-2",
        name: "비품대여현황",
        role: "USER",
        url: "/product/rentalstate",
      },
      {
        id: "3-3",
        name: "비품관리목록",
        role: "ADMIN",
        url: "/product/manage/list",
      },
      {
        id: "3-4",
        name: "비품 상세목록",
        role: "ADMIN",
        url: "/product/manage/detail",
      },
      {
        id: "3-5",
        name: "비품대여현황",
        role: "ADMIN",
        url: "/product/manage/state",
      },
    ],
  },
  {
    id: "4",
    name: "결재",
    role: "USER",
    url: null,
    icon: "/images/sidebar-approval.png",
    children: [
      {
        id: "4-1",
        name: "결재 상신",
        role: "USER",
        url: "/approval/outbox",
      },
      {
        id: "4-2",
        name: "결재 수신함",
        role: "ADMIN",
        url: "/approval/inbox",
      },
      {
        id: "4-3",
        name: "결재 조회",
        role: "USER",
        url: "/approval/approvalhome",
      },
    ],
  },
  {
    id: "5",
    name: "프로젝트",
    role: "USER",
    url: null,
    icon: "/images/sidebar-project.png",
    children: [
      {
        id: "5-1",
        name: "프로젝트 관리",
        role: "USER",
        url: "/project",
      },
      {
        id: "5-2",
        name: "요구사항 관리",
        role: "USER",
        url: "/project/111",
      },
      {
        id: "5-3",
        name: "결재 상신",
        role: "USER",
        url: "/project/222",
      },
      {
        id: "5-4",
        name: "결재 상신",
        role: "USER",
        url: "/project/333",
      },
      {
        id: "5-5",
        name: "결재 상신",
        role: "USER",
        url: "/project/444",
      },
      {
        id: "5-6",
        name: "결재 상신",
        role: "USER",
        url: "/project/555",
      },
      {
        id: "5-7",
        name: "결재 상신",
        role: "USER",
        url: "/project/666",
      },
      {
        id: "5-8",
        name: "결재 상신",
        role: "USER",
        url: "/project/777",
      },
    ],
  },
  {
    id: "6",
    name: "쪽지",
    role: "USER",
    url: null,
    icon: "/images/sidebar-memo.png",
    children: [
      {
        id: "6-1",
        name: "쪽지 작성",
        role: "USER",
        url: "/memo/777",
      },
      {
        id: "6-2",
        name: "받은 쪽지함",
        role: "USER",
        url: "/memo/777",
      },
      {
        id: "6-3",
        name: "보낸 쪽지함",
        role: "USER",
        url: "/memo/777",
      },
      {
        id: "6-4",
        name: "쪽지 보관함",
        role: "USER",
        url: "/memo/777",
      },
    ],
  },
  {
    id: "7",
    name: "출퇴근",
    role: "USER",
    url: null,
    icon: "/images/sidebar-commute.png",
    children: [
      {
        id: "7-1",
        name: "출퇴근 조회",
        role: "USER",
        url: "/memo/777",
      },
      {
        id: "7-2",
        name: "사원 출퇴근 조회",
        role: "USER",
        url: "/memo/777",
      },
    ],
  },
];

$().ready(function () {
  var sidebarMenu = $(".sidebar").find(".sidebar-menu");

  menuList.forEach(function (item) {
    var sidebarSubmenu = $("<div></div>");
    sidebarSubmenu.addClass("sidebar-submenu");

    var subMenuContent = $("<div></div>");
    subMenuContent.addClass("sidebar-submenu-content");

    var icon = $("<img />");
    icon.addClass("sidebar-common-icon");
    icon.attr("src", item.icon);
    subMenuContent.append(icon);

    var menuName = $("<span></span>");
    menuName.addClass("sidedbar-menu-name");
    menuName.text(item.name);
    subMenuContent.append(menuName);

    if (item.children.length > 0) {
      var dropdownIcon = $("<div></div>");
      dropdownIcon.addClass("dropdown-icon");
      subMenuContent.append(dropdownIcon);
    }
    subMenuContent.on("click", menuClickHandler);
    sidebarSubmenu.append(subMenuContent);

    if (item.children.length > 0) {
      var dropDownMenu = $("<div></div>");
      dropDownMenu.addClass("dropdown-menu");

      item.children.forEach(function (subItem) {
        var anchor = $("<a></a>");
        anchor.attr({
          href: "javascript:void(0);",
          "data-menu-id": subItem.id,
          "data-menu-url": subItem.url,
        });
        anchor.text(subItem.name);
        anchor.on("click", menuAnchorClickHandler);
        dropDownMenu.append(anchor);
      });

      sidebarSubmenu.append(dropDownMenu);
    }

    sidebarMenu.append(sidebarSubmenu);
  });
});
