package com.wxf.mvpframework;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by Administrator on 2017/12/29.
 */

public class MyAnimView extends View
{
    public static final float RADIUS = 50f;
    private Paint mPaint;
    private Point mPoint;

    public MyAnimView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if(mPoint == null)
        {
            mPoint = new Point(RADIUS,RADIUS);
            drawCircle(canvas);
            startAnimation();
        }
        else
        {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas)
    {
        float x = mPoint.getX();
        float y = mPoint.getY();

        canvas.drawCircle(x,y,RADIUS,mPaint);
    }

    private void startAnimation()
    {
        Point startPoint = new Point(RADIUS,RADIUS);
        Point endPoint = new Point(getWidth() - RADIUS,getHeight() - RADIUS);

        ValueAnimator objectAnimator = ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        objectAnimator.setInterpolator(new BounceInterpolator());
        objectAnimator.setDuration(12000);
        objectAnimator.start();
    }

    public class PointEvaluator implements TypeEvaluator {

        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Point startPoint = (Point) startValue;
            Point endPoint = (Point) endValue;
            float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
            float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
            Point point = new Point(x, y);
            return point;
        }

    }
}
