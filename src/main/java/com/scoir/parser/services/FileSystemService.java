package com.scoir.parser.services;

import java.io.IOException;
import java.nio.file.*;

public class FileSystemService {

    /**
     *continuously monitors input-directory for new .csv files
     *files are considered new if they have not been recorded yet
     * places new files in the input-directory
     */
    public void watchForChanges(){

        try {
            //TODO: create globals for input, output and error paths
            //get the input paths from system properties
            String inputPathString = System.getProperty("inputpath.csv");
            //interface way to locate file in the system, a way to access part of system, creates a path from a string
            // that allows you to access part of file system
            Path inputPath = Paths.get(inputPathString);

            //http://www.baeldung.com/java-nio2-watchservice
            //https://docs.oracle.com/javase/tutorial/essential/io/notification.html
            WatchService watcher = FileSystems.getDefault().newWatchService();
            //will match watcher with type of event to watch, only care about create events
            inputPath.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

            WatchKey key;
            while((key = watcher.take()) != null){
                for(WatchEvent<?> event: key.pollEvents()){
                    //get absolute path to file
                    //https://stackoverflow.com/a/34459531
                    Path filePath = Paths.get(inputPathString, event.context().toString());

                    //check if it's a csv file if not then skip this file don't throw errors
                    //TODO: write isCsv method and check filepath here

                    //TODO: write this piece I think it needs a db to function properly
                    //if file is a csv then check to see if already processed? If yes, continue skip to next loop
                    //if no ignore or go to the next step

                    //process csv file, path class has a toFile method
                    //TODO: write class to process csv file

                }
                //compares current snapshot to previous snapshot of a folder if differences responds, don't fully understand research more
                key.reset();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
