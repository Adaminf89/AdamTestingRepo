/*Adam Infiesto
* Java1
* MainActivity*/

package com.example.adaminfiesto.infiesto_adam_ce08;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayList<String> list = new ArrayList<>();
    private EditText et;
    private String Temp;
    //var for keys
    private final static String Save_STRING = "MainActivity.STRING";
    private final static String Save_Key = "MainActivity.ListView";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView);
        et = findViewById(R.id.editTxt);
        findViewById(R.id.submitBtn).setOnClickListener(submitBtnClick);
        //reuse look for reload
        if(savedInstanceState != null)
        {
            list = savedInstanceState.getStringArrayList(Save_Key);
            et.setText(savedInstanceState.getString(Save_STRING));
            aaMehtod();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList(Save_Key, list);
        outState.putString(Save_STRING,Temp);

    }

    private final Button.OnClickListener submitBtnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v)
        {
                Temp = et.getText().toString().trim();

                if(Temp.isEmpty())
                {
                    Toast mToast = Toast.makeText(MainActivity.this, R.string.Toast, Toast.LENGTH_LONG);
                    mToast.show();
                }
                else
                {
                    list.add(Temp);
                    aaMehtod();

                }

        }

    };

    private void aaMehtod()
    {
        ArrayAdapter<String> aa = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        aa.setDropDownViewResource(android.R.layout.simple_list_item_1);
        lv.setAdapter(aa);
        et.setText("");
    }



}
