package com.coopwell.smartclient;
import java.util.List;

import com.blueocean.controlcmd.QueryCmd;
import com.blueocean.sharedpreference.SharedPreferenceConfig;
import com.blueocean.viewanalyze.ViewAnalyze;
import com.blueocean.viewex.AirViewEx;
import com.blueocean.viewex.ButtonEx;
import com.coopwell.smartclient.view.AboutView;
import com.coopwell.smartclient.view.AirView;
import com.coopwell.smartclient.view.ControlView;
import com.coopwell.smartclient.view.InitView;
import com.coopwell.smartclient.view.MusicView;
import com.coopwell.smartclient.view.ServeView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;

public class MainActivity extends Activity implements OnClickListener{

	private static final int CLICK_D = R.drawable.left_btn_d;
	private static final int CLICK_N =  R.drawable.left_btn_n;
	private static final int INIT_INPUT=0;
	private static final String TAG = "MainActivity";
	private TextView btnControl;//集中控制
	private TextView btnSerive; //信息服务
	private TextView btnMusic;  //窗帘，音乐
	private TextView btnAir;  //温度风速
	private TextView btnAbout;  //关于
	private TextView btnQuit; //退出
	private LinearLayout mRightLayout;//右边view
	private LinearLayout mMainLayout;
	private AirView mAirView;
	private ControlView mControlView;
	private MusicView mMusicView;
	private ServeView mServeView;
	private AboutView mAboutView;
	private String mTag="";
	private InitView mInitView;
	public static int mWidth;
	public static int mHeight;
	private boolean init;
	private List<ButtonEx> list;
	private List<AirViewEx> airViewList;
	private SharedPreferenceConfig sp;
	private Activity activity;
	private boolean overTime = false;
	private List<QueryCmd> qList;
	private List<ButtonEx> curtainMusicButtonsList;
	
	
	private void timeLimit() {
		sp = new SharedPreferenceConfig(this);
		java.util.Calendar c=java.util.Calendar.getInstance();    
        java.text.SimpleDateFormat f=new java.text.SimpleDateFormat("yyyyMMdd");
        String today = f.format(c.getTime());
        Log.e(TAG, "time = " + today);
//        String time = sp.getString("date_time_key", "");
        String time = "";
        if (!time.equals(today)) {
        	int dateCount = sp.getInt("time_count_key", 0);
        	if (dateCount == 0) {
        		sp.writeInt("time_count_key", 1);
        		showContent();
        	} else if (dateCount >= 16){
        		overTime = true;
        		AlertDialog.Builder b = new AlertDialog.Builder(this);
        		b.setMessage("对不起，软件的试用期已满，请联系当地的商家索取正式版软件，谢谢！");
        		b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						activity.finish();
					}
				});
        		b.show();
        	} else {
        		sp.writeInt("time_count_key", dateCount + 1);
        		showContent();
        	}
        	if (!overTime) {
        		sp.writeString("date_time_key", today);
        	}
        } else {
        	showContent();
        }
        Log.e(TAG, " today = " + today + " time = " + time);
	}
	
	private void showContent() {
		setContentView(R.layout.main_view);
		ViewAnalyze mViewAnalyze = new ViewAnalyze(this);
        list = mViewAnalyze.getMainControlButtons();
        curtainMusicButtonsList = mViewAnalyze.getCurtainMusicButtons();
        
        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        Log.e(TAG, " width = " + dm.widthPixels + " height = " + dm.heightPixels);
        if (dm.widthPixels <= 800 && dm.heightPixels <= 480)  {
        	for (ButtonEx be : list) {
        		be.setTextSize(12.0f);
        	}
        	for (ButtonEx be: curtainMusicButtonsList) {
        		be.setTextSize(12.0f);
        	}
        } else {
        	for (ButtonEx be : list) {
        		be.setTextSize(16.0f);
        	}
        	for (ButtonEx be: curtainMusicButtonsList) {
        		be.setTextSize(16.0f);
        	}
        }
        
        airViewList = mViewAnalyze.getAirView();
         qList = mViewAnalyze.getQueryCmd(list);
        for (QueryCmd qc: qList) {
        	Log.e(TAG, " qc.hardwareID = " + qc.hardwareID + " qc.cmdName = " + qc.cmdName);
        }
        
        mWidth=getWindowManager().getDefaultDisplay().getWidth();
        mHeight=getWindowManager().getDefaultDisplay().getHeight();
       
        //主布局
        mMainLayout=(LinearLayout)findViewById(R.id.main_layout);
        
        //集中控制
        btnControl=(TextView)findViewById(R.id.btn_control);
        btnControl.setOnClickListener(this);
        
        //信息服务
        btnSerive=(TextView)findViewById(R.id.btn_serve);
        btnSerive.setOnClickListener(this);
        
        //窗帘、音乐
        btnMusic=(TextView)findViewById(R.id.btn_music);
        btnMusic.setOnClickListener(this);
        
        //温度风速
        btnAir=(TextView)findViewById(R.id.btn_air);
        btnAir.setOnClickListener(this);
        
        //关于
        btnAbout=(TextView)findViewById(R.id.btn_about);
        btnAbout.setOnClickListener(this);
        
        //退出
        btnQuit = (TextView) findViewById(R.id.btn_quit);
        btnQuit.setOnClickListener(this);
        
        //右边view
        mRightLayout=(LinearLayout)findViewById(R.id.right_layout);
        
        if (mControlView==null) {
        	mControlView=new ControlView(this, list, qList);
		}
        mControlView.showView(mRightLayout);
        mTag="mControlView";
        mInitView=new InitView(this);
        
        SharedPreferences localSharedPreferences =getSharedPreferences("coopwell_config", 0);
        init = localSharedPreferences.getBoolean("init_ip", true);
        
       
//        for (ButtonEx be: list) {
//        	Log.e("ViewAnalyze", " btn text = " + be.getText().toString() + " cmdtype[0] = " + be.getCmdType()[0] +
//        			" cmtype[1] = " + be.getCmdType()[1] + " hardwareid = " + be.getHardwareId() + " targetcode = " + be.getTargetCode());
//        }
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.activity = this;
        timeLimit();
    }

	@Override
	protected void onResume() {
		super.onResume();
		if (init) {
			handler.sendEmptyMessageDelayed(INIT_INPUT, 200);
		}
		
	}
	
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
//			if (msg.what==INIT_INPUT) {
//				mInitView.initPopWindow(mWidth/2, mHeight/2);
//				mInitView.showPopWindow(mMainLayout);
//			}
		}
		
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode==KeyEvent.KEYCODE_BACK||keyCode==KeyEvent.KEYCODE_HOME) {
			// 应用程序退出，不是画面切换的退出
			mInitView.hidePopWindow();
			mControlView.hidePopWindow();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(mControlView!=null){
			mControlView.hidePopWindow();
		}
		return false;
	}
	
	public void onClick(View v) {
		if (v.equals(btnControl)) {
			if (mControlView==null) {
	        	mControlView=new ControlView(this, list, qList);
			}
			if (!mTag.equals("mControlView")) {
				setClickBg(CLICK_N, CLICK_D, CLICK_D, CLICK_D, CLICK_D);
				mTag="mControlView";
				mControlView.showView(mRightLayout);
			}
	        
		}else if (v.equals(btnSerive)) {
			if (mServeView==null) {
				mServeView=new ServeView(this);
			}
			if (!mTag.equals("mServeView")) {
				setClickBg(CLICK_D, CLICK_N, CLICK_D, CLICK_D, CLICK_D);
				mTag="mServeView";
				mServeView.showView(mRightLayout);
			}
			
		}else if (v.equals(btnMusic)) {
			if (mMusicView==null) {
				mMusicView=new MusicView(this, curtainMusicButtonsList);
			}
			if (!mTag.equals("mMusicView")) {
				setClickBg(CLICK_D, CLICK_D, CLICK_N, CLICK_D, CLICK_D);
				mTag="mMusicView";
				mMusicView.showView(mRightLayout);
			}
			
		}else if (v.equals(btnAir)){
			if (mAirView==null) {
				mAirView=new AirView(this, airViewList);
			}
			if (!mTag.equals("mAirView")) {
				setClickBg(CLICK_D, CLICK_D, CLICK_D, CLICK_N, CLICK_D);
				mTag="mAirView";
				mAirView.showView(mRightLayout);
			}
			
		}else if (v.equals(btnAbout)) {
			if (mAboutView == null) {
				mAboutView = new AboutView(this);
			}
			if (!mTag.equals("btnAbout")) {
				setClickBg(CLICK_D, CLICK_D, CLICK_D, CLICK_D, CLICK_N);
				mTag="btnAbout";
				mAboutView.showView(mRightLayout);
			}
		} else if (v.equals(btnQuit)) {
			finish();
		}
	}
	
	/**
	 * 点击切换背景
	 */
	private void setClickBg(int control, int servive, int music,int air, int about) {
		btnControl.setBackgroundResource(control);
		btnSerive.setBackgroundResource(servive);
		btnMusic.setBackgroundResource(music);
		btnAir.setBackgroundResource(air);
		btnAbout.setBackgroundResource(about);
	}
   
}
