package com.westhillcs.compassproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Chandan on 6/3/2016.
 */
public class ArrowView extends View{

    private Bitmap arrow = BitmapFactory.decodeResource(this.getResources(), R.mipmap.arrow);
    private float rotationAngle;


    public ArrowView(Context context) {
        super(context);
        rotationAngle= (float)0.0;
    }

    public void setRotationAngle(float angle) {
        rotationAngle = angle;
    }
/*
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(48);
        paint.setColor(Color.BLUE);
        canvas.drawText("N", canvas.getWidth()/2, 50, paint);
        canvas.drawText("S", canvas.getWidth()/2, canvas.getHeight()-50, paint);
        canvas.restore();
    }
    */

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        int x = canvas.getWidth()/2 - arrow.getWidth()/2;
        int y = canvas.getHeight()/2 - arrow.getHeight()/2;


        //this.rotationAngle = 45;
        canvas.rotate(this.rotationAngle, canvas.getWidth()/2, canvas.getHeight()/2);
        canvas.drawBitmap(arrow, x, y, null);



        canvas.restore();
    }
}
