package com.rblackmore.RFCalculator.controllers;

import android.content.Context;
import android.content.res.AssetManager;
import com.rblackmore.RFCalculator.models.Antenna;
import com.rblackmore.RFCalculator.models.Cable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Ryan Blackmore
 * @Date: 20/12/12
 * @Time: 10:08 AM
 * @Version: 1.0
 * To change this template use File | Settings | File Templates.
 * <p/>
 * Description:
 */
public class RFCalcController {

//    private float fltEirp;
//    private float fltErp;
//    private float fltRadiatedPowerW;
//    private float fltRadiatedIsotropW;
//    private float fltRadiatedPowerDBm;
//    private float fltRadiatedIsotropDBm;
//    private float fltRadiatedPowerDBmPost; //Radiated power post calculation
//    private float fltOutputPowerMW;
//    private float fltConfigPowerW;

    private Calculation calc;

    private List<Antenna> antennas;
    private List<Cable> cables;

//    final private float CORRECTION = 1.64f;
//    final private float CORRECTION_DB = 2.1f;
    final private String FILE_ANTENNAS = "antennas.csv";
    final private String FILE_CABLES = "cables.csv";
    final private String DELIMITER = ";";
    final private int ANTENNAS = 0;
    final private int CABLES = 1;



    public RFCalcController(Context aContext) throws IOException {
        //Initializing constructor
        antennas = new ArrayList<Antenna>();
        cables = new ArrayList<Cable>();
        loadData(aContext, FILE_ANTENNAS, true, ANTENNAS);
        loadData(aContext, FILE_CABLES, true, CABLES);

        calc = new Calculation();

    }

    private void loadData(Context aContext, String fileName, boolean heading, int whichList) throws IOException {
        //loads all antennas/cables from csv file into array list
        AssetManager mngr = aContext.getAssets();
        InputStream stream = mngr.open(fileName);

        BufferedReader br = new BufferedReader(new InputStreamReader(stream));

        String newLine;
        ArrayList<String> data = null;


        //read the first 2 lines (if first line is a heading)
        if (heading) {
            br.readLine();
        }

        newLine = br.readLine();


        while (newLine != null) {

            data = new ArrayList<String>(Arrays.asList(newLine.split(DELIMITER)));

            if (whichList == ANTENNAS) {
                //Populating Antennas list

                int id = Integer.parseInt(data.get(0));
                String name = data.get(1);
                float gain = Float.parseFloat(data.get(2));
                Boolean type;

                if (data.get(3).equalsIgnoreCase("true")) {
                    type = true;
                } else {
                    type = false;
                }

                antennas.add(new Antenna(id, name, gain, type));
            } else if (whichList == CABLES) {
                //populating cables list

                int id = Integer.parseInt(data.get(0));
                String name = data.get(1);
                float dBm = Float.parseFloat(data.get(2));

                cables.add(new Cable(id, name, dBm));

            }
            newLine = br.readLine();

        }

        br.close();
    }

    public List<Antenna> getAntennas() {
        return antennas;
    }

    public List<Cable> getCables() {
        return cables;
    }

    public float beginCalculation(boolean  blnEIRP, float fltOutuput, Antenna antenna, Cable cable, float fltCabLength) {
        calc.initCalculation(blnEIRP, fltOutuput, antenna, cable, fltCabLength);
        float fltCFG3 = calc.calculate();

        return fltCFG3;
    }

    public boolean isEIRP() {
        return calc.isBlnEIRP();
    }

    public float getERPWatt() {
        return calc.getFltERPWatt();
    }

    public float getEIRPWatt() {
        return calc.getFltEIRPWatt();
    }

    public float getERPdBm() {
        return calc.getFltERPdBm();
    }

    public float getEIRPdBm() {
        return calc.getFltEIRPdBm();
    }

    public float getRaddBm() {
        return calc.getFltRadiateddBm();
    }

    public float getCFG3() {
        return calc.getFltWattsCFG3();
    }

    public float getAttenuation() {
        return calc.getAttenuation();
    }

}
