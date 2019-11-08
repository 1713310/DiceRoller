package com.example.diceroller;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.lang.Integer.*;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    int randNo;
    int diceNum;
    int points;
    int userInput;
    TextView displayResult;
    EditText editText;
    TextView pointsBox;
    TextView displayQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pointsBox = this.findViewById(R.id.showPoints);
        editText = this.findViewById(R.id.txtBox);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void guessDice() {
        pointsBox.setVisibility(View.VISIBLE);
        editText.setVisibility(View.VISIBLE);

        displayResult = this.findViewById(R.id.showMsg);

        // calling random function and storing in variable
        diceNum = roll_the_dice();

        // converting editable to integer
        userInput = Integer.parseInt(editText.getText().toString());;

        if (userInput == diceNum) {
            // adds one point
            points = points + 1;
            displayResult.setText("Dice: "+Integer.toString(diceNum));
            // pop up message, disappears after a few seconds
            Toast.makeText(MainActivity.this,"Congratulations!",Toast.LENGTH_SHORT).show();
            // display new points count
            pointsBox.setText("Points: "+Integer.toString(points)); // converting to string variable
        }
        else {
            displayResult.setText("Dice: "+Integer.toString(diceNum));
            pointsBox.setText("Points: "+Integer.toString(points));
            Toast.makeText(MainActivity.this,"Try again!",Toast.LENGTH_SHORT).show();
        }
    }

    public void diceBreaker() {

        pointsBox.setVisibility(View.INVISIBLE);
        editText.setVisibility(View.INVISIBLE);

        displayQ = this.findViewById(R.id.showMsg);
        randNo = roll_the_dice();

        ArrayList<String> iceBreakersList = new ArrayList<String>();

        iceBreakersList.add("If you could go anywhere in the world, where would you go?");
        iceBreakersList.add("If you were stranded on a desert island, what three things would you want to take with you?");
        iceBreakersList.add("If you could eat only one food for the rest of your life, what would that be?");
        iceBreakersList.add("If you won a million dollars, what is the first thing you would buy?");
        iceBreakersList.add("If you could spend the day with one fictional character, who would it be?");
        iceBreakersList.add("If you found a magic lantern and a genie gave you three wishes, what would you wish?");

        Collections.shuffle(iceBreakersList); // shuffles elements in arraylist
        displayQ.setText(iceBreakersList.get(randNo));
    }

    public static int roll_the_dice() {
        Random rand = new Random();
        int num = rand.nextInt(6)+1;
        return num;
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnDiceRoller:
                guessDice();
                break;
            case R.id.btnIceBreakers:
                diceBreaker();
                break;
            default:
                break;
        }
    }
}
