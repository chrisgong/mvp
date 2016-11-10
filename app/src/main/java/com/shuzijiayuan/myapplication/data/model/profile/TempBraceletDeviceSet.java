package com.shuzijiayuan.myapplication.data.model.profile;

/**
 * Created by gc on 16/8/23.
 */

public class TempBraceletDeviceSet {
    public int ms;

    public int offScreen;

    public float warningLowTemp;

    public boolean remind;

    public int getMs() {
        return ms;
    }

    public void setMs(int ms) {
        this.ms = ms;
    }

    public int getOffScreen() {
        return offScreen;
    }

    public void setOffScreen(int offScreen) {
        this.offScreen = offScreen;
    }

    public float getWarningLowTemp() {
        return warningLowTemp;
    }

    public void setWarningLowTemp(float warningLowTemp) {
        this.warningLowTemp = warningLowTemp;
    }

    public boolean isRemind() {
        return remind;
    }

    public void setRemind(boolean remind) {
        this.remind = remind;
    }

    public TempBraceletDeviceSet(int ms, int offScreen, float warningLowTemp, boolean remind) {
        this.ms = ms;
        this.offScreen = offScreen;
        this.warningLowTemp = warningLowTemp;
        this.remind = remind;
    }

    @Override
    public String toString() {
        return "TempBraceletDeviceSet{" +
                "ms=" + ms +
                ", offScreen=" + offScreen +
                ", warningLowTemp=" + warningLowTemp +
                '}';
    }
}
