package com.rblackmore.RFCalculator.views;


import android.app.Activity;
import android.os.Bundle;
import com.example.RFCalculator.R;
import com.rblackmore.RFCalculator.controllers.RFCalcController;

import java.io.IOException;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            RFCalcController rfc;
            rfc = new RFCalcController(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}