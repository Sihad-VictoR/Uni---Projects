package iit.l5.tut.autospy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class IdentifyTheCarMakeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    private boolean timer;
    private TextView timerTextView;
    private CountDownTimer CountDownTimer;
    private TextView result;
    private TextView answer;
    private final Random random = new Random();
    private String[] carArray;
    private String carName;
    private String resource;
    private Button btn1;
    private ImageView carMakeImageView;
    private String spinnerLabel;
    private Long timerChangedValue1;

    private int resource_id;
    ScaleGestureDetector scaleGestureDetector;
    float scaleFactor=1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_car_make);

        //using intent to get the value for timer
        Intent intent = getIntent();
        timer = intent.getBooleanExtra(HomeActivity.EXTRA_MESSAGE,false);

        timerTextView = (TextView) findViewById(R.id.carMakeActivityTimerView);
        result = (TextView) findViewById(R.id.carMakeActivityResultView);
        answer = (TextView) findViewById(R.id.carMakeActivityAnswerView);
        btn1 = (Button) findViewById(R.id.carMakeActivityIdentifyBtn);
        carMakeImageView =(ImageView) findViewById(R.id.carMakeActivityImageView);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.timer_music);

        timerChangedValue1 = 21000L;
        spinnerLabel ="bmw";
        //Implementing Spinner
        Spinner spinner = findViewById(R.id.carMakeActivitySpinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.carName_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

        //Checking whether there are any saved instances before loading up new Activity.
        //Used for Landscape Rotations.
        if(savedInstanceState == null){
            setImage(carMakeImageView);
        }else {
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if (isVisible) {
                carMakeImageView.setVisibility(View.VISIBLE);
                btn1.setText(savedInstanceState.getCharSequence("reply_text"+"btn"));
                answer.setText(savedInstanceState.getCharSequence("reply_text"+"answer"));
                carMakeImageView.setImageResource(savedInstanceState.getInt("reply_text"+"image"));
                carName = savedInstanceState.getString("reply_text"+"value");
                result.setTextColor(savedInstanceState.getInt("reply_text"+"resultColor"));
                result.setText(savedInstanceState.getCharSequence("reply_text"+"result"));
                resource_id = savedInstanceState.getInt("reply_text"+"image");
                timerChangedValue1 = savedInstanceState.getLong("reply_text"+"time");
                spinnerLabel =savedInstanceState.getString("reply_text"+"spinner");
                timerTextView.setText(savedInstanceState.getCharSequence("reply_text"+"timerTextView"));
            }
        }

        if(timer && btn1.getText().equals("Identify")){
            time(timerTextView,timerChangedValue1);
        }
        if(timer){
            timerTextView.setVisibility(View.VISIBLE);
        }
        scaleGestureDetector = new ScaleGestureDetector(this,new ScaleListener());
    }

    public void setImage(ImageView imageView){
        Resources res = getResources();
        //Using the String Array used for Spinner Label
        carArray = res.getStringArray(R.array.carName_array);
        //Getting a random name from the array
        int index=random.nextInt(carArray.length);
        carName = carArray[index].toLowerCase();
       //creating a random name to be taken from image collection
        resource = "img" + random.nextInt(4)+"_"+carName;
        resource_id = getResources().getIdentifier(resource,
                "drawable", "iit.l5.tut.autospy");
        imageView.setImageResource(resource_id);
    }

    //Method to start Time Using Countdown Timer.
    public void time(TextView timerView, Long timerChangedValue){
        if ( mediaPlayer!=null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        CountDownTimer = new CountDownTimer(timerChangedValue, 1000) {
            public void onTick(long millisUntilFinished) {
                timerView.setText("Timer:"+ millisUntilFinished / 1000);
                timerChangedValue1=millisUntilFinished;
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.timer_music);
                mediaPlayer.start();
            }
            public void onFinish() {
                timerTextView.setText(" TIME'S UP");
                answer(timerTextView);
            }
        }.start();

    }

    //Checking For btn Text to set Action to happened next.
    public void checkCarMakeResult(View view) {
        if(btn1.getText().equals("Identify")) {
            answer(timerTextView);
        }else if(btn1.getText().equals("Next")){
            setImage(carMakeImageView);
            result.setText("");
            answer.setText("");
            btn1.setText("Identify");
            if(timer){
                timerChangedValue1 = 21000L;
                time(timerTextView, timerChangedValue1);
            }
        }
    }

    //Validating the answer
    public void answer(TextView timerView){
        try {
            if(timer && mediaPlayer != null){
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer = null;
                CountDownTimer.cancel();
            }
        }catch (NullPointerException e){
            System.out.println("Error");
        }

        if (spinnerLabel.toLowerCase().equals(carName)) {
            result.setText("CORRECT!");
            result.setTextColor(Color.parseColor("#00FF00"));
            mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.coin_sound);
            mediaPlayer2.start();
        } else {
            result.setText("WRONG!");
            answer.setText(carName.toUpperCase());
            result.setTextColor(Color.parseColor("#FF0000"));
            mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.wrong_sound);
            mediaPlayer2.start();
            Vibrator vibrator = null;
            vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(500);
            }
        }
        btn1.setText("Next");
    }

    //Overriding methods to implement.

    @Override
    protected void onPause() {
        super.onPause();
        if(timer && mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if(mediaPlayer2 != null){
            mediaPlayer2.stop();
            mediaPlayer2.reset();
            mediaPlayer2.release();
            mediaPlayer2 = null;
        }
        if(timer && CountDownTimer != null){
            CountDownTimer.cancel();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerLabel = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (carMakeImageView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putInt("reply_text"+"image",resource_id);
            outState.putCharSequence("reply_text"+"result",result.getText());
            outState.putInt("reply_text"+"resultColor",result.getCurrentTextColor());
            outState.putCharSequence("reply_text"+"answer",answer.getText());
            outState.putCharSequence("reply_text"+"btn",btn1.getText());

            outState.putString("reply_text"+"value",carName);
            if(timer){
                outState.putLong("reply_text"+"time",timerChangedValue1);
                outState.putCharSequence("reply_text"+"timerTextView",timerTextView.getText());
            }
            outState.putString("reply_text"+"spinner",spinnerLabel);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return scaleGestureDetector.onTouchEvent(event);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= scaleGestureDetector.getScaleFactor();
            carMakeImageView.setScaleX(scaleFactor);
            carMakeImageView.setScaleY(scaleFactor);
            return true;
        }
    }
}