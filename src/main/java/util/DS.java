package util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DS {

    private static DS ds;
    private DataSource dataSource;

    private DS() {

        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:jboss/datasources/vue");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static DS getDS(){
        if(ds == null) {
            ds = new DS();
        }
        return ds;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}

