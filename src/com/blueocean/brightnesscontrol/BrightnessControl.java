package com.blueocean.brightnesscontrol;

import com.blueocean.controlcmd.SingleControl;
import com.blueocean.datagramHandle.DatagramHandle;
import com.blueocean.sharedpreference.SharedPreferenceConfig;
import com.coopwell.smartclient.view.ControlView;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class BrightnessControl implements OnSeekBarChangeListener {
	private static final String TAG = "BrightnessControl";
	private Dialog brightnessDialog;
	private Context ctx;
	private SeekBar mSeekBar;
	private SingleControl mSingleControl;
	private DatagramHandle mDatagramHandle;
	private SharedPreferenceConfig mSharedPreferenceConfig;

	
	public BrightnessControl(Context ctx) {
		this.ctx = ctx;
		brightnessDialog = new Dialog(ctx);
		brightnessDialog = new Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar);
		brightnessDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		brightnessDialog.setContentView(com.coopwell.smartclient.R.layout.light_brightness_progress);
		mSeekBar = (SeekBar) brightnessDialog.findViewById(com.coopwell.smartclient.R.id.brightness_seekbar);
		Button btnClose = (Button) brightnessDialog.findViewById(com.coopwell.smartclient.R.id.btn_close);
		btnClose.setOnClickListener(quitView);
		mSeekBar.setMax(100);
		mSeekBar.setOnSeekBarChangeListener(this);
		mSingleControl = new SingleControl();
		mDatagramHandle = new DatagramHandle();
		mSharedPreferenceConfig = new SharedPreferenceConfig(ctx);
		mDatagramHandle.setIP(mSharedPreferenceConfig.getString("net_ip", "192.168.1.127"));
		mDatagramHandle.setPort(Integer.parseInt(mSharedPreferenceConfig.getString("net_port", "3342")));
	}
	
	private View.OnClickListener quitView = new View.OnClickListener() {
		
//		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			brightnessDialog.dismiss();
		}
	};
	
	public void show() {
		brightnessDialog.show();
	}
	
	public void dismiss() {
		brightnessDialog.dismiss();
	}
	
	public void setLightHardwareID(int id) {
		mSingleControl.setHardwareId(id);
	}
	
	public void setLightTargetID(int type) {
		mSingleControl.setTargetId(type);
	}

	public void setBrightness(int brightness) {
		mSeekBar.setProgress(brightness);
	}
	
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		mSingleControl.setTargetState(progress);
//		mSingleControl.control();
		mDatagramHandle.send(mSingleControl.getCmd());
		if (ControlView.brightnessCurrentBtn != null)
			ControlView.brightnessCurrentBtn.setBrightness(progress);
		mSeekBar.setProgress(progress);
		Log.e(TAG, " start progress = " + progress);
	}

//	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		mSingleControl.setTargetState(mSeekBar.getProgress());
//		mSingleControl.control();
		mDatagramHandle.send(mSingleControl.getCmd());
		if (ControlView.brightnessCurrentBtn != null)
			ControlView.brightnessCurrentBtn.setBrightness(mSeekBar.getProgress());
		Log.e(TAG, " stop progress = " + mSeekBar.getProgress());
		mSeekBar.setProgress(mSeekBar.getProgress());
	}
	
	
}
