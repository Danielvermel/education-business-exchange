
//1. Create Event
function createNewEvent() {

    // 1.1. validation
    let verifier
    verifier = validateForm();
    // 1.2. Error Message
    if(!verifier){
        $('#failed_message_text').text("The Form was not filled properly.");
        $('#failed_message').removeClass('d-none').addClass('show');
        $("#failed_message").fadeTo(1500, 1);
        setTimeout(function(){
            $("#failed_message").fadeTo(1500, 0);
        },5000);
        return
    }

    let baseUri = "/ebe/api/create/event";
    let eventName_url = "eventName=" + $('input[id=event-name]').val();
    let typeOfEventID_url = "typeOfEventID=" + $('select[id=event-type]').val();
    let eventDate_url = "eventDate=" + $('input[id=event-date]').val();
    let eventTime_url = "eventTime=" + $('input[id=event-time]').val();
    let eventVenueName_url = "eventVenueName=" + $('input[id=event-venue]').val();
    let eventAddressCity_url = "eventAddressCity=" + $('input[id=event-city]').val();
    let eventAddressStreet_url = "eventAddressStreet=" + $('input[id=event-street]').val();
    let eventAddressNumber_url = "eventAddressNumber=" + $('input[id=event-number]').val();
    let eventPostcode_url = "eventPostcode=" + $('input[id=event-postcode]').val();
    let eventSummary_url = "eventSummary=" + $('textarea[id=event-summary]').val();
    let isPublic_url = "isPublic=" + $('select[id=event-public]').val();
    let isCancelled_url = "isCancelled=" + $('select[id=event-cancelled]').val();
    let nameOfAdviser_url = "nameOfAdviser=" + $('input[id=adviser-name]').val();
    let numberOfAttendees_url = "numberOfAttendees=" + $('input[id=no-attendees]').val();
    let promotesApprenticeships_url="promotesApprenticeships=" + $('select[id=promote-apprenticheship]').val();
    let promotesWelshLanguage_url = "promotesWelshLanguage=" + $('select[id=conducted-welsh]').val();
    let challengesGenderStereotypes_url = "challengesGenderStereotypes=" + $('select[id=challenger-gender]').val();
    let isFeatured_url = "isFeatured=" + $('select[id=event-featured]').val();
    let employerAttending_url = "employerAttending=" + $('select[id=employers-attending]').val();
    let schoolAttending_url = "schoolAttending=" + $('select[id=schools-attending]').val();

    var fullUri = baseUri + "?" + "&" + eventName_url+ "&" + typeOfEventID_url + "&" + eventDate_url + "&" + eventTime_url + "&"
        + eventVenueName_url + "&" + eventAddressCity_url  + "&" + eventAddressStreet_url  + "&" + eventAddressNumber_url + "&"
        + eventPostcode_url + "&" + eventSummary_url+ "&" + isPublic_url + "&" + isCancelled_url + "&" +nameOfAdviser_url +"&"
        + numberOfAttendees_url + "&" + promotesApprenticeships_url  + "&" + promotesWelshLanguage_url + "&" + challengesGenderStereotypes_url  + "&"
        + isFeatured_url + "&" + employerAttending_url + "&" + schoolAttending_url ;

    var token = $("meta[name='_csrf']").attr("content");    // Used to bypass Spring Boot's CSRF protocol     -- Solution taken from 'https://stackoverflow.com/questions/34747437/use-of-spring-csrf-with-ajax-rest-call-and-html-page-with-thymeleaf' on Nov 26th 2019
    var header = $("meta[name='_csrf_header']").attr("content");    // Used to bypass Spring Boot's CSRF protocol
    console.log(fullUri)

    $.ajax({
        type: "GET", url: fullUri,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            if (data === true) {
                localStorage.setItem("eventAdded","true");
                location.assign("/ebe/events")
            } else {
                alert("There was an error, please try again.")
                alert(data.responseText)
                alert(data)
            }
        },
        error: function (data) {
            $('#failed_message_text').empty()
            $('#failed_message_text').text("Something went wrong with the submission.");
            $('#failed_message').removeClass('d-none').addClass('show');
            $("#failed_message").fadeTo(1500, 1);
            setTimeout(function(){
                $("#failed_message").fadeTo(1500, 0);
            },5000);
            console.log(data.responseText);
        }
    });

};

