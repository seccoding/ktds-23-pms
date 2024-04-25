let menuList = [];

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

  var parent = window.findMenuObject(pathObject.parent, "id");
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
  $.get("/menu", function (response) {
    menuList = response;

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
      if (item.url) {
        menuName = $("<a></a>");
        menuName.attr({
          href: "javascript:void(0);",
          "data-menu-id": item.id,
          "data-menu-url": item.url,
        });
        menuName.text(item.name);
        menuName.on("click", menuAnchorClickHandler);
      } else {
        menuName.text(item.name);
      }
      menuName.addClass("sidedbar-menu-name");
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
    reloadTabs();
    openDashboard();
  });
});

function openDashboard() {
  if (window.name === "main") {
    menuAnchorClickHandler(null, $(".sidebar").find("a[data-menu-id=8]"));
  }
}

function reloadTabs() {
  if (window.name === "main") {
    var activetabs = sessionStorage.getItem("activetabs");

    if (activetabs) {
      activetabs.split(",").forEach(function (menuId) {
        $(".sidebar")
          .find("a[data-menu-id=" + menuId + "]")
          .click();
      });
    }
  }
}
