package com.example.manan.logicalreasoning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Whenever this button is clicked all the answers will be checked and a toast will appear showing the score...
     */
    public void submit(View view)
    {
        RadioButton R1 = (RadioButton)findViewById(R.id.q1_ans);
        if(R1.isChecked())
            score++;

        RadioButton R2 = (RadioButton)findViewById(R.id.q2_ans);
        if(R2.isChecked())
            score++;

        RadioButton R3 = (RadioButton)findViewById(R.id.q3_ans);
        if(R3.isChecked())
            score++;

        CheckBox C4_1 = (CheckBox)findViewById(R.id.q4_1);

        CheckBox C4_3 = (CheckBox)findViewById(R.id.q4_3);

        CheckBox C4_2 = (CheckBox)findViewById(R.id.q4_2);

        CheckBox C4_4 = (CheckBox)findViewById(R.id.q4_4);

        if(C4_1.isChecked() && C4_3.isChecked() && !C4_2.isChecked() && ! C4_4.isChecked())
            score++;

        CheckBox C5_1 = (CheckBox)findViewById(R.id.q5_1);

        CheckBox C5_2 = (CheckBox)findViewById(R.id.q5_2);

        CheckBox C5_3 = (CheckBox)findViewById(R.id.q5_3);

        CheckBox C5_4 = (CheckBox)findViewById(R.id.q5_4);

        if(C5_1.isChecked() && C5_4.isChecked() && !C5_2.isChecked() && !C5_3.isChecked())
            score++;

        EditText E6 = (EditText)findViewById(R.id.q6_ans);
        if(E6.getText().toString().toLowerCase().equals("flags"))
            score++;

        EditText E7 = (EditText)findViewById(R.id.q7_ans);
        if(E7.getText().toString().toLowerCase().equals("lithium"))
            score++;

        EditText E8 = (EditText)findViewById(R.id.q8_ans);
        if(E8.getText().toString().toLowerCase().equals("jackson"))
            score++;

        if(score == 8)
            Toast.makeText(MainActivity.this,score+" out of 8 are correct!! Great Job",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this,score+" out of 8 are correct!!",Toast.LENGTH_SHORT).show();

        score = 0;
    }

    /**
     * Whenever Reset button is clicked all the answers will be back to defaults..
     */

    public void reset(View view)
    {
        RadioGroup rg1 = (RadioGroup)findViewById(R.id.rg1);
        rg1.clearCheck();

        RadioGroup rg2 = (RadioGroup)findViewById(R.id.rg2);
        rg2.clearCheck();

        RadioGroup rg3 = (RadioGroup)findViewById(R.id.rg3);
        rg3.clearCheck();

        EditText E6 = (EditText)findViewById(R.id.q6_ans);

        EditText E7 = (EditText)findViewById(R.id.q7_ans);

        EditText E8 = (EditText)findViewById(R.id.q8_ans);

        E6.setText("");

        E7.setText("");

        E8.setText("");

        CheckBox C4_1 = (CheckBox)findViewById(R.id.q4_1);

        CheckBox C4_3 = (CheckBox)findViewById(R.id.q4_3);

        CheckBox C4_2 = (CheckBox)findViewById(R.id.q4_2);

        CheckBox C4_4 = (CheckBox)findViewById(R.id.q4_4);

        CheckBox C5_1 = (CheckBox)findViewById(R.id.q5_1);

        CheckBox C5_2 = (CheckBox)findViewById(R.id.q5_2);

        CheckBox C5_3 = (CheckBox)findViewById(R.id.q5_3);

        CheckBox C5_4 = (CheckBox)findViewById(R.id.q5_4);

        C4_1.setChecked(false);

        C4_2.setChecked(false);

        C4_3.setChecked(false);

        C4_4.setChecked(false);

        C5_1.setChecked(false);

        C5_2.setChecked(false);

        C5_3.setChecked(false);

        C5_4.setChecked(false);

        score = 0;

        Toast.makeText(MainActivity.this,"Thank you for Playing!!",Toast.LENGTH_LONG).show();
    }

}