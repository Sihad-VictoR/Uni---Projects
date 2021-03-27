package iit.l5.tut.autospy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

//This is the main Menu Activity
public class HomeActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "iit.l5.tut.autospy.extra.MESSAGE";
    private MediaPlayer mediaPlayer;
    private ImageButton musicBtn;
    private int flag = 1;
    private boolean timerValue;
    private SwitchCompat simpleSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Setting Up Music to the UI
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.background_music);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        simpleSwitch = (SwitchCompat) findViewById(R.id.homeActivitySwitch);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    //changing music on/off using toggle logic
    public void setMusic(View view) {
        musicBtn = (ImageButton) findViewById(R.id.homeActivityMusicBtn);
        if (flag == 0) {
            musicBtn.setImageResource(R.drawable.music);
            flag = 1;
            mediaPlayer.start();
        }else if (flag == 1) {
            musicBtn.setImageResource(R.drawable.music_off);
            flag = 0;
            mediaPlayer.pause();
        }
    }

    public void startCarMakeActivity(View view) {
        // check current state of a Switch (true or false).
        timerValue = simpleSwitch.isChecked();

        Intent intent = new Intent(HomeActivity.this,
                IdentifyTheCarMakeActivity.class);
        intent.putExtra(EXTRA_MESSAGE, timerValue);
        startActivity(intent);
    }

    public void startCarImageActivity(View view) {
        // check current state of a Switch (true or false).
        timerValue = simpleSwitch.isChecked();

        Intent intent = new Intent(HomeActivity.this,
                IdentifyTheCarImageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, timerValue);
        startActivity(intent);
    }

    public void startHintsActivity(View view) {
        // check current state of a Switch (true or false).
        timerValue = simpleSwitch.isChecked();

        Intent intent = new Intent(HomeActivity.this,
                HintsActivity.class);
        intent.putExtra(EXTRA_MESSAGE, timerValue);
        startActivity(intent);
    }

    public void startAdvancedLvlActivity(View view) {
        // check current state of a Switch (true or false).
        timerValue = simpleSwitch.isChecked();

        Intent intent = new Intent(HomeActivity.this,
                AdvancedLevelActivity.class);
        intent.putExtra(EXTRA_MESSAGE, timerValue);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (flag == 1){
            mediaPlayer.start();
        }

    }
}