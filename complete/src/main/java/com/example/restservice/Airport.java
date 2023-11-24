package com.example.restservice;

public record Airport(String name, String iataCode, String country) {
    public String getIataCode() {
        return iataCode;
    }
}
