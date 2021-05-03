package ebe.API;

import ebe.DBMethods.EmployerQueries;
import ebe.DBMethods.EventQueries;
import ebe.DBMethods.SchoolQueries;
import ebe.DBMethods.VacancyQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class EventAPI {

    private EmployerQueries EmployerQrys;
    private EventQueries EventQrys;
    private SchoolQueries SchoolQrys;
    private VacancyQueries VacancyQrys;

    @Autowired
    public EventAPI(EmployerQueries em, EventQueries ev, SchoolQueries sc, VacancyQueries va){
        EmployerQrys = em;
        EventQrys = ev;
        SchoolQrys = sc;
        VacancyQrys = va;
    }

    ///////////////////////    CREATE     ////////////////////////////////
    //1. Create Events
    @RequestMapping(value="/ebe/api/create/event", method= RequestMethod.GET)
    public boolean createEvent(
            @RequestParam(name="eventName", required = true) String EventName,
            @RequestParam(name="typeOfEventID", required = true) int TypeOfEventID,
            @RequestParam(name="eventDate", required = true) String EventDate,
            @RequestParam(name="eventTime", required = true) String EventTime,
            @RequestParam(name="eventVenueName", required = false) String EventVenueName,
            @RequestParam(name="eventAddressCity", required = true) String EventAddressCity,
            @RequestParam(name="eventAddressStreet", required = true) String EventAddressStreet,
            @RequestParam(name="eventAddressNumber", required = true) String EventAddressNumber,
            @RequestParam(name="eventPostcode", required = true) String EventPostcode,
            @RequestParam(name="eventSummary", required = true) String EventSummary,
            @RequestParam(name="isPublic", required = true) Boolean IsPublic,
            @RequestParam(name="isCancelled", required = true) Boolean isCancelled,
            @RequestParam(name="nameOfAdviser", required = false) String NameOfAdviser,
            @RequestParam(name="numberOfAttendees", required = false) int NumberOfAttendees,
            @RequestParam(name="promotesApprenticeships", required = true) Boolean PromotesApprenticeships,
            @RequestParam(name="promotesWelshLanguage", required = true) Boolean PromotesWelshLanguage,
            @RequestParam(name="challengesGenderStereotypes", required = true) Boolean ChallengesGenderStereotypes,
            @RequestParam(name="isFeatured", required = true) Boolean isFeatured,
            @RequestParam(name="employerAttending", required = true) String EmployerAttending,
            @RequestParam(name="schoolAttending", required = true) String SchoolAttending) throws ParseException {

        // ---------------------------
        //1. Create the ArrayList that are going to be used to populate the database
        String EventDateAndTime = EventDate + " " + EventTime;
        ArrayList<Integer> employerIdList = new ArrayList<Integer>();
        ArrayList<Integer> schoolIdList = new ArrayList<Integer>();

        // ---------------------------
        //2. Populate the ArrayList
        for (String employerID : EmployerAttending.split(",")) {
            employerIdList.add(Integer.parseInt(employerID));
        }
        for (String schoolID : SchoolAttending.split(",")) {
            schoolIdList.add(Integer.parseInt(schoolID));
        }

        // ---------------------------
        //3. Create the Event
        EventQrys.createEvent(EventName, TypeOfEventID, EventDateAndTime,  EventVenueName,  EventAddressCity,
                EventAddressStreet, EventAddressNumber, EventPostcode, EventSummary, IsPublic, isCancelled, NameOfAdviser,
                NumberOfAttendees,  PromotesApprenticeships,  PromotesWelshLanguage, ChallengesGenderStereotypes, isFeatured);

        //      Get Event Created Id
        int eventId = EventQrys.getLastEventCreated(EventName);

        //      Insert into the Employer / Event intersection table
        EventQrys.createEmployerEventIntersection(eventId,employerIdList);

        //      Insert into the School / Event intersection table
        EventQrys.createSchoolEventIntersection(eventId, schoolIdList);

        return true;
    }


    ///////////////////////    UPDATE     ////////////////////////////////
    //2. Update Events
    @RequestMapping(value="/ebe/api/update/event", method= RequestMethod.GET)
    public Boolean updateEvent(
            @RequestParam(name="eventID", required = true) int EventID,
            @RequestParam(name="eventName", required = true) String EventName,
            @RequestParam(name="typeOfEventID", required = true) int TypeOfEventID,
            @RequestParam(name="eventDate", required = true) String EventDate,
            @RequestParam(name="eventTime", required = true) String EventTime,
            @RequestParam(name="eventVenueName", required = false) String EventVenueName,
            @RequestParam(name="eventAddressCity", required = true) String EventAddressCity,
            @RequestParam(name="eventAddressStreet", required = true) String EventAddressStreet,
            @RequestParam(name="eventAddressNumber", required = true) String EventAddressNumber,
            @RequestParam(name="eventPostcode", required = true) String EventPostcode,
            @RequestParam(name="eventSummary", required = true) String EventSummary,
            @RequestParam(name="isPublic", required = true) Boolean IsPublic,
            @RequestParam(name="isCancelled", required = true) Boolean isCancelled,
            @RequestParam(name="nameOfAdviser", required = false) String NameOfAdviser,
            @RequestParam(name="numberOfAttendees", required = false) int NumberOfAttendees,
            @RequestParam(name="promotesApprenticeships", required = true) Boolean PromotesApprenticeships,
            @RequestParam(name="promotesWelshLanguage", required = true) Boolean PromotesWelshLanguage,
            @RequestParam(name="challengesGenderStereotypes", required = true) Boolean ChallengesGenderStereotypes,
            @RequestParam(name="isFeatured", required = true) Boolean isFeatured,
            @RequestParam(name="employerAttending", required = true) String EmployerAttending,
            @RequestParam(name="schoolAttending", required = true) String SchoolAttending) throws ParseException {

        // ---------------------------
        //1. Create the ArrayList that are going to be used to populate the database
        String EventDateAndTime = EventDate + " " + EventTime;
        ArrayList<Integer> employerIdList = new ArrayList<Integer>();
        ArrayList<Integer> schoolIdList = new ArrayList<Integer>();

        // ---------------------------
        //2. Populate the ArrayList
        for (String employerID : EmployerAttending.split(",")) {
            employerIdList.add(Integer.parseInt(employerID));
        }
        for (String schoolID : SchoolAttending.split(",")) {
            schoolIdList.add(Integer.parseInt(schoolID));
        }

        // ---------------------------
        //3. Update the Event
        EventQrys.updateEvent(EventID, EventName, TypeOfEventID, EventDateAndTime,  EventVenueName,  EventAddressCity,
                EventAddressStreet, EventAddressNumber, EventPostcode, EventSummary, IsPublic, isCancelled, NameOfAdviser,
                NumberOfAttendees,  PromotesApprenticeships,  PromotesWelshLanguage,ChallengesGenderStereotypes, isFeatured);


        //      Insert into the Employer / Event intersection table
        EventQrys.updateEmployerEventIntersection(EventID,employerIdList);

        //      Insert into the School / Event intersection table
        EventQrys.updateSchoolEventIntersection(EventID, schoolIdList);

        return true;
    }
    ///////////////////////    DELETE     ////////////////////////////////
    //3. Delete Events
    @DeleteMapping("/ebe/api/delete/event")
    public boolean deleteEvents(@RequestParam(value="eventId") Integer eventId){

        if (EventQrys.deleteEvent(eventId) == 1) {
            return true;
        } else {
            return false;
        }
    }

    ///////////////////////    SORT BY     ////////////////////////////////
    @GetMapping("/ebe/api/event/sortBy")
    public List<Integer> SortBy(@RequestParam(value="sortBy") String sortBy,
                                @RequestParam(value="orderBy") String orderBy){

        List<Integer> orderEventIds = new ArrayList<Integer>();
        if(sortBy.equals("Name")){
            orderEventIds = EventQrys.sortByEventByName(orderBy);
        }

        if(sortBy.equals("Date")){
            orderEventIds = EventQrys.sortByEventByDate(orderBy);
        }

        return orderEventIds;
    }

    ///////////////////////    FILTER     ////////////////////////////////
    @GetMapping("/ebe/api/event/filter")
    public List<Integer> filterEvents(@RequestParam(value="typeOfEventID") String typeOfEventID,
                                      @RequestParam(value="nameOfAdviser") String nameOfAdviser,
                                      @RequestParam(value="eventPreferences") String eventPreferences){

        List<Integer> typeOfEventList = new ArrayList<Integer>();
        List<String> nameOfAdviserList = new ArrayList<String>();
        int PromotesApprenticeships = 0;
        int PromotesWelshLanguage = 0;
        int ChallengesGenderStereotypes = 0;
        int IsFeatured = 0;

        if (!typeOfEventID.equals("")) {
            for (String typeOfEvent : typeOfEventID.split(",")) {
                typeOfEventList.add(Integer.parseInt(typeOfEvent));
            }
        }  else{
            typeOfEventList = Arrays.asList();
        }

        if (!nameOfAdviser.equals("")) {
            for (String name : nameOfAdviser.split(",")) {
                nameOfAdviserList.add(name);
            }
        }  else{
            nameOfAdviserList = Arrays.asList();
        }

        if (!eventPreferences.equals("")) {
            for (String preference : eventPreferences.split(",")) {
                if (preference.equals("1")) {
                    PromotesApprenticeships = 1;
                } else if (preference.equals("2")) {
                    PromotesWelshLanguage = 1;
                } else if (preference.equals("3")) {
                    ChallengesGenderStereotypes = 1;
                } else if (preference.equals("4")) {
                    IsFeatured = 1;
                }
            }
        }

        List<Integer> ids = EventQrys.filterEvents(typeOfEventList, nameOfAdviserList,
                PromotesApprenticeships, PromotesWelshLanguage,  ChallengesGenderStereotypes, IsFeatured);

        return ids;
    }

}
