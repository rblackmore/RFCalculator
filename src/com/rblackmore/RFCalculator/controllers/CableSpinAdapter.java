package com.rblackmore.RFCalculator.controllers;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.rblackmore.RFCalculator.models.Cable;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Ryan
 * Date: 16/01/13
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class CableSpinAdapter extends ArrayAdapter<Cable> {

    private Context context;
    private ArrayList<Cable> values;

    public CableSpinAdapter(Context context, int textViewResourceId, ArrayList<Cable> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    public int getCount() {
        return values.size();
    }

    public Cable getItem(int position) {
        return values.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.WHITE);
        label.setText(String.valueOf(values.get(position).getIntID()) + ", " + values.get(position).getStrName());
        label.setTextSize(18.0f);

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.WHITE);
        label.setText(String.valueOf(values.get(position).getIntID()) + ", " + values.get(position).getStrName());
        label.setTextSize(18.0f);

        return label;
    }

}
