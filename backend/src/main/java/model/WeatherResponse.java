package com.example.weather.model;

public class WeatherResponse {
    private String city;
    private String country;
    private double temperature;
    private double feelsLike;
    private String condition;
    private int humidity;
    private int pressure;
    private double windSpeed;
    private double visibility;

    // Getters and Setters
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }
    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getHumidity() {
        return humidity;
    }
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }
    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getVisibility() {
        return visibility;
    }
    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }
}
