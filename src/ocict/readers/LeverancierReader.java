package ocict.readers;

import ocict.config.Utilities;
import ocict.leverancier.Leverancier;
import ocict.list.LeverancierList;

import java.io.IOException;

/**
 * Created by cstegehuis on 09/07/17.
 */
public class LeverancierReader extends Reader {

    BestellingReader bestelReader;
    PlantReader plantReader;

    public LeverancierReader(){
        this.bestelReader = new BestellingReader();
        this.plantReader = new PlantReader();
    }

    public void kiesHoofdMenu(int pOptie){
        switch (pOptie) {
            case 1:
                bestelReader.bestellingOptie();
                break;
            case 2:
                leverancierOptie();
                break;
            case 3:
                plantReader.plantenOptie();
                break;
        }
    }

    public void toonLeverancierMenu() {
        Utilities.toonRegel("\n");
        Utilities.toonRegel("1. Toon leverancier overzicht");
        Utilities.toonRegel("2. Leverancier wijzigen - testen");
        Utilities.toonRegel("3. Leverancier toevoegen - in aanbouw");
        Utilities.toonRegel("4. Leverancier verwijderen - geen optie?");
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


}
