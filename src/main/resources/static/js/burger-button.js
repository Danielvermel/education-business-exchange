//Toggle burger menu
$(".js-primary-nav").click(function () {
    if ($(".js-primary-nav").hasClass("nav--is-open")) {
        $(".js-primary-nav").removeClass("nav--is-open")
        $(".js-main-content-area").removeClass("nav--is-open")
    } else {
        $(".js-primary-nav").addClass("nav--is-open")
        $(".js-main-content-area").addClass("nav--is-open")
    }
});