package com.example.restservice;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.ResourceUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class AirportController {

    private List<Airport> airports;

    public AirportController() {
        // Загрузка данных из файла JSON при инициализации контроллера
        try {
            File file = ResourceUtils.getFile("airports.json");
            ObjectMapper objectMapper = new ObjectMapper();
            airports = objectMapper.readValue(file, new TypeReference<List<Airport>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/airport/{iataCode}")
    public Airport getAirportByIataCode(@PathVariable String iataCode) {
        // Поиск аэропорта по коду IATA
        return airports.stream()
                .filter(airport -> airport.getIataCode().equalsIgnoreCase(iataCode))
                .findFirst()
                .orElse(null); // Вернуть null, если аэропорт не найден
    }

    @GetMapping("/airports")
    public List<Airport> getAllAirports() {
        // Возвращает список всех аэропортов
        return airports;
    }
}
