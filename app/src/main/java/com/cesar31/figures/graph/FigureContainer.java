package com.cesar31.figures.graph;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.cesar31.figures.reports.ElementCount;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FigureContainer implements Serializable {
    private ElementCount count;

    private Figure current;
    private List<DrawFour> four;
    private List<DrawFive> five;
    private List<Polygon> polygon;

    public FigureContainer() {
        this.four = new ArrayList<>();
        this.five = new ArrayList<>();
        this.polygon = new ArrayList<>();

        this.count = new ElementCount();
    }

    public List<DrawFour> getFour() {
        return four;
    }

    public List<DrawFive> getFive() {
        return five;
    }

    public List<Polygon> getPolygon() {
        return polygon;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setFour(DrawFour figure, String type) {
        figure.setType(type);
        four.add(figure);
        this.current = figure;

        // Reportes
        count.setFigure(type);
        count.setColor(figure.getColor());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setFive(DrawFive figure, String type) {
        figure.setType(type);
        five.add(figure);
        this.current = figure;

        // Reportes
        count.setFigure(type);
        count.setColor(figure.getColor());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setPolygon(Polygon figure) {
        polygon.add(figure);
        this.current = figure;

        //Reportes
        count.setFigure("poligono");
        count.setColor(figure.getColor());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setAnimation(Animation animation) {
        this.current.setAnimation(animation);
        this.count.setAnimation(animation.getType());
    }

    public ElementCount getCount() {
        return count;
    }
}
