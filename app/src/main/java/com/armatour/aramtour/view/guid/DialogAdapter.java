package com.armatour.aramtour.view.guid;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.armatour.aramtour.R;
import com.armatour.aramtour.models.Place;
import com.bumptech.glide.Glide;

import java.util.List;

public class DialogAdapter extends ArrayAdapter<Place>{


    public DialogAdapter(@NonNull Context context, @LayoutRes int resource, List<Place> data) {
        super(context, resource, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_place, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.placeImage = (ImageView) convertView.findViewById(R.id.place_image);
            viewHolder.placeTitle = (TextView) convertView.findViewById(R.id.place_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Place place = getItem(position);
        viewHolder.placeTitle.setText(place.getTitle());
        Glide.with(getContext()).load("http://www.hayweb.ru/uploads/posts/2014-06/1404147522_hor-virap-3.jpg").into(viewHolder.placeImage);

        return convertView;
    }

    private static class ViewHolder{
        TextView placeTitle;
        ImageView placeImage;
    }
}
