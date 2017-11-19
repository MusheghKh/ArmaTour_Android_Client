package com.armatour.aramtour.view.places;

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
import com.armatour.aramtour.utils.DataGenerator;

import java.util.List;

public class PlacesFragment extends Fragment implements PlacesAdapter.OnItemClickListener{

    private RecyclerView recyclerView;
    private List<Place> data;

    public static PlacesFragment newInstance() {
        PlacesFragment fragment = new PlacesFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_places, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.paces_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        data = DataGenerator.generatePlaces();
        RecyclerView.Adapter adapter = new PlacesAdapter(data, this);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        Place place = data.get(position);
        Intent intent = new Intent(getContext(), PlaceActivity.class);
        intent.putExtra(PlaceActivity.TITLE, place.getTitle());
        intent.putExtra(PlaceActivity.DESCRIPTION, place.getDescription());
        startActivity(intent);
    }
}
