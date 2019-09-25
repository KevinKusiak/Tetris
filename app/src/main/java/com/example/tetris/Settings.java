package com.example.tetris;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    RadioButton RB1, RB2, RB3, RB4, RB5, RB6, RB7, RB8, RB9;
    CheckBox CB1;
    Marathon parent;
    SharedPreferences mPref = null;

    EditText ED;
    EditText ED2;
    String strDAS;
    String strARR;
    Button ResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        RB1 = (RadioButton)findViewById(R.id.classic);
        RB2 = (RadioButton)findViewById(R.id.SRS);
        RB3 = (RadioButton)findViewById(R.id.custom);
        RB4 = (RadioButton)findViewById(R.id.completeRandom);
        RB5 = (RadioButton)findViewById(R.id.classicRandom);
        RB6 = (RadioButton)findViewById(R.id.bag7Random);
        RB7 = (RadioButton)findViewById(R.id.softDropSlow);
        RB8 = (RadioButton)findViewById(R.id.softDropClassic);
        RB9 = (RadioButton)findViewById(R.id.softDropFast);
        CB1 = (CheckBox)findViewById(R.id.showPoints);
        ResetButton = (Button)findViewById(R.id.RButton);

        ED = (EditText)findViewById(R.id.ED1);
        ED2 = (EditText)findViewById(R.id.ED2);

        mPref = getSharedPreferences("setup", MODE_PRIVATE);
        readPreferences();

        ED.setText(parent.DAS + "");
        ED2.setText(parent.ARR + "");
        ResetButton.setText("RESET ALL SCORES AND TIMES!\nTHIS CANNOT BE UNDONE!");

        if (parent.rotationType == 0) {
            RB1.setChecked(true);
            RB2.setChecked(false);
            RB3.setChecked(false);
        } else if(parent.rotationType == 1){
            RB1.setChecked(false);
            RB2.setChecked(true);
            RB3.setChecked(false);
        } else if(parent.rotationType == 2){
            RB1.setChecked(false);
            RB2.setChecked(false);
            RB3.setChecked(true);
        }

        if(parent.randomType == 0){
            RB4.setChecked(true);
            RB5.setChecked(false);
            RB6.setChecked(false);
        } else if(parent.randomType == 1) {
            RB4.setChecked(false);
            RB5.setChecked(true);
            RB6.setChecked(false);
        } else if(parent.randomType == 2) {
            RB4.setChecked(false);
            RB5.setChecked(false);
            RB6.setChecked(true);
        }

        if(parent.softDropSpeed == 5){
            RB7.setChecked(true);
            RB8.setChecked(false);
            RB9.setChecked(false);
        } else if(parent.softDropSpeed == 10) {
            RB7.setChecked(false);
            RB8.setChecked(true);
            RB9.setChecked(false);
        } else if(parent.softDropSpeed == 20) {
            RB7.setChecked(false);
            RB8.setChecked(false);
            RB9.setChecked(true);
        }
    }

    public void onReset(View v){
        new AlertDialog.Builder(this).setTitle("Confirm Reset?").setMessage("All of your highscores will be deleted! This cannot be undone!").setIcon(R.drawable.pausebutton)
                .setPositiveButton("RESET!!!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        SharedPreferences.Editor editor = mPref.edit();

                        parent.highscore = 0;
                        parent.sprintHighscore = "99 : 99 : 999";
                        editor.putString("highscore", "0");
                        editor.putString("sprint highscore", "99 : 99 : 999");
                        editor.commit();
                    }
                })
                .setNegativeButton("Okay, maybe not", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){

                    }
                }).show();
    }

    public void onExit(View v){
        strDAS = ED.getText().toString().trim();
        strARR = ED2.getText().toString().trim();
        try {
            parent.DAS = Integer.parseInt(strDAS);
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplication(), "Please enter a positive whole number for DAS", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        try {
            parent.ARR = Integer.parseInt(strARR);
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplication(), "Please enter a positive whole number for ARR", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        savePreferences();
        finish();
    }

    public void onRotateSetting(View v){
        switch( v.getId() ){
            case R.id.classic : {
                parent.rotationType = 0;
                Toast toast = Toast.makeText(getApplication(), "Classic Rotation System Selected", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case R.id.SRS : {
                parent.rotationType = 1;
                Toast toast = Toast.makeText(getApplication(), "Super Rotation System Selected", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case R.id.custom : {
                parent.rotationType = 2;
                Toast toast = Toast.makeText(getApplication(), "Insane Rotation System Selected", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
        }
        savePreferences();
        //Toast.makeText(this, Integer.toString(parent.randomType) + " " + Integer.toString(parent.rotationType) + " " + Integer.toString(parent.softDropSpeed), Toast.LENGTH_SHORT).show();
    }

    public void onRandomSetting(View v){
        switch( v.getId() ){
            case R.id.completeRandom : {
                parent.randomType = 0;
                Toast toast = Toast.makeText(getApplication(), "Complete Randomization System Selected", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case R.id.classicRandom : {
                parent.randomType = 1;
                Toast toast = Toast.makeText(getApplication(), "Classic Randomization System Selected", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case R.id.bag7Random : {
                parent.randomType = 2;
                Toast toast = Toast.makeText(getApplication(), "7-Bag Randomization System Selected", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
        }
        savePreferences();
        //Toast.makeText(this, Integer.toString(parent.randomType) + " " + Integer.toString(parent.rotationType) + " " + Integer.toString(parent.softDropSpeed), Toast.LENGTH_SHORT).show();
    }

    public void onSoftDropSetting(View v){
        switch( v.getId() ){
            case R.id.softDropSlow : {
                parent.softDropSpeed = 5;
                Toast toast = Toast.makeText(getApplication(), "Slow Drop Selected", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case R.id.softDropClassic : {
                parent.softDropSpeed = 10;
                Toast toast = Toast.makeText(getApplication(), "Normal Selected", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
            case R.id.softDropFast : {
                parent.softDropSpeed = 20;
                Toast toast = Toast.makeText(getApplication(), "Hyper Drop Selected", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
        }
        savePreferences();
        //Toast.makeText(this, Integer.toString(parent.randomType) + " " + Integer.toString(parent.rotationType) + " " + Integer.toString(parent.softDropSpeed), Toast.LENGTH_SHORT).show();
    }

    public void savePreferences(){
        SharedPreferences.Editor editor = mPref.edit();

        editor.putInt("Random Type", parent.randomType);
        editor.putInt("Rotation Type", parent.rotationType);
        editor.putInt("Soft Drop Type", parent.softDropSpeed);
        editor.putInt("DAS", parent.DAS);
        editor.putInt("ARR", parent.ARR);
        editor.commit();
        //Toast.makeText(this, Integer.toString(parent.randomType) + " " + Integer.toString(parent.rotationType) + " " + Integer.toString(parent.softDropSpeed), Toast.LENGTH_SHORT).show();
    }

    public void readPreferences(){
        //Toast.makeText(this, Integer.toString(parent.randomType) + " " + Integer.toString(parent.rotationType) + " " + Integer.toString(parent.softDropSpeed), Toast.LENGTH_SHORT).show();
        try {
            parent.randomType = mPref.getInt("Random Type", 3); // Second one means get what value if this does not exist
        } catch (Exception PrefEmpty) {
            parent.randomType = 2;
        }
        try {
            parent.rotationType = mPref.getInt("Rotation Type", 3); // Second one means get what value if this does not exist
        } catch (Exception PrefEmpty) {
            parent.rotationType = 1;
        }
        try {
            parent.softDropSpeed = mPref.getInt("Soft Drop Type", 3); // Second one means get what value if this does not exist
        } catch (Exception PrefEmpty) {
            parent.softDropSpeed = 10;
        }
        try {
            parent.DAS = mPref.getInt("DAS", 3); // Second one means get what value if this does not exist
        } catch (Exception PrefEmpty) {
            parent.DAS = 1;
        }
        try {
            parent.ARR = mPref.getInt("ARR", 3); // Second one means get what value if this does not exist
        } catch (Exception PrefEmpty) {
            parent.ARR = 1;
        }
        //Toast.makeText(this, Integer.toString(parent.randomType) + " " + Integer.toString(parent.rotationType) + " " + Integer.toString(parent.softDropSpeed), Toast.LENGTH_SHORT).show();
    }

    public void showPointsLog(View v){
        parent.showPointsLog = CB1.isChecked();
    }
}