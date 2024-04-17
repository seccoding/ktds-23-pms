$().ready(function () {
  var dialog = $(".alert-dialog");
  if (dialog.length > 0) {
    dialog[0].showModal();
  }
});
