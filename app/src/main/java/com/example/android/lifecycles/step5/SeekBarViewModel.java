package com.example.android.lifecycles.step5;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SeekBarViewModel extends ViewModel {

    public MutableLiveData<Integer> seekbarValue = new MutableLiveData<>();
}
