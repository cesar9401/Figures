package com.cesar31.figures;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.cesar31.figures.graph.FigureContainer;
import com.cesar31.figures.graph.Polygon;

public class DrawPanel extends View {

    private FigureContainer container;

    public DrawPanel(Context context, FigureContainer container) {
        super(context);
        this.container = container;
    }

    /**
     * OnDraw para dibujar
     * @param canvas
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ResourceAsColor")
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(5);

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

    /**
     * Metodo para dibujar poligonos
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
