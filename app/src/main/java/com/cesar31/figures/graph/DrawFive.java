package com.cesar31.figures.graph;

public class DrawFive extends Figure {
    private Integer x2;
    private Integer y2;
    private String type;

    public DrawFive(Integer x, Integer y, Integer x2, Integer y2, String color) {
        super(x, y, color);
        this.x2 = x2;
        this.y2 = y2;
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x2) {
        this.x2 = x2;
    }

    public Integer getY2() {
        return y2;
    }

    public void setY2(Integer y2) {
        this.y2 = y2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + "DrawFive{" + "x2=" + x2 + ", y2=" + y2 + ", type=" + type + '}';
    }
}
