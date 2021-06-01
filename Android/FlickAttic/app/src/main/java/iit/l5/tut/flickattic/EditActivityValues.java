package iit.l5.tut.flickattic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
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

public class EditActivityValues extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Declaring variables.
    RatingBar rBar;
    Button btn;
    Database database;
    String[] values = { "Favourite", "Not Favourite"};
    float ratingValue = 0f;
    Movie movie = null;
    EditText mName,mDirector,mYear,mCast,mReview;

    //A List to contain Movie Elements
    private List<Movie> moviesList = new ArrayList<>();

    //From and order Strings to perform database Functions
    private static String[] FROM = { MOVIE_ID,MOVIE_TITLE,MOVIE_YEAR,MOVIE_DIRECTOR,MOVIE_ACTS,MOVIE_RATING,MOVIE_REVIEW,MOVIE_FAVOURITES};
    private static String ORDER_BY = MOVIE_TITLE + " ASC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_values);

        //Setting up elements.
        btn = (Button)findViewById(R.id.regSaveBtnEdit);
        database = new Database(this);

        //Getting intent to get the object passed
        Intent intent = getIntent();
        if(getIntent().getExtras() != null) {
            movie = (Movie) getIntent().getSerializableExtra("Movie");
        }

        //adding data to list method
        initListViewData();

        mName = (EditText) findViewById(R.id.nameEditViewEdit);
        mDirector = (EditText) findViewById(R.id.dirEditViewEdit);
        mYear = (EditText) findViewById(R.id.yearEditViewEdit);
        mCast = (EditText) findViewById(R.id.castEditViewEdit);
        mReview = (EditText) findViewById(R.id.reviewEditViewEdit);
        Spinner spin = (Spinner) findViewById(R.id.spinnerEdit);
        rBar = (RatingBar) findViewById(R.id.ratingBarEdit);

        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, values);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        //Setting up values to edit.
        if (movie != null) {
            mName.setText(movie.getMTitle());
            mDirector.setText(movie.getMDirector());
            mYear.setText(movie.getMYear());
            mCast.setText(movie.getMActs());
            if(movie.isMFavorites()){
                values[0] = "Favourite";
                values[1] = "Not Favourite";
            }else {
                values[0] = "Not Favourite";
                values[1] = "Favourite";
            }
            mReview.setText(movie.getMReview());
            ratingValue = Float.parseFloat(movie.getMRating());
        }

        //Setting up rating Bar
        rBar.setRating(ratingValue);
        rBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating < 1 || rating>10){
                    Toast.makeText(EditActivityValues.this,"Rating cannot be zero or larger than 10", Toast.LENGTH_SHORT).show();
                    rBar.setRating(ratingValue);
                }else {
                    ratingValue = rating;
                    Toast.makeText(EditActivityValues.this," Rating set "+String.valueOf(rating), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Checking for existing title name.
        mName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String checkName = mName.getText().toString();
                for (Movie i : moviesList){
                    if(checkName.toLowerCase().equals(i.getMTitle().toLowerCase())){
                        Toast.makeText(EditActivityValues.this, "Name Already Exists", Toast.LENGTH_SHORT).show();
                        mName.setText(movie.getMTitle());
                    }
                }
            }
        });

        //updating after validating
        btn.setOnClickListener(v -> {

            if(validate()) {
                try {
                    ContentValues values = new ContentValues();
                    SQLiteDatabase db = database.getWritableDatabase();

                    values.put(MOVIE_TITLE, mName.getText().toString());
                    values.put(MOVIE_YEAR, mYear.getText().toString());
                    values.put(MOVIE_DIRECTOR, mDirector.getText().toString());
                    values.put(MOVIE_ACTS, mCast.getText().toString());
                    values.put(MOVIE_RATING, ratingValue);
                    values.put(MOVIE_REVIEW, mReview.getText().toString());
                    values.put(MOVIE_FAVOURITES, movie.isMFavorites());
                    int i = db.update(TABLE_NAME, values, MOVIE_ID + " = \"" + movie.getMId() + "\"", null);

                    if (i > 0) {
                        Toast.makeText(this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Database Error!Try Again Fixing Error", Toast.LENGTH_SHORT).show();
                    }

                } finally {
                    database.close();
                }
            }
        });
    }

    //Validating method according to user inputs.
    public Boolean validate(){
        boolean validate = true;

        if (mName.getText().toString().length() < 1) {
            mName.setError("Movie Name cannot be Zero!");
            validate =false;
        }
        if (mDirector.length() < 2) {
            mDirector.setError("Enter valid names!");
            validate =false;
        }
        if (mCast.length() < 2) {
            mCast.setError("Cast cannot be small!");
            validate =false;
        }
        if (mReview.length() < 2) {
            mReview.setError("Write valid reviews!");
            validate =false;
        }
        int rYear = 0;
        if(mYear.getText().toString().equals("")){
            rYear = 0;
        }else rYear = Integer.parseInt(mYear.getText().toString());

        if (rYear < 1895 || rYear > 2021) {
            mYear.setError("Invalid Year! 2021 > Year > 1895");
            validate =false;
        }


        return validate;
    }

    //adding data to list method
    private void initListViewData()  {

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
            boolean favorite;
            if(bool.equals("1")){
                favorite = true;
            }else favorite = false;

            Movie movie1 = new Movie(id,title,year,director,acts,rating,review,favorite);
            if(movie.getMTitle().toLowerCase().equals(title.toLowerCase())){
                System.out.println();
            }else moviesList.add(movie1);
        }
        cursor.close();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(values[position].equals("Not Favourite")){
            movie.setMFavorites(false);
        }else movie.setMFavorites(true);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, EditActivity.class));
        finish();
    }
}