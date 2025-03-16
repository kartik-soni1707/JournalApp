package com.kartik.jorunalApp.service;

import com.kartik.jorunalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    private static final String api_key="5dfe04f98ea359f0439df6fbcee78a9f";
    private static final String api="https://api.weatherstack.com/current?access_key=KEY&query=CITY";
    @Autowired
    private RestTemplate rt;
    public WeatherResponse getWeather(String City){
        String end=api.replace("KEY",api_key).replace("CITY",City);
        ResponseEntity<WeatherResponse> response=rt.exchange(end, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body=response.getBody();
        return body;
    }


}
