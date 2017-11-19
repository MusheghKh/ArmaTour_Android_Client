package com.armatour.aramtour.view.motels;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armatour.aramtour.R;
import com.armatour.aramtour.utils.DataGenerator;
import com.armatour.aramtour.view.places.PlacesAdapter;

public class MotelsFragment extends Fragment{

    private RecyclerView recyclerView;

    public static MotelsFragment newInstance() {
        MotelsFragment fragment = new MotelsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_motels, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.motels_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new MotelsAdapter(DataGenerator.generateMotels());
        recyclerView.setAdapter(adapter);

        return root;
    }
}
