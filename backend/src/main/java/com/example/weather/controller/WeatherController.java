package com.example.weather.controller;

import com.example.weather.model.WeatherResponse;
import com.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<WeatherResponse> getWeather(@RequestParam String city) {
        try {
            // Step 1: Console me check karne ke liye print
            System.out.println("🔍 City parameter received: " + city);

            // Step 2: Service call
            WeatherResponse weather = weatherService.getWeatherByCity(city);

            // Step 3: Null check
            if (weather == null) {
                System.out.println("⚠️ WeatherService returned null for city: " + city);
                return ResponseEntity.badRequest().body(null);
            }

            // Step 4: Success response
            System.out.println("✅ Weather data fetched successfully!");
            return ResponseEntity.ok(weather);

        } catch (Exception e) {
            // Step 5: Error print karne ke liye
            System.out.println("❌ Exception occurred while fetching weather for: " + city);
            e.printStackTrace(); // ye console me full error dikhayega

            return ResponseEntity.badRequest().build();
        }
    }

}
