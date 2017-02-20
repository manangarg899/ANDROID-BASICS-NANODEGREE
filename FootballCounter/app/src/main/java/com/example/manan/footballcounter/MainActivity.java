package com.example.manan.footballcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.manan.footballcounter.R.id.team_A;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Let's play football!!", Toast.LENGTH_LONG ).show();

    }

    /**
     *All the variables that keeps track of all variable types of scores in football for both the teams
     *
     */

    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int shotsOnTarget_TeamA = 0;
    int shotsOnTarget_TeamB = 0;
    int cornersTeamA = 0;
    int cornersTeamB = 0;
    int offsidesTeamA = 0;
    int offsidesTeamB = 0;
    int foulsTeamA = 0;
    int foulsTeamB = 0;
    int freeKicksTeamA = 0;
    int freeKicksTeamB = 0;

    /**
     * When user clicks on begin match this function will be called..After user enters team names.
     * All the attribites of game score will appear and a buuton in front of them to increase the count..
     */
    public void beginMatch(View view)
    {
        EditText name_team_A = (EditText)findViewById(R.id.name_team_A);

        EditText name_team_B = (EditText)findViewById(R.id.name_team_B);

        TextView textView = (TextView)findViewById(team_A);

        TextView textView1 = (TextView)findViewById(R.id.team_B);

        if( name_team_A.getText().toString().trim().equals(""))
        {
            name_team_A.setError( "Field Required!" );
        }
        if( name_team_B.getText().toString().trim().equals(""))
        {
            name_team_B.setError( "Field Required!" );
        }
        else
        {
            textView.setText(name_team_A.getText().toString());
            textView1.setText(name_team_B.getText().toString());
            Toast.makeText(MainActivity.this,"Kick Off!!",Toast.LENGTH_LONG).show();
            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.container);
            linearLayout.setVisibility(View.VISIBLE);

            Button button1 =(Button)findViewById(R.id.end_button);
            button1.setVisibility(View.VISIBLE);

            Button button = (Button)findViewById(R.id.Start);
            button.setVisibility(View.GONE);
        }
    }

    /**
     * Whenever user clicks on any increment button corresponding function will be called and value of that counter will be increased..
     */

    public void goalTeamA(View view)
    {
        scoreTeamA = scoreTeamA + 1;
        TextView textView = (TextView)findViewById(R.id.goal_team_A);
        textView.setText(String.valueOf(scoreTeamA));

        TextView textView1 = (TextView)findViewById(R.id.score_team_A);
        textView1.setText(String.valueOf(scoreTeamA));

        Toast.makeText(MainActivity.this,"Goal!!",Toast.LENGTH_SHORT).show();
    }

    public void goalTeamB(View view)
    {
        scoreTeamB = scoreTeamB + 1;
        TextView textView = (TextView)findViewById(R.id.goal_team_B);
        textView.setText(String.valueOf(scoreTeamB));

        TextView textView5 = (TextView)findViewById(R.id.score_team_B);
        textView5.setText(String.valueOf(scoreTeamB));

        Toast.makeText(MainActivity.this,"Goal!!",Toast.LENGTH_SHORT).show();
    }

    public void shotsTeamA(View view)
    {
        shotsOnTarget_TeamA = shotsOnTarget_TeamA + 1;
        TextView textView = (TextView)findViewById(R.id.shots_team_A);
        textView.setText(String.valueOf(shotsOnTarget_TeamA));
    }

    public void shotsTeamB(View view)
    {
        shotsOnTarget_TeamB = shotsOnTarget_TeamB + 1;
        TextView textView = (TextView)findViewById(R.id.shots_team_B);
        textView.setText(String.valueOf(shotsOnTarget_TeamB));
    }

    public void cornerTeamA(View view)
    {
        cornersTeamA = cornersTeamA + 1;
        TextView textView = (TextView)findViewById(R.id.corners_team_A);
        textView.setText(String.valueOf(cornersTeamA));
    }

    public void cornerTeamB(View view)
    {
        cornersTeamB = cornersTeamB + 1;
        TextView textView = (TextView)findViewById(R.id.corners_team_B);
        textView.setText(String.valueOf(cornersTeamB));
    }

    public void offsideTeamA(View view)
    {
        offsidesTeamA = offsidesTeamA +1;
        TextView textView = (TextView)findViewById(R.id.offs_team_A);
        textView.setText(String.valueOf(offsidesTeamA));
    }

    public void offsideTeamB(View view)
    {
        offsidesTeamB = offsidesTeamB +1;
        TextView textView = (TextView)findViewById(R.id.offs_team_B);
        textView.setText(String.valueOf(offsidesTeamB));
    }

    public void foulsTeamA(View view)
    {
        foulsTeamA =foulsTeamA + 1;
        TextView textView = (TextView)findViewById(R.id.fouls_team_A);
        textView.setText(String.valueOf(foulsTeamA));
    }

    public void foulsTeamB(View view)
    {
        foulsTeamB =foulsTeamB + 1;
        TextView textView = (TextView)findViewById(R.id.fouls_team_B);
        textView.setText(String.valueOf(foulsTeamB));
    }

    public void freeKicksTeamA(View view)
    {
        freeKicksTeamA = freeKicksTeamA + 1;
        TextView textView = (TextView)findViewById(R.id.free_kick_team_A);
        textView.setText(String.valueOf(freeKicksTeamA));
    }

    public void freeKicksTeamB(View view)
    {
        freeKicksTeamB = freeKicksTeamB + 1;
        TextView textView = (TextView)findViewById(R.id.free_kick_team_B);
        textView.setText(String.valueOf(freeKicksTeamB));
    }

    /**
     * When the END button is clicked the match will be considered completed
     * And a winner will be announced by a toast message which will appear on the screen as soon as the match ends..
     */
    public void end_match(View view)
    {
        TextView teamA = (TextView)findViewById(R.id.team_A);
        TextView teamB = (TextView)findViewById(R.id.team_B);

        if( scoreTeamA > scoreTeamB)
        Toast.makeText(MainActivity.this,teamA.getText().toString() + " won!! ",Toast.LENGTH_LONG).show();
        else if (scoreTeamB > scoreTeamA)
        Toast.makeText(MainActivity.this,teamB.getText().toString()+ "won!! ",Toast.LENGTH_LONG).show();
        else
        Toast.makeText(MainActivity.this,"Tie!! ",Toast.LENGTH_LONG).show();

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.container);
        linearLayout.setVisibility(View.GONE);

        Button button = (Button)findViewById(R.id.end_button);
        button.setVisibility(View.GONE);

        Button button1 = (Button)findViewById(R.id.reset_button);
        button1.setVisibility(View.VISIBLE);

    }

    /**
     * When the reset button is clicked the scores and team names will be change to default
     * And user will be able to begin a new match..
     */

    public void reset(View view)
    {
        scoreTeamA = 0;
        scoreTeamB = 0;
        shotsOnTarget_TeamA = 0;
        shotsOnTarget_TeamB = 0;
        cornersTeamA = 0;
        cornersTeamB = 0;
        offsidesTeamA = 0;
        offsidesTeamB = 0;
        foulsTeamA = 0;
        foulsTeamB = 0;
        freeKicksTeamA = 0;
        freeKicksTeamB = 0;

        TextView textView = (TextView)findViewById(team_A);
        textView.setText("Team A");

        TextView textView1 = (TextView)findViewById(R.id.team_B);
        textView1.setText("Team B");

        TextView textView2 = (TextView)findViewById(R.id.goal_team_A);
        textView2.setText("0");

        TextView textView3 = (TextView)findViewById(R.id.score_team_A);
        textView3.setText("0");

        TextView textView4 = (TextView)findViewById(R.id.goal_team_B);
        textView4.setText("0");

        TextView textView5 = (TextView)findViewById(R.id.score_team_B);
        textView5.setText("0");

        TextView textView6 = (TextView)findViewById(R.id.shots_team_A);
        textView6.setText("0");

        TextView textView7 = (TextView)findViewById(R.id.shots_team_B);
        textView7.setText("0");

        TextView textView8 = (TextView)findViewById(R.id.corners_team_A);
        textView8.setText("0");

        TextView textView9 = (TextView)findViewById(R.id.corners_team_B);
        textView9.setText("0");

        TextView textView10 = (TextView)findViewById(R.id.offs_team_A);
        textView10.setText("0");

        TextView textView11 = (TextView)findViewById(R.id.offs_team_B);
        textView11.setText("0");

        TextView textView12 = (TextView)findViewById(R.id.fouls_team_A);
        textView12.setText("0");

        TextView textView13 = (TextView)findViewById(R.id.fouls_team_B);
        textView13.setText("0");

        TextView textView14 = (TextView)findViewById(R.id.free_kick_team_A);
        textView14.setText("0");

        TextView textView15 = (TextView)findViewById(R.id.free_kick_team_B);
        textView15.setText("0");

        EditText name_team_A = (EditText)findViewById(R.id.name_team_A);
        name_team_A.setText("");
        name_team_A.setHint("Name of Team A");

        EditText name_team_B = (EditText)findViewById(R.id.name_team_B);
        name_team_B.setText("");
        name_team_B.setHint("Name of Team B");

        Button button = (Button)findViewById(R.id.Start);
        button.setVisibility(View.VISIBLE);

        Button button1 = (Button)findViewById(R.id.reset_button);
        button1.setVisibility(View.GONE);

        Toast.makeText(MainActivity.this,"Let's play again!!",Toast.LENGTH_SHORT).show();
    }
}