package ocict.db;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by cstegehuis on 23/06/17.
 */
public class DbConnection {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String MAX_POOL = "250";

    private String user;
    private String naam;
    private String password;
    private String host;
    private Logger logger;

    private Connection conn;
    private Properties properties;


    public DbConnection(String[] databaseinfo, Logger pLogger){
        this.conn = null;
        this.logger = pLogger;

        this.host = databaseinfo[0];
        this.naam = databaseinfo[1];
        this.user = databaseinfo[2];
        this.password = databaseinfo[3];
    }

    private Properties getProperties(){
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("MaxPooledSTatements", MAX_POOL);
            properties.setProperty("useSSL", "false");
        }
        return properties;
    }

    public void createConnection() {
        String db_url = "jdbc:mysql://localhost:" + host + "/" + naam;

        try {
            Class.forName(JDBC_DRIVER);
//            System.out.println("Connecting to the database");
            logger.info("Connecting to the database \n");

            conn = DriverManager.getConnection(db_url, getProperties());

        } catch (SQLException e) {
            e.printStackTrace();

            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResultSet(String query) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        return rs;
    }

    public void updateDb(String pQuery) throws SQLException{
        Statement st = conn.createStatement();
        st.executeUpdate(pQuery);
    }

    public void closeConnection() {
        try{
            conn.close();
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

    }
}
