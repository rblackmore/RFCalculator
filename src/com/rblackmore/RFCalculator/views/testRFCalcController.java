package com.rblackmore.RFCalculator.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.RFCalculator.R;
import com.rblackmore.RFCalculator.controllers.AntennaSpinAdapter;
import com.rblackmore.RFCalculator.controllers.CableSpinAdapter;
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
    private Spinner spinnerCable = null;
    private Spinner spinnerAntenna = null;

    private AntennaSpinAdapter antSpinAdapter;
    private CableSpinAdapter cabSpinAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testrfcontroller);

        try {
            rfc = new RFCalcController(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        //Retrieve the ArrayLists
        ArrayList<Cable> cables = new ArrayList<Cable>(rfc.getCables());
        ArrayList<Antenna> antennas = new ArrayList<Antenna>(rfc.getAntennas());

        //Initialize the adapter sending the current context
        //sedn the simple_spinner_item layout
        //and finally send the Antenna arraylist (the data)

        /**********************ANTENNA SETUP************************/
        antSpinAdapter = new AntennaSpinAdapter(testRFCalcController.this, android.R.layout.simple_spinner_item,
                antennas);

        spinnerAntenna = (Spinner) findViewById(R.id.antennaList);
        spinnerAntenna.setAdapter(antSpinAdapter); //Set the custom adapter to the spinner
        //you can create an anonymouse istener to hand the event when is selected a spinner item.
        spinnerAntenna.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //here you get the current itme (an Antenna object) that is selected by its position
                Antenna antenna = antSpinAdapter.getItem(position);
                //Here you can do the action you want to ...

                Toast.makeText(testRFCalcController.this, "ID: " + antenna.getIntID() + "\nGain: " + antenna.getFltGain()
                        + "\nName: " + antenna.getStrName() + "\nPolarization: " + antenna.isBlnType(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        /******************************CABLE SETUP********************************/
        cabSpinAdapter = new CableSpinAdapter(testRFCalcController.this, android.R.layout.simple_spinner_item,
                cables);

        spinnerCable = (Spinner) findViewById(R.id.cableList);
        spinnerCable.setAdapter(cabSpinAdapter);
        spinnerCable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cable cable = cabSpinAdapter.getItem(position);

                Toast.makeText(testRFCalcController.this, "ID: " + cable.getIntID() + "\nName: " + cable.getStrName()
                        + "\nAtennuation: " + cable.getFltAttenuation(), Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        }

        );
    }
}