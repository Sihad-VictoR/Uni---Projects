package iit.l5.tut.flickattic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapterToDisplayWithCheckBox extends RecyclerView.Adapter<MovieAdapterToDisplayWithCheckBox.MovieHolder>{

    Context context;
    List<Movie> list = new ArrayList<Movie>();
    Boolean value;
    private int selectedPosition = 0;
    private ArrayList<Integer> selectCheck = new ArrayList<>();

    public List<Movie> getReturnList() {
        return returnList;
    }

    List<Movie> returnList = new ArrayList<Movie>();

    public MovieAdapterToDisplayWithCheckBox(Context context, List<Movie> list,boolean value) {
        this.context = context;
        this.list = list;
        this.value =value;

        for (int i = 0; i < list.size(); i++) {
            selectCheck.add(0);
        }
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, final int position) {


        final Movie movie = list.get(position);

        holder.mTitleNameRView.setText(movie.getMTitle());
        holder.mYearRView.setText(movie.getMYear());
        holder.mRatingRView.setText(movie.getMRating());
        if(value){

            holder.checkBox.setChecked(movie.isMFavorites());
            holder.checkBox.setTag(list.get(position));


            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String data = "";
                    Movie movie1 = (Movie)holder.checkBox.getTag();

                    movie1.setMFavorites(holder.checkBox.isChecked());

                    list.get(position).setMFavorites(holder.checkBox.isChecked());

                    data = movie.getMTitle()+" is selected!";

                    Toast.makeText(context,data, Toast.LENGTH_SHORT).show();
                }
            });
        }else {
//            holder.checkBox.setChecked(false);
//            holder.checkBox.setTag(list.get(position));
//            holder.checkBox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(returnList.size()>0 && movie != returnList.get(0)){
//                        Toast.makeText(context,"Already an Item Selected!", Toast.LENGTH_SHORT).show();
//                    }
//                    if(!holder.checkBox.isChecked() && movie == returnList.get(0)){
//                        returnList.clear();
//                    }
//                    if(holder.checkBox.isChecked() && returnList.size()<1){
//                        returnList.add(movie);
//                    }else {
//                        holder.checkBox.setChecked(false);
//                    }
//
//                }
//            });
            holder.setIsRecyclable(false);
            if (selectCheck.get(position) == 1) {
                holder.checkBox.setChecked(true);
            } else {
                holder.checkBox.setChecked(false);
            }

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int k=0; k<selectCheck.size(); k++) {
                        if(k==position) {
                            selectCheck.set(k,1);
                        } else {
                            selectCheck.set(k,0);
                        }
                    }
                    notifyDataSetChanged();

                }
            });
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked==true){
                        returnList.clear();
                        returnList.add(movie);
                    }
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder{

        TextView mTitleNameRView, mYearRView,mRatingRView ;
        CheckBox checkBox;

        public MovieHolder(View itemView) {
            super(itemView);

            mTitleNameRView = itemView.findViewById(R.id.movieItemName);
            mYearRView = itemView.findViewById(R.id.movieItemYear);
            mRatingRView = itemView.findViewById(R.id.movieItemRating);
            checkBox = itemView.findViewById(R.id.movieItemCheckBox);
        }
    }
    public List<Movie> getMoviesList(){
        return list;
    }
}
