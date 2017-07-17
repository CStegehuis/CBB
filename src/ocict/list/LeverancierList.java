package ocict.list;

import ocict.config.Utilities;
import ocict.db.DbConnection;
import ocict.leverancier.Leverancier;
import ocict.product.Plant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by cstegehuis on 04/07/17.
 */
public class LeverancierList extends TreeMap<Integer, Leverancier> {

    private DbConnection connection;

    // constructor
    public LeverancierList(DbConnection pConnection){
        this.connection = pConnection;
        this.fillLeverancierList();
    }

    // method
    protected void fillLeverancierList(){

        ResultSet resultset;
        try {

            resultset = connection.getResultSet("SELECT * FROM leveranciers");

            if (!resultset.isBeforeFirst()) {
                System.out.println("Er zit geen data in de resultset");
            } else {

                while(resultset.next()) {
                    Leverancier leverancier = new Leverancier(resultset);
                    this.put(leverancier.getLevCode(), leverancier);
                }
            }
        }
        catch (SQLException e) {
            System.out.println("leverancierlist 1");

            System.out.println("SQLException: " + e.getMessage());
        }

        return;
    }

    public void toonLeveranciers(){
        Utilities.toonRegel("\nDe leveranciers in de database: ");
        System.out.printf("%-17s | %-18s | %-25s | %-11s\n", "Leverancier code", "Leverancier naam", "Adres", "Woonplaats");
        Utilities.toonRegel("------------------+--------------------+---------------------------+------------");


        for (Map.Entry<Integer, Leverancier> entry : this.entrySet()){
            Leverancier leverancier = entry.getValue();
            System.out.printf("Leverancier %-5s | %-18s | %-25s | %-11s\n", leverancier.getLevCode(), leverancier.getLevNaam(),
                    leverancier.getAdres(), leverancier.getWoonplaats());
        }
    }

    public String toString() {
        return this.toString();
    }


}
