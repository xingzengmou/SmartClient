package com.blueocean.scenecontrol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SceneControl {
	private static final String TAG = "SceneControl";
	private byte[] sceneCmd = new byte[] {0x1e, 0x1e,  0x48, 0x4d, 0x49,  0x53, 0x00, 0x05, 0x00, 0x00,
			(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x81, 0x01, 0x01, (byte) 0xff, 
			(byte) 0xff, (byte) 0xff, (byte) 0xff, 0x00, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00};
	
	private DatagramSocket s = null;
	private InetAddress local = null;
	private String targetIP = null;
	private int targetPort = 0;
	
	public SceneControl() {
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
//		
	}
	
	public void setSceneId(int sceneId) {
		sceneCmd[14] = (byte)sceneId;
	}
	
	public void setSceneCode(int sceneCode) {
		sceneCmd[23] = (byte) (((sceneCode - 1) << 1) + 1);
	}
	
	public byte[] getCmd() {
		return sceneCmd;
	}
	
//	public void control() {
//		new Thread("") {
//			public void run() {
//				DatagramPacket p = new DatagramPacket(sceneCmd, sceneCmd.length, local, targetPort);
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
