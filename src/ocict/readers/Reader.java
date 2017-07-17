package ocict.readers;

import ocict.db.DbConnection;
import ocict.list.*;
import ocict.config.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.logging.Logger;


/**
 * Created by cstegehuis on 25/06/17.
 */
public class Reader {

    protected static DbConnection connection;
    protected static String invoerRegel;
    protected static int optie;
    protected static int bestellingOptie;
    protected static int leverancierOptie;

    protected static BestelList bestelList;
    protected static PlantList plantList;
    protected static LeverancierList leverancierList;

    protected static Date newLeverDatum;

    protected static Logger logger;
    protected static BufferedReader reader;

    Reader(){}

    public Reader(DbConnection pConnection, Logger pLogger) {
        this.connection = pConnection;
        this.logger = pLogger;

        this.bestelList = new BestelList(pConnection);
        this.plantList = new PlantList(pConnection);
        this.leverancierList = new LeverancierList(pConnection);
        this.reader = new BufferedReader(new InputStreamReader(System.in));

    }

    public static void toonHoofdMenu() {
        Utilities.toonRegel("\n");
        Utilities.toonRegel("1. Bestelling overzicht");
        Utilities.toonRegel("2. Leverancier overzicht");
        Utilities.toonRegel("3. Plant overzicht");
        Utilities.toonRegel("4. Sluit het programma af");
    }


    public void kiesHoofdMenu(int pOptie){
    }

    public void hoofdMenuOptie() {
        Reader reader = new Reader(connection, logger);
        reader.toonHoofdMenu();

        int options = 4;
        int optie = Reader.kiesMenuOptie(options);

        kiesHoofdMenu(optie);
    }

    public static int kiesMenuOptie(int pSize){

        int menu_optie = 0;

        try {
            boolean keuze = true;

            Utilities.toonRegel("\nKies een optie uit het menu: toets het cijfer in en druk op enter ");
            invoerRegel = reader.readLine();

            while (keuze){
                if (invoerRegel.length() == 0){
                    Utilities.toonRegel("Dit is geen geldige optie, kies een optie uit het menu en druk op enter");
                    invoerRegel = reader.readLine();
                }

                else {
                    optie = Integer.parseInt(invoerRegel);
                    if (optie > pSize){
                        Utilities.toonRegel("Dit is geen geldige optie, kies een optie uit het menu en druk op enter");
                        invoerRegel = reader.readLine();
                    }

                    else {
                        keuze = false;
//                        kiesHoofdMenu(optie);

                        menu_optie = optie;
                    }
                }
            }

        } catch (IOException e) {
            Utilities.toonRegel("IO fout bij invoer");
        }


        return menu_optie;
    }

    /* Sluit programma */
    public static void sluitProgramma(){
        Utilities.toonRegel("Het programma sluit nu af");
        logger.info("De sessie is gesloten");
        System.exit(0);
    }


}
