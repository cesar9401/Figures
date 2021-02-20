package com.cesar31.figures;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cesar31.figures.graph.FigureContainer;
import com.cesar31.figures.lexerandparser.FigureLex;
import com.cesar31.figures.lexerandparser.parser;
import com.cesar31.figures.reports.ReportError;

import java.io.Serializable;
import java.io.StringReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Elementos de activity_main
    private EditText etInput;
    private Button btnCompile;
    private Button btnClear;
    private String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Componentes de activity main */
        etInput = findViewById(R.id.etInput);
        btnCompile = findViewById(R.id.btnCompile);
        btnClear = findViewById(R.id.btnClear);

        // onClick para btnCompile
        btnCompile.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                btnCompileAction();
            }
        });

        // OnClick para btnClear
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etInput.setText("");
            }
        });


        getData();
    }

    // Accion de btnCompile
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void btnCompileAction() {
        input = etInput.getText().toString();
        if(!input.isEmpty() && !input.trim().isEmpty()) {
            FigureLex lexer = new FigureLex(new StringReader(input));
            parser parser = new parser(lexer);
            try {
                parser.parse();
                if(parser.isParsed()) {
                    // Input aceptado
                    getFigures(input, parser.getContainer());
                } else {
                    getErrors(parser.getHandleError().getErrors());
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }

    /*
        Metodo para cambiar a la activity que muestra los dibujos
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getFigures(String input, FigureContainer container) {
        Intent drawActivity = new Intent(this, DrawActivity.class);
        Bundle bundle = new Bundle();

        // Enviar datos a DrawActivity
        bundle.putSerializable("container", (Serializable) container);
        bundle.putSerializable("input", input);
        drawActivity.putExtras(bundle);

        startActivity(drawActivity);
    }

    /*
        Metodo para cambiar a la activity que muestra errores
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getErrors(List<ReportError> errors) {
        Intent errorActivity = new Intent(this, ErrorActivity.class);
        Bundle bundle = new Bundle();

        // Enviar datos a ErrorActivity
        bundle.putSerializable("errors", (Serializable) errors);
        bundle.putSerializable("input", this.input);
        errorActivity.putExtras(bundle);

        startActivity(errorActivity);
    }

    /*
        Obtener datos de otros activities, input
     */
    private void getData() {
        Bundle data = getIntent().getExtras();
        if(data != null) {
            this.input = (String) getIntent().getSerializableExtra("input");
            etInput.setText(this.input);
        }
    }
}