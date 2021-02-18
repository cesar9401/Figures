package com.cesar31.figures.graph;

public class DrawFour extends Figure {
    private String type;
    private Integer side;

    public DrawFour(Integer x, Integer y, Integer side, String color) {
        super(x, y, color);
        this.side = side;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSide() {
        return side;
    }

    public void setSide(Integer side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return super.toString() + "DrawFour{" + "type=" + type + ", side=" + side + '}';
    }
}
