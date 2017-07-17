import ocict.config.Utilities;
import ocict.db.DbConnection;
import ocict.readers.*;

import java.util.logging.Logger;

/**
 * Created by cstegehuis on 09/07/17.
 */
public class MainReader {

    protected static DbConnection connection;
    protected static Logger logger;

    private BestellingReader bestelReader;
    private PlantReader plantReader;
    private LeverancierReader levReader;


    MainReader(DbConnection pConnection, Logger pLogger) {
        this.connection = pConnection;
        this.logger = pLogger;

        this.bestelReader = new BestellingReader();
        this.levReader = new LeverancierReader();
        this.plantReader = new PlantReader();

        hoofdMenu();

    }

    public void hoofdMenu(){
        Reader reader = new Reader(connection, logger);
        reader.toonHoofdMenu();
    }






}
