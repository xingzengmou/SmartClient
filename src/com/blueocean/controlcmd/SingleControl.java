package com.blueocean.controlcmd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

public class SingleControl {
	private static final String TAG = "SingleControl";
	private byte[] cmd = new byte[] {0x1a, 0x1a,  0x48, 0x4d, 0x49,  0x53, 0x00, 0x05, 0x00, 0x00,
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x81, 0x02, 0x01, (byte) 0xff, 
			(byte) 0xff, (byte) 0xff, (byte) 0xff, 0x00, 0x01, 0x01, 0x00};
	
	private DatagramSocket s = null;
	private InetAddress local = null;
	private String targetIP = null;
	private int targetPort = 0;
	
	public static final int ENABLED = 1;
	public static final int DISABLED = 0;
	
	public SingleControl() {
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
	
	public void setHardwareId(int  i) {
		cmd[14] =  (byte)i;
	}
	
	public void setTargetId(int targetId) {
		cmd[22] = (byte) targetId;
	}
	
	public void setTargetState(int value) {
		cmd[23] = (byte) value;
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
