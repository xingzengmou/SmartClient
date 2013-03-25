package com.coopwell.smartclient.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.blueocean.controlcmd.SceneControl;
import com.blueocean.controlcmd.SingleControl;
import com.blueocean.datagramHandle.DatagramHandle;
import com.blueocean.viewex.ButtonEx;
import com.coopwell.smartclient.R;

public class MusicView extends View implements OnClickListener{

	private LayoutInflater inflater;
	private View mView;
	private TextView btnDrapesDown;
	private TextView btnDrapesUp;
	private TextView btnDayDown;
	private TextView btnDayUp;
	private TextView btnNightDown;
	private TextView btnNightUp;
	private TextView btnBathroomDown;
	private TextView btnBathroomUp;
	private TextView txtMusicState;
	private TextView btnChanelOne;
	private TextView btnChanelTwo;
	private TextView btnChanelThree;
	private TextView btnChanelFour;
	private TextView btnMusicNext;
	private TextView btnMusicPre;
	private ProgressBar mMusicBar;
	private LinearLayout linearLayout01;

	private SceneControl mSceneControl;
	private SingleControl mSingleControl;
	private DatagramHandle mDatagramHandle;
	
	
	public MusicView(Context context, List<ButtonEx> list){
		super(context);
		inflater=LayoutInflater.from(context);
		mView=inflater.inflate(R.layout.music_view_current, null);
//		init();
		mSingleControl = new SingleControl();
		mDatagramHandle = new DatagramHandle();
		mSceneControl = new SceneControl();
		loadButtons(context, list);
	}
	
	private void loadButtons(Context context, List<ButtonEx> btnList) {
		linearLayout01 = (LinearLayout) mView.findViewById(R.id.linearlayout01);
		int index = 0;
		for (int i = 0; i < btnList.size() / 4; i ++) {
			LinearLayout linearLayout02 = new LinearLayout(context);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			linearLayout01.addView(linearLayout02, lp);
			for (int j = 0; j < 4; j ++) {
				lp = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
				linearLayout02.addView(btnList.get(index), lp);
				index ++;
			}
		}
		LinearLayout linearLayout02 = new LinearLayout(context);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		linearLayout01.addView(linearLayout02, lp);
		for (int j = index; j < btnList.size(); j ++) {
			lp = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
			linearLayout02.addView(btnList.get(index), lp);
			index ++;
		}
		
		for (ButtonEx be: btnList) {
			be.setBackgroundResource(R.drawable.curtain_button_selector);
			be.setOnClickListener(this);
		}
		
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
		
		/**
		 * 卧室布帘
		 */
		//向下
		btnDrapesDown=(TextView)mView.findViewById(R.id.btn_drapes_down);
		btnDrapesDown.setOnClickListener(this);
		//向上
		btnDrapesUp=(TextView)mView.findViewById(R.id.btn_drapes_up);
		btnDrapesUp.setOnClickListener(this);
		
		/**
		 * 卧室纱帘
		 */
		//向下
		btnDayDown=(TextView)mView.findViewById(R.id.btn_day_down);
		btnDayDown.setOnClickListener(this);
		//向上
		btnDayUp=(TextView)mView.findViewById(R.id.btn_day_up);
		btnDayUp.setOnClickListener(this);
		
		/**
		 * 卧室遮光帘
		 */
		//向下
		btnNightDown=(TextView)mView.findViewById(R.id.btn_night_down);
		btnNightDown.setOnClickListener(this);
		//向上
		btnNightUp=(TextView)mView.findViewById(R.id.btn_night_up);
		btnNightUp.setOnClickListener(this);
		
		/**
		 * 浴室窗帘
		 */
		//向下
		btnBathroomDown=(TextView)mView.findViewById(R.id.btn_bathroom_down);
		btnBathroomDown.setOnClickListener(this);
		//向上
		btnBathroomUp=(TextView)mView.findViewById(R.id.btn_bathroom_up);
		btnBathroomUp.setOnClickListener(this);
		
		//当前频道
		txtMusicState=(TextView)mView.findViewById(R.id.txt_current_state);
		
		/**
		 * 频道
		 */
		//频道一
		btnChanelOne=(TextView)mView.findViewById(R.id.btn_chanel_one);
		btnChanelOne.setOnClickListener(this);
		
		//频道二
		btnChanelTwo=(TextView)mView.findViewById(R.id.btn_chanel_two);
		btnChanelTwo.setOnClickListener(this);
		
		//频道三
		btnChanelThree=(TextView)mView.findViewById(R.id.btn_chanel_three);
		btnChanelThree.setOnClickListener(this);
		
		//频道四
		btnChanelFour=(TextView)mView.findViewById(R.id.btn_chanel_four);
		btnChanelFour.setOnClickListener(this);
		
		//下一频道
		btnMusicNext=(TextView)mView.findViewById(R.id.btn_music_next);
		btnMusicNext.setOnClickListener(this);
		
		//上一频道
		btnMusicPre=(TextView)mView.findViewById(R.id.btn_music_pre);
		btnMusicPre.setOnClickListener(this);
		
		//调试条
		mMusicBar=(ProgressBar)mView.findViewById(R.id.music_seek_bar);
		//mMusicBar.setOnSeekBarChangeListener(seekListener);
	}

//	@Override
	public void onClick(View v) {
		if (v.equals(btnDrapesDown)) {
			//卧室布帘，向下
		}else if (v.equals(btnDrapesUp)) {
			//卧室布帘，向上
		}else if (v.equals(btnDayDown)) {
			//卧室纱帘，向下
		}else if (v.equals(btnDayUp)) {
			//卧室纱帘，向上
		}else if (v.equals(btnNightDown)) {
			//卧室遮光帘，向下
		}else if (v.equals(btnNightUp)) {
			//卧室遮光帘，向上
		}else if (v.equals(btnBathroomDown)) {
			//浴室窗帘，向下
		}else if (v.equals(btnBathroomUp)) {
			//浴室窗帘，向上
		}else if (v.equals(btnChanelOne)) {
			//频道一
		}else if (v.equals(btnChanelTwo)) {
			//频道二
		}else if (v.equals(btnChanelThree)) {
			//频道三
		}else if (v.equals(btnChanelFour)) {
			//频道四
		}else if (v.equals(btnMusicNext)) {
			//下一频道
		}else if (v.equals(btnMusicPre)) {
			//上一频道
		}
		ButtonEx be = (ButtonEx) v;
		 if (be.getCmdName().equals("single")) {
				if (be.getEnabled()) {
//					be.setEnabled(false);
					mSingleControl.setHardwareId(be.getHardwareId());
					mSingleControl.setTargetId(be.getTargetCode());
					mSingleControl.setTargetState(SingleControl.DISABLED);
					mDatagramHandle.send(mSingleControl.getCmd());
//					be.setBackgroundResource(com.coopwell.smartclient.R.drawable.control_btn_bg_n);
				} else {
					be.setEnabled( true);
					mSingleControl.setHardwareId(be.getHardwareId());
					mSingleControl.setTargetId(be.getTargetCode());
					mSingleControl.setTargetState(SingleControl.ENABLED);
					mDatagramHandle.send(mSingleControl.getCmd());
//					be.setBackgroundResource(com.coopwell.smartclient.R.drawable.control_btn_bg_s);
				}
		 }  else if (be.getCmdName().equals("scene")) {
				mSceneControl.setSceneCode(be.getTargetCode());
				mDatagramHandle.send(mSceneControl.getCmd());
			}
	}

}
