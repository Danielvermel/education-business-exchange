package ebe.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ebe.DBClasses.Employer;
import ebe.DBClasses.Event;
import ebe.DBClasses.School;
import ebe.DBClasses.Vacancy;
import ebe.DBMethods.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Controller
public class BaseController {

    private AuthorisationQueries AuthorisationQrys;
    private EmployerQueries EmployerQrys;
    private EventQueries EventQrys;
    private SchoolQueries SchoolQrys;
    private VacancyQueries VacancyQrys;
    private StatisticsQueries statisticsQueries;

    @Autowired
    public BaseController(EmployerQueries em, EventQueries ev, SchoolQueries sc, VacancyQueries va, StatisticsQueries sq, AuthorisationQueries aq){
        EmployerQrys = em;
        EventQrys = ev;
        SchoolQrys = sc;
        VacancyQrys = va;
        statisticsQueries = sq;
        AuthorisationQrys = aq;
    }

    @Autowired
    private HttpServletRequest context; // this will provide the current instance of HttpServletRequest



//    @GetMapping("/test")
//    public String test (){
//        return "redirect:/";
//    }
    public void Authentication(HttpServletRequest request,
                              HttpSession session,
                              String email,
                              String name){
        //Get The Email and Name of the User
        request.getSession().setAttribute("SESSION_Name", name);
        request.getSession().setAttribute("SESSION_Email", email);

        //With the Email Get the Role of the User
        String role;
        System.out.println("---------------------------squi");
        role = AuthorisationQrys.getUserRoleCWS(email);
        System.out.println(role);

        if(role == "none"){
            role = AuthorisationQrys.getUserRoleTeacher(email,request);
        }
        System.out.println(role);

        request.getSession().setAttribute("SESSION_Role", role);
    }

    // HomePage
    @GetMapping("/ebe/")
    public ModelAndView HomePage( HttpServletRequest request,
                                  HttpSession session,
                                  @AuthenticationPrincipal(expression = "claims['email']") String email,
                                  @AuthenticationPrincipal(expression = "claims['name']") String name) {

        ModelAndView mv = new ModelAndView();

        Authentication(request,session,email,name);

        if((session.getAttribute("SESSION_Role") == "CWS")) {
            mv.setViewName("homepageCWS");

            int numberOfEvents = statisticsQueries.getTotalEvents();
            int numberOfVacancies = statisticsQueries.getTotalVacancies();
            int numberOfPupils = statisticsQueries.getTotalPupils();
            int numberOfEmployers = statisticsQueries.getTotalEmployers();
            int schoolAtEvents = statisticsQueries.getSchoolsAtEvents();
            int requestsBySchools = statisticsQueries.getRequestsBySchools();

            mv.addObject("numberOfEvents", numberOfEvents);
            mv.addObject("numberOfEmployers", numberOfEmployers);
            mv.addObject("numberOfVacancies", numberOfVacancies);
            mv.addObject("numberOfPupils", numberOfPupils);
            mv.addObject("schoolAtEvents", schoolAtEvents);
            mv.addObject("requestsBySchools", requestsBySchools);
            mv.addObject("name", name);
            mv.addObject("role", session.getAttribute("SESSION_Role"));
        }


        //using a random schoolID as we will have to get it from authorization level
        if(session.getAttribute("SESSION_Role") == "Teacher"){
            mv.setViewName("homepageTeacher");
            List<Event> recommendedEvents = statisticsQueries.getEventsForSchool(parseInt(session.getAttribute("SESSION_UserID").toString()));
            List<Event> featuredEvents = EventQrys.getFeaturedEvents();
            School school = SchoolQrys.getSchoolDetailsByEmail(email);

            mv.addObject("recommendedEvents",recommendedEvents);
            mv.addObject("featuredEvents",featuredEvents);
            mv.addObject("name", school.getSchoolName());
            mv.addObject("role", session.getAttribute("SESSION_Role"));
        }

        if(session.getAttribute("SESSION_Role") == "none"){
            mv.setViewName("404");
        }

        return mv;
    };

