package com.cesar31.figures;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.cesar31.figures.reports.ElementCount;
import com.cesar31.figures.reports.Operator;
import com.cesar31.figures.reports.OperatorReport;

import java.util.ArrayList;
import java.util.List;

public class ReportFragment extends Fragment {

    private TableLayout tableOperators;
    private TableLayout tableColors;
    private TableLayout tableFigures;
    private TableLayout tableAnimations;

    private ElementCount count;
    private List<Operator> operators;
    private String input;

    public ReportFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.count = (ElementCount) getArguments().getSerializable("count");
            this.input = (String) getArguments().getSerializable("input");

            // Inicializar operatorReport
            OperatorReport operatorReport = new OperatorReport(this.input);
            this.operators = operatorReport.getOperatorReport();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        // Reporte Operadores
        tableOperators = view.findViewById(R.id.tlTableOperators);
        createOperatorsReport();

        // Reporte Colores
        tableColors = view.findViewById(R.id.tlTableColors);
        createColorsReport();

        // Reporte de Figuras
        tableFigures = view.findViewById(R.id.tlTableFigures);
        createFiguresReport();

        // Reporte de Animacion
        tableAnimations = view.findViewById(R.id.tlTableAnimation);
        createAnimationsReport();

        // Inflate the layout for this fragment
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createOperatorsReport() {
        Table table = new Table(getActivity(), tableOperators);
        table.addHeader(R.array.header_operators);

        operators.forEach(o -> {
            List<String> elements = new ArrayList<>();
            elements.add(o.getOperator());
            elements.add(String.valueOf(o.getLine()));
            elements.add(String.valueOf(o.getColumn()));
            elements.add(o.getInstance());
            table.addRowTable(elements);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createColorsReport() {
        Table table = new Table(getActivity(), tableColors);
        table.addHeader(R.array.header_colors);
        count.getColors().forEach((s, i) -> {
            List<String> elements = new ArrayList<>();
            elements.add(s);
            elements.add(String.valueOf(i));
            table.addRowTable(elements);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createFiguresReport() {
        Table table = new Table(getActivity(), tableFigures);
        table.addHeader(R.array.header_figures);
        count.getFigures().forEach((s, i) -> {
            List<String> elements = new ArrayList<>();
            elements.add(s);
            elements.add(String.valueOf(i));
            table.addRowTable(elements);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createAnimationsReport() {
        Table table = new Table(getActivity(), tableAnimations);
        table.addHeader(R.array.header_animations);
        count.getAnimations().forEach((s, i) -> {
            List<String> elements = new ArrayList<>();
            elements.add(s);
            elements.add(String.valueOf(i));
            table.addRowTable(elements);
        });
    }
}