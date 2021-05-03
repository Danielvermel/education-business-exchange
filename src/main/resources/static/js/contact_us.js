//1. On document Ready
$(document).ready(function () {

    // 1.1 Feedback - Success
    contactFeedback = localStorage.getItem("contactFeedback");

    if (contactFeedback === "true"){
        $('#success_message_text').empty()
        $('#success_message_text').text(' The email was sent!');
        $('#success_message').removeClass('d-none').addClass('show');
        localStorage.clear()
        $("#success_message").fadeTo(1500, 1);
        setTimeout(function(){
            $("#success_message").fadeTo(1500, 0);
        },5000);
    }

});

//2. Feedback - Store local storage
function contactUsFeedback() {
    localStorage.setItem("contactFeedback","true");
}

$("#menuGetInTouch").addClass("is-active")
