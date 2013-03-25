package com.coopwell.smartclient.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blueocean.controlcmd.SceneControl;
import com.blueocean.controlcmd.SingleQueryCmd;
import com.blueocean.datagramHandle.DatagramHandle;
import com.blueocean.viewanalyze.ViewAnalyze;
import com.blueocean.viewex.ButtonEx;
import com.coopwell.smartclient.R;

public class ServeView extends View{
	
	private LayoutInflater inflater;
	private View mView;
//	private TextView btnMakeUp;
//	private TextView btnDisturb;
//	private TextView txtOutDoor;
//	private TextView txtInDoor;
//	private TextView txtWeather;
//	private TextView txtWindSpeed;
	
	private LinearLayout qingdashao_ly;
	private ImageView qingdashao_iv;
	private TextView qingdashao_tv;
	private boolean qingdashao_state = false;
	
	private LinearLayout qingwudarou_ly;
	private ImageView qingwudarou_iv;
	private TextView qingwudarou_tv;
	private boolean qingwudarou_state = false;
	
	private LinearLayout jingjihujiao_ly;
	private ImageView jingjihujiao_iv;
	private TextView jingjihujiao_tv;
	private boolean jingjihujiao_state = false;
	
	private LinearLayout qingshaohou_ly;
	private ImageView qingshaohou_iv;
	private TextView qingshaohou_tv;
	private boolean qingshaohou_state = false;
	
	private LinearLayout qingqiutuifang_ly;
	private ImageView qingqiutuifang_iv;
	private TextView qingqiutuifang_tv;
	private boolean qingqiutuifang_state = false;
	
	private LinearLayout qingqiufuwu_ly;
	private ImageView qingqiufuwu_iv;
	private TextView qingqiufuwu_tv;
	private boolean qingqiufuwu_state = false;
	
	private List<ButtonEx> list;
	private SceneControl mSceneControl;
	private DatagramHandle mDatagramHandle;
	
	public ServeView(Context context){
		super(context);
		mSceneControl = new SceneControl();
		mDatagramHandle = new DatagramHandle();
		ViewAnalyze va = new ViewAnalyze(context);
		list = va.getServiceButtons();
		inflater=LayoutInflater.from(context);
		mView=inflater.inflate(R.layout.serve_view, null);
		init();
		
//		mDatagramHandle.receiver(List.get(qListIndex).cmdName, SingleQueryCmd.getCmd());
	}
	
