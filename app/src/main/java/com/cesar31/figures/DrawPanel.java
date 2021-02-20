package com.cesar31.figures;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.cesar31.figures.graph.FigureContainer;

public class DrawPanel extends View {

    private FigureContainer container;

    public DrawPanel(Context context, FigureContainer container) {
        super(context);
        this.container = container;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ResourceAsColor")
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(5);

        this.container.getFour().forEach(f -> {
            if(f.getType().equals("circulo")) {
                paint.setColor(getResources().getColor(getColor(f.getColor()), null));
                canvas.drawCircle(f.getX(), f.getY(), f.getSide(), paint);
            }

            if(f.getType().equals("cuadrado")) {
                paint.setColor(getResources().getColor(getColor(f.getColor()), null));
                canvas.drawRect(f.getX(), f.getY(), f.getX() + f.getSide(), f.getY() + f.getSide(), paint);
            }
        });

        this.container.getFive().forEach(f -> {
            if(f.getType().equals("rectangulo")) {
                paint.setColor(getResources().getColor(getColor(f.getColor()), null));
                canvas.drawRect(f.getX(), f.getY(), f.getX() + f.getX2(), f.getY() + f.getY2(), paint);
            }

            if(f.getType().equals("linea")) {
                paint.setColor(getResources().getColor(getColor(f.getColor()), null));
                canvas.drawLine(f.getX(), f.getY(), f.getX2(), f.getY2(), paint);
            }
        });

        /*
        canvas.save();
        canvas.translate(0, -800);
        canvas.scale(1f, 2f);
        drawPolygon(canvas, paint);
        canvas.restore();
        drawPolygon(canvas, paint);
         */
    }

    public void drawPolygon(Canvas canvas, Paint paint) {
        int h = 600, k = 800, r = 450, n = 6;
        double rad = 2 * Math.PI / n;
        canvas.drawPoint(h, k, paint);
        for (int i = 0; i < n; i++) {
            float x1 = h + r * (float) Math.cos(rad * i);
            float y1 = k + r * (float) Math.sin(rad * i);

            float x2 = h + r * (float) Math.cos(rad * (i + 1));
            float y2 = k + r * (float) Math.sin(rad * (i + 1));
            canvas.drawLine(x1, y1, x2, y2, paint);
        }
    }

    private int getColor(String clr) {
        switch(clr) {
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
