package com.armatour.aramtour.view.places;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.armatour.aramtour.R;
import com.armatour.aramtour.models.Place;
import com.bumptech.glide.Glide;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder>{

    private List<Place> data;
    private static OnItemClickListener listener;

    public PlacesAdapter(List<Place> data, OnItemClickListener listener) {
        this.data = data;
        PlacesAdapter.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemText.setText(String.format("item %s", position));
        String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/%D4%B3%D5%A1%D5%BC%D5%B6%D5%AB%D5%AB_%D5%8F%D5%A1%D5%B3%D5%A1%D6%80_23.jpg/350px-%D4%B3%D5%A1%D5%BC%D5%B6%D5%AB%D5%AB_%D5%8F%D5%A1%D5%B3%D5%A1%D6%80_23.jpg";
        Glide.with(holder.itemView.getContext()).load(url).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Context context;
        TextView itemText;
        ImageView itemImage;

        ViewHolder(View itemView) {
            super(itemView);
            itemText = (TextView) itemView.findViewById(R.id.place_title);
            itemImage = (ImageView) itemView.findViewById(R.id.place_image);
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
