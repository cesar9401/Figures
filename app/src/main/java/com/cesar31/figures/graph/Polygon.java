package com.cesar31.figures.graph;

public class Polygon extends Figure {
    private Integer height;
    private Integer width;
    private Integer n;

    public Polygon(Integer x, Integer y, Integer height, Integer width, Integer n, String color) {
        super(x, y, color);
        this.height = height;
        this.width = width;
        this.n = n;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return super.toString() + "Polygon{" + "height=" + height + ", width=" + width + ", n=" + n + '}';
    }
}
