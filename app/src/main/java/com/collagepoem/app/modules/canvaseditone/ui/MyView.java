package com.collagepoem.app.modules.canvaseditone.ui;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView extends View {

    private int x;
    private int y;
    private int m;
    private int n;
    private boolean sign;//绘画标记位
    private Paint paint;//画笔

    public MyView(Context context) {
        super(context);
        paint = new Paint(Paint.FILTER_BITMAP_FLAG);
    }

    /**
     * 设置画笔
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {

        Log.e(TAG, String.valueOf(sign));
        if(sign){
            paint.setColor(Color.TRANSPARENT);
        }else{
            paint.setColor(Color.GREEN);
            paint.setAlpha(80);
            Log.e(TAG, String.valueOf(y));
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
        this.sign = sign;
    }


}

