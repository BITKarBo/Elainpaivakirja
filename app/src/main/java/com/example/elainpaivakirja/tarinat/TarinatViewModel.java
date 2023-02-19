package com.example.elainpaivakirja.tarinat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TarinatViewModel extends ViewModel {

    private final MutableLiveData<String> tarinatTexti;
    private final MutableLiveData<String> imagePath = new MutableLiveData<>();
    public TarinatViewModel() {
        tarinatTexti = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return tarinatTexti;
    }

}
