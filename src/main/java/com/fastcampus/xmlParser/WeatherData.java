package com.fastcampus.xmlParser;

public class WeatherData {
    String numEf, tmEf, wf, tmn, tmx;

    public WeatherData(String numEf, String tmEf, String wf, String tmn, String tmx) {
        this.numEf = numEf;
        this.tmEf = tmEf;
        this.wf = wf;
        this.tmn = tmn;
        this.tmx = tmx;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "numEf='" + numEf + '\'' +
                ", tmEf='" + tmEf + '\'' +
                ", wf='" + wf + '\'' +
                ", tmn='" + tmn + '\'' +
                ", tmx='" + tmx + '\'' +
                '}';
    }
}

