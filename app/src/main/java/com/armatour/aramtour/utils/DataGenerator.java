package com.armatour.aramtour.utils;

import com.armatour.aramtour.models.Motel;
import com.armatour.aramtour.models.Place;
import com.armatour.aramtour.models.Tour;
import com.armatour.aramtour.models.TourCompany;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public static List<Place> generatePlaces(){
        List<Place> data = new ArrayList<>();

        for (int i = 0; i < 40; i++){
            data.add(new Place("title " + i, "description " + i));
        }
        return data;
    }

    public static List<Tour> generateTours(){
        List<Tour> data = new ArrayList<>();

        for (int i = 0; i < 40; i++){
            data.add(new Tour("title " + i, "description " + i));
        }
        return data;
    }

    public static List<Motel> generateMotels(){
        List<Motel> data = new ArrayList<>();

        for (int i = 0; i < 40; i++){
            data.add(new Motel("title " + i, "description " + i));
        }
        return data;
    }

    public static List<TourCompany> generateTourCompanies(){
        List<TourCompany> data = new ArrayList<>();

        for (int i = 0; i < 40; i++){
            data.add(new TourCompany("title " + i, "description " + i));
        }
        return data;
    }
}
