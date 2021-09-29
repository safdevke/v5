package dao;

import model.User;
import util.Dbutil;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dbutil {

    public void addUser(User user) {
        String query;

        query = "insert into users(name,email,mobilenumber,securityQuestion,answer,password) values(";
        query += "'" + user.getName() + "',";
        query += "'" + user.getEmail() + "',";
        query += "'" + new BigInteger(user.getMobileNumber()) + "',";
        query += "'" + user.getSecurityQuestion() + "',";
        query += "'" + user.getAnswer() + "',";
        query += "'" + user.getPassword() + "');";

        execUpdate(query);
    }

    public boolean validateUserCredentials (User user) throws SQLException {
        String query;

        query = "select * from users where email=";
        query += "'" + user.getEmail() + "'";
        query += " and password=";
        query += "'" + user.getPassword() + "';";

        ResultSet rs = execQuery(query);

        return rs.next();
    }

    public ResultSet listUserCredentials (String email, String password) throws SQLException {
        String query;

        query = "select * from users where email=";
        query += "'" + email + "'";
        query += " and password=";
        query += "'" + password + "';";

        return execQuery(query);
    }

    public void updateUserPassword(String email, String password) {
        String query;

        query = "update users set password=";
        query += "'" + email + "'" ;
        query += " where email=";
        query += "'" + password + "';";

        execUpdate(query);
    }

    public boolean updateUserCredentials (User user) throws SQLException {
        String query;

        query = "select * from users where email=";
        query += "'" + user.getEmail() + "'";
        query += " and mobilenumber=";
        query += "'" + user.getMobileNumber() + "'";
        query += " and securityQuestion=";
        query += "'" + user.getSecurityQuestion()+ "'";
        query += " and answer=";
        query += "'" + user.getAnswer() + "';";
        System.out.println(query);
        ResultSet rs = execQuery(query);

        while (rs.next()) {
            execUpdate("update users set password=" + "'" + user.getPassword() + "'"
                            + " where email=" + "'" + user.getEmail() + "';");
            return true;
        }
        return false;
    }

    public ResultSet findUser(String email) {
        String query;

        query = "select * from users where email=";
        query += "'" + email + "';";

        return execQuery(query);
    }

    public ResultSet innerJoin(String email) {
        String query;

        query = "select * from users inner join cart where cart.email=";
        query += "'" + email + "'";
        query += " and cart.status='bill';";

        return execQuery(query);
    }
}
