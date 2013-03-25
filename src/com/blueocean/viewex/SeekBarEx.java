package com.blueocean.viewex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class SeekBarEx extends SeekBar {
	private String text;
    private Paint mPaint;
    
	public SeekBarEx(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initText();
	}
	
	public SeekBarEx(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initText();
	}
	
	public SeekBarEx(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initText();
	}
	
	@Override
    protected synchronized void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        Rect rect = new Rect();
        this.mPaint.getTextBounds(this.text, 0, this.text.length(), rect);
        int x = (getWidth() / 2) - rect.centerX();  
        int y = (getHeight() / 2) - rect.centerY();  
        canvas.drawText(this.text, x, y, this.mPaint);  
    }
	
	//初始化，画笔
    private void initText(){
        this.mPaint = new Paint();
        this.mPaint.setColor(Color.WHITE);
        this.mPaint.setTextSize(18);
        //去掉锯齿
        this.mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public synchronized void setProgress(int brightness) {
    	text = brightness + "/" + this.getMax();
    	super.setProgress(brightness);
    }
    
}
