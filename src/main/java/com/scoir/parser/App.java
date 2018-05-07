package com.scoir.parser;

import com.scoir.parser.services.ConfigService;
import com.scoir.parser.services.Csv2JsonService;
import com.scoir.parser.services.FileSystemService;

public class App {

    public static void main(String[] args) {
        //instantiate services
        ConfigService cs = new ConfigService();
        FileSystemService fss = new FileSystemService();
        Csv2JsonService csv = new Csv2JsonService();

        //set service contracts
        fss.setCsvProcessor(csv); //dependency injection

        //set up and run
        //load config
        cs.load("config.properties");

        //watch folders for changes
        fss.ensureDirectoriesExist();

        fss.watchForChanges();
    }
}
