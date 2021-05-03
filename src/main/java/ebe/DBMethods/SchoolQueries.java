package ebe.DBMethods;

import ebe.DBClasses.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SchoolQueries extends DBQueries {

    private Connection connection;
    private Statement statement;

    @Autowired
    public SchoolQueries(JdbcTemplate jdbctemplate) {
        super(jdbctemplate);
    }

    ///////////////////////////////////// GET ALL METHODS ///////////////////////////////////////////////
    // 1. Get All School
    public List<School> getAllSchools() throws DataAccessException {
        return jdbcTemplate().query("SELECT * FROM School", new Object[]{},
                (rs, i) -> new School(rs.getInt("SchoolID"), rs.getString("SchoolName"),
                        rs.getString("SchoolAddressCity"), rs.getString("SchoolAddressStreet"),
                        rs.getString("SchoolAddressNumber"), rs.getString("SchoolEmail"),
                        rs.getString("SchoolPostCode"),rs.getString("SchoolPhone"))
        );
    }

    // 2. Get School by Id
    public School getSchoolDetailsById(int schoolId) throws DataAccessException {
        String getSql = String.format("SELECT * FROM School WHERE SchoolID = \"%s\" LIMIT 1", schoolId);
        List<School> schoolInfo = jdbcTemplate().query(getSql, new Object[]{},
                (rs, i) -> new School(rs.getInt("SchoolID"), rs.getString("SchoolName"),
                        rs.getString("SchoolAddressCity"), rs.getString("SchoolAddressStreet"),
                        rs.getString("SchoolAddressNumber"), rs.getString("SchoolEmail"),
                        rs.getString("SchoolPostCode"),rs.getString("SchoolPhone"))
        );
        return schoolInfo.get(0);
    }

       public School getSchoolDetailsByEmail(String email) throws DataAccessException {
        String getSql = String.format("SELECT * FROM School WHERE SchoolEmail = \"%s\"", email);
        List<School> schoolInfo = jdbcTemplate().query(getSql, new Object[]{},
                (rs, i) -> new School(rs.getInt("SchoolID"), rs.getString("SchoolName"),
                        rs.getString("SchoolAddressCity"), rs.getString("SchoolAddressStreet"),
                        rs.getString("SchoolAddressNumber"), rs.getString("SchoolEmail"),
                        rs.getString("SchoolPostCode"),rs.getString("SchoolPhone"))
        );
        return schoolInfo.get(0);
    }


    // 3. Get All School Names and Ids
    public List<School> getAllSchoolNamesAndIds() throws DataAccessException {
        String getQuery = "SELECT SchoolID,SchoolName FROM School";
        List<School> list = new ArrayList<School>();
        School school = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                school = new School();
                school.setSchoolID(rs.getInt("SchoolID"));
                school.setSchoolName(rs.getString("SchoolName"));

                list.add(school);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return list;
    }

    // 4. Get All School Names and Ids
    public List<Integer> getAllSchoolIDsAttendingEvent(int eventId) throws DataAccessException {
        String getQuery = String.format("SELECT SchoolID FROM INT_AttendingSchoolOnEvent WHERE EventID = \"%s\"", eventId);

        List<Integer> list = new ArrayList<Integer>();
        School school = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {

                list.add(rs.getInt("SchoolID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return list;
    }

    // 5. Get All School Names and Ids
    public List<School> getAllSchoolNamesAttendingEvent(List<Integer> schools) throws DataAccessException {

        List<School> list = new ArrayList<School>();

        for (Integer schoolID : schools) {
            String getQuery = String.format("SELECT SchoolName FROM School WHERE SchoolID = \"%s\"", schoolID);


            School schoolName = null;
            ResultSet rs = null;
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(getQuery);
                while (rs.next()) {
                    schoolName = new School();
                    schoolName.setSchoolName(rs.getString("SchoolName"));

                    list.add(schoolName);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(rs);
                DBUtil.close(statement);
                DBUtil.close(connection);
            }
        }
        return list;
    }

    //6. Add request for school
    public int addRequestCount(String email) throws DataAccessException {

        String updateSql = "UPDATE school SET SchoolNumberOfRequest = ISNULL(SchoolNumberOfRequest, 0) + 1 WHERE SchoolEmail = ?";

        return jdbcTemplate().update(updateSql, email);
    }


    ///////////////////////////////////// CREATE ALL METHODS ///////////////////////////////////////////////
    // 7. Create a new School
    public int createNewSchool(String Name, String AddressCity, String AddressStreet,
                               Boolean AddressNumber, String Email, String Phone) throws DataAccessException {

        String insertSql = "INSERT TO School School(Name, AddressCity, AddressStreet, AddressNumber, Email, Phone)" +
                "                                  VALUES(?,?,?,?,?,?)";

        return jdbcTemplate().update(insertSql, Name, AddressCity, AddressStreet, AddressNumber, Email, Phone);

    }
    ///////////////////////////////////// UPDATE ALL METHODS ///////////////////////////////////////////////
    // 8. Update an School by Id

    public Integer updateSchool(int SchoolID,String Name, String AddressCity, String AddressStreet,
                                Boolean AddressNumber, String Email, String Phone) throws DataAccessException {

        String updateSql = "UPDATE School SET Name =?, AddressCity = ?, AddressStreet =?, AddressNumber=?," +
                "AddressNumber=?, Email=?, Phone=?  WHERE SchoolID =?";

        return jdbcTemplate().update(updateSql, Name, AddressCity, AddressStreet, AddressNumber, Email, Phone, SchoolID);
    }

    ///////////////////////////////////// DELETE ALL METHODS ///////////////////////////////////////////////
    // 9. Delete an School by ID

    public Integer deleteSchool(int schoolId) throws DataAccessException {
        String deleteSql = String.format("DELETE FROM School WHERE SchoolID = '%s'",schoolId);
        return jdbcTemplate().update(deleteSql);
    }
}
