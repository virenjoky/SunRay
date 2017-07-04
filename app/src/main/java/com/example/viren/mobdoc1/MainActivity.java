package com.example.viren.mobdoc1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 //       Toast.makeText(MainActivity.this, "Toast Done", Toast.LENGTH_LONG).show();
        getFieldsFromFile();
//this is the code for the button click
        final ImageButton button = (ImageButton) findViewById(R.id.LookInside);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                // getFieldsFromFile();
                getFields();
                Intent activityChangeIntent = new Intent(MainActivity.this, BodyActivity.class);

                // currentContext.startActivity(activityChangeIntent);

                MainActivity.this.startActivity(activityChangeIntent);
            }
        });
    }


    public void getFields() {
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



        try {
            File path = this.getFilesDir();
            File file = new File(path, "Mytest.txt");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
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

        try {
            File path = this.getFilesDir();
            File file = new File(path, "Mytest.txt");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            UserData result = (UserData) ois.readObject ();
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
//            Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
