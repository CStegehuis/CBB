package ocict.readers;

import ocict.config.Utilities;
import ocict.product.Plant;

/**
 * Created by cstegehuis on 09/07/17.
 */
public class PlantReader extends Reader {

    BestellingReader bestelReader;
    LeverancierReader levReader;

    public PlantReader(){
        this.bestelReader = new BestellingReader();
        this.levReader = new LeverancierReader();
    }

    public void kiesHoofdMenu(int pOptie){
        switch (pOptie) {
            case 1:
                bestelReader.bestellingOptie();
                break;
            case 2:
                levReader.leverancierOptie();
                break;
            case 3:
                plantenOptie();
                break;
        }
    }

    public void toonPlantMenu() {
        Utilities.toonRegel("\n");
        Utilities.toonRegel("1. Toon planten overzicht");
        Utilities.toonRegel("2. Product (plant) wijzigen - optie?");
        Utilities.toonRegel("3. Product (plant) toevoegen - in aanbouw");
        Utilities.toonRegel("4. Product (plant) verwijderen - geen optie");
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


}
