package com.kartik.jorunalApp.service;

import com.kartik.jorunalApp.Cache.AppCache;
import com.kartik.jorunalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Value("${weather_api}")
    private  String api_key;
    @Autowired
    private RestTemplate rt;
    @Autowired
    private AppCache app;
    public WeatherResponse getWeather(String City){
        String end=app.appCache.get("weather_api").replace("<KEY>",api_key).replace("<CITY>",City);
        ResponseEntity<WeatherResponse> response=rt.exchange(end, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body=response.getBody();
        return body;
    }


}
