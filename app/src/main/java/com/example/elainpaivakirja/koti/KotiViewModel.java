package com.example.elainpaivakirja.koti;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KotiViewModel extends ViewModel {

    private final MutableLiveData<String> kotiTexti;

    public KotiViewModel() {
        kotiTexti = new MutableLiveData<>();
        kotiTexti.setValue("Tämä on koti fragment");
    }

    public LiveData<String> getText() {
        return kotiTexti;
    }
}
