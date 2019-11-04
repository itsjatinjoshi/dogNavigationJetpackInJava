package com.example.dognavigationjetpackinjava.view;


import android.opengl.Visibility;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dognavigationjetpackinjava.R;
import com.example.dognavigationjetpackinjava.model.DogBreed;
import com.example.dognavigationjetpackinjava.viewmodel.ListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListFragment extends Fragment {

    private ListViewModel viewModel;
    private DogsListAdapter dogsListAdapter = new DogsListAdapter(new ArrayList<>());

    @BindView(R.id.dogsList)
    RecyclerView dogsList;

    @BindView(R.id.listlError)
    TextView listlError;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    public ListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
//        Navigation.findNavController(view).navigate(action);

        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();

        dogsList.setLayoutManager(new LinearLayoutManager(getContext()));
        dogsList.setAdapter(dogsListAdapter);

        observeViewModel();
    }

    public void observeViewModel(){
        viewModel.dogs.observe(this, new Observer<List<DogBreed>>() {
            @Override
            public void onChanged(List<DogBreed> dogBreeds) {
                if(dogBreeds!= null && dogBreeds instanceof List){
                    dogsList.setVisibility(View.VISIBLE);
                    dogsListAdapter.updateDogsList(dogBreeds);
                }

            }
        });

        viewModel.dogLoadError.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean!= null && aBoolean instanceof Boolean){
                    listlError.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                }
            }
        });

        viewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean!= null && aBoolean instanceof Boolean){
                  loadingView.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                  if(aBoolean){
                      dogsList.setVisibility(View.GONE);
                      listlError.setVisibility(View.GONE);
                  }
                 }
            }
        });
    }
}
