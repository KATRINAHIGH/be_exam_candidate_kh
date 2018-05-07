package com.scoir.parser.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecordTest {

    //TODO: getErrors, getName, setName, toString, Test return message for all fields
    private Record testRecord;
    private Name testName;

    @Before
    public void setup(){
        this.testRecord = new Record();
        this.testName = new Name();
        this.testName.setFirst("Katrina");
        this.testName.setMiddle("Marie");
        this.testName.setLast("High");
        this.testRecord.setId("12345678");
        this.testRecord.setPhone("717-341-6307");
        this.testRecord.setName(testName);
    }

    @Test
    public void id_GetterSetter_HappyPath() {
        String expected = "87654321";
        testRecord.setId(expected);
        String actual = testRecord.getId();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void phone_GetterSetter_HappyPath() {
        String expected = "717-341-9087";
        testRecord.setPhone(expected);
        String actual = testRecord.getPhone();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void record_isValid_HappyPath() {
        boolean actual = testRecord.isValid();
        assertTrue(actual);
    }

    @Test
    public void id_emptyString_ShouldFail() {
        String empty = "";
        testRecord.setId(empty);
        boolean actual = testRecord.isValid();
        assertFalse(actual);
    }

    @Test
    public void id_lessThan8Digits_ShouldFail() {
        String lessThan = "1234567";
        testRecord.setId(lessThan);
        boolean actual = testRecord.isValid();
        assertFalse(actual);
    }

    @Test
    public void id_moreThan8Digits_ShouldFail() {
        String moreThan = "123456789";
        testRecord.setId(moreThan);
        boolean actual = testRecord.isValid();
        assertFalse(actual);
    }

    @Test
    public void id_containsLetter_ShouldFail() {
        String containsLetter = "1234567p";
        testRecord.setId(containsLetter);
        boolean actual = testRecord.isValid();
        assertFalse(actual);
    }

    @Test
    public void phone_emptyString_ShouldFail() {
        String empty = "";
        testRecord.setPhone(empty);
        boolean actual = testRecord.isValid();
        assertFalse(actual);
    }

    @Test
    public void phone_lessThan12Characters_ShouldFail() {
        String lessThan = "17-341-6307";
        testRecord.setPhone(lessThan);
        boolean actual = testRecord.isValid();
        assertFalse(actual);

    }

    @Test
    public void phone_moreThan12Characters_ShouldFail() {
        String moreThan = "7717-341-6307";
        testRecord.setPhone(moreThan);
        boolean actual = testRecord.isValid();
        assertFalse(actual);
    }

    @Test
    public void phone_containsLetter_ShouldFail() {
        String containsLetter = "717-341-630p";
        testRecord.setId(containsLetter);
        boolean actual = testRecord.isValid();
        assertFalse(actual);
    }

    @Test
    public void phone_containsNoDashes_ShouldFail() {
        String containsNoDashes = "7173416306";
        testRecord.setId(containsNoDashes);
        boolean actual = testRecord.isValid();
        assertFalse(actual);
    }

    @Test
    public void phone_dashesReplacedWithSpecialChar_ShouldFail() {
        String dashesReplaced = "717/341/6306";
        testRecord.setId(dashesReplaced);
        boolean actual = testRecord.isValid();
        assertFalse(actual);
    }

    @Test
    public void getErrors() {
    }

    @Test
    public void getName() {
    }

    @Test
    public void setName() {
    }


}