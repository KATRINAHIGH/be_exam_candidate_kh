package com.scoir.parser.services;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.scoir.parser.Globals;
import com.scoir.parser.models.CsvRecord;
import com.scoir.parser.models.Record;
import com.scoir.parser.models.Error;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Csv2JsonService implements ICsv2JsonService{

    //Maps csv to json using jackson.
// https://github.com/FasterXML/jackson-dataformats-text/tree/master/csv

    /**
     *
     * @param csv
     */
    public void processCsvFile(File csv) {

        CsvMapper csvMapper = new CsvMapper();
        try {
            MappingIterator<CsvRecord> iter = csvMapper.readerWithTypedSchemaFor(CsvRecord.class).readValues(csv);
            ArrayList<Record> validRecords = new ArrayList<>();
            ArrayList<Error> invalidRecords = new ArrayList<>();
            boolean isHeaderRow = true;
            int row = -1;

            //https://github.com/FasterXML/jackson-dataformats-text/tree/master/csv
            while(iter.hasNext()){
                row++;
                CsvRecord csvrecord = iter.next();

                //skip header row
                if(isHeaderRow){
                    isHeaderRow = false;
                    continue;
                }

                Record record = csvrecord.toRecord();

                if(record.isValid()){
                    validRecords.add(record);
                }else{
                    Error e = new Error();
                    e.row = row;
                    e.message = String.join("|", record.getErrors());
                    invalidRecords.add(e);
                }
            }

            //handle invalid records
            if(invalidRecords.size() > 0){
                ObjectWriter csvWriter = csvMapper.writerWithTypedSchemaFor(Error.class);
                File errorCsv = Paths.get(System.getProperty(Globals.ErrorPath), csv.getName()).toFile();

                for(Error e : invalidRecords){
                    csvWriter.writeValue(errorCsv, e);
                }
            }

            //handle invalid records
            if(validRecords.size() > 0){
                ObjectMapper jsonMapper = new ObjectMapper();
                jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);

                File jsonFile = getJsonPath(csv).toFile();
                jsonMapper.writeValue(jsonFile, validRecords);
            }

            // delete csv file
            csv.delete();
            StringBuilder sb = new StringBuilder();
            sb.append("Processed file ").append(csv.getName()).append("\n");
            sb.append("\t").append(validRecords.size()).append(" valid rows\n");
            sb.append("\t").append(invalidRecords.size()).append(" invalid rows\n\n");
            System.out.print(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path getJsonPath(File csv){
        String fileName = csv.getName();

        //already know it's a csv file with the extension 'csv'
        fileName = fileName.substring(0, fileName.length()-4)+ ".json";

        String outputPath = System.getProperty(Globals.OutputPath);
        return Paths.get(outputPath, fileName);
    }
}
