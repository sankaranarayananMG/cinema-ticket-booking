package com.flag.bookmyticket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private List<Movie> movieList;
    private ClickListener<Movie> clickListener;

    RecyclerViewAdapter(List<Movie> movieList){
        this.movieList = movieList;
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_adapter_layout,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, final int position) {

        final Movie movie = movieList.get(position);

        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.rating.setText(movie.getRating()+" | "+movie.getRuntime());
        holder.image.setBackgroundResource(movie.getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(movie);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setOnItemClickListener(ClickListener<Movie> movieClickListener) {
        this.clickListener = movieClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView rating;
        private TextView genre;
        private ImageView image;
        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            rating = itemView.findViewById(R.id.rating);
            genre = itemView.findViewById(R.id.genre);
            image = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.carView);
        }
    }
}
interface ClickListener<T> {
    void onItemClick(T data);
}