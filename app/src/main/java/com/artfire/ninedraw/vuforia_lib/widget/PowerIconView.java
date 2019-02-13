package com.artfire.ninedraw.vuforia_lib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者:李少波
 * 邮箱:lishaobo@seengene.com
 * 日期:16/7/13
 */

/**
 * 纯手绘电池图标。。网上荡的
 */
public class PowerIconView extends View {
    private int borderColor = 0xffC1C1C1;
    private int fillBgColor = 0x009E9E9E;
//    private int fillBgColor = 0xffdedede;
    private int whiteColor = 0xffFFFFFF;
    private int greenColor = 0xff12b657;
    private int redColor = 0xffe04d4d;
    private int curColor = 0xffFFFFFF;

    private float width;
    private float height;
    private float borderWidth = 2;
    private float corner;
    private float headerWidth;

    private float value = 0;

    public PowerIconView(Context context) {
        super(context);
    }
    public PowerIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setValue(float value,int color){
        this.value = value;
        if(color==1){
            curColor = whiteColor;
        }
        if(color==2){
            curColor = greenColor;
        }
        if(color==3){
            curColor = redColor;
        }
        this.invalidate();
    }

    public void onDraw(Canvas canvas) {
        width = getWidth();
        height = getHeight();
        headerWidth = width / 10;
        corner = height /5;
        drawBackGround(canvas);
        if (value > 0){
            drawSelectView(canvas,curColor);
        }
    }
    /**
     * 绘制背景
     * @param canvas
     */
    private void drawBackGround(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setColor(fillBgColor);
        paint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        path.moveTo(0, corner);
        path.quadTo(0, 0, corner, 0);
        path.lineTo(width - headerWidth - corner, 0);
        path.quadTo(width - headerWidth, 0, width - headerWidth, corner);
        path.lineTo(width - headerWidth, height / 4);
        path.lineTo(width - corner / 2, height / 4);
        path.quadTo(width, height / 4, width, height / 4 + corner / 2);
        path.lineTo(width, height / 4 * 2 - corner / 2);
        path.quadTo(width, height / 4 * 3, width - corner / 2, height / 4 * 3);
        path.lineTo(width - headerWidth, height / 4 * 3);
        path.lineTo(width - headerWidth, height - corner);
        path.quadTo(width - headerWidth, height, width - headerWidth - corner, height);
        path.lineTo(corner, height);
        path.quadTo(0, height, 0, height - corner);
        path.lineTo(0, corner);
        path.close();
        canvas.drawPath(path, paint);

        paint.setColor(borderColor);
        paint.setStrokeWidth(borderWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
    }
    /**
     * 绘制电量选中效果
     * @param canvas
     */
    private void drawSelectView(Canvas canvas, int color){
        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setColor(color);
//        paint.setColor(value <= 20 ? redColor : whiteColor);
        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.moveTo(borderWidth, borderWidth + corner / 2);
        path.quadTo(borderWidth, borderWidth, borderWidth + corner / 2, borderWidth);
        float xTo = (value / 100.0f) * (width - 2 * borderWidth);
        if (value >= 100){ //满电
            path.lineTo(width - headerWidth - borderWidth - corner / 2, borderWidth);
            path.quadTo(width - headerWidth - borderWidth, borderWidth, width - headerWidth - borderWidth, borderWidth + corner / 2);
            path.lineTo(width - headerWidth - borderWidth, height / 4 + borderWidth);
            path.lineTo(width - borderWidth, height / 4 + borderWidth);
            path.lineTo(width - borderWidth, height / 4 * 3 - borderWidth);
            path.lineTo(width - headerWidth - borderWidth, height / 4 * 3 - borderWidth);
            path.lineTo(width - headerWidth - borderWidth, height - borderWidth - corner / 2);
            path.quadTo(width - headerWidth - borderWidth, height - borderWidth, width - headerWidth - borderWidth - corner / 2, height - borderWidth);
            path.lineTo(borderWidth + corner / 2, height - borderWidth);
            path.quadTo(borderWidth, height - borderWidth, borderWidth, height - borderWidth - corner / 2);
            path.lineTo(borderWidth, borderWidth + corner / 2);
            path.close();
        } else if (xTo < corner / 2){ //电量最低
            path.lineTo(borderWidth + corner / 2, height -borderWidth);
            path.quadTo(borderWidth, height - borderWidth, borderWidth, height -borderWidth - corner / 2);
            path.lineTo(borderWidth, borderWidth + corner / 2);
        } else if ((borderWidth + corner / 2 + xTo) > (width - headerWidth)){ //电量超出了右侧的正极图形处
            path.lineTo(width - headerWidth - borderWidth - corner / 2, borderWidth);
            path.quadTo(width - headerWidth - borderWidth, borderWidth, width - headerWidth - borderWidth, borderWidth + corner / 2);
            path.lineTo(width - headerWidth - borderWidth, height / 4 + borderWidth);
            path.lineTo(borderWidth + xTo, height / 4 + borderWidth);
            path.lineTo(borderWidth + xTo, height / 4 * 3 - borderWidth);
            path.lineTo(width - headerWidth - borderWidth, height / 4 * 3 - borderWidth);
            path.lineTo(width - headerWidth - borderWidth, height - borderWidth - corner / 2);
            path.quadTo(width - headerWidth - borderWidth, height - borderWidth, width - headerWidth - borderWidth - corner / 2, height - borderWidth);
            path.lineTo(borderWidth + corner / 2, height - borderWidth);
            path.quadTo(borderWidth, height - borderWidth, borderWidth, height - borderWidth - corner / 2);
            path.lineTo(borderWidth, borderWidth + corner / 2);
        } else { //电量只需要填充矩形区域
            path.lineTo(borderWidth + xTo, borderWidth);
            path.lineTo(borderWidth + xTo, height - borderWidth);
            path.lineTo(borderWidth + corner / 2, height - borderWidth);
            path.quadTo(borderWidth, height - borderWidth, borderWidth, height - borderWidth - corner / 2);
            path.lineTo(borderWidth, borderWidth + corner / 2);
        }
        canvas.drawPath(path, paint);
    }
}

