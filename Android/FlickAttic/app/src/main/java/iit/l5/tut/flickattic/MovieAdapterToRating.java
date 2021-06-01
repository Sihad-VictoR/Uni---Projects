package iit.l5.tut.flickattic;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapterToRating extends RecyclerView.Adapter<MovieAdapterToRating.MovieHolder>{
    Context context;
    List<MovieRating> list = new ArrayList<MovieRating>();
    URL ImageUrl = null;
    InputStream is = null;
    Bitmap bmImg = null;
    ImageView imageView= null;
    ProgressDialog p;


    public MovieAdapterToRating(Context context, List<MovieRating> list) {
        this.context = context;
        this.list = list;

        //initilize selectCheck

    }

    @Override
    public MovieAdapterToRating.MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(context).inflate(R.layout.rating_item,parent,false);

        return new MovieAdapterToRating.MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieAdapterToRating.MovieHolder holder, final int position) {

        final MovieRating movie = list.get(position);

        holder.mTitleNameRView.setText(movie.getTitle());
        holder.mRatingRView.setText(movie.getRating());

        holder.mTitleNameRView.setOnClickListener(v -> {
            if (holder.hiddenView.getVisibility() == View.GONE) {
                new AsyncTaskExample().execute(movie.getImageURL());
                if(p.isShowing()){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TransitionManager.beginDelayedTransition(holder.cardView,
                                    new AutoTransition());
                            holder.hiddenView.setVisibility(View.VISIBLE);

                            if(bmImg!= null){
                                holder.mImageView.setImageBitmap(bmImg);
                            }else{
                                holder.mImageView.setImageResource(R.drawable.ic_baseline_image_24);
                            }
                            p.dismiss();
                        }},5000);

                }else {
                    TransitionManager.beginDelayedTransition(holder.cardView,
                            new AutoTransition());
                    holder.hiddenView.setVisibility(View.VISIBLE);

                    if(bmImg!= null){
                        holder.mImageView.setImageBitmap(bmImg);
                    }else{
                        holder.mImageView.setImageResource(R.drawable.ic_baseline_image_24);
                    }
                }

            }else {
                TransitionManager.beginDelayedTransition(holder.cardView,
                        new AutoTransition());
                holder.hiddenView.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder{

        TextView mTitleNameRView,mRatingRView ;
        ImageView mImageView;
        LinearLayout hiddenView;
        CardView cardView;


        public MovieHolder(View itemView) {
            super(itemView);

            mTitleNameRView = itemView.findViewById(R.id.rating_title_Name_view2);
            mRatingRView = itemView.findViewById(R.id.rating_rating_view2);
            mImageView = itemView.findViewById(R.id.rating_imageView_view2);
            hiddenView = itemView.findViewById(R.id.hidden_view);
            cardView = itemView.findViewById(R.id.base_cardview);
        }
    }
    public List<MovieRating> getMoviesList(){
        return list;
    }

    private class AsyncTaskExample extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new ProgressDialog(context);
            p.setMessage("Please wait...It is downloading");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                ImageUrl = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) ImageUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bmImg = BitmapFactory.decodeStream(is, null, options);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmImg;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

        }
    }

}
