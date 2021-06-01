package iit.l5.tut.flickattic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewRating extends AppCompatActivity {

    //Declaring a an Adapter to Recycler  view and other variables.
    MovieAdapterToRating adapter;
    ProgressDialog progress;
    Movie movie = null;
    List<MovieRating> movieRatingList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rating);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_rating_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        if(getIntent().getExtras() != null) {
            movie = (Movie) getIntent().getSerializableExtra("Movie");
        }
        if(movie!= null){
            searchMovie(movie.getMTitle().toLowerCase());
        }


    }

    public void searchMovie(String queryString) {
        new GetMovies().execute(queryString);
    }

    private class GetMovies extends AsyncTask<String, String, MovieRating > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(ViewRating.this);
            progress.setMessage("Please wait...It is downloading");
            progress.setIndeterminate(false);
            progress.setCancelable(false);
            progress.show();
        }
        @Override
        protected MovieRating doInBackground(String... strings) {
            // Some long-running task like downloading an image.
            String data = NetworkUtils.getMovieInfo(strings[0],true);

            String title;
            String id;
            String description;
            String imageURL;
            MovieRating movieRating = null;
            try {
                JSONObject response = new JSONObject(data);
                JSONArray movies = response.getJSONArray("results");
                for(int i=0;i<movies.length();i++){
                    JSONObject movieObject = movies.getJSONObject(i);
                    title = movieObject.getString("title");
                    id = movieObject.getString("id");
                    description = movieObject.getString("description");
                    imageURL = movieObject.getString("image");

                    movieRating = new MovieRating(id,title,description,imageURL);
                    movieRatingList.add(movieRating);
                }



            } catch (JSONException e) {
                if(progress.isShowing()) {
                    progress.dismiss();
                }
                e.printStackTrace();
            }
            return movieRating;
        }

        @Override
        protected void onPostExecute(MovieRating bitmap) {
            super.onPostExecute(bitmap);
//            System.out.println(movieRatingList);
            for(int j = 0;j<movieRatingList.size();j++){
                new GetMoviesRating().execute(movieRatingList.get(j).getId());
            }


        }
    }

    private class GetMoviesRating extends AsyncTask<String, String, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.show();
        }
        @Override
        protected String doInBackground(String... strings) {
            // Some long-running task like downloading an image.
            String data = NetworkUtils.getMovieInfo(strings[0],false);

            String rating ="No data received";
            MovieRating movieRating = null;
            try {
                 JSONObject response = new JSONObject(data);
                 rating = response.getString("imDb");

                for(int j = 0;j<movieRatingList.size();j++){
                    if(movieRatingList.get(j).getId().equals(strings[0])){
                        movieRatingList.get(j).setRating(rating);
                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return rating;
        }

        @Override
        protected void onPostExecute(String bitmap) {
            super.onPostExecute(bitmap);

            if(bitmap.equals(movieRatingList.get(movieRatingList.size()-1).getRating())){
                adapter = new MovieAdapterToRating(ViewRating.this,movieRatingList);
                recyclerView.setAdapter(adapter);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (progress.isShowing()){
                        progress.dismiss();

                    }
                }},5000);

        }
    }
}