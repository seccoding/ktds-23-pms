$().ready(function () {
  var deptId = $(".dept-id-data").data("dept-id");
  $.get("/ajax/employee/findbydeptid", { deptId }, function (response) {
    var empList = response.data.result;
    var body = $(".person-log-in");

    empList.forEach((item) => {
      console.log(item);
      var divDomMain = $('<div class="flex flex-col spring-hello"></div>');
      var divDomSub1 = $("<div></div>");
      var divDomSub2 = $("<div></div>");

      if (item.prfl == null) {
        item.prfl = "/images/login.png";
      }
      divDomSub1.css("background-image", "url(" + item.prfl + ")");

      divDomSub1.attr("class", "prfl-photo");
      divDomSub2.attr("class", "prfl-photo-font");
      divDomSub2.text(item.empName);
      if (item.lgnYn === "N") {
        divDomSub1.addClass("image-color-background");
        divDomSub2.addClass("bold-color-background");
      } else {
        divDomSub1.removeClass("image-color-background");
        divDomSub2.removeClass("bold-color-background");
      }

      divDomMain.append(divDomSub1);
      divDomMain.append(divDomSub2);
      body.append(divDomMain);
    });
  });




});
