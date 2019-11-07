package com.example.dognavigationjetpackinjava.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.dognavigationjetpackinjava.R;
import com.example.dognavigationjetpackinjava.model.DogBreed;
import com.example.dognavigationjetpackinjava.util.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DogsListAdapter extends RecyclerView.Adapter<DogsListAdapter.DogViewHolder> {

    private ArrayList<DogBreed> dogsList;

    public DogsListAdapter(ArrayList<DogBreed> dogsList){
        this.dogsList = dogsList;
    }

    public void updateDogsList(List<DogBreed> newdogsList){
        dogsList.clear();
        dogsList.addAll(newdogsList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dog, parent,false);


        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {

        ImageView image = holder.itemView.findViewById(R.id.imageView);
        TextView dogName = holder.itemView.findViewById(R.id.dogName1);
        TextView tvLifeSpan = holder.itemView.findViewById(R.id.tvLifeSpan);


        dogName.setText(dogsList.get(position).dogBreed);
       // System.out.println( + "kkkk");

        tvLifeSpan.setText(dogsList.get(position).lifeSpan);
        util.loadImage(image, dogsList.get(position).imageUrl,
        util.getpD(image.getContext()));

    }

    @Override
    public int getItemCount() {
        return dogsList.size();
    }

    class DogViewHolder extends RecyclerView.ViewHolder {
     public View itemView;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;

        }
    }

}
