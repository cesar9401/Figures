package com.cesar31.figures;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.cesar31.figures.graph.FigureContainer;

public class DrawActivity extends AppCompatActivity {

    private Button btnBackMain;
    private Button btnAnimate;
    private Button btnReport;
    private FrameLayout layout;

    private String input;
    private FigureContainer container;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        btnBackMain = findViewById(R.id.btnDrawBack);
        btnAnimate = findViewById(R.id.btnAnimate);
        btnReport = findViewById(R.id.btnReport);

        // OnClick para btnBackMain
        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMain();
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

        // Agregamos a DrawPanel como hijo de layout
        layout = findViewById(R.id.flDrawContainer);
        layout.addView(new DrawPanel(this, this.container));
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