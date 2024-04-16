package com.example.javamvcmeteo.models;

public class RootModel {
    public String code;
    public String name;
    public String administrativeDivision;
    public String countryCode;

    public RootModel(String name) {
        this.name = name;
    }

    public RootModel() {
    }

    public RootModel(String code, String name, String administrativeDivision, String countryCode) {
        this.code = code;
        this.name = name;
        this.administrativeDivision = administrativeDivision;
        this.countryCode = countryCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdministrativeDivision() {
        return administrativeDivision;
    }

    public void setAdministrativeDivision(String administrativeDivision) {
        this.administrativeDivision = administrativeDivision;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
