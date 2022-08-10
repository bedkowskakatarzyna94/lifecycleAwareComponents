package com.example.android.lifecycles.step5;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.android.codelabs.lifecycle.R;

public class Fragment_step5 extends Fragment {

    private SeekBar mSeekBar;
    private SeekBarViewModel mSeekBarViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_step5, container, false);
        mSeekBar = root.findViewById(R.id.seekBar);

        mSeekBarViewModel = new ViewModelProvider(requireActivity()).get(SeekBarViewModel.class);
        subscribeSeekBar();

        return root;
    }

    private void subscribeSeekBar() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    Log.d("Step5", "Progress changed!");
                    mSeekBarViewModel.seekbarValue.setValue(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mSeekBarViewModel.seekbarValue.observe(
                requireActivity(), value -> {
                    if (value != null) {
                        mSeekBar.setProgress(value);
                    }
                });
    }
}
