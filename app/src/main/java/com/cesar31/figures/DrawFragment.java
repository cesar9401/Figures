package com.cesar31.figures;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.cesar31.figures.graph.FigureContainer;

public class DrawFragment extends Fragment {

    private FrameLayout layout;
    private FigureContainer figures;
    private DrawPanel panel;
    private Button btnAnimate1;

    public DrawFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.figures = (FigureContainer) getArguments().getSerializable("container");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_draw, container, false);

        // Vista para canvas
        layout = view.findViewById(R.id.flCanvasContainer);
        panel = new DrawPanel(this.getContext(), this.figures);
        btnAnimate1 = getActivity().findViewById(R.id.btnAnimate);
        btnAnimate1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // Code for animations
                panel.startAnimations();
                btnAnimate1.setEnabled(false);
            }
        });

        layout.addView(panel);

        // Inflate the layout for this fragment
        return view;
    }
}