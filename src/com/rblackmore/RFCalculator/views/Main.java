package com.rblackmore.RFCalculator.views;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
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

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Ryan Blackmore
 * @Date: 17/12/12
 * @Time: 4:55 PM
 * @Version: 1.0
 * To change this template use File | Settings | File Templates.
 * <p/>
 * Description:
 */

public class Main extends Activity {

    static RFCalcController rfc;

    private Spinner spinnerCable = null;
    private Spinner spinnerAntenna = null;

    private AntennaSpinAdapter antSpinAdapter;
    private CableSpinAdapter cabSpinAdapter;

    private Antenna selectedAntenna;
    private Cable selectedCable;

    EditText edtxtPower;
    RadioButton rdoEIRP;

    EditText edtxtGain;
    EditText edtxtLength;

    AlertDialog.Builder details;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            rfc = new RFCalcController(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        ArrayList<Antenna> antennas = new ArrayList<Antenna>(rfc.getAntennas());
        ArrayList<Cable> cables = new ArrayList<Cable>(rfc.getCables());

        //Populate spinners
        popAntennas(antennas);
        popCables(cables);

        edtxtPower = (EditText) findViewById(R.id.edtxtPower);
        edtxtGain = (EditText) findViewById(R.id.edtxtGain);
        edtxtLength = (EditText) findViewById(R.id.edtxtLength);
        rdoEIRP = (RadioButton) findViewById(R.id.rdoEIRP);

    }

    private void popCables(ArrayList<Cable> cables) {
        /******CABLE SETUP******/
        cabSpinAdapter = new CableSpinAdapter(Main.this,
                android.R.layout.simple_spinner_item, cables);
        spinnerCable = (Spinner) findViewById(R.id.spnrCable);
        spinnerCable.setAdapter(cabSpinAdapter);

        spinnerCable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cable cable = cabSpinAdapter.getItem(position);

                selectedCable = cable;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void popAntennas(ArrayList<Antenna> antennas) {
        /******ANTENNA SETUP******/
        //creates a new adapter object, Passes the context, resource Id, and the arraylist to the adapter
        antSpinAdapter = new AntennaSpinAdapter(Main.this,
                android.R.layout.simple_spinner_item, antennas);
        spinnerAntenna = (Spinner) findViewById(R.id.spnrAntennas);
        //assigns the adapter object to the view, the view here will automatically populate itself using the adapters methods
        spinnerAntenna.setAdapter(antSpinAdapter);

        spinnerAntenna.setSelection(1);

        //Listener for selecting an item
        spinnerAntenna.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                                Antenna antenna = antSpinAdapter.getItem(position);
                EditText edtxtGain = (EditText) findViewById(R.id.edtxtGain);
                CheckBox chkPolarization = (CheckBox) findViewById(R.id.chkPolarization);

                edtxtGain.setText(String.valueOf(antenna.getFltGain()));
                chkPolarization.setChecked(antenna.isBlnType());

                selectedAntenna = antenna;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public void btnCalcClick(View view) {

        Boolean blnEIRP;

        if (rdoEIRP.isChecked()) {
            blnEIRP = true;
        } else {
            blnEIRP = false;
        }

        float fltPower = Float.parseFloat(String.valueOf(edtxtPower.getText()));
        float fltLength = Float.parseFloat(String.valueOf(edtxtLength.getText()));

        float fltCFG3 = rfc.beginCalculation(blnEIRP, fltPower, selectedAntenna, selectedCable, fltLength);

        //fltCFG3 = 2.0f;

        /*Toast.makeText(Main.this, String.valueOf("Answer: " + fltCFG3 + "\nEIRP: " + rfc.isEIRP()
                + "\nERPWatt: " + rfc.getERPWatt() + "\nEIRPWatt: " + rfc.getEIRPWatt()
                + "\nERPDbm: " + rfc.getERPdBm() + "\nEIRPdBm: " + rfc.getEIRPdBm()
                + "\nRadiated Power: " + rfc.getRaddBm() + "\nCFG3: " + rfc.getCFG3())
                + "\nAttenuation: " + rfc.getAttenuation(), Toast.LENGTH_LONG).show();*/

        buildDetailsMessage(rfc.getCFG3());

        details.show();

    }

    private void buildDetailsMessage(float cfg3 ) {
        details = new AlertDialog.Builder(this);

        details.setTitle(R.string.detailsTitle);

        details
                .setMessage("Setting for CFG3: " + String.format("%.1f", cfg3))
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

    }


}