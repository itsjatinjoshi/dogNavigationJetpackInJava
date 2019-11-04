package com.example.dognavigationjetpackinjava.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface DogApi {

    @GET("DevTides/DogsApi/master/dogs.json")
     Single<List<DogBreed>>getDog();
}
