import ocict.config.Config;
import ocict.db.DbConnection;

import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * Created by cstegehuis on 25/06/17.
 */

public class RunProgramma {
    private String[] database_info;
    private DbConnection connection;
    private Logger logger;

//    private ResultSet result;

    RunProgramma(){
        this.database_info = null;

        initializeConfig();
        initializeLogFile();
    }

    /* De database waarden worden uit de config file gehaald en weggeschreven naar een array voor gebruik.*/
    public String[] initializeConfig(){
        System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));

        String config_file = System.getProperty("user.dir")+"/conf/config.ini";
        Config config = new Config(config_file);

        try {
            database_info = config.getDatabaseInfo();
        } catch (IOException e){
            System.out.println("SQLException: " + e.getMessage());
        }
        return database_info;
    }


    public void initializeLogFile(){
        logger = Logger.getLogger("Log file");
        FileHandler fh;

        try {

            // Initialiseren logger met handler en formatter
            fh = new FileHandler(System.getProperty("user.dir")+"/log/logfile.txt");
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);

            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("Log file - de sessie is geopend \n");


        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Het creeren van de database connectie */
    public void createDbConnection(){
        logger.info("Creating the database connection \n");
        connection = new DbConnection(database_info, logger);
        connection.createConnection();
        logger.info("The database connection is created \n");
    }

    /* Methode voor het sluiten van de database */
    public void closeDbConnection(){
        logger.info("Closing the database connection \n");
        connection.closeConnection();
        logger.info("The database connection is closed \n");
    }

    public void run(){
        createDbConnection(); //De database connectie wordt gemaakt

        Reader reader = new Reader(connection, logger);

        closeDbConnection();

    }

}
