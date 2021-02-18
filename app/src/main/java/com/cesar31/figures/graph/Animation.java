package com.cesar31.figures.graph;

public class Animation {
    private Integer x;
    private Integer y;
    private String type;

    public Animation(Integer x, Integer y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Animation{" + "x=" + x + ", y=" + y + ", type=" + type + '}';
    }
}
