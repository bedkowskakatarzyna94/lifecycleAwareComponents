package com.example.android.lifecycles.step6;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class SavedStateViewModel extends ViewModel {

    private final SavedStateHandle mState;

    public SavedStateViewModel(SavedStateHandle savedStateHandle) {
        mState = savedStateHandle;
    }

    private static final String NAME_KEY = "name";

    LiveData<String> getName() {
        return mState.getLiveData(NAME_KEY);
    }

    void saveNewName(String newName) {
        mState.set(NAME_KEY, newName);
    }
}
