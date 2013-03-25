package com.coopwell.smartclient.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blueocean.datagramHandle.DatagramHandle;
import com.blueocean.kongtiao.KongTiaoControl;
import com.blueocean.kongtiao.KongTiaoQuery;
import com.blueocean.netinterface.OnUDPReceiveFinishListener;
import com.blueocean.sharedpreference.SharedPreferenceConfig;
import com.blueocean.viewex.AirViewEx;
import com.coopwell.smartclient.R;
import com.coopwell.smartclient.RemoteServe;
import com.coopwell.smartclient.RemoteServe.ICallback;
import com.coopwell.smartclient.mode.ACInfo;
import com.coopwell.smartclient.mode.AcAdapterInfo;
import com.coopwell.smartclient.mode.DataInfo;
import com.coopwell.smartclient.view.SpinnerView.ISelectListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AirView extends View implements View.OnClickListener, ISelectListener, OnUDPReceiveFinishListener {
	private static final String TAG = "AirView";
	private TextView btnAddT;
	private TextView btnMinusT;
	private TextView btnMode;
	private TextView btnNext;
	private TextView btnOnOff;
	private TextView btnPre;
	private ImageView btnTime;
	private TextView btnWindSpeed;
	private RemoteServe.ICallback iCallback;
	private LayoutInflater inflater;
	private boolean isShowTime;
	private ArrayList<ACInfo> mACList;
	private TextView mAcListText;
	private Context mContext;
	private ArrayList<AcAdapterInfo> mList;
	private View mView;
	private int nIndex;
	private TimePickerDialog.OnTimeSetListener onTimeSetListener;
	private SpinnerView sView;
//	SpinnerView.ISelectListener selectListener;
	private TextView txtAuto;
	private EditText txtCurrentT;
	private TextView txtIconOne;
	private TextView txtIconThree;
	private TextView txtIconTwo;
	private TextView txtInDoorT;
	private TextView txtMode;
	private TextView txtOutDoorT;
	private TextView txtTime;
	private KongTiaoControl mKongTiaoControl;
	private boolean kongTiaoEnableFlag = false;
	private boolean modeFlag = false;
	private int fanSpeedMode = 1;
	private int temperatureValue = 25;
	private DatagramHandle mDatagramHandle;
	private SharedPreferenceConfig mSharedPreferenceConfig;
	private int currentID = 12;
	private List<AirViewEx> list;
	private UIHandler uiHandler;
	
	
	public AirView(Context context, List<AirViewEx> list) {
		super(context);
		uiHandler = new UIHandler();
		inflater = LayoutInflater.from(context);
		mView = inflater.inflate(R.layout.air_view, null);
		mContext = context;
		mKongTiaoControl = new KongTiaoControl();
		mDatagramHandle = new DatagramHandle();
		mSharedPreferenceConfig = new SharedPreferenceConfig(mContext);
		this.list = list;
		init();
		
		txtAuto.setVisibility(View.GONE);
		txtIconOne.setVisibility(View.GONE);
		txtIconTwo.setVisibility(View.GONE);
		txtIconThree.setVisibility(View.GONE);
		txtCurrentT.setText("");
		
	}

	private ArrayList<AcAdapterInfo> getAcList(ArrayList<ACInfo> pList) {

		ArrayList<AcAdapterInfo> list = new ArrayList<AcAdapterInfo>();
		for (int i = 0; i < pList.size(); i++) {
			AcAdapterInfo info = new AcAdapterInfo();
			info.id = pList.get(i).getId();
			info.name = pList.get(i).getName();
			list.add(info);
		}
		return list;
	}

	/**
	 * 获取发送数据包
	 */
	private DataInfo getData(byte type, byte code, byte adrrid, ACInfo aInfo) {
		DataInfo info = new DataInfo();
		info.type = type;
		info.code = code;
		info.id = 0x00;
		info.addrId = adrrid;
		info.state = 0x00;
		info.info = aInfo;
		return info;
	}

	/**
	 * 获取空调列表
	 */
	private ArrayList<ACInfo> getList() {
		ArrayList<ACInfo> list = new ArrayList<ACInfo>();
		ACInfo info1 = new ACInfo();
		info1.setnSeasonMode((byte) 0x00);
		info1.setnSetTemp((byte) 0x19);
		info1.setnTempDate((byte) 0x00);
		info1.setnCtrl((byte) 0x00);
		info1.setnHalt((byte) 0x00);
		info1.setnValild((byte) 0x00);
		info1.setnState((byte) 0x00);
		info1.setnWorkState((byte) 0x00);
		info1.setId((byte) 0x00);
		info1.setName("大厅");
		list.add(info1);

		ACInfo info2 = new ACInfo();
		info2.setnSeasonMode((byte) 0x00);
		info2.setnSetTemp((byte) 0x19);
		info2.setnTempDate((byte) 0x00);
		info2.setnCtrl((byte) 0x00);
		info2.setnHalt((byte) 0x00);
		info2.setnValild((byte) 0x00);
		info2.setnState((byte) 0x00);
		info2.setnWorkState((byte) 0x00);
		info2.setId((byte) 0x00);
		list.add(info2);
		return list;
	}

	private void init() {
		mAcListText = (TextView) mView.findViewById(R.id.ac_list);
		mAcListText.setOnClickListener(this);

		btnNext = (TextView) mView.findViewById(R.id.btn_air_next);
		btnNext.setOnClickListener(this);

		btnPre = (TextView) mView.findViewById(R.id.btn_air_pre);
		btnPre.setOnClickListener(this);

		txtAuto = (TextView) mView.findViewById(R.id.txt_auto);

		txtIconOne = (TextView) mView.findViewById(R.id.air_icon_one);
		txtIconTwo = (TextView) mView.findViewById(R.id.air_icon_two);
		txtIconThree = (TextView) mView.findViewById(R.id.air_icon_three);

		txtInDoorT = (TextView) mView.findViewById(R.id.txt_in_door_t);
		txtOutDoorT = (TextView) mView.findViewById(R.id.txt_out_door_t);

		txtCurrentT = (EditText) mView.findViewById(R.id.txt_current_t);

		txtMode = (TextView) mView.findViewById(R.id.txt_mode);

		txtTime = (TextView) mView.findViewById(R.id.txt_time);

		btnTime = (ImageView) mView.findViewById(R.id.btn_time);
		btnTime.setOnClickListener(this);

		btnOnOff = (TextView) mView.findViewById(R.id.btn_on_off);
		btnOnOff.setOnClickListener(this);

		btnWindSpeed = (TextView) mView.findViewById(R.id.btn_wind_speed);
		btnWindSpeed.setOnClickListener(this);

		btnMode = (TextView) mView.findViewById(R.id.btn_mode);
		btnMode.setOnClickListener(this);

		btnAddT = (TextView) mView.findViewById(R.id.btn_add_t);
		btnAddT.setOnClickListener(this);

		btnMinusT = (TextView) mView.findViewById(R.id.btn_minus_t);
		btnMinusT.setOnClickListener(this);

		sView = new SpinnerView(mContext);
		mACList = new ArrayList<ACInfo>();
		for (AirViewEx ae: list) {
			ACInfo acInfo = new ACInfo();
			acInfo.setId((byte) ae.getHardwareId());
			acInfo.setName(ae.getName());
			mACList.add(acInfo);
		}
		mAcListText.setText(list.get(0).getName());
		currentID = list.get(0).getHardwareId();
		Log.e(TAG, " currentID = " + currentID + " list.get(0).getHardwareId() = " + list.get(0).getHardwareId()
				+ " list.get(0).getName() = " + list.get(0).getName());
		
//		ACInfo acInfo = new ACInfo();
//		acInfo.setId((byte) );
//		acInfo.setName("进门空调");
//		mACList.add(acInfo);
//		
//		acInfo = new ACInfo();
//		acInfo.setId((byte)16);
//		acInfo.setName("哈谈室空调");
//		mACList.add(acInfo);
//		
//		acInfo = new ACInfo();
//		acInfo.setId((byte)17);
//		acInfo.setName("卧室空调");
//		mACList.add(acInfo);
//		mAcListText.setText("进门空调");
		nIndex = 0;
		
		mDatagramHandle.setOnUDPReceivFinishListener(this);
		KongTiaoQuery.setHardwareId(currentID);
		mDatagramHandle.receiver("kongtiao", KongTiaoQuery.getCmd());
	}

	private void selectAc(int paramInt) {
		mList = getAcList(mACList);

	}

	private void send(String msg) {

	}

	Dialog dialog = null;

	private void showDialog() {
		final Calendar calendar = Calendar.getInstance();
		if (!isShowTime)
			;
		dialog = new TimePickerDialog(mContext, onTimeSetListener,
				calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE),
				DateFormat.is24HourFormat(mContext));
		isShowTime = true;
	}

	public void hidePopWindow() {
		if (sView != null) {
			if (!sView.isShow) {
				sView.hideView();
			}
		}
	}

	public void onClick(View paramView) {
		if (paramView.equals(mAcListText)) {
			if (!sView.init) {
				mList = getAcList(mACList);
//				Log.e("amou", " mList = " + mList);
				sView.setList(mList);
				sView.initView(mAcListText.getWidth(), 160);
				sView.setmSelectListener(this);
			}
			if (!sView.isShow) {
				sView.showView(mAcListText);
			}
		} else if (paramView.equals(btnPre)) {
			selectAc(0);
		} else if (paramView.equals(btnNext)) {
			selectAc(1);
		} else if (paramView.equals(btnTime)) {
			showDialog();
		} else if (paramView.equals(btnOnOff)) {
			if (!kongTiaoEnableFlag) {
				mKongTiaoControl.setHardwareModuleId(currentID);
				mKongTiaoControl.setSpeed(KongTiaoControl.SPEED_NONE); // 1 low, 2 mid, 3 high 手动模式才有效
				mKongTiaoControl.setCtrlMode(KongTiaoControl.CTRL_AUTO_MODE);
				mKongTiaoControl.setSeasonMode(KongTiaoControl.SEASON_MODE_HEATING);
				mKongTiaoControl.setTemperature(temperatureValue);
				mKongTiaoControl.setHalt(KongTiaoControl.HALT_POWER_ON);
//				mKongTiaoControl.control();
				mDatagramHandle.send(mKongTiaoControl.getCmd());
				kongTiaoEnableFlag = true;
				txtAuto.setVisibility(View.VISIBLE);
				txtIconOne.setVisibility(View.GONE);
				txtIconTwo.setVisibility(View.GONE);
				txtIconThree.setVisibility(View.GONE);
				txtInDoorT.setText("  27 " + mContext.getString(R.string.air_weather_units));
				txtMode.setText("  暖风");
				txtCurrentT.setText(String.valueOf(temperatureValue));
				txtCurrentT.setVisibility(View.VISIBLE);
//				btnOnOff.setBackgroundResource(com.coopwell.smartclient.R.drawable.air_on_off_d);
				btnOnOff.setBackgroundResource(com.coopwell.smartclient.R.drawable.control_btn_bg_s);
				Log.e(TAG, " ENABLE CURRENTID = " + currentID);
			} else{
				mKongTiaoControl.setHardwareModuleId(currentID);
				mKongTiaoControl.setHalt(KongTiaoControl.HALT_POWER_OFF);
//				mKongTiaoControl.control();
				mDatagramHandle.send(mKongTiaoControl.getCmd());
				kongTiaoEnableFlag = false;
				txtInDoorT.setText("");
				txtMode.setText("");
				txtCurrentT.setVisibility(View.GONE);
//				btnOnOff.setBackgroundResource(com.coopwell.smartclient.R.drawable.air_on_off_n);
				btnOnOff.setBackgroundResource(com.coopwell.smartclient.R.drawable.control_btn_bg_n);
				Log.e(TAG, " DISABLE CURRENTID = " + currentID);
			}
		} else if (paramView.equals(btnWindSpeed)) {
//			send("btnWindSpeed");
			if (fanSpeedMode < 4) {
				mKongTiaoControl.setCtrlMode(KongTiaoControl.CTRL_HAND_MODE); //手动
				mKongTiaoControl.setSpeed(fanSpeedMode);
//				mKongTiaoControl.control();
				mDatagramHandle.send(mKongTiaoControl.getCmd());
				txtAuto.setVisibility(View.GONE);
				if (fanSpeedMode == 1) {
					txtIconOne.setVisibility(View.VISIBLE);
				} else if (fanSpeedMode == 2) {
					txtIconOne.setVisibility(View.VISIBLE);
					txtIconTwo.setVisibility(View.VISIBLE);
				} else if (fanSpeedMode == 3) {
					txtIconOne.setVisibility(View.VISIBLE);
					txtIconTwo.setVisibility(View.VISIBLE);
					txtIconThree.setVisibility(View.VISIBLE);
				}
				fanSpeedMode ++;
			} else {
				fanSpeedMode = 1;
				mKongTiaoControl.setSpeed(KongTiaoControl.SPEED_NONE);
				mKongTiaoControl.setCtrlMode(KongTiaoControl.CTRL_AUTO_MODE); //自动
//				mKongTiaoControl.control();
				mDatagramHandle.send(mKongTiaoControl.getCmd());
				txtAuto.setVisibility(View.VISIBLE);
				txtIconOne.setVisibility(View.GONE);
				txtIconTwo.setVisibility(View.GONE);
				txtIconThree.setVisibility(View.GONE);
			}
		} else if (paramView.equals(btnMode)) {
//			send("btnMode");
			if (!modeFlag) { //冷风
				mKongTiaoControl.setSeasonMode(KongTiaoControl.SEASON_MODE_REFRIGERATION);
//				mKongTiaoControl.control();
				mDatagramHandle.send(mKongTiaoControl.getCmd());
				txtMode.setText("  冷风");
				modeFlag = true;
			} else {
				mKongTiaoControl.setSeasonMode(KongTiaoControl.SEASON_MODE_HEATING);
//				mKongTiaoControl.control();
				mDatagramHandle.send(mKongTiaoControl.getCmd());
				modeFlag = false;
				txtMode.setText("  暖风");
			}
		} else if (paramView.equals(btnAddT)) {
//			send("btnAddT");
			if (temperatureValue > 29) return;
			mKongTiaoControl.setTemperature(++temperatureValue);
//			mKongTiaoControl.control();
			mDatagramHandle.send(mKongTiaoControl.getCmd());
			txtCurrentT.setText(String.valueOf(temperatureValue));
		} else if (paramView.equals(btnMinusT)) {
			if (temperatureValue <= 16) return;
			temperatureValue -= 1;
			mKongTiaoControl.setTemperature(temperatureValue);
//			mKongTiaoControl.control();
			mDatagramHandle.send(mKongTiaoControl.getCmd());
			txtCurrentT.setText(String.valueOf(temperatureValue));
		}
	}

	public void showView(LinearLayout layout) {
		layout.removeAllViews();
		LayoutParams param = new LayoutParams(-1, -1);
		mView.setLayoutParams(param);
		layout.addView(mView);
		mDatagramHandle.setIP(mSharedPreferenceConfig.getString("net_ip", "192.168.1.127"));
		mDatagramHandle.setPort(Integer.parseInt(mSharedPreferenceConfig.getString("net_port", "3342")));
	}

