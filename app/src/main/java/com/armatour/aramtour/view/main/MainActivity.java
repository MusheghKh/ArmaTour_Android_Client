package com.armatour.aramtour.view.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.armatour.aramtour.R;
import com.armatour.aramtour.utils.ViewUtil;
import com.armatour.aramtour.view.map.MapActivity;
import com.armatour.aramtour.view.search.SearchActivity;
import com.armatour.aramtour.view.settings.SettingsActivity;
import com.armatour.aramtour.view.tour_companies.TourCompaniesActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.main_content);
        if (fragment == null) {
            fragment = MainFragment.newInstance();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction = transaction.add(R.id.main_content, fragment);
            transaction.commit();
        }

        ProgressDialog dialog = ViewUtil.createProgressDialog(this);
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent i = null;

        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_tour_companies:
                i = new Intent(this, TourCompaniesActivity.class);
                break;
            case R.id.nav_guide:
                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.main_content);
                if (fragment instanceof MainFragment){
                    ( (MainFragment) fragment).selectTab(R.id.bottom_menu_guide);
                }
                break;
            case R.id.nav_map:
                i = new Intent(this, MapActivity.class);
                break;
            case R.id.nav_search:
                i = new Intent(this, SearchActivity.class);
                break;
            case R.id.nav_settings:
                i = new Intent(this, SettingsActivity.class);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if (i != null) {
            startActivity(i);
        }

        return true;
    }
}
