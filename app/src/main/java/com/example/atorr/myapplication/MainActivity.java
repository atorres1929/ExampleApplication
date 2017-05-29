package com.example.atorr.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Try remaking this app on your own once you've played with this one.
 *
 * Play with values, mess with different stuff, destroy it, rebuild it, whatever.
 *
 * Hope this helps you learn android a little. It'll at least point you in the direction of what to google.
 */

/**
 * THINGS TO GOOGLE: (You should always look for an answer that is as recent as possible, a lot of answers are outdated)
 * What is a view Android
 * What is the R class Android
 * What is an activity Android
 * What is a fragment Android -- I don't have fragments in this project, but they're important
 * What is strings.xml Android
 * What is colors.xml Android
 *
 * Really, you have to learn by doing, so if I were you, I'd just sit down and do it one day when you have time.
 */

/**
 * A couple things to note:
 *
 * An activity is the equivalent of a "window" in Windows.
 *
 * WE SHOULD use a naming convention, google "Android naming convention" to learn more
 *
 * The R class is the "Resources" class. it contains all the IDs of all the items you will
 * have in an app. Ctrl+Left Click on the R class, and you'll see where it takes you.
 * Don't worry about it's internals too much, it's basically just how we communicate between
 * Java and Xml
 *
 * All views are identified by their ID numbers stored in the R class. This is generated automagically by Android Studio
 * More on R class and views below. Also, google them (look for answers on stack overflow for TL;DR)
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    /**
     * All classes need a TAG for logging purposes, There are several different Logs you can do.
     * Log.i = Information Log
     * Log.d = Debug log
     * Log.e = Error log
     * Log.wtf = THIS SHOULD NEVER HAPPEN LOG -- probably unprofessional to use this
     * maybe a few others, but a forget them now.
     *
     * Log.i(TAG, "Andres is dumb") <--- Example of proper use of a tag
     */
    public static final String TAG = "MainActivity";
    private Button red;
    private Button green;
    private Button blue;
    private TextView textView;
    private ConstraintLayout layout;


    /**
     * This method will be used every time the activity is created. Think of it as kind of like a constructor.
     *
     * To be clear, you CAN use a constructor to initialize values, however if they are not in the onCreate method,
     * then the class will probably not properly make them.
     *
     * Look for a flow chart online on how android handles activities.
     *
     * The gist of it is that onCreate can be called multiple times to recreate the Activity, however,
     * the constructor is only called when an instance of the class itself is created.
     *
     * I know that might be confusing
     *
     * TL;DR just use onCreate as your constructor.
     *
     * @param savedInstanceState This parameter is used to carry data between activities.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * The findViewByID() method is something you'll use a lot.
         * TL;DR it finds a view by ID in the R class to reference the Xml to Java so you can
         * code functionality of whatever is contained within that View.
         */
        red = (Button) findViewById(R.id.redButton);
        green = (Button) findViewById(R.id.greenButton);
        blue = (Button) findViewById(R.id.blueButton);

        /**
         * Notice I set the onClickListener for all views, yes, it is necessary to do this. Otherwise,
         * when you click them, nothing will happen.
         */
        red.setOnClickListener(this);
        green.setOnClickListener(this);
        blue.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(this);

        layout = (ConstraintLayout) findViewById(R.id.layout);
    }


    /**
     * This is used to create the 3 vertical dots in the top right of the screen which we generally
     * call the settings menu. The gist of it is, there is a layout, that is like a deflated bag. (Nothing in it)
     * To make it have stuff, we have to use an inflater.
     *
     * Once we inflate the bag, (or inflate the menu) it will have the menu we want.
     *
     * No Menu -> Inflate -> Menu
     *
     * @param menu  the menu to be inflated
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();  //Get this class's menu inflater
        inflater.inflate(R.menu.main, menu);        //Inflate the menu located in res/menu/main.xml
        return true;                                //return true indicates we want the menu to be shown
                                                    //return false indicates we want the menu to NOT be shown
    }


    /**
     * The equivalent of an onClick method for all the menu items.
     *
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mnu_main_DayMode:
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);   //I googled this code
                return true;
            case R.id.mnu_main_NightMode:
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);  //I googled this code
                return true;

            case R.id.mnu_main_About:
                //An intent basically says "I am this class, and I want that activity, and I want that activity to activate with XYZ data
                //In this case, we're not starting a new activity, but opening our website up in whatever the default browser is for the phone
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.datahelix.info"));
                startActivity(browserIntent);   //starts an activity from this class with whatever intent
                return true;

            default:
                Log.e(TAG, "Unknown menu item was pressed!");   //Properly logging if a menu item was clicked, and it was not accounted for in the switch case
                return super.onOptionsItemSelected(item);       //We refer to the super class's method on what to do in this case
        }
    }

    /**
     * An on click method for anything in the class that can be clicked.
     *
     * YOU NEED TO "implement View.OnClickListener" IN ORDER TO USE THIS METHOD!!!!!!!!!
     *
     *
     * @param v The view which as been clicked (A view is any kind of viewable thing in an app)
     *          ((.png, and .jpg are not views, they are properties OF a view)
     *          (for example, a button may contain a .png, the button is the view, not the png).
     *          (you would still see the button without the .png)
     *          (horrible parenthesis that I'm not going to fix)
     */
    @Override
    public void onClick(View v) {

        int id = v.getId(); //All views are identified by their ID numbers stored in the R class. This is generated automagically by Android Studio


        //I think the rest of this code is fairly obvious.
        //Setting background colors
        //referencing R class to get colors and strings
        if (id == red.getId()){
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
            textView.setText(R.string.red);
        }
        else if (id == green.getId()){
            layout.setBackgroundColor(Color.GREEN);
            textView.setText(R.string.green);
        }
        else if (id == blue.getId()) {
            layout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_bright));
            textView.setText(R.string.blue);
        }
        else if (id == textView.getId()) {
            textView.setText(R.string.surpise);
        }
        else {
            Log.wtf(TAG, "you don fucked up son");
        }

    }
}
