package ocict.config;

import java.sql.Date;

/**
 * Created by cstegehuis on 09/07/17.
 */
public class Utilities {

    /* Toonregels */
    public static void toonRegel(String zin){
        System.out.println(zin);
    }

    public static void toonRegel(String zin, String var){
        System.out.printf(zin, var);
    }

    public static void toonRegel(String zin, int var){
        System.out.printf(zin, var);
    }

    public static void toonRegel(String zin, double var){
        System.out.printf(zin, var);
    }

    public static void toonRegel(String zin, Date var){
        System.out.printf(zin, var);
    }


}
