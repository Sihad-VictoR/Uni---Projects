package iit.l5.tut.flickattic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

public class HomeActivity extends AppCompatActivity {
    //Stating Activity of Application

    //Declaring variables
    private static final String VIDEO_SAMPLE = "tacoma_narrows";
    private VideoView mVideoView;
    private Button registerBtn,displayBtn,favouriteBtn,editBtn,searchBtn,ratingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Setting up elements
        mVideoView = findViewById(R.id.videoView);
        mVideoView.setOnPreparedListener(mp -> mp.setLooping(true));

        registerBtn =findViewById(R.id.registerBtn);
        displayBtn =findViewById(R.id.displayBtn);
        favouriteBtn =findViewById(R.id.favoriteBtn);
        editBtn =findViewById(R.id.editBtn);
        searchBtn =findViewById(R.id.searchBtn);
        ratingBtn =findViewById(R.id.imdbBtn);

        //creating onClick Listener to each buttons

        registerBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        displayBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, DisplayActivity.class);
            startActivity(intent);
        });

        favouriteBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, FavouriteActivity.class);
            startActivity(intent);
        });

        editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditActivity.class);
            startActivity(intent);
        });

        searchBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        });

        ratingBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, RatingActivity.class);
            startActivity(intent);
        });
    }

    //Setting up background media , Video View.
    private Uri getMedia() {
        return Uri.parse("android.resource://" + getPackageName() +
                "/raw/" + HomeActivity.VIDEO_SAMPLE);
    }
    private void initializePlayer() {
        Uri videoUri = getMedia();
        mVideoView.setVideoURI(videoUri);
        mVideoView.start();
    }
    private void releasePlayer() {
        mVideoView.stopPlayback();
    }


    @Override
    protected void onStop() {
        super.onStop();

        releasePlayer();
    }
    @Override
    protected void onStart() {
        super.onStart();

        initializePlayer();
    }
    @Override
    protected void onPause() {
        super.onPause();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView.pause();
        }
    }
}