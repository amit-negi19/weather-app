import React, { useState } from "react";
import "./weatherApp.css";

export default function WeatherApp() {
  const [city, setCity] = useState("");
  const [weather, setWeather] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const fetchWeather = async () => {
    if (!city.trim()) {
      setError("Please enter a city name");
      return;
    }

    setLoading(true);
    setError("");

    try {
      const response = await fetch(
        `http://localhost:8081/api/weather?city=${encodeURIComponent(city)}`
      );

      if (!response.ok) throw new Error("City not found or API error");

      const data = await response.json();
      setWeather(data);
    } catch (err) {
      setError(err.message || "Failed to fetch weather data");
      setWeather(null);
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetchWeather();
  };

  return (
    <div className="weather-wrapper">
      <div className="weather-card">
        <h1 className="app-title">
          <span className="icon">🌤️</span> Weather Forecast
        </h1>

        <form onSubmit={handleSubmit} className="search-box">
          <input
            type="text"
            value={city}
            onChange={(e) => setCity(e.target.value)}
            placeholder="Enter your city..."
          />
          <button type="submit" disabled={loading}>
            {loading ? "Loading..." : "Search"}
          </button>
        </form>

        {error && <p className="error-text">{error}</p>}

        {weather && (
          <div className="weather-info">
            <h2>
              {weather.city}, {weather.country}
            </h2>
            <h1>{Math.round(weather.temperature)}°C</h1>
            <p className="condition">{weather.condition}</p>

            <div className="extra-info">
              <div>💨 Wind: {weather.windSpeed} km/h</div>
              <div>💧 Humidity: {weather.humidity}%</div>
              <div>📏 Pressure: {weather.pressure} hPa</div>
              <div>👁️ Visibility: {weather.visibility} km</div>
            </div>

            <p className="feels">Feels like {Math.round(weather.feelsLike)}°C</p>
          </div>
        )}

        {!weather && !error && !loading && (
          <p className="placeholder">Search a city to see the weather ☁️</p>
        )}
      </div>
    </div>
  );
}
