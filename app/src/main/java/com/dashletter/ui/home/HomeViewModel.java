package com.dashletter.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dashletter.HomeInfo;
import com.dashletter.HomeRepository;

import java.util.ArrayList;

public class HomeViewModel extends AndroidViewModel {

    public MutableLiveData<ArrayList<HomeInfo>> mutableLiveData;

    public HomeViewModel(Application application) {
        super(application);
        //private MutableLiveData<String> mText;
        HomeRepository homeRepository = new HomeRepository(application);
        mutableLiveData = homeRepository.getData();
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
    }

    public LiveData<ArrayList<HomeInfo>> getData() {
        return mutableLiveData;
    }
}