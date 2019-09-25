package com.example.tetris;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class Maps extends AppCompatActivity {

    Marathon parent;
    RadioButton[] RB = new RadioButton[7];

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);

        RB[1] = (RadioButton)findViewById(R.id.Button1);
        RB[2] = (RadioButton)findViewById(R.id.Button2);
        RB[3] = (RadioButton)findViewById(R.id.Button3);
        RB[4] = (RadioButton)findViewById(R.id.Button4);
        RB[5] = (RadioButton)findViewById(R.id.Button5);
        RB[6] = (RadioButton)findViewById(R.id.Button6);

        for(int i = 1; i < 7; i++){
            RB[i].setChecked(false);
        }

        try {
            RB[parent.mapNum].setChecked(true);
        } catch (Exception ThereIsNoMapSelectedStopBotheringMe){
            RB[1].setChecked(true);
        }
    }

    public void onExit(View v){
        finish();
    }

    public void onMap(View v){
        for(int i = 1; i < 7; i++){
            RB[i].setChecked(false);
        }

        switch( v.getId() ){
            case R.id.Button1 : {
                RB[1].setChecked(true);
                Toast toast = Toast.makeText(getApplication(), "Map 1 Selected", Toast.LENGTH_SHORT);
                toast.show();
                parent.mapNum = 1;
                break;
            }
            case R.id.Button2 : {
                RB[2].setChecked(true);
                Toast toast = Toast.makeText(getApplication(), "Map 2 Selected", Toast.LENGTH_SHORT);
                toast.show();
                parent.mapNum = 2;
                break;
            }
            case R.id.Button3 : {
                RB[3].setChecked(true);
                Toast toast = Toast.makeText(getApplication(), "Map 3 Selected", Toast.LENGTH_SHORT);
                toast.show();
                parent.mapNum = 3;
                break;
            }
            case R.id.Button4 : {
                RB[4].setChecked(true);
                Toast toast = Toast.makeText(getApplication(), "Map 4 Selected", Toast.LENGTH_SHORT);
                toast.show();
                parent.mapNum = 4;
                break;
            }
            case R.id.Button5 : {
                RB[5].setChecked(true);
                Toast toast = Toast.makeText(getApplication(), "Map 5 Selected", Toast.LENGTH_SHORT);
                toast.show();
                parent.mapNum = 5;
                break;
            }
            case R.id.Button6 : {
                RB[6].setChecked(true);
                Toast toast = Toast.makeText(getApplication(), "Map 6 Selected", Toast.LENGTH_SHORT);
                toast.show();
                parent.mapNum = 6;
                break;
            }
        }
    }
}