//2. Update Event
function UpdateThisEvent(){
    // 2.1. validation
    let verifier
    verifier = validateForm();
    // 2.2. Error Message
    if(!verifier){
        $('#failed_message_text').empty()
        $('#failed_message_text').text("The Form was not filled properly.");
        $('#failed_message').removeClass('d-none').addClass('show');
        $("#failed_message").fadeTo(1500, 1);
        setTimeout(function(){
            $("#failed_message").fadeTo(1500, 0);
        },5000);
        return
    }

    var baseUri = "/ebe/api/update/event";
    var eventID_url = "eventID=" + $('input[id=event-id]').val();
    var eventName_url = "eventName=" + $('input[id=event-name]').val();
    var typeOfEventID_url = "typeOfEventID=" + $('select[id=event-type]').val();
    var eventDate_url = "eventDate=" + $('input[id=event-date]').val();
    var eventTime_url = "eventTime=" + $('input[id=event-time]').val();
    var eventVenueName_url = "eventVenueName=" + $('input[id=event-venue]').val();
    var eventAddressCity_url = "eventAddressCity=" + $('input[id=event-city]').val();
    var eventAddressStreet_url = "eventAddressStreet=" + $('input[id=event-street]').val();
    var eventAddressNumber_url = "eventAddressNumber=" + $('input[id=event-number]').val();
    var eventPostcode_url = "eventPostcode=" + $('input[id=event-postcode]').val();
    var eventSummary_url = "eventSummary=" + $('textarea[id=event-summary]').val();
    var nameOfAdviser_url = "nameOfAdviser=" + $('input[id=adviser-name]').val();
    var numberOfAttendees_url = "numberOfAttendees=" + $('input[id=no-attendees]').val();

    var isPublic_url = "isPublic=" + $('select[id=event-public]').val();
    var isCancelled_url = "isCancelled=" + $('select[id=event-cancelled]').val();
    var promotesApprenticeships_url="promotesApprenticeships=" + $('select[id=promote-apprenticheship]').val();
    var promotesWelshLanguage_url = "promotesWelshLanguage=" + $('select[id=conducted-welsh]').val();
    var challengesGenderStereotypes_url = "challengesGenderStereotypes=" + $('select[id=challenger-gender]').val();
    var isFeatured_url = "isFeatured=" + $('select[id=event-featured]').val();

    var employerAttending_url = "employerAttending=" + $('select[id=employers-attending]').val();
    var schoolAttending_url = "schoolAttending=" + $('select[id=schools-attending]').val();


    var fullUri = baseUri + "?" + "&" + eventID_url + "&" + eventName_url+ "&" + typeOfEventID_url + "&" + eventDate_url + "&" + eventTime_url + "&"
        + eventVenueName_url + "&" + eventAddressCity_url  + "&" + eventAddressStreet_url  + "&" + eventAddressNumber_url + "&"
        + eventPostcode_url + "&" + eventSummary_url+ "&" + isPublic_url + "&" + isCancelled_url + "&" +nameOfAdviser_url +"&"
        + numberOfAttendees_url + "&" + promotesApprenticeships_url  + "&" + promotesWelshLanguage_url + "&" + challengesGenderStereotypes_url  + "&"
        + isFeatured_url + "&" + employerAttending_url + "&" + schoolAttending_url ;

    var token = $("meta[name='_csrf']").attr("content");    // Used to bypass Spring Boot's CSRF protocol     -- Solution taken from 'https://stackoverflow.com/questions/34747437/use-of-spring-csrf-with-ajax-rest-call-and-html-page-with-thymeleaf' on Nov 26th 2019
    var header = $("meta[name='_csrf_header']").attr("content");    // Used to bypass Spring Boot's CSRF protocol
    console.log(fullUri);
    //Validates if there is any file to be submited
    if(!($('#file-upload-input').val() =="")){
        uploadFile()
    }

    $.ajax({
        type: "GET", url: fullUri,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            if (data === true) {
                localStorage.setItem("eventUpdated", 'true')
                // location.assign("/events")
                location.reload()
            } else {
                alert("There was an error, please try again.")
                alert(data.responseText)
                alert(data)
            }
        },
        error: function (data) {
            $('#failed_message_text').empty()
            $('#failed_message_text').text("Something went wrong with the submission.");
            $('#failed_message').removeClass('d-none').addClass('show');
            $("#failed_message").fadeTo(1500, 1);
            setTimeout(function(){
                $("#failed_message").fadeTo(1500, 0);
            },5000);
            console.log(data.responseText);
        }
    });
}

