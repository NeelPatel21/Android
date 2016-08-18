package mydice.dice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import java.awt.*;
import java.util.Random;

import static mydice.dice.R.drawable.*;

public class MainActivity extends AppCompatActivity {
    Button roll,reset,hold;
    TextView tuser,tcom;
    ImageView idice;
    boolean bu = false;
    int isuser=0,iscom=0;
    Random rn;
    final String suser="Your score:";
    final String scom="Computer score:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roll=(Button)findViewById(R.id.roll);
        reset=(Button)findViewById(R.id.reset);
        hold=(Button)findViewById(R.id.hold);
        tuser=(TextView)findViewById(R.id.tuser);
        tcom=(TextView)findViewById(R.id.tcom);
        idice=(ImageView)findViewById(R.id.dice);
        rn=new Random();
    }
    @Override
    protected void onStart(){
        isuser=0;
        iscom=0;
        super.onStart();
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=rn.nextInt(5)+1;
                if(i==1){
                    isuser=0;
                    updateDice(1);
                    updateScore(0);
                    turnCom();
                }else{
                    isuser+=i;
                    updateScore(isuser);
                    updateDice(i);
                    hold.setEnabled(true);
                }
            }
        });
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnCom();
            }
        });
        rset();
    }
    void rset(){
        tuser.setText(suser+'0');
        tcom.setText(scom+'0');
        if(rn.nextBoolean())
            turnUser();
        else
            turnCom();
    }
    void updateDice(int sc){
        switch(sc){
            case 1:idice.setImageResource(dice1);break;
            case 2:idice.setImageResource(dice2);break;
            case 3:idice.setImageResource(dice3);break;
            case 4:idice.setImageResource(dice4);break;
            case 5:idice.setImageResource(dice5);break;
            case 6:idice.setImageResource(dice6);break;
        }
    }
    void updateScore(int sc){
        if(bu)
            tuser.setText(suser+sc);
        else
            tcom.setText(scom+sc);
    }
    void turnUser(){
        bu=true;
        roll.setEnabled(true);
        hold.setEnabled(false);
    }
    void turnCom(){
        bu=false;
        roll.setEnabled(false);
        hold.setEnabled(false);
        for(;true;){
            int i = rn.nextInt();
            if (i == 1) {
                iscom = 0;
                updateDice(0);
                updateScore(0);
                turnUser();
            } else {
                iscom += i;
                updateScore(iscom);
                updateDice(iscom);
            }
            if (rn.nextBoolean()) {
                turnUser();
                break;
            }
        }
    }
}