//	@Override
	public void onSelect(int id, String name) {
		// TODO Auto-generated method stub
		currentID = id;
		mAcListText.setText(name);
		sView.hideView();
		KongTiaoQuery.setHardwareId(currentID);
		mDatagramHandle.receiver("kongtiao", KongTiaoQuery.getCmd());
		Log.e("amou", " id = " + id + " currentID = " + currentID);
	}

	class UIHandler extends Handler {
		public static final int MSG_UI_STATE_UPDATE = 0X01;
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_UI_STATE_UPDATE:
				byte[] mData = (byte[]) msg.obj;
				if (mData[22] == currentID) {
					if (mData[29] == 0) {
						kongTiaoEnableFlag = true;
						btnOnOff.setBackgroundResource(com.coopwell.smartclient.R.drawable.control_btn_bg_s);
					} else {
						kongTiaoEnableFlag = false;
						btnOnOff.setBackgroundResource(com.coopwell.smartclient.R.drawable.control_btn_bg_n);
					}
				}
				break;
			}
		}
	}
	
//	@Override
	public void onUDPReceiveFinish(String cmdType, byte[] msg) {
		// TODO Auto-generated method stub
		if (cmdType.equals("kongtiao")) {
			Message m = uiHandler.obtainMessage(UIHandler.MSG_UI_STATE_UPDATE, msg);
			uiHandler.sendMessage(m);
		}
	}
}