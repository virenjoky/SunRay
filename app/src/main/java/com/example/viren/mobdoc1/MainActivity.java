package com.example.viren.mobdoc1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.Field;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;


import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.text.DateFormat.getDateInstance;
import static java.text.DateFormat.getTimeInstance;

public class MainActivity extends AppCompatActivity {

    UserData result;
    private int steps;
    static final int READ_BLOCK_SIZE = 100;
    static final String FIRST_OPEN_FILE_NAME = "firstOpen.txt";
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


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countSteps(savedInstanceState);

 //       Toast.makeText(MainActivity.this, "Toast Done", Toast.LENGTH_LONG).show();
        getFieldsFromFile();
//this is the code for the button click
        final ImageButton button = (ImageButton) findViewById(R.id.LookInside);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                // getFieldsFromFile();
                saveUserDataToFile();
                //countSteps(savedInstanceState);
                Intent activityChangeIntent = new Intent(MainActivity.this, BodyActivity.class);

                // currentContext.startActivity(activityChangeIntent);

                MainActivity.this.startActivity(activityChangeIntent);
            }
        });




    }


    public void saveUserDataToFile() {
        UserData mUser = new UserData();
        String mEdit = ((EditText) findViewById(R.id.editName)).getText().toString();
        mUser.Name = mEdit;
        mEdit = ((EditText) findViewById(R.id.editPolNo)).getText().toString();
        mUser.PolNo = mEdit;
        mEdit = ((EditText) findViewById(R.id.editAge)).getText().toString();
        int iEdit = Integer.valueOf(mEdit);
        mUser.Age = iEdit;
        mEdit = ((EditText) findViewById(R.id.editHeight)).getText().toString();
        float hEdit = Float.valueOf(mEdit);
        mUser.Height = hEdit;
        mEdit = ((EditText) findViewById(R.id.editWeight)).getText().toString();
        float wEdit = Float.valueOf(mEdit);
        mUser.Weight = wEdit;
        mEdit = ((EditText) findViewById(R.id.editGender)).getText().toString();
        mUser.Gender = mEdit;
        mUser.lastOpenedTimestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        mUser.firstOpenedTimestamp = result.firstOpenedTimestamp;

        int heartPercent=BMICalculator.getHeartPercentage(mUser.Age,Math.round(mUser.Weight),Math.round(mUser.Height),mUser.Gender.charAt(0),steps);
        System.out.println("heaRT..."+heartPercent);

        writeToFile("heartRate.txt",Integer.toString(heartPercent));

        //Toast.makeText(MainActivity.this, heartPercent, Toast.LENGTH_LONG).show();

/*        try {
            File path = this.getFilesDir();
            File file = new File(path, "Mytest.txt");
            //FileInputStream fileIn=openFileInput("Mytest.txt");
            //System.out.println("aaaaaaaa.a.a..a.a.a..a.");
           // countSteps(savedInstanceState);
        } catch (FileNotFoundException e) {
  *//*          *//**//*Date now = new Date();
            Calendar cal= Calendar.getInstance();
            cal.setTime(now);
            cal.add(Calendar.DAY_OF_YEAR, -1);
            writeToFile(FIRST_OPEN_FILE_NAME,new Date(cal.getTimeInMillis()).toString());*//**//*
           // writeToFile(FIRST_OPEN_FILE_NAME,new Date().toString());
            mUser.firstOpenedTimestamp = new Date().toString();
            //countSteps(savedInstanceState);*//*
        }*/



        try {
            File path = this.getFilesDir();
            File file = new File(path, "Mytest.txt");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //mUser.firstOpenedTimestamp = new Date().toString();
            oos.writeObject(mUser);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
       Toast.makeText(MainActivity.this, mUser.toString(), Toast.LENGTH_LONG).show();
*/
        /*Toast.makeText(MainActivity.this, mUser, Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity.this, mUser., Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity.this, mUser.Gender,Toast.LENGTH_LONG).show();
System.out.print(mUser);
*/

    }

    public void getFieldsFromFile() {
 //       Toast.makeText(MainActivity.this, "Unable to load file", Toast.LENGTH_LONG).show();

        result = new UserData();
        try {
            File path = this.getFilesDir();
            File file = new File(path, "Mytest.txt");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            result = (UserData) ois.readObject ();
            ois.close();

            EditText editText1 = (EditText)findViewById(R.id.editName);
            editText1.setText(result.Name, TextView.BufferType.EDITABLE);

            EditText editText2 = (EditText)findViewById(R.id.editPolNo);
            editText2.setText(result.PolNo, TextView.BufferType.EDITABLE);

            EditText editText3 = (EditText)findViewById(R.id.editGender);
            editText3.setText(result.Gender, TextView.BufferType.EDITABLE);

            EditText editText4 = (EditText)findViewById(R.id.editHeight);
            editText4.setText(String.valueOf(result.Height), TextView.BufferType.EDITABLE);

            EditText editText5 = (EditText)findViewById(R.id.editWeight);
            editText5.setText(String.valueOf(result.Weight), TextView.BufferType.EDITABLE);

            EditText editText6 = (EditText)findViewById(R.id.editAge);
            editText6.setText(String.valueOf(result.Age), TextView.BufferType.EDITABLE);
            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.DAY_OF_YEAR, -2);
            Date date=new Date(cal2.getTimeInMillis());
            result.firstOpenedTimestamp = date.toString();

//            Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            result.firstOpenedTimestamp = new Date().toString();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    //Ankit - Code for Gfit starts

    public void countSteps(Bundle savedInstanceState){
        if (savedInstanceState != null) {
            authInProgress = savedInstanceState.getBoolean(AUTH_PENDING);
        }

        buildFitnessClient();
    }


    private void buildFitnessClient() {
        // Create the Google API Client
        mClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
                .addConnectionCallbacks(
                        new GoogleApiClient.ConnectionCallbacks() {
                            @Override
                            public void onConnected(Bundle bundle) {
                                //Log.i(TAG, "Connected!!!");
                                // Now you can make calls to the Fitness APIs.  What to do?
                                // Look at some data!!
                               new GetFitDataTask().execute();
                                //doInBackground();
                            }

                            @Override
                            public void onConnectionSuspended(int i) {
                                // If your connection to the sensor gets lost at some point,
                                // you'll be able to determine the reason and react to it here.
                                if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
                                    //Log.i(TAG, "Connection lost.  Cause: Network Lost.");
                                } else if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
                                    //Log.i(TAG, "Connection lost.  Reason: Service Disconnected");
                                }
                            }
                        }
                )
                .enableAutoManage(this, 0, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult result) {

                    }
                })
                .build();
    }



    private class GetFitDataTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {

            //String firstDate=readFromFile(FIRST_OPEN_FILE_NAME);
            Date startDate=new Date(result.firstOpenedTimestamp);

            /*Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.DAY_OF_YEAR, -2);
            Date date=new Date(cal2.getTimeInMillis());*/

            System.out.println("Dte////"+startDate);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(startDate);
            long startTime = cal1.getTimeInMillis();

            cal1.setTime(new Date());
            long endTime = cal1.getTimeInMillis();

            System.out.println("|End Dte////"+new Date(endTime));

            /*Calendar cal = Calendar.getInstance();
            Date now = new Date();
            cal.setTime(now);
            cal.add(Calendar.DAY_OF_YEAR, -1);
            endTime = cal.getTimeInMillis();
            cal.add(Calendar.DAY_OF_YEAR, -1);
            startTime = cal.getTimeInMillis();*/
            // [END insert_dataset]


            steps=fetchStepsData(startTime,endTime);
            System.out.println("Starte Date..."+startDate);
            System.out.println("End Date..."+new Date());
            System.out.println("Total Steps..."+steps);



            writeToFile("fitnessData.txt",Integer.toString(steps));

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, -1);
            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);
            cal.set(Calendar.MILLISECOND,0);
            Date yesterdayStart=new Date(cal.getTimeInMillis());
            System.out.println(yesterdayStart);

            Calendar cal5 = Calendar.getInstance();
            cal5.add(Calendar.DAY_OF_YEAR, -1);
            cal5.set(Calendar.HOUR_OF_DAY,11);
            cal5.set(Calendar.MINUTE,59);
            cal5.set(Calendar.SECOND,59);
            cal5.set(Calendar.MILLISECOND,0);
            Date yesterdayEnd=new Date(cal5.getTimeInMillis());
            System.out.println(yesterdayEnd);

            Calendar cal3 = Calendar.getInstance();
            cal3.set(Calendar.HOUR_OF_DAY,0);
            cal3.set(Calendar.MINUTE,0);
            cal3.set(Calendar.SECOND,0);
            cal3.set(Calendar.MILLISECOND,0);
            Date todayStart=new Date(cal3.getTimeInMillis());
            System.out.println(todayStart);

            Calendar cal4 = Calendar.getInstance();
            Date todayEnd=new Date(cal4.getTimeInMillis());
            System.out.println(todayEnd);


            int todaySteps=fetchStepsData(todayStart.getTime(),todayEnd.getTime());
            writeToFile("today.txt",Integer.toString(todaySteps));

            int yestSteps=fetchStepsData(yesterdayStart.getTime(),yesterdayEnd.getTime());
            writeToFile("yesterday.txt",Integer.toString(yestSteps));
            return null;
        }
    }

    public int fetchStepsData(long startTime,long endTime){
        // Begin by creating the query.
        // [START build_read_data_request]
        // Setting a start and end date using a range of 1 week before this moment.
        int stepData=0;

        DataReadRequest readRequest = queryFitnessData(startTime,endTime);

        DataReadResult dataReadResult =
                Fitness.HistoryApi.readData(mClient, readRequest).await(1, TimeUnit.MINUTES);

        // [START parse_read_data_result]
        // If the DataReadRequest object specified aggregated data, dataReadResult will be returned
        // as buckets containing DataSets, instead of just DataSets.
        if (dataReadResult.getBuckets().size() > 0) {
            //Log.i(TAG, "Number of returned buckets of DataSets is: " + dataReadResult.getBuckets().size());
            for (Bucket bucket : dataReadResult.getBuckets()) {
                List<DataSet> dataSets = bucket.getDataSets();
                for (DataSet dataSet : dataSets) {
                    stepData=stepData+getStepData(dataSet);
                }
            }
        } else if (dataReadResult.getDataSets().size() > 0) {
            //Log.i(TAG, "Number of returned DataSets is: " + dataReadResult.getDataSets().size());
            for (DataSet dataSet : dataReadResult.getDataSets()) {
                stepData=stepData+getStepData(dataSet);
            }
        }
        return stepData;
    }

    public static DataReadRequest queryFitnessData(long startTime,long endTime) {


        java.text.DateFormat dateFormat = getDateInstance();
        //Log.i(TAG, "Now: " + dateFormat.format(new Date().getTime()));
        //Log.i(TAG, "Range Start: " + dateFormat.format(startTime));
        //Log.i(TAG, "Range End: " + dateFormat.format(endTime));

        DataReadRequest readRequest = new DataReadRequest.Builder()
                // The data request can specify multiple data types to return, effectively
                // combining multiple data queries into one call.
                // In this example, it's very unlikely that the request is for several hundred
                // datapoints each consisting of a few steps and a timestamp.  The more likely
                // scenario is wanting to see how many steps were walked per day, for 7 days.
                .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
                // Analogous to a "Group By" in SQL, defines how data should be aggregated.
                // bucketByTime allows for a time span, whereas bucketBySession would allow
                // bucketing by "sessions", which would need to be defined in code.
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .build();
        // [END build_read_data_request]

        return readRequest;
    }

    private int getStepData(DataSet dataSet){
        //Log.i(TAG, "Data returned for Data type: " + dataSet.getDataType().getName());
        DateFormat dateFormat = getTimeInstance();
        int totalSteps=0;
        for (DataPoint dp : dataSet.getDataPoints()) {
            //Log.i(TAG, "Data point:");
            //Log.i(TAG, "\tType: " + dp.getDataType().getName());
            //Log.i(TAG, "\tStart: " + dateFormat.format(dp.getStartTime(TimeUnit.MILLISECONDS)));
            //Log.i(TAG, "\tEnd: " + dateFormat.format(dp.getEndTime(TimeUnit.MILLISECONDS)));
            for(Field field : dp.getDataType().getFields()) {
                //Log.i(TAG, "\t Your Target Steps are " + 10000);

                //Log.i(TAG, "\t " + field.getName() +
                //   " Value: " + dp.getValue(field));
                System.out.println("aa.....aaaaa..."+dp.getValue(field));
                totalSteps=totalSteps+dp.getValue(field).asInt();
            }
        }
        System.out.println("Steps.."+totalSteps);
        return totalSteps;
    }

    // write text to file
    public void writeToFile(String fileName,String content) {
        // add-write text into file
        try {
            FileOutputStream fileout=openFileOutput(fileName, MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            Date now= new Date();
            outputWriter.write(content);
            outputWriter.close();

            /*//display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();*/

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

}
