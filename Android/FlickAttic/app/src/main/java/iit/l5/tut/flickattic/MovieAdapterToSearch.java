package iit.l5.tut.flickattic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapterToSearch extends RecyclerView.Adapter<MovieAdapterToSearch.MovieHolder>{
    Context context;
    List<Movie> list = new ArrayList<Movie>();
    String searchWord;

    public MovieAdapterToSearch(Context context, List<Movie> list,String word) {
        this.context = context;
        this.list = list;
        this.searchWord = word;
    }

    @Override
    public MovieAdapterToSearch.MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(context).inflate(R.layout.item_movie_search,parent,false);

        return new MovieAdapterToSearch.MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieAdapterToSearch.MovieHolder holder, final int position) {

        final Movie movie = list.get(position);

        holder.mTitleNameRView.setText(movie.getMTitle());
        holder.mYearRView.setText(movie.getMYear());
        holder.mRatingRView.setText(movie.getMRating());
        holder.mDirector.setText(movie.getMDirector());
        holder.mCast.setText(movie.getMActs());

        if(movie.getMTitle().toLowerCase().contains(searchWord.toLowerCase())) {
            holder.mTitleNameRView.setTypeface(null, Typeface.BOLD);
        }
        if(movie.getMDirector().toLowerCase().contains(searchWord.toLowerCase())) {
            holder.mDirector.setTypeface(null, Typeface.BOLD);
        }
        if(movie.getMActs().toLowerCase().contains(searchWord.toLowerCase())) {
            holder.mCast.setTypeface(null, Typeface.BOLD);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder{

        TextView mTitleNameRView, mYearRView,mRatingRView ,mDirector , mCast;


        public MovieHolder(View itemView) {
            super(itemView);

            mTitleNameRView = itemView.findViewById(R.id.search_title);
            mYearRView = itemView.findViewById(R.id.search_Year);
            mRatingRView = itemView.findViewById(R.id.search_Rating);
            mDirector = itemView.findViewById(R.id.search_Director);
            mCast = itemView.findViewById(R.id.search_cast);
        }
    }
    public List<Movie> getMoviesList(){
        return list;
    }
}