package ebe.API;

import ebe.DBClasses.School;
import ebe.DBMethods.SchoolQueries;
import ebe.DBMethods.StatisticsQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StatisticsAPI {

    private SchoolQueries schoolQueries;
    private StatisticsQueries statisticsQueries;

    @Autowired
    public StatisticsAPI(StatisticsQueries statisticsQueries, SchoolQueries schoolQueries) {
        this.statisticsQueries = statisticsQueries;
        this.schoolQueries = schoolQueries;
    }

    @RequestMapping(value = "/ebe/api/filter", method = RequestMethod.GET)
    public int getEvents(@RequestParam(required = false, name = "LocalAuthority") Integer authId,
                         @RequestParam(required = false, name = "EventType") Integer eventId) {

        int noOfEventsByAuthAndTypeOfEvent = 0;

        if ((authId != null) && (eventId != null)) {
            noOfEventsByAuthAndTypeOfEvent = statisticsQueries.getEventsByAuthAndTypeOfEvent(eventId, authId);
        }

        return noOfEventsByAuthAndTypeOfEvent;
    }

    @RequestMapping(value = "/ebe/api/filter/school", method = RequestMethod.GET)
    public int getEventsBySchool(@RequestParam(required = false, name = "School") Integer schoolId) {
        int noOfEventsBySchool = 0;

        if (schoolId != null) {
            noOfEventsBySchool = statisticsQueries.getSchoolsThatAttendedEvents(schoolId);
        }

        return noOfEventsBySchool;
    }
}
