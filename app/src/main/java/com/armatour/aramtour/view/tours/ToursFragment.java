package com.armatour.aramtour.view.tours;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armatour.aramtour.R;
import com.armatour.aramtour.models.Place;
import com.armatour.aramtour.models.Tour;
import com.armatour.aramtour.utils.DataGenerator;
import com.armatour.aramtour.view.places.PlaceActivity;

import java.util.List;

public class ToursFragment extends Fragment implements ToursAdapter.OnItemClickListener
{

    private RecyclerView recyclerView;
    private List<Tour> data;

    public static ToursFragment newInstance() {
        ToursFragment fragment = new ToursFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tours, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.tours_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        data = DataGenerator.generateTours();
        RecyclerView.Adapter adapter = new ToursAdapter(data, this);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        Tour tour = data.get(position);
        Intent intent = new Intent(getContext(), TourActivity.class);
        intent.putExtra(TourActivity.TITLE, tour.getTitle());
        intent.putExtra(TourActivity.DESCRIPTION, tour.getDescription());
        startActivity(intent);
    }
}
