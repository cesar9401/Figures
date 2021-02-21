package com.cesar31.figures;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.cesar31.figures.reports.ReportError;

import java.util.ArrayList;
import java.util.List;

public class ErrorActivity extends AppCompatActivity {

    private String input;
    private Button btnBack;
    private List<ReportError> errors;
    private TableLayout tableErrors;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        btnBack = findViewById(R.id.btnErrorBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMain();
            }
        });

        // Recibir datos desde MainActivity
        getDataFromMain();

        // Reporte de errores
        tableErrors = findViewById(R.id.tlTableErrors);
        createErrorsReport();
    }

    /**
     * Generar reporte de errores
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createErrorsReport() {
        Table table = new Table(this, tableErrors);
        table.addHeader(R.array.header_errors);
        errors.forEach(e -> {
            List<String> elements = new ArrayList<>();
            elements.add(e.getLexema());
            elements.add(String.valueOf(e.getLine()));
            elements.add(String.valueOf(e.getColumn()));
            elements.add(e.getType());
            elements.add(e.getDescription());
            table.addRowTable((ArrayList<String>) elements);
        });
    }

    /**
     *  Metodo para volver a activity_main
     */
    private void backMain() {
        Intent mainActivity = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("input", this.input);
        mainActivity.putExtras(bundle);
        startActivity(mainActivity);
    }

    /**
     * Metodo para obtener objetos enviados desde activity_main
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getDataFromMain() {
        Bundle data = getIntent().getExtras();
        if(data != null) {
            this.input = (String) data.getSerializable("input");
            this.errors = (List<ReportError>) data.getSerializable("errors");
        }
    }
}