// 3.Delete Event
function deleteEvent(eventId) {
    var baseUri = "/ebe/api/delete/event";
    var eventId_url = "eventId=" + eventId;
    var fullUri = baseUri + "?" + eventId_url;

    var token = $("meta[name='_csrf']").attr("content");    // Used to bypass Spring Boot's CSRF protocol     -- SOlution taken from 'https://stackoverflow.com/questions/34747437/use-of-spring-csrf-with-ajax-rest-call-and-html-page-with-thymeleaf' on Nov 26th 2019
    var header = $("meta[name='_csrf_header']").attr("content");    // Used to bypass SPring Boot's CSRF protocol


    $.ajax({
        type: "DELETE", url: fullUri,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            if (data === true) {
                localStorage.setItem("eventDeleted", "true")
                location.assign("/ebe/events")
            } else {
                alert("There was an error, please try again.")
                alert(data.responseText)
                alert(data)
            }
        },
        error: function (data) {
            $('#failed_message_text').empty()
            $('#failed_message_text').text("Something went wrong with the submission.");
            $('#failed_message').removeClass('d-none').addClass('show');
            $("#failed_message").fadeTo(1500, 1);
            setTimeout(function(){
                $("#failed_message").fadeTo(1500, 0);
            },5000);
            console.log(data.responseText);
        }
    });
}

// 4.Search Events
function searchEvents(){
    // 4.1 Get the value from Search input
    let val = $('#event-search').val()

    //4.2 Remove class event found - to restart the "search"
    $(".event-found").removeClass("event-found")

    //4.3 Add classes for the right values
    $(".list-events").find(".searchable:contains('"+val+"')").closest(".event-card").addClass("event-found")

    //4.4 Add the d-none to all cards and then removes it from the ones that are filtered or searched
    $(".event-card").addClass("d-none");
    $('.event-filtered.event-found').removeClass('d-none');
}

//5. Sort Event By Name and Date
function sortEventsByNameAndDate(type, order) {

    let baseUri = "api/event/sortBy";
    let orderBy_url = "orderBy=" + order ;
    let sortBy_url = "sortBy=" + type ;

    var fullUri = baseUri + "?" + "&" + orderBy_url + "&" + sortBy_url
    var token = $("meta[name='_csrf']").attr("content");    // Used to bypass Spring Boot's CSRF protocol     -- Solution taken from 'https://stackoverflow.com/questions/34747437/use-of-spring-csrf-with-ajax-rest-call-and-html-page-with-thymeleaf' on Nov 26th 2019
    var header = $("meta[name='_csrf_header']").attr("content");    // Used to bypass Spring Boot's CSRF protocol

    console.log(fullUri)

    $.ajax({
        type: "GET",
        url: fullUri,
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            sortEvents(data);
        },
        error: function (data) {
            $('#failed_message_text').empty()
            $('#failed_message_text').text("Something went wrong with the submission.");
            $('#failed_message').removeClass('d-none').addClass('show');
            $("#failed_message").fadeTo(1500, 1);
            setTimeout(function(){
                $("#failed_message").fadeTo(1500, 0);
            },5000);
            console.log(data.responseText);
        }
    });
}

// 6. sortEvents
function sortEvents(ids){

    for (i = 0; i < ids.length; i++) {
        $("#"+ids[i]).closest(".event-card").before( $("#"+ids[i+1]).closest(".event-card"));
    }
}

//7. On document Ready
$( document ).ready(function() {
    $("select[name=event-sort-by]").change(function(){
        sortEventsByNameAndDate($(this).val(),$(this).children(":selected").attr("data-val"));
    });
});

//8. Hide Events
function hideEvents(ids){

    //8.1 remove previous filtering
    $(".event-filtered").removeClass("event-filtered");

    //8.2 add .event-filtered class to indicate which filtering results
    for (i = 0; i < ids.length; i++) {
        $("#"+ids[i]).addClass("event-filtered");
    }

    //8.3 Add the d-none to all cards and then removes it from the ones that are filtered or searched
    $(".event-card").addClass("d-none");
    $('.event-filtered.event-found').removeClass('d-none');
}

