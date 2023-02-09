package com.example.elainpaivakirja.tarinat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TarinatViewModel extends ViewModel {

    private final MutableLiveData<String> tarinatTexti;

    public TarinatViewModel() {
        tarinatTexti = new MutableLiveData<>();
        tarinatTexti.setValue("Tämä on tarinat fragment");
    }

    public LiveData<String> getText() {
        return tarinatTexti;
    }

}
