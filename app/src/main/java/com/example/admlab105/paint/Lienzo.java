package com.example.admlab105.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Lienzo extends View {

    //Path que utilizaré para ir pintando las lineas
    private Path drawPath;
    //Paint de dibujar y Paint de canvas
    private Paint drawPaint;
    private Paint canvasPaint;
    //Color inicial
    private int paintColor = 0xFF000000;
    private int paintColorDefecto = 0xFF000000;
    //Canvas
    private Canvas drawCanvas;
    //Canvas para guardar
    private Bitmap canvasBitMap;
    float tamPunto;
    private boolean borrado = false;

    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing() {
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    //Tamaño asignado a la vista
    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w,h, oldW, oldH);
        canvasBitMap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitMap);
    }

    //Pinta la vista. Será llamado desde onTouchEvent
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitMap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(touchX, touchY);
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        //repintar
        invalidate();
        return true;
    }

    //Actualiza color
    public void setColor(String newColor) {
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }

    //Pone el tamaño del punto
    public void setTamPunto(float nTam) {
        drawPaint.setStrokeWidth(nTam);
    }

    //Pone borrado en true o false
    public void setBorrado(boolean estaBorrado) {
        borrado = estaBorrado;
        if (borrado) {
            drawPaint.setColor(Color.WHITE);
        } else {
            drawPaint.setColor(paintColor);
        }
    }

    public void nuevoDibujo() {
        drawPaint.setColor(paintColorDefecto);
        drawPaint.setStrokeWidth(20);
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }
}