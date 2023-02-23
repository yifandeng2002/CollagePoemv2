package com.collagepoem.app.modules.canvaspoem.data.viewmodel;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MyView extends View {

    private int x;
    private int y;
    private int m;
    private int n;
    private boolean sign ;//绘画标记位
    private Paint paint;//画笔
    private int width = 100;
    private int height = 100;

    public MyView(Context context) {
        super(context);
        paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        setWillNotDraw(false);
    }


    /**
     * 设置画笔
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("no","1111");
        if(!sign){
            paint.setColor(Color.TRANSPARENT);
        }else{
            paint.setColor(Color.WHITE);
            paint.setAlpha(200);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            canvas.drawRect(new Rect(x, y, m, n), paint);

        }
        super.onDraw(canvas);

    }

    public void setSeat(int x,int y,int m,int n){
        this.x = x;
        this.y = y;
        this.m = m;
        this.n = n;
    }

    public boolean isSign() {
        return sign;
    }
    public void setSign(boolean sign) {
        Log.e("tha","111");
        this.sign = sign;
    }
}

