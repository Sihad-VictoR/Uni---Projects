package iit.l5.tut.flickattic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
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

public class RegisterActivity extends AppCompatActivity {
    //Declaring Variables.
    private String mName,mDirector,mCast,mReview;
    private int mYear,mRating;
    private EditText rName;
    private EditText rDirector ;
    private EditText rYear ;
    private EditText rCast;
    private EditText rRating ;
    private EditText rReview;
    private Database database;

    //A List to contain Movie Elements
    private List<Movie> moviesList = new ArrayList<>();

    //From and order Strings to perform database Functions
    private static String[] FROM = { MOVIE_ID,MOVIE_TITLE,MOVIE_YEAR,MOVIE_DIRECTOR,MOVIE_ACTS,MOVIE_RATING,MOVIE_REVIEW,MOVIE_FAVOURITES};
    private static String ORDER_BY = MOVIE_TITLE + " ASC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Setting up Elements.
        Button rBtn = (Button) findViewById(R.id.regSaveBtn);
        rName = (EditText) findViewById(R.id.nameEditView);
        rDirector = (EditText) findViewById(R.id.dirEditView);
        rYear = (EditText) findViewById(R.id.yearEditView);
        rCast = (EditText) findViewById(R.id.castEditView);
        rRating = (EditText) findViewById(R.id.ratingEditView);
        rReview = (EditText) findViewById(R.id.reviewEditView);

        database = new Database(this);
        //calling method to add data to list.
        initListViewData();

        //Validating and calling method to add to database.
        rBtn.setOnClickListener(v -> {
            mName = rName.getText().toString();
            mDirector = rDirector.getText().toString();
            mCast = rCast.getText().toString();
            mReview = rReview.getText().toString();
            if(rYear.getText().toString().equals("")){
                mYear = 0;
            }else mYear = Integer.parseInt(rYear.getText().toString());
            if(rRating.getText().toString().equals("")){
                mRating = 0;
            }else mRating = Integer.parseInt(rRating.getText().toString());

            if(validate()){
                if(addToDatabase()){
                    Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    rName.setText("");
                    rDirector.setText("");
                    rYear.setText("");
                    rCast.setText("");
                    rRating.setText("");
                    rReview.setText("");
                }else{
                    Toast.makeText(this, "Database Error!Try Again Fixing Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //Checking for existing title name.
        rName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String checkName = rName.getText().toString();
                for (Movie i : moviesList){
                    if(checkName.toLowerCase().equals(i.getMTitle().toLowerCase())){
                        Toast.makeText(RegisterActivity.this, "Name Already Exists", Toast.LENGTH_SHORT).show();
                        rName.setText("");
                    }
                }
            }
        });

    }
    //A Method to validate user inputs.
    public Boolean validate(){
        boolean validate = true;

        if (mName.length() < 1) {
            rName.setError("Movie Name cannot be Zero!");
            validate =false;
        }
        if (mDirector.length() < 2) {
            rDirector.setError("Enter valid names!");
            validate =false;
        }
        if (mCast.length() < 2) {
            rCast.setError("Cast cannot be small!");
            validate =false;
        }
        if (mReview.length() < 2) {
            rReview.setError("Write valid reviews!");
            validate =false;
        }
        if (mYear < 1895 || mYear > 2021) {
            rYear.setError("Invalid Year! 2021 > Year > 1895");
            validate =false;
        }
        if(mRating < 1 || mRating>10){
            rRating.setError("Rating cannot be zero or larger than 10");
            validate =false;
        }

        return validate;
    }
    //A Method to add Data to Database
    public boolean addToDatabase(){
        try {
            SQLiteDatabase db = database.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(MOVIE_TITLE,mName);
            values.put(MOVIE_YEAR, mYear);
            values.put(MOVIE_DIRECTOR, mDirector);
            values.put(MOVIE_ACTS, mCast);
            values.put(MOVIE_RATING, mRating);
            values.put(MOVIE_REVIEW, mReview);
            values.put(MOVIE_FAVOURITES, "false");
            db.insertOrThrow(TABLE_NAME, null, values);

            return true;
        } catch (SQLiteException e) {
            return false;
        } finally {
            database.close();
        }

    }
    //A Method to get Data from Database
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

            Movie movie = new Movie(id,title,year,director,acts,rating,review,favorite);
            moviesList.add(movie);
            System.out.println(movie);
        }
        cursor.close();
    }

    //clear all edit text views.
    public void clearAll(View view) {
        rName.setText("");
        rDirector.setText("");
        rYear.setText("");
        rCast.setText("");
        rRating.setText("");
        rReview.setText("");
    }
}