//9. Filter Events
function filterEvents() {
    var baseUri = "/ebe/api/event/filter";
    var typeOfEventID_url = "typeOfEventID=" + $('select[id=event-type]').val();
    var nameOfAdviser_url = "nameOfAdviser=" + $('select[id=event-advisor]').val();
    var eventPreferences_url = "eventPreferences=" + $('select[id=event-preference]').val();

    var fullUri = baseUri + "?" + "&" + typeOfEventID_url + "&" + nameOfAdviser_url+ "&" + eventPreferences_url;

    var token = $("meta[name='_csrf']").attr("content");    // Used to bypass Spring Boot's CSRF protocol     -- Solution taken from 'https://stackoverflow.com/questions/34747437/use-of-spring-csrf-with-ajax-rest-call-and-html-page-with-thymeleaf' on Nov 26th 2019
    var header = $("meta[name='_csrf_header']").attr("content");    // Used to bypass Spring Boot's CSRF protocol

    console.log(fullUri)

    $.ajax({
        type: "GET",
        url: fullUri,
        dataType: 'json',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            hideEvents(data);
            console.log(data);
        },
        error: function (data) {
            alert("FAIL");
            alert(data.responseText);
            alert(data.toString());
        }
    });
}

//10. Feedback - Add Event
eventAdded = localStorage.getItem("eventAdded");

if (eventAdded === "true"){
    localStorage.clear()
    $('#success_message_text').empty()
    $('#success_message_text').text(' The event was created!');
    $('#success_message').removeClass('d-none').addClass('show');
    $("#success_message").fadeTo(1500, 1);
    setTimeout(function(){
        $("#success_message").fadeTo(1500, 0);
    },5000);
}

//11. Feedback - Remove Event
eventDeleted = localStorage.getItem("eventDeleted");
console.log(eventDeleted)
console.log("aqui")

if (eventDeleted === "true"){
    localStorage.clear()
    $('#success_message_text').empty()
    $('#success_message_text').text(' The event was deleted!');
    $('#success_message').removeClass('d-none').addClass('show');
    $("#success_message").fadeTo(1500, 1);
    setTimeout(function(){
        $("#success_message").fadeTo(1500, 0);
    },5000);
}
//12. Feedback - Update Event
eventUpdated = localStorage.getItem("eventUpdated");

if (eventUpdated === "true"){
    localStorage.clear()
    $('#success_message_text').empty()
    $('#success_message_text').text(' The event was updated!');
    $('#success_message').removeClass('d-none')
    $("#success_message").fadeTo(1500, 1);
    setTimeout(function(){$("#success_message").fadeTo(1500, 0); },5000);
}

//13. Validation Function
function validateForm(){

    let verifier = true;
    let attributesArray = $(".form-required")

    // 13.1 Remove the Valid/Invalid class
    $(".form-required").removeClass("is-invalid ").removeClass("is-valid ")
    $(".selectpicker").parent().removeClass("is-invalid").removeClass("is-valid ")

    // 13.2 Add The Valid class to all elements
    $(".selectpicker").add("is-valid ")

        // 13.3 Validate inputs
        for (let i = 0; i < attributesArray.length; i++) {
            if (attributesArray[i].value === "") {
                // 13.3.1 Remove The Valid/Invalid class
                attributesArray[i].classList.remove("is-invalid")
                attributesArray[i].classList.remove("is-valid")
                // 13.3.2 Add the Invalid class
                attributesArray[i].classList.add("is-invalid")
                console.log("empty test - inputs")
            }
        }

    // 13.4 Change variable data to selectpickers
    attributesArray = $(".selectpicker")

    //13.5 Validate selectpickers
    for (let i = 0; i < attributesArray.length; i++) {
        if (attributesArray[i].value === "") {
            // 13.5.1 Remove The Valid/Invalid class
            attributesArray[i].classList.remove("is-invalid")
            attributesArray[i].classList.remove("is-valid")
            // 13.5.2 Add the Invalid class
            attributesArray[i].parentNode.classList.add("is-invalid")
            console.log("empty test - selectpickers")
        }
    }

    // 13.6 Validate Input(PostCode)

    // 13.6.1 Set the RegEx and test it
    let postCodeVal = /[a-z][a-z]\d\d\s\d[a-z][a-z]|[a-z][a-z]\d\s\d[a-z][a-z]|[a-z]\d\s\d[a-z][a-z]|[a-z][a-z]\d[a-z]\s\d[a-z][a-z]|[a-z]\d\d\s\d[a-z][a-z]/i.test($("#event-postcode").val());
    // let postCodeVal = postCodeValidation.test($("#employer-postcode").val());
    // 13.6.2 Verify if it's needed to put an invalid class
    if(!postCodeVal){
        $("#event-postcode").removeClass("is-invalid").removeClass("is-valid")
        $("#event-postcode").addClass("is-invalid")
        console.log("empty test - postcode")
    }

    //13.7 Verify if there is any invalid class
    if($(".selectpicker").parent().hasClass("is-invalid") || $(".form-required").hasClass("is-invalid")){
        verifier = false
    }
    return verifier;
}



