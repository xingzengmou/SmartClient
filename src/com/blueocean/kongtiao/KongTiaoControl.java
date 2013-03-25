package com.blueocean.kongtiao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

public class KongTiaoControl {
	private static final String TAG = "KongTiaoControl";
	private byte[] cmd = new byte[] {0x21, 0x21, 0x48, 0x4d, 0x49, 0x53, 0x00, 0x05, 0x00, 0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, 
			0x03, 0x06, 0x04, 0x01, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, 0x01, 0x19, 0x00, 0x01, 0x01, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00};
	private String targetIP;
	private int targetPort;
	private DatagramSocket s = null;
	private InetAddress local = null;
	public static final int SEASON_MODE_REFRIGERATION = 0;
	public static final int SEASON_MODE_HEATING= 1;
	public static final int SEASON_MODE_VENTILATION = 2;
	public static final int SPEED_LOW = 1;
	public static final int SPEED_MID = 2;
	public static final int SPEED_HIGH = 3;
	public static final int SPEED_NONE = 0;
	public static final int CTRL_HAND_MODE = 0;
	public static final int CTRL_AUTO_MODE = 1;
	public static final int HALT_POWER_ON = 0;
	public static final int HALT_POWER_OFF = 1;
	public static final String SPEED_KEY = "fan_speed_key";
	public static final String TEMPERATURE_KEY = "temperature_key";
	public static final String SEASON_MODE_KEY = "season_mode_key";
	public static final String CTRL_MODE_KEY = "ctrl_mode_key";
	
	public KongTiaoControl() {
//		try {
//			this.targetIP = targetIP;
//			this.targetPort = targetPort;
//			s = new DatagramSocket();
//			local = InetAddress.getByName(targetIP);
//		} catch (SocketException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
	public void setHardwareModuleId(int id) {
		cmd[14] = (byte) id;
	}
	
	public void setSeasonMode(int mode) {
		cmd[22] = (byte)mode;
	}
	
	public void setTemperature(int temp) {
		cmd[23] = (byte) temp;
	}
	
	public void setSpeed(int speedMode) {
		cmd[25] = (byte) speedMode;
	}
	
	public void setCtrlMode(int fanMode) {
		cmd[26] = (byte) fanMode;
	}
	
	public void setHalt(int status) {
		cmd[27] = (byte) status;
	}
	
	
	public byte[] getCmd() {
		return cmd;
	}
	
//	public void control() {
//		new Thread("") {
//			public void run() {
//				Log.e(TAG, " single cmd = " + new String(cmd));
//				DatagramPacket p = new DatagramPacket( cmd, cmd.length, local, targetPort);
//				try {
//					s.send(p);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}.start();
//	}
	
}
