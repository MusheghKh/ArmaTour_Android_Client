package com.armatour.aramtour.view.tour_companies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.armatour.aramtour.R;
import com.armatour.aramtour.models.TourCompany;
import com.bumptech.glide.Glide;

import java.util.List;

public class TourComapaniesAdapter extends RecyclerView.Adapter<TourComapaniesAdapter.ViewHolder>{

    private List<TourCompany> data;
    private static OnItemClickListener listener;

    public TourComapaniesAdapter(List<TourCompany> data, OnItemClickListener listener) {
        this.data = data;
        TourComapaniesAdapter.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tour_company, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TourCompany tourCompany = data.get(position);
        holder.title.setText(tourCompany.getTitle());
        String url = "https://s3.amazonaws.com/rezgo/12283/media/images/logo.png";
        Glide.with(holder.itemView.getContext()).load(url).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tour_company_title);
            image = (ImageView) itemView.findViewById(R.id.tour_company_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemCLick(v, getLayoutPosition());
        }
    }

    interface OnItemClickListener{
        void onItemCLick(View view, int position);
    }
}
