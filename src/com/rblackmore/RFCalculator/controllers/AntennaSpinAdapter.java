package com.rblackmore.RFCalculator.controllers;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.rblackmore.RFCalculator.models.Antenna;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 16/01/13
 * Time: 9:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class AntennaSpinAdapter extends ArrayAdapter<Antenna>{

    private Context context;
    private ArrayList<Antenna> values;

    public AntennaSpinAdapter(Context context, int textViewResourceId, ArrayList<Antenna> values) {
        //Constructor sets up the context and the array of objects
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;


    }

    public int getCount() {
        //returns the size of the array
        return values.size();
    }

    public Antenna getItem(int position) {
        //returns a single object from the given position
        return values.get(position);
    }

    public long getItemId(int position) {
        //returns the position
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //builds and Returns a view for the Spinner display
        TextView label = new TextView(context);
        label.setTextColor(Color.WHITE);
        label.setText(String.valueOf(values.get(position).getIntID()) + ", " + values.get(position).getStrName());
        label.setTextSize(18.0f);

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        //build and Returns a view for the drop down list below the spinner display
        TextView label = new TextView(context);
        label.setTextColor(Color.WHITE);
        label.setText(String.valueOf(values.get(position).getIntID()) + ", " + values.get(position).getStrName());
        label.setTextSize(18.0f);

        return label;
    }
}
