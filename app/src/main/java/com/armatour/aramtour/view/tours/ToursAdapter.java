package com.armatour.aramtour.view.tours;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.armatour.aramtour.R;
import com.armatour.aramtour.models.Tour;
import com.bumptech.glide.Glide;

import java.util.List;

public class ToursAdapter extends RecyclerView.Adapter<ToursAdapter.ViewHolder>{

    private List<Tour> data;
    private static ToursAdapter.OnItemClickListener listener;


    public ToursAdapter(List<Tour> data, OnItemClickListener listener) {
        this.data = data;
        ToursAdapter.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tour tour = data.get(position);
        holder.tourTitle.setText(tour.getTitle());
        String url = "http://www.freetourssydney.com.au/images/i-am-free-tours-guide.jpg";
        Glide.with(holder.itemView.getContext()).load(url).into(holder.tourImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tourTitle;
        ImageView tourImage;

        public ViewHolder(View itemView) {
            super(itemView);
            tourImage = (ImageView) itemView.findViewById(R.id.tour_image);
            tourTitle = (TextView) itemView.findViewById(R.id.tour_company_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getLayoutPosition());
        }
    }

    interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
