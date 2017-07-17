package ocict.list;

import ocict.bestelling.BestelRegel;
import ocict.bestelling.Bestelling;
import ocict.db.DbConnection;
import ocict.config.Utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by cstegehuis on 25/06/17.
 */


// was CreateBestelingArray()
public class BestelList extends TreeMap<Integer, Bestelling> {

    private DbConnection connection;

// constructor
    public BestelList(DbConnection pConnection){
        this.connection = pConnection;
        this.fillBestellingList();
    }

// method
    protected void fillBestellingList(){

		ResultSet resultset;
        try {

            resultset = connection.getResultSet("SELECT * FROM bestellingen");

            if (!resultset.isBeforeFirst()) {
                System.out.println("Er zit geen data in de resultset");
            } else {

                while(resultset.next()) {
                    Bestelling bestelling = new Bestelling(resultset, connection);
                    // let op deze !!!!!!!! ..........................
                    this.put(bestelling.getBestelNr(), bestelling);
                }
            }
        } 
        catch (SQLException e) {
            System.out.println("bestellist 1");

            System.out.println("SQLException: " + e.getMessage());
        }

        return;
    }


    public void toonOverzicht() {
        Utilities.toonRegel("\nDe bestellingen in de database: ");
        System.out.printf("%10s  | %12s | %10s | %-13s | %s\n", "Bestel ID", "Bestel datum", "Bedrag (\u20ac)",
                "Status", "Aantal artikelen");
        Utilities.toonRegel("------------+--------------+------------+---------------+------------------");

        for (Map.Entry<Integer, Bestelling> entry : this.entrySet()) {
            Bestelling bestel = entry.getValue();
            if (bestel.getStatus().toUpperCase().equals("O")){
                System.out.printf("%6d \t\t| %12s | %10.2f | %-13s | %8d\n",
                        bestel.getBestelNr(), bestel.getBestelDatum(), bestel.getBedrag(),
                        "Onbekend", bestel.getAantalArtikelen());
            } else if (bestel.getStatus().toUpperCase().equals("C")){
                System.out.printf("%6d \t\t| %12s | %10.2f | %-13s | %8d\n",
                        bestel.getBestelNr(), bestel.getBestelDatum(), bestel.getBedrag(),
                        "Compleet", bestel.getAantalArtikelen());
            } else if (bestel.getStatus().toUpperCase().equals("N")){
                System.out.printf("%6d \t\t| %12s | %10.2f | %-13s | %8d\n",
                        bestel.getBestelNr(), bestel.getBestelDatum(), bestel.getBedrag(),
                        "Niet-Compleet", bestel.getAantalArtikelen());
            }
        }
    }


    public String toString() {
        return this.toString();
    }

}
