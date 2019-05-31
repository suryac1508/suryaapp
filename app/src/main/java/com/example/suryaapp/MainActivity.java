// this is our own package and is  created automatically when new project is created
package com.example.suryaapp;
// we import required modules based upon our requirements 
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
// mainly here we create four functions starttimer, pausetimer, update time,reset time.
public class MainActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 600000;
//assigning the variables
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private ImageView mImageView;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//we are giving access to our xml file

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        // assigning the variable to the views present in xml file we use find viewbyid function to get the particular view id

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);
        mImageView=findViewById(R.id.imageView);
        // setonclicklistener is used to detect whether the button is pressed or not
// under we are assigning that when start button is pressed we start the timer
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
//we assign when ever the reset button is pressed it goes to the resettimer function written under
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
    }
//from here we observe four functions that helps our main app
    //when ever the start button is pressed this function starts functioning
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
        mImageView.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        /*here we are making our button and images as visible in xml file
        we give to particular view instead of android:visibility we give tools:visibility
        such that we can make the views visible or invisible;
        */
        mButtonReset.setVisibility(View.VISIBLE);
        mImageView.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        /*here we are making our button and images as visible in xml file
        we give to particular view instead of android:visibility we give tools:visibility
        such that we can make the views visible or invisible;
        */
        mButtonStartPause.setVisibility(View.VISIBLE);
        mImageView.setVisibility(View.INVISIBLE);
    }
//here comes our main function that sets the timers simple algorithm is used to change the timer
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
//we use format to replace the minutes and seconds with the new ones or the updated ones instantly.
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }
}