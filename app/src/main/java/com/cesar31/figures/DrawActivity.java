package com.cesar31.figures;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cesar31.figures.graph.FigureContainer;
import com.cesar31.figures.reports.ReportError;

import java.util.List;

public class DrawActivity extends AppCompatActivity {

    private Button btnBackMain;
    private Button btnAnimate;
    private Button btnReport;

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

        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMain();
            }
        });

        getDataMain();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getDataMain() {
        Bundle data = getIntent().getExtras();
        if(data != null) {
            this.input = (String) data.getSerializable("input");
            this.container = (FigureContainer) data.getSerializable("container");
            this.container.getFour().forEach(f -> {
                System.out.println(f.toString());
            });

            this.container.getFour().forEach(f -> {
                System.out.println(f.toString());
            });

            this.container.getPolygon().forEach(f -> {
                System.out.println(f.toString());
            });
        }
    }

    private void backMain() {
        Intent mainActivity = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("input", this.input);
        mainActivity.putExtras(bundle);
        startActivity(mainActivity);
    }
}