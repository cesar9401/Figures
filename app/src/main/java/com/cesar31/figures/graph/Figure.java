package com.cesar31.figures.graph;

import java.io.Serializable;

public class Figure implements Serializable {
    private Integer x;
    private Integer y;
    private String color;
    private Animation animation;

    public Figure() {
    }

    public Figure(Integer x, Integer y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public boolean isAnimated() {
        return this.animation != null;
    }

    @Override
    public String toString() {
        return "Figure{" + "x=" + x + ", y=" + y + ", color=" + color + ", animation=" + animation + '}';
    }
}
