package com.example.edulog.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edulog.R;

public class SliderItemFragment extends Fragment {

    private static final String ARG_POSITION = "slider-position";
    private int position;
    private Integer [] images = {R.drawable.ic_graduation,R.drawable.ic_online_class,R.drawable.ic_students};
    private Integer [] texts = {R.string.online,R.string.tests,R.string.placement};
    private Integer [] sub_texts = {R.string.engineering,R.string.access,R.string.widerange};

    public SliderItemFragment() {

    }

    public static SliderItemFragment newInstance(int position) {
        SliderItemFragment fragment = new SliderItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_slider_item, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            TextView title = view.findViewById(R.id.textView2);
            TextView titleText1 = view.findViewById(R.id.textView3);
            ImageView imageView = view.findViewById(R.id.imageView);
            // set page title
            title.setText(texts[position]);
            // set page sub title text
            titleText1.setText(sub_texts[position]);

            // set page image
            imageView.setImageResource(images[position]);
        }catch (ArrayIndexOutOfBoundsException e) {

        }
    }
}