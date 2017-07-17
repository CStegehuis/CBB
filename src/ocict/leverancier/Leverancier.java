package ocict.leverancier;

import ocict.db.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cstegehuis on 14/06/17.
 */
public class Leverancier {

    private int levCode;
    private String levNaam;
    private String adres;
    private String woonplaats;

// constructor (let op: constructor overload) !
    public Leverancier(DbConnection pConnection, int pLevCode){

        try {

            String query = "SELECT lev_code, lev_naam, lev_adres, lev_woonplaats"
				+ " FROM leveranciers"
				+ " WHERE lev_code = " + pLevCode;

			ResultSet resultset = pConnection.getResultSet(query);

            if (!resultset.isBeforeFirst()) {
                System.out.println("Er zit geen data in de resultset");
            } else {
                resultset.next();
				this.getValues(resultset);
			}
			
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return;
    }


// constructor (let op: constructor overload) !
// (is de constructor voor het maken van een LeverancierList, e.d.)
    public Leverancier(ResultSet pResultset){

        this.getValues(pResultset);
    }

// method
    protected void getValues(ResultSet pResultset){
		
        try {
            this.levCode = pResultset.getInt("lev_code");
            this.levNaam = pResultset.getString("lev_naam");
            this.adres = pResultset.getString("lev_adres");
            this.woonplaats = pResultset.getString("lev_woonplaats");

        } catch (SQLException e){
            System.out.println("leverancier 1");

            System.out.println("SQLException: " + e.getMessage());
        }
	}


    /* LevCode
    Get - haalt de waarde op uit het record van de resultset
     */
    public int getLevCode(){
        return levCode;
    }


    /* LevNaam
    Get - haalt de waarde op uit het record van de resultset
    Set - geeft de query terug om de waarde in de database te wijzigen
     */
    public String setLevNaam (String pNewLevNaam) {
        String query = "UPDATE Leveranciers " +  "SET lev_naam =" + pNewLevNaam + " WHERE lev_code="+this.levCode;
        return query;
    }

    public String getLevNaam(){
        return levNaam;
    }


    /* LevAdres
    Get - haalt de waarde op uit het record van de resultset
    Set - geeft de query terug om de waarde in de database te wijzigen
     */
    public String setAdres (String pNewAdres) {
        String query = "UPDATE Leveranciers " +  "SET lev_adres =" + pNewAdres + " WHERE lev_code="+this.levCode;
        return query;
    }

    public String getAdres(){
        return adres;
    }


    /* LevWoonplaats
    Get - haalt de waarde op uit het record van de resultset
    Set - geeft de query terug om de waarde in de database te wijzigen
     */
    public String setWoonplaats (String pNewWoonplaats) {
        String query = "UPDATE Leveranciers " +  "SET lev_woonplaats =" + pNewWoonplaats + " WHERE lev_code="+this.levCode;
        return query;
    }

    public String getWoonplaats(){
        return woonplaats;
    }


    /*ToString method */
    public String toString(){
        return (this.levCode + " " + this.levNaam + " " + this.adres + " " + this.woonplaats);
    }


    /*Methode om kort de waarden uit het object te kunnen printen voor coding gebruik */
    public void printShort(){
        System.out.println(getLevCode());
        System.out.println(getLevNaam());
        System.out.println(getAdres());
        System.out.println(getWoonplaats());
    }

}
