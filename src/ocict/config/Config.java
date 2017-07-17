package ocict.config;

/**
 * Created by cstegehuis on 22/06/17.
 */

import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;

public class Config {

    private File file;

    public Config(String filename){
        this.file = new File(filename);
    }

    public String[] getDatabaseInfo() throws IOException{

        Ini ini = new Ini(this.file);
        Ini.Section section = ini.get("database");

        String[] database_info = new String[4];
        database_info[0] = section.get("host");
        database_info[1] = section.get("database_naam");
        database_info[2] = section.get("username");
        database_info[3] = section.get("wachtwoord");

        return database_info;
    }
}
