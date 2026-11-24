package com.example.fragments1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SimpleFragment extends Fragment {

    public SimpleFragment() {
        // Required empty public constructor
    }

    public static SimpleFragment newInstance() {
        return new SimpleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_simple,
                container,
                false
        );

        TextView fragmentHeader =
                view.findViewById(R.id.fragment_header);

        RadioGroup radioGroup =
                view.findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == R.id.radio_button_yes) {
                fragmentHeader.setText(R.string.yes_message);

            } else if (checkedId == R.id.radio_button_no) {
                fragmentHeader.setText(R.string.no_message);
            }
        });

        return view;
    }
}