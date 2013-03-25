package com.coopwell.smartclient.view;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

import com.blueocean.brightnesscontrol.BrightnessControl;
import com.blueocean.controlcmd.QueryCmd;
import com.blueocean.controlcmd.SceneControl;
import com.blueocean.controlcmd.SingleControl;
import com.blueocean.controlcmd.SingleQueryCmd;
import com.blueocean.datagramHandle.DatagramHandle;
import com.blueocean.netinterface.OnUDPReceiveFinishListener;
import com.blueocean.sharedpreference.SharedPreferenceConfig;
import com.blueocean.viewex.ButtonEx;
import com.coopwell.smartclient.LocationType;
import com.coopwell.smartclient.MainActivity;
import com.coopwell.smartclient.R;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ControlView extends View implements OnClickListener, OnUDPReceiveFinishListener, OnSeekBarChangeListener{

	private static final String TAG = "ControlView";
	private LayoutInflater inflater;
	private View mView;
	private TextView btnOneOne;
	private TextView btnOneTwo;
	private TextView btnOneThree;
	private TextView btnOneFour;
	private TextView btnTwoOne;
	private TextView btnTwoTwo;
	private TextView btnTwoThree;
	private TextView btnTwoFour;
	private TextView btnThreeOne;
	private TextView btnThreeTwo;
	private TextView btnThreeThree;
	private TextView btnThreeFour;
	private TextView btnFourOne;
	private TextView btnFourTwo;
	private TextView btnFourThree;
	private TextView btnFourFour;
	private TextView btnFiveOne;
	private TextView btnFiveTwo;
	private TextView btnFiveThree;
	private TextView btnFiveFour;
	private TextView btnSixOne;
	private TextView btnSixTwo;
	private TextView btnSixThree;
	private TextView btnSixFour;
	private AdjustView mAdjustView;
	private Context mContext;
	private int mWidth;
	private int mHeight;
	private LinearLayout layout;
	private SceneControl mSceneControl;
	private SingleControl mSingleControl;
	private DatagramHandle mDatagramHandle;
	private SharedPreferenceConfig mSharedPreferenceConfig;
	private boolean btnOneOneFlag = false;
	private boolean btnOneTwoFlag = false;
	private boolean btnOneThreeFlag = false;
	private boolean btnOneFourFlag = false;
	private boolean btnTwoOneFlag = false;
	private boolean btnTwoTwoFlag = false;
	private boolean btnTwoThreeFlag = false;
	private boolean btnTwoFourFlag = false;
	private boolean btnThreeOneFlag = false;
	private boolean btnThreeTwoFlag = false;
	private boolean btnThreeThreeFlag = false;
	private boolean btnThreeFourFlag = false;
	private boolean btnFourOneFlag = false;
	private boolean btnFourTwoFlag = false;
	private boolean btnFourThreeFlag = false;
	private boolean btnFourFourFlag = false;
	private boolean btnFiveOneFlag = false;
	private boolean btnFiveTwoFlag = false;
	private boolean btnFiveThreeFlag = false;
	private boolean btnFiveFourFlag = false;
	private List<ButtonEx> btnList;
	private UIHandler uiHandler;
	private PopupWindow window;
	private BrightnessControl mBrightnessControl;
	private List<QueryCmd> qList;
	private int qListIndex = 0;
	public static ButtonEx brightnessCurrentBtn = null;
	
	private LinearLayout linearLayout01;
	
	public ControlView(Context context, List<ButtonEx> btnList, List<QueryCmd> qList){
		super(context);
		this.mContext=context;
		this.qList = qList;
		inflater=LayoutInflater.from(context);
		mView=inflater.inflate(R.layout.control_view, null);
//		init();
//		addListener();
		mSceneControl = new SceneControl();
		mSingleControl = new SingleControl();
		mDatagramHandle = new DatagramHandle();
		mSharedPreferenceConfig = new SharedPreferenceConfig(mContext);
		this.btnList = btnList;
		addButtonByCode(btnList);
		uiHandler = new UIHandler();
		mBrightnessControl = new BrightnessControl(mContext);
		
//		for (QueryCmd qc: qList) {
//			if (qc.cmdName.equals("single")) {
//				Log.e(TAG, " qc.cmdName = " + qc.cmdName + " qc.hardwareid = " + qc.hardwareID);
//				SingleQueryCmd.setHardwareID(qc.hardwareID);
//				mDatagramHandle.receiver(qc.cmdName, SingleQueryCmd.getCmd());
//			} else if (qc.cmdName.equals("light")) {
//				SingleQueryCmd.setHardwareID(qc.hardwareID);
//				mDatagramHandle.receiver(qc.cmdName, SingleQueryCmd.getCmd());
//			}
//		}
		SingleQueryCmd.setHardwareID(qList.get(qListIndex).hardwareID);
		mDatagramHandle.receiver(qList.get(qListIndex).cmdName, SingleQueryCmd.getCmd());
		Log.e(TAG, " qc.cmdName = " + qList.get(qListIndex).cmdName + " qc.hardwraeid = " + qList.get(qListIndex).hardwareID);
		qListIndex ++;
	}
	
	private void addButtonByCode(List<ButtonEx> btnList) {
		linearLayout01 = (LinearLayout) mView.findViewById(R.id.linearlayout01);
		int index = 0;
		for (int i = 0; i < btnList.size() / 4; i ++) {
			LinearLayout linearLayout02 = new LinearLayout(mContext);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			linearLayout01.addView(linearLayout02, lp);
			for (int j = 0; j < 4; j ++) {
				lp = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
				linearLayout02.addView(btnList.get(index), lp);
				index ++;
			}
		}
		LinearLayout linearLayout02 = new LinearLayout(mContext);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		linearLayout01.addView(linearLayout02, lp);
		for (int j = index; j < btnList.size(); j ++) {
			lp = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
			linearLayout02.addView(btnList.get(index), lp);
			index ++;
		}
		
		for (ButtonEx be: btnList) {
			be.setOnClickListener(this);
		}
		
		mDatagramHandle.setOnUDPReceivFinishListener(this);
	}
	
	
	
	public void showView(LinearLayout layout){
		this.layout=layout;
		layout.removeAllViews();
		mView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT));
		layout.addView(mView);
		mDatagramHandle.setIP(mSharedPreferenceConfig.getString("net_ip", "192.168.1.127"));
		mDatagramHandle.setPort(Integer.parseInt(mSharedPreferenceConfig.getString("net_port", "3342")));
	}
	
	/**
	 * 初始化数据
	 */
	private void init(){
		/**
		 * 第一行
		 */
		//廊灯
		btnOneOne=(TextView)mView.findViewById(R.id.btn_one_one);
		//台灯
		btnOneTwo=(TextView)mView.findViewById(R.id.btn_one_two);
		//落地灯
		btnOneThree=(TextView)mView.findViewById(R.id.btn_one_three);
		//厅灯
		btnOneFour=(TextView)mView.findViewById(R.id.btn_one_four);
		
		/**
		 * 第二行
		 */
		//左阅读灯
		btnTwoOne=(TextView)mView.findViewById(R.id.btn_two_one);
		//右阅读灯
		btnTwoTwo=(TextView)mView.findViewById(R.id.btn_two_two);
		//总开
		btnTwoThree=(TextView)mView.findViewById(R.id.btn_two_three);
		//总关
		btnTwoFour=(TextView)mView.findViewById(R.id.btn_two_four);
		
		/**
		 * 第三行
		 */
		//左床灯
		btnThreeOne=(TextView)mView.findViewById(R.id.btn_three_one);
		//右床灯
		btnThreeTwo=(TextView)mView.findViewById(R.id.btn_three_two);
		//总开
		btnThreeThree=(TextView)mView.findViewById(R.id.btn_three_three);
		//总关
		btnThreeFour=(TextView)mView.findViewById(R.id.btn_three_four);
		
		/**
		 * 第四行
		 */
		//场景1
		btnFourOne=(TextView)mView.findViewById(R.id.btn_four_one);
		//场景2
		btnFourTwo=(TextView)mView.findViewById(R.id.btn_four_two);
		//场景3
		btnFourThree=(TextView)mView.findViewById(R.id.btn_four_three);
		//场景4
		btnFourFour=(TextView)mView.findViewById(R.id.btn_four_four);
		
		/**
		 * 第五行
		 */
		//场景5
		btnFiveOne=(TextView)mView.findViewById(R.id.btn_five_one);
		//场景6
		btnFiveTwo=(TextView)mView.findViewById(R.id.btn_five_two);
		//场景7
		btnFiveThree=(TextView)mView.findViewById(R.id.btn_five_three);
		//场景8
		btnFiveFour=(TextView)mView.findViewById(R.id.btn_five_four);
		
		/**
		 * 第六行
		 */
		btnSixOne=(TextView)mView.findViewById(R.id.btn_six_one);
		//总开
		btnSixTwo=(TextView)mView.findViewById(R.id.btn_six_two);
		//总关
		btnSixThree=(TextView)mView.findViewById(R.id.btn_six_three);
		btnSixFour=(TextView)mView.findViewById(R.id.btn_six_four);
		
		//亮度调节界面
		mAdjustView=new AdjustView(mContext);
		mWidth=MainActivity.mWidth;
		mHeight=MainActivity.mHeight;
	}
	
	/**
	 * 添加监听事件
	 */
	private void addListener(){
		btnOneOne.setOnClickListener(this);
		btnOneTwo.setOnClickListener(this);
		btnOneThree.setOnClickListener(this);
		btnOneFour.setOnClickListener(this);
		
		btnTwoOne.setOnClickListener(this);
		btnTwoTwo.setOnClickListener(this);
		btnTwoThree.setOnClickListener(this);
		btnTwoFour.setOnClickListener(this);
		
		btnThreeOne.setOnClickListener(this);
		btnThreeTwo.setOnClickListener(this);
		btnThreeThree.setOnClickListener(this);
		btnThreeFour.setOnClickListener(this);
		
		btnFourOne.setOnClickListener(this);
		btnFourTwo.setOnClickListener(this);
		btnFourThree.setOnClickListener(this);
		btnFourFour.setOnClickListener(this);
		
		btnFiveOne.setOnClickListener(this);
		btnFiveTwo.setOnClickListener(this);
		btnFiveThree.setOnClickListener(this);
		btnFiveFour.setOnClickListener(this);
		
		btnSixOne.setOnClickListener(this);
		btnSixTwo.setOnClickListener(this);
		btnSixThree.setOnClickListener(this);
		btnSixFour.setOnClickListener(this);
	}
	private void send(){
//		String message="hello udp";
		byte[] message = new byte[] {0x1e, 0x1e,  0x48, 0x4d, 0x49,  0x53, 0x00, 0x05, 0x00, 0x00,
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x81, 0x01, 0x01, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 0x00, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00};
		String messageStr = "";
		for (int i = 0; i < message.length; i ++) {
			messageStr += Integer.toHexString((message[i] & 0xf0) >> 4) + Integer.toHexString(message[i] & 0x0f);
		}
		Log.e("amou", " messagestr = " + messageStr + " messagestr len = " + messageStr.length() + " message len = " + message.length);
		int server_port = 3342;
		DatagramSocket s = null;
		InetAddress local = null;
		try {
			s = new DatagramSocket();
			// 换成服务器端IP
			local = InetAddress.getByName("192.168.0.127");
			DatagramPacket p = new DatagramPacket(message, message.length, local, server_port);
			s.send(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 隐藏pop
	 */
	public void hidePopWindow(){
		if (mAdjustView!=null) {
			if(mAdjustView.isShow){
				mAdjustView.hidePopWindow();
			}
		}
	}
	
	/**
	 * 调节回调
	 */
	AdjustView.Callback callback=new AdjustView.Callback() {
		
//		@Override
		public void onChange(int progress, int id) {
			
		}
	};

//	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		for (ButtonEx be: btnList) {
			if (v.equals(be)) {
				if (be.getCmdName().equals("light")) {
					mBrightnessControl.setLightHardwareID(be.getHardwareId());
					mBrightnessControl.setLightTargetID(be.getTargetCode());
					mBrightnessControl.setBrightness(be.getBrightness());
					mBrightnessControl.show();
					brightnessCurrentBtn = be;
				} else if (be.getCmdName().equals("single")) {
					if (be.getEnabled()) {
						be.setEnabled(false);
						mSingleControl.setHardwareId(be.getHardwareId());
						mSingleControl.setTargetId(be.getTargetCode());
						mSingleControl.setTargetState(SingleControl.DISABLED);
						mDatagramHandle.send(mSingleControl.getCmd());
						be.setBackgroundResource(com.coopwell.smartclient.R.drawable.control_btn_bg_n);
					} else {
						be.setEnabled( true);
						mSingleControl.setHardwareId(be.getHardwareId());
						mSingleControl.setTargetId(be.getTargetCode());
						mSingleControl.setTargetState(SingleControl.ENABLED);
						mDatagramHandle.send(mSingleControl.getCmd());
						be.setBackgroundResource(com.coopwell.smartclient.R.drawable.control_btn_bg_s);
					}
				} else if (be.getCmdName().equals("scene")) {
					mSceneControl.setSceneCode(be.getTargetCode());
					mDatagramHandle.send(mSceneControl.getCmd());
				}
				Log.e(TAG, " hardid = " + be.getHardwareId() + " targetid = " + be.getTargetCode());
			}
		}
	}
	
	private String cmdName = "";
	
	class UIHandler extends Handler {
		public static final int MSG_UDP_DATA_RECEIVED = 0X01;
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_UDP_DATA_RECEIVED:
				byte[] data = (byte[]) msg.obj;
				if (cmdName.equals("single")) {
					int hardwareID = data[22];
					for (int i = 1; i <= data[26]; i ++) {
						int index = i + 26;
//						Log.e(TAG, " data[" + i + "] = " + data[i + 26] + " hardwareID = " + hardwareID);
						for (ButtonEx be: btnList) {
//							Log.e(TAG, " be.getHardwareID = " + be.getHardwareId() + " be.getCmdName = " + be.getCmdName());
							if (be.getCmdName().equals("single") && (be.getHardwareId() == hardwareID)) {
								if (be.getTargetCode() == i) {
									if (data[index] == 1) {
										be.setBackgroundResource(com.coopwell.smartclient.R.drawable.control_btn_bg_s);
//										Log.e(TAG, " i = " + i + " index = " + index + " button name = " + be.getText().toString() + " targetcode = " + be.getTargetCode());
										be.setEnabled(true);
									} else {
										be.setBackgroundResource(com.coopwell.smartclient.R.drawable.control_btn_bg_n);
										be.setEnabled(false);
									}
//									Log.e(TAG, " be cmdname = " +be.getCmdName() + " targetcode= " + be.getTargetCode() + " i = " + i + " data = " + data[index]);
								}
							}
						}
					}
				} else if (cmdName.equals("light")) {
					int hardwareID = data[22];
					for (int i = 1; i <= data[26]; i ++) {
						int index = i + 26;
						for (ButtonEx be: btnList) {
							Log.e(TAG, "  hardwareID = " + hardwareID + " be.gethardwareid = " + be.getHardwareId() + " be.getCmdName = " + be.getCmdName());
							if (be.getCmdName().equals("light") && (be.getHardwareId() == hardwareID)) {
								Log.e(TAG, " be.getTargetCode = " + be.getTargetCode() + " i = " + i);
								if (be.getTargetCode() == i) {
									be.setBrightness(data[index]);
									Log.e(TAG, " data = " + data[index]);
								}
							}
						}
					}
				}
				if (qListIndex < qList.size()) {
					Log.e(TAG, " q.cmdName = " + qList.get(qListIndex).cmdName + " qc.hardwareid = " + qList.get(qListIndex).hardwareID);
					SingleQueryCmd.setHardwareID(qList.get(qListIndex).hardwareID);
					mDatagramHandle.receiver(qList.get(qListIndex).cmdName, SingleQueryCmd.getCmd());
					qListIndex ++;
					Log.e(TAG, " qListIndex = " + qListIndex + " qList.size = " + qList.size());
				}
				break;
			}
		}
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (window != null && window.isShowing()) {
				window.dismiss();
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

//	@Override
	public void onUDPReceiveFinish(String cmdType, byte[] msg) {
		// TODO Auto-generated method stub
		Log.e(TAG, " cmdType = " + cmdType);
		String temp = "";
		for (byte b: msg) {
			temp += Integer.toHexString((b & 0xff));
		}
		Log.e(TAG, " cmdName = " + cmdType + " msg = " + temp);
		cmdName = cmdType;
		Message m = uiHandler.obtainMessage(UIHandler.MSG_UDP_DATA_RECEIVED, msg);
		uiHandler.sendMessage(m);
	}

//	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
}
