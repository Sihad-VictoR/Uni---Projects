package iit.l5.tut.flickattic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    //Activity Starting Application
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(this::startHomeActivity,3000);
    }

    public void startHomeActivity(){
        Intent intent = new Intent(MainActivity.this,
                HomeActivity.class);
        startActivity(intent);
        finish();
    }
}