package com.example.adaminfiesto.adaminfiesto_ce01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

//Adam S Infiesto
//Term C20180200
//MainActivity.Java


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    static final String TAG = "JAV1Project";
    //btn items for shuffle
    int [] btn =
            {
            R.id.button1,R.id.button2,
            R.id.button3,R.id.button4,
            R.id.button5,R.id.button6,
            R.id.button7,R.id.button8,
            R.id.button9,R.id.button10,
            R.id.button11,R.id.button12,
            R.id.button13,R.id.button14,
            R.id.button15,R.id.button16
    };

    private TextView countAtt = null;

    int btnClickCount = 0;
    int fID = 0;
    int guessAtt = 0;
    int gameOver = 0;
    String fClick = "";
    String sClick = "";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        shuffleArray(btn);
        setContentView(R.layout.activity_main);

        countAtt = findViewById(R.id.tv_Game);
        //assignbtns to ids sfuffled
        Button btn1 = findViewById(btn[0]);
        Button btn2 = findViewById(btn[1]);
        Button btn3 = findViewById(btn[2]);
        Button btn4 = findViewById(btn[3]);
        Button btn5 = findViewById(btn[4]);
        Button btn6 = findViewById(btn[5]);
        Button btn7 = findViewById(btn[6]);
        Button btn8 = findViewById(btn[7]);
        Button btn9 = findViewById(btn[8]);
        Button btn10 = findViewById(btn[9]);
        Button btn11 = findViewById(btn[10]);
        Button btn12 = findViewById(btn[11]);
        Button btn13 = findViewById(btn[12]);
        Button btn14 = findViewById(btn[13]);
        Button btn15 = findViewById(btn[14]);
        Button btn16 = findViewById(btn[15]);
        Button btn17 = findViewById(R.id.button18);
        Log.d(TAG, "button ID: "+btn[0]);
        //get text
        btn1.setText(R.string.btn_01);
        btn2.setText(R.string.btn_02);
        btn3.setText(R.string.btn_03);
        btn4.setText(R.string.btn_04);
        btn5.setText(R.string.btn_05);
        btn6.setText(R.string.btn_06);
        btn7.setText(R.string.btn_07);
        btn8.setText(R.string.btn_08);
        btn9.setText(R.string.btn_09);
        btn10.setText(R.string.btn_10);
        btn11.setText(R.string.btn_11);
        btn12.setText(R.string.btn_12);
        btn13.setText(R.string.btn_13);
        btn14.setText(R.string.btn_14);
        btn15.setText(R.string.btn_15);
        btn16.setText(R.string.btn_16);
        btn17.setText(R.string.btn18);

        //set listener and make size small so users cant see
        btn1.setOnClickListener(this);
        btn1.setTextSize(0);

        btn2.setOnClickListener(this);
        btn2.setTextSize(0);

        btn3.setOnClickListener(this);
        btn3.setTextSize(0);

        btn4.setOnClickListener(this);
        btn4.setTextSize(0);

        btn5.setOnClickListener(this);
        btn5.setTextSize(0);

        btn6.setOnClickListener(this);
        btn6.setTextSize(0);

        btn7.setOnClickListener(this);
        btn7.setTextSize(0);

        btn8.setOnClickListener(this);
        btn8.setTextSize(0);

        btn9.setOnClickListener(this);
        btn9.setTextSize(0);

        btn10.setOnClickListener(this);
        btn10.setTextSize(0);

        btn11.setOnClickListener(this);
        btn11.setTextSize(0);

        btn12.setOnClickListener(this);
        btn12.setTextSize(0);

        btn13.setOnClickListener(this);
        btn13.setTextSize(0);

        btn14.setOnClickListener(this);
        btn14.setTextSize(0);

        btn15.setOnClickListener(this);
        btn15.setTextSize(0);

        btn16.setOnClickListener(this);
        btn16.setTextSize(0);

       //reset btn
        btn17.setOnClickListener(this);
        btn17.setVisibility(View.INVISIBLE);


    }



    //im 100% sure im not suppose to be doing it this way
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.button1:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    fID = R.id.button1;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();

                }
                else if(btnClickCount == 1)
                {

                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button1);
                    temp2.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                        {
                            //make enabled
                            temp.setEnabled(true);
                            fClick = "";
                            sClick = "";
                            guessAtt += 1;
                            temp.setTextSize(0);
                            temp2.setTextSize(0);
                            countAtt.setText(String.valueOf(guessAtt));

                        }
                        //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;

                }
                break;

            case R.id.button2:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    //fClick = v.toString();
                    fID = R.id.button2;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();

                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button2);
                    temp2.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt += 1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;

            case R.id.button3:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    //fClick = v.toString();
                    fID = R.id.button3;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();

                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button3);
                    temp2.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt +=1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;

            case R.id.button4:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    //fClick = v.toString();
                    fID = R.id.button4;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();
                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button4);
                    temp2.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt +=1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;

            case R.id.button5:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                   // fClick = v.toString();
                    fID = R.id.button5;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();
                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button5);
                    temp2.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt +=1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;

            case R.id.button6:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    //fClick = v.toString();
                    fID = R.id.button6;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();
                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button6);
                    temp2.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt +=1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;

            case R.id.button7:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    //fClick = v.toString();
                    fID = R.id.button7;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();
                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button7);
                    temp2.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt +=1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;

            case R.id.button8:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    fID = R.id.button8;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();
                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button8);
                    temp.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt +=1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;

            case R.id.button9:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    fID = R.id.button9;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();
                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button9);
                    temp2.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt +=1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;
            case R.id.button10:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    fID = R.id.button10;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();
                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button10);
                    temp2.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt +=1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;

            case R.id.button11:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    fID = R.id.button11;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();
                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button11);
                    temp2.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt +=1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;

            case R.id.button12:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    fID = R.id.button12;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();
                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button12);
                    temp.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt +=1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;

            case R.id.button13:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    fID = R.id.button13;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();
                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button13);
                    temp2.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt +=1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;
            case R.id.button14:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    fID = R.id.button14;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();
                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button14);
                    temp2.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt +=1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;
            case R.id.button15:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    fID = R.id.button15;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();
                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button15);
                    temp2.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt +=1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;
            case R.id.button16:
                if(btnClickCount == 0)
                {
                    btnClickCount += 1;
                    v.setEnabled(false);
                    fID = R.id.button16;
                    Button temp = findViewById(fID);
                    temp.setTextSize(24);
                    fClick = temp.getText().toString();
                }
                else if(btnClickCount == 1)
                {
                    //use this btn id to find then set invis
                    Button temp = findViewById(fID);
                    //store this clicksvalue
                    Button temp2 = findViewById(R.id.button16);
                    temp2.setTextSize(24);
                    sClick = temp2.getText().toString();

                    if (fClick.equals(sClick))
                    {
                        //set current click to invisible
                        v.setVisibility(View.INVISIBLE);
                        temp.setVisibility(View.INVISIBLE);
                        gameOver +=1;
                    }
                    else
                    {
                        //make enabled
                        temp.setEnabled(true);
                        fClick = "";
                        sClick = "";
                        temp.setTextSize(0);
                        temp2.setTextSize(0);
                        guessAtt +=1;
                        countAtt.setText(String.valueOf(guessAtt));
                    }
                    //put id to blank n turncount to blank
                    fID = 0;
                    btnClickCount = 0;
                }
                break;
            case R.id.button18:

               recreate();
                break;
            default:
                break;
        }
        if (gameOver == 8)
        {
            Button temp3 = findViewById(R.id.button18);
            temp3.setVisibility(View.VISIBLE);
        }


    }



    static void shuffleArray(int[] ar) {

        Random someNumber = new Random();

        for (int i = ar.length - 1; i > 0; i--)
        {

            Log.d(TAG, "Turn Number: "+i);
            int index = someNumber.nextInt(i + 1);
            if (i == index)
            {
                ++i;
            }
            else
                {
                int a = ar[index];
                ar[index] = ar[i];
                ar[i] = a;
            }
        }
    }
}