    /////////1st - Header Menu (Employer) /////////
    //1. Search Employer
    @GetMapping("/ebe/employers")
    public ModelAndView SearchEmployer(HttpServletRequest request,
                                       HttpSession session,
                                       @AuthenticationPrincipal(expression = "claims['email']") String email,
                                       @AuthenticationPrincipal(expression = "claims['name']") String name) {

        Authentication(request,session,email,name);
        ModelAndView mv = new ModelAndView();

        if((session.getAttribute("SESSION_Role") == "CWS") || (session.getAttribute("SESSION_Role") == "Teacher")) {
        mv.setViewName("searchEmployerPage");

        List<Employer> employers;
        List<Employer> employerLanguage;
        List<Employer> employerIndustrySectorAreas;
        List<Employer> employerCurriculumAreas;
        List<Employer> employerLocalAuthorities;
        List<Employer> employerNumberOfEmployees;
        List<Employer> employerCooperationType;
        List<Employer> employerPreferences;
        List<School> schoolAllNamesAndIds;

        employers = EmployerQrys.getAllEmployers();
        employerLanguage = EmployerQrys.getAllLanguages();
        employerLocalAuthorities = EmployerQrys.getAllLocalAuthorities();
        employerIndustrySectorAreas = EmployerQrys.getAllIndustrySectors();
        employerCurriculumAreas = EmployerQrys.getAllCurriculumAreas();
        employerNumberOfEmployees = EmployerQrys.getAllNumberOfEmployersPossible();
        employerCooperationType = EmployerQrys.getAllCooperationTypes();
        employerPreferences = EmployerQrys.getAllPreferences();
        schoolAllNamesAndIds = SchoolQrys.getAllSchoolNamesAndIds();

        Map<String,Object> allEmployers = new HashMap<String,Object>();

        allEmployers.put("allEmployers", employers);
        allEmployers.put("allEmployerLanguage", employerLanguage);
        allEmployers.put("allEmployerLocalAuthorities", employerLocalAuthorities);
        allEmployers.put("allEmployerIndustrySectors", employerIndustrySectorAreas);
        allEmployers.put("allEmployerCurriculumAreas", employerCurriculumAreas);
        allEmployers.put("allEmployerNumberOfEmployees", employerNumberOfEmployees);
        allEmployers.put("allEmployerCooperationType", employerCooperationType);
        allEmployers.put("allEmployerPreferences", employerPreferences);
        allEmployers.put("allSchoolNamesAndIds", schoolAllNamesAndIds);

        mv.addAllObjects(allEmployers);

        return mv;
        }else{
            mv.setViewName("404");
            return mv;
        }
    }

