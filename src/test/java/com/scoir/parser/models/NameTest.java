package com.scoir.parser.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;



//https://dzone.com/articles/jackson-annotations-for-json-part-3-deserialization
//https://stackoverflow.com/a/41671471
public class NameTest {
    //TODO: Test Null for all fields, Test return message for all fields
    private Name testName;
    private static Validator validator;

    @Before
    public void setup() {
        this.testName = new Name();
        testName.setFirst("Katrina");
        testName.setMiddle("Marie");
        testName.setLast("High");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void first_GetterSetter_HappyPath() {
        String expected = "Mila";
        testName.setFirst(expected);
        String actual = testName.getFirst();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void middle_GetterSetter_HappyPath() {
        String expected = "Lynn";
        testName.setMiddle(expected);
        String actual = testName.getMiddle();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void last_GetterSetterLast_HappyPath() {
        String expected = "Roed";
        testName.setLast(expected);
        String actual = testName.getLast();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void validation_HappyPath() {
        Set<ConstraintViolation<Name>> violations = validator.validate(testName);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void first_emptyString_ShouldFail() {
        String empty = "";
        testName.setFirst(empty);
        Set<ConstraintViolation<Name>> violations = validator.validate(testName);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void first_upperBounds_ShouldFail() {
        String overflow = "reallylongstringoverfifteencharacters";
        testName.setFirst(overflow);
        Set<ConstraintViolation<Name>> violations = validator.validate(testName);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void last_emptyString_ShouldFail() {
        String empty = "";
        testName.setLast(empty);
        Set<ConstraintViolation<Name>> violations = validator.validate(testName);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void last_upperBounds_ShouldFail() {
        String overflow = "reallylongstringoverfifteencharacters";
        testName.setLast(overflow);
        Set<ConstraintViolation<Name>> violations = validator.validate(testName);
        Assert.assertEquals(1, violations.size());
    }

    @Test
    public void middle_emptyString_ShouldPass() {
        String empty = "";
        testName.setMiddle(empty);
        Set<ConstraintViolation<Name>> violations = validator.validate(testName);
        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void middle_upperBounds_ShouldFail() {
        String overflow = "reallylongstringoverfifteencharacters";
        testName.setMiddle(overflow);
        Set<ConstraintViolation<Name>> violations = validator.validate(testName);
        Assert.assertEquals(1, violations.size());
    }

}
