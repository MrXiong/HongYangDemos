package com.zx.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.zx.customview.R;

import static android.content.ContentValues.TAG;
import static android.util.TypedValue.COMPLEX_UNIT_SP;

/**
 * Created by user on 2017/11/15.
 */

public class CustomTitleView extends View {

    private String title;
    private int titleColor;
    private int titleSize;
    private final Paint mPaint;
    private final Rect mRect;

    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context) {
        this(context, null);
    }


    public CustomTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.v(TAG, "===============CustomTitleView");
        //获得我们定义的属性

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);


        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);

            switch (attr) {
                case R.styleable.CustomTitleView_title:
                    title = typedArray.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleColor:
                    titleColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTitleView_titleSize:
                    // 默认设置为16sp，TypeValue也可以把sp转化为px
                    titleSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }

        //这个函数调用了，不能再使用该数组了，比如先调用typedArray.recycle();，再调用title = typedArray.getString(attr);是拿不到值的
        typedArray.recycle();

        //获得绘制文本的宽和高
        mPaint = new Paint();
        mPaint.setTextSize(titleSize);

        mRect = new Rect();
        mPaint.getTextBounds(title, 0, title.length(), mRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(titleSize);
            mPaint.getTextBounds(title, 0, title.length(), mRect);
            float textWidth = mRect.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if(heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(titleSize);
            mPaint.getTextBounds(title, 0, title.length(), mRect);
            float textHeight = mRect.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
        Log.v(TAG, width+"==============="+height);
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();




        canvas.drawRect(10, 10, measuredWidth, measuredHeight, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawText(title, getWidth() / 2 - mRect.width() / 2, getHeight() / 2 + mRect.width() / 2, mPaint);
    }
}