    //2. Employer Profile (with the id)
    @GetMapping("/ebe/profile-employer")
    public ModelAndView EmployersProfile(@RequestParam(value="employerId")int id, HttpServletRequest request, HttpSession session,
                                         @AuthenticationPrincipal(expression = "claims['email']") String email,
                                         @AuthenticationPrincipal(expression = "claims['name']") String name) {
        Authentication(request,session,email,name);
        ModelAndView mv = new ModelAndView();
        if((session.getAttribute("SESSION_Role") == "CWS") || (session.getAttribute("SESSION_Role") == "Teacher")) {
        mv.setViewName("profileEmployerPage");

        List<Employer> employerInfo, employerLanguage, employerIndustrySectorAreas,employerCurriculumAreas, employerPreferences,
                       employerAlumniNamesAndSchoolID, employerStatus, employerLocalAuthorities, employerNumberOfEmployees,
                       employerCooperationType;
        List<Integer>  employerChosenLanguage, employerChosenIndustrySectorAreas, employerChosenCurriculumAreas,employerAlumni,
                       employerChosenLocalAuthorities,employerChosenCooperationType, employerChosenPreferences, employerSchoolPreferences;
        List<School>   schoolAllNamesAndIds;
        List<String>   employerDocuments, employerVideos;
        List<Event>    eventsAllTypes;


        Employer employer = EmployerQrys.getEmployerDetailsById(id);
        employerInfo = EmployerQrys.getAllEmployers();
        employerLanguage = EmployerQrys.getAllLanguages();
        employerChosenLanguage = EmployerQrys.getEmployerLanguages(id);
        employerLocalAuthorities = EmployerQrys.getAllLocalAuthorities();
        employerChosenLocalAuthorities = EmployerQrys.getChosenLocalAuthorities(employer.getEmployerID());

        employerIndustrySectorAreas = EmployerQrys.getAllIndustrySectors();
        employerChosenIndustrySectorAreas = EmployerQrys.getChosenIndustrySectors(employer.getEmployerID());
        employerCurriculumAreas = EmployerQrys.getAllCurriculumAreas();
        employerChosenCurriculumAreas = EmployerQrys.getChosenCurriculumAreas(employer.getEmployerID());
        employerNumberOfEmployees = EmployerQrys.getAllNumberOfEmployersPossible();
        employerCooperationType = EmployerQrys.getAllCooperationTypes();
        employerChosenCooperationType = EmployerQrys.getChosenCooperationTypes(employer.getEmployerID());
        employerPreferences = EmployerQrys.getAllPreferences();
        employerChosenPreferences = EmployerQrys.getChosenPreferences(employer.getEmployerID());
        employerAlumni = EmployerQrys.getAllAlumni(employer.getEmployerID());
        employerAlumniNamesAndSchoolID = EmployerQrys.getAllAlumniNamesAndSchoolID(employerAlumni);
        schoolAllNamesAndIds = SchoolQrys.getAllSchoolNamesAndIds();
        employerStatus = EmployerQrys.getAllEmployerStatus();
        employerSchoolPreferences = EmployerQrys.getEmployerSchoolPreferences(employer.getEmployerID());
        employerDocuments = EmployerQrys.getEmployerDocuments(employer.getEmployerID());
        employerVideos = EmployerQrys.getEmployerVideos(employer.getEmployerID());
        eventsAllTypes = EventQrys.getAllTypesOfEvents();

        Map<String,Object> allEmployer = new HashMap<String,Object>();
        allEmployer.put("employer", employer);
        allEmployer.put("allEmployer", employerInfo);
        allEmployer.put("allEmployerLanguage", employerLanguage);
        allEmployer.put("allEmployerLanguagePreference",employerChosenLanguage);
        allEmployer.put("allEmployerLocalAuthorities", employerLocalAuthorities);
        allEmployer.put("allEmployerChosenLocalAuthorities", employerChosenLocalAuthorities);
        allEmployer.put("allEmployerIndustrySectors", employerIndustrySectorAreas);
        allEmployer.put("allEmployerChosenIndustrySectors", employerChosenIndustrySectorAreas);
        allEmployer.put("allEmployerCurriculumAreas", employerCurriculumAreas);
        allEmployer.put("allEmployerChosenCurriculumAreas", employerChosenCurriculumAreas);
        allEmployer.put("allEmployerNumberOfEmployees", employerNumberOfEmployees);
        allEmployer.put("allEmployerCooperationType", employerCooperationType);
        allEmployer.put("allEmployerChosenCooperationType", employerChosenCooperationType);
        allEmployer.put("allEmployerPreferences", employerPreferences);
        allEmployer.put("allEmployerAlumni",employerAlumniNamesAndSchoolID);
        allEmployer.put("allEmployerChosenPreferences", employerChosenPreferences);
        allEmployer.put("allSchoolNamesAndIds", schoolAllNamesAndIds);
        allEmployer.put("allEmployerStatus",employerStatus);
        allEmployer.put("allEmployerSchoolPreferences", employerSchoolPreferences);
        allEmployer.put("employerDocuments", employerDocuments);
        allEmployer.put("employerVideos", employerVideos);
        allEmployer.put("allEventTypes", eventsAllTypes);

        mv.addAllObjects(allEmployer);
        return mv;
        }else{
            mv.setViewName("404");
            return mv;
        }
    }

