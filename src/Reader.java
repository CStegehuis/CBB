import ocict.db.DbConnection;
import ocict.leverancier.Leverancier;
import ocict.list.*;
import ocict.bestelling.*;
import ocict.config.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.Map;


/**
 * Created by cstegehuis on 25/06/17.
 */
public class Reader {

    private DbConnection connection;
    private String invoerRegel;
    private int optie;
    private int bestellingOptie;
    private int leverancierOptie;

    private BestelList bestelList;
    private PlantList plantList;
    private LeverancierList leverancierList;

    private Date newLeverDatum;

    private Logger logger;
    private BufferedReader reader;

    Reader(DbConnection pConnection, Logger pLogger) {
        this.connection = pConnection;
        this.logger = pLogger;

        this.bestelList = new BestelList(pConnection);
        this.plantList = new PlantList(pConnection);
        this.leverancierList = new LeverancierList(pConnection);
        this.reader = new BufferedReader(new InputStreamReader(System.in));

//        this.plantReader = new ReaderPlant(reader, plantList);

        hoofdMenuOptie();
    }


    public int kiesMenuOptie(int pSize){

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


    public void toonHoofdMenu() {
        Utilities.toonRegel("\n");
        Utilities.toonRegel("1. Bestelling overzicht");
        Utilities.toonRegel("2. Leverancier overzicht");
        Utilities.toonRegel("3. Plant overzicht");
        Utilities.toonRegel("4. Sluit het programma af");
    }

    public void kiesHoofdMenu(int pOptie){
        switch (pOptie) {
            case 1:
                bestellingOptie();
                break;
            case 2:
                leverancierOptie();
                break;
            case 3:
                plantenOptie();
                break;
        }
    }

    public void hoofdMenuOptie() {
        toonHoofdMenu();

        int options = 4;
        int optie = kiesMenuOptie(options);

        kiesHoofdMenu(optie);
    }


    public void toonBestellingMenu() {
        Utilities.toonRegel("\n");
        Utilities.toonRegel("1. Bestelling inzien");
        Utilities.toonRegel("2. Bestelling wijzigen");
        Utilities.toonRegel("3. Bestelling verwijderen");
        Utilities.toonRegel("4. Bestelling toevoegen - in aanbouw");
        Utilities.toonRegel("5. Terug naar hoofdmenu");
        Utilities.toonRegel("6. Sluit het programma af");

    }

    public void kiesBestellingMenu(int pOptie){
        switch (pOptie) {
            case 1:
                toonBestelling();
                break;
            case 2:
                wijzigBestelling();
                break;
            case 3:
                verwijderBestelling();
                break;
            case 4:
                bestellingToevoegen();
                break;
            case 5:
                hoofdMenuOptie();
                break;
            case 6:
                sluitProgramma();
                break;
            default:
                toonBestelling();
                break;

        }
    }

    public void bestellingOptie() {
        toonBestellingMenu();

        int options = 5;
        int optie = kiesMenuOptie(options);

        kiesBestellingMenu(optie);
    }


    public void toonLeverancierMenu() {
        Utilities.toonRegel("\n");
        Utilities.toonRegel("1. Toon leverancier overzicht");
        Utilities.toonRegel("2. Leverancier wijzigen");
        Utilities.toonRegel("3. Leverancier toevoegen - in aanbouw");
        Utilities.toonRegel("4. Leverancier verwijderen - in aanbouw");
        Utilities.toonRegel("5. Terug naar hoofdmenu");
        Utilities.toonRegel("6. Sluit het programma af");

    }

    public void kiesLeverancierMenu(int pOptie){
        switch (pOptie) {
            case 1:
                toonLeveranciers();
                break;
            case 2:
                wijzigLeverancier();
                break;
            case 3:
                voegLeverancierToe();
                break;
            case 4:
                verwijderLeverancier();
                break;
            case 5:
                hoofdMenuOptie();
                break;
            case 6:
                sluitProgramma();
                break;
            default:
                toonLeveranciers();
                break;
        }
    }

    public void leverancierOptie() {
        toonLeverancierMenu();

        int options = 5;
        int optie = kiesMenuOptie(options);

        kiesLeverancierMenu(optie);
    }


    public void toonPlantMenu() {
        Utilities.toonRegel("\n");
        Utilities.toonRegel("1. Toon planten overzicht");
        Utilities.toonRegel("2. Product (plant) wijzigen - in aanbouw");
        Utilities.toonRegel("3. Product (plant) toevoegen - in aanbouw");
        Utilities.toonRegel("4. Product (plant) verwijderen - in aanbouw");
        Utilities.toonRegel("5. Terug naar hoofdmenu");
        Utilities.toonRegel("6. Sluit het programma af");

    }

    public void kiesPlantMenu(int pOptie){
        switch (pOptie) {
            case 1:
                toonPlanten();
                break;
            case 2:
                wijzigPlant();
                break;
            case 3:
                voegPlantToe();
                break;
            case 4:
                verwijderPlant();
                break;
            case 5:
                hoofdMenuOptie();
                break;
            case 6:
                sluitProgramma();
                break;
            default:
                toonPlanten();
                break;
        }
    }

    public void plantenOptie() {
        toonPlantMenu();

        int options = 5;
        int optie = kiesMenuOptie(options);

        kiesPlantMenu(optie);
    }


/* Kies bestelnummer voor verder gebruik */
    public int kiesBestelNr() {

        bestelList.toonOverzicht();

        try {
            Utilities.toonRegel("\nKies een bestelling om de informatie weer te geven. Toets een bestelnummer in en druk op enter. ");
            invoerRegel = reader.readLine();
            bestellingOptie = Integer.parseInt(invoerRegel);
        } catch (IOException e) {
            Utilities.toonRegel("IO fout bij invoer");
        }

        return bestellingOptie;
    }


/* Toon bestelling */
    public void toonBestelling() {
        int bestel_nummer = kiesBestelNr();
        if (!this.bestelList.containsKey(bestel_nummer)) {
            // user chose bestellingnr that does not excist
            Utilities.toonRegel("Deze bestelling komt niet voor in de database ");
            toonBestelling();
        } else {
            // bestellingnr bestaat in list
            Bestelling bestelling = bestelList.get(bestel_nummer);
            bestelling.toonOverzicht();

            try {
                Utilities.toonRegel("Kies een van de volgende opties: \n1. Bestelling wijzigen " +
                        "\n2. Bestelling verwijderen \n3. Ga terug naar bestelling menu\n4. Ga terug naar hoofdmenu");
                invoerRegel = reader.readLine();
                optie = Integer.parseInt(invoerRegel);

            } catch (IOException e) {
                Utilities.toonRegel("IO fout bij invoer");
            }

            if (optie == 1) {
                wijzigBestelling(bestel_nummer);
            } else if (optie == 2) {
                verwijderBestelling(bestel_nummer);
            } else if (optie == 3) {
                bestellingOptie();
            } else if (optie == 4) {
                hoofdMenuOptie();
            } else {
                Utilities.toonRegel("U heeft geen geldige keuze opgegeven. U gaat terug naar het bestellingmenu");
                bestellingOptie();
            }

        }
    }


/* Bestelling wijzigen */
    public void wijzigBestelling() {
        int bestel_nummer = kiesBestelNr();

        Bestelling bestelling = bestelList.get(bestel_nummer);
        kiesWijzigOptie(bestelling);
    }

    public void wijzigBestelling(int pBestelNr) {

        Bestelling bestelling = bestelList.get(pBestelNr);
        kiesWijzigOptie(bestelling);
    }

    public void kiesWijzigOptie(Bestelling bestelling) {

        try {

            boolean keuze = true;


            Utilities.toonRegel("\n De waarden die u kunt wijzigen in deze bestelling zijn: \n" +
                    "1. De lever datum\n2. De status \n \n\nKies het nummer van de waarde die u wilt wijzigen.");
            invoerRegel = reader.readLine();

            while (keuze){
                if (invoerRegel.length() == 0){
                    Utilities.toonRegel("Dit is geen geldige keuze. Kies 1 voor lever datum of 2 voor status");
                    invoerRegel = reader.readLine();
                }

                else {
                    optie = Integer.parseInt(invoerRegel);
                    if (optie > 2){
                        Utilities.toonRegel("Dit is geen geldige keuze. Kies 1 voor lever datum of 2 voor status");
                        invoerRegel = reader.readLine();
                    }

                    else {
                        keuze = false;

                        switch (optie) {
                            case 1:
                                pasLevDatumAan(bestelling);
                                break;
                            case 2:
                                pasStatusAan(bestelling);
                                break;
                        }
                    }
                }
            }

        } catch (IOException e) {
            Utilities.toonRegel("IO fout bij invoer");
        }
    }

    public void pasLevDatumAan(Bestelling bestelling) {
        try {
            Utilities.toonRegel("\nGeef de waarde op waarin u de lever datum wil wijzigen. Gebruik het format YYYY-MM-DD");
            invoerRegel = reader.readLine();
        } catch (IOException e) {
            Utilities.toonRegel("IO fout bij invoer");
        }

        newLeverDatum = Date.valueOf(invoerRegel);

        if (newLeverDatum != bestelling.getLeverDatum()) {
            bestelling.setLeverDatum(newLeverDatum);
        }

        this.bestelList = new BestelList(connection);
        bestellingOptie();
    }

    public void pasStatusAan(Bestelling bestelling) {
        try {
            Utilities.toonRegel("\nGeef de waarde op waarin u de status wil wijzigen. De toegestane waarden zijn" +
                    "\n1. N = Niet-Compleet\n2. C = Compleet\n3. O = Onderweg");
            invoerRegel = reader.readLine().trim();
        } catch (IOException e) {
            Utilities.toonRegel("IO fout bij invoer");
        }


        if (invoerRegel.equals("N") | invoerRegel.equals("C") | invoerRegel.equals("O")) {
            bestelling.setStatus(invoerRegel.trim());
        } else {
            Utilities.toonRegel("Deze invoer is incorrect, kies C (Compleet), N (Niet-Compleet) of O (Onbekend)");
        }

        this.bestelList = new BestelList(connection);
        bestellingOptie();
    }

/* Verwijder bestelling */
    public void verwijderBestelling(){
        int bestel_nummer = kiesBestelNr();

        Bestelling bestelling = bestelList.get(bestel_nummer);
        bestelling.verwijderBestelling();

        this.bestelList = new BestelList(connection);

        bestellingOptie();
    }

    public void verwijderBestelling(int pBestelNr){

        Bestelling bestelling = bestelList.get(pBestelNr);
        bestelling.verwijderBestelling();

        this.bestelList = new BestelList(connection);

        bestellingOptie();
    }

/* Voeg bestelling toe */
    public void bestellingToevoegen(){
        Utilities.toonRegel("in aanbouw");
        //variabelen bestelling laten opgeven
        //variabelen bestelregel laten opgeven
        //variabelen toevoegen aan db, zie link
        //leverancier apart?
        //plant apart?
        //leverancier printen voor voorbeeld, levcode?
        //plant printen voor voorbeeld plantcode?

        Utilities.toonRegel("Om een bestelling toe te voegen, moet u de volgende waarden opgeven:" +
                "\n- Bestel nummer \n- Leverancierscode \n- Bestel datum \n- Lever datum \n- Status\n\n");

        try {
            Utilities.toonRegel("\nVoer het bestel nummer in ");
            invoerRegel = reader.readLine();
            int bestel_nummer = Integer.parseInt(invoerRegel);

            Utilities.toonRegel("Voer de leverancierscode in ");
            invoerRegel = reader.readLine();
            int lev_code = Integer.parseInt(invoerRegel);

            Utilities.toonRegel("Voer de bestel datum in ");
            invoerRegel = reader.readLine();
            Date bestel_datum = Date.valueOf(invoerRegel);

            Utilities.toonRegel("Voer de lever datum in ");
            invoerRegel = reader.readLine();
            Date lever_datum = Date.valueOf(invoerRegel);

            Utilities.toonRegel("Voer de status in ");
            invoerRegel = reader.readLine();
            String status = invoerRegel.trim();


//            toonRegel("De waarden die u hebt opgegeven zijn: " + bestel_nummer + " " + lev_code + " "
//                    + bestel_datum + " " + lever_datum + " " + status +  ". Klopt dit?");



        } catch (IOException e) {
            Utilities.toonRegel("IO fout bij invoer");
        }

    }

    public void bestelRegelToevoegen() {
        Utilities.toonRegel("Om een artikel toe te voegen, moet u de volgende waarden opgeven:" +
                "\n- artkel nr \n- aantal \n- bestel prijs \n\n");

        try {
            Utilities.toonRegel("\nVoer het bestel nummer in ");
            invoerRegel = reader.readLine();
            int bestel_nummer = Integer.parseInt(invoerRegel);

            Utilities.toonRegel("Voer de leverancierscode in ");
            invoerRegel = reader.readLine();
            int lev_code = Integer.parseInt(invoerRegel);



//            toonRegel("De waarden die u hebt opgegeven zijn: " + bestel_nummer + " " + lev_code + " "
//                    + bestel_datum + " " + lever_datum + " " + status +  ". Klopt dit?");



        } catch (IOException e) {
            Utilities.toonRegel("IO fout bij invoer");
        }
    }




/* Planten */
    public void toonPlanten(){
        plantList.toonPlanten();

        plantenOptie();
    }

    public void wijzigPlant(){
        Utilities.toonRegel("in aanbouw");
    }

    public void voegPlantToe(){Utilities.toonRegel("in aanbouw");}

    public void verwijderPlant(){Utilities.toonRegel("in aanbouw");}



/* ------------------------------------------------------
   Leveranciers
   -------------------------------------------------------*/

    public int kiesLevCode() {

        leverancierList.toonLeveranciers();

        try {
            Utilities.toonRegel("\nKies een leverancier. Toets een leverancierscode in en druk op enter. ");
            invoerRegel = reader.readLine();
            leverancierOptie = Integer.parseInt(invoerRegel);
        } catch (IOException e) {
            Utilities.toonRegel("IO fout bij invoer");
        }

        return leverancierOptie;
    }

/* Toon Leveranciers*/
    public void toonLeveranciers(){
        leverancierList.toonLeveranciers();

        leverancierOptie();
    }

/* Wijzig Leveranciers*/
    public void wijzigLeverancier(){

        Utilities.toonRegel("in aanbouw");
        int lev_code = kiesLevCode();

        Leverancier lev = leverancierList.get(lev_code);

        kiesWijzigLev(lev);
        }

    public void kiesWijzigLev(Leverancier leverancier) {

        try {
            boolean keuze = true;
            Utilities.toonRegel("\n De waarden die u kunt wijzigen zijn: \n" +
                    "1. Leveranciersnaam\n2. Adres \n3.Woonplaats \n\nKies het nummer van de waarde die u wilt wijzigen.");
            invoerRegel = reader.readLine();

            while (keuze){
                if (invoerRegel.length() == 0){
                    Utilities.toonRegel("Dit is geen geldige keuze. Kies 1 voor naam, 2 voor adres of 3 voor woonplaats");
                    invoerRegel = reader.readLine();
                }

                else {
                    optie = Integer.parseInt(invoerRegel);
                    if (optie > 2){
                        Utilities.toonRegel("Dit is geen geldige keuze. Kies 1 voor naam, 2 voor adres of 3 voor woonplaats");
                        invoerRegel = reader.readLine();
                    }

                    else {
                        keuze = false;

                        switch (optie) {
                            case 1:
                                pasLevNaamAan(leverancier);
                                break;
                            case 2:
                                pasLevAdresAan(leverancier);
                                break;
                            case 3:
                                pasLevWoonplaatsAan(leverancier);
                                break;
                        }
                    }
                }
            }

        } catch (IOException e) {
            Utilities.toonRegel("IO fout bij invoer");
        }
    }

    public void pasLevNaamAan(Leverancier leverancier) {
        try {
            Utilities.toonRegel("\nGeef de waarde op waarin u de lever datum wil wijzigen. Gebruik het format YYYY-MM-DD");
            invoerRegel = reader.readLine();
        } catch (IOException e) {
            Utilities.toonRegel("IO fout bij invoer");
        }

        if (invoerRegel.trim() != leverancier.getLevNaam()) {
            leverancier.setLevNaam(invoerRegel.trim());
        }

        this.leverancierList = new LeverancierList(connection);
        leverancierOptie();
    }

    public void pasLevAdresAan(Leverancier leverancier) {
        try {
            Utilities.toonRegel("\nGeef de waarde op waarin u de status wil wijzigen. De toegestane waarden zijn" +
                    "\n1. N = Niet-Compleet\n2. C = Compleet\n3. O = Onderweg");
            invoerRegel = reader.readLine().trim();
        } catch (IOException e) {
            Utilities.toonRegel("IO fout bij invoer");
        }


        if (invoerRegel.trim() != leverancier.getAdres()) {
            leverancier.setAdres(invoerRegel.trim());
        }

        this.leverancierList = new LeverancierList(connection);
        leverancierOptie();
    }

    public void pasLevWoonplaatsAan(Leverancier leverancier) {
        try {
            Utilities.toonRegel("\nGeef de waarde op waarin u de status wil wijzigen. De toegestane waarden zijn" +
                    "\n1. N = Niet-Compleet\n2. C = Compleet\n3. O = Onderweg");
            invoerRegel = reader.readLine().trim();
        } catch (IOException e) {
            Utilities.toonRegel("IO fout bij invoer");
        }

        if (invoerRegel.trim() != leverancier.getWoonplaats()) {
            leverancier.setWoonplaats(invoerRegel.trim());
        }

        this.leverancierList = new LeverancierList(connection);
        leverancierOptie();
    }

/* Toon Leveranciers*/
    public void voegLeverancierToe(){
        Utilities.toonRegel("in aanbouw");
    }

/* Verwijder Leveranciers*/
    public void verwijderLeverancier(){Utilities.toonRegel("in aanbouw");}



    /* Sluit programma */
    public void sluitProgramma(){
        Utilities.toonRegel("Het programma sluit nu af");
        logger.info("De sessie is gesloten");
        System.exit(0);
    }


}
