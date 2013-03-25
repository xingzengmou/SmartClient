package com.coopwell.smartclient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.coopwell.smartclient.mode.ACInfo;
import com.coopwell.smartclient.mode.DataInfo;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@SuppressLint({ "HandlerLeak" })
public class RemoteServe {
	private static final int RECEIVE = 2;
	private static final int SEND = 1;
	private static final String TAG = "RemoteServe";
	private static final int UPATE = 3;
	private static final int mPort = 3341;
	private static RemoteServe sInstance;
	private String Ip;
	private DatagramPacket dpIn;
	private DatagramPacket dpOut;
	private CPhandler handler;
	private ICallback iCallback;
	private boolean init;
	private InetAddress local = null;
	private Context mContext;
	private OrderTool mOrderTool;
	private CPReceiveHandler mReceiveHandler;
	private HandlerThread mReceiveThread;
	private HandlerThread mThread;
	private DatagramSocket socket = null;

	private RemoteServe(){
		mThread = new HandlerThread("send_thread");
		mThread.start();
		handler = new CPhandler(mThread.getLooper());

		mReceiveThread = new HandlerThread("receive_thread");
		mReceiveThread.start();
		mReceiveHandler = new CPReceiveHandler(mReceiveThread.getLooper());
	}

	private void doACData(byte[] paramArrayOfByte) {
	}

	private void doLightData(byte[] mByte) {
		if ((mByte == null) || (mByte.length < 16)){
			return;
		}
		if (mByte[16]==0x12) {
			handler.obtainMessage(UPATE, mByte).sendToTarget();
		}
	}

	private byte[] getBytes(DataInfo info) {
		byte[] mByte= new byte[30];
		switch (info.type) {
		case CmdType.BYTE://场景or灯光
			switch (info.code) {
			case CmdType.SCENE://场景控制命令
				mByte=mOrderTool.getSceneByte(mByte, info.id);
				break;
			case CmdType.LIGHT_SEND://灯光控制命令
				mByte=mOrderTool.getByteSendLighting(mByte,info.addrId, info.id, info.state);
				break;
			case CmdType.LIGHT_GET://获取灯光状态
				mByte=mOrderTool.getByteGetLighting(mByte, info.addrId, info.id);
				break;
			}
			break;
		case CmdType.AC_TYPE://空调
			switch (info.code) {
			case CmdType.AC_CONTROL://空调控制命令
				mByte=mOrderTool.getByteAC(mByte, info.addrId, info.info);
				break;
			case CmdType.AC_QUERY://空调查询
				mByte=mOrderTool.getByteQueryAC(mByte, info.addrId);
				break;
			}
			break;
		case CmdType.SERVICE://服务
			mByte=mOrderTool.getService(mByte);
			break;
		}
		return mByte;
	}

	public static RemoteServe getInstance() {
		if (sInstance == null)
			sInstance = new RemoteServe();
		return sInstance;
	}

	private void init(){
		Ip = mContext.getSharedPreferences("coopwell_config", 0).getString(
				"ip", "");
		if (Ip.equals("")) {
			return;
		}
		try {
			socket = new DatagramSocket();
			local = InetAddress.getByName(this.Ip);
			mOrderTool = new OrderTool();
			init = true;
		} catch (Exception localException) {
			localException.printStackTrace();
			Log.e("RemoteServe", "init error!");
		}
  }

	/**
	 * 接收回传信息
	 */
	private void receive(){
		
		byte[] mByte;
		try {
			Log.d("RemoteServe", "receive start...");
			mByte = new byte[30];
			dpIn = new DatagramPacket(mByte, mByte.length);
			socket.setSoTimeout(5000);
			socket.receive(dpIn);
			mByte = dpIn.getData();
			switch (mByte[15]) {
			case (byte) 0x81:
				if (mByte[16] == 0x00) {
					doLightData(mByte);
				}
			case 0x06:
				doACData(mByte);
			}
			Log.d("RemoteServe", "receive end...");
		} catch (Exception localException) {
			localException.printStackTrace();
			Log.e("RemoteServe", "receive msg error!");

		}
	}

	/**
	 * 发送命令
	 */
	private void sendData(DataInfo info) {
		try {
			if (!init) {
				init();
				init = true;
			}
			byte[] mByte = getBytes(info);
			dpOut = new DatagramPacket(mByte, mByte.length, local, mPort);
			socket.send(dpOut);
			if (mByte[17] == 0x01){
				mReceiveHandler.sendEmptyMessage(SEND);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void destory() {
		init=false;
		if (socket != null){
			socket.close();
		}
	}

	/**
	 * 判断是否有网络
	 */
	public boolean isNetworkAvailable() {
		boolean result=false;
		ConnectivityManager manager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
		if (manager != null){
			NetworkInfo localNetworkInfo = manager.getActiveNetworkInfo();
			if ((localNetworkInfo == null) || (!localNetworkInfo.isAvailable())){
				result=false;
			}else {
				result=true;
			}
		}
		return result;
	}

	public void send(DataInfo paramDataInfo) {
		if (!init){
			init();
		}
		handler.obtainMessage(SEND, paramDataInfo).sendToTarget();
	}

	public void setCallback(ICallback paramICallback) {
		this.iCallback = paramICallback;
	}

	public void setmContext(Context paramContext) {
		this.mContext = paramContext;
	}

	@SuppressLint({ "HandlerLeak" })
	class CPReceiveHandler extends Handler {
		
		public CPReceiveHandler(Looper arg2) {
			super(arg2);
		}

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what== RECEIVE){
				receive();
			}
		}
	}

	@SuppressLint({ "HandlerLeak" })
	class CPhandler extends Handler {
		public CPhandler(Looper arg2) {
			super(arg2);
		}

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what==SEND) {
				Log.d("RemoteServe", "send...");
				DataInfo localDataInfo = (DataInfo) msg.obj;
				sendData(localDataInfo);
			}
		}
	}

	public abstract interface ICallback {
		public abstract void onResult(byte[] paramArrayOfByte);
	}
}