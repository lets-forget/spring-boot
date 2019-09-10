$(function () {
    $.post("/header",function (data) {
        $("#header").html(data);
    });
    $.post("/footer",function (data) {
        $("#footer").html(data);
    });
});