package ocict.readers;

import ocict.bestelling.Bestelling;
import ocict.config.Utilities;
import ocict.leverancier.Leverancier;
import ocict.list.BestelList;

import java.io.IOException;
import java.sql.Date;

/**
 * Created by cstegehuis on 09/07/17.
 */
public class BestellingReader extends Reader {


    PlantReader plantReader;
    LeverancierReader levReader;

    public BestellingReader(){
        this.plantReader = new PlantReader();
        this.levReader = new LeverancierReader();
    }

    public void kiesHoofdMenu(int pOptie){
        switch (pOptie) {
            case 1:
                bestellingOptie();
                break;
            case 2:
                levReader.leverancierOptie();
                break;
            case 3:
                plantReader.plantenOptie();
                break;
        }
    }


    public void toonBestellingMenu() {
        Utilities.toonRegel("\n");
        Utilities.toonRegel("1. Bestelling inzien");
        Utilities.toonRegel("2. Bestelling wijzigen - bestelregel wijzigen?");
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

        newLeverDatum = java.sql.Date.valueOf(invoerRegel);

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
            Date bestel_datum = java.sql.Date.valueOf(invoerRegel);

            Utilities.toonRegel("Voer de lever datum in ");
            invoerRegel = reader.readLine();
            Date lever_datum = java.sql.Date.valueOf(invoerRegel);

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


}
