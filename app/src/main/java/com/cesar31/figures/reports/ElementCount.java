package com.cesar31.figures.reports;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;

public class ElementCount {
    private HashMap<String, Integer> figures;
    private HashMap<String, Integer> colors;
    private HashMap<String, Integer> animations;

    public ElementCount() {
        this.figures = new HashMap<>();
        this.colors = new HashMap<>();
        this.animations = new HashMap<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setFigure(String figure) {
        setCount(figures, figure);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setColor(String color) {
        setCount(colors, color);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setAnimation(String animation) {
        setCount(animations, animation);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setCount(HashMap<String, Integer> count, String obj) {
        if (count.containsKey(obj)) {
            count.replace(obj, count.get(obj) + 1);
        } else {
            count.put(obj, 1);
        }
    }

    public HashMap<String, Integer> getFigures() {
        return figures;
    }

    public HashMap<String, Integer> getColors() {
        return colors;
    }

    public HashMap<String, Integer> getAnimations() {
        return animations;
    }
}
