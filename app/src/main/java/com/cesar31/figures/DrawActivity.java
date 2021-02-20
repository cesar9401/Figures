package com.cesar31.figures;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.cesar31.figures.graph.FigureContainer;

public class DrawActivity extends AppCompatActivity {

    private FragmentTransaction transaction;
    private Fragment drawFragment;

    private Button btnBackMain;
    private Button btnDraw;
    private Button btnAnimate;
    private Button btnReport;

    private String input;
    private FigureContainer container;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        // Botones
        btnBackMain = findViewById(R.id.btnDrawBack);
        btnDraw = findViewById(R.id.btnDraw);
        btnAnimate = findViewById(R.id.btnAnimate);
        btnReport = findViewById(R.id.btnReport);

        // Fragments
        drawFragment = new DrawFragment();

        // OnClick para btnBackMain
        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMain();
            }
        });

        // OnClick para btnDraw
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write your code here
            }
        });

        btnAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write your code here
            }
        });

        // OnClick para btnReport
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write your code here
            }
        });

        // Recuperar datos de Main
        getDataMain();

        // Bundle para drawFragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("container", this.container);
        drawFragment.setArguments(bundle);

        // Agregamos fragment principal
        getSupportFragmentManager().beginTransaction().add(R.id.flDrawContainer, drawFragment).commit();
    }

    /*
        Metodo para obtener los objetos enviados desde MainActivity
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getDataMain() {
        Bundle data = getIntent().getExtras();
        if (data != null) {
            this.input = (String) data.getSerializable("input");
            this.container = (FigureContainer) data.getSerializable("container");
        }
    }

    /*
        Metodo para volver a la vista activity_main
     */
    private void backMain() {
        Intent mainActivity = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("input", this.input);
        mainActivity.putExtras(bundle);
        startActivity(mainActivity);
    }
}