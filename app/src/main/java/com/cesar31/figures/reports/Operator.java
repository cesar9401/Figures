package com.cesar31.figures.reports;

public class Operator {
    private String operator;
    private int line;
    private int column;
    private String instance;

    public Operator(String operator, int line, int column, String instance) {
        this.operator = operator;
        this.line = line;
        this.column = column;
        this.instance = instance;
    }

    public String getOperator() {
        return operator;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "Operator{" + "operator=" + operator + ", line=" + line + ", column=" + column + ", instance=" + instance + '}';
    }
}
