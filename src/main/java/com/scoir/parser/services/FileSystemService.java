package com.scoir.parser.services;

import com.scoir.parser.Globals;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileSystemService implements IFileSystemService{
    //TODO: breakup ensureDirectoriesExist for better testing, research suppresswarnings,
    /**
     *
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void ensureDirectoriesExist() {
        //check if directory exists
        //https://stackoverflow.com/a/15571626

        //create input path if it doesn't exist
        String inputPathString = System.getProperty(Globals.InputPath);
        Path inputPath = Paths.get(inputPathString);
        if(Files.notExists(inputPath)){
            //make folder and all necessary parent folders
            //https://stackoverflow.com/a/3634906
            new File(inputPathString).mkdirs();
        }

        //create output path if it doesn't exist
        String outputPathString = System.getProperty(Globals.OutputPath);
        Path outputPath = Paths.get(outputPathString);
        if (Files.notExists(outputPath)){
            //make folder and all necessary folders
            //https://stackoverflow.com/3634906
            new File(outputPathString).mkdirs();
        }

        //create error path if it doesn't exist
        String errorPathString = System.getProperty(Globals.ErrorPath);
        Path errorPath = Paths.get(errorPathString);
        if (Files.notExists(errorPath)){
            //make folder and all necessary folders
            //https://stackoverflow.com/3634906
            new File(errorPathString).mkdirs();
        }

    }

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
                    if(!isCsv(filePath.toString())) {
                        continue;
                    }

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

    /**
     *takes in filePath as a string and checks that the last three characters after the . are equal to csv
     * @param filePath the filename to retrieve the extension of
     * @return true if extension equals csv and false otherwise
     */
    public boolean isCsv(String filePath){
        //https://stackoverflow.com/a/3571239
        int i = filePath.lastIndexOf('.');
        if(i > 1){
            return filePath.substring(i+1).equals("csv");
        }
        return false;
    }
}
