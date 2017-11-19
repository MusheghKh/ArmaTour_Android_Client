package com.armatour.aramtour.view.motels;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.armatour.aramtour.R;
import com.armatour.aramtour.models.Motel;
import com.bumptech.glide.Glide;

import java.util.List;

public class MotelsAdapter extends RecyclerView.Adapter<MotelsAdapter.ViewHolder>{

    private List<Motel> data;

    public MotelsAdapter(List<Motel> data) {
        this.data = data;
    }

    @Override
    public MotelsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_motel, parent, false);

        return new MotelsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MotelsAdapter.ViewHolder holder, int position) {
        Motel motel = data.get(position);
        holder.motelTitle.setText(motel.getTitle());
        String url = "http://amari.azureedge.net/phuket/hotel-photos/superior-room-1.jpg";
        Glide.with(holder.itemView.getContext()).load(url).into(holder.motelImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView motelTitle;
        ImageView motelImage;

        public ViewHolder(View itemView) {
            super(itemView);
            motelImage = (ImageView) itemView.findViewById(R.id.motel_image);
            motelTitle = (TextView) itemView.findViewById(R.id.motel_title);
        }
    }
}
