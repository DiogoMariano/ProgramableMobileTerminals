package com.example.user.trabalho;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private int position=0;
    public int [] colors ={Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.BLACK};

    Button ok;
    //Button cancel = (Button) findViewById(R.id.cancel);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int value;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ok = (Button) findViewById(R.id.ok);

        //Receive the intent
        Intent received_intent = getIntent();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSoundClick(v);
            }
        });



        //Get the data from the Intent.


    }

    //All onClick methods have to be public, return void and take View as
//an argument
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        if (checked) {
        // Check which radio button was clicked
            RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
            switch (view.getId()){
                case R.id.red: position = 0; break;
                case R.id.blue: position = 1;break;
                case R.id.green: position = 2;break;
                case R.id.cyan: position = 3;break;
                case R.id.magenta: position = 4;break;
                case R.id.black: position = 5;break;
            }
        }
    }

    public void setSoundClick(View v){
        Intent data=new Intent();
        data.putExtra(MainActivity.COLOR_ID,position);
        setResult(RESULT_OK,data);
        finish();
}



    public void cancel(View v){
//Create an empty Inent and add the selected_sound variable to it
        Intent data = new Intent(getApplicationContext(),MainActivity.class);
        getIntent().putExtra("color",position);
        //data.putExtra(MainActivity.POSITION,position);
//Set the result code for the MainActivity and attach the data Intent
        setResult(RESULT_OK, data);
//Destroy this Activity and propagate the ActivityResult
        finish();
    }

}
