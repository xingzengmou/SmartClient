package com.coopwell.smartclient.view;

import com.coopwell.smartclient.R;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * 初始化输入框
 */
public class InitView {

	private Context mContext;
	private LayoutInflater inflater;
    private PopupWindow mPopupWindow;
    private View mView;
    private EditText editText;
    private TextView btnOk;
    private TextView btnCancel;
    private boolean isShow;
	
	public InitView(Context context){
		this.mContext=context;
		inflater=LayoutInflater.from(mContext);
	}
	
	/**
	 * 初始化pop
	 */
	public void initPopWindow(int width,int height){
		mView=inflater.inflate(R.layout.init_dialog, null);
		//确定按钮
		btnOk=(TextView)mView.findViewById(R.id.btn_init_ok);
		//取消按钮
		btnCancel=(TextView)mView.findViewById(R.id.btn_init_cancel);
		//输入框
		editText=(EditText)mView.findViewById(R.id.edt_input_txt);
		
		btnOk.setOnClickListener(listener);
		btnCancel.setOnClickListener(listener);
		
		mPopupWindow=new PopupWindow(mView, width, height);
	}
	
	/**
	 * 显示pop
	 */
	public void showPopWindow(View view){
		isShow = true;
		mPopupWindow.setFocusable(false);
		mPopupWindow.update();
		mPopupWindow.setAnimationStyle(R.style.PopupAnimation);
		mPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
	}
	
	/**
	 * 隐藏pop
	 */
	public void hidePopWindow(){
		if (isShow) {
			if (mPopupWindow!=null) {
				mPopupWindow.dismiss();
				isShow=false;
			}
		}
	}
	
	/**
	 * 点击事件
	 */
	View.OnClickListener listener=new View.OnClickListener() {
		
//		@Override
		public void onClick(View v) {
			if(v.equals(btnOk)){
				mPopupWindow.dismiss();
				isShow=false;
				
			}else if (v.equals(btnCancel)) {
				mPopupWindow.dismiss();
				isShow=false;
			}
		}
	};
}
