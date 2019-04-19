package com.example.adaminfiesto.adaminfiesto_ce03;
//Adam S Infiesto
//Term C20180200
//MainActivity.Java
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {


    private static final String TAG = "JAV3Project";
    //usertxt fields

    //holder for items
    private final ArrayList<String> userWords = new ArrayList<>();
    //make the toast = null for initial value so we can shut off toast later in methods
    private Toast daToast = null;
    NumberPicker numPicker;
    //temp vars for calculation
    double temp = 0.0;
    double tempMed = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //here we can set all the onclick listeners for the application
        findViewById(R.id.btnAdd).setOnClickListener(addClickLtn);
        findViewById(R.id.txtAdv).setOnClickListener(addClickLtn);
        findViewById(R.id.txtVMed).setOnClickListener(addClickLtn);
        findViewById(R.id.numberPicker).setOnClickListener(addClickLtn);
        findViewById(R.id.btn_View).setOnClickListener(viewClick);

    }

    private final View.OnClickListener addClickLtn = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            //set the fields
            TextView averageText = findViewById(R.id.txtAdv);
            TextView medianText = findViewById(R.id.txtVMed);
            EditText editText = findViewById(R.id.editTxt_1);
            numPicker = findViewById(R.id.numberPicker);
            
            //get the string from edittext field convert to string and trim spaces
            String userInput = editText.getText().toString().trim();

            //this will cancel the previous toast incase a new toast needs to appear
            if(daToast != null)
            {
                daToast.cancel();
            }

            //conditionally check if its emty
            if (userInput.isEmpty())
            {
                daToast = Toast.makeText(getApplicationContext(), R.string.toastValue, Toast.LENGTH_SHORT);
                daToast.show();
                return;
            }
            //check if the word already is in use
            else if(userWords.contains(userInput))
            {
                daToast = Toast.makeText(getApplicationContext(), R.string.toastValue2, Toast.LENGTH_SHORT);
                daToast.show();
                return;
            }
            //if all else fails then logically it should be a correct value we need to find the adverage for
            else
            {
                //what is being put into the
                Log.i(TAG, "onClick: " + userInput);

                //add to the arrayList of words
                userWords.add(userInput);

                //have to look at each word in the list
                Collections.sort(userWords, sorter);

                // Ref from https://stackoverflow.com/questions/11955728/how-to-calculate-the-median-of-an-array
                // Ref from https://stackoverflow.com/questions/41117879/problems-finding-median-of-arraylist
                //Is there no remander so even
                if(userWords.size()% 2 == 0)
                {

                    tempMed =((double)userWords.get(userWords.size()/2).length() + ((double)userWords.get((userWords.size()/2) - 1).length()))/2;
                    Log.i(TAG, "checking median temp prior: "+tempMed);

                }
                // this is going to be odd
                else if(userWords.size() == 1)
                {
                        tempMed = (double)userWords.get(0).length();
                }
                else
                {
                        tempMed = (double)userWords.get(userWords.size()/2).length();
                        Log.i(TAG, "checking median temp "+tempMed);
                }

                //sum to update the stuff
                temp = calculateAverage(userWords);
                //set it to the adverBox
                averageText.setText("" + temp);
                medianText.setText("" + tempMed);
                editText.setText("");

                //set picker
                numPicker.setMaxValue(userWords.size() - 1);
                numPicker.setMinValue(0);
                numPicker.setWrapSelectorWheel(false);
            }

        }
    };

    private final View.OnClickListener viewClick = new View.OnClickListener() {


        @Override
        public void onClick(View v)
        {

            numPicker = findViewById(R.id.numberPicker);
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

            if(!userWords.isEmpty())
            {

                builder.setTitle(R.string.tItle);
                builder.setMessage("" + userWords.get(numPicker.getValue()));
                builder.setPositiveButton(R.string.close, null);
                builder.setNegativeButton(R.string.Remove, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        numPicker = findViewById(R.id.numberPicker);
                        userWords.remove(numPicker.getValue());
                        numPicker.setDisplayedValues(null);
                        numPicker.setMaxValue(userWords.size() - 1);
                        numPicker.setWrapSelectorWheel(false);

                    }

                });

            }

            else
            {
                //here we need to show the users that the application has an error for some reason
                builder.setTitle(R.string.Error);
                builder.setMessage(R.string.ErrorMessage);
                builder.setPositiveButton(R.string.close, null);

                numPicker.setMaxValue(userWords.size());

            }
            //show the builder notification
            builder.show();
        }

    };

    //original code was referanced from to calculate adverages
    //https://stackoverflow.com/questions/10791568/calculating-average-of-an-array-list
    private double calculateAverage(List<String> Words)
    {
        Integer sum = 0;

        if(!Words.isEmpty())
        {
            for (String word : Words)
            {
                int charCounter = word.length();
                sum += charCounter;
            }
            return sum.doubleValue() / Words.size();
        }
        Log.i(TAG, "calculateAverage: "+sum.toString());
        return sum;
    }


    private Comparator<String> sorter = new Comparator<String>()
    {
        @Override
        public int compare(String o1, String o2) 
        {
            if(o1.length() > o2.length())
            {
                return 1;
            }

            else if(o1.length() < o2.length())
            {
                return -1;
            }
            return 0;
        }

    };




}
