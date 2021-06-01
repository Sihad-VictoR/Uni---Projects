package iit.l5.tut.flickattic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static iit.l5.tut.flickattic.Constants.MOVIE_ACTS;
import static iit.l5.tut.flickattic.Constants.MOVIE_DIRECTOR;
import static iit.l5.tut.flickattic.Constants.MOVIE_FAVOURITES;
import static iit.l5.tut.flickattic.Constants.MOVIE_RATING;
import static iit.l5.tut.flickattic.Constants.MOVIE_REVIEW;
import static iit.l5.tut.flickattic.Constants.MOVIE_TITLE;
import static iit.l5.tut.flickattic.Constants.MOVIE_YEAR;
import static iit.l5.tut.flickattic.Constants.TABLE_NAME;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME =
            "movieList.db";
    private static final int DATABASE_VERSION = 1;

    public Database(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MOVIE_TITLE + " TEXT NOT NULL,"
                + MOVIE_YEAR + " TEXT NOT NULL,"
                + MOVIE_DIRECTOR + " TEXT NOT NULL,"
                + MOVIE_ACTS+ " TEXT NOT NULL,"
                + MOVIE_RATING + " TEXT NOT NULL,"
                + MOVIE_REVIEW + " TEXT NOT NULL,"
                + MOVIE_FAVOURITES + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
