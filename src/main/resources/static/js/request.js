//1. On document Ready
$(document).ready(function () {
    //1.1 Enable/Disable active menu tabs
    $(".tab__link").click(function () {
        $(".tab__link").removeClass("tab--is-active")
        $(".tab__link").removeClass("active")
        $(this).addClass("tab--is-active")
    });

    // 1.2 Feedback - Success
    let requestfeedback = localStorage.getItem("requestFeedback");

    if (requestfeedback === "true"){
        localStorage.clear()
        $('#success_message_text').empty()
        $('#success_message_text').text(' The Request was sent!');
        $('#success_message').removeClass('d-none').addClass('show');
        $("#success_message").fadeTo(1500, 1);
        setTimeout(function(){
            $("#success_message").fadeTo(1500, 0);
        },5000);
    }

});

//2. Feedback - Store local storage
function requestFeedback() {
    localStorage.setItem("requestFeedback","true");
}

$("#menuRequest").addClass("is-active")

