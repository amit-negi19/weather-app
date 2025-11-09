package com.example.weather.service;

import com.example.weather.model.WeatherResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherResponse getWeatherByCity(String city) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            String url = String.format("%s?q=%s&appid=%s&units=metric", WEATHER_API_URL, city, apiKey);
            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            WeatherResponse weather = new WeatherResponse();
            weather.setCity(root.path("name").asText());
            weather.setCountry(root.path("sys").path("country").asText());
            weather.setTemperature(root.path("main").path("temp").asDouble());
            weather.setFeelsLike(root.path("main").path("feels_like").asDouble());
            weather.setCondition(root.path("weather").get(0).path("description").asText());
            weather.setHumidity(root.path("main").path("humidity").asInt());
            weather.setPressure(root.path("main").path("pressure").asInt());
            weather.setWindSpeed(root.path("wind").path("speed").asDouble());
            weather.setVisibility(root.path("visibility").asDouble() / 1000); // meter → km

            return weather;

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch weather data: " + e.getMessage());
        }
    }
}
