package ebe.DBMethods;

import ebe.DBClasses.School;
import ebe.DBClasses.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.net.ssl.SSLEngine;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class AuthorisationQueries extends DBQueries {

    private Connection connection;
    private Statement statement;

    @Autowired
    public AuthorisationQueries(JdbcTemplate jdbctemplate) {
        super(jdbctemplate);
    }

    @Autowired
    private HttpServletRequest context;

    ///////////////////////////////////// GET ALL METHODS ///////////////////////////////////////////////

    // 1. Get User Role CWS
    public String getUserRoleCWS(String email) throws DataAccessException {
        String getQuery = String.format("SELECT * FROM CWSMemberList WHERE CWSEmail = \"%s\"", email);
        String role = "none";
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                if (!((rs.getString("CWSEmail")==""))) {
                    role = "CWS";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return role;
    }

    // 2. Get User Role Teacher
    public String getUserRoleTeacher(String email, HttpServletRequest request) throws DataAccessException {
        String getQuery = String.format("SELECT * FROM School WHERE SchoolEmail = \"%s\"", email);
        String role = "none";
        ResultSet rs = null;
        System.out.println(email);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(getQuery);
            while (rs.next()) {
                if (!((rs.getString("SchoolEmail") == ""))) {
                    role = "Teacher";
                    request.getSession().setAttribute("SESSION_UserID", rs.getString("SchoolID"));
                    request.getSession().setAttribute("SESSION_SchoolName", rs.getString("SchoolName"));
                    request.getSession().setAttribute("SESSION_SchoolPostCode", rs.getString("SchoolPostCode"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return role;
    }


}

