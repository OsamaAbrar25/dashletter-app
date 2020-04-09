package com.dashletter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class ChipViewModel extends AndroidViewModel {
    public MutableLiveData<ArrayList<ChipInfo>> mutableLiveData;
    public ChipViewModel(@NonNull Application application) {
        super(application);
        ChipRepository chipRepository = new ChipRepository(application);
        mutableLiveData = chipRepository.getData();
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }

    }

    public LiveData<ArrayList<ChipInfo>> getData () {
        return mutableLiveData;
    }
}
