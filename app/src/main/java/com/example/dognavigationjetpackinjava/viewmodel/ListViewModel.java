package com.example.dognavigationjetpackinjava.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dognavigationjetpackinjava.model.DogApiService;
import com.example.dognavigationjetpackinjava.model.DogBreed;
import com.example.dognavigationjetpackinjava.model.DogDao;
import com.example.dognavigationjetpackinjava.model.DogDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends AndroidViewModel {

    public MutableLiveData<List<DogBreed>> dogs = new MutableLiveData<List<DogBreed>>();
    public MutableLiveData<Boolean> dogLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    private DogApiService dogsService = new DogApiService();
    private CompositeDisposable disposable = new CompositeDisposable();
    private AsyncTask<List<DogBreed>, Void, List<DogBreed>> insertTask;

    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    public void refresh() {
        fetchFromServer();
    }

    public void fetchFromServer() {
        loading.setValue(true);
        disposable.add(
                dogsService.getDog()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                            @Override
                            public void onSuccess(List<DogBreed> dogBreeds) {
                                insertTask = new InsertDogsTask();
                                insertTask.execute(dogBreeds);


                            }

                            @Override
                            public void onError(Throwable e) {
                                dogLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();

                            }
                        })
        );

    }

    private void dogsRetrieved(List<DogBreed> dogList) {
        dogs.setValue(dogList);
        dogLoadError.setValue(false);
        loading.setValue(false);
    }

    protected void onCleared() {
        super.onCleared();
        disposable.clear();

        if(insertTask!=null){
            insertTask.cancel(true);
            insertTask = null;
        }
    }

    private class InsertDogsTask extends AsyncTask<List<DogBreed>, Void, List<DogBreed>> {

        @Override
        protected List<DogBreed> doInBackground(List<DogBreed>... lists) {
            List<DogBreed> list = lists[0];
            DogDao dao = DogDatabase.getInstance(getApplication()).dogDao();
            dao.deleteAllDog();

            ArrayList<DogBreed> newList = new ArrayList<>(list);
            List<Long> result = dao.insertAll(newList.toArray(new DogBreed[0]));

            int i = 0;
            while (i < list.size()) {
                list.get(i).uuid = result.get(i).intValue();
                ++i;
            }
            return list;

        }

        @Override
        protected void onPostExecute(List<DogBreed> dogBreeds) {
            dogsRetrieved(dogBreeds);
        }
    }


}
