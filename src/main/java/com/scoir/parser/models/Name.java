package com.scoir.parser.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

////https://gist.github.com/asafary/9675488
//https://stackoverflow.com/a/38914186
//http://www.baeldung.com/javax-validation
//https://docs.oracle.com/cd/E19798-01/821-1841/gkaoj/index.html
//http://www.novixys.com/blog/jackson-annotations-guide/

//Annotation tells what things to include in jason, include everything that is not empty
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Name {

    @NotNull
    @Size(min = 1, max = 15, message = "invalid first name")
    private String first;

    @Size(min = 0, max = 15, message = "Middle must be 15 characters")
    private String middle;

    @NotNull
    @Size(min = 1, max = 15, message = "Last Name must be between 1 and 15 characters")
    private String last;


    @JsonGetter("first")
    public String getFirst() {
        return first;
    }

    @JsonSetter("first")
    public void setFirst(String first) {
        this.first = first;
    }

    @JsonGetter("middle")
    public String getMiddle() {
        return middle;
    }

    @JsonSetter("middle")
    public void setMiddle(String middle) {
        this.middle = middle;
    }

    @JsonGetter("last")
    public String getLast() {
        return last;
    }

    @JsonSetter("last")
    public void setLast(String last) {
        this.last = last;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("First:\t").append(this.first).append("\n");
        sb.append("Middle:\t").append(this.middle).append("\n");
        sb.append("Last:\t").append(this.last).append("\n");

        return sb.toString();
    }
}
