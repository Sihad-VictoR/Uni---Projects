package iit.l5.tut.flickattic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

public class SearchActivity extends AppCompatActivity {
    //Declaring a an Adapter to Recycler  view and other variables.
    MovieAdapterToSearch adapter;
    Button searchBtn;
    Database database;
    AutoCompleteTextView searchEditTxt;

    //A List to contain Movie Elements
    List<Movie> moviesList = new ArrayList<>();

    //From and order Strings to perform database Functions
    private static String[] FROM = { MOVIE_ID,MOVIE_TITLE,MOVIE_YEAR,MOVIE_DIRECTOR,MOVIE_ACTS,MOVIE_RATING,MOVIE_REVIEW,MOVIE_FAVOURITES};
    private static String ORDER_BY = MOVIE_TITLE + " ASC";
    ArrayList<String> autoCompletion = new ArrayList<>();
    private static String SELECTION = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Setting up elements.
        database = new Database(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_search_movies);
        searchBtn = findViewById(R.id.search_btn);
        searchEditTxt =findViewById(R.id.search_editView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Set onclick Listener to search LOOK UP Button
        searchBtn.setOnClickListener(v -> {
            String word = initListViewData();
            adapter = new MovieAdapterToSearch(SearchActivity.this,moviesList,word);
            recyclerView.setAdapter(adapter);
        });

        data();
        //Setting Array Adapter to AutoComplete TextView;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,autoCompletion);
        searchEditTxt.setAdapter(adapter);


    }

    //Searching data in database.
    private String initListViewData()  {
        moviesList.clear();
        String searchWord = searchEditTxt.getText().toString();
        SQLiteDatabase db = database.getReadableDatabase();
        SELECTION ="movie like  \"%"+searchWord+"%\" or actors like \"%"+searchWord+"%\" or director like \"%"+searchWord+"%\"";
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
        return searchWord;
    }

    //Adding data from database to lists.
    private void data()  {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, FROM, null,
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

            autoCompletion.add(title);
            autoCompletion.add(director);

            String[] splited = acts.split(",");
            System.out.println(Arrays.toString(splited));
            Collections.addAll(autoCompletion, splited);
        }
        cursor.close();
    }
}