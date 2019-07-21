package racedirector.com;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //attrabutes
    TextView diceText;
    //Button rollButton;
    TextView scoreText;
    //Fields for images


    //Field to hold the Score
    int score;
    int randomNum;
    //Generate a random number
    Random rand;
    int die;
    int die1;
    int die2;
    int die3;
    int scoreTriples=200;
    int scoreDoubles=50;
    String scoreT;
    String diceRoll;

    ArrayList<Integer> Dice;
    /* arraylist for images */
    ArrayList<ImageView> diceImageView;
    int trackClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickMe(view);
            }
        });
        //where to intilize field variables
        randomNum=0;
        trackClick=0;
        //send a brief message called a toast, show will display the toast message
        Toast.makeText(getApplicationContext(),"Welcome to Race Director",Toast.LENGTH_SHORT).show();
        //link an instance to the widget in our activty layout
        diceText=findViewById(R.id.diceText);
        //rollButton=findViewById(R.id.rollButton);
        scoreText=findViewById(R.id.scoreText);
        //Initialize the random object
        rand= new Random();

        Dice=new ArrayList<Integer>();
        ImageView die1ImageView=(ImageView)findViewById(R.id.die1Image);
        ImageView die2ImageView=(ImageView)findViewById(R.id.die2Image);
        ImageView die3ImageView=(ImageView)findViewById(R.id.die3Image);
        diceImageView= new ArrayList<ImageView>();
        diceImageView.add(die1ImageView);
        diceImageView.add(die2ImageView);
        diceImageView.add(die3ImageView);
    }

    //increase the amount show on the screen
    public void clickMe(View v){
    //die1=rand.nextInt(6)+1;
    //die2=rand.nextInt(6)+1;
    //die3=rand.nextInt(6)+1;
    //Dice.clear();

    //Dice.add(die1);
    //Dice.add(die2);
    //Dice.add(die3);


    //Conditional to check the amounts of clicks is no more that the array size of Dice
        //using the amount of clicks the user used
    if(trackClick<=2) {
       // dynamicRoll(trackClick);
        die=rand.nextInt(6)+1;
        Dice.add(die);
        //Dice.set(trackClick,rand.nextInt(6)+1);
        System.out.println("Got dice: "+Dice.get(trackClick));
        diceRoll(trackClick,Dice.get(trackClick));//track the amount of clicks, to access the
        //imageview Array, Dice.get(trackClick) to get the valued rolled
    }else{
        diceScore(Dice.get(0),Dice.get(1),Dice.get(2));
        Dice.clear();
        resetDieImages();
    }


    //*
    //orignal roll of dice, roll all three dice at the same time
    //for(int dieOfSet=0;dieOfSet<3;dieOfSet++){
      //  String imageName="die_"+Dice.get(dieOfSet)+".png";
      //  try{
         //   InputStream stream = getAssets().open(imageName);
        //    Drawable d=Drawable.createFromStream(stream,null);
        //    diceImageView.get(dieOfSet).setImageDrawable(d);
       // }catch(IOException e){
       //     e.printStackTrace();
       // }
    //}
    //*/
    //diceText.setText(diceRoll);
    //scoreText.setText(scoreT);
    //String randomNumGen=("Value"+randomNum);
    //toast can only deal with string values and numerical values
        //are not accepted
    //Toast.makeText(getApplicationContext(),Integer.toString(randomNum),Toast.LENGTH_SHORT).show();
    }




    public void resetDieImages(){

        for(int i=0;i<=2;i++ ) {
            String imageName;

            imageName = "die_" + 1 + ".png";
            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                diceImageView.get(i).setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void diceRoll(int dieValue,int dice){
        String imageName;

                    imageName = "die_" + dice + ".png";
                    try {
                        InputStream stream = getAssets().open(imageName);
                        Drawable d = Drawable.createFromStream(stream, null);
                        diceImageView.get(dieValue).setImageDrawable(d);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    int diceNum=trackClick+1;
                    diceRoll = "Dice " + diceNum + ":" + dice + " Click to roll the next dice";
                    diceText.setText(diceRoll);
                    trackClick++;




                //return diceRoll;

    }

    public void diceScore(int die1, int die2, int die3){

        System.out.println("score sees"+die1+", "+die2+","+die3);
        if(die1 == die2 && die1 == die3){
            //triples
            diceRoll="You rolled a Triple! points added: " +scoreTriples;
            score+=scoreTriples;
            System.out.println("score sees for triples"+die1+", "+die2+","+die3);
            diceText.setText(diceRoll);
            scoreT="Score: "+score;
            scoreText.setText(scoreT);
        }else if(die1 == die2 || die1 ==die3 || die2 == die3){
            diceRoll="You rolled a double! points added "+scoreDoubles;
            score+=scoreDoubles;
            System.out.println("score sees for doubles"+die1+", "+die2+","+die3);
            diceText.setText(diceRoll);
            scoreT="Score: "+score;
            scoreText.setText(scoreT);
        }else{
            diceRoll="You scored nada, try again: "+die1+","+die2+","+die3;
            diceText.setText(diceRoll);
        }

            trackClick=0;


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

}
