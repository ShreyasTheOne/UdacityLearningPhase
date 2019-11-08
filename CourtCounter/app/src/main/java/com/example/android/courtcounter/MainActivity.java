package com.example.android.courtcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    static int scoreA =0, scoreB =0;

    //CODE FOR TEAM A
    public void twoplusA(View view) {
        scoreA+=2;
        displayA(scoreA);
    }
    public void threeplusA(View view) {
        scoreA+=3;
        displayA(scoreA);
    }
    public void oneplusA(View view) {
        scoreA+=1;
        displayA(scoreA);
    }

    public void displayA(int n){
        TextView dispA = (TextView) findViewById(R.id.scoreA);
        dispA.setText(""+n);
    }



    //CODE FOR TEAM B
    public void twoplusB(View view) {
        scoreB+=2;
        displayB(scoreB);
    }
    public void threeplusB(View view) {
        scoreB+=3;
        displayB(scoreB);
    }
    public void oneplusB(View view) {
        scoreB+=1;
        displayB(scoreB);
    }

    public void displayB(int n){
        TextView dispB = (TextView) findViewById(R.id.scoreB);
        dispB.setText(""+n);
    }

    public void reset(View view){
        scoreA=0;
        scoreB=0;
        displayA(scoreA);
        displayB(scoreB);
    }

}
