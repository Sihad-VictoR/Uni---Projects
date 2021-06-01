package iit.l5.tut.flickattic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class FavouriteActivity extends AppCompatActivity {
    //Declaring a an Adapter to Recycler  view and other variables.
    MovieAdapterToDisplayWithCheckBox adapter;
    Button saveBtn;
    Database database;

    //A List to contain Movie Elements
    List<Movie> moviesList = new ArrayList<>();

    //From and order Strings to perform database Functions
    private static String[] FROM = { MOVIE_ID,MOVIE_TITLE,MOVIE_YEAR,MOVIE_DIRECTOR,MOVIE_ACTS,MOVIE_RATING,MOVIE_REVIEW,MOVIE_FAVOURITES};
    private static String ORDER_BY = MOVIE_TITLE + " ASC";
    private static String SELECTION = "favorite = 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        //Setting up elements.
        database = new Database(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_movies_favourites);
        saveBtn = findViewById(R.id.addToFavBtn_favourites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //calling method to add data to list.
        initListViewData();

        //Setting up adapter to recyclerView.
        adapter = new MovieAdapterToDisplayWithCheckBox(FavouriteActivity.this,moviesList,true);
        recyclerView.setAdapter(adapter);

        //onClick Listener to update movies.
        saveBtn.setOnClickListener(v -> {
            List<Movie> movies = adapter.getMoviesList();
            for(int i=0;i< movies.size(); i++ )  {
                update(movies.get(i));
            }
        });

    }
    //A Method to get Data from Database
    private void initListViewData()  {

        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, FROM, SELECTION,
                null, null, null,ORDER_BY);

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
            if(bool.equals("1")){
                favorite = true;
            }else favorite = false;

            Movie movie = new Movie(id,title,year,director,acts,rating,review,favorite);
            moviesList.add(movie);
            System.out.println(movie);
        }
        cursor.close();
    }

    //A Method to update Data to Database
    private void update(Movie movie){
        try {
            ContentValues values = new ContentValues();
            SQLiteDatabase db = database.getWritableDatabase();

            values.put(MOVIE_ID,movie.getMId());
            values.put(MOVIE_TITLE,movie.getMTitle());
            values.put(MOVIE_YEAR, movie.getMYear());
            values.put(MOVIE_DIRECTOR, movie.getMDirector());
            values.put(MOVIE_ACTS, movie.getMActs());
            values.put(MOVIE_RATING, movie.getMRating());
            values.put(MOVIE_REVIEW, movie.getMReview());
            values.put(MOVIE_FAVOURITES, movie.isMFavorites());
            int i = db.update(TABLE_NAME, values, MOVIE_ID + " = \"" + movie.getMId()+"\"", null);

            if(i>0){
                Toast.makeText(this,"Added to Favourites", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"Database Error!Try Again Fixing Error", Toast.LENGTH_SHORT).show();
            }

        } finally {
            database.close();
        }
    }
}