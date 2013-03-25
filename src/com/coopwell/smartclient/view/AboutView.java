package com.coopwell.smartclient.view;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blueocean.kongtiao.KongTiaoControl;
import com.blueocean.sharedpreference.SharedPreferenceConfig;
import com.coopwell.smartclient.R;
import com.coopwell.smartclient.RemoteServe;
import com.coopwell.smartclient.RemoteServe.ICallback;
import com.coopwell.smartclient.mode.ACInfo;
import com.coopwell.smartclient.mode.AcAdapterInfo;
import com.coopwell.smartclient.mode.DataInfo;
import com.coopwell.smartclient.view.SpinnerView.ISelectListener;

import java.util.ArrayList;
import java.util.Calendar;

public class AboutView extends View implements View.OnClickListener {
	private LayoutInflater inflater;
	private View mView;
	private Context mContext;
	private EditText ipEv;
	private EditText portEv;
	private SharedPreferenceConfig mSharedPreferenceConfig;
	private LinearLayout inputPWDLy;
	private LinearLayout netLy;
	private EditText inputPWDET;
	private Button btnInputPwdOk;
	private Button btnSure;
	
	public AboutView(Context context) {
		super(context);
		inflater = LayoutInflater.from(context);
		mView = inflater.inflate(R.layout.net_setting, null);
		mContext = context;
		
		mSharedPreferenceConfig = new SharedPreferenceConfig(mContext);
		
		inputPWDLy = (LinearLayout) mView.findViewById(R.id.input_pwd_linearlayout);
		netLy = (LinearLayout) mView.findViewById(R.id.net_linearlayout);
		inputPWDET = (EditText) mView.findViewById(R.id.input_pwd_et);
		btnInputPwdOk = (Button) mView.findViewById(R.id.btn_input_pwd_ok);
		btnInputPwdOk.setOnClickListener(this);
		
		ipEv = (EditText) mView.findViewById(R.id.net_ip);
		portEv = (EditText) mView.findViewById(R.id.net_port);
		ipEv.setText(mSharedPreferenceConfig.getString("net_ip", "192.168.1.127"));
		portEv.setText(mSharedPreferenceConfig.getString("net_port", "3342"));
		
		btnSure = (Button) mView.findViewById(R.id.sure_btn);
		btnSure.setOnClickListener(this);
	}

	public void showView(LinearLayout layout) {
		layout.removeAllViews();
		LayoutParams param = new LayoutParams(-1, -1);
		mView.setLayoutParams(param);
		layout.addView(mView);
		inputPWDLy.setVisibility(View.VISIBLE);
		netLy.setVisibility(View.GONE);
	}

//	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.equals(btnInputPwdOk)) {
			if (inputPWDET.getText().toString().trim().equals("88888")) {
				inputPWDLy.setVisibility(View.GONE);
				netLy.setVisibility(View.VISIBLE);
			} else {
				AlertDialog.Builder b = new AlertDialog.Builder(mContext);
				b.setMessage("密码输入错误，请联系管理员.");
				b.setPositiveButton("确定", null);
				b.show();
			}
		} else if (v.equals(btnSure)) {
			mSharedPreferenceConfig.writeString("net_ip", ipEv.getText().toString());
			mSharedPreferenceConfig.writeString("net_port", portEv.getText().toString());
			AlertDialog.Builder b = new AlertDialog.Builder(mContext);
			b.setMessage("保存成功");
			b.setPositiveButton("确定", null);
			b.show();
		}
	}

}