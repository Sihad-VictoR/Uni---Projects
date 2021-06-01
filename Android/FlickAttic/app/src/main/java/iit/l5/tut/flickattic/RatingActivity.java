package iit.l5.tut.flickattic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static iit.l5.tut.flickattic.Constants.MOVIE_ACTS;
import static iit.l5.tut.flickattic.Constants.MOVIE_DIRECTOR;
import static iit.l5.tut.flickattic.Constants.MOVIE_FAVOURITES;
import static iit.l5.tut.flickattic.Constants.MOVIE_ID;
import static iit.l5.tut.flickattic.Constants.MOVIE_RATING;
import static iit.l5.tut.flickattic.Constants.MOVIE_REVIEW;
import static iit.l5.tut.flickattic.Constants.MOVIE_TITLE;
import static iit.l5.tut.flickattic.Constants.MOVIE_YEAR;
import static iit.l5.tut.flickattic.Constants.TABLE_NAME;

public class RatingActivity extends AppCompatActivity {

    MovieAdapterToDisplayWithCheckBox adapter;
    Database database;
    Button find_Btn;

    //A List to contain Movie Elements
    List<Movie> moviesList = new ArrayList<>();

    //From and order Strings to perform database Functions
    private static String[] FROM = {MOVIE_ID, MOVIE_TITLE, MOVIE_YEAR, MOVIE_DIRECTOR, MOVIE_ACTS, MOVIE_RATING, MOVIE_REVIEW, MOVIE_FAVOURITES};
    private static String ORDER_BY = MOVIE_TITLE + " ASC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        //Setting up elements.
        database = new Database(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rating_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        find_Btn = findViewById(R.id.findIMDBBtn_rating);

        //calling method to add data to list.
        initListViewData();

        //Setting up adapter to recyclerView.
        adapter = new MovieAdapterToDisplayWithCheckBox(RatingActivity.this, moviesList,false);
        recyclerView.setAdapter(adapter);

        //check network connectivity and finding data
        find_Btn.setOnClickListener(v -> {
            try {
                // Check the status of the network connection.
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = null;
                if (connMgr != null) {
                    networkInfo = connMgr.getActiveNetworkInfo();
                }
                if (networkInfo != null && networkInfo.isConnected()) {

                    List<Movie> find = adapter.getReturnList();
                    Movie movie1 = find.get(0);
                    Intent intent;
                    intent = new Intent(this, ViewRating.class);
                    intent.putExtra("Movie", movie1);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(RatingActivity.this,"Check Network Connection!", Toast.LENGTH_SHORT).show();
                }

            }catch (IndexOutOfBoundsException e){
                Toast.makeText(RatingActivity.this,"Select a movie to find in IMDB!", Toast.LENGTH_SHORT).show();
            }

        });
    }

    //A Method to get Data from Database
    private void initListViewData() {

        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, FROM, null,
                null, null, null, ORDER_BY);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String title = cursor.getString(1);
            String year = cursor.getString(2);
            String director = cursor.getString(3);
            String acts = cursor.getString(4);
            String rating = cursor.getString(5);
            String review = cursor.getString(6);
            String bool = cursor.getString(7);
            boolean favorite;
            if (bool.equals("1")) {
                favorite = true;
            } else favorite = false;

            Movie movie = new Movie(id, title, year, director, acts, rating, review, favorite);
            moviesList.add(movie);
            System.out.println(movie);
        }
        cursor.close();
    }
}