package ebe.DBMethods;

import ebe.DBClasses.Event;
import ebe.DBClasses.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class EventQueries extends DBQueries {

    private Connection connection;
    private Statement statement;

    @Autowired
    public EventQueries(JdbcTemplate jdbctemplate) {
        super(jdbctemplate);
    }

    ///////////////////////////////////// GET ALL METHODS ///////////////////////////////////////////////
    // 1. Get All Events
    public List<Event> getAllEvents() throws DataAccessException {
        String getQuery = "SELECT * FROM Event";
        List<Event> list = new ArrayList<Event>();
        Event event = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
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

                list.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return list;
    }

    // 2. Get Event by Id
    public Event getEventDetailsById(int eventId) throws DataAccessException {
        String getQuery = String.format("SELECT * FROM Event WHERE EventID = \"%s\" LIMIT 1", eventId);
        Event event = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {

                event = new Event(rs.getInt("EventID"), rs.getString("EventName"),
                        rs.getInt("TypeOfEventID"), rs.getDate("EventDateAndTime"),rs.getTime("EventDateAndTime"),
                        rs.getString("EventVenueName"),rs.getString("EventAddressCity"),
                        rs.getString("EventAddressStreet"),rs.getString("EventAddressNumber"),
                        rs.getString("EventVenuePostcode"),rs.getString("EventSummary"),
                        rs.getBoolean("isPublic"), rs.getBoolean("isCancelled"),
                        rs.getString("NameOfAdviser"), rs.getInt("NumberOfAttendees"),
                        rs.getBoolean("PromotesApprenticeships"), rs.getBoolean("PromotesWelshLanguage"),
                        rs.getBoolean("ChallengesGenderStereotypes"),rs.getBoolean("IsFeatured"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return event;
    }



    // 3. Get List of Event Types Names and Ids
    public List<Event> getAllTypesOfEvents() throws DataAccessException {
        String getQuery = "SELECT * FROM TypeOfEventList";
        List<Event> list = new ArrayList<Event>();
        Event event = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                event = new Event();
                event.setTypeOfEventID(rs.getInt("TypeOfEventID"));
                event.setTypeOfEventName(rs.getString("TypeOfEventName"));

                list.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return list;
    }

    // 4. Get List of Event Types Names and Ids
    public Integer getLastEventCreated(String eventName) throws DataAccessException {
        String getQuery = String.format("SELECT EventID FROM Event WHERE EventName = \"%s\"  ORDER BY EventID DESC LIMIT 1", eventName);
        Event event = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                event = new Event();
                event.setEventID(rs.getInt("EventID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return event.getEventID();
    }



    ///////////////////////////////////// CREATE ALL METHODS ///////////////////////////////////////////////
    // 5. Create a new Event
    public int createEvent( String EventName, int TypeOfEventID, String EventDateAndTime, String EventVenueName,
                            String EventAddressCity, String EventAddressStreet, String EventAddressNumber, String EventVenuePostcode,
                            String EventSummary, Boolean IsPublic, Boolean IsCancelled, String NameOfAdviser, int NumberOfAttendees,
                            Boolean PromotesApprenticeships, Boolean PromotesWelshLanguage, Boolean ChallengesGenderStereoTypes, Boolean isFeatured) throws DataAccessException {

        String insertSql = "INSERT INTO Event(EventName, TypeOfEventID, EventDateAndTime, EventVenueName, EventAddressCity," +
                " EventAddressStreet, EventAddressNumber, EventVenuePostcode, EventSummary, IsPublic, IsCancelled, NameOfAdviser," +
                " NumberOfAttendees, PromotesApprenticeships, PromotesWelshLanguage,ChallengesGenderStereoTypes, isFeatured)" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        return jdbcTemplate().update(insertSql, EventName, TypeOfEventID, EventDateAndTime, EventVenueName, EventAddressCity,
                EventAddressStreet, EventAddressNumber, EventVenuePostcode, EventSummary, IsPublic, IsCancelled, NameOfAdviser,
                NumberOfAttendees, PromotesApprenticeships, PromotesWelshLanguage, ChallengesGenderStereoTypes, isFeatured);
    }

    // 6. Create new Employer / Event Intersection
    public void createEmployerEventIntersection(int EventID, List<Integer> EmployerID) throws DataAccessException {

        String updateSql = "INSERT INTO INT_AttendingEmployerOnEvent(EventID, EmployerID) VALUE(?,?)";

        for (Integer employerId : EmployerID ){
            jdbcTemplate().update(updateSql, EventID, employerId);
        };
    }

    // 7. Create new School / Event Intersection
    public void createSchoolEventIntersection(int EventID, List<Integer> SchoolID) throws DataAccessException {

        String updateSql = "INSERT INTO INT_AttendingSchoolOnEvent(EventID, SchoolID) VALUES(?,?)";

        for (Integer schoolId : SchoolID ){
            jdbcTemplate().update(updateSql, EventID, schoolId);
        };
    }

    ///////////////////////////////////// UPDATE ALL METHODS ///////////////////////////////////////////////

    // 8. Update an Events by Id
    public Integer updateEvent( int EventID, String EventName, int TypeOfEventID, String EventDateAndTime, String EventVenueName,
                                String EventAddressCity, String EventAddressStreet, String EventAddressNumber, String EventVenuePostcode,
                                String EventSummary, Boolean IsPublic, Boolean IsCancelled, String NameOfAdviser, int NumberOfAttendees,
                                Boolean PromotesApprenticeships, Boolean PromotesWelshLanguage, Boolean ChallengesGenderStereoTypes, Boolean isFeatured) throws DataAccessException {

        String updateSql = "UPDATE Event SET EventName =?, TypeOfEventID =?, EventDateAndTime= ?, EventVenueName=?, EventAddressCity=?," +
                " EventAddressStreet=?, EventAddressNumber=?, EventVenuePostcode=?, EventSummary=?,  IsPublic=?, IsCancelled=?," +
                " NameOfAdviser=?, NumberOfAttendees=?, PromotesApprenticeships=?, PromotesWelshLanguage=?, ChallengesGenderStereoTypes=?, isFeatured=? WHERE EventID =?";

        return jdbcTemplate().update(updateSql, EventName, TypeOfEventID, EventDateAndTime,EventVenueName, EventAddressCity, EventAddressStreet,
                EventAddressNumber, EventVenuePostcode, EventSummary, IsPublic, IsCancelled, NameOfAdviser,
                NumberOfAttendees, PromotesApprenticeships, PromotesWelshLanguage, ChallengesGenderStereoTypes, isFeatured, EventID);
    }

    // 9. Update new Employer / Event Intersection
    public void updateEmployerEventIntersection(int EventID, List<Integer> EmployerID) throws DataAccessException {

        String deleteSql = String.format("DELETE FROM INT_AttendingEmployerOnEvent WHERE EventID = '%s'",EventID);
        jdbcTemplate().update(deleteSql);

        String updateSql = "INSERT INTO INT_AttendingEmployerOnEvent(EventID, EmployerID) VALUE(?,?)";

        for (Integer employerId : EmployerID ){
            jdbcTemplate().update(updateSql, EventID, employerId);
        };
    }

    // 10. Update new School / Event Intersection
    public void updateSchoolEventIntersection(int EventID, List<Integer> SchoolID) throws DataAccessException {

        String deleteSql = String.format("DELETE FROM INT_AttendingSchoolOnEvent WHERE EventID = '%s'",EventID);
        jdbcTemplate().update(deleteSql);

        String updateSql = "INSERT INTO INT_AttendingSchoolOnEvent(EventID, SchoolID) VALUES(?,?)";

        for (Integer schoolId : SchoolID ){
            jdbcTemplate().update(updateSql, EventID, schoolId);
        };
    }

    ///////////////////////////////////// DELETE ALL METHODS ///////////////////////////////////////////////
    // 11. Delete an Event by ID

    public Integer deleteEvent(int eventId) throws DataAccessException {
        String deleteSql = String.format("DELETE FROM Event WHERE EventID = '%s'",eventId);
        return jdbcTemplate().update(deleteSql);
    }

    ///////////////////////////////////// SORT BY METHODS ///////////////////////////////////////////////

    //12. Get Events order by name
    public List<Integer> sortByEventByName(String type) throws DataAccessException {
        String getQuery = String.format("SELECT * FROM Event ORDER BY EventName %s;", type);
        List<Integer> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                list.add(rs.getInt("EventID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return list;
    }

    //13. Get Events order by date
    public List<Integer> sortByEventByDate(String type) throws DataAccessException {
        String getQuery = String.format("SELECT * FROM Event ORDER BY EventDateAndTime %s;", type);
        List<Integer> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                list.add(rs.getInt("EventID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return list;
    }

    ///////////////////////////////////// FILTER METHODS ///////////////////////////////////////////////

    //14. Filter Events
    public List<Integer> filterEvents(List<Integer> typeOfEventList, List<String> nameOfAdviserList, int promotesApprenticeships, int promotesWelshLanguage, int challengesGenderStereotypes, int isFeatured) {
        List<Integer> ids = new ArrayList<>();
        connection = ConnectionFactory.getConnection();
        ResultSet rs = null;

        String SQL = "SELECT distinct EventID FROM Event WHERE EventID IN ";
        String SQL2 = "(SELECT EventID FROM Event ";
        if (!typeOfEventList.isEmpty()){
            SQL2 = SQL2.concat(" WHERE ");
            for (int i = 0; i < typeOfEventList.size(); ++i){
                SQL2 = SQL2.concat(" Event.TypeOfEventID = " + typeOfEventList.get(i));
                if (i != typeOfEventList.size() - 1){
                    SQL2 = SQL2.concat(" OR ");
                }
            }
        }

        SQL2 = SQL2.concat(") AND EventID IN ");

        String SQL3 = "(SELECT EventID FROM Event ";
        if (!nameOfAdviserList.isEmpty()){
            SQL3 = SQL3.concat(" WHERE ");
            for (int i = 0; i < nameOfAdviserList.size(); ++i){
                SQL3 = SQL3.concat(" Event.NameOfAdviser = " + "\"" + nameOfAdviserList.get(i) + "\"" );
                if (i != nameOfAdviserList.size() - 1){
                    SQL3 = SQL3.concat(" OR ");
                }
            }
        }

        SQL3 = SQL3.concat(")");

        String SQL4 = "";

        List<Integer> booleanFilters = Arrays.asList(promotesApprenticeships, promotesWelshLanguage, challengesGenderStereotypes, isFeatured);

        List<String> SQLStatements = Arrays.asList(" PromotesApprenticeships = "," PromotesWelshLanguage = "," ChallengesGenderStereotypes = ", " IsFeatured = ");

        if (booleanFilters.contains(1)){
            SQL4 = SQL4.concat(" AND (");
            for (int i = 0; i < booleanFilters.size(); ++i){
                if (booleanFilters.get(i) == 1){
                    SQL4 = SQL4.concat(SQLStatements.get(i) + booleanFilters.get(i));
                    if (i+1 < booleanFilters.size() || i+2 < booleanFilters.size()){
                        if (booleanFilters.get(i+1) == 1 ){
                            SQL4 = SQL4.concat(" OR ");
                        } else if (i+2 < booleanFilters.size()){
                            if (booleanFilters.get(i+2) == 1 ) {
                                SQL4 = SQL4.concat(" OR ");
                            }
                        }
                    }
                }
            }
            SQL4 = SQL4.concat(");");
        }
        String finalSQL = SQL+SQL2+SQL3+SQL4;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(finalSQL);
            while (rs.next()) {
                ids.add(rs.getInt("EventID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
        }
        return ids;
    }

    //////////////////////////// GET FEATURED METHOD //////////////////////////////////////

    public List<Event> getFeaturedEvents() throws DataAccessException {
        String getQuery = "SELECT * FROM Event WHERE isFeatured = 1";
        List<Event> list = new ArrayList<Event>();
        Event event = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
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

                list.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return list;
    }


    ///////////////////////////////////// DOCUMENTS ///////////////////////////////////////////////

    public void insertDocument(int eventID, String link) throws DataAccessException {

        String Sql = "INSERT INTO EventDocumentLink(EventID, Link) VALUES (?,?)";
        jdbcTemplate().update(Sql, eventID, link);

    }

    public void deleteDocument(int eventID, String link) throws DataAccessException {

        String Sql = "DELETE FROM EventDocumentLink WHERE EventID = ? AND Link = ?";
        jdbcTemplate().update(Sql, eventID, link);

    }

    public List <String> getEventDocuments(int eventID) throws DataAccessException {

        String sql = "SELECT Link FROM EventDocumentLink WHERE EventID = " + eventID + ";";
        List <String> documents = new ArrayList<>();
        ResultSet rs = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                documents.add(rs.getString("Link"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return documents;
    }

}
