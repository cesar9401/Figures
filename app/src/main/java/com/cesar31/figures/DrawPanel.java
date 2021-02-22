package com.cesar31.figures;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.cesar31.figures.graph.Animation;
import com.cesar31.figures.graph.Figure;
import com.cesar31.figures.graph.FigureContainer;
import com.cesar31.figures.graph.Polygon;

import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends View {

    private FigureContainer container;
    private int width;
    private int height;
    private int count;

    public DrawPanel(Context context, FigureContainer container) {
        super(context);
        this.container = container;
    }

    /**
     * OnDraw para dibujar
     *
     * @param canvas
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ResourceAsColor")
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(5);

        this.width = canvas.getWidth();
        this.height = canvas.getHeight();
        this.count = 0;

        this.container.getFour().forEach(f -> {
            // Circulos
            if (f.getType().equals("circulo")) {
                paint.setColor(getResources().getColor(getColor(f.getColor()), null));
                canvas.drawCircle(f.getX(), f.getY(), f.getSide(), paint);
            }

            // Cuadrados
            if (f.getType().equals("cuadrado")) {
                paint.setColor(getResources().getColor(getColor(f.getColor()), null));
                canvas.drawRect(f.getX(), f.getY(), f.getX() + f.getSide(), f.getY() + f.getSide(), paint);
            }
        });

        this.container.getFive().forEach(f -> {
            // Rectangulos
            if (f.getType().equals("rectangulo")) {
                paint.setColor(getResources().getColor(getColor(f.getColor()), null));
                canvas.drawRect(f.getX(), f.getY(), f.getX() + f.getX2(), f.getY() + f.getY2(), paint);
            }

            // Lineas
            if (f.getType().equals("linea")) {
                paint.setColor(getResources().getColor(getColor(f.getColor()), null));
                canvas.drawLine(f.getX(), f.getY(), f.getX2(), f.getY2(), paint);
            }
        });

        // Poligonos
        this.container.getPolygon().forEach(f -> {
            drawPolygon(f, canvas, paint);
            canvas.drawPoint(f.getX(), f.getY(), paint);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startAnimations() {
        int count = 0;
        List<Figure> figures = new ArrayList<>();
        figures.addAll(this.container.getFour());
        figures.addAll(this.container.getFive());
        figures.addAll(this.container.getPolygon());

        for (Figure figure : figures) {
            if (figure.isAnimated()) {
                if (figure.getAnimation().getType().equals("linea")) {
                    lineAnimation(figure);
                } else {
                    // Animacion de tipo curva
                    curveAnimation(figure);
                }
            }
        }
    }

    /**
     * Metodo para animaciones en forma de curva
     * @param figure
     */
    public void curveAnimation(Figure figure) {
        float x0 = figure.getX();
        float y0 = figure.getY();
        float xf = figure.getAnimation().getX();
        float yf = figure.getAnimation().getY();
        int n = 50;
        double rad = Math.PI / n;
        double tetha0 = Math.atan2(y0, x0);

        int h = (int) ((x0 + xf) / 2);
        int k = (int) ((y0 + yf) / 2);

        int d = (int) Math.sqrt(Math.pow((xf - x0), 2) + Math.pow((yf - y0), 2));
        int r = d / 2;

        for (int i = 0; i <=n; i++) {
            int x = h + (int) (r * Math.cos(Math.PI + tetha0 + rad * i));
            int y = k + (int) (r * Math.sin(Math.PI + tetha0 + rad * i));

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    figure.setX(x);
                    figure.setY(y);
                    invalidate();
                }
            }, 50 * i);
        }
    }

    /**
     * Metodo para animaciones en forma lineal
     * @param figure
     */
    public void lineAnimation(Figure figure) {
        //int count = 0;
        int x = 0, y = 0;
        float x0 = figure.getX();
        float y0 = figure.getY();
        float xf = figure.getAnimation().getX();
        float yf = figure.getAnimation().getY();

        double angle = Math.atan2(yf - y0, xf - x0);

        while (x != xf || y != yf) {
            count++;
            if (x != xf) {
                x0 += Math.cos(angle);
                x = (int) Math.floor(x0);
            } else {
                x = (int) xf;
            }

            if (y != yf) {
                y0 += Math.sin(angle);
                y = (int) Math.floor(y0);
            } else {
                y = (int) yf;
            }

            int finalX = x;
            int finalY = y;
            Figure finalFigure = figure;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finalFigure.setX(finalX);
                    finalFigure.setY(finalY);
                    invalidate();
                }
            }, count);
        }
    }

    /**
     * Metodo para dibujar poligonos
     *
     * @param p
     * @param canvas
     * @param paint
     */
    public void drawPolygon(Polygon p, Canvas canvas, Paint paint) {
        float scale = (float) p.getHeight() / (float) p.getWidth();
        canvas.save();
        canvas.translate(p.getX(), p.getY());
        canvas.rotate(270f);
        canvas.scale(scale, 1f);
        int r = p.getWidth() / 2, n = p.getN();
        double rad = 2 * Math.PI / n;
        paint.setColor(getResources().getColor(getColor(p.getColor()), null));
        for (int i = 0; i < n; i++) {
            float x1 = r * (float) Math.cos(rad * i);
            float y1 = r * (float) Math.sin(rad * i);

            float x2 = r * (float) Math.cos(rad * (i + 1));
            float y2 = r * (float) Math.sin(rad * (i + 1));
            canvas.drawLine(x1, y1, x2, y2, paint);
        }
        canvas.restore();
    }

    /**
     * Metodo para obtener color segun el color de la figura
     *
     * @param clr
     * @return
     */
    private int getColor(String clr) {
        switch (clr) {
            case "azul":
                return R.color.blue;
            case "rojo":
                return R.color.red;
            case "verde":
                return R.color.green;
            case "amarillo":
                return R.color.yellow;
            case "naranja":
                return R.color.orange;
            case "morado":
                return R.color.purple;
            case "cafe":
                return R.color.brown;
            case "negro":
                return R.color.black;
        }
        return R.color.black;
    }
}
