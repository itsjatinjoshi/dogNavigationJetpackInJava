package com.example.dognavigationjetpackinjava.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dognavigationjetpackinjava.model.DogBreed;

public class DetailViewModel extends ViewModel {

    public MutableLiveData<DogBreed> dogDetail = new MutableLiveData<DogBreed>();
    public void getDetail(){
        DogBreed dog1 =new DogBreed("1", "corgi","15 years", "","1111","sweet","");

        dogDetail.setValue(dog1);

    }

    }



