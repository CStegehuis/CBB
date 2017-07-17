package ocict.list;

import ocict.bestelling.Bestelling;
import ocict.config.Utilities;
import ocict.db.DbConnection;
import ocict.product.Plant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by cstegehuis on 04/07/17.
 */
// was CreateBestelingArray()
public class PlantList extends TreeMap<Integer, Plant> {

    private DbConnection connection;

    // constructor
    public PlantList(DbConnection pConnection){
        this.connection = pConnection;
        this.fillPlantList();
    }

    // method
    protected void fillPlantList(){

        ResultSet resultset;
        try {

            resultset = connection.getResultSet("SELECT * FROM planten");

            if (!resultset.isBeforeFirst()) {
                System.out.println("Er zit geen data in de resultset");
            } else {

                while(resultset.next()) {
                    Plant plant = new Plant(resultset);
                    this.put(plant.getArtCode(), plant);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("plantlist 1");

            System.out.println("SQLException: " + e.getMessage());
        }

        return;
    }

    public void toonPlanten() {
        Utilities.toonRegel("\nDe planten in de database: ");
        System.out.printf("%-11s | %-17s | %-10s | %-7s | %-8s | %-7s | %-7s | %-8s | %-11s | %-9s\n","Artikelcode", "Plantnaam",
                "Soort", "Kleur", "Hoogte", "Begin", "Einde", "Prijs", "Aantal op", "Minimale ");
        System.out.printf("%-11s | %-17s | %-10s | %-7s | %-8s | %-7s | %-7s | %-8s | %-11s | %-9s\n", "", "", "", "", "",
                "Bloei", "Bloei", "", "voorraad", "voorraad");
        Utilities.toonRegel("------------+-------------------+------------+---------+----------+---------+---------+----------+-------------+----------");


        for (Map.Entry<Integer, Plant> entry : this.entrySet()){
            Plant plant = entry.getValue();
            System.out.printf("Plant %d \t| %-17s | %-10s | %-7s | %8d | %7d | %7d | €%7.2f | %11d | %8d\n",
                    plant.getArtCode(), plant.getPlantNaam(), plant.getSoort(), plant.getKleur(), plant.getHoogte(),
                    plant.getBloeiBeg(), plant.getBloeiEind(), plant.getPrijs(), plant.getVrrAantal(), plant.getVrrMin());
        }
    }

    public String toString() {
        return this.toString();
    }


}


