package com.example.viren.mobdoc1;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viren.logger.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Device;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import com.example.viren.logger.Log;
import com.example.viren.logger.LogView;
import com.example.viren.logger.LogWrapper;
import com.example.viren.logger.MessageOnlyLogFilter;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


import static com.example.viren.mobdoc1.R.string.intro_text;
import static java.text.DateFormat.getDateInstance;
import static java.text.DateFormat.getTimeInstance;

public class TrackerActivity extends AppCompatActivity {

    public static final String TAG = "BasicHistoryApi";
    private static final int REQUEST_OAUTH = 1;
    private static final String DATE_FORMAT = "yyyy.MM.dd HH:mm:ss";

    /**
     *  Track whether an authorization activity is stacking over the current activity, i.e. when
     *  a known auth error is being resolved, such as showing the account chooser or presenting a
     *  consent dialog. This avoids common duplications as might happen on screen rotations, etc.
     */
    private static final String AUTH_PENDING = "auth_state_pending";
    private static boolean authInProgress = false;

    public static GoogleApiClient mClient = null;

    // Read text from file
    public void readFromFile() {
        //reading text from file
        try {
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[100];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            Log.i(TAG, "Date from file: " + s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read text from file
    public String readFromFile(String fileName) {
        //reading text from file
        String s="";
        try {
            FileInputStream fileIn=openFileInput(fileName);
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[100];

            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.tracker_activity);
        setContentView(R.layout.step_tracker);
        String steps=readFromFile("today.txt");
        int todaysSteps = Integer.parseInt(steps);
        int stepsRemaining = 1000 - todaysSteps;

        TextView editText5 = (TextView)findViewById(R.id.title_text_view);
        editText5.setText(String.valueOf(stepsRemaining), TextView.BufferType.EDITABLE);

        String yesterdaySteps=readFromFile("yesterday.txt");
        int yestSteps = Integer.parseInt(yesterdaySteps);

        //code for overlay - Viren
        ImageView layout = (ImageView)findViewById(R.id.tickMark);
        layout.setVisibility(View.INVISIBLE); // you can use INVISIBLE also instead of GONE
        ImageView layout2 = (ImageView)findViewById(R.id.crossMark);
        layout2.setVisibility(View.INVISIBLE); // you can use INVISIBLE also instead of GONE

        if (yestSteps < 1000) {
            ImageView layout3 = (ImageView)findViewById(R.id.crossMark);
            layout3.setVisibility(View.VISIBLE); // you can use INVISIBLE also instead of GONE
        }else{
            ImageView layout4 = (ImageView)findViewById(R.id.tickMark);
            layout4.setVisibility(View.VISIBLE); // you can use INVISIBLE also instead of GONE
        }


    }


}
