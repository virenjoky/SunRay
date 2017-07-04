package com.example.viren.mobdoc1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Viren on 03/07/2017.
 */
public class ChangesActivity extends AppCompatActivity {
    Button xbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.life_changes);
        addListenerOnButton();

    }

    public void addListenerOnButton() {

        final Context context = this;
        xbutton = (Button) findViewById(R.id.starttrackbut);
        xbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, TrackerActivity.class);
                startActivity(intent);
            }
        });
    }
}
