package com.example.dognavigationjetpackinjava.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dognavigationjetpackinjava.R;
import com.example.dognavigationjetpackinjava.model.DogBreed;
import com.example.dognavigationjetpackinjava.viewmodel.DetailViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailFragment extends Fragment {

    private int dogUuid;
    private DetailViewModel viewModel;

    @BindView(R.id.dogImage)
    ImageView dogImage;

    @BindView(R.id.dogName)
    TextView dogName;

    @BindView(R.id.dogPurpose)
    TextView dogPurpose;

    @BindView(R.id.dogTemperament)
    TextView dogTemperament;

    @BindView(R.id.dogLifespan)
    TextView dogLifespan;


    public DetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null)
            dogUuid = DetailFragmentArgs.fromBundle(getArguments()).getDogUuid();

        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        viewModel.getDetail();

        observeViewMode();

    }

    public void observeViewMode(){
        viewModel.dogDetail.observe(this, new Observer<DogBreed>() {
            @Override
            public void onChanged(DogBreed dogBreed) {
                if(dogBreed!= null && dogBreed instanceof DogBreed){
                    dogName.setText(dogBreed.dogBreed);
                    dogPurpose.setText(dogBreed.breedFor);
                    dogTemperament.setText(dogBreed.temperament);
                    dogLifespan.setText(dogBreed.lifeSpan);
                }
            }
        });
    }


}
