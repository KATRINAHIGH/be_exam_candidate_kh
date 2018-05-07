package com.scoir.parser;

public class Globals {
    //As a User, I can configure input, output, and error directories
    //https://12factor.net/config
    //created constants they never change, can call upon without instantiating
    public static final String InputPath = "inputpath";
    public static final String OutputPath = "outputpath";
    public static final String ErrorPath = "errorpath";
}

