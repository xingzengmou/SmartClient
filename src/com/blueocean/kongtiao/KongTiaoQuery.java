package com.blueocean.kongtiao;

import android.content.Context;

public class KongTiaoQuery {
	private static final String TAG = "KongTiaoQuery";
	private static final byte[] cmd = new byte[] {0x1a, 0x1a, 0x48, 0x4d, 0x49, 0x53, 0x00, 0x05, 0x00, 0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, 
								0x20, 0x06, 0x14, 0x01, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, 0x00, 0x00};
	
	public KongTiaoQuery(Context context) {
		
	}
	
	public static  void setHardwareId(int id) {
		cmd[14] = (byte) id;
	}
	
	public  static byte[] getCmd() {
		return cmd;
	}
	
}
