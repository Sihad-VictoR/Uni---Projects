package iit.l5.tut.flickattic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapterToEditMovies extends RecyclerView.Adapter<MovieAdapterToEditMovies.MovieHolder>{

    Context context;
    List<Movie> list = new ArrayList<Movie>();


    public MovieAdapterToEditMovies(Context context, List<Movie> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MovieAdapterToEditMovies.MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(context).inflate(R.layout.item2_movie,parent,false);

        return new MovieAdapterToEditMovies.MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieAdapterToEditMovies.MovieHolder holder, final int position) {

        final Movie movie = list.get(position);

        holder.mTitleNameRView.setText(movie.getMTitle());
        holder.mYearRView.setText(movie.getMYear());
        holder.mRatingRView.setText(movie.getMRating());
        holder.mDirector.setText(movie.getMDirector());

        holder.mTitleNameRView.setTag(list.get(position));

        holder.mTitleNameRView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "";
                Movie movie1 = (Movie)holder.mTitleNameRView.getTag();

                Intent intent;
                intent = new Intent(context, EditActivityValues.class);
                intent.putExtra("Movie", movie1);
                context.startActivity(intent);
                ((Activity) context).finish();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder{

        TextView mTitleNameRView, mYearRView,mRatingRView ,mDirector;


        public MovieHolder(View itemView) {
            super(itemView);

            mTitleNameRView = itemView.findViewById(R.id.movieItemNameEdit);
            mYearRView = itemView.findViewById(R.id.movieItemYearEdit);
            mRatingRView = itemView.findViewById(R.id.movieItemRatingEdit);
            mDirector = itemView.findViewById(R.id.movieItemDirectorEdit);
        }
    }
    public List<Movie> getMoviesList(){
        return list;
    }

}