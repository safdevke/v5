package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Dbutil {

    public Connection getDbConnection() throws Exception {
        return DS.getDS().getDataSource().getConnection();
    }

    public ResultSet execQuery(String sql) {

        if (sql == null || sql.trim( ).equals("")) {
            return null;
        }

        try {
            PreparedStatement statement = getDbConnection( ).prepareStatement(sql);
            return statement.executeQuery();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void execUpdate(String sql) {

        if (sql == null || sql.trim( ).equals(""))
            return;

        try {
            PreparedStatement statement = getDbConnection( ).prepareStatement(sql);
            statement.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return;
    }

    public void finalize() {
        try {
            getDbConnection().close();
        } catch (Exception e) {
            e.printStackTrace( );
        }
    }
}

