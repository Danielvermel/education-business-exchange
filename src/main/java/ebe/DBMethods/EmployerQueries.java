package ebe.DBMethods;

import ebe.DBClasses.Employer;
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
import java.util.HashMap;
import java.util.List;

@Repository
public class EmployerQueries extends DBQueries {

    private static final String EMPLOYER_ID = "EmployerID";
    private static final String EMPLOYER_STATUS_ID = "StatusOfEmployerID";
    private static final String EMPLOYER_NAME = "EmployerName";
    private static final String ALUMNI_ID = "AlumniID";
    private Connection connection;
    private Statement statement;

    @Autowired
    public EmployerQueries(JdbcTemplate jdbctemplate) {
        super(jdbctemplate);
    }

    ///////////////////////////////////// GET ALL METHODS ///////////////////////////////////////////////
    // 1. Get All Employers
    public List<Employer> getAllEmployers() throws DataAccessException {
        String getQuery = "SELECT * FROM Employer";
        List<Employer> list = new ArrayList<>();
        Employer employer = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                employer = new Employer(rs.getInt(EMPLOYER_ID), rs.getInt(EMPLOYER_STATUS_ID),
                        rs.getString(EMPLOYER_NAME), rs.getString("EmployerAddressCity"),
                        rs.getString("EmployerAddressStreet"), rs.getString("EmployerAddressNumber"),
                        rs.getString("EmployerPostcode"), rs.getString("EmployerEmail"),
                        rs.getString("ContactPersonNameSurname"),rs.getString("ContactPersonPosition"),
                        rs.getString("EmployerPhone"), rs.getString("EmployerWebsite"),
                        rs.getString("EmployerTwitter"), rs.getString("EmployerFB"),
                        rs.getInt("NumberOfEmployeesID"), rs.getString("CompanySummary"),
                        rs.getString("Notes"), rs.getString("LogoLink"));

                list.add(employer);
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

    // 2. Get Employer by Id
    public Employer getEmployerDetailsById(int employerId) throws DataAccessException {
        String getQuery = String.format("SELECT * FROM Employer WHERE EmployerID = \"%s\" LIMIT 1", employerId);
        Employer employer = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {

                employer = new Employer();
                employer.setEmployerID(rs.getInt(EMPLOYER_ID));
                employer.setStatusOfEmployerID(rs.getInt(EMPLOYER_STATUS_ID));
                employer.setEmployerName(rs.getString(EMPLOYER_NAME));
                employer.setEmployerAddressCity(rs.getString("EmployerAddressCity"));
                employer.setEmployerAddressStreet(rs.getString("EmployerAddressStreet"));
                employer.setEmployerAddressNumber(rs.getString("EmployerAddressNumber"));
                employer.setEmployerPostcode(rs.getString("EmployerPostcode"));
                employer.setEmployerEmail(rs.getString("EmployerEmail"));
                employer.setContactPersonNameSurname(rs.getString("ContactPersonNameSurname"));
                employer.setContactPersonPosition(rs.getString("ContactPersonPosition"));
                employer.setEmployerPhone(rs.getString("EmployerPhone"));
                employer.setEmployerWebsite(rs.getString("EmployerWebsite"));
                employer.setEmployerTwitter(rs.getString("EmployerTwitter"));
                employer.setEmployerFB(rs.getString("EmployerFB"));
                employer.setNumberOfEmployeesID(rs.getInt("NumberOfEmployeesID"));
                employer.setCompanySummary(rs.getString("CompanySummary"));
                employer.setNotes(rs.getString("Notes"));
                employer.setLogoLink(rs.getString("LogoLink"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return employer;
    }

    //3. Get all Employer Ids and Names
    public List<Employer> getAllEmployerNamesAndIds() throws DataAccessException {
        String getQuery = "SELECT EmployerID, EmployerName FROM Employer";
        List<Employer> list = new ArrayList<>();
        Employer employer;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                employer = new Employer();
                employer.setEmployerID(rs.getInt(EMPLOYER_ID));
                employer.setEmployerName(rs.getString(EMPLOYER_NAME));

                list.add(employer);
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

    //4. Get Last Employer Created
    public Integer getLastEmployerCreated(String employerName) throws DataAccessException {
        String getQuery = String.format("SELECT EmployerID FROM Employer WHERE EmployerName = \"%s\" ORDER BY EmployerID DESC LIMIT 1", employerName);
        Employer employer = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                employer = new Employer();
                employer.setEmployerID(rs.getInt(EMPLOYER_ID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }

        //check if employer is not null
        return employer.getEmployerID();
    }


    //5. Get Possible Number of Employers
    public List<Employer> getAllNumberOfEmployersPossible() throws DataAccessException {
        String getQuery = "SELECT EmployeesRangeID, EmployeesRangeName FROM EmployeesRangeList";
        List<Employer> list = new ArrayList<>();
        Employer employer;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                employer = new Employer();
                employer.setNumberOfEmployeesID(rs.getInt("EmployeesRangeID"));
                employer.setNumberOfEmployeesName(rs.getString("EmployeesRangeName"));

                list.add(employer);
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

    //6. Get All Possible Language List
    public List<Employer> getAllLanguages() throws DataAccessException {
        String getQuery = "SELECT LanguageID, LanguageName FROM LanguageList";
        List<Employer> list = new ArrayList<>();
        Employer employer = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                employer = new Employer();
                employer.setEmployerLanguageID(rs.getInt("LanguageID"));
                employer.setEmployerLanguageName(rs.getString("LanguageName"));

                list.add(employer);
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

    //7. Get Employer Languages
    public List<Integer> getEmployerLanguages(int EmployerId) throws DataAccessException {
        String getQuery = String.format("SELECT LanguageID FROM INT_LanguageUsedByEmployer WHERE EmployerID = \"%s\"", EmployerId);
        List<Integer> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                list.add(rs.getInt("LanguageID"));
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


    //8. Get All Possible Local Authorities List
    public List<Employer> getAllLocalAuthorities() throws DataAccessException {
        String getQuery = "SELECT LocalAuthorityID, LocalAuthorityName FROM LocalAuthorityList";
        List<Employer> list = new ArrayList<>();
        Employer employer = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                employer = new Employer();
                employer.setEmployerLocalAuthorityID(rs.getInt("LocalAuthorityID"));
                employer.setEmployerLocalAuthorityName(rs.getString("LocalAuthorityName"));

                list.add(employer);
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

    //9. Intersection - Get Chosen Local Authorities
    public List<Integer> getChosenLocalAuthorities(int employerID) throws DataAccessException {
        String getQuery = String.format("SELECT LocalAuthorityID FROM INT_LocalAuthorityEmployerCanWorkWith WHERE EmployerID = \"%s\"", employerID);

        List<Integer> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                list.add(rs.getInt("LocalAuthorityID"));
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

    //10. Get All Possible Industry Sector List
    public List<Employer> getAllIndustrySectors() throws DataAccessException {
        String getQuery = "SELECT IndustrySectorID, IndustrySectorName FROM IndustrySectorList";
        List<Employer> list = new ArrayList<>();
        Employer employer = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                employer = new Employer();
                employer.setEmployerIndustrySectorID(rs.getInt("IndustrySectorID"));
                employer.setEmployerIndustrySectorName(rs.getString("IndustrySectorName"));

                list.add(employer);
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

    //11. Intersection - Get Chosen Industry Sectors
    public List<Integer> getChosenIndustrySectors(int employerID) throws DataAccessException {
        String getQuery = String.format("SELECT IndustrySectorID FROM INT_EmployerIndustrySector WHERE EmployerID = \"%s\"", employerID);

        List<Integer> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                list.add(rs.getInt("IndustrySectorID"));
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

    //12. Get All Possible Curriculum Areas List
    public List<Employer> getAllCurriculumAreas() throws DataAccessException {
        String getQuery = "SELECT AreaOfCurriculumID, AreaOfCurriculumName FROM AreaOfCurriculumList";
        List<Employer> list = new ArrayList<>();
        Employer employer = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                employer = new Employer();
                employer.setEmployerAreaOfCurriculumID(rs.getInt("AreaOfCurriculumID"));
                employer.setEmployAreaOfCurriculumName(rs.getString("AreaOfCurriculumName"));

                list.add(employer);
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


    //13. Intersection - Get Chosen Curriculum Areas
    public List<Integer> getChosenCurriculumAreas(int employerID) throws DataAccessException {
        String getQuery = String.format("SELECT AreaOfCurriculumID FROM INT_EmployerSupportOfAreaOfCurriculum WHERE EmployerID = \"%s\"", employerID);

        List<Integer> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                list.add(rs.getInt("AreaOfCurriculumID"));
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

    //14. Get All Possible Cooperation Types
    public List<Employer> getAllCooperationTypes() throws DataAccessException {
        String getQuery = "SELECT CooperationTypeID, CooperationTypeName FROM CooperationTypeList";
        List<Employer> list = new ArrayList<>();
        Employer employer = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                employer = new Employer();
                employer.setEmployerCooperationTypeID(rs.getInt("CooperationTypeID"));
                employer.setEmployerCooperationTypeName(rs.getString("CooperationTypeName"));

                list.add(employer);
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

    //15. Intersection - Get Chosen Cooperation Types
    public List<Integer> getChosenCooperationTypes(int employerID) throws DataAccessException {
        String getQuery = String.format("SELECT CooperationTypeID FROM INT_EmployerCooperationType WHERE EmployerID = \"%s\";", employerID);

        List<Integer> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                list.add(rs.getInt("CooperationTypeID"));
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

    //16. Get All Possible Preferences
    public List<Employer> getAllPreferences() throws DataAccessException {
        String getQuery = "SELECT PreferenceID, PreferenceName FROM PreferenceList;";
        List<Employer> list = new ArrayList<>();
        Employer employer = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                employer = new Employer();
                employer.setEmployerPreferencesID(rs.getInt("PreferenceID"));
                employer.setEmployerPreferencesName(rs.getString("PreferenceName"));

                list.add(employer);
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

    //17. Intersection - Get Chosen Preferences
    public List<Integer> getChosenPreferences(int employerID) throws DataAccessException {
        String getQuery = String.format("SELECT PreferenceID FROM INT_EmployerPreference WHERE EmployerID = \"%s\";", employerID);

        List<Integer> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                list.add(rs.getInt("PreferenceID"));
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

    //18. Get All Alumni From  Employer - Intersection
    public List<Integer> getAllAlumni(int employerID) throws DataAccessException {
        String getQuery = String.format("SELECT AlumniID FROM INT_AlumniWorkingForEmployer WHERE EmployerID = \"%s\"", employerID);

        List<Integer> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                list.add(rs.getInt(ALUMNI_ID));
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

    //19. Get Alumni Name By AlumniID
    public List<Employer> getAllAlumniNamesAndSchoolID(List<Integer> alumniList) throws DataAccessException {

        List<Employer> list = new ArrayList<>();
        Employer employer = null;
        ResultSet rs = null;
        for (Integer alumni : alumniList) {
            String getQuery = String.format("SELECT *  FROM Alumni WHERE AlumniID = \"%s\";", alumni);
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(getQuery);
                while (rs.next()) {
                    employer = new Employer();
                    employer.setEmployerAlumniID(rs.getInt(ALUMNI_ID));
                    employer.setEmployerAlumniName(rs.getString("AlumniNameAndSurname"));
                    employer.setEmployerAlumniSchoolID(rs.getInt("AlumniSchoolID"));

                    list.add(employer);
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

    // 20. Get All Employer Names and Ids
    public List<Integer> getAllEmployerIDsAttendingEvent(int eventId) throws DataAccessException {
        String getQuery = String.format("SELECT EmployerID FROM INT_AttendingEmployerOnEvent WHERE EventID = \"%s\";", eventId);

        List<Integer> list = new ArrayList<>();
        Employer employer = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                list.add(rs.getInt(EMPLOYER_ID));
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

    // 21. Get All Employer Names and Ids
    public List<Employer> getAllEmployerNamesAttendingEvent(List<Employer> employers) throws DataAccessException {

        List<Employer> list = new ArrayList<>();

        for (Employer employer : employers) {
            String getQuery = String.format("SELECT EmployerName FROM Employer WHERE EmployerID = \"%s\";", employer.getEmployerID());

            Employer employerName = null;
            ResultSet rs = null;
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(getQuery);
                while (rs.next()) {
                    employerName = new Employer();
                    employerName.setEmployerName(rs.getString(EMPLOYER_NAME));

                    list.add(employerName);
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

    // 22. Get Employer Preferences - School
    public List<Integer> getEmployerSchoolPreferences(int employerId) throws DataAccessException {

        String getQuery = String.format("SELECT SchoolID FROM INT_EmployerSchoolPreference WHERE EmployerID = \"%s\";", employerId);

        List<Integer> list = new ArrayList<>();
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

    // 23. Get Employer Preferences - School
    public List<Employer>  getAllEmployerStatus() throws DataAccessException {

        String getQuery = "SELECT StatusOfEmployerID, StatusOfEmployerName FROM StatusOfEmployerList";

        List<Employer> list = new ArrayList<>();
        Employer employer = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                employer = new Employer();
                employer.setStatusOfEmployerID(rs.getInt(EMPLOYER_STATUS_ID));
                employer.setStatusOfEmployerName(rs.getString("StatusOfEmployerName"));

                list.add(employer);
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

    // 24. Get Alumni ID By Alumni Name
    public List<Integer>  getAllAlumniIDFromEmployer(List<String> alumniName, List<Integer>alumniSchoolID) throws DataAccessException {

        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < alumniName.size(); i++) {
            String getQuery = String.format("SELECT AlumniID FROM Alumni WHERE AlumniNameAndSurname = \"%s\" AND AlumniSchoolID = \"%s\";",
                    alumniName.get(i), alumniSchoolID.get(i));

            ResultSet rs = null;
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.createStatement();
                rs = statement.executeQuery(getQuery);
                while (rs.next()) {

                    list.add(rs.getInt(ALUMNI_ID));

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


    // 25. Create a new Employer / version 1 not complete
    public int createEmployer( int statusOfEmployerID, String employerName,
                               String employerAddressCity, String employerAddressStreet,
                               String employerAddressNumber, String employerPostcode,
                               String employerEmail, String contactPersonNameSurname, String contactPersonPosition, String employerPhone,
                               String employerTwitter, String employerFB, String employerWebsite, int numberOfEmployeesID, String companySummary,
                               String notes) throws DataAccessException {

        String insertSql = "INSERT INTO Employer(StatusOfEmployerID, EmployerName, EmployerAddressCity, EmployerAddressStreet, EmployerAddressNumber, EmployerPostcode," +
                " EmployerEmail,ContactPersonNameSurname,ContactPersonPosition, EmployerPhone, EmployerWebsite, EmployerTwitter, EmployerFB, NumberOfEmployeesID," +
                "  CompanySummary, Notes)" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        return jdbcTemplate().update(insertSql, statusOfEmployerID, employerName, employerAddressCity, employerAddressStreet, employerAddressNumber,
                employerPostcode, employerEmail, contactPersonNameSurname, contactPersonPosition, employerPhone, employerWebsite,
                employerTwitter, employerFB, numberOfEmployeesID, companySummary, notes);
    }

    // 27. Create new Employer / Cooperation Type Intersection
    public void createEmployerCooperationIntersection(int employerID, List<Integer> cooperationTypeID) throws DataAccessException {

        String updateSql = "INSERT INTO INT_EmployerCooperationType(EmployerID, CooperationTypeID) VALUE(?,?)";

        for (Integer cooperationTypeId : cooperationTypeID ){
            jdbcTemplate().update(updateSql, employerID, cooperationTypeId);
        }
    }

    // 28. Create new Employer / Industry Sector
    public void createEmployerIndustrySectorIntersection(int employerID, List<Integer> industrySectorID) throws DataAccessException {

        String updateSql = "INSERT INTO INT_EmployerIndustrySector(EmployerID, IndustrySectorID) VALUES(?,?)";

        for (Integer industrySectorId : industrySectorID) {
            jdbcTemplate().update(updateSql, employerID, industrySectorId);
        }
    }


    // 29. Create new Employer / Preference
    public void createEmployerPreferencesIntersection(int employerID, List<Integer> preferenceID) throws DataAccessException {

        String updateSql = "INSERT INTO INT_EmployerPreference(EmployerID, PreferenceID) VALUE(?,?)";

        for (Integer preferencesId : preferenceID ){
            jdbcTemplate().update(updateSql, employerID, preferencesId);
        }
    }

    // 30. Create new Employer / School Preference
    public void createSchoolEmployerSchoolPreferencesIntersection(int employerID, List<Integer> schoolID) throws DataAccessException {

        String updateSql = "INSERT INTO INT_EmployerSchoolPreference(EmployerID, SchoolID) VALUES(?,?)";

        for (Integer schoolId : schoolID) {
            jdbcTemplate().update(updateSql, employerID, schoolId);
        }
    }

    // 31. Create new Employer / Curriculum Area
    public void createEmployerEmployerCurriculumAreaIntersection(int employerID, List<Integer> areaOfCurriculumID) throws DataAccessException {

        String updateSql = "INSERT INTO INT_EmployerSupportOfAreaOfCurriculum(EmployerID, AreaOfCurriculumID) VALUE(?,?)";

        for (Integer areaOfCurriculumId : areaOfCurriculumID ) {
            jdbcTemplate().update(updateSql, employerID, areaOfCurriculumId);
        }
    }

    // 32. Create new Employer / Language
    public void createSchoolEmployerLanguageIntersection(int employerID, List<Integer> languageID) throws DataAccessException {

        String updateSql = "INSERT INTO INT_LanguageUsedByEmployer(EmployerID, LanguageID) VALUES(?,?)";

        for (Integer languageId : languageID) {
            jdbcTemplate().update(updateSql, employerID, languageId);
        }
    }

    // 33. Create new Employer / Local Authorities
    public void createSchoolEmployerLocalAuthoritiesIntersection(int employerID, List<Integer> localAuthorityID) throws DataAccessException {

        String updateSql = "INSERT INTO INT_LocalAuthorityEmployerCanWorkWith(EmployerID, LocalAuthorityID) VALUES(?,?)";

        for (Integer localAuthoritiesId : localAuthorityID) {
            jdbcTemplate().update(updateSql, employerID, localAuthoritiesId);
        }
    }


    // 34. Create Alumni
    public void createAlumni(List<String> alumniName, List<Integer> schoolID) throws DataAccessException {

        for(int i = 0; i < alumniName.size(); i++){
            String updateSql = "INSERT INTO Alumni(AlumniNameAndSurname, AlumniSchoolID) VALUES(?,?)";
            jdbcTemplate().update(updateSql, alumniName.get(i), schoolID.get(i));
        }
    }

    // 35. Create new Employer /  Alumni Intersection
    public void createEmployerAlumniIntersection(int employerID, List<Integer> alumni) throws DataAccessException {

        String updateSql = "INSERT INTO INT_AlumniWorkingForEmployer(AlumniID, EmployerID) VALUES(?,?)";
        for (Integer alumniID : alumni) {
            jdbcTemplate().update(updateSql,alumniID,employerID);
        }
    }


    //36. Update Employer by Id
    public Integer updateEmployer( int employerID, int statusOfEmployerID, String employerName, String employerAddressCity, String employerAddressStreet, String employerAddressNumber,
                                   String employerPostcode, String employerEmail, String contactPersonNameSurname, String contactPersonPosition, String employerPhone, String employerWebsite,
                                   String employerTwitter, String employerFB, int numberOfEmployeesID, String companySummary, String notes) throws DataAccessException {

        String updateSql = "UPDATE Employer SET StatusOfEmployerID =?, EmployerName = ?, EmployerAddressCity =?, EmployerAddressStreet=?, EmployerAddressNumber=?," +
                "EmployerPostcode=?, EmployerEmail=?, ContactPersonNameSurname=?, ContactPersonPosition=?, EmployerPhone=?, EmployerWebsite=?, EmployerTwitter=?," +
                "EmployerFB=?, NumberOfEmployeesID=?,  CompanySummary=?, Notes=? WHERE EmployerID =?";

        return jdbcTemplate().update(updateSql, statusOfEmployerID, employerName, employerAddressCity, employerAddressStreet, employerAddressNumber,
                employerPostcode, employerEmail, contactPersonNameSurname, contactPersonPosition, employerPhone, employerWebsite, employerTwitter,
                employerFB, numberOfEmployeesID, companySummary, notes, employerID);
    }

    // 37. Update new Employer / Cooperation Type Intersection
    public void updateEmployerCooperationIntersection(int employerID, List<Integer> cooperationTypeID) throws DataAccessException {
        String deleteSql = String.format("DELETE FROM INT_EmployerCooperationType WHERE EmployerID = '%s'",employerID);
        jdbcTemplate().update(deleteSql);

        String updateSql = "INSERT INTO INT_EmployerCooperationType(EmployerID, CooperationTypeID) VALUE(?,?)";

        for (Integer cooperationTypeId : cooperationTypeID ){
            jdbcTemplate().update(updateSql, employerID, cooperationTypeId);
        }
    }

    // 38. Update new Employer / Industry Sector
    public void updateEmployerIndustrySectorIntersection(int employerID, List<Integer> industrySectorID) throws DataAccessException {
        String deleteSql = String.format("DELETE FROM INT_EmployerIndustrySector WHERE EmployerID = '%s'",employerID);
        jdbcTemplate().update(deleteSql);

        String updateSql = "INSERT INTO INT_EmployerIndustrySector(EmployerID, IndustrySectorID) VALUES(?,?)";

        for (Integer industrySectorId : industrySectorID) {
            jdbcTemplate().update(updateSql, employerID, industrySectorId);
        }
    }


    // 39. Update new Employer / Preference
    public void updateEmployerPreferencesIntersection(int employerID, List<Integer> preferenceID) throws DataAccessException {
        String deleteSql = String.format("DELETE FROM INT_EmployerPreference WHERE EmployerID = '%s'",employerID);
        jdbcTemplate().update(deleteSql);

        String updateSql = "INSERT INTO INT_EmployerPreference(EmployerID, PreferenceID) VALUE(?,?)";

        for (Integer preferencesId : preferenceID ){
            jdbcTemplate().update(updateSql, employerID, preferencesId);
        }
    }

    // 40. Update new Employer / School Preference
    public void updateSchoolEmployerSchoolPreferencesIntersection(int employerID, List<Integer> schoolID) throws DataAccessException {
        String deleteSql = String.format("DELETE FROM INT_EmployerSchoolPreference WHERE EmployerID = '%s'",employerID);
        jdbcTemplate().update(deleteSql);

        String updateSql = "INSERT INTO INT_EmployerSchoolPreference(EmployerID, SchoolID) VALUES(?,?)";

        for (Integer schoolId : schoolID) {
            jdbcTemplate().update(updateSql, employerID, schoolId);
        }
    }

    // 41. Update new Employer / Curriculum Area
    public void updateEmployerEmployerCurriculumAreaIntersection(int employerID, List<Integer> areaOfCurriculumID) throws DataAccessException {
        String deleteSql = String.format("DELETE FROM INT_EmployerSupportOfAreaOfCurriculum WHERE EmployerID = '%s'",employerID);
        jdbcTemplate().update(deleteSql);

        String updateSql = "INSERT INTO INT_EmployerSupportOfAreaOfCurriculum(EmployerID, AreaOfCurriculumID) VALUE(?,?)";

        for (Integer areaOfCurriculumId : areaOfCurriculumID ){
            jdbcTemplate().update(updateSql, employerID, areaOfCurriculumId);
        }
    }

    // 42. Update new Employer / Language
    public void updateSchoolEmployerLanguageIntersection(int employerID, List<Integer> languageID) throws DataAccessException {
        String deleteSql = String.format("DELETE FROM INT_LanguageUsedByEmployer WHERE EmployerID = '%s'",employerID);
        jdbcTemplate().update(deleteSql);

        String updateSql = "INSERT INTO INT_LanguageUsedByEmployer(EmployerID, LanguageID) VALUES(?,?)";

        for (Integer languageId : languageID) {
            jdbcTemplate().update(updateSql, employerID, languageId);
        }
    }

    // 43. Update new Employer / Local Authorities
    public void updateSchoolEmployerLocalAuthoritiesIntersection(int employerID, List<Integer> localAuthorityID) throws DataAccessException {
        String deleteSql = String.format("DELETE FROM INT_LocalAuthorityEmployerCanWorkWith WHERE EmployerID = '%s'",employerID);
        jdbcTemplate().update(deleteSql);

        String updateSql = "INSERT INTO INT_LocalAuthorityEmployerCanWorkWith(EmployerID, LocalAuthorityID) VALUES(?,?)";

        for (Integer localAuthoritiesId : localAuthorityID) {
            jdbcTemplate().update(updateSql, employerID, localAuthoritiesId);
        }
    }

    // 44. Update new Employer /  Alumni Intersection
    public void updateEmployerAlumniIntersection(int employerID, List<Integer> alumniID) throws DataAccessException {
//            String deleteSql = String.format("DELETE FROM INT_AlumniWorkingForEmployer WHERE EmployerID = \"%s\"",EmployerID);
//            jdbcTemplate().update(deleteSql);

//        String updateSql = "INSERT INTO INT_AlumniWorkingForEmployer(AlumniID, EmployerID) VALUES(?,?)";
        for (int i = 0; i < alumniID.size(); i++) {
            String updateSql = String.format("INSERT INTO INT_AlumniWorkingForEmployer(AlumniID,EmployerID) VALUES(\"%s\",\"%s\")", alumniID.get(i),employerID);
            jdbcTemplate().update(updateSql);
        }
    }

    // 45. Update Alumni
    public void updateAlumni(List<String> alumniName, List<Integer> schoolID, List<Integer> alumniID) throws DataAccessException {

//        for(int i = 0; i < AlumniID.size(); i++) {
//            String deleteSql = String.format("DELETE FROM Alumni WHERE AlumniID = '%s'", AlumniID.get(i));
//            jdbcTemplate().update(deleteSql);
//        }
        for(int i = 0; i < alumniID.size(); i++){
            String updateSql = "UPDATE Alumni SET AlumniNameAndSurname = ?, AlumniSchoolID = ? WHERE AlumniID=?";
            jdbcTemplate().update(updateSql, alumniName.get(i), schoolID.get(i),alumniID.get(i));
        }
    }

    public void insertDocument(int employerID, String link) throws DataAccessException {

        String insertSql = "INSERT INTO EmployerDocumentLinks(EmployerID, Link) VALUES (?,?)";
        jdbcTemplate().update(insertSql, employerID, link);

    }

    public void deleteDocument(int employerID, String link) throws DataAccessException {

        String insertSql = "DELETE FROM employerdocumentlinks WHERE EmployerID = ? AND Link = ?";
        jdbcTemplate().update(insertSql, employerID, link);

    }


    public void insertLogoLink(int employerID, String link) throws DataAccessException {

        String insertSql = "UPDATE Employer SET LogoLink = ? WHERE EmployerID = ?;";
        jdbcTemplate().update(insertSql, link, employerID);

    }

    public void insertVideoLink(int employerID, String link) throws DataAccessException {

        String insertSql = "INSERT INTO EmployerVideoLinks(EmployerID, Link) VALUES (?,?)";
        jdbcTemplate().update(insertSql, employerID, link);

    }

    public void deleteVideoLink(int employerID, String link) throws DataAccessException {

        String insertSql = "DELETE FROM employervideolinks WHERE EmployerID = ? AND Link = ?";
        jdbcTemplate().update(insertSql, employerID, link);

    }

    public List <String> getEmployerDocuments(int employerID) throws DataAccessException {

        String sql = "SELECT Link FROM EmployerDocumentLinks WHERE EmployerID = " + employerID + ";";
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

    public List<String> getEmployerVideos(int employerID) throws DataAccessException {

        String sql = "SELECT Link FROM EmployerVideoLinks WHERE EmployerID = " + employerID + ";";
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

    ///////////////////////////////////// DELETE ALL METHODS ///////////////////////////////////////////////
    //46. Delete Employer by Id
    public Integer deleteEmployer(int employerId) throws DataAccessException {
        String deleteSql = String.format("DELETE FROM Employer WHERE EmployerID = '%s'",employerId);
        return jdbcTemplate().update(deleteSql);
    }
    //47. Delete Alumni by Id
    public Integer deleteAlumni(int alumniID) throws DataAccessException {
        String deleteSql = String.format("DELETE FROM Alumni WHERE AlumniID = '%s'",alumniID);
        return jdbcTemplate().update(deleteSql);
    }

    ///////////////////////////////////// FILTER METHODS ///////////////////////////////////////////////

    //48. Filter Employer
    public List<Integer> getFilteredEmployersIds(List<Integer> IndustrySectors,
                                                 List<Integer> LocalAuthorities, List<Integer> AreasOfCurriculum,
                                                 List<Integer> Languages, List<Integer> SchoolPreferences, List<Integer> EmployerPreferences,
                                                 List<Integer> CooperationTypes, List<Integer> AlumniSchools ) throws DataAccessException {

        List<Integer> ids = new ArrayList<>();
        connection = ConnectionFactory.getConnection();

        // return only live employers
        int StatusOfEmployer = 1;

        ResultSet rs = null;

        List<List<Integer>> listOfFilters = Arrays.asList(IndustrySectors, LocalAuthorities, AreasOfCurriculum,
                Languages, SchoolPreferences, EmployerPreferences, CooperationTypes, AlumniSchools);

        List<String> listOfSQL = Arrays.asList("SELECT eis.EmployerID FROM INT_EmployerIndustrySector AS eis",
                "SELECT la.EmployerID FROM INT_LocalAuthorityEmployerCanWorkWith as la",
                "SELECT ac.EmployerID FROM INT_EmployerSupportOfAreaOfCurriculum as ac",
                "SELECT l.EmployerID FROM INT_LanguageUsedByEmployer as l",
                "SELECT sp.EmployerID FROM INT_EmployerSchoolPreference as sp",
                "SELECT ep.EmployerID FROM INT_EmployerPreference as ep",
                "SELECT ct.EmployerID FROM INT_EmployerCooperationType as ct",
                "SELECT ae.EmployerID FROM INT_AlumniWorkingForEmployer AS ae WHERE ae.AlumniID IN (SELECT a.AlumniID FROM Alumni AS a");

        List<String> listOfExtensions = Arrays.asList("eis.IndustrySectorID = ", "la.LocalAuthorityID = ", "ac.AreaOfCurriculumID = ",
                "l.LanguageID = ", "sp.SchoolID = ", "ep.PreferenceID = ", "ct.CooperationTypeID = ", "a.AlumniSchoolID = ");

        List<String> generatedSQL = new ArrayList<>();

        // Prepare Queries
        for(int i = 0; i < listOfFilters.size(); ++i){
            if (listOfFilters.get(i).size() != 0){
                listOfSQL.set(i,listOfSQL.get(i).concat(" WHERE "));
            } else {
                listOfSQL.set(i,listOfSQL.get(i).concat(""));
            }

            String Extension = "";
            for (int k = 0; k < listOfFilters.get(i).size(); ++k) {
                Extension = Extension.concat(listOfExtensions.get(i) + listOfFilters.get(i).get(k) + " ");
                if (k != listOfFilters.get(i).size()-1){
                    Extension = Extension.concat("OR ");
                } else {
                    // Trap for AlumniSchool to close the statement
                    if (i == listOfFilters.size()-1){
                        Extension = Extension.concat(")");
                    }
                    Extension = Extension.concat("");
                }
            }
            generatedSQL.add(listOfSQL.get(i)+Extension);
        }

        // If AlumniSchool is empty, add ")" to the string in order to close statement
        if (listOfFilters.get(listOfFilters.size()-1).size() == 0){
            generatedSQL.set(listOfFilters.size()-1,generatedSQL.get(generatedSQL.size()-1).concat(")"));
        }

        String finalSQL = "SELECT distinct EmployerID FROM Employer WHERE EmployerID IN ";
        for (int i = 0; i < generatedSQL.size(); ++i){
            finalSQL = finalSQL.concat("( " + generatedSQL.get(i) + ") ");
            if (i != generatedSQL.size()-1){
                finalSQL = finalSQL.concat(" AND EmployerID IN ");
            } else{
                finalSQL = finalSQL.concat("");
            }
        }
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(finalSQL);
            while (rs.next()) {
                ids.add(rs.getInt("EmployerID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs);
        }

        return ids;
    }
    ///////////////////////////////////// SORT BY METHODS ///////////////////////////////////////////////

    //49. Get Employers order by name
    public List<Integer> sortByEmployerByName(String type) throws DataAccessException {
        String getQuery = String.format("SELECT * FROM Employer ORDER BY EmployerName %s;", type);
         List<Integer> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                list.add(rs.getInt("EmployerID"));
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
}
