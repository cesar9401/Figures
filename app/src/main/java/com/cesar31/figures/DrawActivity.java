package com.cesar31.figures;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cesar31.figures.graph.FigureContainer;

public class DrawActivity extends AppCompatActivity {

    private Fragment drawFragment;
    private Fragment reportFragment;

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

        // inicializar Fragments
        drawFragment = new DrawFragment();
        reportFragment = new ReportFragment();

        // Recuperar datos de Main
        getDataMain();

        // Bundle para drawFragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("container", this.container);
        drawFragment.setArguments(bundle);

        // Cambiamos a DrawFragment
        getSupportFragmentManager().beginTransaction().add(R.id.flDrawContainer, drawFragment).commit();

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
                changeToDrawFragment();
            }
        });

        // OnClick para btnAnimate
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
                changeToReportFragment();
            }
        });
    }

    /**
     * Recuperar objetos desde activity_main
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getDataMain() {
        Bundle data = getIntent().getExtras();
        if (data != null) {
            this.input = (String) data.getSerializable("input");
            this.container = (FigureContainer) data.getSerializable("container");
        }
    }

    /**
     * Metodo para volver a activity_main
     */
    private void backMain() {
        Intent mainActivity = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("input", this.input);
        mainActivity.putExtras(bundle);
        startActivity(mainActivity);
    }

    /**
     *  Metodo para cambiar a DrawFragment
     */
    private void changeToDrawFragment() {
        // Bundle para drawFragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("container", this.container);
        drawFragment.setArguments(bundle);

        // Cambiamos a DrawFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.flDrawContainer, drawFragment).commit();

        // Mostrar btnAnimate
        btnAnimate.setVisibility(View.VISIBLE);

        // Ocultar btnDraw
        btnDraw.setVisibility(View.GONE);
    }

    /**
     * Metodo para cambiar a ReportFragment para ver los reportes
     */
    private void changeToReportFragment() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("count", this.container.getCount());
        bundle.putSerializable("input", this.input);
        this.reportFragment.setArguments(bundle);

        // Cambiamos a ReportFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.flDrawContainer, reportFragment).commit();

        // Mostrar btnDraw
        btnDraw.setVisibility(View.VISIBLE);

        // Ocultar btnAnimate
        btnAnimate.setVisibility(View.GONE);
    }
}