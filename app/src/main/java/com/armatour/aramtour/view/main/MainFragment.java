package com.armatour.aramtour.view.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.armatour.aramtour.R;
import com.armatour.aramtour.view.guid.GuideFragment;
import com.armatour.aramtour.view.motels.MotelsFragment;
import com.armatour.aramtour.view.places.PlacesFragment;
import com.armatour.aramtour.view.tours.ToursFragment;

public class MainFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView navigation;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        navigation = (BottomNavigationView) root.findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        FragmentManager fm = getChildFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_container);
        if (fragment == null){
            fragment = PlacesFragment.newInstance();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.main_container, fragment);
            transaction.commit();
        }

        return root;
    }

    public void selectTab(int tabId){
        navigation.setSelectedItemId(tabId);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fm = getChildFragmentManager();
        Fragment fragment;

        switch (item.getItemId()){
            case R.id.bottom_menu_guide:
                fragment = GuideFragment.newInstance();
                break;
            case R.id.bottom_menu_motels:
                fragment = MotelsFragment.newInstance();
                break;
            case R.id.bottom_menu_places:
                fragment = PlacesFragment.newInstance();
                break;
            case R.id.bottom_menu_tours:
                fragment = ToursFragment.newInstance();
                break;
            default:
                fragment = PlacesFragment.newInstance();
                break;
        }
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.commit();
        return true;
    }
}
