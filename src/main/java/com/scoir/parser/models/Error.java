package com.scoir.parser.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"row", "message"}) //https://gist.github.com/asafary/9675488
public class Error {

    public Integer row;
    public String message;
}

