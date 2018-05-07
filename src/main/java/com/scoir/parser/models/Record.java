package com.scoir.parser.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Set;

@JsonPropertyOrder({"id", "name", "phone"})//https://gist.github.com/asafrary/96575488
public class Record {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private Set<ConstraintViolation<Record>> violations;

    //starts with digit 1 to 9 followed by 7 other digits, 10000000 <= 99999999
    @Pattern(regexp = "^[1-9]\\d{7}$", message = "invalid id")
    private String id;

    @NotNull
    @Valid
    private Name name;

    @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$")
    private String phone;

    @JsonGetter("id")
    public String getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonGetter("name")
    public Name getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(Name name) {
        this.name = name;
    }

    @JsonGetter("phone")
    public String getPhone() {
        return phone;
    }

    @JsonSetter("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return true if field meet validation requirements, false otherwise
     */
    @JsonIgnore
    public boolean isValid() {
        this.violations = validator.validate(this);
        return this.violations.size() == 0;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public ArrayList<String> getErrors() {
        ArrayList<String> out = new ArrayList<>();
        if (this.violations == null || this.violations.size() == 0) {
            return out;
        }
        for (ConstraintViolation<Record> violation : this.violations) {
            out.add(violation.getMessage());
        }
        return out;
    }

    /**
     *
     * @return
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID:\t").append(this.id).append("\n");
        sb.append("Name:\t").append(this.name.toString()).append("\n");
        sb.append("Phone:\t").append(this.phone).append("\n");

        return sb.toString();
    }
}
