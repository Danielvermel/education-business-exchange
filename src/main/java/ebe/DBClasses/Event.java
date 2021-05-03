package ebe.DBClasses;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Event {

    private int EventID;
    private String EventName;
    private int TypeOfEventID;
    private String TypeOfEventName;
    private @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date EventDateAndTime ;
    private @DateTimeFormat(pattern = "yyyy-MM-dd") Date EventDate ;
    private @DateTimeFormat(pattern = "HH:mm") Date EventTime ;
    private String EventVenueName;
    private String EventAddressCity;
    private String EventAddressStreet;
    private String EventAddressNumber;
    private String EventVenuePostcode;
    private String EventSummary;
    private Boolean isPublic;
    private Boolean isCancelled;
    private String NameOfAdviser;
    private int NumberOfAttendees;
    private Boolean PromotesApprenticeships;
    private Boolean PromotesWelshLanguage;
    private Boolean ChallengesGenderStereotypes;
    private Boolean IsFeatured;

    public Event() {
    }

    public Event(int eventID, String eventName, int typeOfEventID, Date eventDateAndTime, String eventVenueName, String eventAddressCity, String eventAddressStreet, String eventAddressNumber, String eventVenuePostcode, String eventSummary, Boolean isPublic, Boolean isCancelled, String nameOfAdviser, int numberOfAttendees, Boolean promotesApprenticeships, Boolean promotesWelshLanguage, Boolean challengesGenderStereotypes, Boolean isFeatured) {
        EventID = eventID;
        EventName = eventName;
        TypeOfEventID = typeOfEventID;
        EventDateAndTime = eventDateAndTime;
        EventVenueName = eventVenueName;
        EventAddressCity = eventAddressCity;
        EventAddressStreet = eventAddressStreet;
        EventAddressNumber = eventAddressNumber;
        EventVenuePostcode = eventVenuePostcode;
        EventSummary = eventSummary;
        this.isPublic = isPublic;
        this.isCancelled = isCancelled;
        NameOfAdviser = nameOfAdviser;
        NumberOfAttendees = numberOfAttendees;
        PromotesApprenticeships = promotesApprenticeships;
        PromotesWelshLanguage = promotesWelshLanguage;
        ChallengesGenderStereotypes = challengesGenderStereotypes;
        IsFeatured = isFeatured;
    }

    public Event(String eventName, int typeOfEventID, Date eventDateAndTime, String eventVenueName, String eventAddressCity, String eventAddressStreet, String eventAddressNumber, String eventVenuePostcode, String eventSummary, Boolean isPublic, Boolean isCancelled, String nameOfAdviser, int numberOfAttendees, Boolean promotesApprenticeships, Boolean promotesWelshLanguage, Boolean challengesGenderStereotypes, Boolean isFeatured) {
        EventName = eventName;
        TypeOfEventID = typeOfEventID;
        EventDateAndTime = eventDateAndTime;
        EventVenueName = eventVenueName;
        EventAddressCity = eventAddressCity;
        EventAddressStreet = eventAddressStreet;
        EventAddressNumber = eventAddressNumber;
        EventVenuePostcode = eventVenuePostcode;
        EventSummary = eventSummary;
        this.isPublic = isPublic;
        this.isCancelled = isCancelled;
        NameOfAdviser = nameOfAdviser;
        NumberOfAttendees = numberOfAttendees;
        PromotesApprenticeships = promotesApprenticeships;
        PromotesWelshLanguage = promotesWelshLanguage;
        ChallengesGenderStereotypes = challengesGenderStereotypes;
        IsFeatured = isFeatured;
    }

    public Event(int eventID, String eventName, int typeOfEventID, Date eventDate, Date eventTime, String eventVenueName, String eventAddressCity, String eventAddressStreet, String eventAddressNumber, String eventVenuePostcode, String eventSummary, Boolean isPublic, Boolean isCancelled, String nameOfAdviser, int numberOfAttendees, Boolean promotesApprenticeships, Boolean promotesWelshLanguage, Boolean challengesGenderStereotypes, Boolean isFeatured) {
        EventID = eventID;
        EventName = eventName;
        TypeOfEventID = typeOfEventID;
        EventDate = eventDate;
        EventTime = eventTime;
        EventVenueName = eventVenueName;
        EventAddressCity = eventAddressCity;
        EventAddressStreet = eventAddressStreet;
        EventAddressNumber = eventAddressNumber;
        EventVenuePostcode = eventVenuePostcode;
        EventSummary = eventSummary;
        this.isPublic = isPublic;
        this.isCancelled = isCancelled;
        NameOfAdviser = nameOfAdviser;
        NumberOfAttendees = numberOfAttendees;
        PromotesApprenticeships = promotesApprenticeships;
        PromotesWelshLanguage = promotesWelshLanguage;
        ChallengesGenderStereotypes = challengesGenderStereotypes;
        IsFeatured = isFeatured;
    }

    public int getEventID() {
        return EventID;
    }

    public void setEventID(int eventID) {
        EventID = eventID;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public int getTypeOfEventID() {
        return TypeOfEventID;
    }

    public void setTypeOfEventID(int typeOfEventID) {
        TypeOfEventID = typeOfEventID;
    }

    public String getTypeOfEventName() {
        return TypeOfEventName;
    }

    public void setTypeOfEventName(String typeOfEventName) {
        TypeOfEventName = typeOfEventName;
    }

    public Date getEventDateAndTime() {
        return EventDateAndTime;
    }

    public void setEventDateAndTime(Date eventDateAndTime) {
        EventDateAndTime = eventDateAndTime;
    }

    public String getEventVenueName() {
        return EventVenueName;
    }

    public void setEventVenueName(String eventVenueName) {
        EventVenueName = eventVenueName;
    }

    public String getEventAddressCity() {
        return EventAddressCity;
    }

    public void setEventAddressCity(String eventAddressCity) {
        EventAddressCity = eventAddressCity;
    }

    public String getEventAddressStreet() {
        return EventAddressStreet;
    }

    public void setEventAddressStreet(String eventAddressStreet) {
        EventAddressStreet = eventAddressStreet;
    }

    public String getEventAddressNumber() {
        return EventAddressNumber;
    }

    public void setEventAddressNumber(String eventAddressNumber) {
        EventAddressNumber = eventAddressNumber;
    }

    public String getEventVenuePostcode() {
        return EventVenuePostcode;
    }

    public void setEventVenuePostcode(String eventVenuePostcode) {
        EventVenuePostcode = eventVenuePostcode;
    }

    public String getEventSummary() {
        return EventSummary;
    }

    public void setEventSummary(String eventSummary) {
        EventSummary = eventSummary;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Boolean getCancelled() {
        return isCancelled;
    }

    public void setCancelled(Boolean cancelled) {
        isCancelled = cancelled;
    }

    public String getNameOfAdviser() {
        return NameOfAdviser;
    }

    public void setNameOfAdviser(String nameOfAdviser) {
        NameOfAdviser = nameOfAdviser;
    }

    public int getNumberOfAttendees() {
        return NumberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        NumberOfAttendees = numberOfAttendees;
    }

    public Boolean getPromotesApprenticeships() {
        return PromotesApprenticeships;
    }

    public void setPromotesApprenticeships(Boolean promotesApprenticeships) {
        PromotesApprenticeships = promotesApprenticeships;
    }

    public Boolean getPromotesWelshLanguage() {
        return PromotesWelshLanguage;
    }

    public void setPromotesWelshLanguage(Boolean promotesWelshLanguage) {
        PromotesWelshLanguage = promotesWelshLanguage;
    }

    public Boolean getChallengesGenderStereotypes() {
        return ChallengesGenderStereotypes;
    }

    public void setChallengesGenderStereotypes(Boolean challengesGenderStereotypes) {
        ChallengesGenderStereotypes = challengesGenderStereotypes;
    }

    public Date getEventDate() {
        return EventDate;
    }

    public void setEventDate(Date eventDate) {
        EventDate = eventDate;
    }

    public Date getEventTime() {
        return EventTime;
    }

    public void setEventTime(Date eventTime) {
        EventTime = eventTime;
    }

    public Boolean getFeatured() {
        return IsFeatured;
    }

    public void setFeatured(Boolean featured) {
        IsFeatured = featured;
    }
}


