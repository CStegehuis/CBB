package ocict.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by cstegehuis on 14/06/17.
 */
public class Plant {

    private int artcode;
    private String plantNaam;
    private String soort;
    private String kleur;
    private int hoogte;
    private int bloeiBeg;
    private int bloeiEind;
    private double prijs;
    private int vrrAantal;
    private int vrrMin;

    private ResultSet resultset;

    public Plant(ResultSet pResultset){
        this.resultset = pResultset;

        this.getValues(pResultset);
    }

    protected void getValues(ResultSet pResultset){

        try {
            this.artcode = resultset.getInt("art_code");
            this.plantNaam = resultset.getString("plant_naam");
            this.soort = resultset.getString("soort");
            this.kleur = resultset.getString("kleur");
            this.hoogte = resultset.getInt("hoogte");
            this.bloeiBeg = resultset.getInt("bloei_beg");
            this.bloeiEind = resultset.getInt("bloei_eind");
            this.prijs = resultset.getInt("prijs");
            this.vrrAantal = resultset.getInt("vrr_aantal");
            this.vrrMin = resultset.getInt("vrr_min");

        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public int getArtCode(){
        return artcode;
    }

    public String getPlantNaam(){
        return plantNaam;
    }

    public String getSoort(){
        return soort;
    }

    public String getKleur(){
        return kleur;
    }

    public int getHoogte(){
        return hoogte;
    }

    public int getBloeiBeg(){
        return bloeiBeg;
    }

    public int getBloeiEind(){
        return bloeiEind;
    }

    public double getPrijs(){
        return prijs;
    }

    public int getVrrAantal(){
        return vrrAantal;
    }

    public int getVrrMin(){
        return vrrMin;
    }

    public String toString(){
        return (this.artcode + " " + this.plantNaam + " " + this.soort + " " + this.kleur +
                " " + this.hoogte + " " + this.bloeiBeg + " " + this.bloeiEind+ " " + this.prijs +
                " " + this.vrrAantal + " " + this.vrrMin);
    }

    public void printShort(){
        System.out.println(getArtCode());
        System.out.println(getPlantNaam());
        System.out.println(getSoort());
        System.out.println(getKleur());
        System.out.println(getHoogte());
        System.out.println(getBloeiBeg());
        System.out.println(getBloeiEind());
        System.out.println(getPrijs());
        System.out.println(getVrrAantal());
        System.out.println(getVrrMin());
    }

    protected void toonRegel(String zin){
        System.out.println(zin);
    }
    protected void toonRegel(String zin, String var){
        System.out.printf(zin, var);
    }
    protected void toonRegel(String zin, int var){
        System.out.printf(zin, var);
    }
    protected void toonRegel(String zin, double var){
        System.out.printf(zin, var);
    }
    protected void toonRegel(String zin, java.sql.Date var){
        System.out.printf(zin, var);
    }
}
