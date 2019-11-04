package com.example.dognavigationjetpackinjava.model;

import com.google.gson.annotations.SerializedName;

public class DogBreed {


    @SerializedName("id")
    public String breedId;

    @SerializedName("name")
    public String dogBreed;

    @SerializedName("life_span")
    public String lifeSpan;

    @SerializedName("breed_group")
    public String breedGroup;

    @SerializedName("bred_for")
    public String breedFor;

    public String temperament;

    @SerializedName("url")
    public String imageUrl;


    public int uui;

    public DogBreed(String breedId, String dogBreed, String lifeSpan, String breedGroup, String breedFor, String temperament, String imageUrl) {
        this.breedId = breedId;
        this.dogBreed = dogBreed;
        this.lifeSpan = lifeSpan;
        this.breedGroup = breedGroup;
        this.breedFor = breedFor;
        this.temperament = temperament;
        this.imageUrl = imageUrl;
    }
}
