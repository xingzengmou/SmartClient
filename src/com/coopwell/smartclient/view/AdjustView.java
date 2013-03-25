package com.coopwell.smartclient.view;

import com.coopwell.smartclient.LocationType;
import com.coopwell.smartclient.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.SeekBar;

/**
 * 调节界面
 */
public class AdjustView{

	private Context mContext;
	private LayoutInflater inflater;
    private PopupWindow mPopupWindow;
    private View mView;
    private SeekBar mSeekBar;
    public boolean isShow;
    private int mProgress;
    private Callback callback; 
    private int mId;
    
	public AdjustView(Context context){
    	this.mContext=context;
    	inflater=LayoutInflater.from(mContext);
    }

	/**
     * 初始化pop
     */
    public void initPopWindow(int width,int height){
    	if (mPopupWindow==null) {
    		mView=inflater.inflate(R.layout.adjust_view, null);
        	mSeekBar=(SeekBar)mView.findViewById(R.id.see_bar);
        	mSeekBar.setOnSeekBarChangeListener(seekListener);
        	mPopupWindow=new PopupWindow(mView, width, height);
        	mPopupWindow.setOutsideTouchable(true);
        	mPopupWindow.getContentView().setOnTouchListener(new OnTouchListener() {
				
//				@Override
				public boolean onTouch(View v, MotionEvent event) {
					mPopupWindow.dismiss();
					isShow=false;
					return false;
				}
			});
		}
    }
    
    /**
     * 显示pop
     */
    public void showPopWindow(View view,int x,int y,LocationType type,int progress,int id){
    	if (!isShow) {
    		this.mId=id;
        	this.mProgress=progress;
        	isShow = true;
    		mPopupWindow.setFocusable(false);
    		mPopupWindow.update();
    		mPopupWindow.showAsDropDown(view, -1, -1);
		}
    }
    
    /**
     * 调节
     */
    SeekBar.OnSeekBarChangeListener seekListener=new SeekBar.OnSeekBarChangeListener() {
		
//		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			if (callback!=null) {
				callback.onChange(mProgress,mId);
			}
		}
		
//		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			
		}
		
//		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			mProgress=progress;
		}
	};
    
	/**
	 * 隐藏pop
	 */
	public void hidePopWindow(){
		if (isShow) {
			mPopupWindow.dismiss();
			isShow=false;
		}
	}
	
    /**
     * 点击事件
     */
    View.OnClickListener listener=new View.OnClickListener() {
		
//		@Override
		public void onClick(View v) {
			
		}
	};
	
	/**
	 * 回调
	 */
	public interface Callback{
		void onChange(int progress,int id);
	}
	
	public void setCallback(Callback callback) {
		this.callback = callback;
	}

}