    //3. Add Employer
    @GetMapping("/ebe/add-employer")
    public ModelAndView AddEmployer (HttpSession session,
                                     HttpServletRequest request,
                                     @AuthenticationPrincipal(expression = "claims['email']") String email,
                                     @AuthenticationPrincipal(expression = "claims['name']") String name) {

        Authentication(request,session,email,name);
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("SESSION_Role") == "CWS") {
            mv.setViewName("addEmployerPage");

            List<Employer> employer;
            List<Employer> employerLanguage;
            List<Employer> employerIndustrySectorAreas;
            List<Employer> employerCurriculumAreas;
            List<Employer> employerLocalAuthorities;
            List<Employer> employerNumberOfEmployees;
            List<Employer> employerCooperationType;
            List<Employer> employerPreferences;
            List<Employer> employerStatus;
            List<School> schoolAllNamesAndIds;

            employer = EmployerQrys.getAllEmployers();
            employerLanguage = EmployerQrys.getAllLanguages();
            employerLocalAuthorities = EmployerQrys.getAllLocalAuthorities();
            employerIndustrySectorAreas = EmployerQrys.getAllIndustrySectors();
            employerCurriculumAreas = EmployerQrys.getAllCurriculumAreas();
            employerNumberOfEmployees = EmployerQrys.getAllNumberOfEmployersPossible();
            employerCooperationType = EmployerQrys.getAllCooperationTypes();
            employerPreferences = EmployerQrys.getAllPreferences();
            employerStatus = EmployerQrys.getAllEmployerStatus();
            schoolAllNamesAndIds = SchoolQrys.getAllSchoolNamesAndIds();

            Map<String, Object> allEmployer = new HashMap<String, Object>();
            allEmployer.put("allEmployer", employer);
            allEmployer.put("allEmployerLanguage", employerLanguage);
            allEmployer.put("allEmployerLocalAuthorities", employerLocalAuthorities);
            allEmployer.put("allEmployerIndustrySectors", employerIndustrySectorAreas);
            allEmployer.put("allEmployerCurriculumAreas", employerCurriculumAreas);
            allEmployer.put("allEmployerNumberOfEmployees", employerNumberOfEmployees);
            allEmployer.put("allEmployerCooperationType", employerCooperationType);
            allEmployer.put("allEmployerPreferences", employerPreferences);
            allEmployer.put("allEmployerStatus", employerStatus);
            allEmployer.put("allSchoolNamesAndIds", schoolAllNamesAndIds);

            mv.addAllObjects(allEmployer);

            return mv;
        }else{
            mv.setViewName("404");
            return mv;
        }

    }

    /////////2nd - Header Menu (Vacancies) /////////
    //4. Search Vacancies
    @GetMapping("/ebe/vacancies")
    public ModelAndView SearchVacancies(HttpSession session,
                                        HttpServletRequest request,
                                        @AuthenticationPrincipal(expression = "claims['email']") String email,
                                        @AuthenticationPrincipal(expression = "claims['name']") String name) {

        Authentication(request,session,email,name);
        ModelAndView mv = new ModelAndView();
        if(session.getAttribute("SESSION_Role") == "CWS") {
            mv.setViewName("searchVacanciesPage");

            List<Vacancy> vacancies;
            List<Vacancy> vacanciesAllTypes;
            List<Employer> employerAllNamesAndIds;
            List<Vacancy> vacanciesAllOccupationalCodes;

            vacancies = VacancyQrys.getAllVacancy();
            vacanciesAllTypes = VacancyQrys.getAllTypesOfVacancy();
            vacanciesAllOccupationalCodes = VacancyQrys.getAllOccupationalCodes();
            employerAllNamesAndIds = EmployerQrys.getAllEmployerNamesAndIds();


            Map<String,Object> allVacancies = new HashMap<>();

            allVacancies.put("allVacancies", vacancies);
            allVacancies.put("AllEmployerNamesAndIds", employerAllNamesAndIds);
            allVacancies.put("allVacanciesTypes", vacanciesAllTypes);
            allVacancies.put("allVacanciesOccupationalCodes", vacanciesAllOccupationalCodes);

            mv.addAllObjects(allVacancies);

            return mv;
        }else{
            mv.setViewName("404");
            return mv;
        }
    }

    //5. Vacancy Profile
    @GetMapping("/ebe/profile-vacancy")
    public ModelAndView Vacancy(@RequestParam(value="vacancyId")int id,
                                HttpSession session,
                                HttpServletRequest request,
                                @AuthenticationPrincipal(expression = "claims['email']") String email,
                                @AuthenticationPrincipal(expression = "claims['name']") String name){

        Authentication(request,session,email,name);
        ModelAndView mv = new ModelAndView();
        if(session.getAttribute("SESSION_Role") == "CWS") {
            mv.setViewName("profileVacancyPage");

            Vacancy vacancy = VacancyQrys.getVacancyDetailsById(id);
            Vacancy vacanciesEmployerName = VacancyQrys.getVacancyEmployerName(vacancy.getEmployerID());
            List<Vacancy> vacanciesAllTypes;
            List<Vacancy> vacanciesAllStatus;
            List<Vacancy> vacanciesAllOccupationalCodes;
            List<Vacancy> vacanciesAllApplicationMethods;
            List<Employer> employerAllNamesAndIds;
            List<String> vacancyDocuments;

            employerAllNamesAndIds = EmployerQrys.getAllEmployerNamesAndIds();
            vacanciesAllTypes = VacancyQrys.getAllTypesOfVacancy();
            vacanciesAllStatus = VacancyQrys.getAllStatusOfVacancy();
            vacanciesAllOccupationalCodes = VacancyQrys.getAllOccupationalCodes();
            vacanciesAllApplicationMethods = VacancyQrys.getAllApplicationMethods();
            vacancyDocuments = VacancyQrys.getVacancyDocuments(vacancy.getVacancyID());

            Map<String,Object> allVacancies = new HashMap<String,Object>();

            allVacancies.put("vacancy", vacancy);
            allVacancies.put("vacancyEmployerName", vacanciesEmployerName);
            allVacancies.put("AllEmployerNamesAndIds", employerAllNamesAndIds);
            allVacancies.put("allVacanciesTypes", vacanciesAllTypes);
            allVacancies.put("allVacanciesStatus", vacanciesAllStatus);
            allVacancies.put("allVacanciesOccupationalCodes", vacanciesAllOccupationalCodes);
            allVacancies.put("allVacanciesApplicationMethods", vacanciesAllApplicationMethods);
            allVacancies.put("vacancyDocuments", vacancyDocuments);

            mv.addAllObjects(allVacancies);

            return mv;
        }else{
            mv.setViewName("404");
            return mv;
        }
    }

    //6. Add Vacancy
    @GetMapping("/ebe/add-vacancy")
    public ModelAndView AddVacancy (HttpSession session,
                                    HttpServletRequest request,
                                    @AuthenticationPrincipal(expression = "claims['email']") String email,
                                    @AuthenticationPrincipal(expression = "claims['name']") String name) {

        Authentication(request,session,email,name);
        ModelAndView mv = new ModelAndView();
        if(session.getAttribute("SESSION_Role") == "CWS") {
            mv.setViewName("addVacancyPage");

            List<Employer> employerAllNamesAndIds;
            List<Vacancy> vacanciesAllTypes;
            List<Vacancy> vacanciesAllStatus;
            List<Vacancy> vacanciesAllOccupationalCodes;
            List<Vacancy> vacanciesAllApplicationMethods;

            employerAllNamesAndIds = EmployerQrys.getAllEmployerNamesAndIds();
            vacanciesAllTypes = VacancyQrys.getAllTypesOfVacancy();
            vacanciesAllStatus = VacancyQrys.getAllStatusOfVacancy();
            vacanciesAllOccupationalCodes = VacancyQrys.getAllOccupationalCodes();
            vacanciesAllApplicationMethods = VacancyQrys.getAllApplicationMethods();

            Map<String,Object> allVacancies = new HashMap<String,Object>();
            allVacancies.put("AllEmployerNamesAndIds", employerAllNamesAndIds);
            allVacancies.put("allVacanciesTypes", vacanciesAllTypes);
            allVacancies.put("allVacanciesStatus", vacanciesAllStatus);
            allVacancies.put("allVacanciesOccupationalCodes", vacanciesAllOccupationalCodes);
            allVacancies.put("allVacanciesApplicationMethods", vacanciesAllApplicationMethods);
            mv.addAllObjects(allVacancies);

            return mv;
        }else{
            mv.setViewName("404");
            return mv;
        }
    }

    /////////3rd - Header Menu (Events) /////////
    //7. Search Events
    @GetMapping("/ebe/events")
    public ModelAndView SearchEvents(HttpSession session,
                                     HttpServletRequest request,
                                     @AuthenticationPrincipal(expression = "claims['email']") String email,
                                     @AuthenticationPrincipal(expression = "claims['name']") String name) {

        Authentication(request,session,email,name);
        ModelAndView mv = new ModelAndView();
        if(session.getAttribute("SESSION_Role") == "CWS") {
            mv.setViewName("searchEventsPage");

            List<Event> events;
            List<Event> eventsAllTypes;
            List<String> advisors;

            events = EventQrys.getAllEvents();
            eventsAllTypes = EventQrys.getAllTypesOfEvents();
            advisors = statisticsQueries.getAllEventsAdvisors();

            Map<String,Object> allEvents = new HashMap<String,Object>();
            allEvents.put("allEvents", events);
            allEvents.put("allEventTypes", eventsAllTypes);
            allEvents.put("allAdvisorsNames", advisors);
            mv.addAllObjects(allEvents);

            return mv;
        }else{
            mv.setViewName("404");
            return mv;
        }
    }


    //8. Events Profile (get id of the event)
    @GetMapping("/ebe/profile-event")
    public ModelAndView EventProfile(@RequestParam(value="eventId")int id,
                                     HttpSession session,
                                     HttpServletRequest request,
                                     @AuthenticationPrincipal(expression = "claims['email']") String email,
                                     @AuthenticationPrincipal(expression = "claims['name']") String name) {

        Authentication(request,session,email,name);
        ModelAndView mv = new ModelAndView();
        if(session.getAttribute("SESSION_Role") == "CWS" || session.getAttribute("SESSION_Role") == "Teacher") {
            mv.setViewName("profileEventPage");

            Event event = EventQrys.getEventDetailsById(id);
            List<Event> eventsAllTypes;
            List<School> schoolAllNamesAndIds;
            List<Employer> employerAllNamesAndIds;
            List<Integer> eventSchoolsIDs = SchoolQrys.getAllSchoolIDsAttendingEvent(id);
            List<Integer> eventEmployerIDs = EmployerQrys.getAllEmployerIDsAttendingEvent(id);
    //        List<Employer> eventEmployerNames = EmployerQrys.getAllEmployerNamesAttendingEvent(eventEmployerIDs);
            List<String> eventDocuments;

            schoolAllNamesAndIds = SchoolQrys.getAllSchoolNamesAndIds();
            eventsAllTypes = EventQrys.getAllTypesOfEvents();
            eventDocuments = EventQrys.getEventDocuments(event.getEventID());
            employerAllNamesAndIds = EmployerQrys.getAllEmployerNamesAndIds();

            Map<String,Object> Event = new HashMap<String,Object>();
            Event.put("event", event);
    //        Event.put("AllSchoolsNames", eventSchoolNames);
            Event.put("EventSchoolsIDs", eventSchoolsIDs);
    //        Event.put("AllEmployersNames", eventEmployerNames);
            Event.put("EventEmployersIDs", eventEmployerIDs);
            Event.put("allSchoolNamesAndIds", schoolAllNamesAndIds);
            Event.put("allEventTypes", eventsAllTypes);
            Event.put("eventDocuments", eventDocuments);
            Event.put("AllEmployerNamesAndIds", employerAllNamesAndIds);
            mv.addAllObjects(Event);
        }else{
            mv.setViewName("404");
            return mv;
        }
        return mv;
    }

    //9. Add Events
    @GetMapping("/ebe/add-events")
    public ModelAndView AddEvents(HttpSession session,
                                  HttpServletRequest request,
                                  @AuthenticationPrincipal(expression = "claims['email']") String email,
                                  @AuthenticationPrincipal(expression = "claims['name']") String name) {

        Authentication(request,session,email,name);
        ModelAndView mv = new ModelAndView();
        if(session.getAttribute("SESSION_Role") == "CWS") {
            mv.setViewName("addEventsPage");

            List<Event> eventsAllTypes;
            List<School> schoolAllNamesAndIds;
            List<Employer> employerAllNamesAndIds;

            schoolAllNamesAndIds = SchoolQrys.getAllSchoolNamesAndIds();
            eventsAllTypes = EventQrys.getAllTypesOfEvents();
            employerAllNamesAndIds = EmployerQrys.getAllEmployerNamesAndIds();

            Map<String,Object> allEvents = new HashMap<String,Object>();
            allEvents.put("allSchoolNamesAndIds", schoolAllNamesAndIds);
            allEvents.put("allEventTypes", eventsAllTypes);
            allEvents.put("AllEmployerNamesAndIds", employerAllNamesAndIds);

            mv.addAllObjects(allEvents);

            return mv;
        }else{
            mv.setViewName("404");
            return mv;
        }
    }

    /////////4th - Header Menu (Request) /////////
    //10. Request
    @GetMapping("/ebe/request")
    public ModelAndView Request(HttpSession session,
                                HttpServletRequest request,
                                @AuthenticationPrincipal(expression = "claims['email']") String email,
                                @AuthenticationPrincipal(expression = "claims['name']") String name) {

        Authentication(request,session,email,name);
        ModelAndView mv = new ModelAndView();
        if(session.getAttribute("SESSION_Role") == "Teacher") {
            mv.setViewName("requestPage");

            ObjectMapper objectMapper = new ObjectMapper();
            // session = context.getSession();

            List<Event> eventsAllTypes;
            List<Employer> employerIndustrySectorAreas;
            List<Employer> employerLanguage;
            List<Employer> employers;

            employers = EmployerQrys.getAllEmployers();
            employerLanguage = EmployerQrys.getAllLanguages();
            eventsAllTypes = EventQrys.getAllTypesOfEvents();
            employerIndustrySectorAreas = EmployerQrys.getAllIndustrySectors();

            mv.addObject("allEventTypes",eventsAllTypes);
            mv.addObject("allIndustrySectors",employerIndustrySectorAreas);
            mv.addObject("allLanguages",employerLanguage);
            mv.addObject("allEmployers",employers);
            return mv;
        }else{
            mv.setViewName("404");
            return mv;
        }
    }

    /////////5th - Header Menu (Report) /////////
    //11. Report
    @GetMapping("/ebe/report")
    public ModelAndView Report(HttpSession session,
                               HttpServletRequest request,
                               @AuthenticationPrincipal(expression = "claims['email']") String email,
                               @AuthenticationPrincipal(expression = "claims['name']") String name) {

        Authentication(request,session,email,name);
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("SESSION_Role") == "CWS") {
            mv.setViewName("reportPage");

            int numberOfEvents = statisticsQueries.getTotalEvents();
            int numberOfVacancies = statisticsQueries.getTotalVacancies();
            int numberOfPupils = statisticsQueries.getTotalPupils();
            int numberOfEmployers = statisticsQueries.getTotalEmployers();
            int schoolAtEvents = statisticsQueries.getSchoolsAtEvents();
            int requestsBySchools = statisticsQueries.getRequestsBySchools();

            Map<String,Integer> eventsByAdv = statisticsQueries.getEventsByAdviser();
            Map<String,Integer> eventsByType = statisticsQueries.getEventsByType();
            Map<String,Integer> empBySector = statisticsQueries.getEmployersBySector();
            Map<String,Integer> evByLocalAuth = statisticsQueries.getEventByLocalAuth();
            Map<String,Integer> pupilsByAuth = statisticsQueries.getTotalPupilsByAuth();
            Map<String,Integer> schoolsOnEveLocalAuth = statisticsQueries.getSchoolsAttendingEventsByAuth();
            Map<String,Integer> empByLocalAuth = statisticsQueries.getEmpByLocalAuth();
            Map<String,Integer> schoolAndRequests = statisticsQueries.getSchoolsAndRequests();

            List<School> allSchoolsNames = SchoolQrys.getAllSchoolNamesAndIds();
            List<Event> allTypesOfEvents = EventQrys.getAllTypesOfEvents();
            List<Employer> allLocalAuthorities = EmployerQrys.getAllLocalAuthorities();

            mv.addObject("numberOfEvents",numberOfEvents);
            mv.addObject("numberOfEmployers",numberOfEmployers);
            mv.addObject("numberOfVacancies",numberOfVacancies);
            mv.addObject("numberOfPupils",numberOfPupils);
            mv.addObject("schoolAtEvents",schoolAtEvents);
            mv.addObject("requestsBySchools",requestsBySchools);
            mv.addObject("eventsByAdv",eventsByAdv);
            mv.addObject("eventsByType",eventsByType);
            mv.addObject("empBySector",empBySector);
            mv.addObject("evByLocalAuth",evByLocalAuth);
            mv.addObject("pupilsByAuth",pupilsByAuth);
            mv.addObject("schoolsOnEveLocalAuth",schoolsOnEveLocalAuth);
            mv.addObject("empByLocalAuth",empByLocalAuth);
            mv.addObject("allSchoolsNames",allSchoolsNames);
            mv.addObject("allTypesOfEvents",allTypesOfEvents);
            mv.addObject("allLocalAuthorities",allLocalAuthorities);
            mv.addObject("schoolAndRequests",schoolAndRequests);

            return mv;
        }else{
            mv.setViewName("404");
            return mv;
        }
    }

    /////////6th - Header Menu (Contact Us) /////////
    //12. Contact Us
    @GetMapping("/ebe/contact-us")
    public ModelAndView contactUs(HttpSession session,
                                  HttpServletRequest request,
                                  @AuthenticationPrincipal(expression = "claims['email']") String email,
                                  @AuthenticationPrincipal(expression = "claims['name']") String name) {

        Authentication(request,session,email,name);
        ModelAndView mv = new ModelAndView();
        if(session.getAttribute("SESSION_Role") == "Teacher") {
            ObjectMapper objectMapper = new ObjectMapper();
            // session = context.getSession();
            mv.setViewName("contactUsPage");
            return mv;
        }else{
            mv.setViewName("404");
            return mv;
        }
    }


//    //    13. CWS home page
//    @GetMapping("/homecws")
//    public ModelAndView homeCWS(){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("homepageCWS");
//
//        int numberOfEvents = statisticsQueries.getTotalEvents();
//        int numberOfVacancies = statisticsQueries.getTotalVacancies();
//        int numberOfPupils = statisticsQueries.getTotalPupils();
//        int numberOfEmployers = statisticsQueries.getTotalEmployers();
//        int schoolAtEvents = statisticsQueries.getSchoolsAtEvents();
//        int requestsBySchools = statisticsQueries.getRequestsBySchools();
//
//        mv.addObject("numberOfEvents",numberOfEvents);
//        mv.addObject("numberOfEmployers",numberOfEmployers);
//        mv.addObject("numberOfVacancies",numberOfVacancies);
//        mv.addObject("numberOfPupils",numberOfPupils);
//        mv.addObject("schoolAtEvents",schoolAtEvents);
//        mv.addObject("requestsBySchools",requestsBySchools);
//
//        return mv;
//    }
//
//    @GetMapping("/homeTeacher")
//    public ModelAndView homeTeach(){
//        ModelAndView mv = new ModelAndView();
//
//        return mv;
//    }



}
