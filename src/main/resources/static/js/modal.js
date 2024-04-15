function alertModal(title, bodyHtml) {
    $.get("/html/modal.html", function(modalHtml) {
        modalHtml = modalHtml.replaceAll("#title#", title);
        modalHtml = modalHtml.replaceAll("#html#", bodyHtml);

        var alertModal = $(modalHtml);
        alertModal.find("button").on("click", function(event) {
            alertModal[0].close();
        })
        $("body").prepend(alertModal);
        alertModal[0].showModal();
    })
}