//14. Feedback - Store local storage
function EventProfileShowInterest() {
    localStorage.setItem("EventProfileShowInterest","true");
}

$("#menuEvents").addClass("is-active")

//15. Document ready
$(document).ready(function(){
    $('#filterButton').click(function(){
        filterEvents();
    });

    $("#tooltip").hover(function(){
            $(this).tooltip('show')
        },
        function(){
            $(this).tooltip('hide')
        }
    )

    // Overwriting contains to be case insensitive, found at :
    // https://css-tricks.com/snippets/jquery/make-jquery-contains-case-insensitive/
    $.expr[":"].contains = $.expr.createPseudo(function(arg) {
        return function( elem ) {
            return $(elem).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0;
        };
    });

    //1.1.  Feedback - Success
    let eventProfileShowInterest = localStorage.getItem("EventProfileShowInterest");

    if (eventProfileShowInterest === "true"){
        localStorage.clear()
        $('#success_message_text').empty()
        $('#success_message_text').text(' Interest was registered!');
        $('#success_message').removeClass('d-none').addClass('show');
        $("#success_message").fadeTo(1500, 1);
        setTimeout(function(){
            $("#success_message").fadeTo(1500, 0);
        },5000);
    }

});


// Function to retrieve eventID from URL
var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
};

function uploadFile(){

    var myFile = $('#file-upload-input').prop('files');
    var eventID = getUrlParameter('eventId');
    var formData = new FormData();

    formData.append("ID", eventID);
    formData.append("name", "event");
    formData.append("file", myFile[0]);

    var token = $("meta[name='_csrf']").attr("content");    // Used to bypass Spring Boot's CSRF protocol     -- Solution taken from 'https://stackoverflow.com/questions/34747437/use-of-spring-csrf-with-ajax-rest-call-and-html-page-with-thymeleaf' on Nov 26th 2019
    var header = $("meta[name='_csrf_header']").attr("content");    // Used to bypass Spring Boot's CSRF protocol

    // use $.ajax() to upload file
    $.ajax({
        url: "/ebe/upload",
        type: "POST",
        data: formData,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (res) {

        },
        error: function (err) {
            console.error(err);

        }
    });
}

function deleteFile(document, fileID) {

    var eventID = getUrlParameter('eventId');
    var formData = new FormData();

    var filename = document.replace(/^.*[\\\/]/, '')

    formData.append("ID", eventID);
    formData.append("name", "event");
    formData.append("file", filename);
    formData.append("URL", document);

    var token = $("meta[name='_csrf']").attr("content");    // Used to bypass Spring Boot's CSRF protocol     -- Solution taken from 'https://stackoverflow.com/questions/34747437/use-of-spring-csrf-with-ajax-rest-call-and-html-page-with-thymeleaf' on Nov 26th 2019
    var header = $("meta[name='_csrf_header']").attr("content");    // Used to bypass Spring Boot's CSRF protocol

    // use $.ajax() to upload file
    $.ajax({
        url: "/ebe/delete",
        type: "DELETE",
        data: formData,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (res) {
            //Remove the file from frontend
            fileID.parent().parent().remove();

            $('#success_message_text').empty()
            $('#success_message_text').text('The file was deleted!');
            $('#success_message').removeClass('d-none').addClass('show');
            localStorage.clear()
            $("#success_message").fadeTo(1500, 1);
            setTimeout(function(){
                $("#success_message").fadeTo(1500, 0);
            },5000);
        },
        error: function (err) {
            console.error(err);
        }
    });
}

