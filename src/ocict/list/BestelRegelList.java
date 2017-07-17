package ocict.list;

import ocict.bestelling.BestelRegel;
import ocict.bestelling.Bestelling;
import ocict.db.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by cstegehuis on 25/06/17.
 */


// was CreateBestelRegelArray()
public class BestelRegelList extends TreeMap<Integer, BestelRegel> {

    private DbConnection connection;
    private int bestelNr;

// constructor
    public BestelRegelList(DbConnection pConnection, int pBestelNr){
        this.connection = pConnection;
        this.bestelNr = pBestelNr;
        this.fillBestelRegelList();
    }

// method
    protected void fillBestelRegelList(){

		ResultSet resultset;
        try {

// let op !! deze joint planten
            String query = "SELECT bestelregels.bestel_nr, bestelregels.art_code, bestelregels.aantal, bestelregels.bestel_prijs, planten.plant_naam, planten.soort, planten.kleur"
				+ " FROM bestelregels JOIN planten ON bestelregels.art_code = planten.art_code"
				+ " WHERE bestel_nr = \"" + this.bestelNr + "\"";

            resultset = connection.getResultSet(query);

            if (!resultset.isBeforeFirst()) {
                System.out.println("Er zit geen data in de resultset");
            } else {

                while(resultset.next()) {
                    BestelRegel bestelRegel = new BestelRegel(resultset, connection);
                    // let op deze !!!!!!!! ..........................
                    this.put(bestelRegel.getArtCode(), bestelRegel);
                }

            }
        } 
        catch (SQLException e) {
            System.out.println("bestelregellist 1");
            System.out.println("SQLException: " + e.getMessage());
        }

        return;
    }


    public String toString() {
        return this.toString();
    }

}
