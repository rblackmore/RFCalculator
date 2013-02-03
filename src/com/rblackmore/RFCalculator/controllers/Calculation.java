package com.rblackmore.RFCalculator.controllers;

import com.rblackmore.RFCalculator.models.Antenna;
import com.rblackmore.RFCalculator.models.Cable;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 22/01/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class Calculation {
    //Variables passed from UI
    private boolean blnEIRP;
    private float fltOutput;
    private Antenna antenna;
    private Cable cable;
    private float fltCabLength;

    //Variables for background calculations
    private float fltERPWatt;
    private float fltEIRPWatt;
    private float fltERPdBm;
    private float fltEIRPdBm;
    private float fltRadiateddBm;
    private float fltWattsCFG3;



    //Constants
    private final float CORRECTION_EIRP = 1.64f;
    private final float CORRECTION_DB = 2.1f;

    public Calculation(boolean blnEIRP, float fltOutput, Antenna antenna, Cable cable, float fltCabLength) {
        this.blnEIRP = blnEIRP;
        this.fltOutput = fltOutput;
        this.antenna = antenna;
        this.cable = cable;
        this.fltCabLength = fltCabLength;
    }

    public Calculation() {

    }

    public void initCalculation(boolean blnEIRP, float fltOutput, Antenna antenna, Cable cable, float fltCabLength) {
        this.blnEIRP = blnEIRP;
        this.fltOutput = fltOutput;
        this.antenna = antenna;
        this.cable = cable;
        this.fltCabLength = fltCabLength;
    }

    public float calculate() {
        doERP();
        doERPdBm();
        doRadiatedPowerDB();
        doOutputWatts();

        return this.fltWattsCFG3;

    }

    public void doERP() {
        //calculates the ERP power by converting the EIRP power if EIRP was selected

        if (this.blnEIRP) {
            this.fltEIRPWatt = this.fltOutput;
            this.fltERPWatt = this.fltOutput / CORRECTION_EIRP;
        } else {
            this.fltERPWatt = this.fltOutput;
        }
    }

    public void doERPdBm() {

        fltERPdBm = (float) ((float) 10 * (Math.log10(this.fltERPWatt * 1000)));
        fltEIRPdBm = this.fltERPdBm + CORRECTION_DB;
    }

    public void doRadiatedPowerDB() {

        float fltAttenuation = this.cable.getAttenuation(this.fltCabLength);

        this.fltRadiateddBm = this.fltEIRPdBm - this.antenna.getFltGain() + this.antenna.getTypeDB() + fltAttenuation;
    }

    public void doOutputWatts() {

        float fltMilliWatts = (float) Math.pow(10, this.fltRadiateddBm / 10);

        this.fltWattsCFG3 = fltMilliWatts / 1000;

    }

    public boolean isBlnEIRP() {
        return blnEIRP;
    }

    public float getFltCabLength() {
        return fltCabLength;
    }

    public float getFltERPWatt() {
        return fltERPWatt;
    }

    public float getFltEIRPWatt() {
        return fltEIRPWatt;
    }

    public float getFltERPdBm() {
        return fltERPdBm;
    }

    public float getFltEIRPdBm() {
        return fltEIRPdBm;
    }

    public float getFltRadiateddBm() {
        return fltRadiateddBm;
    }

    public float getFltWattsCFG3() {
        return fltWattsCFG3;
    }

    public float getAttenuation() {
        return this.cable.getAttenuation(this.fltCabLength);
    }
}
