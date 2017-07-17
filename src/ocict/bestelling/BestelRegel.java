package ocict.bestelling;

import ocict.db.DbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cstegehuis on 14/06/17.
 */
public class BestelRegel {

    private int bestelNr;
    private int artCode;
    private double bestelPrijs;
    private int aantal;
    private String plantNaam;
    private String plantSoort;
    private String plantKleur;

    private DbConnection connection;

// constructor
    public BestelRegel(ResultSet pResultset, DbConnection pConnection){

        this.getValues(pResultset);
        this.connection = pConnection;

    }

    protected void getValues(ResultSet pResultset){
        try {
            this.bestelNr = pResultset.getInt("bestel_nr");
            this.artCode = pResultset.getInt("art_code");
            this.bestelPrijs = pResultset.getDouble("bestel_prijs");
            this.aantal = pResultset.getInt("aantal");
            this.plantNaam = pResultset.getString("plant_naam");
            this.plantSoort = pResultset.getString("soort");
            this.plantKleur = pResultset.getString("kleur");

        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }


    /* BestelNr
    Get - haalt de waarde op uit het record van de resultset
     */
    public int getBestelNr(){
        return this.bestelNr;
    }

    /* ArtCode
    Get - haalt de waarde op uit het record van de resultset
     */
    public int getArtCode(){
        return this.artCode;
    }

    /* BestelPrijs
    Get - haalt de waarde op uit het record van de resultset
     */
    public void setBestelPrijs(double pNewPrijs) {
        String query = "UPDATE bestellingen SET status = '" + pNewPrijs + "' WHERE bestel_nr = "+this.getBestelNr();

        try {
            connection.updateDb(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public double getBestelPrijs(){
        return this.bestelPrijs;
    }

    /* Aantal
    Get - haalt de waarde op uit het record van de resultset
     */
    public void setAantal(int pNewAantal) {
        String query = "UPDATE bestellingen SET status = '" + pNewAantal + "' WHERE bestel_nr = "+this.getBestelNr();

        try {
            connection.updateDb(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public int getAantal(){
        return this.aantal;
    }

    /* plantNaam
    Get - haalt de waarde op uit het record van de resultset
     */
    public String getPlantNaam(){
        return this.plantNaam;
    }

    /* plantSoort
    Get - haalt de waarde op uit het record van de resultset
     */
    public String getPlantSoort(){
        return this.plantSoort;
    }

    /* plantKleur
    Get - haalt de waarde op uit het record van de resultset
     */
    public String getPlantKleur(){
        return this.plantKleur;
    }

    /*ToString method */
    public String toString(){
        return (this.bestelNr + " " + this.artCode + " " + this.bestelPrijs + " " + this.aantal
				 + " " + this.plantNaam+ " " + this.plantSoort+ " " + this.plantKleur);
    }

    /*Methode om kort de waarden uit het object te kunnen printen voor coding gebruik */
    public void printShort(){
        System.out.println(this.bestelNr);
        System.out.println(this.artCode);
        System.out.println(this.bestelPrijs);
        System.out.println(this.aantal);
        System.out.println(this.plantNaam);
        System.out.println(this.plantSoort);
        System.out.println(this.plantKleur);
    }
}
