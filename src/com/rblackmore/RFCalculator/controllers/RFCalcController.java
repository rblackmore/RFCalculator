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
    private float fltEirp;
    private float fltErp;
    private float fltRadiatedPowerW;
    private float fltRadiatedIsotropW;
    private float fltRadiatedPowerDBm;
    private float fltRadiatedIsotropDBm;
    private float fltRadiatedPowerDBmPost; //Radiated power post calculation
    private float fltOutputPowerMW;
    private float fltConfigPowerW;

    private List<Antenna> antennas;
    private List<Cable> cables;

    final private float CORRECTION = 1.64f;
    final private float CORRECTION_DB = 2.1f;
    final private String FILE_ANTENNAS = "antennas.csv";
    final private String FILE_CABLES = "cables.csv";
    final private String DELIMITER = ";";


    public RFCalcController(Context aContext) throws IOException {
        //Initializing constructor
        antennas = new ArrayList<Antenna>();
        loadData(aContext, FILE_ANTENNAS, antennas);
        loadData(aContext, FILE_CABLES, cables);
    }

    private void loadData(Context aContext, String fileName, List list) throws IOException {
        //loads all antennas from csv file into array list
        AssetManager aMngr = aContext.getAssets();
        InputStream antennaStream = aMngr.open(fileName);

        BufferedReader br = new BufferedReader(new InputStreamReader(antennaStream));

        String lineData = br.readLine(); //one whole line from csv vile

        while (lineData != null) {
            String[] values = lineData.split(DELIMITER);
            if (list == antennas) {
                antennas.add(new Antenna(values[0], values[1], values[2], values[3]));

            } else if (list == cables) {
                cables.add(new Cable(values[0], values[1], values[2]));
            }
        }


    }
}
