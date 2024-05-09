package com.flag.bookmyticket;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookingsAdapter extends RecyclerView.Adapter <BookingsAdapter.viewHolder>{

    Context context;
    Activity activity;
    ArrayList<Bookings> arrayList;
    SQLiteHelper database_helper;

    public BookingsAdapter(Context context,Activity activity, ArrayList<Bookings> arrayList) {
        this.context = context;
        this.activity  = activity ;
        this.arrayList = arrayList;
    }

    @Override
    public BookingsAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.bookings_adapter_layout, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BookingsAdapter.viewHolder holder, final int position) {
        holder.title.setText(arrayList.get(position).getTitle());
        holder.screen.setText(arrayList.get(position).getDate()+" | "+arrayList.get(position).getScreen()+" | "+arrayList.get(position).getShow());
        holder.seats.setText(arrayList.get(position).getSeats());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title, screen, seats;
        public viewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            screen = (TextView) itemView.findViewById(R.id.screendetails);
            seats = (TextView) itemView.findViewById(R.id.seats);
        }
    }

}