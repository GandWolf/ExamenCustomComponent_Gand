package com.example.gand.customcomponent_gand;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by gand on 2/02/17.
 */

public class Porcentaje extends View {

    private int porcentaje;
    private int angulo;

    private int colorArc, colorCentro, colorText;
    private float tamanioText;
    private int top, letf, right, bootom, diametro;

    private Paint paintArco, paintCentro, paintText;

    public Porcentaje(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Porcentaje,
                0, 0);

        try {
            colorArc = a.getColor(R.styleable.Porcentaje_colorArco, getResources().getColor(R.color.colorArco));
            colorCentro = a.getColor(R.styleable.Porcentaje_colorCentro, getResources().getColor(R.color.colorArco));
            colorText = a.getColor(R.styleable.Porcentaje_colorTexto, getResources().getColor(R.color.colorArco));
            tamanioText = a.getFloat(R.styleable.Porcentaje_tamnioTexto, getResources().getDimension(R.dimen.tamanioTexto));

        } finally {
            a.recycle();
        }

        iniciarPaint();

        porcentaje = 0;

    }

    private void iniciarPaint() {
        paintArco = new Paint();
        paintCentro = new Paint();
        paintText = new Paint();

        paintArco.setStyle(Paint.Style.FILL);
        paintArco.setAntiAlias(true);
        paintArco.setColor(colorArc);

        paintCentro.setStyle(Paint.Style.FILL);
        paintCentro.setAntiAlias(true);
        paintCentro.setColor(colorCentro);

        paintText.setColor(colorText);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setTextSize(tamanioText);
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
        angulo = (porcentaje * 360) /100;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        top = getPaddingTop();
        letf = getPaddingLeft();
        right = Math.min(w - getPaddingRight(), h - getPaddingBottom());
        bootom = right;
        diametro = right - letf;

        if(paintText.measureText(porcentaje + "%") > diametro*0.9){
            paintText.setTextSize((float) (diametro*0.25));
            tamanioText = (float) (diametro * 0.25);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(0xff000000);
        canvas.drawArc(letf, top, right, bootom, 270, angulo, true, paintArco);

        canvas.drawArc((float) (letf + diametro * 0.1), (float) (top + diametro * 0.1),
                (float) (right - diametro * 0.1), (float) (bootom - diametro *0.1),
                0, 360, true, paintCentro);

        canvas.drawText(porcentaje + "%", letf + diametro/2 + tamanioText/6,
                 top + diametro/2 + tamanioText/3, paintText);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (porcentaje<100){
            setPorcentaje(porcentaje+5);
        }else
            Toast.makeText(getContext(), "No se puede aumentar más", Toast.LENGTH_SHORT).show();


        return super.onTouchEvent(event);
    }
}
