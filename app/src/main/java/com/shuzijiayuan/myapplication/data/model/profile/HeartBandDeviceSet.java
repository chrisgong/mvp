package com.shuzijiayuan.myapplication.data.model.profile;

/**
 * Created by gc on 16/8/23.
 */

public class HeartBandDeviceSet {
    private Integer heartHigh;

    private Integer heartLow;

    private Integer raHigh;

    private Integer raLow;

    private Integer spo2Low;

    private Float tempHigh;

    private Float tempLow;


    public HeartBandDeviceSet() {

    }

    @Override
    public String toString() {
        return "HeartBandDeviceSet{" +
                "heartHigh=" + heartHigh +
                ", heartLow=" + heartLow +
                ", raHigh=" + raHigh +
                ", raLow=" + raLow +
                ", spo2Low=" + spo2Low +
                ", tempHigh=" + tempHigh +
                ", tempLow=" + tempLow +
                '}';
    }

    public Integer getHeartHigh() {
        return heartHigh;
    }

    public void setHeartHigh(Integer heartHigh) {
        this.heartHigh = heartHigh;
    }

    public Integer getHeartLow() {
        return heartLow;
    }

    public void setHeartLow(Integer heartLow) {
        this.heartLow = heartLow;
    }

    public Integer getRaHigh() {
        return raHigh;
    }

    public void setRaHigh(Integer raHigh) {
        this.raHigh = raHigh;
    }

    public Integer getRaLow() {
        return raLow;
    }

    public void setRaLow(Integer raLow) {
        this.raLow = raLow;
    }

    public Integer getSpo2Low() {
        return spo2Low;
    }

    public void setSpo2Low(Integer spo2Low) {
        this.spo2Low = spo2Low;
    }

    public Float getTempHigh() {
        return tempHigh;
    }

    public void setTempHigh(Float tempHigh) {
        this.tempHigh = tempHigh;
    }

    public Float getTempLow() {
        return tempLow;
    }

    public void setTempLow(Float tempLow) {
        this.tempLow = tempLow;
    }

    public HeartBandDeviceSet(Integer heartHigh, Integer heartLow, Integer raHigh, Integer raLow, Integer spo2Low, Float tempHigh, Float tempLow) {
        this.heartHigh = heartHigh;
        this.heartLow = heartLow;
        this.raHigh = raHigh;
        this.raLow = raLow;
        this.spo2Low = spo2Low;
        this.tempHigh = tempHigh;
        this.tempLow = tempLow;
    }
}
