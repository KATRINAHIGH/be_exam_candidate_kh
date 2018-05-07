package com.scoir.parser.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileSystemServiceTest {


    @Before
    public void setUp() {
    }

    //https://stackoverflow.com/questions/29719932/unit-test-code-with-watchservice
    //TODO: Will require mocking of the csv service
    @Test
    public void watchForChanges() {
    }

    //TODO: break down method for better testability
    @Test
    public void ensureDirectoriesExist() {
    }

    @Test
    public void isCsv_HappyPath_shouldReturnTrue() {
        FileSystemService testFSS = new FileSystemService();
        boolean actual = testFSS.isCsv("/testPath.csv");
        Assert.assertTrue(actual);
    }

    @Test
    public void isCsv_FileTypeInPath_shouldReturnTrue() {
        FileSystemService testFSS = new FileSystemService();
        boolean actual = testFSS.isCsv("/testcsvPath.csv");
        Assert.assertTrue(actual);
    }

    @Test
    public void isCsv_FileTypeInPathNoExtension_shouldReturnFalse() {
        FileSystemService testFSS = new FileSystemService();
        boolean actual = testFSS.isCsv("/testcsvPath");
        Assert.assertFalse(actual);
    }

    @Test
    public void isCsv_XMLFile_shouldReturnFalse() {
        FileSystemService testFSS = new FileSystemService();
        boolean actual = testFSS.isCsv("/testPath.xml");
        Assert.assertFalse(actual);
    }


}