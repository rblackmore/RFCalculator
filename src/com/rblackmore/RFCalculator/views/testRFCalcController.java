package com.rblackmore.RFCalculator.views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.example.RFCalculator.R;
import com.rblackmore.RFCalculator.controllers.RFCalcController;
import com.rblackmore.RFCalculator.models.Antenna;
import com.rblackmore.RFCalculator.models.Cable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 15/01/13
 * Time: 11:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class testRFCalcController extends Activity {
    static RFCalcController rfc;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testrfcontroller);

        try {
            rfc = new RFCalcController(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Spinner spinnerCable = null;
        Spinner spinnerAntenna = null;

        ArrayList<Cable> cables = new ArrayList<Cable>(rfc.getCables());
        ArrayList<String> cableNames = new ArrayList<String>();

        for (Cable tempCable : cables) {
            cableNames.add(tempCable.getStrName());
        }

        ArrayList<Antenna> antennas = new ArrayList<Antenna>(rfc.getAntennas());
        ArrayList<String> antennaNames = new ArrayList<String>();

        for (Antenna tempAntenna : antennas) {
            antennaNames.add(tempAntenna.getStrName());
        }

        spinnerCable = (Spinner) findViewById(R.id.cableList);
        ArrayAdapter<String> cablesAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cableNames);
        cablesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerCable.setAdapter(cablesAdapter);



        spinnerAntenna = (Spinner) findViewById(R.id.antennaList);
        ArrayAdapter<String> antennasAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, antennaNames);
        antennasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerAntenna.setAdapter(antennasAdapter);



    }
}