package com.scoir.parser.services;

import com.scoir.parser.Globals;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigServiceTest {

    private ConfigService testService;

    @Before
    public void setup() {
        //instantiate service
        testService = new ConfigService();
    }

    @Test
    public void load_ConfigMapsToGlobal_True() {
        //https://stackoverflow.com/a/28674230,maven knows where the paths are
        testService.load("src/test/resources/testconfig1.properties");
        String expected = "/filePath/input";
        String actual = System.getProperty(Globals.InputPath);
        Assert.assertEquals(expected, actual);
    }


    @After
    public void cleanup() {
        //clear paths before test
        System.clearProperty(Globals.InputPath);
        System.clearProperty(Globals.OutputPath);
        System.clearProperty(Globals.ErrorPath);
    }
}