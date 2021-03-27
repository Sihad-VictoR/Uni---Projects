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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class IdentifyTheCarImageActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    private boolean timer;
    private TextView timerTextView;
    private android.os.CountDownTimer CountDownTimer;
    private TextView result;
    private TextView answer;
    private final Random random = new Random();
    private String[] carArray;
    private String carName;
    private String resource;
    private Button nextBtn;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private String imageName1;
    private String imageName2;
    private String imageName3;
    private String chosen;
    private int resource_id;
    private int resource_id1;
    private int resource_id2;
    private int resource_id3;
    private Long timerChangedValue1;
    private Boolean image1Bool = false;
    private Boolean image2Bool = false;
    private Boolean image3Bool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_car_image);

        //using intent to get the value for timer
        Intent intent = getIntent();
        timer = intent.getBooleanExtra(HomeActivity.EXTRA_MESSAGE,false);
        timerTextView = (TextView) findViewById(R.id.carImageActivityTimerView);
        result = (TextView) findViewById(R.id.carImageActivityResultView);
        answer = (TextView) findViewById(R.id.carImageActivityQuestionView);
        nextBtn = (Button) findViewById(R.id.carImageActivityNextBtn);
        imageView1 = (ImageView) findViewById(R.id.carImageActivityImageView1);
        imageView2 = (ImageView) findViewById(R.id.carImageActivityImageView2);
        imageView3 = (ImageView) findViewById(R.id.carImageActivityImageView3);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.timer_music);
        mediaPlayer.setLooping(true);
        timerChangedValue1 = 21000L;

        //Checking whether there are any saved instances before loading up new Activity.
        //Used for Landscape Rotations.
        if(savedInstanceState == null){
            getCars();
        }else {
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            imageView1.setVisibility(View.VISIBLE);
            if(savedInstanceState.getBoolean("reply_text"+"btn")){
                nextBtn.setVisibility(View.VISIBLE);
            }else nextBtn.setVisibility(View.INVISIBLE);
                imageView1.setImageResource(savedInstanceState.getInt("reply_text"+"image1"));
                imageView2.setImageResource(savedInstanceState.getInt("reply_text"+"image2"));
                imageView3.setImageResource(savedInstanceState.getInt("reply_text"+"image3"));
                resource_id1 = savedInstanceState.getInt("reply_text"+"image1");
                resource_id2 =savedInstanceState.getInt("reply_text"+"image2");
                resource_id3 =savedInstanceState.getInt("reply_text"+"image3");
                answer.setText(savedInstanceState.getCharSequence("reply_text"+"answer"));
                result.setTextColor(savedInstanceState.getInt("reply_text"+"resultColor"));
                result.setText(savedInstanceState.getCharSequence("reply_text"+"result"));
                imageName1 = savedInstanceState.getString("reply_text"+"answer1");
                imageName2 = savedInstanceState.getString("reply_text"+"answer2");
                imageName3 = savedInstanceState.getString("reply_text"+"answer3");
                timerChangedValue1 = savedInstanceState.getLong("reply_text"+"time");
                resource_id = savedInstanceState.getInt("reply_text"+"image");
                chosen = savedInstanceState.getString("reply_text"+"value");
                timerTextView.setText(savedInstanceState.getCharSequence("reply_text"+"timerTextView"));

                image1Bool = savedInstanceState.getBoolean("reply_text"+"image1Bool");
                image2Bool = savedInstanceState.getBoolean("reply_text"+"image2Bool");
                image3Bool = savedInstanceState.getBoolean("reply_text"+"image3Bool");
        }
        if(nextBtn.getVisibility()==View.VISIBLE){
            imageView1.setEnabled(false);
            imageView2.setEnabled(false);
            imageView3.setEnabled(false);
            System.out.println(savedInstanceState.getCharSequence("reply_text"+"result"));
            if(savedInstanceState.getCharSequence("reply_text"+"result").equals("WRONG!")) {
                if (image1Bool) {
                    imageView1.setBackgroundResource(R.drawable.rounded_corner);
                } else if (image2Bool) {
                    imageView2.setBackgroundResource(R.drawable.rounded_corner);
                } else if (image3Bool) {
                    imageView3.setBackgroundResource(R.drawable.rounded_corner);
                }
            }
        }
        if(timer && nextBtn.getVisibility()==View.INVISIBLE){
            time(timerTextView,timerChangedValue1);
        }
        if(timer){
            timerTextView.setVisibility(View.VISIBLE);
        }
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
                timerView.setText("Timer:" + millisUntilFinished / 1000);
                timerChangedValue1=millisUntilFinished;
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.timer_music);
                mediaPlayer.start();
            }
            public void onFinish() {
                timerTextView.setText(" TIME'S UP");
                checkAnswer("chosenAnswer");
            }
        }.start();
    }

    //Validating the answer
    public void checkAnswer(String chosenAnswer){
        try {
            if (timer && mediaPlayer!=null) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer = null;
                CountDownTimer.cancel();
            }
        }catch (NullPointerException e){
            System.out.println("Error");
        }

        if(chosenAnswer.equals(chosen)){
            result.setText("CORRECT!");
            result.setTextColor(Color.parseColor("#00FF00"));
            mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.coin_sound);
            mediaPlayer2.start();
        }else{
            if(chosen.equals(imageName1)){
                imageView1.setBackgroundResource(R.drawable.rounded_corner);
                image1Bool = true;
                System.out.println(image1Bool);
            }else if(chosen.equals(imageName2)){
                imageView2.setBackgroundResource(R.drawable.rounded_corner);
                image2Bool = true;
            }else if(chosen.equals(imageName3)){
                imageView3.setBackgroundResource(R.drawable.rounded_corner);
                image3Bool = true;
            }
            result.setText("WRONG!");
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
        nextBtn.setVisibility(View.VISIBLE);
        //making imageView un-clickable after selecting answer
        imageView1.setEnabled(false);
        imageView2.setEnabled(false);
        imageView3.setEnabled(false);
    }

    //setting unique images to three imageviews
    public void getCars(){
        setImage(imageView1);
        imageName1 = carName;
        resource_id1 = resource_id;
        System.out.println(imageName1);

        setImage(imageView2);
        imageName2 = carName;
        resource_id2 =resource_id;

        setImage(imageView3);
        imageName3 = carName;
        resource_id3 =resource_id;

        while(imageName1.equals(imageName2) || imageName2.equals(imageName3)){
            setImage(imageView2);
            imageName2 = carName;
            resource_id2 =resource_id;
        }

        while(imageName1.equals(imageName3)){
            setImage(imageView3);
            imageName3 = carName;
            resource_id3 =resource_id;
        }

        String[] array = {imageName1,imageName2,imageName3};
        chosen = array[(int) (Math.random() * array.length)];

        answer.setText(chosen.substring(0, 1).toUpperCase() + chosen.substring(1));
    }

    public void setImage(ImageView imageView){
        Resources res = getResources();
        //Using the String Array used for Spinner Label
        carArray = res.getStringArray(R.array.carName_array);
        //Getting a random name from the array
        int index=random.nextInt(carArray.length);
        carName = carArray[index];
        //creating a random name to be taken from image collection
        resource = "img" + random.nextInt(4)+"_"+carName.toLowerCase();
        resource_id = getResources().getIdentifier(resource,
                "drawable", "iit.l5.tut.autospy");
        imageView.setImageResource(resource_id);
    }

    //Passing Different messages on different clicks
    public void sendMessage1(View view){
        checkAnswer(imageName1);
    }

    public void sendMessage2(View view){
        checkAnswer(imageName2);
    }

    public void sendMessage3(View view){
        checkAnswer(imageName3);
    }

    //Event to perform on next button click.
    public void Next(View view) {
        image1Bool =false;
        image2Bool =false;
        image3Bool =false;
        imageView1.setEnabled(true);
        imageView2.setEnabled(true);
        imageView3.setEnabled(true);
        imageView1.setBackgroundColor(Color.parseColor("#977BB6"));
        imageView2.setBackgroundColor(Color.parseColor("#977BB6"));
        imageView3.setBackgroundColor(Color.parseColor("#977BB6"));
        timerChangedValue1 = 21000L;
        getCars();
        result.setText("");
        if(timer){
            time(timerTextView,timerChangedValue1);;
        }
        nextBtn.setVisibility(View.INVISIBLE);
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (imageView1.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putInt("reply_text"+"image1",resource_id1);
            outState.putInt("reply_text"+"image2",resource_id2);
            outState.putInt("reply_text"+"image3",resource_id3);
            outState.putCharSequence("reply_text"+"result",result.getText());
            outState.putInt("reply_text"+"resultColor",result.getCurrentTextColor());
            outState.putCharSequence("reply_text"+"answer",answer.getText());
            if(nextBtn.getVisibility() == View.VISIBLE){
                outState.putBoolean("reply_text"+"btn",true);
            }else {
                outState.putBoolean("reply_text"+"btn",false);
            }
            outState.putString("reply_text"+"answer1",imageName1);
            outState.putString("reply_text"+"answer2",imageName2);
            outState.putString("reply_text"+"answer3",imageName3);
            outState.putString("reply_text"+"value",chosen);
            outState.putBoolean("reply_text"+"image1Bool",image1Bool);
            outState.putBoolean("reply_text"+"image2Bool",image2Bool);
            outState.putBoolean("reply_text"+"image3Bool",image3Bool);
            if(timer){
                outState.putLong("reply_text"+"time",timerChangedValue1);
                outState.putCharSequence("reply_text"+"timerTextView",timerTextView.getText());
            }
        }
    }

}