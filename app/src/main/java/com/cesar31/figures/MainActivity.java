package com.cesar31.figures;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cesar31.figures.graph.FigureContainer;
import com.cesar31.figures.lexerandparser.FigureLex;
import com.cesar31.figures.lexerandparser.parser;
import com.cesar31.figures.reports.Operator;
import com.cesar31.figures.reports.OperatorReport;
import com.cesar31.figures.reports.ReportError;

import java.io.StringReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private Button btnCompile;

    /* Figures and reports */
    private FigureContainer container;
    private List<ReportError> errors;
    private List<Operator> operators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Componentes de activity main */
        etInput = findViewById(R.id.etInput);
        btnCompile = findViewById(R.id.btnMain);
        //btnError.setVisibility(View.GONE);

        btnCompile.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String input = etInput.getText().toString();
                if(input != null) {
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
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getFigures(String input, FigureContainer container) {
        this.container = container;
        OperatorReport or = new OperatorReport(input);
        this.operators = or.getOperatorReport();

        /* Mostrando figuras */
        container.getFour().forEach(d -> {
            System.out.println(d.toString());
        });

        container.getFive().forEach(d -> {
            System.out.println(d.toString());
        });

        container.getPolygon().forEach(d -> {
            System.out.println(d.toString());
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getErrors(List<ReportError> errors) {
        this.errors = errors;
        System.out.printf("Lexema\tLinea\tColumna\tTipo\tDescripcion\n");
        this.errors.forEach(e -> {
            System.out.printf("%s\t%d\t%d\t%s\t%s\n", e.getLexema(), e.getLine(), e.getColumn(), e.getType(), e.getDescription());
        });
    }
}