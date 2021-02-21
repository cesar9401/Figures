package com.cesar31.figures;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private TableLayout table;
    private ArrayList<TableRow> rows;
    private Activity activity;
    private Resources rs;
    private int row, col;

    public Table(Activity activity, TableLayout table) {
        this.activity = activity;
        this.table = table;
        this.rs = this.activity.getResources();
        this.row = 0;
        this.col = 0;
        this.rows = new ArrayList<>();
    }

    public void addHeader(int header) {
        TableRow.LayoutParams layoutCell;
        TableRow r = new TableRow(this.activity);
        TableRow.LayoutParams layoutRow = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        r.setLayoutParams(layoutRow);

        String[] arrayHeader = rs.getStringArray(header);
        this.col = arrayHeader.length;

        for(int i = 0; i < this.col; i++) {
            TextView text = new TextView(this.activity);
            layoutCell = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
            text.setText(arrayHeader[i]);
            text.setGravity(Gravity.CENTER_HORIZONTAL);
            text.setTextAppearance(R.style.cell_style);
            text.setBackgroundResource(R.drawable.table_cell_header);
            text.setLayoutParams(layoutCell);
            r.addView(text);
        }

        this.table.addView(r);
        this.rows.add(r);
        this.row++;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addRowTable(List<String> elements) {
        TableRow.LayoutParams layoutCell;
        TableRow.LayoutParams layoutRow = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        TableRow r = new TableRow(this.activity);
        r.setLayoutParams(layoutRow);

        for (int i = 0; i < elements.size(); i++) {
            TextView text = new TextView(this.activity);
            text.setText(String.valueOf(elements.get(i)));
            text.setGravity(Gravity.CENTER_HORIZONTAL);
            text.setTextAppearance(R.style.cell_style);
            text.setBackgroundResource(R.drawable.cell_style);
            layoutCell = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
            text.setLayoutParams(layoutCell);

            r.addView(text);
        }

        this.table.addView(r);
        this.rows.add(r);
        this.row++;
    }

    private int getWidthPx(String text) {
        Paint paint = new Paint();
        Rect bound = new Rect();
        paint.setTextSize(48);
        paint.getTextBounds(text, 0, text.length(), bound);
        return bound.width();
    }
}
