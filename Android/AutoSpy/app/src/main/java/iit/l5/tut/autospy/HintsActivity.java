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

public class HintsActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    private boolean timer;
    private TextView timerTextView;
    private android.os.CountDownTimer CountDownTimer;
    private final Random random = new Random();
    private String[] carArray;
    private String carName;
    private String resource;
    private String correctValue;
    private Button submitBtn;
    private TextView crossView;
    private TextView dashView;
    private TextView resultView;
    private TextView answerView;
    private EditText userInput;
    private ImageView imageView;
    private String imageName;
    private String dashWord;
    private int count = 0;
    private int resource_id;
    private Long timerChangedValue1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);

        //using intent to get the value for timer
        Intent intent = getIntent();
        timer = intent.getBooleanExtra(HomeActivity.EXTRA_MESSAGE,false);
        timerTextView = (TextView) findViewById(R.id.hintsActivityTimerView);
        answerView = (TextView)  findViewById(R.id.hintsActivityAnswer);
        submitBtn = (Button) findViewById(R.id.hintsActivitySubmitBtn);
        crossView = (TextView) findViewById(R.id.hintsActivityCrossView);
        dashView = (TextView) findViewById(R.id.hintsActivityTextView);
        resultView = (TextView) findViewById(R.id.hintsActivityResultView);
        userInput = (EditText) findViewById(R.id.hintsActivityInputText);
        imageView = (ImageView) findViewById(R.id.hintsActivityImageView);
        dashView.setKeyListener(null);
        timerChangedValue1 = 21000L;

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.timer_music);
        mediaPlayer.setLooping(true);

        //Checking whether there are any saved instances before loading up new Activity.
        //Used for Landscape Rotations.
        if(savedInstanceState == null){
            setImageToView();
        }else {
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            imageView.setVisibility(View.VISIBLE);

            imageView.setImageResource(savedInstanceState.getInt("reply_text"+"image"));
            resource_id = savedInstanceState.getInt("reply_text"+"image");
            imageName = savedInstanceState.getString("reply_text"+"answer");
            if(savedInstanceState.getCharSequence("reply_text"+"answerView").length()>0) {
                answerView.setText(savedInstanceState.getCharSequence("reply_text" + "answerView"));
            }
            resultView.setTextColor(savedInstanceState.getInt("reply_text"+"resultColor"));
            resultView.setText(savedInstanceState.getCharSequence("reply_text"+"result"));
            crossView.setText(savedInstanceState.getCharSequence("reply_text"+"crosses"));
            count = savedInstanceState.getInt("reply_text"+"count");
            dashView.setText(savedInstanceState.getCharSequence("reply_text"+"dashView"));
            userInput.setText(savedInstanceState.getCharSequence("reply_text"+"user_input"));
            submitBtn.setText(savedInstanceState.getCharSequence("reply_text"+"btn"));
            dashWord = savedInstanceState.getString("reply_text"+"dashWord");
            if(timer){
                timerTextView.setText(savedInstanceState.getCharSequence("reply_text"+"timerTextView"));
                timerChangedValue1 = savedInstanceState.getLong("reply_text"+"time");
                if(timerChangedValue1>0 && submitBtn.getText().equals("Submit")){
                    time(timerTextView,timerChangedValue1);
                }
            }
        }

        if(timer){
            timerTextView.setVisibility(View.VISIBLE);
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
                checkForGuesses();
            }
        }.start();
    }

    //Setting up new Image to image View
    public void setImageToView(){
        setImage(imageView);
        imageName = carName.toLowerCase();
        System.out.println(imageName);
        if(timer){
            timerTextView.setVisibility(View.VISIBLE);
            time(timerTextView,timerChangedValue1);
        }
        dashWord = new String(new char[imageName.length()]).replace("\0", "-");
        dashView.setText(dashWord);
        crossView.setText("");
        resultView.setText("");
        answerView.setText("");
        submitBtn.setText("Submit");
        count = 0;
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

    //Validating answer
    public  void checkForGuesses() {
        timerChangedValue1 = 21000L;
        if(timer && mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
            CountDownTimer.cancel();
        }
        userInput = (EditText) findViewById(R.id.hintsActivityInputText);
        String input = userInput.getText().toString().toLowerCase();
        if(input.length() == 0){
            input ="1";
        }
        System.out.println(input);

        String checkWord = "";
        for (int i = 0; i < imageName.length(); i++) {
            if (imageName.charAt(i) == input.charAt(0)) {
                checkWord += input.charAt(0);
            } else if (dashWord.charAt(i) != '-') {
                checkWord += imageName.charAt(i);
            } else {
                checkWord += "-";
            }
        }
        if (dashWord.equals(checkWord)) {
            count++;
            numberOFCrosses();
        } else {
            dashWord = checkWord;
        }
        if (dashWord.equals(imageName)) {
            checkResult(true);
            System.out.println("Correct! You win! The word was " + imageName);
        }else if(!crossView.getText().equals("XXX")) {
            if(timer){
                timerTextView.setVisibility(View.VISIBLE);
                time(timerTextView,timerChangedValue1);
            }
        }
        dashView.setText(dashWord);
    }

    //Checking for crosses and setting it
    public  void numberOFCrosses() {
        if(count ==1){
            crossView.setText("X");
        }
        if (count == 2) {
            crossView.setText("XX");
        }
        if (count == 3) {
            crossView.setText("XXX");
            checkResult(false);
        }
    }

    //Final result check
    public void checkResult(boolean value){
        if(value){
            resultView.setText("CORRECT!");
            resultView.setTextColor(Color.parseColor("#00FF00"));
            mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.coin_sound);
            mediaPlayer2.start();
        }else{
            resultView.setText("WRONG!");
            resultView.setTextColor(Color.parseColor("#FF0000"));
            answerView.setText(imageName.toUpperCase());
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
        submitBtn.setText("Next");
    }

    //Button click event check
    public void checkForAnswer(View view) {
        timerChangedValue1 = 21000L;

        if(submitBtn.getText().equals("Submit")) {
            checkForGuesses();
        }else if (submitBtn.getText().equals("Next")){
            setImageToView();
        }
        userInput.setText("");
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
        if (imageView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putInt("reply_text"+"image",resource_id);
            outState.putCharSequence("reply_text"+"crosses",crossView.getText());
            outState.putCharSequence("reply_text"+"result",resultView.getText());
            outState.putInt("reply_text"+"resultColor",resultView.getCurrentTextColor());
            outState.putCharSequence("reply_text"+"user_input",userInput.getText());
            outState.putCharSequence("reply_text"+"btn",submitBtn.getText());
            outState.putInt("reply_text"+"count",count);
            outState.putCharSequence("reply_text"+"dashView",dashView.getText());
            outState.putString("reply_text"+"answer",imageName);
            outState.putString("reply_text"+"dashWord",dashWord);
            if(answerView.getText().length()>0) {
                outState.putCharSequence("reply_text" + "answerView", answerView.getText());
            }else{
                outState.putCharSequence("reply_text" + "answerView","");
            }
            if(timer){
                outState.putLong("reply_text"+"time",timerChangedValue1);
                outState.putCharSequence("reply_text"+"timerTextView",timerTextView.getText());
            }
        }
    }
}