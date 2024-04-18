const menuList = [
  {
    id: "1",
    name: "사원",
    role: "USER",
    url: null,
    icon: "/images/sidebar-employee.png",
    parent: undefined,
    children: [
      {
        id: "1-1",
        name: "사원 정보 조회",
        role: "USER",
        url: "/employee/search",
        parent: { id: "1" },
      },
      {
        id: "1-2",
        name: "사원 이력 정보 조회",
        role: "ADMIN",
        url: "/employee/search/history",
        parent: { id: "1" },
      },
    ],
  },
  {
    id: "2",
    name: "부서",
    role: "USER",
    url: null,
    icon: "/images/sidebar-department.png",
    parent: undefined,
    children: [
      {
        id: "2-1",
        name: "부서/ 팀 조회",
        role: "USER",
        url: "/department/search",
        parent: { id: "2" },
      },
    ],
  },
  {
    id: "3",
    name: "비품",
    role: "USER",
    url: null,
    icon: "/images/sidebar-product.png",
    parent: undefined,
    children: [
      {
        id: "3-1",
        name: "비품 조회",
        role: "USER",
        url: "/product/list",
        parent: { id: "3" },
      },
      {
        id: "3-2",
        name: "비품대여현황",
        role: "USER",
        url: "/product/rentalstate",
        parent: { id: "3" },
      },
      {
        id: "3-3",
        name: "비품관리목록",
        role: "ADMIN",
        url: "/product/manage/list",
        parent: { id: "3" },
      },
      {
        id: "3-4",
        name: "비품 상세목록",
        role: "ADMIN",
        url: "/product/manage/detail",
        parent: { id: "3" },
      },
      {
        id: "3-5",
        name: "비품대여현황",
        role: "ADMIN",
        url: "/product/manage/state",
        parent: { id: "3" },
      },
    ],
  },
  {
    id: "4",
    name: "결재",
    role: "USER",
    url: null,
    icon: "/images/sidebar-approval.png",
    parent: undefined,
    children: [
      {
        id: "4-1",
        name: "결재 상신",
        role: "USER",
        url: "/approval/outbox",
        parent: { id: "4" },
      },
      {
        id: "4-2",
        name: "결재 수신함",
        role: "ADMIN",
        url: "/approval/inbox",
        parent: { id: "4" },
      },
      {
        id: "4-3",
        name: "결재 조회",
        role: "USER",
        url: "/approval/approvalhome",
        parent: { id: "4" },
      },
    ],
  },
  {
    id: "5",
    name: "프로젝트",
    role: "USER",
    url: null,
    icon: "/images/sidebar-project.png",
    parent: undefined,
    children: [
      {
        id: "5-1",
        name: "프로젝트 관리",
        role: "USER",
        url: "/project/search",
        parent: { id: "5" },
        children: [
          {
            name: "프로젝트 생성",
            url: "/project/write",
            parent: { id: "5-1" },
          },
          {
                name: "프로젝트 상세",
                url: "/project/view",
                parent: { id: "5-1" },
          },
          {
                name: "프로젝트 팀",
                url: "/project/team",
                parent: { id: "5-1" },
          },
        ],
      },
      {
        id: "5-2",
        name: "요구사항 관리",
        role: "USER",
        url: "/requirement/search?prjId=all",
        parent: { id: "5" },
      },
      {
        id: "5-3",
        name: "이슈 관리",
        role: "USER",
        url: "/issue",
        parent: { id: "5" },
        children: [
          {
            name: "이슈 내용",
            url: "/issue/view",
            parent: { id: "5-3" },
          },
        ],
      },
      {
        id: "5-4",
        name: "QnA ",
        role: "USER",
        url: "/qna",
        parent: { id: "5" },
      },
      {
        id: "5-5",
        name: "지식관리",
        role: "USER",
        url: "/knowledge",
        parent: { id: "5" },
      },
      {
        id: "5-6",
        name: "산출물 관리",
        role: "USER",
        url: "/output",
        parent: { id: "5" },
      },
      {
        id: "5-7",
        name: "설문 관리",
        role: "USER",
        url: "/survey",
        parent: { id: "5" },
      },
      {
        id: "5-8",
        name: "후기 관리",
        role: "USER",
        url: "/review",
        parent: { id: "5" },
        children: [
          {
            name: "후기작성",
            url: "/review/prjId/*/write",
            parent: { id: "5-8" },
          },
        ],
      },
    ],
  },
  {
    id: "6",
    name: "쪽지",
    role: "USER",
    url: null,
    icon: "/images/sidebar-memo.png",
    parent: undefined,
    children: [
      {
        id: "6-1",
        name: "쪽지 작성",
        role: "USER",
        url: "/memo/write",
        parent: { id: "6" },
      },
      {
        id: "6-2",
        name: "받은 쪽지함",
        role: "USER",
        url: "/memo/receive",
        parent: { id: "6" },
        children: [
          {
            name: "쪽지상세",
            url: "/memo/receive/view",
            parent: { id: "6-2" },
          },
        ],
      },
      {
        id: "6-3",
        name: "보낸 쪽지함",
        role: "USER",
        url: "/memo/sent",
        parent: { id: "6" },
        children: [
          { name: "쪽지상세", url: "/memo/sent/view", parent: { id: "6-3" } },
        ],
      },
      {
        id: "6-4",
        name: "쪽지 보관함",
        role: "USER",
        url: "/memo/storage",
        parent: { id: "6" },
        children: [
          {
            name: "쪽지상세",
            url: "/memo/storage/view",
            parent: { id: "6-4" },
          },
        ],
      },
    ],
  },
  {
    id: "7",
    name: "시스템관리",
    role: "ADMIN",
    url: null,
    icon: "/images/sidebar-memo.png",
    parent: undefined,
    children: [
      {
        id: "7-1",
        name: "공통코드 관리",
        role: "ADMIN",
        url: "/commoncode",
        parent: { id: "7" },
      },
    ],
  },
];

window.getActiveFrameDataset = function () {
  return window.getActiveFrame().parentElement.dataset;
};

window.getActiveFrame = function () {
  return document.querySelector(".frame-active > iframe");
};

window.getLocationPathInFrame = function () {
  var frame = document.querySelector(".frame-active > iframe");
  var path = frame.contentDocument.location.pathname;
  console.log("path: ", path);
  return path;
};

window.findMenuObject = function findMenuObject(
  path,
  findType = "url",
  menuObject
) {
  if (!path) {
    path = getLocationPathInFrame();
  }

  if (!menuObject) {
    pathArray = [];
  }

  var targetObject = menuObject || menuList;

  for (const i in targetObject) {
    var menu = targetObject[i];
    var value = menu[findType];

    var isMatch =
      (value && value === path) ||
      (findType === "url" &&
        value &&
        new RegExp("^" + value.replaceAll("*", ".+") + "$").test(path));

    if (isMatch) {
      return menu;
    } else if (menu.children) {
      var pathResult = findMenuObject(path, findType, menu.children);
      if (pathResult) {
        return pathResult;
      }
    }
  }

  return null;
};

window.makeTree = function (pathObject, tree = []) {
  if (tree.length == 0) {
    tree.push(pathObject);
  }

  var parent = window.findMenuObject(pathObject.parent.id, "id");
  if (parent) {
    tree.push(parent);
    if (parent.parent) {
      makeTree(parent, tree);
    }
  }
  return tree;
};

window.makePath = function (tree) {
  var pathArray = [];

  var len = tree.length - 1;
  for (let i = len; i >= 0; i--) {
    pathArray.push(tree[i]);
  }

  return pathArray;
};

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
