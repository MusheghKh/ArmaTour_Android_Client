package com.armatour.aramtour.view.guid;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.armatour.aramtour.R;
import com.armatour.aramtour.utils.DataGenerator;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;

import java.io.IOException;

public class GuideFragment extends Fragment implements View.OnClickListener, OnItemClickListener, LocationListener {

    private static final String AuDIO_URL = "http://storage.feems.com/music/Fab4ish/John%20Lennon%20-%20Imagine.mp3";

    private ImageButton playpause;
    private ImageButton forward;
    private ImageButton rewind;
    private ProgressBar progressBar;
    private DialogPlus dialog;

    private MediaPlayer mediaPlayer;
    private TrackStatus trackStatus = TrackStatus.STOPPED;
    private int playbackPosition = 0;

    private LocationManager locationManager;

    public static GuideFragment newInstance() {
        GuideFragment fragment = new GuideFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_guide, container, false);

        playpause = (ImageButton) root.findViewById(R.id.guide_play_pause);
        playpause.setOnClickListener(this);

        forward = (ImageButton) root.findViewById(R.id.guide_forward);
        forward.setOnClickListener(this);

        rewind = (ImageButton) root.findViewById(R.id.guide_rewind);
        rewind.setOnClickListener(this);

        progressBar = (ProgressBar) root.findViewById(R.id.speech_progress);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        } else {
            locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10000, this);
        }

        DialogAdapter dialogAdapter = new DialogAdapter(getContext(), R.layout.item_place, DataGenerator.generatePlaces());
        dialog = DialogPlus.newDialog(getContext())
                .setAdapter(dialogAdapter)
                .setOnItemClickListener(this)
                .setExpanded(true)
                .setHeader(R.layout.header_choose_place)
                .setCancelable(true)
                .create();
        dialog.show();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if (mediaPlayer != null){
            try{
                mediaPlayer.release();
                mediaPlayer = null;
            }catch (Exception e){

            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guide_play_pause:

                if (trackStatus == TrackStatus.STOPPED){
                    releaseMediaPlayer();

                    try {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(AuDIO_URL);
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                            @Override
//                            public void onPrepared(MediaPlayer mp) {
//                                mediaPlayer.start();
//                            }
//                        });
//                        mediaPlayer.prepareAsync();
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    }catch (IOException e){

                    }
                    trackStatus = TrackStatus.PLAYING;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        playpause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play, null));
                    } else {
                        playpause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play));
                    }
                }else if (trackStatus == TrackStatus.PLAYING){
                    if (mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                    }
                    trackStatus = TrackStatus.PAUSED;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        playpause.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause, null));
                    } else {
                        playpause.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause));
                    }
                }else if (trackStatus == TrackStatus.PAUSED){
                    mediaPlayer.start();
                    trackStatus = TrackStatus.PLAYING;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        playpause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play, null));
                    } else {
                        playpause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play));
                    }
                }
                break;
            case R.id.guide_rewind:
                Toast.makeText(getContext(), "rewind", Toast.LENGTH_SHORT).show();
                break;
            case R.id.guide_forward:
                Toast.makeText(getContext(), "forward", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
        Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getContext(), "long: " + location.getLongitude() + " lat: " + location.getLatitude(), Toast.LENGTH_SHORT).show();
        locationManager.removeUpdates(this);
        locationManager = null;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private enum TrackStatus {
        PLAYING, PAUSED, STOPPED
    }
}
