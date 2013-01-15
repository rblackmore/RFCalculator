package com.rblackmore.RFCalculator.models;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Ryan Blackmore
 * @Date: 20/12/12
 * @Time: 9:26 AM
 * @Version: 1.0
 * To change this template use File | Settings | File Templates.
 * <p/>
 * Description: Class for storing antenna information
 */
public class Antenna {
    private int intID;
    private String strName;
    private float fltGain;
    private boolean blnType; //true = Circular, false = Linear

    public Antenna(int intID, String strName, float fltGain, boolean blnType) {
        this.intID = intID;
        this.strName = strName;
        this.fltGain = fltGain;
        this.blnType = blnType;
    }

    public int getIntID() {
        return intID;
    }

    public void setIntID(int intID) {
        this.intID = intID;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public float getFltGain() {
        return fltGain;
    }

    public void setFltGain(float fltGain) {
        this.fltGain = fltGain;
    }

    public boolean isBlnType() {
        return blnType;
    }

    public void setBlnType(boolean blnType) {
        this.blnType = blnType;
    }

    public float getTypeDB() {
        if (this.blnType) {
            return (float) 3.0f;
        } else {
            return (float) 0.0f;
        }

    }
}
