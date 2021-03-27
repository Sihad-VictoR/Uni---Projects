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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class AdvancedLevelActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    private boolean timer;
    private TextView timerTextView;
    private android.os.CountDownTimer CountDownTimer;
    private final Random random = new Random();
    private String[] carArray;
    private String carName;
    private String resource;

    private TextView scoreView;
    private EditText input1;
    private EditText input2;
    private EditText input3;


    private int count = 0;
    private int score = 0 ;

    private TextView answer1;
    private TextView answer2;
    private TextView answer3;
    private TextView result;
    private Button submitBtn;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private String imageName1;
    private String imageName2;
    private String imageName3;
    private int resource_id1;
    private int resource_id2;
    private int resource_id3;
    private Long timerChangedValue1;
    private int resource_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);

        //using intent to get the value for timer
        Intent intent = getIntent();
        timer = intent.getBooleanExtra(HomeActivity.EXTRA_MESSAGE,false);
        timerTextView = (TextView) findViewById(R.id.advancedLvlActivityTimerView);

        scoreView = (TextView) findViewById(R.id.advancedLvlActivityScoreView);
        answer1 = (TextView) findViewById(R.id.advancedLvlActivityAnswerView1);
        answer2 = (TextView) findViewById(R.id.advancedLvlActivityAnswerView2);
        answer3 = (TextView) findViewById(R.id.advancedLvlActivityAnswerView3);
        result = (TextView) findViewById(R.id.advancedLvlActivityResultView);
        input1 = (EditText) findViewById(R.id.advancedLvlActivityInputView1);
        input2 = (EditText) findViewById(R.id.advancedLvlActivityInputView2);
        input3 = (EditText) findViewById(R.id.advancedLvlActivityInputView3);
        submitBtn = (Button) findViewById(R.id.advancedLvlActivitySubmitBtn);
        imageView1 = (ImageView) findViewById(R.id.advancedLvlActivityImageView1);
        imageView2 = (ImageView) findViewById(R.id.advancedLvlActivityImageView2);
        imageView3 = (ImageView) findViewById(R.id.advancedLvlActivityImageView3);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.timer_music);
        mediaPlayer.setLooping(true);
        timerChangedValue1 = 21000L;

        //Checking whether there are any saved instances before loading up new Activity.
        //Used for Landscape Rotations.
        if(savedInstanceState == null){
            getCars();
        }else {
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            imageView1.setImageResource(savedInstanceState.getInt("reply_text"+"image1"));
            imageView2.setImageResource(savedInstanceState.getInt("reply_text"+"image2"));
            imageView3.setImageResource(savedInstanceState.getInt("reply_text"+"image3"));
            resource_id1 = savedInstanceState.getInt("reply_text"+"image1");
            resource_id2 =savedInstanceState.getInt("reply_text"+"image2");
            resource_id3 =savedInstanceState.getInt("reply_text"+"image3");
            result.setTextColor(savedInstanceState.getInt("reply_text"+"resultColor"));
            result.setText(savedInstanceState.getCharSequence("reply_text"+"result"));
            imageName1 = savedInstanceState.getString("reply_text"+"answer1");
            imageName2 = savedInstanceState.getString("reply_text"+"answer2");
            imageName3 = savedInstanceState.getString("reply_text"+"answer3");
            answer1.setText(savedInstanceState.getString("reply_text"+"answer1View"));
            answer2.setText(savedInstanceState.getString("reply_text"+"answer2View"));
            answer3.setText(savedInstanceState.getString("reply_text"+"answer3View"));
            count = savedInstanceState.getInt("reply_text"+"count");
            score = savedInstanceState.getInt("reply_text"+"score");
            scoreView.setText(savedInstanceState.getCharSequence("reply_text"+"scoreView"));
            submitBtn.setText(savedInstanceState.getCharSequence("reply_text"+"btnText"));
            input1.setText(savedInstanceState.getCharSequence("reply_text"+"input1Txt"));
            input2.setText(savedInstanceState.getCharSequence("reply_text"+"input2Txt"));
            input3.setText(savedInstanceState.getCharSequence("reply_text"+"input3Txt"));

            if(count>0){
                if(!input1.getText().toString().toLowerCase().equals(imageName1)){
                    input1.setBackgroundResource(R.drawable.wrong_rounded_corner);
                }else if(input1.getText().toString().toLowerCase().equals(imageName1)){
                    input1.setEnabled(true);
                    input1.setBackgroundResource(R.drawable.correct_rounded_corner);
                }
                if(!input2.getText().toString().toLowerCase().equals(imageName2)){
                    input2.setBackgroundResource(R.drawable.wrong_rounded_corner);
                }else if(input2.getText().toString().toLowerCase().equals(imageName2)){
                    input2.setEnabled(true);
                    input2.setBackgroundResource(R.drawable.correct_rounded_corner);
                }
                if(!input3.getText().toString().toLowerCase().equals(imageName3)){
                    input3.setBackgroundResource(R.drawable.wrong_rounded_corner);
                }else if(input3.getText().toString().toLowerCase().equals(imageName3)){
                    input3.setEnabled(true);
                    input3.setBackgroundResource(R.drawable.correct_rounded_corner);
                }
            }


            timerChangedValue1 = savedInstanceState.getLong("reply_text"+"time");
            if(timer && submitBtn.getText().equals("Submit")){
                time(timerTextView, timerChangedValue1);
            }
            if(timer){
                timerTextView.setBackgroundResource(R.drawable.rounded_corner);
                timerTextView.setVisibility(View.VISIBLE);
                timerTextView.setText(savedInstanceState.getCharSequence("reply_text"+"timerTextView"));
            }
        }
     }

    //Method to start Time Using Countdown Timer.
    public void time(TextView timerView,Long timerChangedValue){
        if ( mediaPlayer!=null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        CountDownTimer = new CountDownTimer(timerChangedValue, 1000) {

            public void onTick(long millisUntilFinished) {
                timerView.setText("Timer:" + millisUntilFinished / 1000);
                timerChangedValue1 = millisUntilFinished;
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.timer_music);
                mediaPlayer.start();
            }
            public void onFinish() {
                timerTextView.setText(" TIME'S UP");
                submit();
            }
        }.start();
    }

    //Validating Answer
    private void checkForAnswers() {
        if(timer && mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
            CountDownTimer.cancel();
        }
        if(input1.getText().toString().toLowerCase().equals(imageName1) && input1.isEnabled()){
            input1.setEnabled(false);
            input1.setBackgroundResource(R.drawable.correct_rounded_corner);
            score+=1;
        }else if(!input1.getText().toString().equals(imageName1)&& input1.isEnabled()) {
            input1.setBackgroundResource(R.drawable.wrong_rounded_corner);

        }

        if(input2.getText().toString().toLowerCase().equals(imageName2 ) && input2.isEnabled()){
            input2.setEnabled(false);
            input2.setBackgroundResource(R.drawable.correct_rounded_corner);
            score+=1;
        }else if(!input2.getText().toString().equals(imageName2) && input2.isEnabled())  input2.setBackgroundResource(R.drawable.wrong_rounded_corner);

        if(input3.getText().toString().toLowerCase().equals(imageName3) && input3.isEnabled()){
            input3.setEnabled(false);
            input3.setBackgroundResource(R.drawable.correct_rounded_corner);
            score+=1;
        }else if(!input3.getText().toString().toLowerCase().equals(imageName3) && input3.isEnabled())  input3.setBackgroundResource(R.drawable.wrong_rounded_corner);

        if(!input1.getText().toString().equals(imageName1) || !input2.getText().toString().equals(imageName2)
                ||!input3.getText().toString().equals(imageName3)){
            count+=1;
        }
        if(input1.getText().toString().toLowerCase().equals(imageName1) && input2.getText().toString().toLowerCase().equals(imageName2)
                && input3.getText().toString().toLowerCase().equals(imageName3)){
            result.setText("CORRECT!");
            result.setTextColor(Color.parseColor("#00FF00"));
            mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.coin_sound);
            mediaPlayer2.start();
            submitBtn.setText("Next");
        }else {
            if(count == 1) {
                result.setText("X");
                result.setTextColor(Color.parseColor("#FF0000"));
            }else if (count == 2){
                result.setText("XX");
                result.setTextColor(Color.parseColor("#FF0000"));
            }else if(count == 3){
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
                submitBtn.setText("Next");
                if(input1.isEnabled()){
                    answer1.setText(imageName1.substring(0, 1).toUpperCase() + imageName1.substring(1));
                }
                if(input2.isEnabled()){
                    answer2.setText(imageName2.substring(0, 1).toUpperCase() + imageName2.substring(1));
                }
                if(input3.isEnabled()){
                    answer3.setText(imageName3.substring(0, 1).toUpperCase() + imageName3.substring(1));
                }
            }
        }

        if(timer && submitBtn.getText().equals("Submit")){
            time(timerTextView, timerChangedValue1);
        }
        String value = "Score : "+String.valueOf(score);
        scoreView.setText(value);

    }

    //Setting image to ImageViews.
    public void getCars(){
        if(timer){
            timerTextView.setBackgroundResource(R.drawable.rounded_corner);
            timerTextView.setVisibility(View.VISIBLE);
            time(timerTextView, timerChangedValue1);
        }
        setImage(imageView1);
        imageName1 = carName.toLowerCase();
        resource_id1 = resource_id;
        System.out.println(imageName1);

        setImage(imageView2);
        imageName2 = carName.toLowerCase();
        resource_id2 =resource_id;
        System.out.println(imageName2);

        setImage(imageView3);
        imageName3 = carName.toLowerCase();
        resource_id3 =resource_id;
        System.out.println(imageName3);

        while(imageName1.equals(imageName2) || imageName2.equals(imageName3)){
            setImage(imageView2);
            imageName2 = carName.toLowerCase();
            resource_id2 =resource_id;
            System.out.println(imageName2);
        }

        while(imageName1.equals(imageName3)){
            setImage(imageView3);
            imageName3 = carName.toLowerCase();
            resource_id3 =resource_id;
            System.out.println(imageName3);
        }
    }

    //Method to setup image.
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

    //responding to onclick btn
    public void submit(){
        timerChangedValue1 = 21000L;
        if(submitBtn.getText().equals("Submit")){
            checkForAnswers();
        }else if(submitBtn.getText().equals("Next")){
            input1.setEnabled(true);
            input2.setEnabled(true);
            input3.setEnabled(true);
            count = 0;
            getCars();
            input1.setText("");
            input2.setText("");
            input3.setText("");
            answer1.setText("");
            answer2.setText("");
            answer3.setText("");
            result.setText("");
            submitBtn.setText("Submit");
            input1.setBackgroundResource(R.drawable.rounded_corner);
            input2.setBackgroundResource(R.drawable.rounded_corner);
            input3.setBackgroundResource(R.drawable.rounded_corner);
            result.setBackgroundColor(Color.parseColor("#977BB6"));
        }
    }

    public void submitButton(View view) {
        submit();
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
            outState.putInt("reply_text"+"count",count);
            outState.putInt("reply_text"+"score",score);
            outState.putInt("reply_text"+"resultColor",result.getCurrentTextColor());
            outState.putString("reply_text"+"answer1",imageName1);
            outState.putString("reply_text"+"answer2",imageName2);
            outState.putString("reply_text"+"answer3",imageName3);
            outState.putCharSequence("reply_text"+"result",result.getText());
            outState.putCharSequence("reply_text"+"answer1View",answer1.getText());
            outState.putCharSequence("reply_text"+"answer2View",answer2.getText());
            outState.putCharSequence("reply_text"+"answer3View",answer3.getText());
            outState.putCharSequence("reply_text"+"scoreView",scoreView.getText());
            outState.putCharSequence("reply_text"+"btnText",submitBtn.getText());


            outState.putCharSequence("reply_text"+"input1Txt",input1.getText());
            outState.putCharSequence("reply_text"+"input2Txt",input2.getText());
            outState.putCharSequence("reply_text"+"input3Txt",input3.getText());
            outState.putBoolean("reply_text"+"input1Bool",input1.isEnabled());
            outState.putBoolean("reply_text"+"input2Bool",input2.isEnabled());
            outState.putBoolean("reply_text"+"input3Bool",input3.isEnabled());


            if(timer){
                outState.putLong("reply_text"+"time",timerChangedValue1);
                outState.putCharSequence("reply_text"+"timerTextView",timerTextView.getText());
            }
        }
    }

}