	public void showView(LinearLayout layout){
		layout.removeAllViews();
		mView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT));
		layout.addView(mView);
	}
	
	/**
	 * 初始化
	 */
	private void init(){
		qingdashao_ly = (LinearLayout) mView.findViewById(R.id.qingdashao_ly);
		qingdashao_ly.setOnClickListener(listener);
		qingdashao_iv = (ImageView) mView.findViewById(R.id.qingdashao_iv);
		qingdashao_tv = (TextView) mView.findViewById(R.id.qingdashao_tv);
		
		qingwudarou_ly = (LinearLayout) mView.findViewById(R.id.qingwudarou_ly);
		qingwudarou_ly.setOnClickListener(listener);
		qingwudarou_iv = (ImageView) mView.findViewById(R.id.qingwudarou_iv);
		qingwudarou_tv = (TextView) mView.findViewById(R.id.qingwudarou_tv);
		
		jingjihujiao_ly = (LinearLayout) mView.findViewById(R.id.jingjihujiao_ly);
		jingjihujiao_ly.setOnClickListener(listener);
		jingjihujiao_iv = (ImageView) mView.findViewById(R.id.jingjihujiao_iv);
		jingjihujiao_tv = (TextView) mView.findViewById(R.id.jingjihujiao_tv);
		
		qingshaohou_ly = (LinearLayout) mView.findViewById(R.id.qingshaohou_ly);
		qingshaohou_ly.setOnClickListener(listener);
		qingshaohou_iv = (ImageView) mView.findViewById(R.id.qingshaohou_iv);
		qingshaohou_tv = (TextView) mView.findViewById(R.id.qingshaohou_tv);
		
		qingqiutuifang_ly = (LinearLayout) mView.findViewById(R.id.qingqiutuifang_ly);
		qingqiutuifang_ly.setOnClickListener(listener);
		qingqiutuifang_iv = (ImageView) mView.findViewById(R.id.qingqiutuifang_iv);
		qingqiutuifang_tv = (TextView) mView.findViewById(R.id.qingqiutuifang_tv);
		
		qingqiufuwu_ly = (LinearLayout) mView.findViewById(R.id.qingqiufuwu_ly);
		qingqiufuwu_ly.setOnClickListener(listener);
		qingqiufuwu_iv = (ImageView) mView.findViewById(R.id.qingqiufuwu_iv);
		qingqiufuwu_tv = (TextView) mView.findViewById(R.id.qingqiufuwu_tv);
		
//		//请打扫
//		btnMakeUp=(TextView)mView.findViewById(R.id.btn_make_up);
//		btnMakeUp.setOnClickListener(listener);
//		
//		//请忽打扰
//		btnDisturb=(TextView)mView.findViewById(R.id.btn_disturb);
//		btnDisturb.setOnClickListener(listener);
//		
//		//室外气温
//		txtOutDoor=(TextView)mView.findViewById(R.id.txt_out_door);
//		
//		//室内气温
//		txtInDoor=(TextView)mView.findViewById(R.id.txt_in_door);
//		
//		//天气状况
//		txtWeather=(TextView)mView.findViewById(R.id.txt_weather);
//		
//		//室外风速
//		txtWindSpeed=(TextView)mView.findViewById(R.id.txt_wind_speed);
	}
	
	private void changeButtonState(int index, boolean state) {
		if (state) {
			if (index == 0) {
				qingdashao_iv.setBackgroundResource(R.drawable.make_up_d);
				qingdashao_tv.setBackgroundResource(R.drawable.service_bk_en);
			} else if (index == 1) {
				qingwudarou_iv.setBackgroundResource(R.drawable.disturb_d);
				qingwudarou_tv.setBackgroundResource(R.drawable.service_bk_en);
			} else if (index == 2) {
				jingjihujiao_iv.setBackgroundResource(R.drawable.jingjihujiao_en);
				jingjihujiao_tv.setBackgroundResource(R.drawable.service_bk_en);
			} else if (index == 3) {
				qingshaohou_iv.setBackgroundResource(R.drawable.qingshaohou_en);
				qingshaohou_tv.setBackgroundResource(R.drawable.service_bk_en);
			} else if (index == 4) {
				qingqiutuifang_iv.setBackgroundResource(R.drawable.qingqiutuifang_en);
				qingqiutuifang_tv.setBackgroundResource(R.drawable.service_bk_en);
			}  else if (index == 5) {
				qingqiufuwu_iv.setBackgroundResource(R.drawable.qingqiufuwu_en);
				qingqiufuwu_tv.setBackgroundResource(R.drawable.service_bk_en);
			}
		} else {
			if (index == 0) {
				qingdashao_iv.setBackgroundResource(R.drawable.make_up_n);
				qingdashao_tv.setBackgroundResource(R.drawable.service_bk_dis);
			} else if (index == 1) {
				qingwudarou_iv.setBackgroundResource(R.drawable.disturb_n);
				qingwudarou_tv.setBackgroundResource(R.drawable.service_bk_dis);
			} else if (index == 2) {
				jingjihujiao_iv.setBackgroundResource(R.drawable.jingjihujiao);
				jingjihujiao_tv.setBackgroundResource(R.drawable.service_bk_dis);
			} else if (index == 3) {
				qingshaohou_iv.setBackgroundResource(R.drawable.qingshaohou);
				qingshaohou_tv.setBackgroundResource(R.drawable.service_bk_dis);
			} else if (index == 4) {
				qingqiutuifang_iv.setBackgroundResource(R.drawable.qingqiutuifang);
				qingqiutuifang_tv.setBackgroundResource(R.drawable.service_bk_dis);
			} else if (index == 5) {
				qingqiufuwu_iv.setBackgroundResource(R.drawable.qingqiufuwu);
				qingqiufuwu_tv.setBackgroundResource(R.drawable.service_bk_dis);
			}
		}
	}
	
	/**
	 * 点击事件
	 */
	View.OnClickListener listener=new View.OnClickListener() {
		
//		@Override
		public void onClick(View v) {
			if (v.equals(qingdashao_ly)) {
				if (list.size() > 0) {
						ButtonEx be = list.get(0);
						if (be.getCmdName().equals("scene")) {
							mSceneControl.setSceneCode(be.getTargetCode());
							mDatagramHandle.send(mSceneControl.getCmd());
							qingdashao_state = qingdashao_state ? false : true;
							changeButtonState(0, qingdashao_state);
						 }
				}
			} else if (v.equals(qingwudarou_ly)) {
				if (list.size() > 1) {
					ButtonEx be = list.get(1);
					if (be.getCmdName().equals("scene")) {
						mSceneControl.setSceneCode(be.getTargetCode());
						mDatagramHandle.send(mSceneControl.getCmd());
						qingwudarou_state = qingwudarou_state ? false : true;
						changeButtonState(1, qingwudarou_state);
					 }
				}
			} else if (v.equals(jingjihujiao_ly)) {
				if (list.size() > 2) {
					ButtonEx be = list.get(2);
					if (be.getCmdName().equals("scene")) {
						mSceneControl.setSceneCode(be.getTargetCode());
						mDatagramHandle.send(mSceneControl.getCmd());
						jingjihujiao_state  = jingjihujiao_state ? false : true;
						changeButtonState(2, jingjihujiao_state);
					 }
				}
			} else if (v.equals(qingshaohou_ly)) {
				if (list.size() > 3) {
					ButtonEx be = list.get(3);
					if (be.getCmdName().equals("scene")) {
						mSceneControl.setSceneCode(be.getTargetCode());
						mDatagramHandle.send(mSceneControl.getCmd());
						qingshaohou_state = qingshaohou_state ? false : true;
						changeButtonState(3, qingshaohou_state);
					 }
				}
			} else if (v.equals(qingqiutuifang_ly)) {
				if (list.size() > 4) {
					ButtonEx be = list.get(4);
					if (be.getCmdName().equals("scene")) {
						mSceneControl.setSceneCode(be.getTargetCode());
						mDatagramHandle.send(mSceneControl.getCmd());
						qingqiutuifang_state = qingqiutuifang_state ? false : true;
						changeButtonState(4, qingqiutuifang_state);
					 }
				}
			} else if (v.equals(qingqiufuwu_ly)) {
				if (list.size() > 5) {
					ButtonEx be = list.get(5);
					if (be.getCmdName().equals("scene")) {
						mSceneControl.setSceneCode(be.getTargetCode());
						mDatagramHandle.send(mSceneControl.getCmd());
						qingqiufuwu_state = qingqiufuwu_state ? false : true;
						changeButtonState(5, qingqiufuwu_state);
					 }
				}
			}
		}
	};
}
