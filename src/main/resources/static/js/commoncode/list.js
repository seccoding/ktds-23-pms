function loadCommonCode(pid, appendDomSelector, fnCallback) {
  $.get("/ajax/commoncode/" + pid, function (response) {
    $(appendDomSelector).html("");
    var codeList = response.data.codeList;

    codeList.forEach((codeItem) => {
      fnCallback(codeItem);
    });
  });
}

$().ready(function () {
  function fnCodeItemClick() {
    var codeId = $(this).data("code-id");

    if (codeId) {
      var appendTo = $(this).data("append-to");
      var appendTargetDom = $(appendTo);
      appendTargetDom.html("<p>Loading...</p>");

      var appendSubClass = appendTargetDom.data("sub-class");
      var appendToSubClass = appendTargetDom.data("append-to");

      loadCommonCode(codeId, appendTo, function (codeItem) {
        var itemDom = $("<div></div>");
        itemDom.addClass(appendSubClass);

        itemDom.attr("data-code-id", codeItem.cmcdId);
        itemDom.attr("data-code-pid", codeItem.cmcdPid);
        itemDom.attr("append-to", appendToSubClass);
        itemDom.text(codeItem.cmcdName);

        itemDom.on("click", fnCodeItemClick);

        appendTargetDom.append(itemDom);
      });
    }
  }

  $(".code-item").on("click", fnCodeItemClick);
});
