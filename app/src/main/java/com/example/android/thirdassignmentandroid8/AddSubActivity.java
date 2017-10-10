package com.example.android.thirdassignmentandroid8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
  Code developed for Android class showing passing of data between activities
  This activity has an add/subtract function and 2 ways to get to the random Activity
  One through a menu, this passed the count value to random
  The other is through a button that calls random for a result
  Bill Thomas 10/10/17
 */


public class AddSubActivity extends AppCompatActivity implements View.OnClickListener{

    static final String COUNT = "count";
    static final String RANDOM = "random";
    static final int RANDOM_NUMBER_REQUEST = 1;
    TextView mainNum;
    Button buttonAdd;
    Button buttonSub;
    Button buttonGet;
    int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainNum = (TextView)(findViewById(R.id.text_total_num));

        // the three buttons we need to complete the assignment
        buttonAdd = (Button)findViewById(R.id.butAdd);
        buttonSub = (Button)findViewById(R.id.butSub);
        buttonGet = (Button)findViewById(R.id.butGet);

        //we implemented View.onClickListener above so we can use "this"
        buttonAdd.setOnClickListener(this);
        buttonSub.setOnClickListener(this);
        buttonGet.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_sub, menu);
        return true;
    }
    @Override
    public void onClick(View v){

        //set-up a switch statement since we are handling all the buttons here
        switch (v.getId()) {
            case R.id.butAdd:
                counter++;
                mainNum.setText(String.valueOf(counter));
                break;
            case R.id.butSub:
                counter--;
                mainNum.setText(String.valueOf(counter));
                break;
            case R.id.butGet:
                Intent intent = new Intent(this, RandomActivity.class);
                startActivityForResult(intent, RANDOM_NUMBER_REQUEST);
                break;
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.random:
                Intent intent = new Intent(getApplicationContext(),RandomActivity.class);
                intent.putExtra(COUNT, counter);  //send the current counter to the second Activity
                startActivity(intent);
                return true;

            default:
                return true;
        }

     //   return super.onOptionsItemSelected(item);
    }

    //this will handle the return call asking for a value only works when the get button passes us to Random
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RANDOM_NUMBER_REQUEST) {
            if(resultCode == RESULT_OK) {

                //received back the random value
                counter = data.getIntExtra(RANDOM, 0);
                mainNum.setText(String.valueOf(counter));

                //Just put this in for testing purposes so I know when this method is the called method
                //Remove before production
                Context context = getApplicationContext();
                CharSequence text = "Called Back with return value!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }
}
