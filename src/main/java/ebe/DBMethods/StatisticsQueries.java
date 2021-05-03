package ebe.DBMethods;

import ebe.DBClasses.Event;
import ebe.DBClasses.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StatisticsQueries extends DBQueries {

    private Connection connection;
    private Statement statement;

    @Autowired
    public StatisticsQueries(JdbcTemplate jdbctemplate) {
        super(jdbctemplate);
    }

    public int getTotalEvents() throws DataAccessException {
        return jdbcTemplate().queryForObject("SELECT count(*) FROM Event",
                new Object[]{}, Integer.class);
    }

    public int getTotalEmployers() throws DataAccessException {
        return jdbcTemplate().queryForObject("SELECT count(*) FROM Employer;",
                new Object[]{}, Integer.class);
    }

    public int getTotalVacancies() throws DataAccessException {
        return jdbcTemplate().queryForObject("SELECT count(*) FROM Vacancy;",
                new Object[]{}, Integer.class);
    }

    public int getSchoolsAtEvents() throws DataAccessException {
        return jdbcTemplate().queryForObject("SELECT COUNT(*) FROM INT_AttendingSchoolOnEvent;",
                new Object[]{}, Integer.class);
    }

    public int getTotalPupils() throws DataAccessException {
        return jdbcTemplate().queryForObject("SELECT sum(NumberOfAttendees) From Event;",
                new Object[]{}, Integer.class);
    }

    public int getRequestsBySchools() throws DataAccessException {
        return jdbcTemplate().queryForObject("SELECT sum(SchoolNumberOfRequest) FROM School;",
                new Object[]{}, Integer.class);
    }

    public Map<String, Integer> getEventsByAdviser() throws DataAccessException {
        String query = "SELECT NameOfAdviser, count(NameOfAdviser) FROM Event group by 1;";
        Map<String, Integer> evMap = new HashMap<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                evMap.put(rs.getString("NameOfAdviser"), rs.getInt("count(NameOfAdviser)"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evMap;
    }

    public Map<String, Integer> getEventsByType() throws DataAccessException {
        String query = "SELECT  eventList.TypeOfEventName, count(eve.TypeOfEventID) \n" +
                "FROM Event as eve\n" +
                "INNER JOIN TypeOfEventList as eventList ON eventList.TypeOfEventID = eve.TypeOfEventID\n" +
                "group by eve.TypeOfEventID;";
        Map<String, Integer> evMap = new HashMap<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                evMap.put(rs.getString("TypeOfEventName"), rs.getInt("count(eve.TypeOfEventID)"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evMap;
    }

    public Map<String, Integer> getEmployersBySector() throws DataAccessException {
        String query = "SELECT count(emp.EmployerName) as empCount, ind.IndustrySectorName as sector\n" +
                "FROM INT_EmployerIndustrySector inter \n" +
                "INNER JOIN Employer emp ON inter.EmployerID = emp.EmployerID\n" +
                "INNER JOIN IndustrySectorList ind ON ind.IndustrySectorID = inter.IndustrySectorID\n" +
                "GROUP BY inter.IndustrySectorID;";
        Map<String, Integer> empMap = new HashMap<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                empMap.put(rs.getString("sector"), rs.getInt("empCount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empMap;
    }

    public Map<String, Integer> getEventByLocalAuth() throws DataAccessException {
        String query = "SELECT count(eve.EventID) as eveCount, la.LocalAuthorityName as authName, la.LocalAuthorityID " +
                "FROM Event eve " +
                "INNER JOIN PostcodeList pc ON eve.EventVenuePostcode LIKE concat(SUBSTRING_INDEX(pc.PostcodeName, ' ', 1),' %') " +
                "INNER JOIN LocalAuthorityList la ON la.LocalAuthorityID = pc.LocalAuthorityID " +
                "GROUP BY 2; ";
        Map<String, Integer> eveMap = new HashMap<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                eveMap.put(rs.getString("authName"), rs.getInt("eveCount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eveMap;
    }

    public Map<String, Integer> getTotalPupilsByAuth() throws DataAccessException {
        String query = "SELECT sum(eve.NumberOfAttendees)  as numberOfAttendees, la.LocalAuthorityName as authName " +
                "From Event eve " +
                "INNER JOIN PostcodeList pc on eve.EventVenuePostcode LIKE concat(SUBSTRING_INDEX(pc.PostcodeName, ' ', 1),' %') " +
                "INNER JOIN LocalAuthorityList la ON la.LocalAuthorityID = pc.LocalAuthorityID\n" +
                "GROUP BY 2;";
        Map<String, Integer> pupilsMap = new HashMap<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                pupilsMap.put(rs.getString("authName"), rs.getInt("numberOfAttendees"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pupilsMap;
    }

    public Map<String, Integer> getSchoolsAttendingEventsByAuth() throws DataAccessException {
        String query = "SELECT COUNT(sc.SchoolID) as schoolCount, la.LocalAuthorityName as authName " +
                "FROM INT_AttendingSchoolOnEvent sc " +
                "INNER JOIN Event eve ON eve.EventID = sc.EventID  " +
                "INNER JOIN PostcodeList pc on eve.EventVenuePostcode LIKE concat(SUBSTRING_INDEX(pc.PostcodeName, ' ', 1),' %') " +
                "INNER JOIN LocalAuthorityList la ON la.LocalAuthorityID = pc.LocalAuthorityID " +
                "GROUP BY 2;";
        Map<String, Integer> schoolMap = new HashMap<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                schoolMap.put(rs.getString("authName"), rs.getInt("schoolCount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schoolMap;
    }

    public Map<String, Integer> getEmpByLocalAuth() throws DataAccessException {
        String query = "SELECT count(inter.EmployerID) empCount, la.LocalAuthorityName as authName " +
                "FROM INT_LocalAuthorityEmployerCanWorkWith inter\n" +
                "INNER JOIN LocalAuthorityList la ON inter.LocalAuthorityID = la.LocalAuthorityID " +
                "GROUP BY 2;";
        Map<String, Integer> empMap = new HashMap<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                empMap.put(rs.getString("authName"), rs.getInt("empCount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empMap;
    }

    public int getEventsByAuthAndTypeOfEvent(int eventId, int authId) throws DataAccessException{
        String query = String.format("SELECT count(eve.EventID) FROM Event eve " +
                "INNER JOIN PostcodeList pc on eve.EventVenuePostcode LIKE concat(SUBSTRING_INDEX(pc.PostcodeName, ' ', 1),' %%')" +
                "INNER JOIN LocalAuthorityList la ON la.LocalAuthorityID = pc.LocalAuthorityID " +
                "INNER JOIN TypeOfEventList evType ON evType.TypeOfEventID = eve.TypeOfEventID " +
                "WHERE evType.TypeOfEventID = \"%s\" AND la.LocalAuthorityID = \"%s\";", eventId,authId);
        ResultSet rs = null;
        int eventNumber = 0;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                eventNumber = rs.getInt("count(eve.EventID)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventNumber;
    }

    public int getSchoolsThatAttendedEvents(int schoolID) throws DataAccessException{
        String query = String.format("SELECT count(inter.SchoolID) " +
                "FROM INT_AttendingSchoolOnEvent inter " +
                "WHERE SchoolID = \"%s\";", schoolID);
        ResultSet rs = null;
        int schoolsOnEvents = 0;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                schoolsOnEvents = rs.getInt("count(inter.SchoolID)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schoolsOnEvents;
    }

    public Map<String,Integer> getSchoolsAndRequests(){
        String query = "SELECT SchoolName, SchoolNumberOfRequest\n" +
                "FROM School;";
        Map<String, Integer> schoolMap = new HashMap<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                schoolMap.put(rs.getString("SchoolName"), rs.getInt("SchoolNumberOfRequest"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schoolMap;
    }

    public int getLocalAuthID(int schoolID){
        String query = String.format("select la.LocalAuthorityID as schoolAuthorityID " +
                "FROM School sc " +
                "INNER JOIN PostcodeList pc ON pc.PostcodeName LIKE CONCAT('%%',sc.SchoolPostcode) " +
                "INNER JOIN LocalAuthorityList la ON la.LocalAuthorityID = pc.LocalAuthorityID " +
                "WHERE sc.SchoolID = \"%s\"; ", schoolID);
        int localAuthID = 0;
        ResultSet rs;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                localAuthID = rs.getInt("schoolAuthorityID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localAuthID;
    }

    public List<Event> getEventsForSchool(int schoolID){
        int localAuthID = getLocalAuthID(schoolID);
        String query = String.format("select eve.* " +
                "From Event eve " +
                "WHERE SUBSTRING_INDEX(eve.EventVenuePostcode, ' ', 1) IN " +
                "(SELECT PostcodeName AS postcode " +
                "FROM PostcodeList " +
                "WHERE LocalAuthorityID = \"%s\");", localAuthID);
        List<Event> events = new ArrayList<>();
        ResultSet rs;
        Event event;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                event = new Event(rs.getInt("EventID"), rs.getString("EventName"),
                        rs.getInt("TypeOfEventID"), rs.getDate("EventDateAndTime"),
                        rs.getString("EventVenueName"),rs.getString("EventAddressCity"),
                        rs.getString("EventAddressStreet"),rs.getString("EventAddressNumber"),
                        rs.getString("EventVenuePostcode"),rs.getString("EventSummary"),
                        rs.getBoolean("isPublic"), rs.getBoolean("isCancelled"),
                        rs.getString("NameOfAdviser"), rs.getInt("NumberOfAttendees"),
                        rs.getBoolean("PromotesApprenticeships"), rs.getBoolean("PromotesWelshLanguage"),
                        rs.getBoolean("ChallengesGenderStereotypes"), rs.getBoolean("isFeatured"));

                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public List<String> getAllEventsAdvisors() {

        String SQL = "SELECT distinct NameOfAdviser FROM Event;";
        List<String> advisors = new ArrayList<>();
        ResultSet rs;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while (rs.next()) {
                advisors.add(rs.getString("NameOfAdviser"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return advisors;
    }

    public String getLocalAuthNameById(int authId) throws DataAccessException{
        String query = String.format("SELECT * FROM localauthoritylist WHERE LocalAuthorityID = \"%s\";",authId);
        ResultSet rs = null;
        String localAuth = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                localAuth = rs.getString("LocalAuthorityName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localAuth;
    }

    public Integer updateSchoolRequestNumber(int schoolID) throws DataAccessException{
        String query = String.format("SELECT * FROM School WHERE SchoolID = \"%s\";",schoolID);
        ResultSet rs = null;
        Integer numberOfRequests = 0;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                numberOfRequests = rs.getInt("SchoolNumberOfRequest");
                numberOfRequests = numberOfRequests +1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String updateSql = "UPDATE School SET SchoolNumberOfRequest=? WHERE  SchoolID=?";
        return jdbcTemplate().update(updateSql,numberOfRequests, schoolID);
    }

}












