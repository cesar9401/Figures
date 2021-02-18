package com.cesar31.figures.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java_cup.runtime.Symbol;

public class HandleError {
    private HashMap<String, String> gram;
    private List<ReportError> errors;

    public HandleError() {
        initGram();
        this.errors = new ArrayList<>();
    }

    public void setError(Symbol symbol, String typeE, List<String> expectedTokens) {
        ReportError error = new ReportError((String) symbol.value, symbol.left, symbol.right);
        String type = typeE.equals("ERROR") ? "Lexico" : "Sintactico";
        System.out.println("type = " + type);
        error.setType(type);
        error.setDescription(getDescription(type, expectedTokens));

        errors.add(error);
    }

    private String getDescription(String type, List<String> expectedTokens) {
        String description = type.equals("Lexico") ? "No existe simbolo en el lenguaje. " : "";

        description += "Se esperaba ";
        for (int i = 0; i < expectedTokens.size(); i++) {
            description = description.concat("'" + gram.get(expectedTokens.get(i)) + "'");
            if (i == expectedTokens.size() - 1) {
                description = description.concat(".");
            } else {
                description = description.concat(", ");
            }
        }

        return description;
    }

    private void initGram() {
        gram = new HashMap<>();
        gram.put("GRAPH", "graficar");
        gram.put("CIRCLE", "circulo");
        gram.put("SQUARE", "cuadrado");
        gram.put("RCTNGL", "rectangulo");
        gram.put("LINE", "linea");
        gram.put("POLYGN", "poligono");
        gram.put("ANIMT", "animar");
        gram.put("OBJ", "objeto");
        gram.put("ANT", "anterior");
        gram.put("CURVE", "curva");
        gram.put("COLOR", "color");

        gram.put("COMMA", "coma");
        gram.put("LPAREN", "(");
        gram.put("RPAREN", ")");
        gram.put("PLUS", "+");
        gram.put("MINUS", "-");
        gram.put("TIMES", "*");
        gram.put("DIV", "/");

        gram.put("ENTERO", "entero");

        gram.put("ERROR", "No existe simbolo en el lenguaje");

        /* jflex */
        gram.put("EOF", "end of file");
        gram.put("error", "error");
    }

    public List<ReportError> getErrors() {
        return errors;
    }
}
