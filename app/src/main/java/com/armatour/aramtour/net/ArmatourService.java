package com.armatour.aramtour.net;

import com.armatour.aramtour.models.Apartment;
import com.armatour.aramtour.models.Place;
import com.armatour.aramtour.models.Tour;
import com.armatour.aramtour.models.TourCompany;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArmatourService {

    @GET("/places")
    Call<List<Place>> getPlaces();

    @GET("/places/{place_id}")
    Call<Place> getPlaceById(@Path("place_id") String placeId);

    @GET("/tours")
    Call<List<Tour>> getTours();

    @GET("/tours/{tour_id}")
    Call<Tour> getTourById(@Path("tour_id") String tourId);

    @GET("/apartments")
    Call<List<Apartment>> getApartments();

    @GET("/apartments/{apartment_id}")
    Call<Apartment> getApartmentById(@Path("apartment_id") String apartmentId);

    @GET("/tour_companies")
    Call<List<TourCompany>> getTourCompanies();

    @GET("/tour_companies/{tour_company_id}")
    Call<TourCompany> getTourCompany(@Path("tour_company_id") String tourCompanyId);
}
