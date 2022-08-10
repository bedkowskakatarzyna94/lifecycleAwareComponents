package com.example.android.lifecycles.step6;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.codelabs.lifecycle.R;

public class SavedStateActivity extends AppCompatActivity {

    private SavedStateViewModel mSavedStateViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_state_activity);

        mSavedStateViewModel = new ViewModelProvider(this).get(SavedStateViewModel.class);

        mSavedStateViewModel.getName().observe(this, savedString -> ((TextView) findViewById(R.id.saved_vm_tv))
                .setText(getString(R.string.saved_in_vm, savedString)));

        findViewById(R.id.save_bt).setOnClickListener(v -> {
            String newName = ((EditText) findViewById(R.id.name_et)).getText().toString();
            mSavedStateViewModel.saveNewName(newName);
        });
    }
}
