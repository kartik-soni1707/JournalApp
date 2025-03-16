package com.kartik.jorunalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class WeatherResponse{


    private Current current;

    @Getter
    @Setter
    public class Current{

        public int temperature;
        @JsonProperty("weather_description")
        public ArrayList<String> weather_descriptions;
        public int wind_speed;
        public int feelslike;

    }

}

