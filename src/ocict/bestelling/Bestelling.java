package ocict.bestelling;

import ocict.leverancier.Leverancier;
import ocict.list.BestelRegelList;
import ocict.db.DbConnection;
import ocict.config.Utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Map;

/**
 * Created by cstegehuis on 25/06/17.
 */
public class Bestelling {

    private int bestelNr;
    private int levCode;
    private Date bestelDatum;
    private Date levDatum;
    private double bedrag;
    private String status;

    private DbConnection connection;

	private Leverancier leverancier;
	private BestelRegelList bestelRegelList;
	private boolean changed;

    public Bestelling(ResultSet pResultset, DbConnection pConnection){

        this.changed = false;
		this.getValues(pResultset);
        this.connection = pConnection;

        this.leverancier = new Leverancier(pConnection, this.levCode);
        this.bestelRegelList = new BestelRegelList(pConnection, this.bestelNr);
    }

    protected void getValues(ResultSet pResultset){

        try {
            this.bestelNr = pResultset.getInt("bestel_nr");
            this.levCode = pResultset.getInt("lev_code");
            this.bestelDatum = pResultset.getDate("bestel_datum");
            this.levDatum = pResultset.getDate("lever_datum");
            this.bedrag = pResultset.getDouble("bedrag");
            this.status = pResultset.getString("status");

        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }		
	}


	protected boolean getChanged() {
        return this.changed;
    }

/* BestelNr
   Get - haalt de waarde op uit het record van de resultset */
    public int getBestelNr(){
        return bestelNr;
    }


/* LevCode
    Get - haalt de waarde op uit het record van de resultset */
    public int getLevCode(){
        return levCode;
    }


/* BestelDatum
    Get - haalt de waarde op uit het record van de resultset */
    public Date getBestelDatum(){
        return bestelDatum;
    }


/* LeverDatum
    Get - haalt de waarde op uit het record van de resultset
    Set - geeft de query terug om de waarde in de database te wijzigen */
    public void setLeverDatum (Date pNewLeverDatum) {
        String query = "UPDATE bestellingen SET lever_datum = '" + pNewLeverDatum + "' WHERE bestel_nr = " + this.getBestelNr();

        try {
            connection.updateDb(query);
        } catch (SQLException e){
            e.printStackTrace();
        }

        this.changed = true;
    }

    public Date getLeverDatum(){
        return levDatum;
    }


/* Bedrag
    Get - haalt de waarde op uit het record van de resultset */
    public void setBedrag (double pNewBedrag) {
        String query = "UPDATE bestellingen SET bedrag = '" + pNewBedrag + "' WHERE bestel_nr = " + this.getBestelNr();

        try {
            connection.updateDb(query);
        } catch (SQLException e){
            e.printStackTrace();
        }

        this.changed = true;
    }

    public double getBedrag(){
        return bedrag;
    }


/* Status
    Get - haalt de waarde op uit het record van de resultset
    Set - geeft de query terug om de waarde in de database te wijzigen*/
    public void setStatus (String pNewStatus) {
        String query = "UPDATE bestellingen SET status = '" + pNewStatus + "' WHERE bestel_nr = "+this.getBestelNr();

        try {
            connection.updateDb(query);
        } catch (SQLException e){
            e.printStackTrace();
        }

        this.changed = true;    }

    public String getStatus(){
        return status;
    }

    public void verwijderBestelling(){
        String query = "DELETE from bestellingen WHERE bestel_nr = "+ this.getBestelNr();
        try {
            connection.updateDb(query);
        } catch (SQLException e){
            e.printStackTrace();
        }

        this.changed = true;
    }

    public String toString(){
        return (this.bestelNr + " " + this.levCode + " " + this.bestelDatum + " " + this.levDatum +
        " " + this.bedrag + " " + this.status);
    }

    public void printShort(){
        System.out.println(getBestelNr());
        System.out.println(getLevCode());
        System.out.println(getBestelDatum());
        System.out.println(getLeverDatum());
        System.out.println(getBedrag());
        System.out.println(getStatus());
    }

/* Toon overzicht */
    public void toonOverzicht(){

        Utilities.toonRegel("\nBestelling:");
		Utilities.toonRegel("\tNummer\t\t: %d\n", this.getBestelNr());
		Utilities.toonRegel("\tBesteldatum\t: %s\n", this.getBestelDatum());
		Utilities.toonRegel("\tLeverdatum\t: %s\n", this.getLeverDatum());
		Utilities.toonRegel("\tBedrag\t\t: \u20ac%.2f\n", this.getBedrag());
		if (this.getStatus() == "N"){
            Utilities.toonRegel("\tStatus\t\t: %s (Niet-Compleet)\n", this.getStatus());
        } else if (this.getStatus() == "C"){
            Utilities.toonRegel("\tStatus\t\t: %s (Compleet)\n", this.getStatus());
        } else if (this.getStatus() == "O"){
            Utilities.toonRegel("\tStatus\t\t: %s (Onbekend)\n", this.getStatus());
        }

		
		Utilities.toonRegel("\nLeverancier:");
		Utilities.toonRegel("\tCode\t\t: %d\n", this.leverancier.getLevCode());
		Utilities.toonRegel("\tNaam\t\t: %s\n", this.leverancier.getLevNaam());
		Utilities.toonRegel("\tAdres\t\t: %s\n", this.leverancier.getAdres());
		Utilities.toonRegel("\tPlaats\t\t: %s\n", this.leverancier.getWoonplaats());
		
		int nummer = 0;
		for(Map.Entry<Integer, BestelRegel> entry : this.bestelRegelList.entrySet()) {
			
			nummer++;
			BestelRegel regel = entry.getValue();
			Utilities.toonRegel("\nArtikel\t\t\t\t\t: %d\n", nummer);
			Utilities.toonRegel("\tCode\t\t\t\t: %d\n", regel.getArtCode());
			Utilities.toonRegel("\tNaam\t\t\t\t: %s\n", regel.getPlantNaam());
			Utilities.toonRegel("\tSoort\t\t\t\t: %s\n", regel.getPlantSoort());
			if (regel.getPlantKleur().length() == 0){
                Utilities.toonRegel("\tKleur\t\t\t\t: De kleur is onbekend");
            } else {
                Utilities.toonRegel("\tKleur\t\t\t\t: %s\n", regel.getPlantKleur());
            }

			Utilities.toonRegel("\tPrijs per plant\t\t: \u20ac%.2f\n", regel.getBestelPrijs());
			Utilities.toonRegel("\tAantal\t\t\t\t: %d\n", regel.getAantal());

			double totaal = 0d;
			for (int p = 1; p <= regel.getAantal(); p++){
			    totaal += regel.getBestelPrijs();
            }
            Utilities.toonRegel("\tTotaalprijs artikel\t: \u20ac%.2f\n\n", totaal);
        }
    }




    public int getAantalArtikelen() {
        int nummer = 0;
        for(Map.Entry<Integer, BestelRegel> entry : this.bestelRegelList.entrySet()) {

            nummer++;
        }
        return nummer;
    }

}
