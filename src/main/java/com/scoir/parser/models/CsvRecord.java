package com.scoir.parser.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "first", "middle", "last", "phone"}) //https://gist.github.com/asafary/9675488
public class CsvRecord {

    public String id;
    public String first;
    public String middle;
    public String last;
    public String phone;

    /**
     *creates an object from the csv file and converts it to a record. Allowing the nesting of name.
     * @return a Record object with properly nested name fields
     */
    public Record toRecord(){
        Record record = new Record();
        Name name = new Name();

        record.setId(this.id);
        record.setPhone(this.phone);
        name.setFirst(this.first);
        name.setMiddle(this.middle);
        name.setLast(this.last);
        record.setName(name);

        return record;
    }
}



