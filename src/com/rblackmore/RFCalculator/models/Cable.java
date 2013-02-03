package com.rblackmore.RFCalculator.models;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Ryan Blackmore
 * @Date: 20/12/12
 * @Time: 9:41 AM
 * @Version: 1.0
 * To change this template use File | Settings | File Templates.
 * <p/>
 * Description: Class for storing cable object information
 */
public class Cable {
    private int intID;
    private String strName;
    private float fltAttenuation; //100 Metres

    public Cable(int intID, String strName, float fltAttenuation) {
        this.intID = intID;
        this.strName = strName;
        this.fltAttenuation = fltAttenuation;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public float getFltAttenuation() {
        return fltAttenuation;
    }

    public void setFltAttenuation(float fltAttenuation) {
        this.fltAttenuation = fltAttenuation;
    }

    public int getIntID() {
        return intID;
    }

    public void setIntID(int intID) {
        this.intID = intID;
    }

    public float getAttenuation(float length) {
        //calculates The attenuation based on the length of the cable.
        float attenuation = (this.fltAttenuation / 100) * length;
        return attenuation;
    }
}
