package com.example.adaminfiesto.infiestoadam_ce02;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;


//Adam S Infiesto
//Term C20180200
//MainActivity.Java

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "JAV1Project";
    //user text fields
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;

    //only doing number 0-9
    int [] possibleNum = {0,1,2,3,4,5,6,7,8,9};

    public int colorValueR = 0;
    public int colorValueG = 0;
    public int colorValueB = 0;
    int userValues1 = 0;
    int userValues2 = 0;
    int userValues3 = 0;
    int userValues4 = 0;

    int gTurns = 4;
    //check to see if hitting
    int chickenDinner = 0;
    //need someplace  for answers?
    int aws1 = 0;
    int aws2 = 0;
    int aws3 = 0;
    int aws4 = 0;
    Toast daToast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnGuess).setOnClickListener(checkGameListener);
        editText1 = findViewById(R.id.editText_num1);
        editText2 = findViewById(R.id.editText_num2);
        editText3 = findViewById(R.id.editText_num3);
        editText4 = findViewById(R.id.editText_num4);
        //shuffle the array of numbers
        shuffleArray(possibleNum);

        //what answers are
         aws1 = possibleNum[0];
         aws2 = possibleNum[1];
         aws3 = possibleNum[2];
         aws4 = possibleNum[3];

        //RGB values from Color Resource Folder in project
        colorValueR = getColor(R.color.colorRed);
        colorValueG = getColor(R.color.colorGreen);
        colorValueB = getColor(R.color.colorBlue);

    }

    private final View.OnClickListener checkGameListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            //getting text
            String uInput1 = editText1.getText().toString();
            String uInput2 = editText2.getText().toString();
            String uInput3 = editText3.getText().toString();
            String uInput4 = editText4.getText().toString();

            if(daToast != null)
            {
                daToast.cancel();
            }
            //check if "ANY" not "ALL" userInput are empty
            if (!uInput1.isEmpty() | uInput2.isEmpty() | uInput3.isEmpty() | uInput4.isEmpty())
            {
                try
                {

                    userValues1 = Integer.parseInt(uInput1);
                    userValues2 = Integer.parseInt(uInput2);
                    userValues3 = Integer.parseInt(uInput3);
                    userValues4 = Integer.parseInt(uInput4);
                }
                catch (NumberFormatException e)
                {
                    daToast = Toast.makeText(getApplicationContext(), "No Black Spaces", Toast.LENGTH_SHORT);
                    daToast.show();
                    return;
                }
                catch (Exception e)
                {
                    daToast = Toast.makeText(getApplicationContext(), "No Black Spaces", Toast.LENGTH_SHORT);
                    daToast.show();

                    Log.i(TAG, "selected number " + uInput1);
                    e.printStackTrace();
                    return;
                }
            }

            if(!editText1.isEnabled() & !editText2.isEnabled() & !editText3.isEnabled() & !editText4.isEnabled() )
            {
                findViewById(R.id.btnGuess).setOnClickListener(wonGameListener);
            }
            else if(gTurns == 1)
            {
                findViewById(R.id.btnGuess).setOnClickListener(lossGameListener);
            }

            if (gTurns >= 0)
            {
                //log values to test if u have the right numbers
                Log.d(TAG, "answers "+aws1+","+aws2+","+aws3+","+aws4);

                //check the values with helper methods
                checkerMethod(userValues1, aws1, editText1);
                checkerMethod(userValues2, aws2, editText2);
                checkerMethod(userValues3, aws3, editText3);
                checkerMethod(userValues4, aws4, editText4);

                //subtract turns so that the users will have a limit
                gTurns -= 1;
                //let them know how many turns they have
                String toast = Integer.toString(gTurns);
                Log.i(TAG, "Count"+chickenDinner);
                daToast = Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT);
                daToast.show();
            }
        }

    };


    //listeners for when the game is won or loss
    private final View.OnClickListener lossGameListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle(R.string.dtitleL);
                builder.setMessage(R.string.dmsgL);

                builder.setPositiveButton(R.string.dbtnL, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recreate();
                    }
                });

            builder.show();
        }
    };

    private final View.OnClickListener wonGameListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(R.string.dtitleW);
            builder.setMessage(R.string.dmsgW);
            builder.setPositiveButton(R.string.dbtnL, new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    recreate();
                }
            });
            builder.show();
        }
    };

    private void checkerMethod(Integer userValues, Integer gameValues, EditText fieldToEdit)
    {
        //if the uservalue is above the  gamesvalue
        if (userValues > gameValues)
        {
            fieldToEdit.setTextColor(colorValueR);
        }
        //if the uservalue is below the games value
        else if (userValues < gameValues)
        {
            fieldToEdit.setTextColor(colorValueB);
        }
        else if(userValues.intValue() == gameValues.intValue())
        {
            fieldToEdit.setTextColor(colorValueG);
            fieldToEdit.setEnabled(false);
            //keep count
            chickenDinner +=1;
        }
    }

    private static void shuffleArray(int[] ar)
    {
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
