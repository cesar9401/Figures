package com.cesar31.figures.reports;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.cesar31.figures.lexerandparser.FigureLex;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import java_cup.runtime.Symbol;

public class OperatorReport {
    private List<Operator> operators;
    private FigureLex lex;

    public OperatorReport(String input) {
        this.operators = new ArrayList<>();
        this.lex = new FigureLex(new StringReader(input));
    }

    public List<Operator> getOperatorReport() {
        List<Symbol> sym = new ArrayList<>();
        while (true) {
            try {
                Symbol symbol = lex.next_token();
                if (symbol.sym == 0) {
                    break;
                }

                sym.add(symbol);
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }

        for (int i = 0; i < sym.size(); i++) {
            if (i - 1 > 0 && i + 1 < sym.size()) {
                if (sym.get(i).value.equals("+")) {
                    operators.add(new Operator("Suma", sym.get(i).left, sym.get(i).right, sym.get(i - 1).value + " + " + sym.get(i + 1).value));
                }

                if (sym.get(i).value.equals("-")) {
                    operators.add(new Operator("Resta", sym.get(i).left, sym.get(i).right, sym.get(i - 1).value + " - " + sym.get(i + 1).value));
                }

                if (sym.get(i).value.equals("*")) {
                    operators.add(new Operator("Multiplicacion", sym.get(i).left, sym.get(i).right, sym.get(i - 1).value + " * " + sym.get(i + 1).value));
                }

                if (sym.get(i).value.equals("/")) {
                    operators.add(new Operator("Division", sym.get(i).left, sym.get(i).right, sym.get(i - 1).value + " / " + sym.get(i + 1).value));
                }
            }
        }

        return this.operators;
    }
}
